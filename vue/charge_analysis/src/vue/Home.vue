
<script>
import InfiniteLoading from 'vue-infinite-scroll';
import LazyComponent from 'vue-lazy-component';
import * as echarts from 'echarts';
import axios from 'axios';
import mapData from '/src/json/wuhans.json'

export default {
    components:{
        InfiniteLoading,
        LazyComponent
    },
    data(){
        return{
            list: [],
            page: 1,
        }
    },
    methods:{
        loadMore($state) {
            // 使用setTimeout模拟异步加载数据
            setTimeout(() => {
                for (let i = 0; i < 10; i++, this.page++) {
                    this.list.push({id: this.page});
                }
                $state.loaded();

                // 如果所有数据已经加载完，调用$state.complete()
                if (this.page > 100) {
                    $state.complete();
                }
            }, 1500);
        }
    },
    mounted() {
        const chart = echarts.init(this.$refs.chart);
        axios.get('http://localhost:8080/flight/typedelay')
            .then(response => {
                const typeDelayData = response.data;
                console.log(typeDelayData);

                let option;
                if (typeDelayData.length > 0) {
                    const newData = typeDelayData.map(item => ({
                        name: item.delayType,
                        value: item.maxDelay
                    }));
                    // 如果 avgData 有数据，设置图表选项为 avgData 的数据
                    option = {
                        title: {
                            text: '航班延误类型及最大延误时间',
                        },
                        tooltip: {
                            trigger: 'axis',
                            axisPointer: {
                                type: 'shadow'
                            }
                        },
                        legend: {},
                        grid: {
                            left: '3%',
                            right: '4%',
                            bottom: '3%',
                            containLabel: true
                        },
                        xAxis: {
                            type: 'value',
                            boundaryGap: [0, 0.01],
                            name: '延误时间/分钟',
                            axisLabel: {
                                fontWeight: 'bold'
                            },
                            nameLocation: 'middle',
                            nameGap: 19,
                            nameTextStyle: {
                                fontSize: 14,
                                fontWeight: 'bold'
                            }
                        },
                        yAxis: {
                            type: 'category',
                            data: newData.map(item => item.name),
                            axisLabel: {
                                fontWeight: 'bold'
                            }
                        },
                        series: [
                            {
                                type: 'bar',
                                data: newData
                            }
                        ]
                    };
                } else {
                    // 如果 avgData 没有数据，设置图表选项为备用内容
                    option = {
                        title: {
                            text: '数据请求失败',
                        },
                        xAxis: {
                            type: 'value',
                            boundaryGap: [0, 0.01]
                        },
                        yAxis: {
                            type: 'category',
                            data: []
                        },
                        series: [
                            {
                                type: 'bar',
                                data: []
                            }
                        ]
                    };
                }
                chart.setOption(option);
            })
            .catch(error => {
                console.log("error1");
            });

        const charge_t = echarts.init(this.$refs.charge_time);
        axios.get('http://localhost:8080/flight/airlongest')
            .then(reponseCount => {
                const countData = reponseCount.data.reverse();
                console.log(countData);
                let optionCount;
                if(countData.length > 0) {
                    const newData = countData.map(item => ({
                        name: "航班号" + item.flightNub,
                        value: item.airTime
                    }));
                    optionCount = {
                        title: {
                            text: '飞行时间最长的10个航班',
                        },
                        tooltip: {
                            trigger: 'axis',
                            axisPointer: {
                                type: 'shadow'
                            }
                        },
                        xAxis: {
                            type: 'value',
                            boundaryGap: [0, 0.01],
                            name: '飞行时间/分钟',
                            axisLabel: {
                                fontWeight: 'bold'
                            },
                            nameLocation: 'middle',
                            nameGap: 40,
                            nameTextStyle: {
                                fontSize: 14,
                                fontWeight: 'bold'
                            }
                        },
                        yAxis: {
                            type: 'category',
                            data: newData.map(item => item.name)
                        },
                        series: [
                            {
                                type: 'bar',
                                data: newData
                            }
                        ]
                    };
                } else {
                    optionCount = {
                        title: {
                            text: '数据请求失败！！！',
                        },
                        xAxis: {
                            type: 'value',
                            boundaryGap: [0, 0.01]
                        },
                        yAxis: {
                            type: 'category',
                            data: []
                        },
                        series: [
                            {
                                type: 'bar',
                                data: []
                            }
                        ]
                    };
                }
                charge_t.setOption(optionCount);
            })
            .catch(error => {
                console.log("error2");
            });



        axios.get("http://localhost:8080/flight/originmaxdelay")
            .then(responseUC => {
                const charge_u = echarts.init(this.$refs.charge_user);
                const data = responseUC.data.reverse();
                console.log(data);

                let optionUC;
                if(data.length > 0)
                {
                    const newData = data.map(item => ({
                        name: item.origin,
                        value: item.maxDelay
                    }));
                    optionUC = {
                        title: {
                            text: '10个起始机场最大起飞延误时间',
                        },
                        tooltip: {
                            trigger: 'axis',
                            axisPointer: {
                                type: 'shadow'
                            }
                        },
                        xAxis: {
                            type: 'value',
                            boundaryGap: [0, 0.01],
                            name: '延误时间/分钟',
                            axisLabel: {
                                fontWeight: 'bold'
                            },
                            nameLocation: 'middle',
                            nameGap: 40,
                            nameTextStyle: {
                                fontSize: 14,
                                fontWeight: 'bold'
                            }
                        },
                        yAxis: {
                            type: 'category',
                            data: newData.map(item => item.name)
                        },
                        series: [
                            {
                                type: 'bar',
                                data: newData
                            }
                        ]
                    }
                }else {
                    optionUC = {
                        title: {
                            text: '数据请求失败！！！'
                        },
                        tooltip: {
                            trigger: 'axis',
                            axisPointer: {
                                type: 'shadow'
                            }
                        },
                        legend: {},
                        grid: {
                            left: '3%',
                            right: '4%',
                            bottom: '3%',
                            containLabel: true
                        },
                        xAxis: {
                            type: 'value',
                            boundaryGap: [0, 0.01]
                        },
                        yAxis: {
                            type: 'category',
                            data: test_data.map(item => item.name)
                        },
                        series: [
                            {
                                type: 'bar',
                                data: []
                            }
                        ]
                    }
                }
                charge_u.setOption(optionUC);
            })
            .catch(error => {
                console.log("error4");
            });

        axios.get("http://localhost:8080/flight/destmaxdelay")
            .then(responseUM => {
                const best_u = echarts.init(this.$refs.best_user);
                const data = responseUM.data.reverse().map(item => ({
                    name: item.dest,
                    value: item.maxArrDelay
                }));

                let optionUM;
                if(data.length > 0)
                {
                    optionUM = {
                        title: {
                            text: '10个到达机场最大降落延误时间',
                        },
                        tooltip: {
                            trigger: 'axis',
                            axisPointer: {
                                type: 'shadow'
                            }
                        },
                        xAxis: {
                            type: 'value',
                            boundaryGap: [0, 0.01],
                            name: '延误时间/分钟',
                            axisLabel: {
                                fontWeight: 'bold'
                            },
                            nameLocation: 'middle',
                            nameGap: 40,
                            nameTextStyle: {
                                fontSize: 14,
                                fontWeight: 'bold'
                            }
                        },
                        yAxis: {
                            type: 'category',
                            data: data.map(item => item.name)
                        },
                        series: [
                            {
                                type: 'bar',
                                data: data
                            }
                        ]
                    }
                }else {
                    optionUM = {
                        title: {
                            text: '数据请求失败！！！'
                        },
                        tooltip: {
                            trigger: 'axis',
                            axisPointer: {
                                type: 'shadow'
                            }
                        },
                        legend: {},
                        grid: {
                            left: '3%',
                            right: '4%',
                            bottom: '3%',
                            containLabel: true
                        },
                        xAxis: {
                            type: 'value',
                            boundaryGap: [0, 0.01]
                        },
                        yAxis: {
                            type: 'category',
                            data: test_data.map(item => item.name)
                        },
                        series: [
                            {
                                name: '消费金额',
                                type: 'bar',
                                data: []
                            }
                        ]
                    }
                }
                best_u.setOption(optionUM);
            })
            .catch(error => {
                console.log("error5");
            });


        //echarts.registerMap('武汉', mapData);
        const map_wuhan_t = echarts.init(this.$refs.map_wuhan);
        axios.get('http://localhost:8080/flight/monthrate')
            .then(responseMap => {
                const mapData = responseMap.data;
                console.log(mapData);
                let optionMap;
                let optionChargeType;
                if(mapData.length > 0)
                {
                    const newData = mapData.map(item => ({
                        name: item.month,
                        value: item.delay_ratio
                    }));
                    optionMap = {
                        tooltip: {
                            trigger: 'item',
                            formatter: '{a} <br/>{b}月 : {c}%'
                        },
                        legend: {
                            top: '5%',
                            left: 'center'
                        },
                        series: [
                            {
                                name: '每月飞机延误率',
                                type: 'pie',
                                radius: ['10%', '50%'],
                                avoidLabelOverlap: false,
                                itemStyle: {
                                    borderRadius: 10,
                                    borderColor: '#fff',
                                    borderWidth: 2
                                },
                                label: {
                                    show: true,
                                    position: 'outside',
                                    formatter: '{b}月: {c}%'
                                },
                                emphasis: {
                                    label: {
                                        show: true,
                                        fontSize: 40,
                                        fontWeight: 'bold'
                                    }
                                },
                                labelLine: {
                                    show: false
                                },
                                data: newData
                            }
                        ]
                    }
                }else{

                    optionMap = {
                        title: {
                            text: '数据请求失败！！！',
                            x:'center'
                        },
                        tooltip: {
                            trigger: 'wuh',
                            formatter: '{b}<br/>{c}'
                        },
                        dataRange: {
                            min: 0,
                            max: 1800,
                            text: ['高', '低'],
                            realtime: true,
                            calculable: true,
                            color: ['orangered', 'yellow', 'lightskyblue', 'lime']
                        },
                        series: [
                            {
                                name: '数据请求失败',
                                type: 'map',
                                map: '武汉',
                                itemStyle: {
                                    label: {show: true}
                                },
                                emphasis: {
                                    itemStyle: {label: {show: true}}
                                },
                                data: []
                            }
                        ]
                    }
                }
                map_wuhan_t.setOption(optionMap);

            })
            .catch(error => {
                console.log("error3");
            });

        const delayPerMonth = echarts.init(this.$refs.delayPerMonth);
        axios.get('http://localhost:8080/flight/maindelayreasonrate')
            .then(response => {
                const colors = ['#5470C6', '#91CC75', '#FAC858', '#EE6666', '#73C0DE'];
                const delayPerMonthData = response.data;
                console.log(delayPerMonthData);
                let optionPerMonth;
                var myChart = echarts.init(this.$refs.delayPerMonth);

                optionPerMonth = {
                    legend: {},
                    tooltip: {
                        trigger: 'axis',
                        showContent: false
                    },
                    dataset: {
                        source: [
                            ['month', ...delayPerMonthData.map(item => item.month)],
                            ['Carrier Delay', ...delayPerMonthData.map(item => item.numCarrierDelays)],
                            ['Late Aircraft Delay', ...delayPerMonthData.map(item => item.numLateAircraftDelays)],
                            ['Security Delay', ...delayPerMonthData.map(item => item.numSecurityDelays)],
                            ['Weather Delay', ...delayPerMonthData.map(item => item.numWeatherDelays)],
                            ['NAS Delay', ...delayPerMonthData.map(item => item.numNASDelays)],
                        ]
                    },
                    xAxis: { type: 'category' },
                    yAxis: { gridIndex: 0 },
                    grid: { top: '55%' },
                    series: [
                        {
                            type: 'line',
                            smooth: true,
                            seriesLayoutBy: 'row',
                            emphasis: { focus: 'series' },
                            color: colors[0]
                        },
                        {
                            type: 'line',
                            smooth: true,
                            seriesLayoutBy: 'row',
                            emphasis: { focus: 'series' },
                            color: colors[1]
                        },
                        {
                            type: 'line',
                            smooth: true,
                            seriesLayoutBy: 'row',
                            emphasis: { focus: 'series' },
                            color: colors[2]
                        },
                        {
                            type: 'line',
                            smooth: true,
                            seriesLayoutBy: 'row',
                            emphasis: { focus: 'series' },
                            color: colors[3]
                        },
                        {
                            type: 'line',
                            smooth: true,
                            seriesLayoutBy: 'row',
                            emphasis: { focus: 'series' },
                            color: colors[4]
                        },
                        {
                            type: 'pie',
                            id: 'pie',
                            radius: '30%',
                            center: ['50%', '25%'],
                            emphasis: {
                                focus: 'self'
                            },
                            itemStyle: {
                                color: function (params) {
                                    return colors[params.dataIndex];
                                }
                            }
                        }
                    ]
                };

                myChart.on('updateAxisPointer', function (event) {
                    const xAxisInfo = event.axesInfo[0];
                    if (xAxisInfo) {
                        const month = xAxisInfo.value + 1;  // 获取当前的月份
                        const source = optionPerMonth.dataset.source;
                        const pieData = source.map(row => row[month]);

                        myChart.setOption({
                            series: {
                                id: 'pie',
                                label: {
                                    formatter: '{b}: {@[' + month + ']} ({d}%)'
                                },
                                encode: {
                                    itemName: 'month',
                                    value: month,  // 使用当前的月份来更新饼图的数据
                                    tooltip: month
                                },
                                data: pieData.slice(1).map((value, index) => ({
                                    value,
                                    name: source[0][index + 1]
                                })),
                                itemStyle: {
                                    color: function (params) {
                                        return colors[params.dataIndex];
                                    }
                                }
                            }
                        });
                    }
                });

                myChart.setOption(optionPerMonth);

            })
            .catch(error => {
                console.log("error6", error);
            });

    }
};
</script>


