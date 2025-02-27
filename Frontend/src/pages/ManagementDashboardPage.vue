<template>
	<q-page class="dashboard-page">
		<h3 style="font-weight: 700">
			{{
				language["willkommen_zurueck"][
					accountStore.account.einstellungen?.sprache
				]
			}}
			{{ accountStore.account.vorname }}!
		</h3>
		<div class="grid-container" v-if="visible">
			<div class="grid-item">
				<h4>
					{{
						language["letztes_jahr"][
							accountStore.account.einstellungen?.sprache
						]
					}}
				</h4>
				<AufgabenBarComponement
					:id="0"
					:year="currentYear - 1"
					:title="{
						display: false,
						text: 'Last year',
						font: {
							size: 35,
						},
					}" />
			</div>
			<div class="grid-item">
				<h4>
					{{
						language["dieses_jahr"][
							accountStore.account.einstellungen?.sprache
						]
					}}
				</h4>
				<AufgabenBarComponement
					:id="1"
					:year="currentYear"
					:title="{
						display: false,
						text: 'This year',
						font: {
							size: 35,
						},
					}" />
			</div>
			<div class="grid-item">
				<KundenstatusDoughtnutComponement
					:title="{
						display: true,
						text: 'Kunden',
						font: {
							size: 35,
						},
					}" />
			</div>
			<div class="grid-item"></div>
		</div>
	</q-page>
</template>

<script setup>
import AufgabenBarComponement from "src/components/stats/aufgaben/AufgabenBarComponement.vue";
import { useAufgabenStore } from "src/stores/aufgaben";
import { useAccountStore } from "src/stores/account";
import { ref } from "vue";
import KundenstatusDoughtnutComponement from "src/components/stats/kunden/KundenstatusDoughtnutComponement.vue";
import language from "src/language";

const aufgabenStore = useAufgabenStore();
const accountStore = useAccountStore();
const visible = ref(false);
const currentYear = new Date().getFullYear();

aufgabenStore.fetchAllAufgaben().then(() => {
	console.log(aufgabenStore.aufgaben);
	visible.value = true;
});
</script>

<style scoped>
.dashboard-page {
	padding: 16px;
}

/* CSS Grid for the charts */
.grid-container {
	display: grid;
	gap: 16px;
	/* Force exactly 2 columns per row */
	grid-template-columns: repeat(2, 1fr);
}

.grid-item {
	/* Optional styling for grid items */
}
</style>
