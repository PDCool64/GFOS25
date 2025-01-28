import { defineStore, acceptHMRUpdate } from "pinia";
import { get_no_data } from "src/request";

export const useAccountsStore = defineStore("accounts", {
	state: () => ({
		accounts: {},
	}),
	getters: {},
	actions: {
		async fetchAllAccounts() {
			const response = await get_no_data("/accounts/all");
			if (!response.ok) {
				throw new Error("HTTP error, status = " + response.status);
			}
			const data = await response.json();
			for (const account of data) {
				this.accounts[account.id] = account;
			}
		},
	},
});

if (import.meta.hot) {
	import.meta.hot.accept(acceptHMRUpdate(useAccountsStore, import.meta.hot));
}
