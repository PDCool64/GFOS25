<template>
	<div class="account">
		<div class="account-wrapper">
			<div class="box" id="eins">
				<h2>
					{{
						language["account"][
							accountStore.account.einstellungen?.sprache
						]
					}}
				</h2>
				<AccountComponent />
			</div>
			<div class="box" id="zwei">
				<h2>
					{{
						language["aktuelle_statistik"][
							accountStore.account.einstellungen?.sprache
						]
					}}
				</h2>
				<div class="bar-wrapper">
					<AccountPieGraphComponent />
				</div>
			</div>
		</div>
		<div class="box" id="drei">
			<div class="bar">
				<h2>
					{{
						language["aktivität"][
							accountStore.account.einstellungen?.sprache
						]
					}}
				</h2>
				<BarComponement id="2" />
			</div>
		</div>
	</div>
</template>

<script setup>
import BarComponement from "src/components/stats/aufgaben/AufgabenBarComponement.vue";
import AccountPieGraphComponent from "src/components/stats/aufgaben/AufgabenPieGraphComponent.vue";
import AccountComponent from "src/components/AccountComponent.vue";
import { useAccountStore } from "src/stores/account";
import language from "src/language";

const accountStore = useAccountStore();
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
	margin-left: 20%;
	margin-right: 20%;
	margin-bottom: 20%;
	align-items: center;
	justify-content: center;
	text-align: center;
	display: flex; /* Flexbox hinzufügen */
	flex-direction: column; /* Elemente vertikal anordnen */
	padding: 10px;
	height: 100vh;
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

.bar-wrapper {
	display: flex;
	justify-content: center;
	align-items: center;
	width: auto;
	height: auto;
}

@media screen and (min-width: 1024px) {
	.account-wrapper {
		display: grid;
		grid-template-columns: 1fr 1fr 1fr;
		gap: 1rem;
		width: 100%;
		height: 100%;
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
		display: flex;
		align-items: center;
		width: 100%;
		text-align: center;
		justify-content: center;
		margin-top: 20px;
	}
	#eins #zwei {
		text-align: center;
		justify-content: center;
	}
}
</style>
