<template>
    <q-layout view="lHh Lpr lFf">
        <q-header elevated>
            <q-toolbar>
                <q-btn
                    flat
                    dense
                    round
                    icon="menu"
                    aria-label="Menu"
                    @click="toggleLeftDrawer"
                />

                <q-toolbar-title> GFOS Projekt </q-toolbar-title>

            </q-toolbar>
        </q-header>
                <q-drawer v-model="leftDrawerOpen" show-if-above bordered>
                    <q-list>
                        <q-item-label header> </q-item-label>

                        <EssentialLink
                            v-for="link in linksList"
                            :key="link.title"
                            v-bind="link"
                        />
                    </q-list>
                </q-drawer>

        <q-page-container>
            <router-view />
        </q-page-container>
    </q-layout>
</template>

<script setup>
import { ref } from 'vue';
import EssentialLink from 'components/EssentialLink.vue';

defineOptions({
    name: 'MainLayout',
});

const linksList = [
    {
        title: 'Übersicht',
        icon: 'space_dashboard',
        link: '/#/dashboard',
    },
    {
        title: 'Chat',
        icon: 'chat',
        link: '/#/chat',
    },
    {
        title: 'Account',
        icon: 'person',
        link: '/#/account',
    },
];

const leftDrawerOpen = ref(false);

function toggleLeftDrawer() {
    leftDrawerOpen.value = !leftDrawerOpen.value;
}
</script>
