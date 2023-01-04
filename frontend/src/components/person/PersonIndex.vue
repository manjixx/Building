<template>
  <el-container>
    <el-aside style="width: 200px;margin-top: 20px">
      <switch></switch>
      <SideMenu @sideBarTrigger="this.parentHandle"></SideMenu>
    </el-aside>
    <el-main>
      <component v-bind:is=componted></component>
    </el-main>
  </el-container>
</template>

<script>
import SideMenu from './SideMenu'
import PersonInfo from './PersonInfo'
import PersonFeedback from './PersonFeedback'

export default {
  name: 'AppPerson',
  components: {SideMenu, PersonInfo, PersonFeedback},
  data () {
    return {
      componted: PersonInfo
    }
  },
  parentHandle (key) {
    this.componted = key
  }
}
</script>

<style scoped>

</style>
