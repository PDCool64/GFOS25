<template>
	<q-item class="main">
		<q-item-section align="center">
			<q-item-label>
				{{ kunde.vorname }} {{ kunde.nachname }}
			</q-item-label>
			<q-item-label caption> {{ kunde.email }} </q-item-label>
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
	background-color: var(--q-secondary);
	border-bottom: 3px solid var(--q-primary);
}
</style>
