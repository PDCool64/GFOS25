<template>
	<q-page class="flex flex-center">
		<div class="q-pa-md suggestions-container" style="max-width: 350px">
			<div class="q-gutter-md">
				<q-input
					v-model="search"
					debounce="500"
					filled
					placeholder="Search"
					round>
					<template v-slot:append>
						<q-icon name="search" />
					</template>
				</q-input>
			</div>
			<!-- Use q-list instead of q-li -->
			<q-list bordered class="q-mt-md rounded-borders">
				<q-item
					clickable
					v-for="account in data"
					:key="account.id"
					:to="`/test/${account.id}`">
					<q-item-section>
						<q-item-label>
							<div class="text-h6">
								{{ account.vorname }} {{ account.name }}
							</div>
							<div class="text-subtitle">{{ account.email }}</div>
						</q-item-label>
					</q-item-section>
				</q-item>
			</q-list>
		</div>
	</q-page>
</template>

<script setup>
import { ref, watch } from "vue";
import { useRoute } from "vue-router";

const router = useRoute();
const search = ref("");
const data = ref([]);

// Watch for changes in the search variable and perform actions
watch(search, async (newValue, oldValue) => {
	console.log(newValue); // This will log the current value of search
	const result = await fetch(
		`http://localhost:8080/Backend/api/accounts/search/${newValue}`,
		{
			method: "GET",
		}
	);
	if (result.ok) {
		data.value = await result.json();
		console.log(data);
	} else {
		console.log("Error fetching data");
	}
});

defineOptions({
	name: "TestPage",
});
</script>
