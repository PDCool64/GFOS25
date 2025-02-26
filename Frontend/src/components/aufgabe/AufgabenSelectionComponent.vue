<template>
	<q-list v-for="aufgabe in aufgaben" :key="aufgabe">
		<q-item>
			<q-item-section>
				<q-item-label> {{ aufgabe.titel }} </q-item-label>
				<q-item-label caption>
					{{
						aufgabe.beschreibung.split(" ").splice(0, 10).join(" ")
					}}...
				</q-item-label>
			</q-item-section>
			<q-item-section side>
				{{ aufgabe.kunde.vorname }} {{ aufgabe.kunde.nachname }}
			</q-item-section>
		</q-item>
	</q-list>
	<q-input
		type="search"
		placeholder="Search..."
		v-model="search"
		@focus="showing = true"
		@blur="onFocusLost" />
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
				@click="handleClick(aufgabenList[aufgabe[0]])">
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

const emit = defineEmits(["aufgabe-pushed"]);

const aufgaben = defineModel();
const showing = ref(false);

const onFocusLost = () => {
	setTimeout(() => {
		showing.value = false;
	}, 100);
};

const handleClick = (aufgabe) => {
	console.log("Something still works");
	aufgaben.value.push(aufgabe);
	console.log(aufgaben.value);
	search.value = "";
	emit("aufgabe-pushed");
};

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
	temp = temp.filter((a) => a[1] != 0);
	temp.sort((a, b) => b[1] - a[1]);
	searchableAufgaben.value = temp.slice(0, 5);
});
</script>

<style lang="scss" scoped></style>
