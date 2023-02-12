<template>
  <body>
  <el-row class="bread">
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/admin/questionnaire/feedback' }">管理中心</el-breadcrumb-item>
      <el-breadcrumb-item>设备管理</el-breadcrumb-item>
      <el-breadcrumb-item>设备监控</el-breadcrumb-item>
    </el-breadcrumb>
  </el-row>
  <div class="note" :style="note">
    <!-- 左边部分 -->
    <div class="left">
      <div class="mini-box">
        <h2 class="title-h2">空调概况</h2>
        <div>
          <MailTable class="table" frame="below" :tableData="tableData"></MailTable>
        </div>
      </div>
      <div class="mini-box">
        <h2 class="title-h2">设备统计</h2>
        <div>
          <MailTable class="table" frame="below" :tableData="tableData"></MailTable>
        </div>
      </div>
      <div class="mini-box">
        <h2 class="title-h2">资产统计</h2>
        <div>
          <MailTable class="table" frame="below" :tableData="tableData"></MailTable>
        </div>
      </div>
    </div>
    <!-- 中间部分 -->
    <div class="mid">
        <div class="mid-box-top">
          <h2 class="title-h2" style="padding-top:0.5% ;">展厅地图</h2>
          <div class="amiddboxttop_map" >
            <a class="people1" href="#" @click="room1">
              <img src="../../../assets/img/room7.png" alt />
            </a>
            <a class="people2" href="#" @click="room2">
              <img src="../../../assets/img/room7.png" alt />
            </a>
            <a class="people3" href="#" @click="room3">
              <img src="../../../assets/img/room7.png" alt />
            </a>
            <a class="people4" href="#" @click="room4">
              <img src="../../../assets/img/room7.png" alt />
            </a>
            <a class="people5" href="#" @click="room5">
              <img src="../../../assets/img/room7.png" alt />
            </a>
            <a class="people6" href="#" @click="room6">
              <img src="../../../assets/img/room7.png" alt />
            </a>
          </div>
      </div>
      <div>
        <PanelGroup class="mid-panel" @handleSetLineChartData="handleSetLineChartData" />
      </div>
    </div>
    <div class="right">
      <div class="mini-box">
        <h2 class="title-h2">传感器概况</h2>
        <div>
          <MailTable class="table" frame="below" :tableData="tableData"></MailTable>
        </div>
      </div>
      <div class="mini-box">
        <h2 class="title-h2">预警提示</h2>
        <div>
          <MailTable class="table" frame="below" :tableData="tableData"></MailTable>
        </div>
      </div>
      <div class="mini-box">
        <h2 class="title-h2">故障统计</h2>
        <div>
          <MailTable class="table" frame="below" :tableData="tableData"></MailTable>
        </div>
      </div>
    </div>
  </div>
  </body>
</template>
<script>
import MailTable from './components/TableVertical'
import HomePie from './components/HomePie'
import HomeMix from './components/HomeMix'
import PanelGroup from './components/PanelGroup'
export default {
  components: {
    MailTable,
    HomePie,
    HomeMix,
    PanelGroup
  },
  data () {
    return {
      tableData: [],
      tableKey: ['温度', '湿度', 'CO2', 'PM2.5', '能耗', '人数', '舒适度']
    }
  },
  methods: {
    room1 () {
      this.$router.push('/room1/environment')
    },
    room2 () {
      this.$router.push('/room2/environment')
    },
    room3 () {
      this.$router.push('/room3/environment')
    },
    room4 () {
      this.$router.push('/room4/environment')
    },
    room5 () {
      this.$router.push('/room5/environment')
    },
    room6 () {
      this.$router.push('/room6/environment')
    },
    next () {
      this.$router.push('/identification')
    },
    before () {
      this.$router.push('/fivefloor')
    }
  },
  created () {
  }
}
</script>

<style scoped>
body {
  position: relative;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  margin: 0;
  padding: 0;
  weight: 100%;
  height: 90%;
  /*background: #051655;*/
  /*font-family: 微软雅黑, MicrosoftYahei, sans-serif;*/
  /*color: #fff;*/
}

.bread {
  margin: 18px 0px 0px 18px
}

.note {
  /*background: url("../../../assets/img/bg1.jpg") repeat;*/
  background-size: 100% auto;
  width: 100%;
  height: calc(100% - 10px);
  marginTop: '5px'
  /* position: fixed; */
}

.mid {
  float: left;
  width: 55%;
  height: 100%;
  margin-left: 2%;
  padding-top: 1%;
}

.mid-box-top {
  background-image: url("../../../assets/img/amiddboxttop.png");
  overflow: hidden;
  background-size: 100% 100%;
  background-repeat: no-repeat;
  background-position: top center;
  width: 100%;
  height: 65%;
}

.mid-panel{
  background-image: url("../../../assets/img/amiddboxttop.png");
  overflow: hidden;
  background-size: 100% 100%;
  background-repeat: no-repeat;
  background-position: top center;
  margin-left: 0%;
  width: 100%;
}

.left {
  width: 20%;
  height: 100%;
  float: left;
  /*margin-right: 10px;*/
  /*padding-left: 3.2%;*/
  text-align: center;
}

