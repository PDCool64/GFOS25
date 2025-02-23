<template>
	<q-table
		class="q-table"
		:rows="rows"
		:columns="columns"
		row-key="id"
		hide-bottom>
		<template v-slot:body="props">
			<q-tr
				:props="props"
				:class="getRowClass(props.row)"
				@click="() => onClick(props.row.id)"
				style="">
				<q-td
					v-for="col in props.cols"
					:key="col.name"
					:props="props"
					style="cursor: pointer; overflow: visible">
					{{ props.row[col.field] }}
				</q-td>
			</q-tr>
		</template>
	</q-table>
</template>

<script setup>
import language from "src/language";
import { computed } from "vue";
import { useAufgabenStore } from "src/stores/aufgaben";
import { useRouter } from "vue-router";
import { QTable, QTr, QTd } from "quasar";
import { useAccountStore } from "src/stores/account";

const accountStore = useAccountStore();
const router = useRouter();
const aufgabenStore = useAufgabenStore();

console.log("Aufgaben im Store:", aufgabenStore.aufgaben);

const columns = [
	{
		name: "titel",
		required: true,
		label: "Titel",
		align: "left",
		field: "titel",
	},
	{
		name: "beschreibung",
		label: language["beschreibung"][
			accountStore.account.einstellungen?.sprache
		],
		align: "left",
		field: "beschreibung",
	},
	{
		name: "fälligkeitsdatum",
		label: language["faelligkeitsdatum"][
			accountStore.account.einstellungen?.sprache
		],
		align: "left",
		field: "faelligkeitsdatum",
	},
];

const rows = computed(() => {
	const aufgabenArray = Object.values(aufgabenStore.active);
	console.log("Rows für die Tabelle:", aufgabenArray);
	return aufgabenArray.map((aufgabe) => ({
		id: aufgabe.id,
		titel: aufgabe.titel,
		beschreibung: aufgabe.beschreibung,
		status: aufgabe.status,
		faelligkeitsdatum: displayDate(new Date(aufgabe.faelligkeitsdatum)),
	}));
});

const displayDate = (date) => {
	return new Date(date).toLocaleDateString();
};

const getRowClass = (row) => {
	switch (row.status) {
		case 0:
			return "status-red";
		case 1:
			return "status-orange";
		case 2:
			return "status-green";
		default:
			return "";
	}
};

const onClick = (id) => {
	console.log("Clicked row ID:", id);
	router.push({ path: `/aufgaben/${id}` });
};
</script>

<style scoped>
.q-table {
	width: 50vw;
}

.q-td {
	font-size: 1.1em;
}

.status-orange {
	background-color: var(--q-aufgabe-in-progress);
}

.status-green {
	background-color: var(--q-aufgabe-done);
}

.status-red {
	background-color: var(--q-aufgabe-undone);
}

@media (max-width: 1024px) {
	.q-table {
		width: 100%;
	}
}
.q-td {
	white-space: normal !important;
	word-wrap: break-word;
}
</style>
