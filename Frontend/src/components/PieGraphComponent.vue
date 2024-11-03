<template>
    <div class="wrapper">
        <Pie :data="data" :options="options" />
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { Pie } from 'vue-chartjs';
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js';
import { defineOptions, defineProps } from 'vue';

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

ChartJS.register(ArcElement, Tooltip, Legend);
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
