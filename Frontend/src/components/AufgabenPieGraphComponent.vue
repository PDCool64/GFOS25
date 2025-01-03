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
				aufgabenStore.activeStats.done,
				aufgabenStore.activeStats.in_progress,
				aufgabenStore.activeStats.undone,
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
						aufgabenStore.activeStats.done,
						aufgabenStore.activeStats.in_progress,
						aufgabenStore.activeStats.undone,
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
width: auto;
height: auto;
}
</style>
