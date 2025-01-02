<template>
	<div>
		<canvas id="myChart"></canvas>
	</div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import { Chart, registerables } from "chart.js";
import { useAufgabenStore } from "src/stores/aufgaben";

const aufgabenStore = useAufgabenStore();
Chart.register(...registerables);

const StatusImMonat = (monat, status) => {
	let count = 0;
	for (const aufgabe of Object.values(aufgabenStore.aufgaben)) {
		const date = new Date(aufgabe.aufgabe.faelligkeitsdatum);
		console.log(aufgabe.faelligkeitsdatum);
		if (date.getMonth() === monat) {
			if (aufgabe.aufgabe.status == status) {
				count++;
			}
		}
	}
	return count;
};

const chartRef = ref(null);

onMounted(() => {
	const ctx = document.getElementById("myChart").getContext("2d");
	const months = [
		"January",
		"February",
		"March",
		"April",
		"May",
		"June",
		"July",
		"August",
		"September",
		"October",
		"November",
		"December",
	];
	const data = {
		labels: months,
		datasets: [
			{
				label: "In Progress",
				data: months.map((month, index) => StatusImMonat(index, 1)),
				borderColor: "#F2C037",
				borderWidth: 3,
				fill: false,
			},
		],
	};

	chartRef.value = new Chart(ctx, {
		type: "line",
		data: data,
		options: {
			scales: {
				y: {
					beginAtZero: true,
				},
			},
		},
	});
});
aufgabenStore.$subscribe(() => {
	if (chartRef.value && chartRef.value.chart) {
		console.log("Irgendwas funktioniert");
	}
});
</script>
