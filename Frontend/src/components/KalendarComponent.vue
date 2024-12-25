<template>
	<div class="kalendar-container">
		<h1>Kalendar</h1>
		<div v-if="termine.length === 0">No Termine available</div>
		<ul v-else>
			<li v-for="termin in termine" :key="termin.id">
				<h2>{{ termin.titel }}</h2>
				<p>{{ termin.beschreibung }}</p>
				<p>Start: {{ formatDate(termin.startzeit) }}</p>
				<p>End: {{ formatDate(termin.endzeit) }}</p>
			</li>
		</ul>
	</div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useKalendarStore } from "src/stores/kalendar";

const kalendarStore = useKalendarStore();
const termine = ref([]);

onMounted(async () => {
	await kalendarStore.fetchTermine();
	termine.value = kalendarStore.termine;
});

const formatDate = (date) => {
	return new Date(date).toLocaleString();
};
</script>

<style scoped>
.kalendar-container {
	padding: 1rem;
}

ul {
	list-style-type: none;
	padding: 0;
}

li {
	background-color: #f9f9f9;
	margin: 0.5rem 0;
	padding: 1rem;
	border-radius: 5px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

h2 {
	margin: 0;
	font-size: 1.5rem;
}

p {
	margin: 0.5rem 0;
}
</style>