.right {
  width: 20%;
  height: 100%;
  float: right;
  padding-right: 0%;
  text-align: center;
}

.mini-box {
  background-size: 100% 100%;
  background-image: url("../../../assets/img/leftb1.png");
  backgroud: no-repeat top center;
  width: 99%;
  height: 30%;
  margin-bottom: 5%;
}

.table {
  width: 91%;
  height: 68%;
  margin-left: 4.4%;
  margin-top: 0;
  background: rgba(1, 202, 217, 0.2);
  font-size: 15px;
  border: 1px white;
  color: rgba(255, 255, 255, 1);
}

.amiddboxttop_map {
  background-image: url("../../../assets/img/map3.png");
  background-size: 100% 100%;
  background-repeat: no-repeat;
  background-position: top center;
  position: relative;
  width: 90%;
  height: 100%;
  margin: 1.4% 5% 0;
}

.people1:hover,
.people2:hover,
.people3:hover,
.people4:hover,
.people5:hover,
.people6:hover {
  opacity: 0.7;
}

.people1 {
  position: absolute;
  left: 2% !important;
  top: 24% !important;
}

.people2 {
  position: absolute;
  left: -9% !important;
  top: 19% !important;
}

.people3 {
  position: absolute;
  left: -19% !important;
  top: 14% !important;
}

.people4 {
  position: absolute;
  left: -29% !important;
  top: 10% !important;
}

.people5 {
  position: absolute;
  left: 13% !important;
  top: 13% !important;
}

.people6 {
  position: absolute;
  left: 17% !important;
  top: 26% !important;
}

.pt1 {
  padding-top: 1.3%;
}

.pt2 {
  padding-top: 2.2%;
}

.pt3 {
  padding-top: 3.3%;
}

.pt6 {
  padding-top: 6px;
}

.pt17 {
  padding-top: 17px;
}

.pt14 {
  padding-top: 14px;
}

.pt12 {
  padding-top: 12px;
}

.pt20 {
  padding-top: 22px;
}

.table2 {
  width: 91%;
  height: 68%;
  margin-left: 4.4%;
  margin-top: 0;
  background: rgba(1, 202, 217, 0.2);
  font-size: 15px;
  border: 1px white;
}

.title-h2 {
  text-align: center;
  width: 100%;
  font-size: 13px;
  padding-top: 1.9%;
  font-weight: bold;
  letter-spacing: 2px;
  font-weight: normal;
  overflow: hidden;
  color: rgba(255, 255, 255, 1);
  letter-spacing: 6px;
}

.lefttoday_tit {
  overflow: hidden;
  padding: 0;
  height: 6%;
  position: relative;
}

.lefttoday_tit p.fl {
  font-size: 12px;
  color: rgba(255, 255, 255, 1);
  position: absolute;
  left: 5%;
  top: 0%;
  margin-bottom: 0 !important;
  margin-top: 0 !important;
}

.lefttoday_tit p.fr {
  font-size: 12px;
  color: rgba(255, 255, 255, 0.6);
  position: absolute;
  right: 5%;
  top: 0%;
  margin-bottom: 0 !important;
  margin-top: 0 !important;
}

.lefttoday_tit p.fm {
  font-size: 12px;
  color: rgba(255, 255, 255, 1);
  position: absolute;
  left: 40%;
  top: 25%;
}

/* .wpbox {
    width: 80%;
    height: 987px;
  } */

.info1 {
  border: 1px;
  border-color: white;
  border-style: solid;
  width: 200px;
  height: 180px;
  color: white;
  top: 200px;
  left: 60px;
}

li {
  font-weight: 100;
}

/* .people1 {width: 40px;height: 40px;padding-top: 350px;margin-left: -180px;} */
.people1 {
  width: 40px;
  height: 40px;
  position: relative;
  top: 355px;
  left: -30px;
}

.people2 {
  width: 40px;
  height: 40px;
  position: relative;
  top: 320px;
  left: -160px;
}

.people3 {
  width: 40px;
  height: 40px;
  position: relative;
  top: 290px;
  left: -280px;
}

.people4 {
  width: 40px;
  height: 40px;
  position: relative;
  top: 250px;
  left: -400px;
}

.people5 {
  width: 45px;
  height: 45px;
  position: absolute;
  top: 310px;
  left: 300px;
}

.people6 {
  width: 40px;
  height: 40px;
  position: absolute;
  top: 405px;
  left: 400px;
}

.people7 {
  width: 52px;
  height: 54px;
  position: absolute;
  top: 670px;
  left: 1370px;
}

.people8 {
  width: 50px;
  height: 50px;
  position: absolute;
  top: 630px;
  left: 1100px;
}

a {
  display: inline-block;
}

.people1:hover,
.people2:hover,
.people3:hover,
.people4:hover,
.people5:hover,
.people6:hover,
.people7:hover,
.people8:hover {
  opacity: 0.7;
  width: unset;
}

.weather-box {
  width: 4.1rem;
  height: 1.1rem;
  border: 1px solid #0e94ea;
  display: flex;
  flex-flow: row nowrap;
  justify-content: space-around;
  align-items: center;
}
</style>
