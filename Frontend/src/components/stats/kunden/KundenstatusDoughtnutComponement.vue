<template>
	<div class="wrapper">
		<Doughnut
			:data="data"
			:options="options"
			ref="chartRef"
			v-if="visible" />
	</div>
</template>

<script setup>
import { Doughnut } from "vue-chartjs";
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from "chart.js";
import { ref, onMounted } from "vue";
import { useKundeStore } from "src/stores/kunde";

ChartJS.register(ArcElement, Tooltip, Legend);

const visible = ref(false);
const chartRef = ref(null);
const kundenStore = useKundeStore();

const data = ref({
	labels: ["Interested", "no contact", "contacted", "customer"],
});

kundenStore.fetchAllKunden().then(() => {
	console.log(kundenStore.kunden);
	data.value = {
		...data.value,
		datasets: [
			{
				backgroundColor: ["#21BA45", "#F2C037", "#C10015", "#FF5733"],
				data: [
					kundenStore.activeStats.interessenten,
					kundenStore.activeStats.kein_Kontakt,
					kundenStore.activeStats.kontakt_aufgenommen,
					kundenStore.activeStats.kunden,
				],
			},
		],
	};
	visible.value = true;
});

console.log(kundenStore.activeStats.interessenten);
console.log(kundenStore.activeStats.kein_Kontakt);
console.log(kundenStore.activeStats.kontakt_aufgenommen);
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
