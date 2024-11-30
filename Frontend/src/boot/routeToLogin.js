import { boot } from "quasar/wrappers";
import { useTokenStore } from "src/stores/token";

// "async" is optional;
// more info on params: https://v2.quasar.dev/quasar-cli/boot-files
export default boot(async ({ app, router }) => {
	const tokenStore = useTokenStore();
	console.log(tokenStore);

	router.beforeEach((to) => {
		console.log(to);
		console.log(to.meta.requiresAuth);
		if (to.meta.requiresAuth && tokenStore.token == "") return "/login";
	});
});
