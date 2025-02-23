import { defineStore, acceptHMRUpdate } from "pinia";

export const useAccountStore = defineStore("account", {
	state: () => ({
		account: {
			id: -1,
			email: "",
			vorname: "",
			nachname: "",
		},
	}),
	getters: {
		sprache() {
			if (this.account.einstellungen === undefined) {
				return "de";
			}
			return this.account.einstellungen.sprache;
		},
	},
	actions: {
		reset() {
			this.account = {
				settings: this.account.settings,
				id: -1,
				email: "",
				vorname: "",
				nachname: "",
			};
		},
	},
	persist: {
		enabled: true,
		strategy: "local",
	},
});

if (import.meta.hot) {
	import.meta.hot.accept(acceptHMRUpdate(useAccountStore, import.meta.hot));
}
