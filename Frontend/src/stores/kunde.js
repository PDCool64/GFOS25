import { defineStore, acceptHMRUpdate } from "pinia";
import { get, get_no_data, post, post_no_data } from "src/request";
import { useAccountStore } from "./account";
import KundeComponent from "src/components/KundeComponent.vue";

const accountStore = useAccountStore();

export const useKundeStore = defineStore("kunde", {
	state: () => ({
		kunden: {},
		interessenten: {},
	}),
	getters: {
		activeStats() {
			let stats = {
				kein_Kontakt: 0,
				kontakt_aufgenommen: 0,
				kunden: 0,
				interessenten: 0,
			};
			for (const kunde of Object.values(this.kunden)) {
				if (kunde.kundenstatus == 1) {
					stats.interessenten++;
					continue;
				}
				if (kunde.kontaktstatus == 0) {
					stats.kein_Kontakt++;
					continue;
				}
				if (kunde.kontaktstatus == 1) {
					stats.kontakt_aufgenommen++;
					continue;
				}
				stats.kunden++;
			}
			console.log(stats);
			return stats;
		},
	},
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
			console.log(data);
			for (const kunde of data) {
				if (kunde.kundenstatus == 1) {
					this.interessenten[String(kunde.id)] = kunde;
				} else {
					this.kunden[String(kunde.id)] = kunde;
				}
			}
		},
		async updateKontaktstatus(id, status) {
			const response = await post_no_data(
				"/kunde/" + id + "/kontaktstatus/" + status
			);
			const data = await response.json();
			if (data.kundenstatus == 1) {
				this.interessenten[String(id)] = data;
			} else {
				this.kunden[String(id)] = data;
			}
		},
		async updateKundenstatus(id, status) {
			if (status) {
				this.interessenten[id] = this.kunden[id];
				delete this.kunden[id];
			} else {
				this.kunden[id] = this.interessenten[id];
				delete this.interessenten[id];
			}
			const response = await post_no_data(
				"/kunde/" + id + "/kundenstatus/" + Number(status)
			);
			const data = await response.json();
			if (data.kundenstatus == 1) {
				this.interessenten[String(id)] = data;
			} else {
				this.kunden[String(id)] = data;
			}
		},
		async createKunde(kunde) {
			const response = await post("/kunde", kunde);
			const data = await response.json();
			if (data.kundenstatus == 1) {
				this.interessenten[String(data.id)] = data;
			} else {
				this.kunden[String(data.id)] = data;
			}
			return data;
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
