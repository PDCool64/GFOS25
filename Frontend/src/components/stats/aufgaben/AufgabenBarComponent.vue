<template>
	<div class="bar-container">
		<canvas :id="'myChart' + props.id"></canvas>
	</div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import { Chart, registerables } from "chart.js";
import { useAufgabenStore } from "src/stores/aufgaben";
import { format } from "quasar";

const props = defineProps({
	year: {
		type: Number,
		default: new Date().getFullYear(),
	},
	id: {
		required: true,
	},
	title: {
		type: Object,
	},
});

const aufgabenStore = useAufgabenStore();
Chart.register(...registerables);

const StatusImMonat = (monat, status) => {
	let count = 0;
	for (const aufgabe of Object.values(aufgabenStore.aufgaben)) {
		const date = new Date(aufgabe.faelligkeitsdatum);
		if (date.getMonth() === monat && date.getFullYear() === props.year) {
			if (aufgabe.status == status) {
				count++;
			}
		}
	}
	return count;
};

const chartRef = ref(null);

onMounted(() => {
	const ctx = document.getElementById("myChart" + props.id).getContext("2d");
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
				pointBackgroundColor: "#F2C037", // Punktfarbe
				radius: 3, // Punktgröße
				tension: 0.3,
			},
			{
				label: "done",
				data: months.map((month, index) => StatusImMonat(index, 2)),
				borderColor: "#21BA45",
				borderWidth: 3,
				fill: false,
				pointBackgroundColor: "#21BA45", // Punktfarbe
				radius: 3, // Punktgröße
				tension: 0.3,
			},
			{
				label: "undone",
				data: months.map((month, index) => StatusImMonat(index, 0)),
				borderColor: "#C10015",
				borderWidth: 3,
				fill: false,
				pointBackgroundColor: "#C10015",
				radius: 3,
				tension: 0.3,
			},
		],
	};

	chartRef.value = new Chart(ctx, {
		type: "line",
		data: data,
		options: {
			scales: {
				x: {
					grid: {
						display: false, // Raster entfernen
					},
					ticks: {
						font: {
							size: 15, // Schriftgröße der x-Achse
						},
					},
					border: {
						display: true,
						width: 1.5, // Dicke der x-Achse
						color: "black", // Farbe der x-Achse
					},
				},
				y: {
					beginAtZero: true,
					grid: {
						display: false, // Raster entfernen
					},
					ticks: {
						stepSize: 1, // Nur ganze Zahlen anzeigen
						font: {
							size: 20, // Schriftgröße der y-Achse
						},
						// Farbe der y-Achsenschrift
					},
					border: {
						display: true,
						width: 1.5, // Dicke der x-Achse
						color: "black", // Farbe der x-Achse
					},
				},
			},
			plugins: {
				title: props.title,
			},
			elements: {
				line: {
					borderWidth: 5, // Linienbreite
				},
				point: {
					radius: 12, // Punktgröße
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

<style scoped>
.bar-container {
	width: 35vw;
	height: auto;
	display: flex;
	align-items: center;
}
@media (max-width: 1024px) {
	.bar-container {
		width: 100vw;
		height: auto;
	}
}
</style>
