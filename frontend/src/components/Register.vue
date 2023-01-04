<template>
  <body id="paper">
  <el-form :model="registerForm" :rules="rules" ref="registerForm" class="login-container" label-position="left"
           label-width="0px" v-loading="loading">
    <h3 class="login_title">用户注册</h3>
    <el-form-item prop="username">
      <el-input type="text" v-model="registerForm.username"
                auto-complete="off" placeholder="账号"></el-input>
    </el-form-item>
    <el-form-item prop="password">
      <el-input type="password" v-model="registerForm.password"
                auto-complete="off" placeholder="密码"></el-input>
    </el-form-item>
    <el-form-item style="width: 100%">
      <el-button type="primary" style="width: 40%;background: #505458;border: none" v-on:click="submitForm">注册</el-button>
    </el-form-item>
  </el-form>
  </body>
</template>
<script>
import { register } from './../api/index'
export default{
  data () {
    return {
      rules: {
        username: [{required: true, message: '用户名不能为空', trigger: 'blur'}],
        password: [{required: true, message: '密码不能为空', trigger: 'blur'}]
      },
      checked: true,
      registerForm: {
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
      this.$refs.registerForm.validate((valid) => {
        if (valid) {
          register(this.registerForm)
            .then(res => {
              if (res.data.code === 200) {
                this.$alert('注册成功', '提示', {
                  confirmButtonText: '确定'
                })
                _this.$router.replace('/login')
              } else {
                this.$alert(res.data.message, '提示', {
                  confirmButtonText: '确定'
                })
              }
            })
            .catch(failResponse => {})
        } else {
          this.$message({
            type: 'error',
            message: '数据格式不对'
          })
          // return false;
        }
      })
    },
    // 表单重置
    resetForm (formName) {
      this.$refs[formName].resetFields()
    }
  }
}
</script>

<style>
#paper {
  background: url("../assets/eva1.jpg") no-repeat center;
  height: 100%;
  width: 100%;
  background-size: cover;
  position: fixed;
}
body{
  margin: -5px 0px;
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
.login_remember {
  margin: 0px 0px 35px 0px;
  text-align: left;
}
</style>