<template>
    <v-col style="width: 100%; height: 1000px">
        <v-row class="background">
            <v-row>
                <v-col cols="4">
                    <v-card style="width: 100%; max-height: 480px; overflow-y: auto;" class="transparent-background" >
                        <v-card-text>
                            <div ref="chart" style="width: 100%; height: 450px;"></div>
                        </v-card-text>
                    </v-card>
                    <v-card style="width: 100%; max-height: 480px; overflow-y: auto;" class="transparent-background">
                        <v-card-text>
                            <div ref="charge_time" style="width: 100%; height: 450px;"></div>
                        </v-card-text>
                    </v-card>
                </v-col>
                <v-col cols="4">
                    <v-row>
                        <v-col cols="12">
                            <v-card class="transparent-background" rounded="lg" width="100%" height="74px">
                                <!--
                                <v-btn rounded="lg" width="100%" variant="tonal" height="100%" @click="() => {this.$router.push('/finduser')}">
                                    前往搜索🔍
                                </v-btn>
                                -->
                                <v-card-title class="text-center">
                                    <h1>每月飞机延误率</h1>
                                </v-card-title>
                            </v-card>
                        </v-col>
                    </v-row>
                    <v-card style="max-height: 960px; overflow-y: auto" class="transparent-background">
                        <v-card-text>
                            <div ref="map_wuhan" style="width: 100%; height: 850px;"></div>
                        </v-card-text>
                    </v-card>
                    <!--
                    -->
                </v-col>
                <v-col cols="4">
                    <v-card style="width: 100%; max-height: 480px; overflow-y: auto;" class="transparent-background">
                        <v-card-text>
                            <div ref="charge_user" style="width: 100%; height: 450px;"></div>
                        </v-card-text>
                    </v-card>
                    <v-card style="width: 100%; max-height: 480px; overflow-y: auto;" class="transparent-background">
                        <v-card-text>
                            <div ref="best_user" style="width: 100%; height: 450px;"></div>
                        </v-card-text>
                    </v-card>
                </v-col>
            </v-row>
            <!--
            <v-row>
                <v-col cols="12">
                    <v-card style="width: 100%; max-height: 700px; overflow-y: auto;">
                        <v-card-title class="text-center">
                            <h1>武汉各区充电桩类型数量</h1>
                        </v-card-title>
                        <v-card-text>
                            <div ref="wuhanChargeType" style="width: 100%; height: 450px;"></div>
                        </v-card-text>
                    </v-card>
                </v-col>
            </v-row>
            -->
        </v-row>
        <v-row>
            <v-col cols="12" style="width: 100%; height: 1000px;" class="backgroundnext">
                <v-card style="width: 100%; max-height: 900px; overflow-y: auto;" class="transparent-background">
                    <v-card-title class="text-center">
                        <h1>每月航班各情况延误数量及比例（按延误时间最长划分）</h1>
                    </v-card-title>
                    <v-card-text>
                        <div ref="delayPerMonth" style="width: 100%; height: 800px;"></div>
                    </v-card-text>
                </v-card>
            </v-col>
        </v-row>
    </v-col>


</template>

<style scoped>
.background{
    background-image: linear-gradient(rgba(255, 255, 255, 0.1), rgba(255, 255, 255, 0.1)), url("@/picture/Designer.png");
    background-size: cover;
    background-repeat: no-repeat;
    background-position: center;
    height: 100%;
    width: 100%;
}
.backgroundnext{
    background-color: #fafafa;
    background-size: cover;
    background-repeat: no-repeat;
    background-position: center;
    height: 100%;
    width: 100%;
}
.backgroundnextn{
    background-image: linear-gradient(rgba(255, 255, 255, 0.1), rgba(255, 255, 255, 0.1)), url("@/picture/Designer3.png");
    background-size: cover;
    background-repeat: no-repeat;
    background-position: center;
    height: 100%;
    width: 100%;
}
.transparent-background {
    background-color: transparent;
}
</style>
