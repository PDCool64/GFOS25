<template>
    <div class="q-pa-md">
      <q-table
        flat bordered
        title="Treats"
        :rows="rows"
        :columns="columns"
        row-key="name"
        binary-state-sort
      >
        <template v-slot:body="props">
          <q-tr :props="props">
            <q-td key="name" :props="props">
              {{ props.row.name }}
              <q-popup-edit title="Update name" buttons v-slot="scope">
                <q-input v-model="props.row.name" dense autofocus counter @keyup.enter="scope.set" />
                <q-input v-model="props.row.vorname" dense autofocus counter @keyup.enter="scope.set" />
                <q-input type="number" v-model="props.row.alter" />
                <q-input v-model="props.row.email" dense autofocus counter @keyup.enter="scope.set" />
              </q-popup-edit>
            </q-td>
            <q-td key="vorname" :props="props">
              {{ props.row.vorname }}
              <q-popup-edit title="Update name" buttons v-slot="scope">
                <q-input v-model="props.row.name" dense autofocus counter @keyup.enter="scope.set" />
                <q-input v-model="props.row.vorname" dense autofocus counter @keyup.enter="scope.set" />
                <q-input type="number" v-model="props.row.alter" />
                <q-input v-model="props.row.email" dense autofocus counter @keyup.enter="scope.set" />
              </q-popup-edit>
            </q-td>
            <q-td key="alter" :props="props">
              {{ props.row.alter }}
              <q-popup-edit title="Update name" buttons v-slot="scope">
                <q-input v-model="props.row.name" dense autofocus counter @keyup.enter="scope.set" />
                <q-input v-model="props.row.vorname" dense autofocus counter @keyup.enter="scope.set" />
                <q-input type="number" v-model="props.row.alter" />
                <q-input v-model="props.row.email" dense autofocus counter @keyup.enter="scope.set" />
              </q-popup-edit>
            </q-td>
            <q-td key="email" :props="props">
              {{ props.row.email }}
              <q-popup-edit title="Update name" buttons v-slot="scope">
                <q-input v-model="props.row.name" dense autofocus counter @keyup.enter="scope.set" />
                <q-input v-model="props.row.vorname" dense autofocus counter @keyup.enter="scope.set" />
                <q-input type="number" v-model="props.row.alter" />
                <q-input v-model="props.row.email" dense autofocus counter @keyup.enter="scope.set" />
              </q-popup-edit>
            </q-td>
            <q-td key="id" :props="props">{{ props.row.id }}</q-td>

          </q-tr>
        </template>
      </q-table>
    </div>
  </template>
  
  <script>
  import { ref } from 'vue'
  
  const columns = [
    {
      name: 'name',
      required: true,
      label: 'name',
      align: 'left',
      field: row => row.name,
      format: val => `${val}`,
      sortable: true
    },
    { name: 'vorname', align: 'center', label: 'vorname', field: 'vorname', sortable: true },
    { name: 'alter', label: 'alter', field: 'alter', sortable: true, style: 'width: 10px' },
    { name: 'email', label: 'email', field: 'email' },
    { name: 'id', label: 'id', field: 'id' },

  ]
  
  const rows = [
    {
      name: 'Nessler',
      vorname: 'Ben',
      alter: 6.0,
      email: 'Ben.Nessler@gamil.con',
      id: 1,
    },
    {
      name: 'Nessler',
      vorname: 'Ben',
      alter: 6.0,
      email: 'Ben.Nessler@gamil.con',
      id: 1,
    },
    {
      name: 'Nessler',
      vorname: 'Ben',
      alter: 6.0,
      email: 'Ben.Nessler@gamil.con',
      id: 1,
    },
    {
      name: 'Nessler',
      vorname: 'Ben',
      alter: 6.0,
      email: 'Ben.Nessler@gamil.con',
      id: 1,
    },
  ]
  
  export default {
    setup () {
      return {
        columns,
        rows: ref(rows)
      }
    }
  }
  </script>
