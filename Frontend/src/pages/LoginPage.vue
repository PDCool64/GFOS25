<template>
	<div class="login-page">
		<div class="login-form">
			<h1>LOGIN</h1>
			<form @submit.prevent="onSubmit">
				<input
					type="text"
					v-model="email"
					placeholder="Email"
					required />
				<input
					type="password"
					v-model="password"
					placeholder="Password"
					required />
				<button>LOGIN</button>
			</form>
			<button @click="onForgotPassword">Forgot Password?</button>
		</div>

		<q-dialog v-model="showPopup">
			<q-card>
				<q-card-section>
					<q-card-title>
						<h4>Leider Pech gehabt du Huremsom</h4>
						<h5>Aber wird vielleicht später mal implementiert</h5>
					</q-card-title>
				</q-card-section>
				<q-card-actions align="right">
					<q-btn
						flat
						label="Dismiss"
						color="primary"
						@click="closePopup" />
				</q-card-actions>
			</q-card>
		</q-dialog>
	</div>
</template>

<script setup>
import { ref } from "vue";
import { login } from "src/requests/login";
import { useTokenStore } from "src/stores/token";
import { useAccountStore } from "src/stores/account";
import { useAufgabenStore } from "src/stores/aufgaben";
import { useRouter } from "vue-router";

const tokenStore = useTokenStore();
const accountStore = useAccountStore();
const aufgabenStore = useAufgabenStore();

const router = useRouter();

const email = ref("");
const password = ref("");

const showPopup = ref(false);

const onSubmit = async () => {
	console.log("username = " + email.value);
	console.log("password = " + password.value);
	const data = await login(email.value, password.value);

	if (data?.token != "") {
		router.push("/dashboard");
	}

	accountStore.account = JSON.parse(data.account);
	tokenStore.token = data.token;
	tokenStore.refresh_token = data.refresh_token;
	console.log(tokenStore.token);
	console.log(aufgabenStore.aufgaben);
	aufgabenStore.fetchAufgaben();
	setTimeout(() => {
		console.log("Refreshing token");
		tokenStore.refreshToken();
	}, 1000 * 1);
};

const onForgotPassword = () => {
	showPopup.value = true;
	console.log(showPopup.value);
};

const closePopup = () => {
	showPopup.value = false;
};
</script>
<style scoped>
.login-page {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.login-form {
	display: flex;
	flex-direction: column;
	align-items: center;
	gap: 1rem;
}
</style>
