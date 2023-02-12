<template>
  <highcharts :options="chartOptions" :callback="myCallback" style="margin-top:20px;"></highcharts>
</template>
<script>
export default {
  name: 'HomeMix',
  data () {
    var T = 1000
    var tem = [
      25.2,
      26.5,
      23.3,
      14.5,
      18.2,
      21.5,
      25.2,
      26.5,
      23.3,
      18.3,
      13.9,
      23.3,
      18.3,
      13.9,
      14.5,
      18.2,
      21.5,
      25.2,
      26.5,
      23.3,
      18.3,
      13.9,
      19.6
    ]
    var index0 = 11
    var hum = [
      50.0,
      66.2,
      75.2,
      63.5,
      52.2,
      36.5,
      75.0,
      62.0,
      52.0,
      45.3,
      36.5,
      58.6,
      50.0,
      66.2,
      75.2,
      63.5,
      52.2,
      36.5,
      75.0,
      62.0,
      52.0,
      45.3,
      36.5,
      58.6
    ]
    var index1 = 11
    var co2 = [
      449.9,
      371.5,
      406.4,
      429.2,
      144.0,
      176.0,
      135.6,
      148.5,
      216.4,
      194.1,
      95.6,
      54.4,
      49.9,
      71.5,
      106.4,
      129.2,
      144.0,
      176.0,
      135.6,
      148.5,
      216.4,
      194.1,
      95.6,
      54.4
    ]
    var index2 = 11
    return {
      chartOptions: {
        chart: {
          zoomType: 'xy',
          backgroundColor: 'none',
          height: 300,
          events: {
            load: function () {
              let series0 = this.series[0]
              let series1 = this.series[1]
              let series2 = this.series[2]
              // activeLastPointToolip(chart);
              setInterval(function () {
                index0++
                index1++
                index2++
                if (index0 > 24) {
                  window.location.reload() // 页面每日刷新一次
                }
                series0.addPoint([index0, tem[index0 - 1]], true, true)
                series1.addPoint([index1, hum[index1 - 1]], true, true)
                series2.addPoint([index2, co2[index2 - 1]], true, true)
                // activeLastPointToolip(chart);
              }, T)
            }
          }
        },
        title: {
          text: ''
        },
        xAxis: [
          {
            categories: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11],
            crosshair: true,
            labels: {
              style: {
                color: '#fff',
                fontSize: '13px'
              }
            }
          }
        ],
        yAxis: [
          {
            // Primary yAxis
            labels: {
              format: '{value}',
              style: {
                fontSize: '13px',
                // color: Highcharts.getOptions().colors[3]
                color: '#e89651'
              }
            },
            title: {
              text: '温度(°C)',
              style: {
                fontSize: '13px',
                // color: Highcharts.getOptions().colors[3]
                color: '#e89651'
              }
            },
            opposite: true
          },
          {
            // Primary yAxis
            labels: {
              format: '{value}',
              style: {
                fontSize: '13px',
                // color: Highcharts.getOptions().colors[0]
                color: '#90ce5e'
              }
            },
            title: {
              text: '湿度(%)',
              style: {
                fontSize: '13px',
                // color: Highcharts.getOptions().colors[0]
                color: '#90ce5e'
              }
            },
            opposite: true
          },
          {
            // Secondary yAxis
            // gridLineWidth: 0,
            title: {
              text: 'CO2浓度(ppm)',
              style: {
                fontSize: '13px',
                // color: Highcharts.getOptions().colors[2]
                color: '#95ceff'
              }
            },
            labels: {
              format: '{value} ',
              style: {
                fontSize: '13px',
                // color: Highcharts.getOptions().colors[2]
                color: '#95ceff'
              }
            }
          }
        ],
        colors: ['#e89651', '#90ce5e', '#95ceff'],
        // colors: ["red", "green", "blue"],

        credits: {
          enabled: false
        },
        tooltip: {
          shared: true
        },
        // legend: {
        //   layout: "horizontal",
        //   align: "bottom",
        //   x: 125,
        //   verticalAlign: "bottom",
        //   y: 24,
        //   floating: false,
        //   backgroundColor: "none",
        //   color: "#fff",
        //   itemStyle: {
        //     fontSize: "15px",
        //     color:"white",
        //   },
        // },
        legend: {
          layout: 'vertical',
          align: 'right',
          verticalAlign: 'middle',
          enabled: false
        },
        series: [
          {
            name: '温度',
            type: 'spline',
            yAxis: 0,
            style: {
              // color: "#90ce5e"
            },
            data: [
              27.0,
              26.9,
              29.5,
              24.5,
              18.2,
              21.5,
              25.2,
              26.5,
              23.3,
              28.3,
              23.9,
              19.6
            ],
            tooltip: {
              valueSuffix: ' °C'
            }
          },
          {
            name: '湿度',
            type: 'spline',
            yAxis: 1,
            style: {
              // color: "#95ceff"
            },
            data: [
              50.0,
              66.2,
              75.2,
              63.5,
              52.2,
              36.5,
              75.0,
              62.0,
              52.0,
              45.3,
              36.5,
              58.6
            ],
            tooltip: {
              valueSuffix: ' %'
            }
          },
          {
            name: 'CO2浓度',
            type: 'column',
            yAxis: 2,
            style: {
              // color: "#e89651"
            },
            data: [
              449.9,
              471.5,
              406.4,
              529.2,
              544.0,
              676.0,
              635.6,
              548.5,
              516.4,
              494.1,
              495.6,
              454.4
            ],
            tooltip: {
              valueSuffix: 'ppm'
            }
          }
        ]
      }
    }
  },
  mounted () {},
  methods: {
    myCallback () {
      // console.log('this is callback function')
    }
  }
}
</script>
<style scoped>
.highcharts-container {
  width: 2000px;
  height: 650px;
  border: 0px solid #ddd;
  margin: auto;
}
</style>
