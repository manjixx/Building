<template>
  <table class="mailTable" :style="styleObject" v-if="s_showByRow">
    <tr v-for="(value,name,index) in tableData" :key="index">
      <td class="column" style="text-align:center">{{tableKey[name]}}</td>
      <td class="column">{{tableKey1[name]}}</td>
      <!-- <td class="column">{{tableKey1[name]}}</td>
      <td class="column">{{tableKey1[name]}}</td>
      <td class="column">{{tableKey1[name]}}</td> -->

      <td style="text-align:left">{{value}}{{tableKey1[name]}}{{tableKey3[name]}}</td>
    </tr>
  </table>
</template>
<script>
export default {
  name: 'TableVertical',
  data () {
    return {
      tableKey: ['温度', '湿度', 'CO2', 'PM2.5', '能耗', '人数', '舒适度'],
      tableKey1: ['  ', '  ', '  ', '  ', '  ', '  ', '  '],
      tableKey3: ['℃', '%', 'ppm', 'ug/m3', 'kw', '', ''],
      styleObject: {},
      s_showByRow: true
    }
  },
  props: ['tableData', 'tableStyle', 'showByRow'],
  computed: {
    rowCount: function () {
      // return this.tableDate.length;
      return Math.ceil(this.tableKey.length / 1)
    }
  },
  created () {

  }
}
</script>
<style scoped>
.column{
  width: 100px;
}
</style>
