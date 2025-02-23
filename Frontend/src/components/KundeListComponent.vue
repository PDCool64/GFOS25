<template>
	<q-item
		class="main"
		:class="[
			{ red: props.kunde && kunde.kontaktstatus == 0 },
			{ blue: props.kunde && kunde.kontaktstatus == 1 },
			{ green: props.kunde && kunde.kontaktstatus == 2 },
		]">
		<q-item-section>
			<q-item-label>
				{{ kunde.vorname }} {{ kunde.nachname }}
			</q-item-label>
			<q-item-label caption>
				{{ kunde.email }}
			</q-item-label>
		</q-item-section>
		<q-item-section side>
			<div class="digital-clock">
				{{ formattedTime }}
			</div>
		</q-item-section>
	</q-item>
</template>

<script setup>
import { ref, onMounted, onUnmounted, computed } from "vue";
import { useKundeStore } from "src/stores/kunde";

const props = defineProps({
	id: String,
	kunde: Boolean,
});

console.log(props.id);
const kundenStore = useKundeStore();
const kunde = props.kunde
	? ref(kundenStore.kunden[props.id])
	: ref(kundenStore.interessenten[props.id]);

const currentTime = ref(new Date());
let timer = null;
onMounted(() => {
	timer = setInterval(() => {
		currentTime.value = new Date(
			new Date().getTime() + kunde.value.utcOffset * 1000 * 60
		);
	}, 1000);
});
onUnmounted(() => {
	clearInterval(timer);
});

const formattedTime = computed(() =>
	currentTime.value.toTimeString().slice(0, 8)
);
</script>

<style lang="scss" scoped>
.main {
	background-color: var(--q-secondary);
	border-bottom: 3px solid var(--q-primary);
}
.red {
	background-color: var(--q-aufgabe-undone);
}
.blue {
	background-color: var(--q-aufgabe-in-progress);
}
.green {
	background-color: var(--q-aufgabe-done);
}
.digital-clock {
	font-family: monospace;
	font-size: 1.5rem;
	padding: 0.5rem 1rem;
	text-align: center;
	border-radius: 4px;
}
</style>
