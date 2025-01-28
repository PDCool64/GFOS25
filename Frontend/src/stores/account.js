import { defineStore, acceptHMRUpdate } from "pinia";

export const useAccountStore = defineStore("account", {
	state: () => ({
		account: {
			id: -1,
			email: "",
			vorname: "",
			nachname: "",
		},
		accounts: {},
	}),
	getters: {},
	actions: {},
	persist: {
		enabled: true,
		strategy: "local",
	},
});

if (import.meta.hot) {
	import.meta.hot.accept(acceptHMRUpdate(useAccountStore, import.meta.hot));
}
