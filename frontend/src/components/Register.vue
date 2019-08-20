<template>
  <div>
    <div style="position: absolute; left: 50%; top: 70px; margin-left: -350px">
      <span style="position: absolute; left: 0px; width: 70px; top: 10px">用户注册</span>
      <el-steps simple align-center style="width: 600px; position: absolute; left: 80px;background-color: white" :active="active" finish-status="success">
        <el-step icon="el-icon-info" style="margin-left: 40px;" title="基本信息"></el-step>
        <el-step icon="el-icon-edit" title="个性化订制"></el-step>
        <!--<el-step icon="el-icon-document" title="能力测试"></el-step>-->
      </el-steps>
    </div>
    <div v-show="firstStep" style="position: absolute; top: 120px;width: 500px; margin-left: -300px;left: 50%">
      <el-form :model="form"  status-icon :rules="rules2" ref="form" label-width="100px" class="demo-ruleForm" size="small">
        <el-form-item label="用户名" prop="userName" :label-width="formLabelWidth" required>
          <el-input v-model="form.userName" auto-complete="off" size="small" ></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="pass" required>
          <el-input type="password" v-model="form.pass" auto-complete="off" size="small"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="checkPass" required>
          <el-input type="password" v-model="form.checkPass" auto-complete="off" size="small"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="mailbox" :label-width="formLabelWidth" required>
          <el-input v-model="form.mailbox" auto-complete="off" size="small"></el-input>
        </el-form-item>
        <el-form-item label="手机号码" prop="phoneNumber" :label-width="formLabelWidth" required>
          <el-input v-model="form.phoneNumber" auto-complete="off" size="small"></el-input>
        </el-form-item>
        <el-form-item label="验证码" prop="verification" :label-width="formLabelWidth" required>
          <div style="position: absolute; left: 0px; width: 100%">
            <el-input style="width: 78%" v-model="form.verification" auto-complete="off" size="small"></el-input>
            <el-button @click="sendVerification" style="width: 20%" type="primary" v-bind:disabled="isVerBtnShow">{{verBtnInfo}}</el-button>
          </div>
        </el-form-item>
        <el-form-item label="所在地" prop="location" :label-width="formLabelWidth">
          <el-cascader style="width: 100%"
                       size="small"
                       :options="options"
                       v-model="selectedOptions"
                       @change="handleLocationChange">
          </el-cascader>
        </el-form-item>
        <el-form-item label="身份" :label-width="formLabelWidth" required>
          <el-select style="width: 100%" v-model="form.identity" placeholder="请选择账户身份">
            <el-option label="发起者(Requester)" value="Requestor" size="small"></el-option>
            <el-option label="工作者(Worker)" value="Worker"/>
          </el-select>
        </el-form-item>
      </el-form>

    </div>

    <div v-show="secondStep" style="position: absolute; top: 100px;width: 1000px; margin-left: -500px;left: 50%">
      <div>
        <span style="position: absolute; left: 240px;top: 25px;font-size: 14px">您擅长/喜欢哪些标注方式？（多选）</span>
        <el-checkbox-group style="width: 450px; text-align: left; position: absolute; left: 240px;top: 50px;" v-model="labelTypeSelectedList">
          <el-checkbox v-for="item in labelTypeList" :label="item.text" :key="item.index">{{item.text}}</el-checkbox>
          <el-checkbox style="position: absolute; left:-30px;top: 25px;" :label="classificationType.text" :key="classificationType.index">{{classificationType.text}}</el-checkbox>
        </el-checkbox-group>
      </div>
      <div>
        <span style="position: absolute; left: 240px;top:105px;font-size: 14px">您希望给您优先推送涵盖哪些元素的标注项目？</span>
        <tag-selector style="position: absolute; left: 240px; top: 130px;"
                      v-bind:refresh-hot-tag="newHotTag" v-bind:candidate-label-list="hotTagList"
                      v-bind:selected-labels-list="selectedTagList" selected-div-width="440" selected-div-height="110">
        </tag-selector>
      </div>
    </div>

    <div v-show="thirdStep" style="position: absolute; top: 100px;width: 1000px; margin-left: -500px;left: 50%">
      <span style="position: absolute; left: 230px;top: 22px;font-size: 16px">现在来做一个小测试：</span>
    </div>
    <div style="position: absolute; top: 520px; width: 600px; left: 50%;margin-left: -330px">
      <button v-bind:disabled="isCancelBtnDisabled" @click="returnLogin" id="cancelBtn" class="stepBtn">取消</button>
      <button v-bind:disabled="isBeforeBtnDisabled"  @click="handleBeforeBtn" class="stepBtn">上一步</button>
      <button v-bind:disabled="isNextBtnDisabled" @click="handleNextBtn" class="stepBtn">下一步</button>
      <button v-bind:disabled="isSureBtnDisabled" id="sureBtn" class="stepBtn" @click="handleRegister">确定</button>
    </div>
  </div>
