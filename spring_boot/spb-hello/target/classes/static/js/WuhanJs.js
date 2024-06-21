window.onload = function() {
    let retries = 3;
    $.ajax({
        type: "GET",
        url: "/json/wuhans.json",
        error: function () {
            console.log("error1");
        },
        success: function reqs (data) {
            console.log(data);
            $.ajax({
                type: "GET",
                url: "/wuhanData",
                error: function () {
                    retries--;
                    if (retries > 0) {
                        reqs();
                    } else {
                        alert("Failed to retrieve data after 3 attempts.");
                    }
                },
                success: function (wuh) {
                    console.log("good2");
                    echarts.registerMap('武汉', data);
                    var chart = echarts.init(document.getElementById('main'));
                    var intermdf = wuh.reduce(function (acc, cur) {
                        var found = acc.find(function (element) {
                            return element.name === cur.name;
                        });
                        if (found) {
                            found.value += cur.value;
                            found.count += 1;
                        } else {
                            acc.push({
                                name: cur.name,
                                value: cur.value,
                                count: 1
                            });
                        }
                        return acc;
                    }, []);
                    var res = intermdf.map(function (element) {
                        return {
                            name: element.name,
                            value: Math.round(element.value / element.count)
                        };
                    });
                    var option = {
                        title: {
                            text: '武汉销售额地图',
                            x: 'center'
                        },
                        tooltip: {
                            trigger: 'wuh',
                            formatter: '{b}<br/>{c}'
                        },
                        dataRange: {
                            min: 2500,
                            max: 3000,
                            text: ['高', '低'],
                            realtime: true,
                            calculable: true,
                            color: ['orangered', 'yellow', 'lightskyblue', 'lime']
                        },
                        series: [
                            {
                                name: '销售额',
                                type: 'map',
                                map: '武汉',
                                itemStyle: {
                                    label: {show: true}
                                },
                                emphasis: {
                                    itemStyle: {label: {show: true}}
                                },
                                data: res
                            }
                        ]
                    };
                    chart.setOption(option);
                }
            })

        }
    })
};