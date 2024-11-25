<template>
	<q-item clickable tag="a" :href="props.link">
		<q-item-section v-if="props.icon" avatar>
			<q-icon :name="props.icon" />
		</q-item-section>

		<q-item-section>
			<q-item-label>{{ props.title }}</q-item-label>
		</q-item-section>
	</q-item>
</template>

<script setup>
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
