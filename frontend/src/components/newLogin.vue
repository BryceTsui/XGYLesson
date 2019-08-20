<template>
  <div v-cloak class="aui-page page-login">
    <div class="login-wrapper">
      <div class="aui-page-header">
        <h2 class="aui-brand">MY COURSE</h2>
        <ul class="aui-intro">
          <li>A Website for j2ee assignment.</li>
        </ul>
      </div>
      <main class="aui-page-main" >

        <div class="page-title" ><h3>登录</h3></div>
      <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmitHandle()">
        <el-form-item prop="username">
          <el-input v-model="dataForm.username" placeholder="用户名">
      <span slot="prefix" class="el-input__icon">
        <svg class="icon-svg" aria-hidden="true"><use xlink:href="#icon-user"></use></svg>
      </span>
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="dataForm.password" type="password" placeholder="密码">
      <span slot="prefix" class="el-input__icon">
        <svg class="icon-svg" aria-hidden="true"><use xlink:href="#icon-lock"></use></svg>
      </span>
          </el-input>
        </el-form-item>


        <el-form-item size="mini">
          <div class="base-line-height clearfix">
            <el-checkbox v-model="dataForm.rememb" class="f-left">记住密码</el-checkbox>
            <a href="./forget@@v2.html" class="f-right">忘记密码？</a>
          </div>
        </el-form-item>
        <el-form-item>
          <el-button class="w-100" type="primary" @click="login()">登录</el-button>
        </el-form-item>
      </el-form>

      </main>
      <div><p class="login-guide">还没有账号？<a href="./#/register">立即注册</a></p></div>

    </div>
  </div>

</template>

<script>
  import '../icons/iconfont.js'
    export default {
        name: "newLogin",
      data () {
        return {
          dataForm: {
            language: 'zh-CN',
            username: '',
            password: '',
            captcha: '',
            rememb: false
          },
          dataRule: {
            username: [
              { required: true, message: '必填项不能为空', trigger: 'blur' }
            ],
            password: [
              { required: true, message: '必填项不能为空', trigger: 'blur' }
            ],
            captcha: [
              { required: true, message: '必填项不能为空', trigger: 'blur' }
            ]
          }
        }
      },
      beforeCreate () {
        vm = this;
      },
      methods: {
        openWarn: function (text) {
          this.$notify({
            title: '警告',
            message: text,
            type: 'warning',
            duration: 2000,
            position: 'top-left'
          })
        },
        login () {

          var xmlhttp = new XMLHttpRequest();

          var _this = this
          xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
              console.log('13'+xmlhttp.responseText);
              if(xmlhttp.responseText!=null&&xmlhttp.responseText.length!=0) {

                var user = JSON.parse(xmlhttp.responseText)

                localStorage.setItem("user",JSON.stringify(user));
                localStorage.setItem("username",user.name)
                localStorage.setItem("role",user.role)
                localStorage.setItem("email",user.email)
                localStorage.setItem("password",user.password)

                if(user.role==='student') {
                  _this.$router.push({path: '/9'})
                }
                else if(user.role==='teacher') {
                  _this.$router.push({path: '/teacherbench'})
                }
                else{

                  _this.$router.push({path: '/admin'})
                }
              }
              else{
                _this.openWarn("登陆失败,请检查用户名或密码是否正确")
              }
            }
          }

          let formData = new FormData()
          formData.append('name', this.dataForm.username)
          formData.append('password', this.dataForm.password)
          xmlhttp.open('POST', 'http://localhost:8080/j2ee/user/login', true)
          xmlhttp.send(formData)
        }
      }
    }
</script>

<style scoped>
  @import "../style/aui-orange.min.css";
</style>
