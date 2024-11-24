<template>
    <div class="login-page">
        <div class="login-form">
            <h1>Login</h1>
            <form @submit.prevent="temp">
                <input
                    type="text"
                    v-model="email"
                    placeholder="Username"
                    required
                />
                <input
                    type="password"
                    v-model="password"
                    placeholder="Password"
                    required
                />
                <button>Login</button>
            </form>
        </div>
        <button @click="console.log(tokenStore.token)">Test</button>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { login } from 'src/requests/login';
import { useTokenStore } from 'src/stores/token';
import { useAccountStore } from 'src/stores/account';

const tokenStore = useTokenStore();
const accountStore = useAccountStore();

const email = ref('');
const password = ref('');

const temp = async () => {
    console.log("username = " + email.value);
    console.log("password = " + password.value);
    const data = await login(email.value, password.value);
    accountStore.email = email.value;
    tokenStore.token = data.token;
    tokenStore.refresh_token = data.refresh_token;
    console.log(tokenStore.token);
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
</style>