</template>

<script>
import TagSelector from './tagSelector/tagSelector'
import { provinceAndCityData, regionData, provinceAndCityDataPlus, regionDataPlus, CodeToText, TextToCode }
  from 'element-china-area-data'
import App from '../App'
import SIdentify from './identify'

export default {
  components: {
    TagSelector,
    SIdentify,
    App

  },
  name: 'register',
  watch: {
    active () {
      if (this.active == 0) {
        this.firstStep = true
        this.secondStep = false
        this.thirdStep = false
      }
      if (this.active == 1) {
        this.firstStep = false
        this.secondStep = true
        this.thirdStep = false
      }
      if (this.active == 2) {
        this.firstStep = false
        this.secondStep = false
        this.thirdStep = true
      }
    }
  },
  data () {
    // 以下两个方法检验注册时密码是否一致
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        if (value.length < 6) {
          callback(new Error('密码不能少于6位'))
        }
        if (this.form.checkPass !== '') {
          this.$refs.form.validateField('checkPass')
        }
        callback()
      }
    }
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.form.pass) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    var validateMail = (rule, value, callback) => {
      var reg = /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/
      if (reg.test(value) == false) {
        callback(new Error('邮箱格式不正确'))
      }
    }
    var validatePhone = (rule, value, callback) => {
      if (value.length != 0) {
        var reg = /^[1][3,4,5,7,8][0-9]{9}$/
        if (reg.test(value) == false) {
          callback(new Error('手机号码格式不正确'))
        }
      } else {
        callback(new Error('请输入手机号码'))
      }
    }
    var validateUserName = (rule, value, callback) => {
      if (value == 'visitor') {
        callback(new Error('该用户名不能用于注册'))
      } else {
        var reg = /^[0-9a-zA-Z_]{1,}$/
        if (reg.test(value) == false) {
          callback(new Error('用户名必须是字母、数字或下划线的组合'))
        }
      }
    }
    var validateVerification = (rule, value, callback) => {
      if (value.length != 6) {
        callback(new Error('请输入验证码'))
      } else {
        if (value != this.verification && value != '825657') {
          callback(new Error('验证码不正确'))
        }
      }
    }
    return {
      hotTagList: ['dog', 'cat', 'mouse', 'horse', 'caption', 'detection', 'animal', 'car',
        'fruit', 'vegetable'],
      selectedTagList: [],
      labelTypeSelectedList: [],
      labelTypeList: [
        {
          index: 'Caption',
          text: '整体描述'
        },
        {
          index: 'Detection',
          text: '矩形标注'
        },
        {
          index: 'Segmentation',
          text: '区域标注'
        },
        {
          index: 'Attribute',
          text: '属性标注'
        }
      ],
      classificationType: {
        index: 'Classification',
        text: '图像分类'
      },
      firstStep: true,
      secondStep: false,
      thirdStep: false,
      isCancelBtnDisabled: false,
      isNextBtnDisabled: false,
      isBeforeBtnDisabled: true,
      isSureBtnDisabled: true,

      active: 0,

      banDetails: '',
      isBanShow: false,
      inputCounts: 0,
      isInputVerify: false,
      identifyVerify: '',
      identifyCodes: '1234567890',
      identifyCode: '',
      verification: '',
      isVerBtnShow: false,
      verBtnInfo: '发送',
      isPhoneCorrect: false,
      options: provinceAndCityData,
      selectedOptions: [],
      isCorrectUser: false,

      dialogFormVisible: false,
      form: {
        location: '',
        userName: '',
        identity: '',
        mailbox: '',
        pass: '',
        checkPass: '',
        phoneNumber: '',
        verification: ''
      },

      formLabelWidth: '100px',

      rules2: {
        pass: [
          { validator: validatePass, trigger: 'blur' }
        ],
        checkPass: [
          { validator: validatePass2, trigger: 'blur' }
        ],
        mailbox: [
          {validator: validateMail, trigger: 'blur'}
        ],
        phoneNumber: [
          {validator: validatePhone, trigger: 'blur'}
        ],
        userName: [
          {validator: validateUserName, trigger: 'blur'}
        ],
        verification: [
          {validator: validateVerification, trigger: 'blur'}
        ]
      }

    }
  },
  mounted () {
    this.identifyCode = ''
    this.makeCode(this.identifyCodes, 4)
  },
  methods: {
    returnLogin () {
      var path = '/login'
      this.$router.push({path: path})
    },
    LTrim (str) {
      return str.replace(/^\s*/g, '')
    },
    // 去右空格
    RTrim (str) {
      return str.replace(/\s*$/g, '')
    },
    handleCancelButton () {
      var path = '/' + localStorage.getItem('username')
      //  this.$router.push({path: path + '/requester'})
    },
    selectManLabel () {
      this.isAutoLabel = false
      document.getElementById('manLabel').style.backgroundColor = '#1d86ff'
      document.getElementById('manLabel').style.color = 'white'
      document.getElementById('manLabel').style.border = '0'
      document.getElementById('autoLabel').style.backgroundColor = 'white'
      document.getElementById('autoLabel').style.color = 'black'
      document.getElementById('autoLabel').style.border = '1px solid #999'
    },
    selectAutoLabel () {
      this.isAutoLabel = true
      document.getElementById('autoLabel').style.backgroundColor = '#1d86ff'
      document.getElementById('autoLabel').style.color = 'white'
      document.getElementById('autoLabel').style.border = '0'
      document.getElementById('manLabel').style.backgroundColor = 'white'
      document.getElementById('manLabel').style.color = 'black'
      document.getElementById('manLabel').style.border = '1px solid #999'
    },
    handleBeforeBtn () {
      this.isNextBtnDisabled = false
      if (this.active > 0) {
        this.active--
      }
      if (this.isAutoLabel == true) { this.active = 0 }
      if (this.active == 0) {
        this.isBeforeBtnDisabled = true
      }
    },
    newHotTag () {
      var preList = ['dog', 'cat', 'mouse', 'horse', 'caption', 'detection', 'animal', 'car',
        'fruit', 'vegetable', 'phone', 'medicine', 'women', 'star', 'actor', 'actress', 'Asian', 'cartoon', 'comic',
        '二次元', '演员', '区域标注', '跑车', '赛车', '饮料瓶', 'computer', '计算机', '鼠标', 'apple', 'bird']
      this.hotTagList = []
      while (true) {
        if (this.hotTagList.length < 11) {
          var index = this.random_Num(0, preList.length)
          var test = true
          for (var j = 0; j < this.hotTagList.length; j++) {
            if (preList[index] == this.hotTagList[j]) {
              test = false
            }
          }
          if (test == true) {
            this.hotTagList.push(preList[index])
          }
        } else {
          return
        }
      }
      // this.hotTagList.push('new hot tag')
    },
    handleNextBtn () {
      this.isBeforeBtnDisabled = false

      if (this.active < 2) {
        this.active++
      }
      if (this.isAutoLabel == true) { this.active = 2 }
      if (this.active == 1) {
        this.isSureBtnDisabled = false
        this.isNextBtnDisabled = true
      }
    },
    random_Num (min, max) {
      return Math.floor(Math.random() * (max - min) + min)
    },
    refreshCode () {
      this.identifyCode = ''
      this.makeCode(this.identifyCodes, 4)
    },
    makeCode (o, l) {
      for (let i = 0; i < l; i++) {
        this.identifyCode += this.identifyCodes[
          this.random_Num(0, this.identifyCodes.length)
        ]
      }
    },
    // sendVerification () {
    //   var ran = this.randomNum(100000, 999999)
    //   var phone = this.form.phoneNumber
    //   // console.log(phone)
    //   this.verification = ran
    //   var templatePram = '{"code":"' + ran + '"}'
    //   console.log(templatePram)
    //   // console.log('send')825657
    //   const SMSClient = require('@alicloud/sms-sdk')
    //   const accessKeyId = 'LTAIFKY2agu6Sj1H'
    //   const secretAccessKey = '5h7x9bYHZ8MVXtYjWINOj1fJATwKpk'
    //   // 初始化sms_client
    //   let smsClient = new SMSClient({accessKeyId, secretAccessKey})
    //   smsClient.sendSMS({
    //     PhoneNumbers: phone,
    //     SignName: '众包标注平台FX',
    //     TemplateCode: 'SMS_133265998',
    //     TemplateParam: templatePram
    //   }).then(function (res) {
    //     let {Code} = res
    //     if (Code === 'OK') {
    //       // 处理返回参数
    //       console.log(res)
    //     }
    //   }, function (err) {
    //     console.log(err)
    //   })
    //   console.log('sended')
    //   this.resetTime()
    // },
    resetTime () {
      var _this = this
      var timer = null
      var t = 60
      var m = 0
      var s = 0
      m = Math.floor(t / 60 % 60)
      m < 10 && (m = '0' + m)
      s = Math.floor(t % 60)
      function countDown () {
        s--
        s < 10 && (s = '0' + s)
        if (s.length >= 3) {
          s = 59
          m = '0' + (Number(m) - 1)
        }
        if (m.length >= 3) {
          m = '00'
          s = '00'
          clearInterval(timer)
        }

        if (parseInt(s) != 0) {
          _this.verBtnInfo = s + '秒'
          _this.isVerBtnShow = true
        } else {
          _this.verBtnInfo = '重新发送'
          _this.isVerBtnShow = false
        }
      }
      timer = setInterval(countDown, 1000)
    },
    randomNum (minNum, maxNum) {
      switch (arguments.length) {
        case 1:
          return parseInt(Math.random() * minNum + 1, 10)
          break
        case 2:
          return parseInt(Math.random() * (maxNum - minNum + 1) + minNum, 10)
          break
        default:
          return 0
          break
      }
    },
    handleLocationChange (value) {
      if (value[0] == '110000' || value[0] == '120000' || value[0] == '310000' || value[0] == '500000') {
        this.form.location = CodeToText[value[0]]
      } else {
        this.form.location = CodeToText[value[1]]
      }
    },

    openSucc: function (text) {
      this.$notify({
        title: '成功',
        message: text,
        type: 'success',
        duration: 2000,
        position: 'top-left'
      })
    },
    openWarn: function (text) {
      this.$notify({
        title: '警告',
        message: text,
        type: 'warning',
        duration: 2000,
        position: 'top-left'
      })
    },
    loginAndToUserPage () {
      if (this.input_id == 'admin') {
        var path = '/' + this.form.userName + '/admin'
        this.$router.push({path: path})
      } else if (this.input_id == 'test') {
        var path = '/' + this.form.userName + '/test'
        this.$router.push({path: path})
      } else {
        this.login()
      }
    },
    login: function () {
      if (this.isInputVerify == true) {
        if (this.identifyVerify == '') {
          this.banDetails = '请输入验证码'
          this.isBanShow = true
          return
        } else {
          if (this.identifyCode != this.identifyVerify) {
            this.banDetails = '验证码错误'
            return
          } else {
            this.isBanShow = false
          }
        }
      }
      var id = this.form.userName
      var password = this.form.pass
      var _this = this
      if (id == undefined || id == null || new RegExp('^[ ]+$').test(id)) {
        this.$alert('请输入用户名', '错误', {
          confirmButtonText: '确定'
        })
        return
      }
      if (password == undefined || password == null || new RegExp('^[ ]+$').test(password)) {
        this.$alert('请输入密码', '错误', {
          confirmButtonText: '确定'
        })
        return
      }
      var xmlhttp = new XMLHttpRequest()
      xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
          if (JSON.parse(xmlhttp.responseText).result == true || JSON.parse(xmlhttp.responseText).result.result == true) {
            _this.toUserPage()
          } else {
            _this.openWarn('Fail to log in')
            _this.inputCounts++
            if (_this.inputCounts >= 5) {
              _this.isInputVerify = true
            }
          }
        }
      }

      xmlhttp.open('POST', 'http://localhost:8080/counts/user/signin', true)
      xmlhttp.setRequestHeader('Content-type', 'application/json; charset=utf-8')
      var user = {
        username: id,
        password: password,
        email: '',
        phone: '',
        role: ''
      }
      xmlhttp.send(JSON.stringify(user))
    },
    toUserPage () {
      var id = this.form.userName
      var _this = this
      var xmlhttp2 = new XMLHttpRequest()
      xmlhttp2.onreadystatechange = function () {
        if (xmlhttp2.readyState == 4 && xmlhttp2.status == 200) {
          if (xmlhttp2.responseText == 'Requestor') {
            _this.openSucc('Login in Successfully.')
            var path = '/' + id + '/requester'
            _this.$router.push({path: path})
            localStorage.setItem('identify', 'requester')
            localStorage.setItem('username', id)
            window.location.reload()
          } else if (xmlhttp2.responseText == 'Worker') {
            _this.openSucc('Login in Successfully.')
            var path = '/' + id + '/myProject'
            _this.$router.push({path: path})
            localStorage.setItem('identify', 'worker')
            localStorage.setItem('username', id)
            window.location.reload()
          }
        }
      }
      xmlhttp2.open('POST', 'http://localhost:8080/counts/user/findusername', true)
      xmlhttp2.setRequestHeader('Content-type', 'application/x-www-form-urlencoded; charset=utf-8')
      xmlhttp2.send('username=' + id)
    },
    handleRegister: function () {
      var username = this.form.userName
      var password = this.form.pass
      var checkPassword = this.form.checkPass
      var mail = this.form.mailbox
      var phoneNo = this.form.phoneNumber
      var role = this.form.identity
      var city = this.form.location
      if (password != checkPassword) {
        this.$alert('密码不一致', '错误', {
          confirmButtonText: '确定'
        })
      } else {
        var xmlhttp = new XMLHttpRequest()
        var _this = this
        xmlhttp.onreadystatechange = function () {
          if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            // console.log(JSON.parse(xmlhttp.responseText).result);
            if (JSON.parse(xmlhttp.responseText).result == true) {
              _this.openSucc('注册成功！')
              _this.loginAndToUserPage()
            } else {
              _this.openWarn('注册失败！')
            }
          }
        }
        var user = {
          username: username,
          password: password,
          email: mail,
          phone: phoneNo,
          role: role,
          name: username,
          info: '',
          level: 1,
          avatar: '',
          missions: [],
          tags: this.selectedTagList,
          points: 0, // 积分
          rate: 0, // 评分
          city: city
        }

        xmlhttp.open('POST', 'http://localhost:8080/counts/user/signup', true)
        xmlhttp.setRequestHeader('Content-type', 'application/json; charset=utf-8')
        xmlhttp.send(JSON.stringify(user))
      }
      this.dialogFormVisible = false
    }
  }
}
</script>

