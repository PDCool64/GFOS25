<template>
	<q-item class="main">
		<q-item-section>
			<q-item-label>
				{{ kunde.vorname }} {{ kunde.nachname }}
			</q-item-label>
			<q-item-label caption> Test </q-item-label>
		</q-item-section>
	</q-item>
</template>

<script setup>
import { useKundeStore } from "src/stores/kunde";

const props = defineProps({
	id: String,
});

const kundenStore = useKundeStore();

const kunde = kundenStore.kunden[props.id];
</script>

<style lang="scss" scoped>
.main {
	background-color: var(--q-primary);
	border-bottom: 2px solid var(--q-secondary);
}
</style>
