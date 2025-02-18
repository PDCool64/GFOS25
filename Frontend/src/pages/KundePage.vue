<template>
	<div>
		<q-list class="q-table">
			<KundeListComponent
				:id="'' + kunde.id"
				v-for="kunde in kundenStore.kunden"
				:key="kunde" />
		</q-list>
	</div>
</template>

<script setup>
import { useKundeStore } from "src/stores/kunde";
import KundeListComponent from "src/components/KundeListComponent.vue";
const kundenStore = useKundeStore();
kundenStore.fetchOwnKunden();
</script>

<style lang="scss" scoped>
.q-table {
	top: 15%;
	left: 15%;
	position: relative;
	width: 70%;
}
</style>
