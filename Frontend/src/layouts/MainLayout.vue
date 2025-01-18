<template>
	<q-layout view="hHh Lpr lFf">
		<q-header elevated>
			<q-toolbar>
				<!--<q-btn
					flat
					dense
					round
					icon="menu"
					aria-label="Menu"
					@click="toggleLeftDrawer"
					v-if="tokenStore.token != ''" /> -->
				<q-toolbar-title class="logo"> flow </q-toolbar-title>

				<q-btn
					flat
					dense
					icon="settings"
					aria-label="Logout"
					@click="settingsOpen = true"
					v-if="tokenStore.token != ''" />

				<q-btn
					flat
					dense
					icon="logout"
					aria-label="Logout"
					@click="logout"
					v-if="tokenStore.token != ''" />
			</q-toolbar>
		</q-header>
		<q-drawer
			v-model="leftDrawerOpen"
			show-if-above
			bordered
			v-if="tokenStore.token != ''"
			class="drawer">
			<q-list>
				<EssentialLink
					v-for="link in linksList"
					:key="link.title"
					@click="link.onClick ? link.onClick : null"
					v-bind="link" />
			</q-list>
		</q-drawer>

		<q-page-container>
			<router-view />
		</q-page-container>

		<q-dialog v-model="settingsOpen" persistent>
			<q-card>
				<q-card-section>AHHHHHHHHHHHhhhhhhhhhhhhHH </q-card-section>
				<q-card-section> </q-card-section>
				<q-card-actions align="right">
					<q-btn label="OK" color="primary" @click="settingsOpen = false" />
				</q-card-actions>
			</q-card>
		</q-dialog>
	</q-layout>
</template>

<script setup>
import { ref } from "vue";
import EssentialLink from "components/EssentialLink.vue";
import { useTokenStore } from "src/stores/token";
import { useRouter } from "vue-router";

const tokenStore = useTokenStore();

defineOptions({
	name: "MainLayout",
});

const router = useRouter();

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
		title: "Kalender",
		icon: "event",
		link: "/#/calendar",
	},
	{
		title: "Account",
		icon: "person",
		link: "/#/account",
	},
];

const leftDrawerOpen = ref(true);

const settingsOpen = ref(false);

function toggleLeftDrawer() {
	leftDrawerOpen.value = !leftDrawerOpen.value;
}

function logout() {
	tokenStore.token = "";
	router.push("/login");
}
</script>

<style scoped>
.logo {
	font-size: 3em;
	font: Noto Sans;
	font-weight: bold;
}

.drawer {
	background-color: red !important;
}
</style>
