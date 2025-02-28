<template>
	<q-card class="kunden-card">
		<q-card-section>
			<q-input
				v-model="vorname"
				:label="
					language['vorname'][
						accountStore.account.einstellungen?.sprache
					]
				" />

			<q-input
				v-model="nachname"
				:label="
					language['name'][
						accountStore.account.einstellungen?.sprache
					]
				" />

			<q-input v-model="email" :label="'email'" />

			<q-input
				v-model="telefonnummer"
				:label="
					language['telefonnummer'][
						accountStore.account.einstellungen?.sprache
					]
				" />
			<q-input
				v-model="firma"
				:label="
					language['firma'][
						accountStore.account.einstellungen?.sprache
					]
				" />
			<q-input v-model="utc_offset" :label="'utc - offset'" />

			<q-btn
				@click="createKunde"
				:label="
					language['kunde_erstellen'][
						accountStore.account.einstellungen?.sprache
					]
				"
				color="primary" />
		</q-card-section>
	</q-card>
</template>

<script setup>
import { useAccountStore } from "src/stores/account";
import language from "src/language";
import { useKundeStore } from "src/stores/kunde";
import { computed, ref, watch } from "vue";

const accountStore = useAccountStore();
const kundenStore = useKundeStore();

const vorname = ref("");
const nachname = ref("");
const email = ref("");
const telefonnummer = ref("");
const firma = ref("");
const ansprechpartner = ref("");
const utc_offset = ref("");

const emit = defineEmits(["creationDone"]);

async function createKunde() {
	const newKunde = {
		vorname: vorname.value,
		nachname: nachname.value,
		email: email.value,
		telefonnummer: telefonnummer.value,
		firma: firma.value,
		kontaktstatus: 0,
		utcOffset: utc_offset.value,
		ansprechpartner: accountStore.account,
		kundenstatus: 0,
	};
	console.log(newKunde);
	await kundenStore.createKunde(newKunde);

	emit("creationDone");
}
</script>

<style scoped>
.kunden-card {
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
</style>
