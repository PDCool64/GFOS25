import { defineStore, acceptHMRUpdate } from "pinia";
import { get, post, get_no_data } from "../request";

export const useKalendarStore = defineStore("kalendar", {
	state: () => ({
		termine: [],
	}),
	getters: {
		active() {
			return this.termine;
		},
	},
	actions: {
		async fetchTermine() {
			try {
				const response = await get_no_data("/termine/all");
				if (!response.ok) {
					throw new Error("Failed to fetch termine");
				}
				const data = await response.json();
				this.termine = data;
			} catch (error) {
				console.error("Error fetching termine:", error);
			}
		},
		async fetchTermin(id) {
			try {
				const response = await get_no_data(`/termine/${id}`);
				if (!response.ok) {
					throw new Error("Failed to fetch termin");
				}
				const data = await response.json();
				const index = this.termine.findIndex((t) => t.id === id);
				if (index !== -1) {
					this.termine[index] = data;
				}
			} catch (error) {
				console.error("Error fetching termin:", error);
			}
		},
		async addTermin(termin) {
			try {
				const response = await post("/termine", {
					body: JSON.stringify(termin),
				});
				if (!response.ok) {
					throw new Error("Failed to add termin");
				}
				const data = await response.json();
				this.termine.push(data);
			} catch (error) {
				console.error("Error adding termin:", error);
			}
		},
		async updateTermin(id, termin) {
			try {
				const response = await post(`/termine/${id}`, {
					method: "PUT",
					body: JSON.stringify(termin),
				});
				if (!response.ok) {
					throw new Error("Failed to update termin");
				}
				const data = await response.json();
				const index = this.termine.findIndex((t) => t.id === id);
				if (index !== -1) {
					this.termine[index] = data;
				}
			} catch (error) {
				console.error("Error updating termin:", error);
			}
		},
	},
});

if (import.meta.hot) {
	import.meta.hot.accept(acceptHMRUpdate(useKalendarStore, import.meta.hot));
}
