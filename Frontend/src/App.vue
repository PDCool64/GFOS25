<template>
	<router-view class="main-view" />
</template>

<script setup>
import { useQuasar } from "quasar";

defineOptions({
	name: "App",
});
document.title = "flow";

const $q = useQuasar();

const account = localStorage.getItem("account");
if (account) {
	const accountJson = JSON.parse(account);
	console.log(accountJson);
	const farbschema = accountJson?.account?.einstellungen?.farbschema;
	if (farbschema == 0) {
		console.log("setting light");
		$q.dark.set(false);
	} else if (farbschema == 1) {
		console.log("setting dark");
		$q.dark.set(true);
	} else {
		console.log(farbschema);

		console.log("setting auto");
		$q.dark.set("auto");
	}
}
</script>

<style scoped>
.main-view {
	height: 10vh;
}
</style>
