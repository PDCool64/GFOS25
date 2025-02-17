<template>
	<q-item>{{ kunde.vorname }} {{ kunde.nachname }}</q-item>
</template>

<script setup>
import { useKundeStore } from "src/stores/kunde";

const props = defineProps({
	id: String,
});

const kundenStore = useKundeStore();

const kunde = kundenStore.kunden[props.id];
</script>

<style lang="scss" scoped></style>
