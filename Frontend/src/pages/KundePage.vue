<template>
	<div>
		<div>
			<h3>
				{{ language["kunden"][accountStore.sprache] }}
			</h3>
			<q-list class="q-table">
				<KundeListComponent
					:id="'' + kunde.id"
					v-for="kunde in kundenStore.kunden"
					:key="kunde"
					kunde />
			</q-list>
			<h3>
				{{ language["interessenten"][accountStore.sprache] }}
			</h3>
			<q-list class="q-table">
				<KundeListComponent
					:id="'' + kunde.id"
					v-for="kunde in kundenStore.interessenten"
					:key="kunde" />
			</q-list>
		</div>
		<div class="plus-wrapper">
			<div class="plus" @click="openCreate = true">
				<q-icon name="add" size="50px">
					<q-popup-proxy cover v-model="openCreate">
						<KundeCreateComponent
							@creation-done="openCreate = false" />
					</q-popup-proxy>
				</q-icon>
			</div>
		</div>
	</div>
</template>

<script setup>
import { useKundeStore } from "src/stores/kunde";
import KundeListComponent from "src/components/KundeListComponent.vue";
import language from "src/language";
import { useAccountStore } from "src/stores/account";
import KundeCreateComponent from "src/components/create/KundeCreateComponent.vue";
import { ref } from "vue";

const kundenStore = useKundeStore();
const accountStore = useAccountStore();

const openCreate = ref(false);

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
