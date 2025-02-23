<template>
	<q-page class="termin-page">
		<q-card class="q-pa-md full-card">
			<q-card-section>
				<div class="text-h6">
					{{
						language["termin_erstellen"][
							accountStore.account.einstellungen?.sprache
						]
					}}
				</div>
			</q-card-section>
			<q-card-section>
				<q-input
					filled
					v-model="termin.titel"
					:label="
						language['titel'][
							accountStore.account.einstellungen?.sprache
						]
					"
					placeholder="Geben Sie den Titel ein" />
				<q-input
					filled
					v-model="termin.beschreibung"
					:label="
						language['beschreibung'][
							accountStore.account.einstellungen?.sprache
						]
					"
					type="textarea"
					autogrow
					placeholder="Beschreibung des Termins" />
				<q-input
					filled
					v-model="termin.startzeit"
					:label="
						language['startzeit'][
							accountStore.account.einstellungen?.sprache
						]
					"
					type="datetime-local" />
				<q-input
					filled
					v-model="termin.endzeit"
					:label="
						language['endzeit'][
							accountStore.account.einstellungen?.sprache
						]
					"
					type="datetime-local" />
			</q-card-section>
			<q-card-actions align="right">
				<q-btn
					:label="
						language['erstellen'][
							accountStore.account.einstellungen?.sprache
						]
					"
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

const accountStore = useAccountStore();
const kalendarStore = useKalendarStore();

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
