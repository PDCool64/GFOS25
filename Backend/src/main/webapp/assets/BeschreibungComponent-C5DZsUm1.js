import{Q as i}from"./QSpinner-BFNwqP9i.js";import{Q as x}from"./QPopupProxy-5Umc2OVJ.js";import{_ as D,r as v,a as V,J as B,v as f,f as g,k as S,z as a,m as o,n as _,t as s,A as y,j as A}from"./index-BoGljDLN.js";import{u as N}from"./aufgaben-lGrxl6jT.js";import Q from"./KundeComponent-DwE-4qXk.js";/* empty css                                                                              */const I={id:"aufgabe"},L={class:"outer"},K={class:"row"},M={class:"inner"},P={class:"inner"},$={class:"inner"},j={class:"inner"},z={__name:"BeschreibungComponent",props:{id:String},setup(h){const b=h,n=v(null),u=v(!1),k=V(()=>{if(n.value==null)return 0;let e=0;for(const t of n.value.aufgabenpunktList)e+=t.erledigt;return e}),r=N(),c=e=>new Date(e).toLocaleDateString(),C=e=>{const t=r.aufgaben[e];t?n.value=t:console.error(`Aufgabe mit ID ${e} nicht gefunden`)};B(()=>{const e=b.id;e&&r.fetchAufgabe(e).finally(()=>C(e))});const w=()=>{};return(e,t)=>{var d,m,p;return g(),f("div",I,[n.value?(g(),f("div",{key:0,onClick:w,class:"wrapper"},[a("div",L,[a("div",K,[a("div",M,[o(i,{name:"edit",class:"icon"}),_(" "+s(c(new Date(n.value.erstellungsdatum))),1)]),a("div",P,[o(i,{name:"schedule",class:"icon"}),_(" "+s(c(new Date(n.value.faelligkeitsdatum))),1)]),a("div",{class:"inner",onContextmenu:t[1]||(t[1]=y(l=>(u.value=!0,console.log("Something happened"),console.log(u.value)),["prevent"]))},[o(i,{name:"person",class:"icon relative"}),a("div",null,s((d=n.value.kunde)==null?void 0:d.vorname)+" "+s((m=n.value.kunde)==null?void 0:m.nachname),1),o(x,{modelValue:u.value,"onUpdate:modelValue":t[0]||(t[0]=l=>u.value=l)},{default:A(()=>{var l;return[o(Q,{id:""+((l=n.value.kunde)==null?void 0:l.id)},null,8,["id"])]}),_:1},8,["modelValue"])],32)]),a("div",$,[o(i,{class:"icon",name:"description"}),a("div",null,s(n.value.beschreibung),1)]),a("div",j,[o(i,{class:"icon",name:"task_alt"}),a("div",null,s(k.value)+"/"+s((p=n.value.aufgabenpunktList)==null?void 0:p.length),1)])])])):S("",!0)])}}},G=D(z,[["__scopeId","data-v-d3866b6f"]]);export{G as B};
