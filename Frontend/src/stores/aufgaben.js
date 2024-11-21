import { defineStore, acceptHMRUpdate } from 'pinia';

export const useAufgabenStoreStore = defineStore('aufgaben-store', () => {
    return {
        state: {
            aufgaben: [],
        },
        getters: {},
        actions: {
            setAufgaben(aufgaben) {
                this.aufgaben = aufgaben;
            },
            clearAufgaben() {
                this.aufgaben = [];
            },
        },
    };
});

if (import.meta.hot) {
    import.meta.hot.accept(
        acceptHMRUpdate(useAufgabenStoreStore, import.meta.hot),
    );
}
