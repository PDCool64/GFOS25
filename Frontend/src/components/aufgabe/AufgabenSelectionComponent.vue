<template>
	<q-input
		type="search"
		placeholder="Search..."
		v-model="search"
		@focus="(showing = true), console.log('Showing')"
		@blur="showing = false" />
	<div>
		<q-list
			v-if="showing"
			floating
			style="position: absolute; z-index: 2; margin: 20px">
			<q-item
				clickable
				style="cursor: pointer; background-color: var(--q-login-form)"
				v-for="aufgabe in searchableAufgaben"
				:key="aufgabe"
				@click="
					(receiver = '' + account[0]),
						console.log('Something works'),
						(searchOpen = false)
				">
				<q-item-section>
					<q-item-label>{{
						aufgabenList[aufgabe[0]].titel
					}}</q-item-label>
					<q-item-label caption>
						{{ aufgabenList[aufgabe[0]].kunde?.vorname }}
						{{ aufgabenList[aufgabe[0]].kunde?.nachname }}
					</q-item-label>
				</q-item-section>
			</q-item>
		</q-list>
	</div>
</template>

<script setup>
import { useAufgabenStore } from "src/stores/aufgaben";
import { computed, ref, watch } from "vue";

const model = defineModel();
const showing = ref(false);

const aufgabenStore = useAufgabenStore();

aufgabenStore.fetchAufgaben();

const search = ref("");

const aufgabenList = computed(() => {
	let temp = [];
	for (const id in aufgabenStore.aufgaben) {
		temp.push(aufgabenStore.aufgaben[id]);
	}
	console.log(temp);
	return temp;
});

const searchableAufgaben = ref(aufgabenList.value.slice(0, 4));

watch(search, () => {
	console.log("watch");
	let temp = [];
	for (let i = 0; i < aufgabenList.value.length; i++) {
		let summe = 0;
		console.log(aufgabenList.value);
		console.log(aufgabenList.value[i]);
		console.log(aufgabenList.value[i].titel);
		summe +=
			100 / 0.5 +
			aufgabenList.value[i].titel
				.toLowerCase()
				.indexOf(search.value.toLowerCase());
		summe +=
			100 / 0.5 +
			aufgabenList.value[i].beschreibung
				.toLowerCase()
				.indexOf(search.value.toLowerCase());
		temp.push([i, summe - 398]);
	}
	console.log(temp);
	temp = temp.filter((a) => a[1] != 0);
	temp.sort((a, b) => b[1] - a[1]);
	searchableAufgaben.value = temp.slice(0, 5);
});
</script>

<style lang="scss" scoped></style>
