import{_ as u,I as _,L as m,i as g,f as s,b as r,w as h,r as l,o,F as w,g as y,h as v,d as $,v as x}from"./index-CRoSIu1a.js";const C={components:{InfiniteLoading:_,LazyComponent:m},data(){return{list:[{id:0,show:!0},{id:1,show:!0}],page:2}},methods:{loadMore(t){setTimeout(()=>{for(let e=0;e<1;e++,this.page++)this.list.push({id:this.page,show:!1});t.loaded(),this.page>100&&t.complete()},1500)},initChart(t){this.$nextTick(()=>{if(console.log(`Initializing chart ${t}`),!this.$refs[`chart${t}`])return;const e=g(this.$refs[`chart${t}`]);console.log("kknd"),e.setOption({xAxis:{type:"category",data:["Mon","Tue","Wed","Thu","Fri","Sat","Sun"]},yAxis:{type:"value"},series:[{data:[150,230,224,218,135,147,260],type:"line"}]}),this.list[t].show=!0})}},watch:{list:{handler(t){t.forEach((e,i)=>{e.show&&this.$refs[`chart${i}`]&&this.initChart(i)})},deep:!0}},mounted(){this.list.forEach((t,e)=>{this.initChart(e)})}};function k(t,e,i,I,c,a){const d=l("lazy-component"),f=l("infinite-loading");return o(),s("div",null,[r(f,{onInfinite:a.loadMore},{default:h(()=>[(o(!0),s(w,null,y(c.list,(p,n)=>(o(),s("div",{key:n,style:{height:"100vh"}},[r(d,{onAppear:z=>a.initChart(n)},{default:h(()=>[v($("div",{ref_for:!0,ref:`chart${n}`,style:{width:"100%",height:"100%"}},null,512),[[x,p.show]])]),_:2},1032,["onAppear"])]))),128))]),_:1},8,["onInfinite"])])}const E=u(C,[["render",k]]);export{E as default};
