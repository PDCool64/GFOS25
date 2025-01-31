<template>
	<div id="aufgabe">
		<div v-if="aufgabe" @click="onClick" class="wrapper">
			<div class="outer">
				<div class="row">
					<div class="inner">
						<q-icon name="edit" class="icon" />
						{{ displayDate(new Date(aufgabe.erstellungsdatum)) }}
					</div>
					<div class="inner">
						<q-icon name="schedule" class="icon" />
						{{ displayDate(new Date(aufgabe.faelligkeitsdatum)) }}
					</div>
					<div
						class="inner"
						@contextmenu.prevent="
							(openKunde = true),
								console.log('Something happened'),
								console.log(openKunde)
						">
						<q-icon name="person" class="icon relative" />
						<div>
							{{ aufgabe.kunde?.vorname }}
							{{ aufgabe.kunde?.nachname }}
						</div>
						<q-popup-proxy v-model="openKunde">
							<KundeComponent />
						</q-popup-proxy>
					</div>
				</div>
				<div class="inner">
					<q-icon class="icon" name="description" />
					<div>
						{{ aufgabe.beschreibung }}
					</div>
				</div>
				<div class="inner">
					<q-icon class="icon" name="task_alt" />
					<div>
						{{ done }}/{{ aufgabe.aufgabenpunktList?.length }}
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script setup>
import { computed, ref, onMounted } from "vue";
import { useAufgabenStore } from "src/stores/aufgaben";
import KundeComponent from "../KundeComponent.vue";

const props = defineProps({
	id: String,
});

console.log(props.id);

const aufgabe = ref(null);

const openKunde = ref(false);

const done = computed(() => {
	if (aufgabe.value == null) return 0;
	let summe = 0;
	for (const punkt of aufgabe.value.aufgabenpunktList) {
		summe += punkt.erledigt;
	}
	return summe;
});
const aufgabenStore = useAufgabenStore();

const displayDate = (date) => {
	return new Date(date).toLocaleDateString();
};

const loadAufgabe = (id) => {
	console.log(aufgabenStore.aufgaben); // Ausgabe des Objekts in der Konsole
	const task = aufgabenStore.aufgaben[id];
	if (task) {
		aufgabe.value = task;
	} else {
		console.error(`Aufgabe mit ID ${id} nicht gefunden`);
	}
};

onMounted(() => {
	const id = props.id;
	console.log(id);
	if (id) {
		aufgabenStore.fetchAufgabe(id).finally(() => loadAufgabe(id));
	}
});

console.log(aufgabe.value);
</script>

<style scoped>
#aufgabe {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100%;
}

.wrapper {
	display: flex;
	width: auto;
	height: 100%;
	flex-direction: column;
	align-items: center;
	background-color: var(--q-secondary);
	border-radius: 5px;
	cursor: pointer;
	user-select: none;
	box-shadow: rgba(17, 17, 26, 0.1) 0px 4px 16px,
		rgba(17, 17, 26, 0.1) 0px 8px 24px, rgba(17, 17, 26, 0.1) 0px 16px 56px;
}

h3 {
	font-size: auto;
	font: Noto Sans;
	font-weight: bold;
	color: var(--q-primary);
}

.outer {
	width: 85%;
	margin-bottom: 10%;
	margin-top: 10%;
	padding: 0;
	list-style-type: none;
	background-color: var(--q-secondary);
	border-radius: 10px;
	overflow-x: hidden;
}

.inner {
	font-size: auto;
	padding: 10px 20px;
	color: white;
	display: flex;
}

.icon {
	margin: auto 0;
	margin-right: 5px;
	position: relative;
	top: 0;
}
</style>