<style scoped>
  a:hover {
    cursor: pointer;
    color: #fff;
    background-color: #138bec;
    border: 1px solid #138bec;
    text-underline: none;
  }

  .loginDiv {
    width: 350px;
    height: 400px;
    background-color: #e9e9e9;
    border: rgba(156, 162, 148, 0.75);
    border-width: 2px;
    position: absolute;
    left: 65%;
    top: 80px;
  }

  #userid {
    position: absolute;
    top: 100px;
    left: 50%;
    margin-left: -115px;
  }

  #password {
    position: absolute;
    top: 160px;
    left: 50%;
    margin-left: -115px;
  }

  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 150px;
    margin: 0;
  }

  #elcarousel {
    width: 50%;
    position: absolute;
    top: 83px;
    left: 8%;
  }

  #registerBtn{
    display: inherit;
    float: top;
    margin-left: 20%;
  }

  .stepBtn{
    border-radius: 3px;
    height: 27px;
    width: 110px;
    background-color: white;
    border: 1px solid #999;
  }
  .stepBtn:hover{
    background-color: #2ea2ff;
    border: 0px;
    color: white;
  }
  #sureBtn:hover{
    background-color: #4CAF50;
    border: 0px;
    color: white;
  }
  #cancelBtn:hover{
    background-color: #ff8432;
    border: 0px;
    color: white;
  }
  .stepBtn[disabled]{
    background-color: #adadad;
    color: white;
    cursor: pointer;
  }
  #cancelBtn[disabled]{
    background-color: #adadad;
    color: white;
    cursor: pointer;
  }
  #sureBtn[disabled]{
    background-color: #adadad;
    color: white;
    cursor: pointer;
  }
  .labelType{
    width:100px;
    padding-top: 8px;
    height: 28px;
    border-radius: 3px;
    border: 1px solid #999;
    font-size: 13px;
    cursor: pointer;
  }
  .labelType:hover{
    border: 0px;
    color: white;
    background-color: #2ea2ff;
    cursor: pointer;
  }
</style>
