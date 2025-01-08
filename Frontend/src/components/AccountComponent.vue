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
		<q-file filled type="file" v-model="file" />
		<div v-if="fileContent">
			<h3>File Content:</h3>
			<img :src="fileContent" alt="Uploaded Image" />
		</div>
	</div>
</template>

<script setup>
import { ref, watch } from "vue";
import { getAccountById } from "src/requests/account";

const _2fa = ref(false);
const file = ref(null);
const fileContent = ref("");

watch(file, (newFile) => {
	if (newFile) {
		const reader = new FileReader();
		reader.onload = (e) => {
			console.log(e);
			fileContent.value = e.target.result;
			console.log(fileContent);
		};
		reader.readAsDataURL(newFile);
	}
});

const account = ref({});
const fetchAccount = async () => {
	let a = await getAccountById(1);
	console.log(a);
	account.value = a;
	console.log(account.value);
};

const handleFileUpload = (event) => {
	console.log(event);
	const selectedFile = event.target.files[0];
	console.log(selectedFile);
	if (selectedFile) {
		file.value = selectedFile;
		const reader = new FileReader();
		reader.onload = (e) => {
			fileContent.value = e.target.result;
		};
		reader.readAsText(file.value);
	}
};

// fetchAccount();
</script>

<style scoped>
ul {
	margin: 0;
	height: 100%;
	width: 100%;
	padding: 0;
	list-style-type: none;
	display: flex;
	flex-direction: column;
	/* Listenelemente vertikal anordnen */
}

li {
	padding: 4%;
	font-size: 1.5em;
	color: black;
	display: flex;
	/* Inhalt innerhalb eines Listenelements horizontal anordnen */
	flex-direction: row;
	/* Inhalt innerhalb eines Listenelements horizontal anordnen */
	align-items: center;
	/* Vertikale Ausrichtung der Inhalte innerhalb eines Listenelements */
}

p {
	opacity: 0.6;
	margin-left: auto;
	/* Verschiebt das <p>-Element an das Ende des Listenelements */
}
</style>
