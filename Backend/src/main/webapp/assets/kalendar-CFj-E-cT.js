import{a7 as a}from"./index-BoGljDLN.js";import{p as n,g as i}from"./request-CTPT2BVh.js";const m=a("kalendar",{state:()=>({termine:{}}),getters:{active(){let r=Object.values(this.termine).filter(e=>new Date(e.faelligkeitsdatum)>new Date);return console.log(r),r}},actions:{async fetchTermine(){try{const r=await i("/termine/all");if(!r.ok)throw new Error("Failed to fetch termine");const e=await r.json();this.termine={};for(const t of e)this.termine[t.id]=t,console.log(t.id);console.log(this.termine)}catch(r){console.error("Error fetching termine:",r)}},async fetchTermin(r){try{const e=await i(`/termine/${r}`);if(!e.ok)throw new Error("Failed to fetch termin");const t=await e.json();this.termine[r]=t}catch(e){console.error("Error fetching termin:",e)}},async addTermin(r){try{const e=await n("/termine",r);if(!e.ok)throw new Error("Failed to add termin");const t=await e.json();this.termine[t.id]=t}catch(e){console.error("Error adding termin:",e)}},async updateTermin(r,e){try{const t=await n(`/termine/${r}`,{method:"PUT",body:JSON.stringify(e)});if(!t.ok)throw new Error("Failed to update termin");const o=await t.json();this.termine[o.id]=o}catch(t){console.error("Error updating termin:",t)}}},persist:{enabled:!0,strategy:"local"}});export{m as u};
