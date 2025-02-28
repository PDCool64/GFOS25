<template>
	<div class="wrapper">
		<h3 class="q-pa-xs">{{ kalendar?.titel }}</h3>
		<div class="inner">
			<q-icon class="icon" name="description" />
			<div>
				{{ kalendar?.beschreibung }}
			</div>
		</div>
		<div class="inner">
			<q-icon name="edit" class="icon" />
			{{ kalendar?.startzeit }}
		</div>
		<div class="inner">
			<q-icon name="schedule" class="icon" />
			{{ kalendar?.startzeit }}
		</div>
		<div class="inner"></div>
	</div>
</template>

<script setup>
import { computed, ref } from "vue";
import { useKalendarStore } from "src/stores/kalendar";

const kalendarStore = useKalendarStore();

const props = defineProps({
	id: String,
});

console.log("Kalendar Store: ", kalendarStore.termine);

const kalendar = computed(() => {
	return kalendarStore.termine[props.id];
});
</script>

<style scoped>
.wrapper {
	display: flex;
	width: 100%;
	height: 100%;
	flex-direction: column;
	align-items: center;
	background-color: var(--q-secondary);
	border-radius: 5px;
	user-select: none;
}

.inner {
	font-size: auto;
	padding: 10px 20px;
	color: white;
	display: flex;
}

h3 {
	font-size: 2em;
	font: Noto Sans;
	font-weight: bold;
	color: var(--q-primary);
}
</style>
