<template>
	<div>
		<div v-for="punkt in aufgabenpunktList" :key="punkt.id">
			{{ punkt.titel }}test
		</div>
	</div>
</template>

<script setup>
import { computed, ref, watch, onMounted } from "vue";
import { useRoute } from "vue-router";
import { useAufgabenStore } from "src/stores/aufgaben";

const route = useRoute();
const aufgabenStore = useAufgabenStore();

const props = defineProps({
	id: {
		type: String,
		required: true,
	},
});

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

console.log(aufgabe.value);
</script>
