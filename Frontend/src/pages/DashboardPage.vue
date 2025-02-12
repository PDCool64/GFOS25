<template>
	<div class="dashboard-container">
		<h3 style="font-weight: 700">
			Willkommen zurück, {{ accountStore.account.vorname }}!
		</h3>
		<div class="top-row">
			<div class="tasks-section">
				<h4>Offene Aufgaben</h4>
				<div class="tasks-wrapper">
					<div v-for="id in aufgabenIds" :key="id" class="task-item">
						<AufgabenComponent :id="'' + id" />
					</div>
					<div
						class="task-item add-task-item"
						@click="createAufgabeOpen = true">
						<q-icon name="add" size="220px">
							<q-popup-proxy cover v-if="createAufgabeOpen">
								<AufgabenCreateComponent
									@creationDone="onCreationDone" />
							</q-popup-proxy>
						</q-icon>
					</div>
					<NewAufgabenComponement></NewAufgabenComponement>
				</div>
			</div>
			<div class="chart-section">
				<h4>Charts</h4>
				<AccountPieGraphComponent />
			</div>
		</div>
		<!-- Space for future components -->
		<div class="bottom-row">
			<h4>Anstehende Termine</h4>

			<!-- Future components will go here -->
		</div>
	</div>
</template>

<script setup>
import { ref, watch } from "vue";
import AufgabenComponent from "src/components/AufgabenComponent.vue";
import AccountPieGraphComponent from "src/components/stats/aufgaben/AufgabenPieGraphComponent.vue";
import AufgabenCreateComponent from "src/components/create/AufgabenCreateComponent.vue";
import { useAufgabenStore } from "src/stores/aufgaben";
import { useAccountStore } from "src/stores/account";
import NewAufgabenComponement from "src/components/NewAufgabenComponement.vue";

const createAufgabeOpen = ref(false);

const aufgabenStore = useAufgabenStore();
const accountStore = useAccountStore();

const onCreationDone = () => {
	console.log("Something worked");
	createAufgabeOpen.value = false;
};

const aufgabenIds = ref([]);
aufgabenStore.fetchAufgaben().finally(() => {
	console.log(aufgabenStore.active);
	for (const aufgabe of aufgabenStore.active) {
		aufgabenIds.value.push(aufgabe.id);
	}
});
console.log(aufgabenStore.stats);
</script>

<style scoped>
.dashboard-container {
	display: grid;
	grid-template-rows: auto 1fr;
	/* First row for content, second for future components */
	gap: 1rem;
	padding: 1rem;
	height: calc(100vh - 100px);
}

.top-row {
	display: grid;
	grid-template-columns: 3fr 1fr;
	/* Tasks take 3/4, chart takes 1/4 */
	gap: 1rem;
}

.tasks-wrapper {
	display: flex;
	flex-wrap: wrap;
	gap: 1rem;
	padding: 0.5rem;
}

.task-item {
	flex: 0 1 calc(33.333% - 1rem);
	/* Three items per row with gap */
	min-width: 250px;
	/* Minimum width before wrapping */
	background-color: var(--q-secondary);
}

.chart-section {
	display: flex;
	align-items: start;
	justify-content: center;
	min-height: 0;
}

.aufgaben {
	height: 100%;
	overflow-y: auto;
	padding: 0.5rem;
}

.bottom-row {
	min-height: 200px;
	/* Minimum height for future components */
}

.add-task-item {
	cursor: pointer;
	border: 2px dashed #ccc;
	transition: background-color 0.3s;
	display: flex;
	justify-content: center;
	align-items: center;
}

/* Responsive design */
@media (max-width: 1024px) {
	.top-row {
		grid-template-columns: 1fr;
	}

	.task-item {
		flex: 0 1 calc(50% - 1rem);
		/* Two items per row on smaller screens */
	}
}
</style>
