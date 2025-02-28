<template>
	<q-layout view="hHh Lpr lFf">
		<q-header :elevated="!$q.dark.isActive">
			<q-toolbar>
				<!-- <q-btn
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

		<q-page-container class="flex-container">
			<router-view class="flex-item" />
		</q-page-container>

		<q-dialog v-model="settingsOpen" persistent>
			<q-card style="width: 25vh; height: 25vh">
				<q-card-section>
					{{ language["einstellungen"][accountStore.sprache] }}
				</q-card-section>
				<q-toggle label="Dark Mode" v-model="darkMode" />
				<q-select
					v-model="accountStore.account.einstellungen.sprache"
					:options="languages"
					option-value="code"
					option-label="name"
					dense
					outlined
					class="q-ml-md"
					style="max-width: 120px"
					@update:model-value="changeLanguage"
					emit-value
					map-options />
				<q-card-actions align="center">
					<q-btn
						label="OK"
						color="primary"
						@click="settingsOpen = false" />
				</q-card-actions>
			</q-card>
		</q-dialog>
	</q-layout>
</template>

<script setup>
import { computed, ref, watch } from "vue";
import EssentialLink from "components/EssentialLink.vue";
import { useTokenStore } from "src/stores/token";
import { useRouter } from "vue-router";
import { useQuasar } from "quasar";
import { useAccountStore } from "src/stores/account";
import language from "src/language";

const tokenStore = useTokenStore();
const accountStore = useAccountStore();

const $q = useQuasar();

defineOptions({
	name: "MainLayout",
});

const router = useRouter();

const darkMode = ref($q.dark.isActive);

const languages = [
	{ name: "Deutsch", code: "de" },
	{ name: "English", code: "en" },
	{ name: "Français", code: "fr" },
];

const warning = ref(true);

const changeLanguage = (language) => {
	if (accountStore.account && accountStore.account.einstellungen) {
		accountStore.account.einstellungen.sprache = language;
	}
	console.log("Language changed to", language);
};

watch(
	() => darkMode.value,
	() => {
		console.log("Switching");
		$q.dark.set(darkMode.value);
		accountStore.account.einstellungen.farbschema = darkMode;
	}
);

const linksList = computed(() => {
	return accountStore.account.rang < 1
		? //  Non-Managment Links
		  [
				{
					title: "übersicht",
					icon: "space_dashboard",
					link: "/#/dashboard",
				},
				{
					title: "chat",
					icon: "chat",
					link: "/#/chat",
				},
				{
					title: "kalender",
					icon: "event",
					link: "/#/calendar",
				},
				{
					title: "termin_erstellen",
					icon: "edit_calendar",
					link: "/#/termin",
				},
				{
					title: "kunden",
					icon: "support_agent",
					link: "/#/clients",
				},
				{
					title: "account",
					icon: "person",
					link: "/#/account",
				},
		  ]
		: //   Managment Links
		  [
				{
					title: "übersicht",
					icon: "space_dashboard",
					link: "/#/management_dashboard",
				},
				{
					title: "chat",
					icon: "chat",
					link: "/#/chat",
				},
				{
					title: "kalender",
					icon: "event",
					link: "/#/calendar",
				},
				{
					title: "termin_erstellen",
					icon: "edit_calendar",
					link: "/#/termin",
				},
				{
					title: "account",
					icon: "person",
					link: "/#/account",
				},
		  ];
});

const leftDrawerOpen = ref(false);

const settingsOpen = ref(false);
const alertOpen = ref(false);

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

.flex-container {
	display: flex;
	flex-direction: column;
	height: 100%;
}

.flex-item {
	flex: 1;
}
</style>
