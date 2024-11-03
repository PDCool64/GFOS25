<template>
    <div class="grid">
        <div class="row">
            <q-card class="aufgaben">
                <div v-for="id in aufgabenIds" :key="id">
                    <AufgabenComponent :id="id" />
                </div>
            </q-card>
            <div class="chart">
                <PieGraphComponent :options="options" :data="data" />
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import AufgabenComponent from 'src/components/AufgabenComponent.vue';
import PieGraphComponent from 'src/components/PieGraphComponent.vue';

const aufgabenIds = ref([1, 2, 3]);

const data = ref({
    labels: ['Open', 'In-Progress', 'Done'],
    datasets: [
        {
            backgroundColor: ['#C10015', '#F2C037', '#21BA45'],
            data: [0, 2, 1],
        },
    ],
});

const options = ref({
    responsive: true,
});
</script>

<style scoped>
.aufgaben {
    margin: 1em;
    display: flex;
    width: max-content;
    height: max-content;
}

.grid {
    display: grid;
    padding: 1em;
    grid-template-columns: 1f 1fr 1fr 3fr;
}

.chart {
    margin: 1em;
    width: min-content;
}

.row {
    display: flex;
    flex-direction: row;
    align-items: center;
    width: 100%;
    height: 100%;
}
</style>
