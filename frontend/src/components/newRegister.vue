<template>
  <div v-cloak class="aui-page page-login">
    <div class="login-wrapper">
      <div class="aui-page-header">


      </div>
      <main class="aui-page-main" >

        <div><h3 class="login-title">注册</h3></div>

        <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmitHandle()" status-icon>
          <el-form-item prop="language">
            <el-select v-model="dataForm.language" placeholder="身份选择" class="w-100">
              <el-option label="学生" value="student"></el-option>
              <el-option label="教师" value="teacher"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item prop="mobile">
            <el-input v-model="dataForm.mobile" placeholder="电子邮箱">
      <span slot="prefix" class="el-input__icon">
        <svg class="icon-svg" aria-hidden="true"><use xlink:href="#icon-phone"></use></svg>
      </span>
            </el-input>
          </el-form-item>

          <el-form-item prop="name">
            <el-input v-model="dataForm.name" placeholder="用户名">
      <span slot="prefix" class="el-input__icon">
        <svg class="icon-svg" aria-hidden="true"><use xlink:href="#icon-phone"></use></svg>
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
          <el-form-item prop="confirmPassword">
            <el-input v-model="dataForm.confirmPassword" type="password" placeholder="确认密码">
      <span slot="prefix" class="el-input__icon">
        <svg class="icon-svg" aria-hidden="true"><use xlink:href="#icon-lock"></use></svg>
      </span>
            </el-input>
          </el-form-item>

          <el-form-item prop="smsCaptcha">
            <el-row :gutter="20">
              <el-col :span="14">
                <el-input v-model="dataForm.smsCaptcha" placeholder="电子邮件验证码">
          <span slot="prefix" class="el-input__icon">
            <svg class="icon-svg" aria-hidden="true"><use xlink:href="#icon-safetycertificate"></use></svg>
          </span>
                </el-input>
              </el-col>
              <el-col :span="10">
                <el-button class="w-100" @click="sendCode()">获取验证码</el-button>
              </el-col>
            </el-row>
          </el-form-item>

          <el-form-item>
            <el-button class="w-100" type="primary" @click="register()">注册</el-button>
          </el-form-item>
        </el-form>
        <div><p class="login-guide">已经注册过？<a href="./#/login">立即登录</a></p></div>


      </main>



    </div>
  </div>

</template>

<script>
  import '../icons/iconfont.js'
  export default {
    name: "newRegister",
    data () {
      return {

        code:'XG8Y',
        dataForm: {
          mobile: '',
          name:'',
          password: '',
          confirmPassword: '',
          captcha: '',
          smsCaptcha: '',
          agreement: false
        },
        dataRule: {

          mobile: [
            { required: true, message: '必填项不能为空', trigger: 'blur' }
          ],
          name: [
            { required: true, message: '必填项不能为空', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '必填项不能为空', trigger: 'blur' }
          ],
          confirmPassword: [
            { required: true, message: '必填项不能为空', trigger: 'blur' }
          ],
          captcha: [
            { required: true, message: '必填项不能为空', trigger: 'blur' }
          ],
          smsCaptcha: [
            { required: true, message: '必填项不能为空', trigger: 'blur' }
          ],
          agreement: [
            { validator: function (rule, value, callback) {
                if (!value) {
                  callback(new Error('请先阅读并勾选注册协议'));
                } else {
                  callback();
                }
              }, trigger: 'change' }
          ]
        }
      }
    },
    beforeCreate () {
      vm = this;
    },
    methods: {

      sendCode(){
        var xmlhttp = new XMLHttpRequest();

        var _this = this
        xmlhttp.onreadystatechange = function () {
          if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            _this.code = xmlhttp.responseText;
            console.log(xmlhttp.responseText);
          }
        }

        let formData = new FormData()
        formData.append('address', this.dataForm.mobile)
        xmlhttp.open('POST', 'http://localhost:8080/j2ee/user/sendemail', true)
        xmlhttp.send(formData)

      },
      register(){


        console.log('1'+this.dataForm.smsCaptcha)
        console.log('2'+this.code)
        if(this.dataForm.smsCaptcha!=this.code){

          return
        }
        var xmlhttp = new XMLHttpRequest();

        console.log(this.dataForm.mobile)
        var user = {

          name:this.dataForm.name,
          password:this.dataForm.password,
          role:this.dataForm.language,
          email:this.dataForm.mobile,
          avator:''
        }
        var _this = this
        xmlhttp.onreadystatechange = function () {
          if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

            if(xmlhttp.responseText!=null){
              _this.$router.push({path: '/login'})
            }
          }
        }



        xmlhttp.open('POST', 'http://localhost:8080/j2ee/user/register', true)
        xmlhttp.setRequestHeader('Content-type', 'application/json; charset=utf-8')
        xmlhttp.send(JSON.stringify(user))
      },
      dataFormSubmitHandle () {
        vm.$refs['dataForm'].validate(function (valid) {
          if (valid) {
            window.location.href = './login.html';
          }
        });
      }
    }
  }
</script>

<style scoped>
  @import "../style/aui-orange.min.css";
</style>
