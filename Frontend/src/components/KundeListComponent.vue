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
				<q-badge rounded v-if="numberOfTasks">
					{{ numberOfTasks }}</q-badge
				>
			</q-item-label>
			<q-item-label caption>
				{{ kunde.email }}
			</q-item-label>
		</q-item-section>
		<q-item-section side>
			<q-btn-group>
				<q-btn
					:class="[
						{ active: props.kunde && kunde.kontaktstatus == 0 },
					]"
					:label="
						language['nicht_kontaktiert'][
							accountStore.account.einstellungen.sprache
						]
					" />
				<q-btn
					:class="[
						{ active: props.kunde && kunde.kontaktstatus == 1 },
					]"
					:label="
						language['kontaktiert'][
							accountStore.account.einstellungen.sprache
						]
					" />
				<q-btn
					:class="[
						{ active: props.kunde && kunde.kontaktstatus == 2 },
					]"
					:label="
						language['kunde'][
							accountStore.account.einstellungen.sprache
						]
					" />
			</q-btn-group>
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
import language from "src/language";
import { useAccountStore } from "src/stores/account";
import { useAufgabenStore } from "src/stores/aufgaben";

const accountStore = useAccountStore();
const aufgabenStore = useAufgabenStore();

const numberOfTasks = computed(() => {
	console.log("Computing number of tasks for " + props.id);

	let sum = 0;
	for (const id in aufgabenStore.aufgaben) {
		const task = aufgabenStore.aufgaben[id];
		console.log(task.kunde?.id);
		if (task.kunde?.id === undefined) {
			console.log(task);
		}
		if (task.kunde?.id != props.id) {
			continue;
		}
		console.log("sum++");
		sum++;
		console.log("Summe = " + sum);
	}
	return sum;
});

const props = defineProps({
	id: String,
	kunde: Boolean,
});

console.log(props.id);
const kundenStore = useKundeStore();
const kunde = props.kunde
	? ref(kundenStore.kunden[props.id])
	: ref(kundenStore.interessenten[props.id]);

const currentTime = ref(
	new Date(new Date().getTime() + kunde.value.utcOffset * 1000 * 60)
);
let timer = null;
onMounted(() => {
	timer = setInterval(() => {
		currentTime.value = new Date(
			new Date().getTime() + kunde.value.utcOffset * 1000 * 60
		);
	}, 6000);
});
onUnmounted(() => {
	clearInterval(timer);
});

const formattedTime = computed(() =>
	currentTime.value.toTimeString().slice(0, 5)
);
</script>

<style lang="scss" scoped>
.main {
	margin: 1vh;
	background-color: var(--q-login-form);
	border: 1px solid var(--q-primary);
}
.red {
	background-color: var(--q-aufgabe-undone);
	background-image: linear-gradient(
		90deg,
		var(--q-aufgabe-undone) 1%,
		var(--q-login-form) 1%
	);
}
.blue {
	background-color: var(--q-aufgabe-in-progress);
	background-image: linear-gradient(
		90deg,
		var(--q-aufgabe-in-progress) 1%,
		var(--q-login-form) 1%
	);
}
.green {
	background-color: var(--q-aufgabe-done);
	background-image: linear-gradient(
		90deg,
		var(--q-aufgabe-done) 1%,
		var(--q-login-form) 1%
	);
}
.digital-clock {
	font-size: 1.5rem;
	padding: 0.1rem 0.2rem;
	text-align: center;
	border-radius: 4px;
	color: var(--q-login-form-text);
}

.q-btn-group {
	box-shadow: none;
	//background-color: var(--q-primary);
	//color: white;
	border: 1px solid var(--q-primary);
	background: rgba($color: #000000, $alpha: 0.03);
}

.q-btn {
	width: 9vw;
}

.q-btn.active {
	background-color: var(--q-primary);
	color: white;
}
</style>
