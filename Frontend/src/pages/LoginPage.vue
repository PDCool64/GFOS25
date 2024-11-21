<template>
    <div class="login-page">
        <div class="login-form">
            <h1>Login</h1>
            <form @submit.prevent="temp">
                <input
                    type="text"
                    v-model="username"
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

const tokenStore = useTokenStore();

const username = ref('');
const password = ref('');

const temp = async () => {
  console.log("username = " + username.value);
  console.log("password = " + password.value);
  const data = await login(username.value, password.value);
  const token = data.token;
  tokenStore.setToken(token);
  console.log(tokenStore.token);
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
