<template>
	<div class="everything">
		<ChatComponent :receiver="accountStore.account.id == 0 ? 1 : 0" />
	</div>
</template>

<script setup>
import ChatComponent from "src/components/ChatComponent.vue";
import { useAccountStore } from "src/stores/account";

const accountStore = useAccountStore();
</script>

<style scoped>
.everything {
	overflow-y: hidden;
}
</style>
