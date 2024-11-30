import { defineStore, acceptHMRUpdate } from "pinia";
import address from "src/address";
import { useAccountStore } from "./account";
import { useTokenStore } from "./token";

const accountStore = useAccountStore();
const tokenStore = useTokenStore();

export const useAufgabenStore = defineStore("aufgaben", {
	state: () => ({
		aufgaben: {},
	}),
	getters: {
		stats() {
			let stats = {
				total: 0,
				done: 0,
				in_progress: 0,
				undone: 0,
			};
			for (const id in this.aufgaben) {
				const aufgabe = this.aufgaben[id].aufgabe;
				stats.total++;
				if (aufgabe.status % 3 == 0) {
					stats.undone++;
				} else if (aufgabe.status % 3 == 1) {
					stats.in_progress++;
				} else {
					stats.done++;
				}
			}
			return stats;
		},
	},
	actions: {
		async fetchAufgaben() {
			console.log("Fetching Aufgaben");
			if (accountStore.account?.id == undefined) {
				console.log("Account not defined");
				return;
			}
			const response = await fetch(
				address + "/accounts/aufgaben/" + accountStore.account.id,
				{
					headers: {
						Authorization: tokenStore.token,
					},
				}
			);
			if (!response.ok) {
				console.log(response);
				throw new Error("HTTP error, status = " + response.status);
			}
			const data = await response.json();
			for (const aufgabe of data) {
				console.log(aufgabe.id);
				this.aufgaben[aufgabe.aufgabe.id] = aufgabe;
			}
			console.log(this.aufgaben);
		},
	},
	persist: {
		enabled: true,
		strategy: "local",
	},
});

if (import.meta.hot) {
	import.meta.hot.accept(acceptHMRUpdate(useAufgabenStore, import.meta.hot));
}
