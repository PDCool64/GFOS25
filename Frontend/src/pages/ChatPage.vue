<template>
	<q-layout view="hHh lpR fFf">
		<q-drawer
			show-if-above
			v-model="drawer"
			side="left"
			bordered
			class="drawer"
			style="overflow-y: auto; max-height: 100vh">
			<q-list>
				<q-item clickable v-ripple>
					<q-item-section>
						<q-item-label>Chat 1</q-item-label>
					</q-item-section>
				</q-item>
				<q-item clickable v-ripple>
					<q-item-section>
						<q-item-label>Chat 2</q-item-label>
					</q-item-section>
				</q-item>
				<!-- Add more chat items as needed -->
			</q-list>
		</q-drawer>

		<q-page-container>
			<q-page class="q-pa-md">
				<div class="everything">
					<ChatComponent
						:receiver="accountStore.account.id == 0 ? '1' : '0'" />
				</div>
			</q-page>
		</q-page-container>
	</q-layout>
</template>

<script setup>
import { ref } from "vue";
import { useQuasar } from "quasar";
import ChatComponent from "src/components/ChatComponent.vue";
import { useAccountStore } from "src/stores/account";

const $q = useQuasar();
const drawer = ref(true);
const accountStore = useAccountStore();
</script>

<style scoped>
.everything {
	overflow-y: hidden;
	height: 100%;
}
</style>
