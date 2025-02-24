import{a as M,aq as Ge,ar as Ht,c as Ct,g as qt,r as A,P as Ot,w as re,Q as Pe,h as g,an as oe}from"./index-BoGljDLN.js";import{Q as N}from"./QBtn-CpPlhTW9.js";import{u as kt,a as It}from"./use-dark-CpAO_YU6.js";import{d as jt,h as Tt,i as Vt}from"./use-key-composition-ChOD7TKA.js";import{p as w}from"./format-BOf40pHW.js";import{h as $t}from"./render-CABIAf-z.js";function Ft(){let e=Object.create(null);return{getCache:(r,s)=>e[r]===void 0?e[r]=typeof s=="function"?s():s:e[r],setCache(r,s){e[r]=s},hasCache(r){return Object.hasOwnProperty.call(e,r)},clearCache(r){r!==void 0?delete e[r]:e=Object.create(null)}}}const z=[-61,9,38,199,426,686,756,818,1111,1181,1210,1635,2060,2097,2192,2262,2324,2394,2456,3178];function Et(e,r,s){return Object.prototype.toString.call(e)==="[object Date]"&&(s=e.getDate(),r=e.getMonth()+1,e=e.getFullYear()),Bt(Ie(e,r,s))}function Xe(e,r,s){return et(Zt(e,r,s))}function At(e){return Nt(e)===0}function me(e,r){return r<=6?31:r<=11||At(e)?30:29}function Nt(e){const r=z.length;let s=z[0],i,d,o,D,c;if(e<s||e>=z[r-1])throw new Error("Invalid Jalaali year "+e);for(c=1;c<r&&(i=z[c],d=i-s,!(e<i));c+=1)s=i;return D=e-s,d-D<6&&(D=D-d+S(d+4,33)*33),o=I(I(D+1,33)-1,4),o===-1&&(o=4),o}function Ke(e,r){const s=z.length,i=e+621;let d=-14,o=z[0],D,c,m,x,f;if(e<o||e>=z[s-1])throw new Error("Invalid Jalaali year "+e);for(f=1;f<s&&(D=z[f],c=D-o,!(e<D));f+=1)d=d+S(c,33)*8+S(I(c,33),4),o=D;x=e-o,d=d+S(x,33)*8+S(I(x,33)+3,4),I(c,33)===4&&c-x===4&&(d+=1);const Y=S(i,4)-S((S(i,100)+1)*3,4)-150,F=20+d-Y;return r||(c-x<6&&(x=x-c+S(c+4,33)*33),m=I(I(x+1,33)-1,4),m===-1&&(m=4)),{leap:m,gy:i,march:F}}function Zt(e,r,s){const i=Ke(e,!0);return Ie(i.gy,3,i.march)+(r-1)*31-S(r,7)*(r-7)+s-1}function Bt(e){const r=et(e).gy;let s=r-621,i,d,o;const D=Ke(s,!1),c=Ie(r,3,D.march);if(o=e-c,o>=0){if(o<=185)return d=1+S(o,31),i=I(o,31)+1,{jy:s,jm:d,jd:i};o-=186}else s-=1,o+=179,D.leap===1&&(o+=1);return d=7+S(o,30),i=I(o,30)+1,{jy:s,jm:d,jd:i}}function Ie(e,r,s){let i=S((e+S(r-8,6)+100100)*1461,4)+S(153*I(r+9,12)+2,5)+s-34840408;return i=i-S(S(e+100100+S(r-8,6),100)*3,4)+752,i}function et(e){let r=4*e+139361631;r=r+S(S(4*e+183187720,146097)*3,4)*4-3908;const s=S(I(r,1461),4)*5+308,i=S(I(s,153),5)+1,d=I(S(s,153),12)+1;return{gy:S(r,1461)-100100+S(8-d,6),gm:d,gd:i}}function S(e,r){return~~(e/r)}function I(e,r){return e-~~(e/r)*r}const Qt=["gregorian","persian"],Je={mask:{type:String},locale:Object,calendar:{type:String,validator:e=>Qt.includes(e),default:"gregorian"},landscape:Boolean,color:String,textColor:String,square:Boolean,flat:Boolean,bordered:Boolean,readonly:Boolean,disable:Boolean},Rt=["update:modelValue"];function Q(e){return e.year+"/"+w(e.month)+"/"+w(e.day)}function zt(e,r){const s=M(()=>e.disable!==!0&&e.readonly!==!0),i=M(()=>s.value===!0?0:-1),d=M(()=>{const c=[];return e.color!==void 0&&c.push(`bg-${e.color}`),e.textColor!==void 0&&c.push(`text-${e.textColor}`),c.join(" ")});function o(){return e.locale!==void 0?{...r.lang.date,...e.locale}:r.lang.date}function D(c){const m=new Date,x=c===!0?null:0;if(e.calendar==="persian"){const f=Et(m);return{year:f.jy,month:f.jm,day:f.jd}}return{year:m.getFullYear(),month:m.getMonth()+1,day:m.getDate(),hour:x,minute:x,second:x,millisecond:x}}return{editable:s,tabindex:i,headerClass:d,getLocale:o,getCurrentDate:D}}const tt=864e5,Lt=36e5,ke=6e4,at="YYYY-MM-DDTHH:mm:ss.SSSZ",Pt=/\[((?:[^\]\\]|\\]|\\)*)\]|do|d{1,4}|Mo|M{1,4}|m{1,2}|wo|w{1,2}|Qo|Do|DDDo|D{1,4}|YY(?:YY)?|H{1,2}|h{1,2}|s{1,2}|S{1,3}|Z{1,2}|a{1,2}|[AQExX]/g,Xt=/(\[[^\]]*\])|do|d{1,4}|Mo|M{1,4}|m{1,2}|wo|w{1,2}|Qo|Do|DDDo|D{1,4}|YY(?:YY)?|H{1,2}|h{1,2}|s{1,2}|S{1,3}|Z{1,2}|a{1,2}|[AQExX]|([.*+:?^,\s${}()|\\]+)/g,He={};function Jt(e,r){const s="("+r.days.join("|")+")",i=e+s;if(He[i]!==void 0)return He[i];const d="("+r.daysShort.join("|")+")",o="("+r.months.join("|")+")",D="("+r.monthsShort.join("|")+")",c={};let m=0;const x=e.replace(Xt,Y=>{switch(m++,Y){case"YY":return c.YY=m,"(-?\\d{1,2})";case"YYYY":return c.YYYY=m,"(-?\\d{1,4})";case"M":return c.M=m,"(\\d{1,2})";case"Mo":return c.M=m++,"(\\d{1,2}(st|nd|rd|th))";case"MM":return c.M=m,"(\\d{2})";case"MMM":return c.MMM=m,D;case"MMMM":return c.MMMM=m,o;case"D":return c.D=m,"(\\d{1,2})";case"Do":return c.D=m++,"(\\d{1,2}(st|nd|rd|th))";case"DD":return c.D=m,"(\\d{2})";case"H":return c.H=m,"(\\d{1,2})";case"HH":return c.H=m,"(\\d{2})";case"h":return c.h=m,"(\\d{1,2})";case"hh":return c.h=m,"(\\d{2})";case"m":return c.m=m,"(\\d{1,2})";case"mm":return c.m=m,"(\\d{2})";case"s":return c.s=m,"(\\d{1,2})";case"ss":return c.s=m,"(\\d{2})";case"S":return c.S=m,"(\\d{1})";case"SS":return c.S=m,"(\\d{2})";case"SSS":return c.S=m,"(\\d{3})";case"A":return c.A=m,"(AM|PM)";case"a":return c.a=m,"(am|pm)";case"aa":return c.aa=m,"(a\\.m\\.|p\\.m\\.)";case"ddd":return d;case"dddd":return s;case"Q":case"d":case"E":return"(\\d{1})";case"do":return m++,"(\\d{1}(st|nd|rd|th))";case"Qo":return"(1st|2nd|3rd|4th)";case"DDD":case"DDDD":return"(\\d{1,3})";case"DDDo":return m++,"(\\d{1,3}(st|nd|rd|th))";case"w":return"(\\d{1,2})";case"wo":return m++,"(\\d{1,2}(st|nd|rd|th))";case"ww":return"(\\d{2})";case"Z":return c.Z=m,"(Z|[+-]\\d{2}:\\d{2})";case"ZZ":return c.ZZ=m,"(Z|[+-]\\d{2}\\d{2})";case"X":return c.X=m,"(-?\\d+)";case"x":return c.x=m,"(-?\\d{4,})";default:return m--,Y[0]==="["&&(Y=Y.substring(1,Y.length-1)),Y.replace(/[.*+?^${}()|[\]\\]/g,"\\$&")}}),f={map:c,regex:new RegExp("^"+x)};return He[i]=f,f}function nt(e,r){return e!==void 0?e:r!==void 0?r.date:Ht.date}function We(e,r=""){const s=e>0?"-":"+",i=Math.abs(e),d=Math.floor(i/60),o=i%60;return s+w(d)+r+w(o)}function Wt(e,r,s,i,d){const o={year:null,month:null,day:null,hour:null,minute:null,second:null,millisecond:null,timezoneOffset:null,dateHash:null,timeHash:null};if(d!==void 0&&Object.assign(o,d),e==null||e===""||typeof e!="string")return o;r===void 0&&(r=at);const D=nt(s,Ge.props),c=D.months,m=D.monthsShort,{regex:x,map:f}=Jt(r,D),Y=e.match(x);if(Y===null)return o;let F="";if(f.X!==void 0||f.x!==void 0){const T=parseInt(Y[f.X!==void 0?f.X:f.x],10);if(isNaN(T)===!0||T<0)return o;const V=new Date(T*(f.X!==void 0?1e3:1));o.year=V.getFullYear(),o.month=V.getMonth()+1,o.day=V.getDate(),o.hour=V.getHours(),o.minute=V.getMinutes(),o.second=V.getSeconds(),o.millisecond=V.getMilliseconds()}else{if(f.YYYY!==void 0)o.year=parseInt(Y[f.YYYY],10);else if(f.YY!==void 0){const T=parseInt(Y[f.YY],10);o.year=T<0?T:2e3+T}if(f.M!==void 0){if(o.month=parseInt(Y[f.M],10),o.month<1||o.month>12)return o}else f.MMM!==void 0?o.month=m.indexOf(Y[f.MMM])+1:f.MMMM!==void 0&&(o.month=c.indexOf(Y[f.MMMM])+1);if(f.D!==void 0){if(o.day=parseInt(Y[f.D],10),o.year===null||o.month===null||o.day<1)return o;const T=i!=="persian"?new Date(o.year,o.month,0).getDate():me(o.year,o.month);if(o.day>T)return o}f.H!==void 0?o.hour=parseInt(Y[f.H],10)%24:f.h!==void 0&&(o.hour=parseInt(Y[f.h],10)%12,(f.A&&Y[f.A]==="PM"||f.a&&Y[f.a]==="pm"||f.aa&&Y[f.aa]==="p.m.")&&(o.hour+=12),o.hour=o.hour%24),f.m!==void 0&&(o.minute=parseInt(Y[f.m],10)%60),f.s!==void 0&&(o.second=parseInt(Y[f.s],10)%60),f.S!==void 0&&(o.millisecond=parseInt(Y[f.S],10)*10**(3-Y[f.S].length)),(f.Z!==void 0||f.ZZ!==void 0)&&(F=f.Z!==void 0?Y[f.Z].replace(":",""):Y[f.ZZ],o.timezoneOffset=(F[0]==="+"?-1:1)*(60*F.slice(1,3)+1*F.slice(3,5)))}return o.dateHash=w(o.year,6)+"/"+w(o.month)+"/"+w(o.day),o.timeHash=w(o.hour)+":"+w(o.minute)+":"+w(o.second)+F,o}function Ce(e){const r=new Date(e.getFullYear(),e.getMonth(),e.getDate());r.setDate(r.getDate()-(r.getDay()+6)%7+3);const s=new Date(r.getFullYear(),0,4);s.setDate(s.getDate()-(s.getDay()+6)%7+3);const i=r.getTimezoneOffset()-s.getTimezoneOffset();r.setHours(r.getHours()-i);const d=(r-s)/(tt*7);return 1+Math.floor(d)}function Z(e,r,s){const i=new Date(e),d=`set${s===!0?"UTC":""}`;switch(r){case"year":case"years":i[`${d}Month`](0);case"month":case"months":i[`${d}Date`](1);case"day":case"days":case"date":i[`${d}Hours`](0);case"hour":case"hours":i[`${d}Minutes`](0);case"minute":case"minutes":i[`${d}Seconds`](0);case"second":case"seconds":i[`${d}Milliseconds`](0)}return i}function fe(e,r,s){return(e.getTime()-e.getTimezoneOffset()*ke-(r.getTime()-r.getTimezoneOffset()*ke))/s}function rt(e,r,s="days"){const i=new Date(e),d=new Date(r);switch(s){case"years":case"year":return i.getFullYear()-d.getFullYear();case"months":case"month":return(i.getFullYear()-d.getFullYear())*12+i.getMonth()-d.getMonth();case"days":case"day":case"date":return fe(Z(i,"day"),Z(d,"day"),tt);case"hours":case"hour":return fe(Z(i,"hour"),Z(d,"hour"),Lt);case"minutes":case"minute":return fe(Z(i,"minute"),Z(d,"minute"),ke);case"seconds":case"second":return fe(Z(i,"second"),Z(d,"second"),1e3)}}function qe(e){return rt(e,Z(e,"year"),"days")+1}function K(e){if(e>=11&&e<=13)return`${e}th`;switch(e%10){case 1:return`${e}st`;case 2:return`${e}nd`;case 3:return`${e}rd`}return`${e}th`}const Ue={YY(e,r,s){const i=this.YYYY(e,r,s)%100;return i>=0?w(i):"-"+w(Math.abs(i))},YYYY(e,r,s){return s!=null?s:e.getFullYear()},M(e){return e.getMonth()+1},Mo(e){return K(e.getMonth()+1)},MM(e){return w(e.getMonth()+1)},MMM(e,r){return r.monthsShort[e.getMonth()]},MMMM(e,r){return r.months[e.getMonth()]},Q(e){return Math.ceil((e.getMonth()+1)/3)},Qo(e){return K(this.Q(e))},D(e){return e.getDate()},Do(e){return K(e.getDate())},DD(e){return w(e.getDate())},DDD(e){return qe(e)},DDDo(e){return K(qe(e))},DDDD(e){return w(qe(e),3)},d(e){return e.getDay()},do(e){return K(e.getDay())},dd(e,r){return r.days[e.getDay()].slice(0,2)},ddd(e,r){return r.daysShort[e.getDay()]},dddd(e,r){return r.days[e.getDay()]},E(e){return e.getDay()||7},w(e){return Ce(e)},wo(e){return K(Ce(e))},ww(e){return w(Ce(e))},H(e){return e.getHours()},HH(e){return w(e.getHours())},h(e){const r=e.getHours();return r===0?12:r>12?r%12:r},hh(e){return w(this.h(e))},m(e){return e.getMinutes()},mm(e){return w(e.getMinutes())},s(e){return e.getSeconds()},ss(e){return w(e.getSeconds())},S(e){return Math.floor(e.getMilliseconds()/100)},SS(e){return w(Math.floor(e.getMilliseconds()/10))},SSS(e){return w(e.getMilliseconds(),3)},A(e){return e.getHours()<12?"AM":"PM"},a(e){return e.getHours()<12?"am":"pm"},aa(e){return e.getHours()<12?"a.m.":"p.m."},Z(e,r,s,i){const d=i==null?e.getTimezoneOffset():i;return We(d,":")},ZZ(e,r,s,i){const d=i==null?e.getTimezoneOffset():i;return We(d)},X(e){return Math.floor(e.getTime()/1e3)},x(e){return e.getTime()}};function Ut(e,r,s,i,d){if(e!==0&&!e||e===1/0||e===-1/0)return;const o=new Date(e);if(isNaN(o))return;r===void 0&&(r=at);const D=nt(s,Ge.props);return r.replace(Pt,(c,m)=>c in Ue?Ue[c](o,D,i,d):m===void 0?c:m.split("\\]").join("]"))}const J=20,pt=["Calendar","Years","Months"],pe=e=>pt.includes(e),Oe=e=>/^-?[\d]+\/[0-1]\d$/.test(e),ee=" — ";function R(e){return e.year+"/"+w(e.month)}const ra=Ct({name:"QDate",props:{...Je,...jt,...kt,modelValue:{required:!0,validator:e=>typeof e=="string"||Array.isArray(e)===!0||Object(e)===e||e===null},multiple:Boolean,range:Boolean,title:String,subtitle:String,mask:{...Je.mask,default:"YYYY/MM/DD"},defaultYearMonth:{type:String,validator:Oe},yearsInMonthView:Boolean,events:[Array,Function],eventColor:[String,Function],emitImmediately:Boolean,options:[Array,Function],navigationMinYearMonth:{type:String,validator:Oe},navigationMaxYearMonth:{type:String,validator:Oe},noUnset:Boolean,firstDayOfWeek:[String,Number],todayBtn:Boolean,minimal:Boolean,defaultView:{type:String,default:"Calendar",validator:pe}},emits:[...Rt,"rangeStart","rangeEnd","navigation"],setup(e,{slots:r,emit:s}){const{proxy:i}=qt(),{$q:d}=i,o=It(e,d),{getCache:D}=Ft(),{tabindex:c,headerClass:m,getLocale:x,getCurrentDate:f}=zt(e,d);let Y;const F=Tt(e),T=Vt(F),V=A(null),L=A(Ne()),H=A(x()),ot=M(()=>Ne()),lt=M(()=>x()),E=M(()=>f()),h=A(Ze(L.value,H.value)),k=A(e.defaultView),je=M(()=>d.lang.rtl===!0?"right":"left"),le=A(je.value),he=A(je.value),ge=h.value.year,ue=A(ge-ge%J-(ge<0?J:0)),O=A(null),ut=M(()=>{const t=e.landscape===!0?"landscape":"portrait";return`q-date q-date--${t} q-date--${t}-${e.minimal===!0?"minimal":"standard"}`+(o.value===!0?" q-date--dark q-dark":"")+(e.bordered===!0?" q-date--bordered":"")+(e.square===!0?" q-date--square no-border-radius":"")+(e.flat===!0?" q-date--flat no-shadow":"")+(e.disable===!0?" disabled":e.readonly===!0?" q-date--readonly":"")}),P=M(()=>e.color||"primary"),W=M(()=>e.textColor||"white"),se=M(()=>e.emitImmediately===!0&&e.multiple!==!0&&e.range!==!0),ye=M(()=>Array.isArray(e.modelValue)===!0?e.modelValue:e.modelValue!==null&&e.modelValue!==void 0?[e.modelValue]:[]),$=M(()=>ye.value.filter(t=>typeof t=="string").map(t=>be(t,L.value,H.value)).filter(t=>t.dateHash!==null&&t.day!==null&&t.month!==null&&t.year!==null)),U=M(()=>{const t=a=>be(a,L.value,H.value);return ye.value.filter(a=>Ot(a)===!0&&a.from!==void 0&&a.to!==void 0).map(a=>({from:t(a.from),to:t(a.to)})).filter(a=>a.from.dateHash!==null&&a.to.dateHash!==null&&a.from.dateHash<a.to.dateHash)}),ie=M(()=>e.calendar!=="persian"?t=>new Date(t.year,t.month-1,t.day):t=>{const a=Xe(t.year,t.month,t.day);return new Date(a.gy,a.gm-1,a.gd)}),Me=M(()=>e.calendar==="persian"?Q:(t,a,n)=>Ut(new Date(t.year,t.month-1,t.day,t.hour,t.minute,t.second,t.millisecond),a===void 0?L.value:a,n===void 0?H.value:n,t.year,t.timezoneOffset)),te=M(()=>$.value.length+U.value.reduce((t,a)=>t+1+rt(ie.value(a.to),ie.value(a.from)),0)),Te=M(()=>{if(e.title!==void 0&&e.title!==null&&e.title.length!==0)return e.title;if(O.value!==null){const n=O.value.init,l=ie.value(n);return H.value.daysShort[l.getDay()]+", "+H.value.monthsShort[n.month-1]+" "+n.day+ee+"?"}if(te.value===0)return ee;if(te.value>1)return`${te.value} ${H.value.pluralDay}`;const t=$.value[0],a=ie.value(t);return isNaN(a.valueOf())===!0?ee:H.value.headerTitle!==void 0?H.value.headerTitle(a,t):H.value.daysShort[a.getDay()]+", "+H.value.monthsShort[t.month-1]+" "+t.day}),st=M(()=>$.value.concat(U.value.map(a=>a.from)).sort((a,n)=>a.year-n.year||a.month-n.month)[0]),it=M(()=>$.value.concat(U.value.map(a=>a.to)).sort((a,n)=>n.year-a.year||n.month-a.month)[0]),Ve=M(()=>{if(e.subtitle!==void 0&&e.subtitle!==null&&e.subtitle.length!==0)return e.subtitle;if(te.value===0)return ee;if(te.value>1){const t=st.value,a=it.value,n=H.value.monthsShort;return n[t.month-1]+(t.year!==a.year?" "+t.year+ee+n[a.month-1]+" ":t.month!==a.month?ee+n[a.month-1]:"")+" "+a.year}return $.value[0].year}),ce=M(()=>{const t=[d.iconSet.datetime.arrowLeft,d.iconSet.datetime.arrowRight];return d.lang.rtl===!0?t.reverse():t}),$e=M(()=>e.firstDayOfWeek!==void 0?Number(e.firstDayOfWeek):H.value.firstDayOfWeek),ct=M(()=>{const t=H.value.daysShort,a=$e.value;return a>0?t.slice(a,7).concat(t.slice(0,a)):t}),B=M(()=>{const t=h.value;return e.calendar!=="persian"?new Date(t.year,t.month,0).getDate():me(t.year,t.month)}),dt=M(()=>typeof e.eventColor=="function"?e.eventColor:()=>e.eventColor),C=M(()=>{if(e.navigationMinYearMonth===void 0)return null;const t=e.navigationMinYearMonth.split("/");return{year:parseInt(t[0],10),month:parseInt(t[1],10)}}),q=M(()=>{if(e.navigationMaxYearMonth===void 0)return null;const t=e.navigationMaxYearMonth.split("/");return{year:parseInt(t[0],10),month:parseInt(t[1],10)}}),De=M(()=>{const t={month:{prev:!0,next:!0},year:{prev:!0,next:!0}};return C.value!==null&&C.value.year>=h.value.year&&(t.year.prev=!1,C.value.year===h.value.year&&C.value.month>=h.value.month&&(t.month.prev=!1)),q.value!==null&&q.value.year<=h.value.year&&(t.year.next=!1,q.value.year===h.value.year&&q.value.month<=h.value.month&&(t.month.next=!1)),t}),de=M(()=>{const t={};return $.value.forEach(a=>{const n=R(a);t[n]===void 0&&(t[n]=[]),t[n].push(a.day)}),t}),Fe=M(()=>{const t={};return U.value.forEach(a=>{const n=R(a.from),l=R(a.to);if(t[n]===void 0&&(t[n]=[]),t[n].push({from:a.from.day,to:n===l?a.to.day:void 0,range:a}),n<l){let u;const{year:b,month:v}=a.from,y=v<12?{year:b,month:v+1}:{year:b+1,month:1};for(;(u=R(y))<=l;)t[u]===void 0&&(t[u]=[]),t[u].push({from:void 0,to:u===l?a.to.day:void 0,range:a}),y.month++,y.month>12&&(y.year++,y.month=1)}}),t}),ae=M(()=>{if(O.value===null)return;const{init:t,initHash:a,final:n,finalHash:l}=O.value,[u,b]=a<=l?[t,n]:[n,t],v=R(u),y=R(b);if(v!==j.value&&y!==j.value)return;const _={};return v===j.value?(_.from=u.day,_.includeFrom=!0):_.from=1,y===j.value?(_.to=b.day,_.includeTo=!0):_.to=B.value,_}),j=M(()=>R(h.value)),vt=M(()=>{const t={};if(e.options===void 0){for(let n=1;n<=B.value;n++)t[n]=!0;return t}const a=typeof e.options=="function"?e.options:n=>e.options.includes(n);for(let n=1;n<=B.value;n++){const l=j.value+"/"+w(n);t[n]=a(l)}return t}),ft=M(()=>{const t={};if(e.events===void 0)for(let a=1;a<=B.value;a++)t[a]=!1;else{const a=typeof e.events=="function"?e.events:n=>e.events.includes(n);for(let n=1;n<=B.value;n++){const l=j.value+"/"+w(n);t[n]=a(l)===!0&&dt.value(l)}}return t}),mt=M(()=>{let t,a;const{year:n,month:l}=h.value;if(e.calendar!=="persian")t=new Date(n,l-1,1),a=new Date(n,l-1,0).getDate();else{const u=Xe(n,l,1);t=new Date(u.gy,u.gm-1,u.gd);let b=l-1,v=n;b===0&&(b=12,v--),a=me(v,b)}return{days:t.getDay()-$e.value-1,endDay:a}}),Ee=M(()=>{const t=[],{days:a,endDay:n}=mt.value,l=a<0?a+7:a;if(l<6)for(let v=n-l;v<=n;v++)t.push({i:v,fill:!0});const u=t.length;for(let v=1;v<=B.value;v++){const y={i:v,event:ft.value[v],classes:[]};vt.value[v]===!0&&(y.in=!0,y.flat=!0),t.push(y)}if(de.value[j.value]!==void 0&&de.value[j.value].forEach(v=>{const y=u+v-1;Object.assign(t[y],{selected:!0,unelevated:!0,flat:!1,color:P.value,textColor:W.value})}),Fe.value[j.value]!==void 0&&Fe.value[j.value].forEach(v=>{if(v.from!==void 0){const y=u+v.from-1,_=u+(v.to||B.value)-1;for(let ne=y;ne<=_;ne++)Object.assign(t[ne],{range:v.range,unelevated:!0,color:P.value,textColor:W.value});Object.assign(t[y],{rangeFrom:!0,flat:!1}),v.to!==void 0&&Object.assign(t[_],{rangeTo:!0,flat:!1})}else if(v.to!==void 0){const y=u+v.to-1;for(let _=u;_<=y;_++)Object.assign(t[_],{range:v.range,unelevated:!0,color:P.value,textColor:W.value});Object.assign(t[y],{flat:!1,rangeTo:!0})}else{const y=u+B.value-1;for(let _=u;_<=y;_++)Object.assign(t[_],{range:v.range,unelevated:!0,color:P.value,textColor:W.value})}}),ae.value!==void 0){const v=u+ae.value.from-1,y=u+ae.value.to-1;for(let _=v;_<=y;_++)t[_].color=P.value,t[_].editRange=!0;ae.value.includeFrom===!0&&(t[v].editRangeFrom=!0),ae.value.includeTo===!0&&(t[y].editRangeTo=!0)}h.value.year===E.value.year&&h.value.month===E.value.month&&(t[u+E.value.day-1].today=!0);const b=t.length%7;if(b>0){const v=7-b;for(let y=1;y<=v;y++)t.push({i:y,fill:!0})}return t.forEach(v=>{let y="q-date__calendar-item ";v.fill===!0?y+="q-date__calendar-item--fill":(y+=`q-date__calendar-item--${v.in===!0?"in":"out"}`,v.range!==void 0&&(y+=` q-date__range${v.rangeTo===!0?"-to":v.rangeFrom===!0?"-from":""}`),v.editRange===!0&&(y+=` q-date__edit-range${v.editRangeFrom===!0?"-from":""}${v.editRangeTo===!0?"-to":""}`),(v.range!==void 0||v.editRange===!0)&&(y+=` text-${v.color}`)),v.classes=y}),t}),ht=M(()=>e.disable===!0?{"aria-disabled":"true"}:{});re(()=>e.modelValue,t=>{if(Y===t)Y=0;else{const a=Ze(L.value,H.value);p(a.year,a.month,a)}}),re(k,()=>{V.value!==null&&i.$el.contains(document.activeElement)===!0&&V.value.focus()}),re(()=>h.value.year+"|"+h.value.month,()=>{s("navigation",{year:h.value.year,month:h.value.month})}),re(ot,t=>{Le(t,H.value,"mask"),L.value=t}),re(lt,t=>{Le(L.value,t,"locale"),H.value=t});function Ae(){const{year:t,month:a,day:n}=E.value,l={...h.value,year:t,month:a,day:n},u=de.value[R(l)];(u===void 0||u.includes(l.day)===!1)&&_e(l),Ye(l.year,l.month)}function gt(t){pe(t)===!0&&(k.value=t)}function yt(t,a){["month","year"].includes(t)&&(t==="month"?Qe:we)(a===!0?-1:1)}function Ye(t,a){k.value="Calendar",p(t,a)}function Mt(t,a){if(e.range===!1||!t){O.value=null;return}const n=Object.assign({...h.value},t),l=a!==void 0?Object.assign({...h.value},a):n;O.value={init:n,initHash:Q(n),final:l,finalHash:Q(l)},Ye(n.year,n.month)}function Ne(){return e.calendar==="persian"?"YYYY/MM/DD":e.mask}function be(t,a,n){return Wt(t,a,n,e.calendar,{hour:0,minute:0,second:0,millisecond:0})}function Ze(t,a){const n=Array.isArray(e.modelValue)===!0?e.modelValue:e.modelValue?[e.modelValue]:[];if(n.length===0)return Be();const l=n[n.length-1],u=be(l.from!==void 0?l.from:l,t,a);return u.dateHash===null?Be():u}function Be(){let t,a;if(e.defaultYearMonth!==void 0){const n=e.defaultYearMonth.split("/");t=parseInt(n[0],10),a=parseInt(n[1],10)}else{const n=E.value!==void 0?E.value:f();t=n.year,a=n.month}return{year:t,month:a,day:1,hour:0,minute:0,second:0,millisecond:0,dateHash:t+"/"+w(a)+"/01"}}function Qe(t){let a=h.value.year,n=Number(h.value.month)+t;n===13?(n=1,a++):n===0&&(n=12,a--),p(a,n),se.value===!0&&ve("month")}function we(t){const a=Number(h.value.year)+t;p(a,h.value.month),se.value===!0&&ve("year")}function Dt(t){p(t,h.value.month),k.value=e.defaultView==="Years"?"Months":"Calendar",se.value===!0&&ve("year")}function Yt(t){p(h.value.year,t),k.value="Calendar",se.value===!0&&ve("month")}function bt(t,a){const n=de.value[a];(n!==void 0&&n.includes(t.day)===!0?Se:_e)(t)}function X(t){return{year:t.year,month:t.month,day:t.day}}function p(t,a,n){if(C.value!==null&&t<=C.value.year&&((a<C.value.month||t<C.value.year)&&(a=C.value.month),t=C.value.year),q.value!==null&&t>=q.value.year&&((a>q.value.month||t>q.value.year)&&(a=q.value.month),t=q.value.year),n!==void 0){const{hour:u,minute:b,second:v,millisecond:y,timezoneOffset:_,timeHash:ne}=n;Object.assign(h.value,{hour:u,minute:b,second:v,millisecond:y,timezoneOffset:_,timeHash:ne})}const l=t+"/"+w(a)+"/01";l!==h.value.dateHash&&(le.value=h.value.dateHash<l==(d.lang.rtl!==!0)?"left":"right",t!==h.value.year&&(he.value=le.value),Pe(()=>{ue.value=t-t%J-(t<0?J:0),Object.assign(h.value,{year:t,month:a,day:1,dateHash:l})}))}function Re(t,a,n){const l=t!==null&&t.length===1&&e.multiple===!1?t[0]:t;Y=l;const{reason:u,details:b}=ze(a,n);s("update:modelValue",l,u,b)}function ve(t){const a=$.value[0]!==void 0&&$.value[0].dateHash!==null?{...$.value[0]}:{...h.value};Pe(()=>{a.year=h.value.year,a.month=h.value.month;const n=e.calendar!=="persian"?new Date(a.year,a.month,0).getDate():me(a.year,a.month);a.day=Math.min(Math.max(1,a.day),n);const l=G(a);Y=l;const{details:u}=ze("",a);s("update:modelValue",l,t,u)})}function ze(t,a){return a.from!==void 0?{reason:`${t}-range`,details:{...X(a.target),from:X(a.from),to:X(a.to)}}:{reason:`${t}-day`,details:X(a)}}function G(t,a,n){return t.from!==void 0?{from:Me.value(t.from,a,n),to:Me.value(t.to,a,n)}:Me.value(t,a,n)}function _e(t){let a;if(e.multiple===!0)if(t.from!==void 0){const n=Q(t.from),l=Q(t.to),u=$.value.filter(v=>v.dateHash<n||v.dateHash>l),b=U.value.filter(({from:v,to:y})=>y.dateHash<n||v.dateHash>l);a=u.concat(b).concat(t).map(v=>G(v))}else{const n=ye.value.slice();n.push(G(t)),a=n}else a=G(t);Re(a,"add",t)}function Se(t){if(e.noUnset===!0)return;let a=null;if(e.multiple===!0&&Array.isArray(e.modelValue)===!0){const n=G(t);t.from!==void 0?a=e.modelValue.filter(l=>l.from!==void 0?l.from!==n.from&&l.to!==n.to:!0):a=e.modelValue.filter(l=>l!==n),a.length===0&&(a=null)}Re(a,"remove",t)}function Le(t,a,n){const l=$.value.concat(U.value).map(u=>G(u,t,a)).filter(u=>u.from!==void 0?u.from.dateHash!==null&&u.to.dateHash!==null:u.dateHash!==null);s("update:modelValue",(e.multiple===!0?l:l[0])||null,n)}function wt(){if(e.minimal!==!0)return g("div",{class:"q-date__header "+m.value},[g("div",{class:"relative-position"},[g(oe,{name:"q-transition--fade"},()=>g("div",{key:"h-yr-"+Ve.value,class:"q-date__header-subtitle q-date__header-link "+(k.value==="Years"?"q-date__header-link--active":"cursor-pointer"),tabindex:c.value,...D("vY",{onClick(){k.value="Years"},onKeyup(t){t.keyCode===13&&(k.value="Years")}})},[Ve.value]))]),g("div",{class:"q-date__header-title relative-position flex no-wrap"},[g("div",{class:"relative-position col"},[g(oe,{name:"q-transition--fade"},()=>g("div",{key:"h-sub"+Te.value,class:"q-date__header-title-label q-date__header-link "+(k.value==="Calendar"?"q-date__header-link--active":"cursor-pointer"),tabindex:c.value,...D("vC",{onClick(){k.value="Calendar"},onKeyup(t){t.keyCode===13&&(k.value="Calendar")}})},[Te.value]))]),e.todayBtn===!0?g(N,{class:"q-date__header-today self-start",icon:d.iconSet.datetime.today,flat:!0,size:"sm",round:!0,tabindex:c.value,onClick:Ae}):null])])}function xe({label:t,type:a,key:n,dir:l,goTo:u,boundaries:b,cls:v}){return[g("div",{class:"row items-center q-date__arrow"},[g(N,{round:!0,dense:!0,size:"sm",flat:!0,icon:ce.value[0],tabindex:c.value,disable:b.prev===!1,...D("go-#"+a,{onClick(){u(-1)}})})]),g("div",{class:"relative-position overflow-hidden flex flex-center"+v},[g(oe,{name:"q-transition--jump-"+l},()=>g("div",{key:n},[g(N,{flat:!0,dense:!0,noCaps:!0,label:t,tabindex:c.value,...D("view#"+a,{onClick:()=>{k.value=a}})})]))]),g("div",{class:"row items-center q-date__arrow"},[g(N,{round:!0,dense:!0,size:"sm",flat:!0,icon:ce.value[1],tabindex:c.value,disable:b.next===!1,...D("go+#"+a,{onClick(){u(1)}})})])]}const _t={Calendar:()=>[g("div",{key:"calendar-view",class:"q-date__view q-date__calendar"},[g("div",{class:"q-date__navigation row items-center no-wrap"},xe({label:H.value.months[h.value.month-1],type:"Months",key:h.value.month,dir:le.value,goTo:Qe,boundaries:De.value.month,cls:" col"}).concat(xe({label:h.value.year,type:"Years",key:h.value.year,dir:he.value,goTo:we,boundaries:De.value.year,cls:""}))),g("div",{class:"q-date__calendar-weekdays row items-center no-wrap"},ct.value.map(t=>g("div",{class:"q-date__calendar-item"},[g("div",t)]))),g("div",{class:"q-date__calendar-days-container relative-position overflow-hidden"},[g(oe,{name:"q-transition--slide-"+le.value},()=>g("div",{key:j.value,class:"q-date__calendar-days fit"},Ee.value.map(t=>g("div",{class:t.classes},[t.in===!0?g(N,{class:t.today===!0?"q-date__today":"",dense:!0,flat:t.flat,unelevated:t.unelevated,color:t.color,textColor:t.textColor,label:t.i,tabindex:c.value,...D("day#"+t.i,{onClick:()=>{St(t.i)},onMouseover:()=>{xt(t.i)}})},t.event!==!1?()=>g("div",{class:"q-date__event bg-"+t.event}):null):g("div",""+t.i)]))))])])],Months(){const t=h.value.year===E.value.year,a=l=>C.value!==null&&h.value.year===C.value.year&&C.value.month>l||q.value!==null&&h.value.year===q.value.year&&q.value.month<l,n=H.value.monthsShort.map((l,u)=>{const b=h.value.month===u+1;return g("div",{class:"q-date__months-item flex flex-center"},[g(N,{class:t===!0&&E.value.month===u+1?"q-date__today":null,flat:b!==!0,label:l,unelevated:b,color:b===!0?P.value:null,textColor:b===!0?W.value:null,tabindex:c.value,disable:a(u+1),...D("month#"+u,{onClick:()=>{Yt(u+1)}})})])});return e.yearsInMonthView===!0&&n.unshift(g("div",{class:"row no-wrap full-width"},[xe({label:h.value.year,type:"Years",key:h.value.year,dir:he.value,goTo:we,boundaries:De.value.year,cls:" col"})])),g("div",{key:"months-view",class:"q-date__view q-date__months flex flex-center"},n)},Years(){const t=ue.value,a=t+J,n=[],l=u=>C.value!==null&&C.value.year>u||q.value!==null&&q.value.year<u;for(let u=t;u<=a;u++){const b=h.value.year===u;n.push(g("div",{class:"q-date__years-item flex flex-center"},[g(N,{key:"yr"+u,class:E.value.year===u?"q-date__today":null,flat:!b,label:u,dense:!0,unelevated:b,color:b===!0?P.value:null,textColor:b===!0?W.value:null,tabindex:c.value,disable:l(u),...D("yr#"+u,{onClick:()=>{Dt(u)}})})]))}return g("div",{class:"q-date__view q-date__years flex flex-center"},[g("div",{class:"col-auto"},[g(N,{round:!0,dense:!0,flat:!0,icon:ce.value[0],tabindex:c.value,disable:l(t),...D("y-",{onClick:()=>{ue.value-=J}})})]),g("div",{class:"q-date__years-content col self-stretch row items-center"},n),g("div",{class:"col-auto"},[g(N,{round:!0,dense:!0,flat:!0,icon:ce.value[1],tabindex:c.value,disable:l(a),...D("y+",{onClick:()=>{ue.value+=J}})})])])}};function St(t){const a={...h.value,day:t};if(e.range===!1){bt(a,j.value);return}if(O.value===null){const n=Ee.value.find(u=>u.fill!==!0&&u.i===t);if(e.noUnset!==!0&&n.range!==void 0){Se({target:a,from:n.range.from,to:n.range.to});return}if(n.selected===!0){Se(a);return}const l=Q(a);O.value={init:a,initHash:l,final:a,finalHash:l},s("rangeStart",X(a))}else{const n=O.value.initHash,l=Q(a),u=n<=l?{from:O.value.init,to:a}:{from:a,to:O.value.init};O.value=null,_e(n===l?a:{target:a,...u}),s("rangeEnd",{from:X(u.from),to:X(u.to)})}}function xt(t){if(O.value!==null){const a={...h.value,day:t};Object.assign(O.value,{final:a,finalHash:Q(a)})}}return Object.assign(i,{setToday:Ae,setView:gt,offsetCalendar:yt,setCalendarTo:Ye,setEditingRange:Mt}),()=>{const t=[g("div",{class:"q-date__content col relative-position"},[g(oe,{name:"q-transition--fade"},_t[k.value])])],a=$t(r.default);return a!==void 0&&t.push(g("div",{class:"q-date__actions"},a)),e.name!==void 0&&e.disable!==!0&&T(t,"push"),g("div",{class:ut.value,...ht.value},[wt(),g("div",{ref:V,class:"q-date__main col column",tabindex:-1},t)])}}});export{ra as Q};
