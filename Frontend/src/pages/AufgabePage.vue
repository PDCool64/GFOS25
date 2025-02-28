<template>
	<div id="aufgabe">
		<h1 class="q-pa-xs">{{ aufgabe?.titel }}</h1>
		<div class="wrapper">
			<div
				class="bearbeiter"
				v-if="aufgabe && bearbeiterList"
				style="height: 50vh; overflow-y: auto">
				<h2>
					{{
						language["mitwirkende"][
							accountStore.account.einstellungen?.sprache
						]
					}}
				</h2>
				<q-list style="height: max-content">
					<q-item
						v-for="bearbeiter in bearbeiterList"
						style="color: white"
						:key="bearbeiter">
						<q-item-section>
							<q-item-label class="email">
								{{ bearbeiter?.vorname }}
								{{ bearbeiter?.nachname }}
							</q-item-label>
							<q-item-label section>
								{{ bearbeiter?.email }}</q-item-label
							>
						</q-item-section>
					</q-item>
				</q-list>
				<AccountSelectionComponent
					v-model="bearbeiterList"
					@account-pushed="
						(account) => {
							aufgabenStore.addAccountToAufgabe(
								aufgabe.id,
								account.id
							);
							console.log(account);
							aufgabe.aufgabenbearbeitungList.push({
								bearbeiter: account,
							});
						}
					"
					hide-names />
			</div>
			<div>
				<Unterpunkte :id="route.params.id" class="unterpunkte" />
			</div>
			<div class="beschreibung">
				<BeschreibungComponent :id="route.params.id" />
				<div
					:class="[
						{ done: aufgabe?.status === 2 },
						{ undone: aufgabe?.status === 0 },
						{ inprogress: aufgabe?.status === 1 },
						'bar',
					]"></div>
			</div>
		</div>
	</div>
</template>

<script setup>
import { ref, onMounted, watch, computed } from "vue";
import { useRoute } from "vue-router";
import { useAufgabenStore } from "src/stores/aufgaben";
import BeschreibungComponent from "src/components/aufgabe/BeschreibungComponent.vue";
import Unterpunkte from "src/components/aufgabe/AufgabenUnterpunkteComponent.vue";
import AccountSelectionComponent from "src/components/account/AccountSelectionComponent.vue";
import { useAccountStore } from "src/stores/account";
import language from "src/language";

const accountStore = useAccountStore();

const route = useRoute();
const aufgabenStore = useAufgabenStore();

const aufgabe = ref(null);
const bearbeiterList = computed(() => {
	if (aufgabe.value == null) return [];
	let temp = [];
	for (const bearbeitung of aufgabe.value.aufgabenbearbeitungList) {
		temp.push(bearbeitung.bearbeiter);
	}
	return temp;
});

watch(bearbeiterList, () => {
	console.log(bearbeiterList.value);
});

const displayDate = (date) => {
	return new Date(date).toLocaleDateString();
};

const loadAufgabe = (id) => {
	console.log(aufgabenStore.aufgaben); // Ausgabe des Objekts in der Konsole
	const task = aufgabenStore.aufgaben[id];
	if (task) {
		aufgabe.value = task;
	} else {
		console.error(`Aufgabe mit ID ${id} nicht gefunden`);
	}
};

onMounted(() => {
	const id = route.params.id;
	console.log(id);
	if (id) {
		aufgabenStore.fetchAufgabe(id).finally(() => loadAufgabe(id));
	}
});
</script>

<style scoped>
.email {
	font-weight: bold;
}

.q-item {
	background-color: var(--q-primary);
	height: max-content;
	border-radius: 5px;
	margin-top: 0.5vw;
	padding: 20px;
}

.bearbeiter {
	padding: 1vw;
	display: flex;
	flex-direction: column;
	margin-right: 1vw;
	width: 25vw;
	box-shadow: rgba(17, 17, 26, 0.1) 0px 4px 16px,
		rgba(17, 17, 26, 0.1) 0px 8px 24px, rgba(17, 17, 26, 0.1) 0px 16px 56px;
}
h2 {
	font-size: 3em;
	font: Noto Sans;
	font-weight: bold;
	color: var(--q-primary);
}

#aufgabe {
	align-items: center;
	justify-content: center;
	text-align: center;
	display: flex; /* Flexbox hinzufügen */
	padding: 0;
	flex-direction: column;
}
h1 {
	font-size: 4em;
	font: Noto Sans;
	font-weight: bold;
	color: var(--q-primary);
}
.wrapper {
	display: flex;
}

.beschreibung {
	height: 50vh;
	margin-left: 1vw;
}

.unterpunkte {
	height: 50vh;
	overflow-y: auto;
	width: 25vw;
}

.bar {
	width: 100%;
	height: 10px;
	background-color: var(--q-primary);
}

.done {
	background-color: #a5efc2;
}

.undone {
	background-color: #f7b7b7;
}

.inprogress {
	background-color: #f7f7b7;
}
</style>
