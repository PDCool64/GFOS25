<template>
	<div class="wrapper">
		<Pie :data="data" :options="options" />
	</div>
</template>

<script setup>
import { Pie } from "vue-chartjs";
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from "chart.js";
import { computed, onMounted, watch } from "vue";

const props = defineProps({
	data: {
		type: Object,
		default: () => ({}),
	},
	options: {
		type: Object,
		default: () => ({}),
	},
});

watch(props, () => {
	console.log("Props changed");
});

const sum = computed(() => {
	let sum = 0;
	console.log(props.data.datasets[0].data);
	for (const i in props.data) {
		sum += props.data[i];
	}
	return sum;
});

ChartJS.register(ArcElement, Tooltip, Legend);

onMounted(() => {
	console.log(props.data);
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
