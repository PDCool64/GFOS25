<template>
  <q-page class="flex flex-center">
    <PieGraphComponent />
  </q-page>
</template>

<script setup>
import PieGraphComponent from 'src/components/PieGraphComponent.vue';
defineOptions({
  name: 'IndexPage'
});
</script>
