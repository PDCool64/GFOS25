<template>
	<q-item clickable tag="a" :href="props.link">
		<q-item-section v-if="props.icon" avatar>
			<q-icon :name="props.icon" />
		</q-item-section>

		<q-item-section>
			<q-item-label>{{
				language[props.title][
					accountStore.account.einstellungen.sprache
				]
			}}</q-item-label>
		</q-item-section>
	</q-item>
</template>

<script setup>
import language from "src/language";
import { useAccountStore } from "src/stores/account";
const accountStore = useAccountStore();
defineOptions({
	name: "EssentialLink",
});

const props = defineProps({
	title: {
		type: String,
		required: true,
	},

	link: {
		type: String,
		default: "#",
	},

	icon: {
		type: String,
		default: "",
	},
});
</script>

<style scoped>
.q-item {
	padding: 5px 20px;
}
</style>
