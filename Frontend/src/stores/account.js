import { defineStore, acceptHMRUpdate } from 'pinia'

export const useAccountStore = defineStore('account', {
   state: () => ({
      email: '',

    }),
    getters: {},
    actions: {},
})

if (import.meta.hot) {
    import.meta.hot.accept(acceptHMRUpdate(useAccountStore, import.meta.hot))
}
