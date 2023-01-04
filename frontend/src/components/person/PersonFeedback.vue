<template>
  <body id="poster">
  <el-form :model="feedBackForm" :rules="rules" ref="feedBackForm" class="login-container" label-position="left" label-width="0px">
    <h3 class="login_title">热舒适反馈表</h3>
    <el-form-item>
      <el-input type="text" v-model="feedBackForm.thermalSens"
                auto-complete="off" placeholder="账号"></el-input>
    </el-form-item>
    <el-form-item>
      <el-input type="text" v-model="feedBackForm.humidSens"
                auto-complete="off" placeholder="密码"></el-input>
    </el-form-item>
    <el-form-item prop="thermalAccept">
      <el-radio-group v-model="feedBackForm.thermalAccept" class = el-radio_label>
        <el-radio :label="0" >可接受</el-radio>
        <el-radio :label="1" >不可接受</el-radio>
      </el-radio-group>
    </el-form-item>
    <el-form-item prop="thermalComfort">
      <el-radio-group v-model="feedBackForm.thermalComfort" class = el-radio_label>
        <el-radio :label="0" >舒适</el-radio>
        <el-radio :label="1" >不舒适</el-radio>
      </el-radio-group>
    </el-form-item>
    <el-form-item style="width: 100%">
      <el-button type="primary" style="width: 100%;background: #505458;border: none" v-on:click="submitForm">登录</el-button>
    </el-form-item>
  </el-form>
  </body>
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
  margin: 90px auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}
.login_title {
  margin: 0px auto 40px auto;
  text-align: center;
  color: #505458;
}

</style>
