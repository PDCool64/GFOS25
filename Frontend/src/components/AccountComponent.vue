<template>
    <div>
        <h1>Account</h1>
        <h2>Ich bin ein Text</h2>
        <p>{{ account.value }}</p>
    </div>
</template>

<script setup>
    import { defineOptions } from 'vue'
    import { ref } from 'vue'

    import { getAccountById } from 'src/requests/account';

    const account = ref(null);
    account.value = getAccountById(1);
    console.log(account.value);
</script> 

<style scoped>
</style>
