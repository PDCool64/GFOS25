<template>
	<div>
		<h3>
			{{ language["kunden"][accountStore.account.einstellungen.sprache] }}
		</h3>
		<q-list class="q-table">
			<KundeListComponent
				:id="'' + kunde.id"
				v-for="kunde in kundenStore.kunden"
				:key="kunde"
				kunde />
		</q-list>
		<h3>
			{{
				language["interessenten"][
					accountStore.account.einstellungen.sprache
				]
			}}
		</h3>
		<q-list class="q-table">
			<KundeListComponent
				:id="'' + kunde.id"
				v-for="kunde in kundenStore.interessenten"
				:key="kunde" />
		</q-list>
	</div>
	<div class="plus-wrapper">
		<div
			:class="['plus', { active: null === activePunktId }]"
			@click="openCreate = true">
			<q-icon name="add" size="50px">
				<q-popup-proxy cover v-model="openCreate">
					<KundeCreateComponent />
				</q-popup-proxy>
			</q-icon>
		</div>
	</div>
</template>

<script setup>
import { useKundeStore } from "src/stores/kunde";
import KundeListComponent from "src/components/KundeListComponent.vue";
import language from "src/language";
import { useAccountStore } from "src/stores/account";
import KundeCreateComponent from "src/components/create/KundeCreateComponent.vue";

const kundenStore = useKundeStore();
const accountStore = useAccountStore();

kundenStore.fetchOwnKunden().finally(() => {
	console.log(kundenStore.kunden);
});
</script>

<style lang="scss" scoped>
.q-table {
	margin: 5% 15%;
	position: relative;
	width: 70%;
}

h3 {
	text-align: center;
}

.plus-wrapper {
	display: flex;
	justify-content: center; /* Zentriert das Plus-Symbol horizontal */
	align-items: center;
	margin-top: 10px; /* Optional: Abstand nach oben */
}

.plus {
	display: flex;
	justify-content: center;
	align-items: center;
	border-radius: 50px;
	height: 75px;
	width: 75px;
	background-color: var(--q-secondary);
	cursor: pointer;
}
</style>
