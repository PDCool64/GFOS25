<template>
    <div @click="onClick" :class="taskClass">
        <div class="header-wrapper">
            <h2>{{ aufgabe.titel }}</h2>
            <q-icon class="icon" :name="icon" :color="taskClass" size="3em" />
        </div>
        <ul>
            <li>{{ aufgabe.beschreibung }}</li>
            <!-- <li>{{ aufgabe }}</li> -->
            <li>
                Erstellt am
                {{ displayDate(new Date(aufgabe.erstellungsdatum)) }}
            </li>
            <li>
                Fällig am {{ displayDate(new Date(aufgabe.faelligkeitsdatum)) }}
            </li>
            <li>Status: {{ aufgabe.status }}</li>
        </ul>
    </div>
</template>

<script setup>
import { computed, ref } from 'vue';
import { getAufgabeById } from 'src/requests/aufgabe';
import { defineOptions, defineProps } from 'vue';

defineOptions({
    name: 'AufgabenComponent',
});
const props = defineProps({
    id: {
        type: Number,
        default: 1,
    },
});

const icon = ref('check');
const displayDate = (date) => {
    return new Date(date).toLocaleDateString();
};

const onClick = () => {
    aufgabe.value.status = (aufgabe.value.status + 1) % 3;
    switch (aufgabe.value.status) {
        case 0:
            icon.value = 'check';
            break;
        case 1:
            icon.value = 'hourglass_empty';
            break;
        case 2:
            icon.value = 'clear';
            break;
        default:
            icon.value = 'check';
            break;
    }
};

const taskClass = computed(() => {
    switch (aufgabe.value.status % 3) {
        case 0:
            return 'open';
        case 1:
            return 'in-progress';
        case 2:
            return 'closed';
        default:
            return 'bg-primary';
    }
});

const aufgabe = ref({
    titel: 'Beispiel Aufgabe',
    beschreibung: 'Dies ist eine Beispielbeschreibung für die Aufgabe.',
    termin: '2023-10-01',
    done: false,
});

async function fetchAufgabe() {
    const response = await getAufgabeById(props.id);
    console.log(response);
    aufgabe.value = response;
}

fetchAufgabe();
</script>

<style scoped>
div {
    margin: 0;
    height: auto;
    display: flex;
    max-width: 500px;
    flex-direction: column;
    align-items: center;
    background-color: var(--q-primary);
    border-radius: 10px;
    cursor: pointer;
}

h2 {
    font-size: 3em;
    font-family: 'Roboto', sans-serif;
    color: white;
    margin: 0;
    margin-left: 10%;
}

ul {
    width: 80%;
    margin-bottom: 10%;
    padding: 0;
    list-style-type: none;
    background-color: var(--q-secondary);
    border-radius: 10px;
    box-shadow: 2px 4px 5px rgba(0, 0, 0, 0.3);
    overflow-x: hidden;
}

li {
    font-size: 1.5em;
    padding: 10px 20px;
    color: white;
    display: flex;
}

.header-wrapper {
    display: flex;
    flex-direction: row;
    justify-content: center;
    width: 100%;
    padding: 10px 20px;
}

.icon {
  flex-grow: 0;
  box-shadow: #0008 3px 3px 8px;
  border-radius: 20%;
  margin-left: auto;
  margin-right: 10%;
}

</style>
