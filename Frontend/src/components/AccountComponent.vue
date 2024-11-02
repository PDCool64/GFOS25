<template>
    <div>
        <h1>Account</h1>
        <ul>
      <li> Name: &nbsp; <p>{{ account.vorname }}</p></li>
      <li> Accountname: &nbsp; <p>{{ account.nachname }}</p> </li>
      <li> Account: &nbsp; <p>{{ account.email }}</p> </li>
        </ul>
        <!-- <q-toggle
        checked-icon="check"
        color="green"
        unchecked-icon="clear"
        v-model="_2fa"
      />  -->
    </div>
</template>

<script setup>
import { defineOptions } from 'vue';
import { ref } from 'vue';

const _2fa = ref(false);

import { getAccountById } from 'src/requests/account';

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



div {
    margin-top: 10%;
    margin-left: 25%;
    height: 100%;
    width: 60%;
    display: flex;
    flex-direction: column;
    text-align: center;
    align-items: center;
    justify-content: center;
    background-color: var(--q-primary);
    border-radius: 10px;
}

h1 {
    font-size: 5em;
    font-family: 'Roboto', sans-serif;
    color: white;
    font-weight: 400;
}

ul {
    height: 100%;
    width: 80%;
    margin-bottom: 10%;
    padding: 0;
    list-style-type: none;
    background-color: var(--q-secondary);
    display: flex;
    flex-direction: column;
    border-radius: 10px;
    box-shadow: 2px 4px 5px rgba(0, 0, 0, 0.3);
    overflow-x: hidden;
}

li {
    padding: 4%;
    font-size: 1.5em;
    color: white;
    display: flex;
}

p {
    opacity: 0.6;
    margin-left: auto;
}

</style>
