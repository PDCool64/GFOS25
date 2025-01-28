import { defineStore, acceptHMRUpdate } from "pinia";
import { useAccountStore } from "./account";
import address from "src/address";
import { get_no_data } from "src/request";

const accountStore = useAccountStore();

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
			console.log(response);
			if (!response.ok) {
				throw new Error("HTTP error, status = " + response.status);
			}
			const data = await response.json();
			this.chats[receiver] = data;
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
