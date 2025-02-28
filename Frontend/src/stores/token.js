import { defineStore, acceptHMRUpdate } from "pinia";
import address from "src/address";
import { useAccountStore } from "./account";

const accountStore = useAccountStore();

export const useTokenStore = defineStore("token", {
	state: () => ({
		token: "",
		refresh_token: "",
	}),
	getters: {},
	actions: {
		clearToken() {
			this.token = "";
		},
		async logout() {
			const router = useRouter();
			this.token = "";
			this.refresh_token = "";
			accountStore.reset();
			window.location.href = "/#/login";
		},
		async refreshToken() {
			console.log("refreshing token");

			const response = await fetch(address + "/tokens/refresh", {
				method: "POST",
				body: JSON.stringify({
					email: accountStore.account.email,
					refresh_token: this.refresh_token,
				}),
				headers: {
					"Content-Type": "application/json",
				},
			});
			if (!response.ok) {
				console.log(response);
				throw new Error("HTTP error, status = " + response.status);
			}
			const data = await response.json();
			this.token = data.token;
			this.refresh_token = data.refresh_token;
			setTimeout(() => {
				this.refreshToken();
			}, 1000 * 60 * 5);
		},
	},
	persist: {
		enabled: true,
		strategy: "local",
	},
});

if (import.meta.hot) {
	import.meta.hot.accept(acceptHMRUpdate(useTokenStore, import.meta.hot));
}
