<template>
  <div style="text-align: left">
    <el-button class="add-button" type="success" @click="dialogFormVisible = true">批量添加设备</el-button>
    <el-dialog
      title="添加设备"
      :visible.sync="dialogFormVisible"
      @close="clear"
      width="25%">
      <el-form :model="deviceForm" :rules="rules" label-position="left"
               label-width="0px">
        <el-form-item prop="deviceId">
          <el-input type="text" v-model="deviceForm.deviceId"
                    auto-complete="off" placeholder="设备编号"></el-input>
        </el-form-item>
        <el-form-item prop="deviceType">
          <el-select type="text" v-model="deviceForm.deviceType" auto-complete="off" placeholder="设备类型" style="width: 100%">
            <el-option label="空调" value="0"></el-option>
            <el-option label="六合一传感器" value="1"></el-option>
            <el-option label="二合一传感器" value="2"></el-option>
            <el-option label="风速仪" value="3"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="deviceName">
          <el-input type="text" v-model="deviceForm.deviceName"
                    auto-complete="off" placeholder="设备名称"></el-input>
        </el-form-item>
        <el-form-item prop="deviceHealth">
          <el-select type="text" v-model="deviceForm.deviceHealth" auto-complete="off" placeholder="健康状况" style="width: 100%">
            <el-option label="在线" value="0"></el-option>
            <el-option label="离线" value="1"></el-option>
            <el-option label="告警" value="2"></el-option>
            <el-option label="故障" value="3"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="location">
          <el-input type="text" v-model="deviceForm.location"
                    auto-complete="off" placeholder="设备位置"></el-input>
        </el-form-item>
        <el-form-item style="width: 100%">
          <el-button type="primary" style="width: 40%;background: #505458;border: none" v-on:click="register">添加</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'Registration',
  data () {
    return {
      dialogFormVisible: false,
      rules: { },
      deviceForm: {
        deviceId: '',
        deviceType: '',
        deviceName: '',
        deviceHealth: '',
        location: ''
      }
    }
  },
  methods: {
    clear () {
      this.deviceForm = {
        deviceId: '',
        deviceType: '',
        deviceName: '',
        deviceHealth: '',
        location: ''
      }
    },
    register () {
      this.$axios
        .post('/admin/device', {
          deviceId: this.deviceForm.deviceId,
          deviceType: this.deviceForm.deviceType,
          deviceName: this.deviceForm.deviceName,
          deviceHealth: this.deviceForm.deviceHealth,
          location: this.deviceForm.location
        })
        .then(resp => {
          if (resp.data.code === 200) {
            this.$alert(resp.data.result, '提示', {
              confirmButtonText: '确定'
            })
            this.clear()
            this.$emit('onSubmit')
          } else {
            this.$alert(resp.data.message, '提示', {
              confirmButtonText: '确定'
            })
          }
        })
        .catch(failResponse => {})
    }
  }
}
</script>

<style scoped>
.add-button {
  margin: 18px 0 0 10px;
}
</style>
