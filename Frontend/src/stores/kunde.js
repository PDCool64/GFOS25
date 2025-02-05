import { defineStore, acceptHMRUpdate } from "pinia";
import { get, get_no_data } from "src/request";

export const useKundeStore = defineStore("kunde", {
	state: () => ({
		kunden: {},
	}),
	getters: {},
	actions: {
		async fetchKunde(id) {
			id = parseInt(id);
			const response = await get_no_data("/kunde/" + id);
			const data = await response.json();
			this.kunden[id] = data;
			console.log(data);
		},
	},
});

if (import.meta.hot) {
	import.meta.hot.accept(acceptHMRUpdate(useKundeStore, import.meta.hot));
}
