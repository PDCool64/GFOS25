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
				<q-item clickable v-ripple>
					<q-item-section>
						<q-item-label>
							<q-icon name="add" />
							Chat beginnen
						</q-item-label></q-item-section
					>
				</q-item>
			</q-list>
		</q-drawer>

		<q-page-container>
			<q-page class="q-pa-md">
				<div class="everything" v-if="receiver !== -1">
					<ChatComponent :receiver="receiver" />
				</div>
				<div v-else>Öffnen Sie einen Chat</div>
			</q-page>
		</q-page-container>
	</q-layout>
</template>

<script setup>
import { ref } from "vue";
import ChatComponent from "src/components/ChatComponent.vue";
import { useAccountStore } from "src/stores/account";
import { useMessageStore } from "src/stores/message";
import { useAccountsStore } from "src/stores/accounts";

const receiver = ref(-1);

const messagesStore = useMessageStore();
const accountsStore = useAccountsStore();
accountsStore.fetchAllAccounts().then(async () => {
	await messagesStore.fetchChats().then(() => {
		console.log("Something worked");
	});
	const chats = messagesStore.chats;
	for (let index in chats) {
		console.log(accountsStore.accounts[index]);
	}
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
