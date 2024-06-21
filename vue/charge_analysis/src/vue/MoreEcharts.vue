<template>
    <div>
        <infinite-loading @infinite="loadMore">
            <div v-for="(item, i) in list" :key="i" style="height: 100vh;">
                <lazy-component @appear="initChart(i)">
                    <div :ref="`chart${i}`" v-show="item.show" style="width: 100%; height: 100%;"></div>
                </lazy-component>
            </div>
        </infinite-loading>
    </div>
</template>

<script>
import InfiniteLoading from 'vue-infinite-scroll';
import LazyComponent from 'vue-lazy-component';
import * as echarts from 'echarts';

export default {
    components: {
        InfiniteLoading,
        LazyComponent
    },
    data() {
        return {
            list: [{id: 0, show: true}, {id: 1, show: true}],  // 初始化时有两个元素
            page: 2  // 用于记录当前加载到哪一页
        };
    },
    methods: {
        loadMore($state) {
            // 使用setTimeout模拟异步加载数据
            setTimeout(() => {
                for (let i = 0; i < 1; i++, this.page++) {
                    this.list.push({id: this.page, show: false});
                }
                $state.loaded();

                // 如果所有数据已经加载完，调用$state.complete()
                if (this.page > 100) {
                    $state.complete();
                }
            }, 1500);
        },
        initChart(i) {
            this.$nextTick(() => {
                console.log(`Initializing chart ${i}`);
                if (!this.$refs[`chart${i}`]) {
                    return;
                }
                const chart = echarts.init(this.$refs[`chart${i}`]);
                console.log("kknd");
                // 设置图表选项
                chart.setOption({
                    xAxis: {
                        type: 'category',
                        data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [
                        {
                            data: [150, 230, 224, 218, 135, 147, 260],
                            type: 'line'
                        }
                    ]
                });
                this.list[i].show = true;  // 图表初始化完成后显示图表
            });
        }
    },
    watch: {
        'list': {
            handler(newList) {
                newList.forEach((item, i) => {
                    if (item.show && this.$refs[`chart${i}`]) {
                        this.initChart(i);
                    }
                });
            },
            deep: true
        }
    },
    mounted() {
        this.list.forEach((item, i) => {
            this.initChart(i);
        });
    }
}
</script>
