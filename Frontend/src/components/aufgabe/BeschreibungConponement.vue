<template>
	<div id="aufgabe">
		<div v-if="aufgabe" @click="onClick" class="wrapper">
			<div class="header-wrapper text-center"></div>
			<div class="outer">
				<div class="row">
					<div class="inner">
						<q-icon name="edit" class="icon" />
						{{ displayDate(new Date(aufgabe?.erstellungsdatum)) }}
					</div>
					<div class="inner">
						<q-icon name="schedule" class="icon" />
						{{ displayDate(new Date(aufgabe?.faelligkeitsdatum)) }}
					</div>
					<div class="inner" @mouseenter="openKunde = true">
						<q-icon name="person" class="icon relative" />
						<div>
							{{ aufgabe?.kunde?.vorname }}
							{{ aufgabe?.kunde?.nachname }}
						</div>
						<q-popup-proxy
							v-model="openKunde"
							@mouseleave="openKunde = false">
							<KundeComponent></KundeComponent>
						</q-popup-proxy>
					</div>
				</div>
				<div class="inner">
					<q-icon class="icon" name="description" />
					<div>
						{{ aufgabe?.beschreibung }}
					</div>
				</div>
				<div class="inner">
					<q-icon class="icon" name="task_alt" />
					<div>{{ done }}/{{ aufgabe.aufgabenpunktList.length }}</div>
				</div>
			</div>
		</div>
		<div
			id="bar"
			:class="[
				{ done: aufgabe?.status === 1 },
				{ undone: aufgabe?.status === 2 },
				{ inprogress: aufgabe?.status === 0 },
			]"></div>
	</div>
</template>

<script setup>
import { computed, ref, watch, onMounted } from "vue";
import { useRoute } from "vue-router";
import { useAufgabenStore } from "src/stores/aufgaben";
import KundeComponent from "../KundeComponent.vue";

const openKunde = ref(false);
const done = ref(false);

const route = useRoute();
const aufgabenStore = useAufgabenStore();

const aufgabe = ref(null);

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
	const id = route.params.id;
	console.log(id);
	if (id) {
		aufgabenStore.fetchAufgabe(id).finally(() => loadAufgabe(id));
	}
});

const onClick = () => {
	console.log(aufgabe.value);
};

console.log(aufgabe.value);
</script>

<style scoped>
#aufgabe {
	display: flex;
	justify-content: center;
	align-items: center;
}

.wrapper {
	display: flex;
	height: 50vh;
	width: auto;
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

#bar {
	width: 100%;
	height: 10px;
	background-color: var(--q-primary);
}

.done {
	background-color: #a5efc2;
}

.undone {
	background-color: #f7b7b7;
}

.inprogress {
	background-color: #f7f7b7;
}
</style>
