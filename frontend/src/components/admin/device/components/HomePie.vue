<template>
  <highcharts :options="chartOptions" :callback="myCallback"></highcharts>
</template>
<script>
export default {
  name: 'HomePie',
  data () {
    return {
      chartOptions: {
        chart: {
          type: 'pie',
          backgroundColor: 'none',
          height: 380,
          width: 500,
          options3d: {
            enabled: true,
            alpha: 45,
            beta: 0
          }
        },
        title: {
          text: ''
        },
        tooltip: {
          pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
          pie: {
            allowPointSelect: true,
            cursor: 'pointer',
            depth: 35,
            dataLabels: {
              style: {
                fontSize: '18px',
                color: '#fff',
                fontFamily: '微软雅黑'
              },
              enabled: true,
              // format: "{point.name}"
              formatter: function () {
                return (
                  // '<span ' +
                  // // this.point.color +
                  // '"> ' +
                  this.point.name +
                  // this.y +
                  '，' +
                  this.percentage +
                  '%'

                )
              }
            }
          }
        },
        credits: {
          enabled: false
        },
        series: [
          {
            type: 'pie',
            name: '能耗占比',
            data: [
              ['空调', 45.0],
              ['实验设备', 26.8],
              {
                name: '照明',
                y: 21.3,
                sliced: true,
                selected: true
              },
              ['其他', 6.9]
            ]
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
  width: 100%;
  height: 100%;
  border: 0px solid #ddd;
  margin-bottom: 2%
}
</style>
