<template>
    <div class="wrapper">
        <Pie :data="data" :options="options" />
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { Pie } from 'vue-chartjs';
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js';

ChartJS.register(ArcElement, Tooltip, Legend);

const data = ref({
    labels: ['January', 'February', 'March'],
    datasets: [
        {
            backgroundColor: ['#FF6633', '#FFB399', '#FF33FF'],
            data: [30, 20, 12],
        },
    ],
});
const options = ref({
    responsive: true,
    maintainAspectRatio: true,
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
