<template>
  <div :class="className" :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
import resize from './mixins/resize'
require('echarts/theme/macarons') // echarts theme

export default {
  name: 'LineChart',
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '100%'
    },
    height: {
      type: String,
      default: '350px'
    },
    autoResize: {
      type: Boolean,
      default: true
    },
    chartData: {
      type: Object,
      required: true
    }
  },
  data () {
    return {
      chart: null
    }
  },
  watch: {
    chartData: {
      deep: true,
      handler (val) {
        this.setOptions(val)
      }
    }
  },
  mounted () {
    this.$nextTick(() => {
      this.initChart()
    })
  },
  beforeDestroy () {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },
  methods: {
    initChart () {
      this.chart = echarts.init(this.$el, 'macarons')
      this.setOptions(this.chartData)
    },
    setOptions ({ title, name, data } = {}) {
      this.chart.setOption({
        title: {
          text: title,
          left: 'center',
          textStyle: { // 标题内容的样式
            color: '#000000', // 黑色
            fontStyle: 'normal', // 主标题文字字体风格，默认normal，有italic(斜体),oblique(斜体)
            fontWeight: 'normal', // 可选normal(正常)，bold(加粗)，bolder(加粗)，lighter(变细)，100|200|300|400|500...
            // fontFamily: 'san-serif', // 主题文字字体，默认微软雅黑
            fontSize: 18// 主题文字字体大小，默认为18px
          },
          textAlign: 'left', // 标题文本对齐方式
          textBaseline: 'top'// 默认垂直对齐方式
        },
        xAxis: {
          data: ['09:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00'],
          boundaryGap: false,
          axisTick: {
            show: false
          },
          axisLine: {
            show: true,
            lineStyle: {
              color: '#000000',
              width: 1,
              type: 'solid'
            }
          }
        },
        grid: {
          left: 10,
          right: 10,
          bottom: 20,
          top: 30,
          containLabel: true
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross'
          },
          padding: [5, 10]
        },
        yAxis: {
          axisTick: {
            show: false
          },
          minInterval: 1,
          axisLine: {// y轴线的颜色以及宽度
            show: false,
            lineStyle: {
              color: '#000000',
              width: 1,
              type: 'solid'
            }
          },
          axisLabel: {
            show: true,
            textStyle: {
              color: '#000000'
            }
          }
        },
        series: [{
          name: name,
          itemStyle: {
            normal: {
              color: '#409EFF',
              lineStyle: {
                color: '#409EFF',
                width: 2
              }
            }
          },
          smooth: false,
          type: 'line',
          data: data,
          animationDuration: 2800,
          animationEasing: 'cubicInOut'
        }]
      })
    }
  }
}
</script>
