import{_ as v,I as A,L as D,i as p,a as h,r as g,o as L,c as S,w as s,b as r,d as c,p as C,e as W}from"./index-CRoSIu1a.js";const k={components:{InfiniteLoading:A,LazyComponent:D},data(){return{list:[],page:1}},methods:{loadMore(n){setTimeout(()=>{for(let u=0;u<10;u++,this.page++)this.list.push({id:this.page});n.loaded(),this.page>100&&n.complete()},1500)}},mounted(){const n=p(this.$refs.chart);h.get("http://localhost:8080/flight/typedelay").then(i=>{const l=i.data;console.log(l);let t;if(l.length>0){const o=l.map(e=>({name:e.delayType,value:e.maxDelay}));t={title:{text:"航班延误类型及最大延误时间"},tooltip:{trigger:"axis",axisPointer:{type:"shadow"}},legend:{},grid:{left:"3%",right:"4%",bottom:"3%",containLabel:!0},xAxis:{type:"value",boundaryGap:[0,.01],name:"延误时间/分钟",axisLabel:{fontWeight:"bold"},nameLocation:"middle",nameGap:19,nameTextStyle:{fontSize:14,fontWeight:"bold"}},yAxis:{type:"category",data:o.map(e=>e.name),axisLabel:{fontWeight:"bold"}},series:[{type:"bar",data:o}]}}else t={title:{text:"数据请求失败"},xAxis:{type:"value",boundaryGap:[0,.01]},yAxis:{type:"category",data:[]},series:[{type:"bar",data:[]}]};n.setOption(t)}).catch(i=>{console.log("error1")});const u=p(this.$refs.charge_time);h.get("http://localhost:8080/flight/airlongest").then(i=>{const l=i.data.reverse();console.log(l);let t;if(l.length>0){const o=l.map(e=>({name:"航班号"+e.flightNub,value:e.airTime}));t={title:{text:"飞行时间最长的10个航班"},tooltip:{trigger:"axis",axisPointer:{type:"shadow"}},xAxis:{type:"value",boundaryGap:[0,.01],name:"飞行时间/分钟",axisLabel:{fontWeight:"bold"},nameLocation:"middle",nameGap:40,nameTextStyle:{fontSize:14,fontWeight:"bold"}},yAxis:{type:"category",data:o.map(e=>e.name)},series:[{type:"bar",data:o}]}}else t={title:{text:"数据请求失败！！！"},xAxis:{type:"value",boundaryGap:[0,.01]},yAxis:{type:"category",data:[]},series:[{type:"bar",data:[]}]};u.setOption(t)}).catch(i=>{console.log("error2")}),h.get("http://localhost:8080/flight/originmaxdelay").then(i=>{const l=p(this.$refs.charge_user),t=i.data.reverse();console.log(t);let o;if(t.length>0){const e=t.map(a=>({name:a.origin,value:a.maxDelay}));o={title:{text:"10个起始机场最大起飞延误时间"},tooltip:{trigger:"axis",axisPointer:{type:"shadow"}},xAxis:{type:"value",boundaryGap:[0,.01],name:"延误时间/分钟",axisLabel:{fontWeight:"bold"},nameLocation:"middle",nameGap:40,nameTextStyle:{fontSize:14,fontWeight:"bold"}},yAxis:{type:"category",data:e.map(a=>a.name)},series:[{type:"bar",data:e}]}}else o={title:{text:"数据请求失败！！！"},tooltip:{trigger:"axis",axisPointer:{type:"shadow"}},legend:{},grid:{left:"3%",right:"4%",bottom:"3%",containLabel:!0},xAxis:{type:"value",boundaryGap:[0,.01]},yAxis:{type:"category",data:test_data.map(e=>e.name)},series:[{type:"bar",data:[]}]};l.setOption(o)}).catch(i=>{console.log("error4")}),h.get("http://localhost:8080/flight/destmaxdelay").then(i=>{const l=p(this.$refs.best_user),t=i.data.reverse().map(e=>({name:e.dest,value:e.maxArrDelay}));let o;t.length>0?o={title:{text:"10个到达机场最大起飞延误时间"},tooltip:{trigger:"axis",axisPointer:{type:"shadow"}},xAxis:{type:"value",boundaryGap:[0,.01],name:"延误时间/分钟",axisLabel:{fontWeight:"bold"},nameLocation:"middle",nameGap:40,nameTextStyle:{fontSize:14,fontWeight:"bold"}},yAxis:{type:"category",data:t.map(e=>e.name)},series:[{type:"bar",data:t}]}:o={title:{text:"数据请求失败！！！"},tooltip:{trigger:"axis",axisPointer:{type:"shadow"}},legend:{},grid:{left:"3%",right:"4%",bottom:"3%",containLabel:!0},xAxis:{type:"value",boundaryGap:[0,.01]},yAxis:{type:"category",data:test_data.map(e=>e.name)},series:[{name:"消费金额",type:"bar",data:[]}]},l.setOption(o)}).catch(i=>{console.log("error5")});const f=p(this.$refs.map_wuhan);h.get("http://localhost:8080/flight/monthrate").then(i=>{const l=i.data;console.log(l);let t;if(l.length>0){const o=l.map(e=>({name:e.month,value:e.delay_ratio}));t={tooltip:{trigger:"item",formatter:"{a} <br/>{b}月 : {c}%"},legend:{top:"5%",left:"center"},series:[{name:"每月飞机延误率",type:"pie",radius:["10%","50%"],avoidLabelOverlap:!1,itemStyle:{borderRadius:10,borderColor:"#fff",borderWidth:2},label:{show:!0,position:"outside",formatter:"{b}月: {c}%"},emphasis:{label:{show:!0,fontSize:40,fontWeight:"bold"}},labelLine:{show:!1},data:o}]}}else t={title:{text:"数据请求失败！！！",x:"center"},tooltip:{trigger:"wuh",formatter:"{b}<br/>{c}"},dataRange:{min:0,max:1800,text:["高","低"],realtime:!0,calculable:!0,color:["orangered","yellow","lightskyblue","lime"]},series:[{name:"数据请求失败",type:"map",map:"武汉",itemStyle:{label:{show:!0}},emphasis:{itemStyle:{label:{show:!0}}},data:[]}]};f.setOption(t)}).catch(i=>{console.log("error3")}),p(this.$refs.delayPerMonth),h.get("http://localhost:8080/flight/maindelayreasonrate").then(i=>{const l=["#5470C6","#91CC75","#FAC858","#EE6666","#73C0DE"],t=i.data;console.log(t);let o;var e=p(this.$refs.delayPerMonth);o={legend:{},tooltip:{trigger:"axis",showContent:!1},dataset:{source:[["month",...t.map(a=>a.month)],["Carrier Delay",...t.map(a=>a.numCarrierDelays)],["Late Aircraft Delay",...t.map(a=>a.numLateAircraftDelays)],["Security Delay",...t.map(a=>a.numSecurityDelays)],["Weather Delay",...t.map(a=>a.numWeatherDelays)],["NAS Delay",...t.map(a=>a.numNASDelays)]]},xAxis:{type:"category"},yAxis:{gridIndex:0},grid:{top:"55%"},series:[{type:"line",smooth:!0,seriesLayoutBy:"row",emphasis:{focus:"series"},color:l[0]},{type:"line",smooth:!0,seriesLayoutBy:"row",emphasis:{focus:"series"},color:l[1]},{type:"line",smooth:!0,seriesLayoutBy:"row",emphasis:{focus:"series"},color:l[2]},{type:"line",smooth:!0,seriesLayoutBy:"row",emphasis:{focus:"series"},color:l[3]},{type:"line",smooth:!0,seriesLayoutBy:"row",emphasis:{focus:"series"},color:l[4]},{type:"pie",id:"pie",radius:"30%",center:["50%","25%"],emphasis:{focus:"self"},itemStyle:{color:function(a){return l[a.dataIndex]}}}]},e.on("updateAxisPointer",function(a){const y=a.axesInfo[0];if(y){const d=y.value,x=o.dataset.source,b=x.map(m=>m[d]);e.setOption({series:{id:"pie",label:{formatter:"{b}: {@["+d+"]} ({d}%)"},encode:{itemName:"month",value:d,tooltip:d},data:b.slice(1).map((m,w)=>({value:m,name:x[0][w+1]})),itemStyle:{color:function(m){return l[m.dataIndex]}}}})}}),e.setOption(o)}).catch(i=>{console.log("error6",i)})}},_=n=>(C("data-v-b8d890b8"),n=n(),W(),n),G={ref:"chart",style:{width:"100%",height:"450px"}},I={ref:"charge_time",style:{width:"100%",height:"450px"}},P=_(()=>c("h1",null,"每月飞机延误率",-1)),$={ref:"map_wuhan",style:{width:"100%",height:"850px"}},B={ref:"charge_user",style:{width:"100%",height:"450px"}},M={ref:"best_user",style:{width:"100%",height:"450px"}},O=_(()=>c("h1",null,"每月航班各情况延误数量及比例（按延误时间最长划分）",-1)),T={ref:"delayPerMonth",style:{width:"100%",height:"800px"}};function z(n,u,f,i,l,t){const o=g("v-card-text"),e=g("v-card"),a=g("v-col"),y=g("v-card-title"),d=g("v-row");return L(),S(a,{style:{width:"100%",height:"1000px"}},{default:s(()=>[r(d,{class:"background"},{default:s(()=>[r(d,null,{default:s(()=>[r(a,{cols:"4"},{default:s(()=>[r(e,{style:{width:"100%","max-height":"480px","overflow-y":"auto"},class:"transparent-background"},{default:s(()=>[r(o,null,{default:s(()=>[c("div",G,null,512)]),_:1})]),_:1}),r(e,{style:{width:"100%","max-height":"480px","overflow-y":"auto"},class:"transparent-background"},{default:s(()=>[r(o,null,{default:s(()=>[c("div",I,null,512)]),_:1})]),_:1})]),_:1}),r(a,{cols:"4"},{default:s(()=>[r(d,null,{default:s(()=>[r(a,{cols:"12"},{default:s(()=>[r(e,{class:"transparent-background",rounded:"lg",width:"100%",height:"74px"},{default:s(()=>[r(y,{class:"text-center"},{default:s(()=>[P]),_:1})]),_:1})]),_:1})]),_:1}),r(e,{style:{"max-height":"960px","overflow-y":"auto"},class:"transparent-background"},{default:s(()=>[r(o,null,{default:s(()=>[c("div",$,null,512)]),_:1})]),_:1})]),_:1}),r(a,{cols:"4"},{default:s(()=>[r(e,{style:{width:"100%","max-height":"480px","overflow-y":"auto"},class:"transparent-background"},{default:s(()=>[r(o,null,{default:s(()=>[c("div",B,null,512)]),_:1})]),_:1}),r(e,{style:{width:"100%","max-height":"480px","overflow-y":"auto"},class:"transparent-background"},{default:s(()=>[r(o,null,{default:s(()=>[c("div",M,null,512)]),_:1})]),_:1})]),_:1})]),_:1})]),_:1}),r(d,null,{default:s(()=>[r(a,{cols:"12",style:{width:"100%",height:"1000px"},class:"backgroundnext"},{default:s(()=>[r(e,{style:{width:"100%","max-height":"900px","overflow-y":"auto"},class:"transparent-background"},{default:s(()=>[r(y,{class:"text-center"},{default:s(()=>[O]),_:1}),r(o,null,{default:s(()=>[c("div",T,null,512)]),_:1})]),_:1})]),_:1})]),_:1})]),_:1})}const E=v(k,[["render",z],["__scopeId","data-v-b8d890b8"]]);export{E as default};