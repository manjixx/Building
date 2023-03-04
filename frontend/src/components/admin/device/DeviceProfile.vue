<template>
  <div class="dashboard-editor-container">
    <el-row style="margin: 18px 0px 0px 18px ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin/questionnaire/feedback' }">管理中心</el-breadcrumb-item>
        <el-breadcrumb-item>设备管理</el-breadcrumb-item>
        <el-breadcrumb-item>设备监控</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
    <panel-group @handleSetLineChartData="handleSetLineChartData" />

    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <line-chart :chart-data="modelSetData" />
    </el-row>

    <el-row :gutter="32">
      <!--空调模式统计-->
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <step-line-chart :chart-data="modelSetData" />
        </div>
      </el-col>
      <!--空气流速统计-->
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <line-chart :chart-data="airVelocityData" />
        </div>
      </el-col>
      <!--温度设置统计-->
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <line-chart :chart-data="tempData" />
        </div>
      </el-col>
    </el-row>

    <el-row :gutter="32">
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <raddar-chart />
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <pie-chart />
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <bar-chart />
        </div>
      </el-col>
    </el-row>

    <el-row :gutter="8">
      <el-col :xs="{span: 24}" :sm="{span: 24}" :md="{span: 24}" :lg="{span: 12}" :xl="{span: 12}" style="padding-right:8px;margin-bottom:30px;">
        <transaction-table />
      </el-col>
      <el-col :xs="{span: 24}" :sm="{span: 12}" :md="{span: 12}" :lg="{span: 6}" :xl="{span: 6}" style="margin-bottom:30px;">
        <todo-list />
      </el-col>
      <el-col :xs="{span: 24}" :sm="{span: 12}" :md="{span: 12}" :lg="{span: 6}" :xl="{span: 6}" style="margin-bottom:30px;">
        <box-card />
      </el-col>
    </el-row>
  </div></template>

<script>
import PanelGroup from '../components/PanelGroup'
import RadarChart from '../components/RadarChart'
import PieChart from '../components/PieChart'
import LineChart from '../components/LineChart'
import BarChart from '../components/BarChart'
import TransactionTable from '../components/TransactionTable'
import TodoList from '../components/TodoList'
import BoxCard from '../components/BoxCard'
import StepLineChart from '../components/StepLineChart'

const lineChartData = {
  modelSet: {
    title: '空调温度设置',
    name: '空调模式',
    data: [1, 1, 2, 2, 1, 1, 1, 1, 3, 3]
  },
  airVelocity: {
    title: '空调风速设置',
    name: '空调风速',
    data: [1, 1, 2, 2, 1, 1, 1, 1, 3, 3]
  },
  temperature: {
    title: '空调温度设置',
    name: '温度',
    data: [18, 19, 20, 25, 27, 16, 22, 28, 24, 17]
  },
  newVisitis: {
    expectedData: [100, 120, 161, 134, 105, 160, 165],
    actualData: [120, 82, 91, 154, 162, 140, 145]
  },
  messages: {
    expectedData: [200, 192, 120, 144, 160, 130, 140],
    actualData: [180, 160, 151, 106, 145, 150, 130]
  },
  purchases: {
    expectedData: [80, 100, 121, 104, 105, 90, 100],
    actualData: [120, 90, 100, 138, 142, 130, 130]
  },
  shoppings: {
    expectedData: [130, 140, 141, 142, 145, 150, 160],
    actualData: [120, 82, 91, 154, 162, 140, 130]
  }
}

export default {
  name: 'DeviceProfile',
  components: {
    StepLineChart,
    PanelGroup,
    RadarChart,
    LineChart,
    PieChart,
    BarChart,
    TransactionTable,
    TodoList,
    BoxCard
  },
  data () {
    return {
      modelSetData: lineChartData.modelSet,
      airVelocityData: lineChartData.airVelocity,
      tempData: lineChartData.temperature
    }
  },
  methods: {
    handleSetLineChartData (type) {
      this.lineChartData = lineChartData[type]
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard-editor-container {
  padding: 32px;
  background-color: #f6f6f6;
  position: relative;
  margin: -20px 0 0 -10px;
  .github-corner {
    position: absolute;
    top: 0px;
    border: 0;
    right: 0;
  }
  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
}
@media (max-width:1024px) {
  .chart-wrapper {
    padding: 8px;
  }
}
</style>
