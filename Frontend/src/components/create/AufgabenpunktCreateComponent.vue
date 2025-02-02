<template>
	<q-card class="aufgaben-card">
		<q-card-section>
			<q-input v-model="titel" label="Titel" />
			<q-input
				v-model="beschreibung"
				label="Beschreibung"
				type="textarea" />
			<q-btn
				@click="createPunkt"
				label="Punkt erstellen"
				color="primary" /> </q-card-section
	></q-card>
</template>

<script setup>
import { ref } from "vue";
import { useAufgabenStore } from "src/stores/aufgaben";

const props = defineProps({
	id: String,
});

const emit = defineEmits(["creationDone"]);

const aufgabenStore = useAufgabenStore();

const titel = ref("");
const beschreibung = ref("");

async function createPunkt() {
	const newPunkt = {
		titel: titel.value,
		beschreibung: beschreibung.value,
		erledigt: false,
		aufgabeId: props.id,
	};
	await aufgabenStore.createAufgabenpunkt(newPunkt);

	emit("creationDone");
}
</script>

<style scoped>
.aufgaben-card {
	max-width: 600px;
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

.q-input,
.q-select,
.q-btn {
	width: 100%;
}
</style>
