<template>
	<div>
		<q-list>
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

<style lang="scss" scoped></style>
