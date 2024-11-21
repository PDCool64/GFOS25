import { defineStore, acceptHMRUpdate } from 'pinia';

export const useTokenStore = defineStore('token', {
    state: () => ({
        token: '',
    }),
    getters: {},
    actions: {
        setToken(token) {
            this.token = token;
        },
        clearToken() {
            this.token = '';
        },
    },
});

if (import.meta.hot) {
    import.meta.hot.accept(acceptHMRUpdate(useTokenStore, import.meta.hot));
}
