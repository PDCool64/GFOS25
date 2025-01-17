<template>
	<div class="account">
		<h1>Account</h1>
		<div class="account-wrapper">
			<div class="box" id="eins">
				<h2>Accountdetails</h2>
				<AccountComponent />
			</div>
			<div class="box" id="zwei">
				<h2>Aktuelle Statistik</h2>
				<AccountPieGraphComponent />
			</div>
			<div class="box" id="drei">
				<div class="bar">
					<h2>Aktivitäten letztes Jahr</h2>
					<BarComponement />
				</div>
			</div>
		</div>
	</div>
</template>

<script setup>
import BarComponement from "src/components/stats/aufgaben/AufgabenBarComponement.vue";
import AccountPieGraphComponent from "src/components/stats/aufgaben/AufgabenPieGraphComponent.vue";
import AccountComponent from "src/components/AccountComponent.vue";
</script>

<style scoped>
h1 {
	font-size: 8em;
	font: Noto Sans;
	font-weight: bold;
	color: var(--q-primary);
}

h2 {
	font-size: 2em;
	font: Noto Sans;
	font-weight: bold;
	color: var(--q-primary);
}

.account {
	margin-top: 0;
	margin-left: 10%;
	margin-right: 10%;
	align-items: center;
	justify-content: center;
	text-align: center;
	display: flex; /* Flexbox hinzufügen */
	flex-direction: column; /* Elemente vertikal anordnen */
	padding: 0;
}

#eins {
	padding: 0;
	overflow-x: hidden;
	background-color: var(--q-secondary);
}
.box {
	border-radius: 10px;
	box-shadow: rgba(17, 17, 26, 0.1) 0px 4px 16px,
		rgba(17, 17, 26, 0.1) 0px 8px 24px, rgba(17, 17, 26, 0.1) 0px 16px 56px;
}

@media screen and (min-width: 600px) {
	.account-wrapper {
		display: grid;
		grid-template-columns: 1fr 2fr 1fr;
		grid-template-rows: 1fr 1fr 1fr;
		gap: 1rem;
		width: 100%;
	}

	#eins {
		grid-column: 1 / span 2;
		grid-row: 1 / span 1;
	}

	#zwei {
		grid-column: 3 / span 1;
		grid-row: 1 / span 1;
	}

	#drei {
		grid-column: 1 / span 3;
		grid-row: 2 / span 1;
	}
}
</style>
