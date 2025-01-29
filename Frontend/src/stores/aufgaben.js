import { defineStore, acceptHMRUpdate } from "pinia";
import address from "src/address";
import { useAccountStore } from "./account";
import { useTokenStore } from "./token";
import { get_no_data, post, post_no_data, put_no_data } from "src/request";

const accountStore = useAccountStore();
const tokenStore = useTokenStore();

export const useAufgabenStore = defineStore("aufgaben", {
	state: () => ({
		aufgaben: {},
	}),
	getters: {
		active() {
			return Object.values(this.aufgaben).filter(
				(aufgabe) =>
					new Date(aufgabe.aufgabe?.faelligkeitsdatum) > new Date()
			);
		},
		stats() {
			let stats = {
				total: 0,
				done: 0,
				in_progress: 0,
				undone: 0,
			};
			for (const id in this.aufgaben) {
				const aufgabe = this.aufgaben[id].aufgabe;
				if (aufgabe === undefined) {
					console.log(id);
					continue;
				}
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
		activeStats() {
			let stats = {
				total: 0,
				done: 0,
				in_progress: 0,
				undone: 0,
			};
			for (const aufgabe of this.active) {
				stats.total++;
				if (aufgabe.aufgabe.status % 3 == 0) {
					stats.undone++;
				} else if (aufgabe.aufgabe.status % 3 == 1) {
					stats.in_progress++;
				} else {
					console.log(aufgabe.aufgabe.status);
					stats.done++;
				}
			}
			console.log(stats);

			return stats;
		},
		perMonth(month, status) {
			counter = 0;
			for (const aufgabe in this.aufgaben) {
				console.log(aufgabe);
				if (aufgabe.month == month && aufgabe.status == status) {
					counter++;
				}
			}
			return counter;
		},
	},
	actions: {
		async fetchAufgaben() {
			console.log("Fetching Aufgaben");
			if (accountStore.account?.id == undefined) {
				console.log("Account not defined");
				return;
			}
			const response = await get_no_data(
				"/accounts/aufgaben/" + accountStore.account.id
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
		async fetchAufgabe(id) {
			const response = await get_no_data("/aufgaben/" + id);
			if (!response.ok) {
				console.log(response);
				throw new Error("HTTP error, status = " + response.status);
			}
			const aufgabe = await response.json();
			this.aufgaben[aufgabe.id] = aufgabe;
		},
		async createAufgabe(aufgabe) {
			this.aufgaben[aufgabe.id] = aufgabe;
			const response = await post("/aufgaben", aufgabe);
			if (!response.ok) {
				console.log(response);
				throw new Error("HTTP error, status = " + response.status);
			}
			const data = await response.json();
			const aufgabenId = data.id;
			console.log(aufgabenId);
			const response_to_account_add = await post_no_data(
				`/aufgaben/${aufgabenId}/add-account/` + accountStore.account.id
			);
		},
		async togglePunkt(id) {
			const response = await put_no_data(
				"/aufgaben/punkte/" + id + "/toggle"
			);
			console.log(response);

			if (!response.ok) {
				throw new Error("HTTP error, status = " + response.status);
			}
			const data = await response.json();
			this.aufgaben[id] = data;
		},
	},
});

/*
fetch("http://localhost:8080/Backend/api//aufgaben", {
  "headers": {
    "content-type": "application/json",
    "sec-ch-ua": "\"Google Chrome\";v=\"131\", \"Chromium\";v=\"131\", \"Not_A Brand\";v=\"24\"",
    "sec-ch-ua-mobile": "?0",
    "sec-ch-ua-platform": "\"Windows\"",
    "Referer": "http://localhost:9001/",
    "Referrer-Policy": "strict-origin-when-cross-origin"
  },
  "body": "{\"titel\":\"Test\",\"beschreibung\":\"Test\",\"erstellungsdatum\":\"\",\"faelligkeitsdatum\":\"2025/01/14\",\"prioritaet\":{\"label\":\"Low\",\"value\":0},\"status\":null}",
  "method": "POST"
});
 */

if (import.meta.hot) {
	import.meta.hot.accept(acceptHMRUpdate(useAufgabenStore, import.meta.hot));
}
