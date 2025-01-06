<template>
	<q-card class="aufgaben-card">
		<q-card-section>
			<q-input v-model="titel" label="Titel" />
			<q-input v-model="beschreibung" label="Beschreibung" type="textarea" />
			<!-- Input with Date-Picker as a popup -->
			<q-input filled v-model="faelligkeitsdatum" mask="date" :rules="['date']" label="Fälligkeitsdatum">
				<template v-slot:append>
					<q-icon name="event" class="cursor-pointer">
						<q-popup-proxy cover transition-show="scale" transition-hide="scale">
							<q-date v-model="faelligkeitsdatum">
								<div class="row items-center justify-end">
									<q-btn v-close-popup label="Close" color="primary" flat />
								</div>
							</q-date>
						</q-popup-proxy>
					</q-icon>
				</template>
			</q-input>
			<q-select v-model="prioritaet" :options="prioritaetOptions" label="Priorität" />
			<q-btn @click="createAufgabe" label="Create Aufgabe" color="primary" />
		</q-card-section>
	</q-card>
</template>

<script setup>
import { ref } from 'vue';
import { useAufgabenStore } from 'src/stores/aufgaben';
import { defineEmits } from 'vue';
const emit = defineEmits(["creationDone"])

const aufgabenStore = useAufgabenStore();

const titel = ref('');
const beschreibung = ref('');
const erstellungsdatum = ref('');
const faelligkeitsdatum = ref('');
const prioritaet = ref(null);
const status = ref(null);

const prioritaetOptions = [
	{ label: 'Low', value: 0 },
	{ label: 'Medium', value: 1 },
	{ label: 'High', value: 2 },
];

const statusOptions = [
	{ label: 'Open', value: 0 },
	{ label: 'In Progress', value: 1 },
	{ label: 'Completed', value: 2 },
];

async function createAufgabe() {
	const newAufgabe = {
		titel: titel.value,
		beschreibung: beschreibung.value,
		erstellungsdatum: erstellungsdatum.value,
		faelligkeitsdatum: faelligkeitsdatum.value,
		prioritaet: prioritaet.value.value,
		status: 0,
	};
	await aufgabenStore.createAufgabe(newAufgabe);

	emit("creationDone");
};
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
