<template>
  <div>
    <el-row style="margin: 18px 0px 0px 18px ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin/questionnaire/feedback' }">管理中心</el-breadcrumb-item>
        <el-breadcrumb-item>设备管理</el-breadcrumb-item>
        <el-breadcrumb-item>设备信息</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
    <registration @onSubmit="listSensors()"></registration>
    <el-card style="margin: 18px 2%;width: 95%">
      <el-table
        :data="sensors"
        stripe
        :default-sort = "{prop: 'id', order: 'ascending'}"
        style="width: 100%"
        :max-height="tableHeight">
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
        <el-table-column
          prop="id"
          label="id"
          sortable
          fit>
        </el-table-column>
        <el-table-column
          prop="deviceId"
          label="设备编号"
          fit>
        </el-table-column>
        <el-table-column
          prop="deviceType"
          label="设备类型"
          fit>
        </el-table-column>
        <el-table-column
          prop="deviceName"
          label="设备名称"
          fit>
        </el-table-column>
        <el-table-column
          prop="deviceStatus"
          label="设备状态"
          sortble
          show-overflow-tooltip
          fit>
          <template slot-scope="scope">
            <el-switch
              v-model="scope.row.enabled"
              active-color="#13ce66"
              inactive-color="#ff4949"
              @change="(value) => commitStatusChange(value, scope.row)">
            </el-switch>
          </template>
        </el-table-column>
        <el-table-column
          prop="deviceHealth"
          label="健康状况"
          fit>
        </el-table-column>
        <el-table-column
          prop="location"
          label="设备位置"
          fit>
        </el-table-column>
        <el-table-column
          prop="gmtCreate"
          label="添加时间"
          fit>
        </el-table-column>
        <el-table-column
          prop="gmtModified"
          label="维护时间"
          fit>
        </el-table-column>
        <el-table-column
          label="操作"
          width="120">
          <template slot-scope="scope">
            <el-button
              @click="editSensor(scope.row)"
              type="text"
              size="small">
              编辑
            </el-button>
            <el-button
              @click="deleteSensor(scope.row)"
              type="text"
              size="small">
              移除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <div style="margin: 20px 0 20px 0;float: left">
        <el-dialog
          title="修改设备信息"
          :visible.sync="dialogFormVisible">
          <el-form v-model="selectedSensor" style="text-align: left" ref="dataForm">
            <el-form-item label="设备编号" label-width="120px" prop="deviceId">
              <label>{{selectedSensor.deviceId}}</label>
            </el-form-item>
            <el-form-item label="设备类型" label-width="120px" prop="deviceType">
              <label>{{selectedSensor.deviceType}}</label>
            </el-form-item>
            <el-form-item label="设备名称" label-width="120px" prop="deviceName">
              <label>{{selectedSensor.deviceName}}</label>
            </el-form-item>
            <el-form-item label="健康状况" label-width="120px" prop="deviceHealth">
              <el-input v-model="selectedSensor.deviceHealth" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="设备位置" label-width="120px" prop="location">
              <el-input v-model="selectedSensor.location" autocomplete="off"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="onSubmit(selectedSensor)">确 定</el-button>
          </div>
        </el-dialog>
        <el-button>取消选择</el-button>
        <el-button>批量删除</el-button>
      </div>
    </el-card>
  </div>
</template>

<script>
import Registration from './Registration'
export default {
  name: 'DeviceManagement',
  components: {Registration},
  data () {
    return {
      sensors: [],
      dialogFormVisible: false,
      selectedSensor: [],
      selectedRolesIds: []
    }
  },
  mounted () {
    this.listSensors()
    // this.listRoles()
  },
  computed: {
    tableHeight () {
      return window.innerHeight - 320
    }
  },
  methods: {
    listSensors () {
      var _this = this
      this.$axios.get('/admin/device').then(resp => {
        if (resp && resp.data.code === 200) {
          _this.sensors = resp.data.result
        }
      })
    },
    commitStatusChange (value, sensor) {
      this.$axios.put('/admin/device/status', {
        enabled: value,
        deviceName: sensor.deviceName
      }).then(resp => {
        if (resp && resp.data.code === 200) {
          if (value) {
            this.$message('用户 [' + sensor.deviceName + '] 已启用')
          } else {
            this.$message('用户 [' + sensor.deviceName + '] 已禁用')
          }
        }
      })
    },
    onSubmit (sensors) {
      this.$axios.put('/admin/device', {
        deviceId: sensors.deviceId,
        deviceType: sensors.deviceType,
        deviceName: sensors.deviceName,
        deviceHealth: sensors.deviceHealth,
        location: sensors.location
      }).then(resp => {
        if (resp && resp.data.code === 200) {
          this.$alert('用户信息修改成功')
          this.dialogFormVisible = false
          // 修改角色后重新请求用户信息，实现视图更新
          this.listSensors()
        } else {
          this.$alert(resp.data.message)
        }
      })
    },
    editSensor (sensor) {
      this.dialogFormVisible = true
      this.selectedSensor = sensor
      let roleIds = []
      for (let i = 0; i < sensor.roles.length; i++) {
        roleIds.push(sensor.roles[i].id)
      }
      this.selectedRolesIds = roleIds
    },
    deleteSensor (sensor) {
      this.$axios.delete('/admin/device?sid=' + sensor.id).then(resp => {
        if (resp && resp.data.code === 200) {
          this.$alert(resp.data.result, '提示', {
            confirmButtonText: '确定'
          })
          this.listSensors()
        } else {
          this.$alert(resp.data.message, '提示', {
            confirmButtonText: '确定'
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
