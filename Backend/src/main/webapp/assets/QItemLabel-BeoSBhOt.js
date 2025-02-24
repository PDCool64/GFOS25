import{c as v,g as k,a,h as o,r as q,G as I,T as S}from"./index-BoGljDLN.js";import{u as g,a as y}from"./use-dark-CpAO_YU6.js";import{h as m,a as K}from"./render-CABIAf-z.js";import{b as R,c as $}from"./QBtn-CpPlhTW9.js";const A=["ul","ol"],T=v({name:"QList",props:{...g,bordered:Boolean,dense:Boolean,separator:Boolean,padding:Boolean,tag:{type:String,default:"div"}},setup(e,{slots:n}){const l=k(),i=y(e,l.proxy.$q),u=a(()=>A.includes(e.tag)?null:"list"),r=a(()=>"q-list"+(e.bordered===!0?" q-list--bordered":"")+(e.dense===!0?" q-list--dense":"")+(e.separator===!0?" q-list--separator":"")+(i.value===!0?" q-list--dark":"")+(e.padding===!0?" q-list--padding":""));return()=>o(e.tag,{class:r.value,role:u.value},m(n.default))}}),F=v({name:"QItem",props:{...g,...R,tag:{type:String,default:"div"},active:{type:Boolean,default:null},clickable:Boolean,dense:Boolean,insetLevel:Number,tabindex:[String,Number],focused:Boolean,manualFocus:Boolean},emits:["click","keyup"],setup(e,{slots:n,emit:l}){const{proxy:{$q:i}}=k(),u=y(e,i),{hasLink:r,linkAttrs:_,linkClass:h,linkTag:B,navigateOnClick:x}=$(),c=q(null),d=q(null),b=a(()=>e.clickable===!0||r.value===!0||e.tag==="label"),s=a(()=>e.disable!==!0&&b.value===!0),L=a(()=>"q-item q-item-type row no-wrap"+(e.dense===!0?" q-item--dense":"")+(u.value===!0?" q-item--dark":"")+(r.value===!0&&e.active===null?h.value:e.active===!0?` q-item--active${e.activeClass!==void 0?` ${e.activeClass}`:""}`:"")+(e.disable===!0?" disabled":"")+(s.value===!0?" q-item--clickable q-link cursor-pointer "+(e.manualFocus===!0?"q-manual-focusable":"q-focusable q-hoverable")+(e.focused===!0?" q-manual-focusable--focused":""):"")),C=a(()=>e.insetLevel===void 0?null:{["padding"+(i.lang.rtl===!0?"Right":"Left")]:16+e.insetLevel*56+"px"});function Q(t){s.value===!0&&(d.value!==null&&(t.qKeyEvent!==!0&&document.activeElement===c.value?d.value.focus():document.activeElement===d.value&&c.value.focus()),x(t))}function w(t){if(s.value===!0&&I(t,[13,32])===!0){S(t),t.qKeyEvent=!0;const f=new MouseEvent("click",t);f.qKeyEvent=!0,c.value.dispatchEvent(f)}l("keyup",t)}function E(){const t=K(n.default,[]);return s.value===!0&&t.unshift(o("div",{class:"q-focus-helper",tabindex:-1,ref:d})),t}return()=>{const t={ref:c,class:L.value,style:C.value,role:"listitem",onClick:Q,onKeyup:w};return s.value===!0?(t.tabindex=e.tabindex||"0",Object.assign(t,_.value)):b.value===!0&&(t["aria-disabled"]="true"),o(B.value,t,E())}}}),O=v({name:"QItemSection",props:{avatar:Boolean,thumbnail:Boolean,side:Boolean,top:Boolean,noWrap:Boolean},setup(e,{slots:n}){const l=a(()=>`q-item__section column q-item__section--${e.avatar===!0||e.side===!0||e.thumbnail===!0?"side":"main"}`+(e.top===!0?" q-item__section--top justify-start":" justify-center")+(e.avatar===!0?" q-item__section--avatar":"")+(e.thumbnail===!0?" q-item__section--thumbnail":"")+(e.noWrap===!0?" q-item__section--nowrap":""));return()=>o("div",{class:l.value},m(n.default))}}),W=v({name:"QItemLabel",props:{overline:Boolean,caption:Boolean,header:Boolean,lines:[Number,String]},setup(e,{slots:n}){const l=a(()=>parseInt(e.lines,10)),i=a(()=>"q-item__label"+(e.overline===!0?" q-item__label--overline text-overline":"")+(e.caption===!0?" q-item__label--caption text-caption":"")+(e.header===!0?" q-item__label--header":"")+(l.value===1?" ellipsis":"")),u=a(()=>e.lines!==void 0&&l.value>1?{overflow:"hidden",display:"-webkit-box","-webkit-box-orient":"vertical","-webkit-line-clamp":l.value}:null);return()=>o("div",{style:u.value,class:i.value},m(n.default))}});export{O as Q,W as a,F as b,T as c};
