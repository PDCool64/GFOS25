<template>
	<q-card :class="['kunde-card']" v-if="kunde !== null">
		<q-card-section>
			<q-input v-model="kunde.nachname" label="Name" readonly />
			<q-input v-model="kunde.vorname" label="Vorname" readonly />
			<q-input v-model="kunde.email" label="Email" readonly />
			<q-input v-model="kunde.telefonnummer" label="Telefon" readonly />
		</q-card-section>
	</q-card>
</template>

<script setup>
import { useKundeStore } from "src/stores/kunde";
import { ref } from "vue";

const props = defineProps({
	id: String,
});

const kundeStore = useKundeStore();

const kunde = ref(null);
if (kundeStore.kunden[props.id]) {
	kunde.value = kundeStore.kunden[props.id];
}

kundeStore.fetchKunde(props.id).then(() => {
	kunde.value = kundeStore.kunden[parseInt(props.id)];
	console.log(kunde.value);
	console.log(kundeStore.kunden);
	console.log(props.id);
	console.log("Interesting");
});
</script>

<style lang="scss" scoped>
.kunde-card {
	max-width: 400px;
	margin: 0 auto;
	padding: 20px;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
	border-radius: 8px;
}

.q-card-section {
	display: flex;
	flex-direction: column;
	gap: 15px;
}

.q-input {
	width: 100%;
}
</style>
