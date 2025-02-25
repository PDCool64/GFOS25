<template>
	<div class="wrapper">
		<Doughnut :data="data" :options="options" ref="chartRef" />
	</div>
</template>

<script setup>
import { Doughnut } from "vue-chartjs";
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from "chart.js";
import { ref, onMounted } from "vue";
import { useKundeStore } from "src/stores/kunde";

ChartJS.register(ArcElement, Tooltip, Legend);

const chartRef = ref(null);
const kundenStore = useKundeStore();

kundenStore.fetchAllKunden().then(() => {
	console.log(kundenStore.kunden);
});

const data = ref({
	labels: ["Active", "Inactive"],
	datasets: [
		{
			backgroundColor: ["#21BA45", "#F2C037"],
			data: [
				kundenStore.activeStats.kunden,
				kundenStore.activeStats.interessenten,
			],
		},
	],
});

console.log(kundenStore.activeStats.interessenten);
console.log(kundenStore.activeStats.kunden);

const options = ref({
	responsive: true,
	maintainAspectRatio: false,
});

onMounted(() => {
	if (chartRef.value && chartRef.value.chart) {
		chartRef.value.chart.update();
	}
});
</script>

<style scoped>
.wrapper {
	width: 100%; /* Passe die Breite an */
	height: 100%; /* Passe die Höhe an */
}
</style>
