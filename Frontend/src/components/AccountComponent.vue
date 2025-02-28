<template>
	<q-table
		:rows="transformedRows"
		:columns="transformedColumns"
		row-key="field"
		hide-bottom
		hide-header>
		<template v-slot:body="props">
			<q-tr :props="props" class="q-tr--no-hover">
				<q-td v-for="col in props.cols" :key="col.name" :props="props">
					{{ props.row[col.field] }}
				</q-td>
			</q-tr>
		</template>
	</q-table>
</template>

<script setup>
import { ref, onMounted, watch, computed } from "vue";
import { useAccountStore } from "src/stores/account";
import language from "src/language";
import { getAccountById } from "src/requests/account";

const accountStore = useAccountStore();
const _2fa = ref(false);

const account = ref({});
const fetchAccount = async () => {
	account.value = accountStore.account;
	console.log(account.value);
};

onMounted(() => {
	fetchAccount();
});

const columns = [
	{
		name: "vorname",
		label: language["vorname"][accountStore.sprache],
		align: "left",
		field: "vorname",
	},
	{
		name: "name",
		label: language["name"][accountStore.sprache],
		align: "left",
		field: "nachname",
	},
	{
		name: "email",
		label: "Email",
		align: "left",
		field: "email",
	},
	{
		name: "telefonnummer",
		label: language["telefonnummer"][accountStore.sprache],
		align: "left",
		field: "telefonnummer",
	},
];

const rows = ref([]);

watch(account, (newAccount) => {
	rows.value = [newAccount];
});

// Transformiere die Datenstruktur, um die Achsen zu vertauschen
const transformedColumns = ref([
	{ name: "field", label: "Field", align: "left", field: "field" },
	{ name: "value", label: "Value", align: "left", field: "value" },
]);

const transformedRows = computed(() => {
	if (!rows.value.length) return [];
	const accountData = rows.value[0];
	return columns.map((col) => ({
		field: col.label,
		value: accountData[col.field],
	}));
});
</script>

<style scoped>
.q-table__card {
	height: auto;
	width: auto;
	background: transparent;
	box-shadow: none;
}

.q-td {
	background: transparent;
	font-size: 1.5em;
}

.q-tr {
	background: transparent;
}
</style>
