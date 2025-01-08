<template>
	<div v-if="aufgabe" @click="onClick" :class="taskClass" class="wrapper">
		<div class="header-wrapper text-center">
			<h3 class="q-pa-xs">{{ aufgabe?.titel }}</h3>
		</div>
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
					<q-popup-proxy v-model="openKunde">
						<KundeComponent />
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
</template>

<script setup>
import { computed, ref } from "vue";
import { useAufgabenStore } from "src/stores/aufgaben";
import KundeComponent from "./KundeComponent.vue";

const aufgabenStore = useAufgabenStore();

const openKunde = ref(false);

const props = defineProps({
	id: Number,
});

const aufgabe = computed(() => aufgabenStore.aufgaben[props.id].aufgabe);

const done = computed(() => {
	let summe = 0;
	for (const punkt of aufgabe.value.aufgabenpunktList) {
		summe += punkt.erledigt;
	}
	return summe;
});

console.log(done.value);

const taskClass = computed(() => {
	// Define your task class logic here
	return "task-class";
});

const displayDate = (date) => {
	// Define your date display logic here
	return date.toLocaleDateString();
};

const onClick = () => {
	// Define your click handler logic here
	console.log("Task clicked");
};

const isLastPunkt = (punkt) => {
	if (aufgabe.value.aufgabenpunktList === undefined) return false;
	return (
		aufgabe.value.aufgabenpunktList.indexOf(punkt) ===
		aufgabe.value.aufgabenpunktList.length - 1
	);
};
</script>

<style scoped>
.wrapper {
	display: flex;
	max-width: 500px;
	flex-direction: column;
	align-items: center;
	background-color: var(--q-primary);
	border-radius: 5px;
	cursor: pointer;
	user-select: none;
}

h3 {
	font-size: 1.5em;
	font-family: "Roboto", sans-serif;
	color: white;
	margin: 0;
	line-height: 1.5em;
	border-radius: 15px;
}

.outer {
	width: 85%;
	margin-bottom: 10%;
	padding: 0;
	list-style-type: none;
	background-color: var(--q-secondary);
	border-radius: 10px;
	box-shadow: 2px 4px 5px rgba(0, 0, 0, 0.3);
	overflow-x: hidden;
}

.inner {
	padding: 10px 20px;
	color: white;
	display: flex;
}

.header-wrapper {
	display: flex;
	flex-direction: row;
	justify-content: center;
	width: 100%;
	padding: 10px 20px;
}

.icon {
	margin: auto 0;
	margin-right: 5px;
	position: relative;
	top: 0;
	left: 0;
}
</style>
