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
import { ref } from "vue";
const _2fa = ref(false);

import { getAccountById } from "src/requests/account";

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
	height: 30vh;
	width: auto;
	padding: 0;
	list-style-type: none;
	display: flex;
	flex-direction: column; /* Listenelemente vertikal anordnen */
}

li {
	padding: auto;
	padding-left: 3em;
	padding-right: 3em;
	font-size: 1.5em;
	color: --var(--q-whitedark);
	display: flex; /* Inhalt innerhalb eines Listenelements horizontal anordnen */
	flex-direction: row; /* Inhalt innerhalb eines Listenelements horizontal anordnen */
	align-items: center; /* Vertikale Ausrichtung der Inhalte innerhalb eines Listenelements */
}

p {
	opacity: 0.6;
	margin-left: auto; /* Verschiebt das <p>-Element an das Ende des Listenelements */
}
</style>
