<template>
	<q-layout view="hhh lpR fFf">
		<q-drawer
			show-if-above
			v-model="drawer"
			side="left"
			bordered
			class="drawer"
			style="overflow-y: auto; max-height: 100vh">
			<q-list>
				<q-item
					clickable
					v-ripple
					@click="receiver = chat.id"
					v-for="chat in messagesStore.chats"
					:key="chat">
					<q-item-section>
						<q-item-label>
							<q-icon name="person" />
							{{
								accountsStore.accounts[chat.id]?.vorname +
								" " +
								accountsStore.accounts[chat.id]?.nachname
							}}</q-item-label
						>
					</q-item-section>
				</q-item>
				<q-item clickable v-ripple @click="console.log('Click')">
					<q-item-section>
						<q-item-label>
							<q-icon name="edit" />
							{{
								language["chat_beginnen"][accountStore.sprache]
							}}
						</q-item-label>
					</q-item-section>
					<q-popup-proxy v-model="searchOpen">
						<q-list>
							<q-input
								type="search"
								style="padding: 0 16px"
								placeholder="Search..."
								v-model="search" />
							<q-item
								clickable
								style="cursor: pointer"
								v-for="account in searchableAccounts"
								:key="account"
								@click="
									(receiver = '' + account[0]),
										console.log('Something works'),
										(searchOpen = false)
								">
								{{ accountList[account[0]].vorname }}
								{{ accountList[account[0]].nachname }}
								{{ account }}
							</q-item>
						</q-list>
					</q-popup-proxy>
				</q-item>
			</q-list>
		</q-drawer>

		<q-page-container>
			<q-page class="q-pa-md">
				<div class="everything" v-if="receiver !== -1">
					<ChatComponent :receiver="receiver" />
				</div>
				<div v-else>
					{{ language["chat_oeffnen"][accountStore.sprache] }}
				</div>
			</q-page>
		</q-page-container>
	</q-layout>
</template>

<script setup>
import { computed, ref, watch } from "vue";
import ChatComponent from "src/components/ChatComponent.vue";
import { useAccountStore } from "src/stores/account";
import { useMessageStore } from "src/stores/message";
import { useAccountsStore } from "src/stores/accounts";
import language from "src/language";

const receiver = ref(-1);

const messagesStore = useMessageStore();
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
const searchOpen = ref(false);

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

accountsStore.fetchAllAccounts().then(async () => {
	await messagesStore.fetchChats().then(() => {
		console.log("Something worked");
	});
});

const drawer = ref(true);
const accountStore = useAccountStore();
</script>

<style scoped>
.everything {
	height: 100%;
	overflow-y: hidden;
}
</style>
