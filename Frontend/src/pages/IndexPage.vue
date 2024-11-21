<template>
    <div></div>
</template>

<script setup>
import PieGraphComponent from 'src/components/PieGraphComponent.vue';
defineOptions({
    name: 'IndexPage',
});

// TODO: Check Token and possible redirect to LoginPage

import { useRouter } from 'vue-router';
const router = useRouter();
router.push('/dashboard');
</script>
