<template>
    <div class="login-page">
        <div class="login-form">
            <h1>LOGIN</h1>
            <form @submit.prevent="temp">
                <input
                    type="text"
                    v-model="email"
                    placeholder="Email"
                    required
                />
                <input
                    type="password"
                    v-model="password"
                    placeholder="Password"
                    required
                />
                <button>LOGIN</button>
            </form>
        </div>
    </div>
</template>


<script setup>
import { ref } from 'vue';
import { login } from 'src/requests/login';
import { useTokenStore } from 'src/stores/token';
import { useAccountStore } from 'src/stores/account';
import { useAufgabenStore } from 'src/stores/aufgaben';

const tokenStore = useTokenStore();
const accountStore = useAccountStore();
const aufgabenStore = useAufgabenStore();

const email = ref('');
const password = ref('');

const temp = async () => {
    console.log("username = " + email.value);
    console.log("password = " + password.value);
    const data = await login(email.value, password.value);
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
