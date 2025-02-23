<template>
	<q-card class="aufgaben-card">
		<q-card-section>
			<q-input
				v-model="titel"
				:label="
					language['titel'][
						accountStore.account.einstellungen?.sprache
					]
				" />
			<q-input
				v-model="beschreibung"
				:label="
					language['beschreibung'][
						accountStore.account.einstellungen?.sprache
					]
				"
				type="textarea" />
			<!-- Input with Date-Picker as a popup -->
			<q-input
				filled
				v-model="faelligkeitsdatum"
				mask="date"
				:rules="['date']"
				:label="
					language['faelligkeitsdatum'][
						accountStore.account.einstellungen?.sprache
					]
				">
				<template v-slot:append>
					<q-icon name="event" class="cursor-pointer">
						<q-popup-proxy
							cover
							transition-show="scale"
							transition-hide="scale">
							<q-date v-model="faelligkeitsdatum">
								<div class="row items-center justify-end">
									<q-btn
										v-close-popup
										label="Close"
										color="primary"
										flat />
								</div>
							</q-date>
						</q-popup-proxy>
					</q-icon>
				</template>
			</q-input>
			<q-select
				v-model="prioritaet"
				:options="prioritaetOptions"
				:label="
					language['priorität'][
						accountStore.account.einstellungen?.sprache
					]
				" />
			<q-btn
				@click="createAufgabe"
				:label="
					language['aufgabe_erstellen'][
						accountStore.account.einstellungen?.sprache
					]
				"
				color="primary" />
		</q-card-section>
	</q-card>
</template>

<script setup>
import { ref } from "vue";
import { useAufgabenStore } from "src/stores/aufgaben";
import { useAccountStore } from "src/stores/account";
import language from "src/language";

const accountStore = useAccountStore();

const emit = defineEmits(["creationDone"]);

const aufgabenStore = useAufgabenStore();

const titel = ref("");
const beschreibung = ref("");
const faelligkeitsdatum = ref("");
const prioritaet = ref(null);
const status = ref(null);

const prioritaetOptions = [
	{ label: "Low", value: 0 },
	{ label: "Medium", value: 1 },
	{ label: "High", value: 2 },
];

const statusOptions = [
	{ label: "Open", value: 0 },
	{ label: "In Progress", value: 1 },
	{ label: "Completed", value: 2 },
];

const formatDate = (date) => {
	const year = date.getFullYear();
	const month = String(date.getMonth() + 1).padStart(2, "0"); // Months are zero-based
	const day = String(date.getDate()).padStart(2, "0");
	return `${year}-${month}-${day}`;
};

async function createAufgabe() {
	const newAufgabe = {
		titel: titel.value,
		beschreibung: beschreibung.value,
		erstellungsdatum: formatDate(new Date()),
		faelligkeitsdatum: faelligkeitsdatum.value,
		prioritaet: prioritaet.value.value,
		status: 0,
	};
	await aufgabenStore.createAufgabe(newAufgabe);

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
