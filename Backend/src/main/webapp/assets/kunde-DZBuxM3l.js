import{a7 as o}from"./index-BoGljDLN.js";import{g as s}from"./request-CTPT2BVh.js";import{a}from"./token--ulPFVqS.js";const r=a(),d=o("kunde",{state:()=>({kunden:{},interessenten:{}}),getters:{},actions:{async fetchKunde(n){n=parseInt(n);const e=await(await s("/kunde/"+n)).json();e.kundenstatus==1?this.interessenten[String(n)]=e:this.kunden[String(n)]=e,console.log(e)},async fetchOwnKunden(){const t=await(await s("/kunde/account/"+r.account.id)).json();for(const e of t)e.kundenstatus==1?this.interessenten[String(e.id)]=e:(console.log(e),this.kunden[""+e.id]=e);console.log(this.kunden),console.log(t)}},persist:{enabled:!0,strategy:"local"}});export{d as u};
