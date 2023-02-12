<template>
  <div class="container">
    <el-row style="margin: 18px 0px 0px 18px ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin/questionnaire/feedback' }">管理中心</el-breadcrumb-item>
        <el-breadcrumb-item>数据采集</el-breadcrumb-item>
        <el-breadcrumb-item>反馈信息</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
    <div class="details-bg">
      <h3 class="form_title">热舒适反馈表</h3>
      <el-form :inline="true" :model="feedBackForm" :rules="rules" ref="feedBackForm" class="block_form" >
        <el-row :gutter=24>
          <el-col :span="12">
            <el-form-item label="姓名" prop="name">
              <el-input  v-model="feedBackForm.name" ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span=12>
            <el-form-item label="时间" prop="time" >
              <el-time-select style="width: 102%; height: 105%;"
                              v-model="feedBackForm.time"
                              :picker-options="{
                  start: '08:30',
                  step: '00:30',
                  end: '18:30'
                }"
                              placeholder="选择时间">
              </el-time-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter=24>
          <el-col :span="12" class="el-topic">请填写热感觉，-3：非常寒冷，3：非常炎热 </el-col>
          <el-col :span="12">
            <el-slider style="margin-bottom: 15px;margin-top: 5px"
                       v-model="feedBackForm.thermalSens"
                       :step="0.1"
                       :max="3"
                       :min="-3">
            </el-slider>
          </el-col>
        </el-row>
        <el-row :gutter=24>
          <el-col :span="12" class="el-topic">请填写湿度感觉，-3：非常干燥，3：非常潮湿 </el-col>
          <el-col :span="12">
            <el-slider style="margin-bottom: 15px;margin-top: 5px"
                       v-model="feedBackForm.humidSens"
                       :step="0.1"
                       :max="3"
                       :min="-3">
            </el-slider>
          </el-col>
        </el-row>

        <el-row :gutter=24>
          <el-col :span="12" class="el-topic" >请选择是否能接受当前热环境</el-col>
          <el-col :span="12">
            <el-form-item prop="pref">
              <el-radio-group v-model="feedBackForm.pref" class = el-radio_label>
                <el-radio :label="0" >不可接受</el-radio>
                <el-radio :label="1" >可以接受</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter=24>
          <el-col :span="12" class="el-topic">仅针对热环境，请选择当前是否觉得舒适</el-col>
          <el-col :span="12">
            <el-form-item prop="thermalComfort">
              <el-radio-group v-model="feedBackForm.thermalComfort" class = el-radio_label>
                <el-radio :label="0" >不舒适</el-radio>
                <el-radio :label="1" >舒适</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter=24>
          <el-col :span="12" class="el-topic">请选择是否能接受当前室内空气流动速度</el-col>
          <el-col :span="12">
            <el-form-item prop="airVelAccept">
              <el-radio-group v-model="feedBackForm.airVelAccept" class = el-radio_label>
                <el-radio :label="0" >不可接受</el-radio>
                <el-radio :label="1" >可以接受</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter=24>
          <el-col :span="12" class="el-topic">请选择当前的实时热偏好</el-col>
          <el-col :span="12">
            <el-form-item prop="thermalPref">
              <el-radio-group v-model="feedBackForm.thermalPref" class = el-radio_label>
                <el-radio :label="-1" >变凉</el-radio>
                <el-radio :label="0" >不变</el-radio>
                <el-radio :label="1" >变热</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter=24>
          <el-col :span="12" class="el-topic">请选择您当前的情绪状态</el-col>
          <el-col :span="12">
            <el-form-item prop="mood">
              <el-radio-group v-model="feedBackForm.mood" class = el-radio_label>
                <el-radio :label="-1" >负面</el-radio>
                <el-radio :label="0" >中性</el-radio>
                <el-radio :label="1" >正面</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-row :gutter=24 class="el-row-button">
        <el-button @click=resetForm style="margin-right: 5%">取 消</el-button>
        <el-button type="primary" @click=submitForm()>提 交</el-button>
      </el-row>
    </div>
  </div>
</template>
<script>
import { uploadFeedback } from './../../../api/index.js'
export default {
  name: 'FeedBack',
  data () {
    return {
      rules: {
        thermalSens: [{required: true, message: '用户名不能为空', trigger: 'blur'}],
        humidSens: [{required: true, message: '密码不能为空', trigger: 'blur'}]
      },
      checked: true,
      feedBackForm: {
        name: '',
        time: '',
        thermalSens: '',
        humidSens: '',
        thermalAccept: '',
        thermalComfort: '',
        airVelAccept: '',
        thermalPref: '',
        mood: ''
      },
      loading: false
    }
  },
  methods: {
    // 表单校验并提交
    submitForm () {
      this.$refs.feedBackForm.validate((valid) => {
        if (valid) {
          uploadFeedback(this.feedBackForm)
            .then(successResponse => {
              if (successResponse.data.code === 200) {
                this.$alert('提交个人信息问卷成功!')
              } else {
                this.$alert('提交个人信息问卷失败！')
              }
            })
            .catch(failResponse => {
            })
        } else {
          this.$message({
            type: 'error',
            message: '数据格式不对'
          })
        }
      })
    },
    // 跳转到注册界面
    resetForm () {
      this.$refs.infoForm.resetFields()
    }
  }
}
</script>

<style lang="less" scoped>

.container {
  /* 让内部设置了 position 的元素相对于此元素定位 */
  position: relative;
  /* 父级内容最好滚动条，而不是任由内容撑开 */
   //max-height: 650px;
  width: auto;
  height: 85%;
  overflow-y: auto;
}

.details-bg{
  border-radius: 15px;
  background-clip: padding-box;
  width: 780px;
  margin: 10px auto;
  padding: 15px 35px 15px 35px;
  background: #fff;
}
.block_form{
  border: 1px solid #eaeefb;
  padding: 30px 30px;
  border-radius: 6px;
}
.form_title {
  /*margin: 0px auto 40px auto;*/
  text-align: center;
  /*color: #505458;*/
  margin-bottom: 12px;
  //margin-top: 1%;
}
.el-row {
  text-align: left;
}
.el-row-button {
  margin-top: 2%;
  text-align: center;
}
/deep/ .el-form-item__label {
  text-align: left;
  vertical-align: middle;
  float: left;
  font-size: 15px;
  color: #2c3e50;
  line-height: 40px;
  padding: 0 12px 0 0;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
}
/deep/.el-slider__runway {
  width: 70%;
  height: 6px;
  margin: 16px 0;
  background-color: #E4E7ED;
  border-radius: 3px;
  position: relative;
  cursor: pointer;
  vertical-align: middle;
}

.el-input{
  width: 120%;
}

.el-topic{
  text-align: left;
  margin-top: 8px;
  margin-bottom: 8px;
  font-size: 15px;
}
.my-button {
  /* 相对父级定位，处于父级的右下角，距离右边30px、底部50px的位置 */
  position: fixed;
  right: 500px;
  bottom: 50px;
}
</style>
