<template>
  <el-menu
    :default-active="'/index'"
    router
    mode="horizontal"
    background-color="white"
    text-color="#222"
    active-text-color="red"
    class = el-nav>
    <i class="el-icon-switch-button" v-on:click="logout" style="float:right;font-size: 20px;color: #070707;padding: 10px"></i>
    <span class="el-title">人员热舒适数据采集系统</span>
  </el-menu>
</template>

<script>
export default {
  name: 'NavMenu',
  methods: {
    handleSelect (key, keyPath) {
      // console.log(key, keyPath)
    },
    logout () {
      var _this = this
      this.$axios.get('/logout').then(resp => {
        if (resp.data.code === 200) {
          // 登出操作前后端应保持一致
          _this.$store.commit('logout')
          _this.$router.replace('/login')
        }
      }).catch(failResponse => {})
    }
  }
}
</script>

<style scoped>
.el-nav {
  height: 80px;
  opacity: 0.85;
  line-height: 40px;
  min-width: 900px;
}

.el-title{
  position: absolute;
  padding-top: 20px;
  right: 43%;
  font-size: 20px;
  font-weight: bold;
}

.el-icon-switch-button {
  cursor: pointer;
  /*去除点击时的框线*/
  outline:0;
  color: white;
  margin-top: 20px;
}

</style>
