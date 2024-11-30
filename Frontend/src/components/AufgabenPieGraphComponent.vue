<template>
	<div class="wrapper">
		<Pie :data="data" :options="options" ref="chartRef" />
	</div>
</template>

<script setup>
import { Pie } from "vue-chartjs";
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from "chart.js";
import { onMounted, ref } from "vue";
import { useAufgabenStore } from "src/stores/aufgaben";

ChartJS.register(ArcElement, Tooltip, Legend);

const chartRef = ref(null);
const aufgabenStore = useAufgabenStore();

const data = ref({
	labels: ["Done", "In-Progress", "Open"],
	datasets: [
		{
			backgroundColor: ["#21BA45", "#F2C037", "#C10015"],
			data: [
				aufgabenStore.stats.done,
				aufgabenStore.stats.in_progress,
				aufgabenStore.stats.undone,
			],
		},
	],
});

const options = ref({
	responsive: true,
	maintainAspectRatio: false,
});

onMounted(() => {
	if (chartRef.value && chartRef.value.chart) {
		chartRef.value.chart.update();
	}
});

aufgabenStore.$subscribe(() => {
	if (chartRef.value && chartRef.value.chart) {
		data.value = {
			...data.value,
			datasets: [
				{
					backgroundColor: ["#21BA45", "#F2C037", "#C10015"],
					data: [
						aufgabenStore.stats.done,
						aufgabenStore.stats.in_progress,
						aufgabenStore.stats.undone,
					],
				},
			],
		};
		chartRef.value.chart.update();
	}
});
</script>

<style scoped>
.wrapper {
	width: 100%;
	height: 100%;
	display: flex;
	justify-content: center;
	align-items: center;
}
</style>
