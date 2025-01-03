<template>
	<div class="account-container">
			<ul>
				<li>
					Name: &nbsp;
					<p>{{ account.vorname }}</p>
				</li>
				<li>
					Accountname: &nbsp;
					<p>{{ account.nachname }}</p>
				</li>
				<li>
					E-Mail: &nbsp;
					<p>{{ account.email }}</p>
				</li>
				<li>
					Telefonnummer: &nbsp;
					<p>{{ account.telefonnummer }}</p>
				</li>
			</ul>
	</div>
</template>

<script setup>
import { defineOptions } from "vue";
import { ref } from "vue";
import BarComponement from "src/components/AufgabenBarComponement.vue";
import PieGraphComponent from "src/components/AufgabenPieGraphComponent.vue";

const _2fa = ref(false);

import { getAccountById } from "src/requests/account";

// TODO: get the logged-in user's account

const account = ref({});
const fetchAccount = async () => {
	let a = await getAccountById(1);
	console.log(a);
	account.value = a;
	console.log(account.value);
};

fetchAccount();
</script>

<style scoped>


ul {
	margin: 0;
	height: 100%;
	width: 100%;
	padding: 0;
	list-style-type: none;
	display: flex;
	flex-direction: column; /* Listenelemente vertikal anordnen */

}

li {
	padding: 4%;
	font-size: 1.5em;
	color: black;
	display: flex; /* Inhalt innerhalb eines Listenelements horizontal anordnen */
	flex-direction: row; /* Inhalt innerhalb eines Listenelements horizontal anordnen */
	align-items: center; /* Vertikale Ausrichtung der Inhalte innerhalb eines Listenelements */
}

p {
	opacity: 0.6;
	margin-left: auto; /* Verschiebt das <p>-Element an das Ende des Listenelements */
}
</style>
