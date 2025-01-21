<template>
	<div id="aufgabe">
		<h1 class="q-pa-xs">{{ aufgabe?.titel }}</h1>
		<div id="box1">
			<BeschreibungComponent />
		</div>
		<div>
			<Unterpunkte />
		</div>
	</div>
</template>

<script setup>
import { computed, ref, watch, onMounted } from "vue";
import { useRoute } from "vue-router";
import { useAufgabenStore } from "src/stores/aufgaben";
import BeschreibungComponent from "src/components/aufgabe/BeschreibungConponement.vue";
import Unterpunkte from "src/components/aufgabe/AufgabenUnterpunkte.vue";

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

console.log(aufgabe.value);
</script>

<style scoped>
#aufgabe {
	margin-top: 0;
	margin-left: 10%;
	margin-right: 10%;
	align-items: center;
	justify-content: center;
	text-align: center;
	display: flex; /* Flexbox hinzufügen */
	flex-direction: column; /* Elemente vertikal anordnen */
	padding: 0;
}
h1 {
	font-size: 8em;
	font: Noto Sans;
	font-weight: bold;
	color: var(--q-primary);
}
</style>
