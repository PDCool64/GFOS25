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
</template>

<script setup>
import { useKundeStore } from "src/stores/kunde";
import KundeListComponent from "src/components/KundeListComponent.vue";
import language from "src/language";
import { useAccountStore } from "src/stores/account";

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
</style>
