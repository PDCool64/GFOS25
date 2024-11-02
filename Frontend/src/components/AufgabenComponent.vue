<template>
    <div>
 <h1>{{ aufgabe.titel }}</h1>
      <ul>
          <li>{{ aufgabe.beschreibung }}</li>
          <li>{{ aufgabe.termin }}</li>
          <li><input type="checkbox" v-model="aufgabe.done" /> </li>
      </ul>

    </div>
  </template>

  <script>
  export default {
    data() {
      return {
        aufgabe: {
          titel: 'Beispiel Aufgabe',
          beschreibung: 'Dies ist eine Beispielbeschreibung für die Aufgabe.',
          termin: '2023-10-01',
          done: false
        }
      }
    }
  }
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
        box-shadow: 2px 4px 5px rgba(0,0,0,0.3);
        overflow-x: hidden;
    }

    li {
        padding: 4%;
        font-size: 1.5em;
        color: white;
        display: flex;
    }

    </style>
```
