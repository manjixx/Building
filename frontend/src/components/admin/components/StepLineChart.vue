<template>
  <div :class="className" :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
import resize from './mixins/resize'
require('echarts/theme/macarons') // echarts theme
export default {
  name: 'StepLineChart',
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
          left: 5,
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
          type: 'value',
          axisTick: {
            show: false
          },
          splitArea: { show: false }, // 保留网格区域
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
            },
            // 这里重新定义就可以
            formatter: function (value) {
              var texts = []
              if (value === 0 || value === '0') {
                texts.push('关闭')
              } else if (value === 1 || value === '1') {
                texts.push('制冷')
              } else if (value === 2 || value === '2') {
                texts.push('制热')
              } else if (value === 3 || value === '3') {
                texts.push('通风')
              }
              return texts
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
          step: 'start',
          data: data,
          animationDuration: 2800,
          animationEasing: 'cubicInOut'
        }]
      })
    }
  }
}
</script>
