<template>
  <q-page class="flex flex-center">
  <div class="q-pa-md" style="max-width: 350px">
    <div class="q-gutter-md">
      <q-input
        v-model="search"
        @keydown="e => onKeydown(e)"
        debounce="500"
        filled
        placeholder="Search"
        round
      >
        <template v-slot:append>
          <q-icon name="search" />
        </template>
      </q-input>
    </div>
  </div>
  </q-page>
</template>

<script setup>
import { ref, watch } from 'vue'
import { useRoute } from 'vue-router'

const router = useRoute()
const search = ref('')

const onKeydown = async (e) =>  {

    const result = await fetch("http://localhost:8080/Backend/api/accounts/search/" + search.value + e.key, {
        method: "GET",
    })
    if (result.ok) {
        const data = await result.json()
        console.log(data)
    }
    else {
        console.log("Error")
    }

}

defineOptions({
  name: 'TestPage'
});
</script>
