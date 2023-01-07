<template>
  <el-menu
    class="categories"
    default-active="0"
    @select="selected"
    active-text-color="red">
    <el-menu-item index="PersonInfo">
      <i class="el-icon-menu"></i>
      <span slot="title">基本信息采集</span>
    </el-menu-item>
    <el-menu-item index="PersonFeedback">
      <i class="el-icon-menu"></i>
      <span slot="title">热舒适反馈</span>
    </el-menu-item>
  </el-menu>
</template>

<script>
export default {
  name: 'SideMenu',
  methods: {
    selected (key, keyPath) {
      console.log(key, keyPath)
      this.$emit('sideBarTrigger', key)
    }
  }
}
</script>

<style scoped>
.categories {
  position: fixed;
  margin-left: 50%;
  left: -700px;
  top: 100px;
  width: 150px;
}
</style>
