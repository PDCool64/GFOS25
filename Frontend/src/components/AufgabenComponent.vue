<template>
	<div v-if="aufgabe" @click="onClick" :class="taskClass" class="wrapper">
		<div class="header-wrapper text-center">
			<h3 class="q-pa-xs">{{ aufgabe?.titel }}</h3>
		</div>
		<BeschreibungConponement :id="props.id" />
	</div>
</template>

<script setup>
import { computed, ref } from "vue";
import { useAufgabenStore } from "src/stores/aufgaben";
import { useRouter } from "vue-router";
import BeschreibungConponement from "./aufgabe/BeschreibungComponent.vue";

const router = useRouter();

const aufgabenStore = useAufgabenStore();

const openKunde = ref(false);

const props = defineProps({
	id: String,
});

const aufgabe = computed(() => {
	return aufgabenStore.aufgaben[props.id];
});

const done = computed(() => {
	let summe = 0;
	for (const punkt of aufgabe.value.aufgabenpunktList) {
		summe += punkt.erledigt;
	}
	return summe;
});

const taskClass = computed(() => {
	// Define your task class logic here
	return "task-class";
});

const displayDate = (date) => {
	// Define your date display logic here
	return date.toLocaleDateString();
};

const onClick = async () => {
	await router.push("/aufgaben/" + props.id);
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
	background-color: var(--q-secondary);
	border-radius: 5px;
	cursor: pointer;
	user-select: none;
	box-shadow: rgba(17, 17, 26, 0.1) 0px 4px 16px,
		rgba(17, 17, 26, 0.1) 0px 8px 24px, rgba(17, 17, 26, 0.1) 0px 16px 56px;
}

h3 {
	font-size: 2em;
	font: Noto Sans;
	font-weight: bold;
	color: var(--q-primary);
}

.outer {
	width: 100%;
	margin-bottom: 10%;
	padding: 0;
	list-style-type: none;
	background-color: var(--q-secondary);
	border-radius: 10px;
	overflow-x: hidden;
}

.inner {
	padding: 10px 20px;
	color: white;
	display: flex;
}

.icon {
	margin: auto 0;
	margin-right: 5px;
	position: relative;
	top: 0;
	left: 0;
}
</style>
