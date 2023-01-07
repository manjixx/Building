<template>
  <div class="container">
    <div class="details-bg">
    <h3 class="form_title">热舒适反馈表</h3>
    <el-form :inline="true" :model="feedBackForm" :rules="rules" ref="feedBackForm" class="login-container" label-position="left" lable-width="80px" >
      <el-row :gutter=24>
        <el-col :span=12>
          <el-form-item label="时间" label-position="left" prop="time" >
            <el-time-select
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
        <el-col :span="12">
          <el-form-item label="热感觉" label-position="left" prop="thermalSens">
            <el-input v-model="feedBackForm.thermalSens"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter=24>
        <el-col :span=12>
          <el-form-item label="湿度感觉" prop="humidSens">
            <el-input v-model="feedBackForm.humidSens"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="热可接受性" prop="thermalAccept">
            <el-select v-model="feedBackForm.thermalAccept">
              <el-option label="可接受" value="1"></el-option>
              <el-option label="不可接受" value="0"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter=24>
        <el-col :span=12>
          <el-form-item label="热舒适" prop="thermalComfort">
            <el-select v-model="feedBackForm.thermalComfort">
              <el-option label="舒适" value="1"></el-option>
              <el-option label="不舒适" value="0"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="风速可接受性" prop="airVelAccept">
            <el-select v-model="feedBackForm.airVelAccept">
              <el-option label="可接受" value="1"></el-option>
              <el-option label="不可接受" value="0"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter=24>
        <el-col :span=12>
          <el-form-item label="实时热偏好" prop="thermalPref">
            <el-select v-model="feedBackForm.thermalPref">
              <el-option label="变凉" value="-1"></el-option>
              <el-option label="不变" value="0"></el-option>
              <el-option label="变热" value="1"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="情绪" prop="mood">
            <el-select v-model="feedBackForm.mood">
              <el-option label="负面" value="-1"></el-option>
              <el-option label="中性" value="0"></el-option>
              <el-option label="正面" value="1"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </div>
</div>
</template>
<script>

export default {
  name: 'PersonFeedBack',
  data () {
    return {
      rules: {
        thermalSens: [{required: true, message: '用户名不能为空', trigger: 'blur'}],
        humidSens: [{required: true, message: '密码不能为空', trigger: 'blur'}]
      },
      checked: true,
      feedBackForm: {
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
    submitForm (formName) {
      // var _this = this
      // console.log(this.$store.state)
      // this.$refs.loginForm.validate((valid) => {
      //   console.log(valid)
      //   console.log(this.loginForm)
      //   if (valid) {
      //     login(this.loginForm)
      //       .then(successResponse => {
      //         if (successResponse.data.code === 200) {
      //           // var data = this.loginForm
      //           _this.$store.commit('login', _this.loginForm)
      //           var path = this.$route.query.redirect
      //           this.$router.replace({path: path === '/' || path === undefined ? '/index' : path})
      //         }
      //       })
      //       .catch(failResponse => {
      //       })
      //   } else {
      //     this.$message({
      //       type: 'error',
      //       message: '数据格式不对'
      //     })
      //   }
      // })
    },
    // 跳转到注册界面
    navigateToRegister () {
      this.$router.push('/register')
    }
  }
}
</script>

<style>
/*#poster {*/
/*  background: url("../assets/eva1.jpg") no-repeat center;*/
/*  height: 100%;*/
/*  width: 100%;*/
/*  background-size: cover;*/
/*  position: fixed;*/
/*}*/
body{
  margin: 0px;
}
.login-container {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 20px auto;
  width: 780px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  /*border: 1px solid #eaeaea;*/
  /*box-shadow: 0 0 20px #cac6c6;*/
}
.form_title {
  /*margin: 0px auto 40px auto;*/
  text-align: center;
  /*color: #505458;*/
  margin-bottom: 12px;
  margin-top: 18px;
}
</style>
