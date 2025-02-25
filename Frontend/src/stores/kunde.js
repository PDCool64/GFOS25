import { defineStore, acceptHMRUpdate } from "pinia";
import { get, get_no_data } from "src/request";
import { useAccountStore } from "./account";

const accountStore = useAccountStore();

export const useKundeStore = defineStore("kunde", {
	state: () => ({
		kunden: {},
		interessenten: {},
	}),
	getters: {},
	actions: {
		async fetchKunde(id) {
			id = parseInt(id);
			const response = await get_no_data("/kunde/" + id);
			const data = await response.json();
			if (data.kundenstatus == 1) {
				this.interessenten[String(id)] = data;
			} else {
				this.kunden[String(id)] = data;
			}
			console.log(data);
		},
		async fetchOwnKunden() {
			const response = await get_no_data(
				"/kunde/account/" + accountStore.account.id
			);
			const data = await response.json();
			for (const kunde of data) {
				if (kunde.kundenstatus == 1) {
					this.interessenten[String(kunde.id)] = kunde;
				} else {
					console.log(kunde);
					this.kunden["" + kunde.id] = kunde;
				}
			}
			console.log(this.kunden);
			console.log(data);
		},
		async fetchAllKunden() {
			const response = await get_no_data("/kunde/all");
			const data = await response.json();
			for (const kunde of data) {
				if (kunde.kundenstatus == 1) {
					this.interessenten[String(kunde.id)] = kunde;
				} else {
					this.kunden[String(kunde.id)] = kunde;
				}
			}
		},
	},
	persist: {
		enabled: true,
		strategy: "local",
	},
});

if (import.meta.hot) {
	import.meta.hot.accept(acceptHMRUpdate(useKundeStore, import.meta.hot));
}
