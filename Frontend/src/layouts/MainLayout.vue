<template>
	<q-layout view="lHh Lpr lFf">
		<q-header elevated>
			<q-toolbar>
				<q-btn
					flat
					dense
					round
					icon="menu"
					aria-label="Menu"
					@click="toggleLeftDrawer"
					v-if="tokenStore.token != ''" />
				<q-toolbar-title class="title"> flow </q-toolbar-title>
			</q-toolbar>
		</q-header>
		<q-drawer
			v-model="leftDrawerOpen"
			show-if-above
			bordered
			v-if="tokenStore.token != ''">
			<q-list>
				<q-item-label header> </q-item-label>

				<EssentialLink
					v-for="link in linksList"
					:key="link.title"
					v-bind="link" />
			</q-list>
		</q-drawer>

		<q-page-container>
			<router-view />
		</q-page-container>
	</q-layout>
</template>

<script setup>
import { onActivated, onMounted, onRenderTriggered, onUpdated, ref } from "vue";
import EssentialLink from "components/EssentialLink.vue";
import { useTokenStore } from "src/stores/token";
import { useRouter } from "vue-router";

defineOptions({
	name: "MainLayout",
});

const router = useRouter();
const tokenStore = useTokenStore();
console.log(tokenStore);

router.beforeEach((to) => {
	console.log(to);
	console.log(to.meta.requiresAuth);
	if (to.meta.requiresAuth && tokenStore.token == "") return "/login";
});

const linksList = [
	{
		title: "Übersicht",
		icon: "space_dashboard",
		link: "/#/dashboard",
	},
	{
		title: "Chat",
		icon: "chat",
		link: "/#/chat",
	},
	{
		title: "Account",
		icon: "person",
		link: "/#/account",
	},
];

const leftDrawerOpen = ref(false);

function toggleLeftDrawer() {
	leftDrawerOpen.value = !leftDrawerOpen.value;
}
</script>

<style scoped>
.title {
	font-size: 3em;
	font: Noto Sans;
	font-weight: bold;
}
</style>
