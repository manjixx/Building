<template>
  <body id="poster">
  <el-form :model="loginForm" :rules="rules" ref="loginForm" class="login-container" label-position="left" label-width="0px">
    <h3 class="login_title">系统登录</h3>
    <el-form-item>
      <el-input type="text" v-model="loginForm.username"
                auto-complete="off" placeholder="账号"></el-input>
    </el-form-item>
    <el-form-item>
      <el-input type="password" v-model="loginForm.password"
                auto-complete="off" placeholder="密码"></el-input>
    </el-form-item>
    <el-form-item class="tips">
      <a href="javascript:;" @click="navigateToRegister">没有账号？点击注册</a>
    </el-form-item>
    <el-form-item style="width: 100%">
      <el-button type="primary" style="width: 100%;background: #505458;border: none" v-on:click="submitForm">登录</el-button>
    </el-form-item>
  </el-form>
  </body>
</template>
<script>

import { login } from './../api/index'
export default {
  data () {
    return {
      rules: {
        username: [{required: true, message: '用户名不能为空', trigger: 'blur'}],
        password: [{required: true, message: '密码不能为空', trigger: 'blur'}]
      },
      checked: true,
      loginForm: {
        username: '',
        password: ''
      },
      loading: false
    }
  },
  methods: {
    // 表单校验并提交
    submitForm (formName) {
      var _this = this
      // console.log(this.$store.state)
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          login(this.loginForm)
            .then(successResponse => {
              if (successResponse.data.code === 200) {
                // var data = this.loginForm
                _this.$store.commit('login', _this.loginForm)
                var path = this.$route.query.redirect
                this.$router.replace({path: path === '/' || path === undefined ? 'admin/questionnaire/feedback' : path})
                successResponse.data.isNewUser = 1
                if (successResponse.data.isNewUser === 1) {
                  this.$confirm('请填写基本信息', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                  }).then(() => {
                    this.$router.push({
                      path: '/admin/questionnaire/information'
                    })
                  })
                }
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
    navigateToRegister () {
      this.$router.push('/register')
    }
  }
}
</script>

<style>
#poster {
  background: url("../assets/eva1.jpg") no-repeat center;
  height: 100%;
  width: 100%;
  background-size: cover;
  position: fixed;
}
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
