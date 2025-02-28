<!-- Diesen File bitte noch einmal überarbeiten tun, weil sonst ist der schlimmer als wie du! -->

<template>
	<div
		class="fullscreen text-blue bg-blue text-center q-pa-md flex flex-center">
		<div>
			<div style="font-size: 30vh">404</div>
			<q-btn
				class="q-mt-xl"
				color="white"
				text-color="blue"
				unelevated
				to="/"
				label="Go Home"
				no-caps />
		</div>
	</div>
</template>

<script setup>
defineOptions({
	name: "ErrorNotFound",
});
</script>

<style scoped>
.wrapper {
	color: var(--q-primary);
}
</style>
