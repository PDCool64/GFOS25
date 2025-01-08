<template>
    <q-card :class="['kunde-card']">
        <q-card-section>
            <q-input v-model="name" label="Name" readonly />
            <q-input v-model="vorname" label="Vorname" readonly />
            <q-input v-model="email" label="Email" readonly />
            <q-input v-model="telefon" label="Telefon" readonly />
        </q-card-section>
    </q-card>
</template>

<script setup>
import { ref } from 'vue';

const name = ref('Mustermann');
const vorname = ref('Max');
const email = ref('max.mustermann@example.com');
const telefon = ref('0123456789');
</script>

<style lang="scss" scoped>
.kunde-card {
    max-width: 400px;
    margin: 0 auto;
    padding: 20px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    border-radius: 8px;
}

.q-card-section {
    display: flex;
    flex-direction: column;
    gap: 15px;
}

.q-input {
    width: 100%;
}
</style>