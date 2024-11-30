<template>
	<div @click="onClick" :class="taskClass" class="wrapper">
		<div class="header-wrapper">
			<h2>{{ aufgabe?.titel }}</h2>
			<div class="icon-wrapper">
				<q-icon
					class="icon"
					:name="icon"
					:color="taskClass"
					size="3em" />
			</div>
		</div>
		<ul>
			<li>{{ aufgabe?.beschreibung }}</li>
			<!-- <li>{{ aufgabe }}</li> -->
			<li>
				Erstellt am
				{{ displayDate(new Date(aufgabe?.erstellungsdatum)) }}
			</li>
			<li>
				Fällig am
				{{ displayDate(new Date(aufgabe?.faelligkeitsdatum)) }}
			</li>
		</ul>
	</div>
</template>

<script setup>
import { computed, ref } from "vue";
import { useAufgabenStore } from "src/stores/aufgaben";

const aufgabenStore = useAufgabenStore();

defineOptions({
	name: "AufgabenComponent",
});
const props = defineProps({
	id: {
		type: String,
		required: true,
	},
});

const icon = ref("check");
const displayDate = (date) => {
	return new Date(date).toLocaleDateString();
};

const onClick = () => {
	aufgabe.value.status = (aufgabe.value.status + 1) % 3;
	switch (aufgabe.value.status) {
		case 0:
			icon.value = "clear";
			break;
		case 1:
			icon.value = "hourglass_empty";
			break;
		case 2:
			icon.value = "check";
			break;
		default:
			icon.value = "question_mark";
			break;
	}
};
const taskClass = computed(() => {
	switch (aufgabe.value.status % 3) {
		case 0:
			return "open";
		case 1:
			return "in-progress";
		case 2:
			return "closed";
		default:
			return "bg-primary";
	}
});
const aufgabe = ref(aufgabenStore.aufgaben[props.id].aufgabe);
console.log(aufgabe.value);

onClick();
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
	margin: 1em;
	user-select: none;
}

.open {
	background-color: var(--q-negative);
}

.in-progress {
	background-color: var(--q-warning);
}

.closed {
	background-color: var(--q-positive);
}

h2 {
	font-size: 2em;
	font-family: "Roboto", sans-serif;
	color: white;
	margin: 0;
	margin-right: auto;

	line-height: 1.5em;
}

ul {
	width: 85%;
	margin-bottom: 10%;
	padding: 0;
	list-style-type: none;
	background-color: var(--q-secondary);
	border-radius: 10px;
	box-shadow: 2px 4px 5px rgba(0, 0, 0, 0.3);
	overflow-x: hidden;
}

li {
	font-size: 1.5em;
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
	flex-grow: 0;
	box-shadow: #0008 3px 3px 8px;
	border-radius: 20%;
	margin-left: auto;
	margin-right: 10%;
}

.icon-wrapper {
	display: flex;
	justify-content: center;
	align-items: center;
	width: max-content;
	margin: 0 10px;
}
</style>
