import { defineStore, acceptHMRUpdate } from 'pinia';
import address from 'src/address';
import { useAccountStore } from './account';
import { useTokenStore } from './token';

const accountStore = useAccountStore();
const tokenStore = useTokenStore();

export const useAufgabenStore = defineStore('aufgaben', {
      state: () => ({
          aufgaben: {},
      }),
      getters: {},
      actions: {
          async fetchAufgaben() {
              console.log("Fetching Aufgaben");
              if (accountStore.account?.id == undefined){
                  console.log("Account not defined");
                  return;
              }
              const response = await fetch(address + '/accounts/aufgaben/' + accountStore.account.id, {
                  headers: {
                      Authorization: tokenStore.token,
                  }
              });
              if (!response.ok) {
                  console.log(response);
                  throw new Error('HTTP error, status = ' + response.status);
              }
              const data = await response.json();
              for (const aufgabe of data) {
                  this.aufgaben[aufgabe.id] = aufgabe;
              }
              console.log(this.aufgaben);
          }
      }
});

if (import.meta.hot) {
    import.meta.hot.accept(
        acceptHMRUpdate(useAufgabenStore, import.meta.hot),
    );
}
