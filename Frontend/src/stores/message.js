import { defineStore, acceptHMRUpdate } from "pinia";
import { useAccountStore } from "./account";
import { useAccountsStore } from "./accounts";
import address from "src/address";
import { get_no_data } from "src/request";

const accountStore = useAccountStore();
const accountsStore = useAccountsStore();

export const useMessageStore = defineStore("message", {
	state: () => ({
		messages: {},
		chats: {},
	}),
	getters: {},
	actions: {
		async fetchMessages() {
			const response = await fetch(address + "/messages");
			if (!response.ok) {
				throw new Error("HTTP error, status = " + response.status);
			}
			const data = await response.json();
			for (const message of data) {
				this.messages[message.id] = message;
			}
		},
		async fetchChat(receiver) {
			const response = await get_no_data(
				"/messages/chat/" + accountStore.account.id + "/" + receiver
			);
			if (!response.ok) {
				throw new Error("HTTP error, status = " + response.status);
			}
			const data = await response.json();
			if (data.sent.length === 0 && data.received.length === 0) {
				return;
			}
			this.chats[receiver] = data;
			this.chats[receiver].id = receiver;
		},
		async fetchChatForced(receiver) {
			const response = await get_no_data(
				"/messages/chat/" + accountStore.account.id + "/" + receiver
			);
			if (!response.ok) {
				throw new Error("HTTP error, status = " + response.status);
			}
			const data = await response.json();
			this.chats[receiver] = data;
			this.chats[receiver].id = receiver;
		},
		async fetchChats() {
			console.log(accountsStore.accounts["0"]);
			console.log("Fetching Chats");
			console.log(accountsStore.accounts);
			for (const account in Object.values(accountsStore.accounts)) {
				this.fetchChat(account);
			}
		},
	},
	persist: {
		enabled: true,
		strategy: "local",
	},
});

if (import.meta.hot) {
	import.meta.hot.accept(acceptHMRUpdate(useMessageStore, import.meta.hot));
}
