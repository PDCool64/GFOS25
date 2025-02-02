<template>
	<div id="aufgabe">
		<h1 class="q-pa-xs">{{ aufgabe?.titel }}</h1>
		<div class="wrapper">
			<div>
				<BeschreibungComponent
					:id="route.params.id"
					style="height: 50vh" />
				<div
					:class="[
						{ done: aufgabe?.status === 1 },
						{ undone: aufgabe?.status === 2 },
						{ inprogress: aufgabe?.status === 0 },
						'bar',
					]">
				</div>
			</div>
			<div>
				<Unterpunkte :id="route.params.id" />
			</div>
		</div>
	</div>
</template>

<script setup>
import { ref, onMounted } from "vue";
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
</script>

<style scoped>
#aufgabe {
	align-items: center;
	justify-content: center;
	text-align: center;
	display: flex; /* Flexbox hinzufügen */
	padding: 0;
	flex-direction: column;
}
h1 {
	font-size: 8em;
	font: Noto Sans;
	font-weight: bold;
	color: var(--q-primary);
}
.wrapper {
	align-items: center;
	justify-content: center;
	text-align: center;
	display: flex; /* Flexbox hinzufügen */
	padding: 20px;
	margin: 20px;
}

.bar {
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
