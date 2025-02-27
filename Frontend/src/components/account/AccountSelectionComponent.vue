<template>
	<div v-if="!props.hideNames">
		<q-list v-for="account in accounts" :key="account">
			<q-item>
				<q-item-section>
					{{ account.vorname }} {{ account.nachname }}
				</q-item-section>
			</q-item>
		</q-list>
	</div>
	<q-input
		type="search"
		placeholder="Search..."
		v-model="search"
		@focus="showing = true"
		@blur="onFocusLost" />
	<div>
		<q-list
			v-if="showing"
			style="position: absolute; z-index: 2; margin: 20px">
			<q-item
				clickable
				style="cursor: pointer; background-color: var(--q-login-form)"
				v-for="account in searchableAccounts"
				:key="account"
				@click="handleClick(accountList[account[0]])">
				<q-item-section>
					<q-item-label>
						{{ accountList[account[0]].vorname }}
						{{ accountList[account[0]].nachname }}
					</q-item-label>
				</q-item-section>
			</q-item>
		</q-list>
	</div>
</template>

<script setup>
import { useAccountsStore } from "src/stores/accounts";
import { computed, ref, watch } from "vue";

const emit = defineEmits(["account-pushed"]);
const props = defineProps({
	hideNames: { type: Boolean, default: false },
});

const accounts = defineModel();
const showing = ref(false);

const onFocusLost = () => {
	setTimeout(() => {
		showing.value = false;
	}, 100);
};

const handleClick = (account) => {
	console.log("Something still works");
	accounts.value.push(account);
	console.log(accounts.value);
	search.value = "";
	emit("account-pushed", account);
};

const accountStore = useAccountsStore();
const accountsStore = useAccountsStore();

const accountList = computed(() => {
	let temp = [];
	for (const id in accountsStore.accounts) {
		temp.push(accountsStore.accounts[id]);
	}
	return temp;
});

const searchableAccounts = ref(accountList.value.slice(0, 4));
console.log(searchableAccounts.value);

const search = ref("");

watch(search, () => {
	let temp = [];
	for (let i = 0; i < accountList.value.length; i++) {
		let summe = 0;
		summe +=
			100 / 0.5 +
			accountList.value[i].vorname
				.toLowerCase()
				.indexOf(search.value.toLowerCase());
		summe +=
			100 / 0.5 +
			accountList.value[i].nachname
				.toLowerCase()
				.indexOf(search.value.toLowerCase());
		temp.push([i, summe - 398]);
	}
	temp = temp.filter((a) => a[1] != 0);
	temp.sort((a, b) => b[1] - a[1]);
	searchableAccounts.value = temp.slice(0, 5);
});

accountStore.fetchAllAccounts();
</script>

<style lang="scss" scoped></style>
