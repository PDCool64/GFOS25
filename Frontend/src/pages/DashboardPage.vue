<template>
	<div class="dashboard-container">
		<div class="top-row">
			<div class="tasks-section">
				<div class="aufgaben">
					<div class="tasks-wrapper">
						<div
							v-for="id in aufgabenIds"
							:key="id"
							class="task-item">
							<AufgabenComponent :id="id" />
						</div>
					</div>
				</div>
			</div>
			<div class="chart-section">
				<PieGraphComponent :options="options" :data="data" />
			</div>
		</div>
		<!-- Space for future components -->
		<div class="bottom-row">
			<!-- Future components will go here -->
		</div>
	</div>
</template>

<script setup>
import { ref, watch } from "vue";
import AufgabenComponent from "src/components/AufgabenComponent.vue";
import PieGraphComponent from "src/components/PieGraphComponent.vue";
import { useAufgabenStore } from "src/stores/aufgaben";

const data = ref({
	labels: ["Open", "In-Progress", "Done"],
	datasets: [
		{
			backgroundColor: ["#C10015", "#F2C037", "#21BA45"],
			data: [0, 1, 0],
		},
	],
});
watch(
	data,
	() => {
		console.log("Data changed");
	},
	{
		immediate: true,
	}
);

const aufgabenStore = useAufgabenStore();
const aufgabenIds = ref([]);
aufgabenStore.fetchAufgaben().finally(() => {
	console.log(aufgabenStore.aufgaben);
	for (const aufgabeId in aufgabenStore.aufgaben) {
		aufgabenIds.value.push(aufgabeId);
	}
	console.log(aufgabenStore.stats);
	data.value.datasets.data = [
		aufgabenStore.stats?.done,
		aufgabenStore.stats?.in_progress,
		aufgabenStore.stats?.undone,
	];
	console.log([
		aufgabenStore.stats?.done,
		aufgabenStore.stats?.in_progress,
		aufgabenStore.stats?.undone,
	]);
	console.log(data.value.datasets);
});

setTimeout(() => {
	console.log(data.value.datasets);
	data.value.datasets.data = [
		aufgabenStore.stats?.done,
		aufgabenStore.stats?.in_progress,
		aufgabenStore.stats?.undone,
	];
}, 1000);

console.log(aufgabenStore.stats);

const options = ref({
	responsive: true,
});
</script>

<style scoped>
.dashboard-container {
	display: grid;
	grid-template-rows: auto 1fr; /* First row for content, second for future components */
	gap: 1rem;
	padding: 1rem;
	height: calc(100vh - 100px);
}

.top-row {
	display: grid;
	grid-template-columns: 3fr 1fr; /* Tasks take 3/4, chart takes 1/4 */
	gap: 1rem;
}

.tasks-wrapper {
	display: flex;
	flex-wrap: wrap;
	gap: 1rem;
	padding: 0.5rem;
}

.task-item {
	flex: 0 1 calc(33.333% - 1rem); /* Three items per row with gap */
	min-width: 250px; /* Minimum width before wrapping */
}

.chart-section {
	display: flex;
	align-items: start;
	justify-content: center;
	min-height: 0;
}

.aufgaben {
	height: 100%;
	overflow-y: auto;
	padding: 0.5rem;
}

.bottom-row {
	min-height: 200px; /* Minimum height for future components */
}

/* Responsive design */
@media (max-width: 1024px) {
	.top-row {
		grid-template-columns: 1fr;
	}

	.task-item {
		flex: 0 1 calc(50% - 1rem); /* Two items per row on smaller screens */
	}
}
</style>
