<template>
	<router-view class="main-view" />
</template>

<script setup>
defineOptions({
	name: "App",
});
document.title = "flow";
</script>

<style scoped>
.main-view {
	height: 10vh;
}
</style>
