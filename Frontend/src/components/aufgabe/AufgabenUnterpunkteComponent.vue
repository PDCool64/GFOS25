<template>
	<div class="wrapper">
		<div><h1>Unterpunkte</h1></div>
		<div
			v-for="punkt in aufgabenpunktList"
			:key="punkt.id"
			:class="[
				'titel',
				{ active: punkt.id === activePunktId },
				{ done: punkt.erledigt },
				{ undone: !punkt.erledigt },
			]"
			@click="onClick(punkt.id)">
			{{ punkt.titel }}
			<div class="Beschreibung" v-if="punkt.id === activePunktId">
				{{ punkt.beschreibung }}
			</div>
		</div>
		<div class="plus-wrapper">
			<div
				:class="['plus', { active: null === activePunktId }]"
				@click="openCreate = true">
				<q-icon name="add" size="50px">
					<q-popup-proxy cover v-model="openCreate">
						<AufgabenpunktCreateComponent
							:id="props.id"
							@creation-done="
								aufgabenStore
									.fetchAufgabe(id)
									.finally(() => loadAufgabe(id)),
									(openCreate = false)
							" />
					</q-popup-proxy>
				</q-icon>
			</div>
		</div>
	</div>
</template>

<script setup>
import { computed, ref, watch, onMounted } from "vue";
import { useRoute } from "vue-router";
import { useAufgabenStore } from "src/stores/aufgaben";
import AufgabenpunktCreateComponent from "../create/AufgabenpunktCreateComponent.vue";

const route = useRoute();
const aufgabenStore = useAufgabenStore();

const props = defineProps({
	id: {
		type: String,
		required: true,
	},
});

const openCreate = ref(false);

const aufgabe = ref(null);

const aufgabenpunktList = ref(null);

const displayDate = (date) => {
	return new Date(date).toLocaleDateString();
};

const loadAufgabe = (id) => {
	console.log(aufgabenStore.aufgaben); // Ausgabe des Objekts in der Konsole
	const task = aufgabenStore.aufgaben[id];
	if (task) {
		aufgabe.value = task;
		aufgabenpunktList.value = task.aufgabenpunktList;
	} else {
		console.error(`Aufgabe mit ID ${id} nicht gefunden`);
	}
};

onMounted(() => {
	const id = route.params.id;
	console.log(id);
	if (id) {
		aufgabenStore.fetchAufgabe(id).finally(() => loadAufgabe(id));
	}
});

const activePunktId = ref(null);

const onClick = (id) => {
	if (activePunktId.value === id) {
		const punkt = aufgabenpunktList.value.find((p) => p.id === id);
		if (punkt) {
			punkt.erledigt = !punkt.erledigt;
		}
		// database update
		aufgabenStore.togglePunkt(id);
	} else {
		activePunktId.value = id;
	}
};
</script>

<style scoped>
.wrapper {
	box-shadow: rgba(17, 17, 26, 0.1) 0px 4px 16px,
		rgba(17, 17, 26, 0.1) 0px 8px 24px, rgba(17, 17, 26, 0.1) 0px 16px 56px;
	border-radius: 5px;
	display: flex;
	flex-direction: column;
	width: 20vw;
	min-height: 50vh;
	overflow-y: auto;
	padding: 10px;
}

.titel {
	border-radius: 5px;
	flex: 0.5;
	margin: 3px 0;
	cursor: pointer;
	width: 100%;
	background-color: var(--q-secondary);
	font-size: 2em;
	font: Noto Sans;
	font-weight: bold;
	color: var(--q-primary);
	transition: flex 0.7s ease-in;
}

.plus-wrapper {
	display: flex;
	justify-content: center; /* Zentriert das Plus-Symbol horizontal */
	align-items: center;
	margin-top: 10px; /* Optional: Abstand nach oben */
}

.plus {
	display: flex;
	justify-content: center;
	align-items: center;
	border-radius: 50px;
	height: 75px;
	width: 75px;
	background-color: var(--q-secondary);
	cursor: pointer;
}

.done {
	background-color: #a5efc2;
}

.undone {
	background-color: #f7b7b7;
}

.active.done {
	flex: 5;
}

.active .q-icon {
	font-size: 150px;
}

.active.undone {
	flex: 5;
}

.Beschreibung {
	font-size: 0.5em;
	font: Noto Sans;
	color: black;
}

h1 {
	font-size: 3em;
	font: Noto Sans;
	font-weight: bold;
	color: var(--q-primary);
}
</style>
