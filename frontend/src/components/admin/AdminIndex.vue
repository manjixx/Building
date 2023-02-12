<template>
  <div>
    <nav-menu></nav-menu>
    <el-container id="admin-body">
      <el-aside style="height: 112%;width: 200px">
        <side-menu></side-menu>
      </el-aside>
      <el-main style="margin-left: 0%">
        <router-view/>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import NavMenu from '../common/NavMenu'
import SideMenu from '../common/SideMenu'

export default {
  name: 'AdminIndex',
  components: {NavMenu, SideMenu},
  data () {
    return {
      dialogVisible: false
    }
  },
  mounted () {
    // this.$router.replace('/admin/dashboard')
  }
}
</script>

<style scoped>
#admin-body {
  position: fixed;
  height: 100%;
  width: 100%;
}
</style>
