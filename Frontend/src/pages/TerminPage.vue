<template>
	<q-page class="termin-page">
		<q-card class="q-pa-md full-card">
			<q-card-section>
				<div class="text-h6">
					{{
						language["termin_erstellen"][
							accountStore.account.einstellungen.sprache
						]
					}}
				</div>
			</q-card-section>
			<q-card-section>
				<q-input
					filled
					v-model="termin.titel"
					label="Titel"
					placeholder="Geben Sie den Titel ein" />
				<q-input
					filled
					v-model="termin.beschreibung"
					label="Beschreibung"
					type="textarea"
					autogrow
					placeholder="Beschreibung des Termins" />
				<q-input
					filled
					v-model="termin.startzeit"
					label="Startzeit"
					type="datetime-local" />
				<q-input
					filled
					v-model="termin.endzeit"
					label="Endzeit"
					type="datetime-local" />
			</q-card-section>
			<q-card-actions align="right">
				<q-btn
					label="Erstellen"
					color="primary"
					@click="createTermin" />
			</q-card-actions>
		</q-card>
	</q-page>
</template>

<script setup>
import { ref } from "vue";
import { useKalendarStore } from "src/stores/kalendar";
import { useAccountStore } from "src/stores/account";
import language from "src/language";

const kalendarStore = useKalendarStore();
const accountStore = useAccountStore();

const termin = ref({
	titel: "",
	beschreibung: "",
	startzeit: "",
	endzeit: "",
});

const createTermin = async () => {
	try {
		await kalendarStore.addTermin(termin.value);
		console.log("Termin created successfully");
		termin.value = {
			titel: "",
			beschreibung: "",
			startzeit: "",
			endzeit: "",
		};
	} catch (error) {
		console.error("Error creating termin:", error);
	}
};
</script>

<style lang="scss" scoped>
.termin-page {
	height: 100vh; /* Full viewport height */
	display: flex;
	align-items: center;
	justify-content: center;
	padding: 0;
	margin: 0;
}

.full-card {
	width: 100%;
	height: 100%;
	margin: 0;
	max-width: none;
	border-radius: 0; /* Optional: remove card rounding for a true full-screen look */
}
</style>
