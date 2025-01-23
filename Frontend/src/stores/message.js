import { defineStore, acceptHMRUpdate } from "pinia";
import { useAccountStore } from "./account";
import address from "src/address";

const accountStore = useAccountStore();

export const useMessageStore = defineStore("message", () => ({
	state: () => ({
		messages: {},
		chats: [],
	}),
	getter: {},
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
	},
	perist: {
		enabled: true,
		strategy: "local",
	},
}));

if (import.meta.hot) {
	import.meta.hot.accept(acceptHMRUpdate(useMessageStore, import.meta.hot));
}
