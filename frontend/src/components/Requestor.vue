<template>
  <div>
    <div style="background-color: #E9E9E9; top: 57px; position: fixed; bottom: 0px; left: 0px; width: 100%"></div>
    <div style="position: absolute; left: 0px; top: 57px; background-color: #E9E9E9; width: 100%; height: 550px">
      <div style="position: absolute; left: 50%; margin-left: -550px; top: 20px;">
        <div style="position: absolute; width: 5px; height:30px; background-color:#108cee; left:0px;"></div>
        <span style="position: absolute; left: 10px; width: 200px; font-size: 20px; text-align: left">个人信息</span>
      </div>
      <div style="position: absolute; top: 60px; height: 220px; width: 1100px; left: 50%; margin-left: -550px; background-color: white">
        <img :src="headImage" style="width: 150px; position: absolute; left: 20px; top: 15px;">
        <el-button icon="el-icon-edit-outline" type="text" style="position: absolute; left: 85px;top: 170px;font-size: 15px" @click="dialogVisible = true">修改头像</el-button>
        <span style="position: absolute; left: 210px; top: 25px; font-size: 28px;">123</span>

      </div>
      <!--<div style="position: absolute; top: 300px;width: 1100px; left: 50%; margin-left: -550px;">-->
        <!--<el-menu :default-active="default_index" class="el-menu-demo" mode="horizontal" @select="handleSelect">-->
          <!--<el-menu-item index="1" style="width: 250px">我的项目</el-menu-item>-->
          <!--<el-menu-item index="2" style="width: 250px">我的数据</el-menu-item>-->
          <!--<el-menu-item index="3" style="width: 250px">我的资料</el-menu-item>-->
        <!--</el-menu>-->
      <!--</div>-->
    </div>
    <!--<div v-show="project_show" style="position: absolute; top: 400px;width: 100%; left: 0; top: 418px;background-color:white">-->
      <!--<div style="position: absolute; top: 0px;width: 1100px; left: 50%; margin-left: -550px; background-color: white;">-->
        <!--<div style="position: absolute;top: 0;left: 0; width: 100%; height:370px; background-color: white"></div>-->
        <!--<div>-->
          <!--<el-cascader style="position: absolute; left: 30px; top: 10px;" placeholder="全部项目"-->
                       <!--expand-trigger="hover"-->
                       <!--:options="labelTypeOptions"-->
                       <!--v-model="selectLabelTypeOption"-->
                       <!--@change="handleLabelTypeChange">-->
          <!--</el-cascader>-->
        <!--</div>-->
        <!--<div style="position: absolute; top: 50px; left: 0px; padding-left: 20px; background-color:white">-->
          <!--<div v-for="item in projectInfo" style="float: left;background-color:white" :key="item">-->
            <!--<el-card v-show="item.show" class="el_card" :body-style="{padding:'0px'}">-->
              <!--<el-tag v-show="item.isContinue" style="position: absolute; left: 20px; top: 10px; color:white; width: 70px " color="#4CAF50">进行中...</el-tag>-->
              <!--<el-tag v-show="item.isEnd" style="position: absolute; left: 20px; top: 10px; color:white;width: 60px " color="#E97506">已截止</el-tag>-->
              <!--<img v-bind:src="item.cover" class="image" v-bind:id="item.missionname">-->
              <!--<div style="height: 130px;position:absolute; top: 150px; width: 97%; left: 1.5%; background-color: white">-->
                <!--<span style="font-size: 20px; color: #4CAF50">{{item.missionname}}</span>-->
                <!--<span class="time" style="position: absolute; left: 5px;top: 30px;font-size: 14px; color:black">时间：{{item.dateStart}} - {{item.dateEnd}}</span>-->
                <!--<span style="position: absolute; left: 5px;top: 52px;font-size: 14px;">类型：{{item.type}}</span>-->
                <!--<span style="position: absolute; left: 140px;top: 52px;font-size: 14px;">积分：{{item.counts}}</span>-->
                <!--<el-button style="position: absolute; left: 50px;top: 75px;font-size: 14px;"  type="text" class="card_button" @click="goProjectDetails(item.id)">项目详情</el-button>-->
                <!--<el-button style="position: absolute; left: 150px;top: 75px;font-size: 14px;" type="text" class="card_button" @click="downloadData(item.id,0)">数据下载</el-button>-->
              <!--</div>-->
              <!--<div class="imgOnClick" >-->
                <!--<img  src="../assets/img_1.png" style="width: 96%; position: absolute; left: 1.5%;top: 0px; height: 145px">-->
                <!--<p style="color:#ffffff;position: absolute; top: 10px; left: 6%; width: 88%;text-align: justify; font-size: 14px">{{item.details}}</p>-->
              <!--</div>-->
            <!--</el-card>-->
          <!--</div>-->
          <!--<div v-for="item in autoProjects" style="float: left;background-color:white" :key="item">-->
            <!--<el-card v-show="item.show" class="el_card" :body-style="{padding:'0px'}">-->
              <!--<img v-bind:src="item.cover" class="image" v-bind:id="item.missionname">-->
              <!--<div style="height: 130px;position:absolute; top: 150px; width: 97%; left: 1.5%; background-color: white">-->
                <!--<span style="font-size: 20px; color: #4CAF50">{{item.missionname}}</span>-->
                <!--<span style="position: absolute;left:90px; top: 28px;font-size: 15px;color:green">自动化标注</span>-->
                <!--<span style="position: absolute; left: 5px;top: 52px;font-size: 14px;">类型：{{item.type}}</span>-->
                <!--<span style="position: absolute; left: 140px;top: 52px;font-size: 14px;">积分：{{item.counts}}</span>-->

                <!--<el-button style="position: absolute; left: 150px;top: 75px;font-size: 14px;" type="text" class="card_button" @click="downloadData(item.id,1)">数据下载</el-button>-->
              <!--</div>-->
              <!--<div class="imgOnClick" >-->
                <!--<img  src="../assets/img_1.png" style="width: 96%; position: absolute; left: 1.5%;top: 0px; height: 145px">-->
                <!--<p style="color:#ffffff;position: absolute; top: 10px; left: 6%; width: 88%;text-align: justify; font-size: 14px">{{item.details}}</p>-->
              <!--</div>-->
            <!--</el-card>-->
          <!--</div>-->
        <!--</div>-->
      <!--</div>-->

    <!--</div>-->
    <!--<div v-show="data_show" style="position: absolute;width: 100%; left: 0; top: 418px;">-->
      <!--<div style="position: absolute; top: 0px;width: 1100px; left: 50%; margin-left: -550px; background-color: white; height: 300px">-->
        <!--<div style="position: absolute; left: 5%; width: 90%; top: 10px;">-->
          <!--<div style="position: relative;float:left; width: 400px; height: 250px;">-->
            <!--<span style="position: absolute; left: 0px; top: 0px; text-align: left; font-size: 16px">项目完成情况</span>-->
            <!--<div id="pie" style="position: absolute;left: 100px; top: 50px;width: 300px; height: 200px;"></div>-->
          <!--</div>-->
          <!--<div style="float:left; margin-left: 10px; width: 400px; height: 250px;">-->
            <!--<div style="position: relative;">-->
              <!--<span style="position: absolute; left: 0px; top: 0px; text-align: left; font-size: 16px">类型分布</span>-->
              <!--<div id="radar" style="position: absolute;left: 100px; top: 50px;width: 300px; height: 200px;"></div>-->
            <!--</div>-->
          <!--</div>-->
        <!--</div>-->
      <!--</div>-->
    <!--</div>-->
    <!--<div v-show="info_show" style="position: absolute; top: 400px;width: 100%; left: 0; top: 418px;">-->
      <!--<div style="position: absolute; top: 0px;width: 1100px; left: 50%; margin-left: -550px; background-color: white; height: 550px">-->
        <!--<div id="account_info" style="width: 100%; height: 171px; position: absolute; left: 0px; top: 0px;">-->
          <!--<span style="font-size: 18px;position: absolute;left: 30px; top: 20px;">账号信息</span>-->
          <!--<div style="position: absolute; left: 35px; top: 70px">-->
            <!--编号：<span style="font-size: 16px;color: #767676">{{personal_info.id}}</span>-->
          <!--</div>-->
          <!--<div style="position: absolute; left: 400px; top: 70px">-->
            <!--用户名：<span style="font-size: 16px;color: #767676">{{personal_info.username}}</span>-->
          <!--</div>-->
          <!--<div style="position: absolute; left: 765px; top: 70px">-->
            <!--昵称：<span style="font-size: 16px;color: #767676">{{personal_info.name}}</span>-->
          <!--</div>-->
          <!--<div style="position: absolute; left: 35px; top:110px">-->
            <!--身份：<span style="font-size: 16px;color: #767676">{{personal_info.identify}}</span>-->
          <!--</div>-->
          <!--<div style="width: 94%;height: 1px;background-color: #dadada;left: 3%; top: 170px;position: absolute;"></div>-->
          <!--<el-button id="edit_name" style="position: absolute; top: 30px; right: 50px" icon="el-icon-edit-outline" type="text" @click="editNameDialogShow">修改昵称</el-button>-->
        <!--</div>-->
        <!--<div id="details_info" style="width: 100%; height: 181px; position: absolute; left: 0px; top: 175px;">-->
          <!--<span style="font-size: 18px;position: absolute;left: 30px; top: 20px;">个人详情</span>-->
          <!--<div style="position: absolute; left: 35px; top: 70px;">-->
            <!--个人简述：-->
          <!--</div>-->
          <!--<p style="color: #767676;position: absolute;left: 120px; top: 70px;font-size:14px;width: 700px; text-align: left">{{personal_info.info}}</p>-->
          <!--<div style="position: absolute; left: 35px; top: 120px">-->
            <!--所在地：<span style="font-size: 16px;color: #767676">{{personal_info.province}}/{{personal_info.city}}</span>-->
          <!--</div>-->
          <!--<el-button id="edit_details" style="position: absolute; top: 30px; right: 50px" icon="el-icon-edit-outline" type="text" @click="editDetailsDialogShow">修改</el-button>-->
          <!--<div style="width: 94%;height: 1px;background-color: #dadada;left: 3%; top: 180px;position: absolute;"></div>-->
        <!--</div>-->
        <!--<div id="connect_info" style="width: 100%; height: 171px; position: absolute; left: 0px; top: 357px;">-->
          <!--<span style="font-size: 18px;position: absolute;left: 30px; top: 20px;">联系方式</span>-->
          <!--<div style="position: absolute; left: 35px; top: 70px">-->
            <!--电子邮箱：<span style="font-size: 16px;color: #767676">{{personal_info.email}}</span>-->
          <!--</div>-->
          <!--<div style="position: absolute; left: 400px; top: 70px">-->
            <!--手机号码：<span style="font-size: 16px;color: #767676">{{personal_info.phone}}</span>-->
          <!--</div>-->
          <!--<el-button id="edit_connect" style="position: absolute; top: 30px; right: 50px" icon="el-icon-edit-outline" type="text" @click="editConnectDialogShow">修改</el-button>-->
        <!--</div>-->
      <!--</div>-->
    <!--</div>-->
    <!--<div>-->
      <!--<el-dialog title="修改头像" :visible.sync="dialogVisible" width="600px;">-->
        <!--<div style="height: 350px">-->
          <!--<div class="show-info">-->
            <!--<div class="test">-->
            <!---->
            <!--</div>-->
            <!--<div style="position: absolute; left: 450px; top:100px; width: 100px; height: 100px">-->
              <!--<div class="show-preview" :style="{'width': previews.w + 'px', 'height': previews.h + 'px',  'overflow': 'hidden', 'margin': '5px'}">-->
                <!--<div :style="previews.div">-->
                  <!--<img :src="previews.url" :style="previews.img">-->
                <!--</div>-->
              <!--</div>-->
            <!--</div>-->
            <!--<label class="btn" for="upload2">选择照片</label>-->
            <!--<input type="file" id="upload2" style="position:absolute; clip:rect(0 0 0 0);" accept="image/png, image/jpeg, image/gif, image/jpg" @change="uploadImg($event, 2)">-->
            <!--<button @click="saveHeadImg('base64')" class="btn">确定</button>-->
            <!--<button class="btn" @click="cancel">取消</button>-->
          <!--</div>-->
        <!--</div>-->
      <!--</el-dialog>-->
    <!--</div>-->
    <!--<div>-->
      <!--<el-dialog title="修改昵称" :visible.sync="editNameVisible" style="width: 1200px;" >-->
        <!--<el-input style="position: absolute; left: 100px; top: 70px; width: 300px;" v-model="newName"></el-input>-->
        <!--<el-button type="primary" style="position: absolute; top: 70px; left: 440px; height: 38px;width: 80px" @click="editNameSure">确定</el-button>-->
        <!--<div style="height: 40px"></div>-->
      <!--</el-dialog>-->
      <!--<el-dialog  title="修改详情" width="650px" :visible.sync="editDetailsVisible" >-->
        <!--<span style="position: absolute; left:50px; top: 70px; width: 100px">个人简述</span>-->
        <!--<el-input style="color:#5c5c5c; font-size:12px;position: absolute; left: 150px; width: 400px; top: 70px;" type="textarea" :rows="3" v-bind:placeholder="personal_info.info" v-model="newDetailsInfo"></el-input>-->
        <!--<span style="position: absolute; left: 50px; top: 170px; width: 100px">所在地</span>-->
        <!--<el-cascader style="position: absolute; left: 150px; top: 170px; width: 400px"-->
                     <!--size="small"-->
                     <!--:options="options"-->
                     <!--v-model="selectedOptions"-->
                     <!--@change="handleLocationChange">-->
        <!--</el-cascader>-->
        <!--<el-button type="primary" style="position: absolute; top: 220px; left: 470px; height: 30px;width: 80px" @click="editDetailsSure">确定</el-button>-->
        <!--<div style="height: 170px">-->
        <!--</div>-->
      <!--</el-dialog>-->
      <!--<el-dialog title="修改联系方式" width="650px" :visible.sync="editConnectVisible" >-->
        <!--<el-form  style="position: absolute; left: 100px; width: 400px" :model="connectform"  status-icon :rules="rules2" ref="form" label-width="100px" class="demo-ruleForm" size="small">-->
          <!--<el-form-item label="电子邮箱" prop="newMail" required>-->
            <!--<el-input  v-model="connectform.newMail" auto-complete="off" size="small" style="width: 300px"></el-input>-->
          <!--</el-form-item>-->
          <!--<el-form-item label="手机号码" prop="newPhone" :label-width="formLabelWidth" required>-->
            <!--<el-input v-model="connectform.newPhone" auto-complete="off" size="small"></el-input>-->
          <!--</el-form-item>-->
        <!--</el-form>-->
        <!--<el-button type="primary" style="position: absolute; top: 180px; left: 420px; height: 30px;width: 80px" @click="editConnectSure">确认</el-button>-->
        <!--<div style="height: 140px"></div>-->
      <!--</el-dialog>-->
    <!--</div>-->
    <!--<div class="createProject">-->
      <!--<el-tooltip class="item" effect="dark" content="发布项目" placement="bottom">-->
        <!--<el-button class="createProject_Button" @click="createProject()">-->
          <!--<img class="icon2" src="../assets/icon2.png">-->
          <!--<img class="icon" src="../assets/icon.png">-->
        <!--</el-button>-->
      <!--</el-tooltip>-->
    <!--</div>-->
    <!--<a style="position: absolute; top: 0;" id="download_a" download="download">this is a download link</a>-->
  </div>
</template>

<script>
import { provinceAndCityData, regionData, provinceAndCityDataPlus, regionDataPlus, CodeToText, TextToCode }
  from 'element-china-area-data'
import echarts from 'echarts'

export default {
  name: 'requester',

  data () {
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
      }
    }
    return {
      selectLabelTypeOption: [],
      labelTypeOptions: [
        {
          value: 'all',
          label: '全部项目'
        },
        {
          value: 'man',
          label: '人工标注项目'
        },
        {
          value: 'auto',
          label: '自动化标注项目'
        }
      ],
      hasProject: false,
      user: {},
      connectform: {
        newMail: '',
        newPhone: ''
      },
      options: provinceAndCityData,
      selectedOptions: [],
      newName: '',
      newDetailsInfo: '',
      newProvince: '',
      newCity: '',
      detailDialog_Details: '',
      detailDialog_Locations: '',
      editDetailsVisible: false,
      editConnectVisible: false,
      editNameVisible: false,
      user_name_myProject: this.$store.state.user_name,
      headImage: '',
      model: false,
      modelSrc: '',
      crap: false,
      previews: {},
      dialogVisible: false,
      option: {
        img: '',
        size: 1,
        full: false,
        outputType: 'png',
        canMove: true,
        fixedBox: false,
        original: false,
        canMoveBox: true
      },
      example2: {
        img: 'http://localhost:8080/static/img/userDefault.d70d303.png',
        info: true,
        size: 1,
        outputType: 'jpeg',
        canScale: true,
        autoCrop: true,
        // 只有自动截图开启 宽度高度才生效
        autoCropWidth: 150,
        autoCropHeight: 125,
        fixed: true,
        fixedNumber: [1, 1]
      },
      cropper_info: {
        img: 'https://o90cnn3g2.qnssl.com/0C3ABE8D05322EAC3120DDB11F9D1F72.png',
        autoCrop: true,
        autoCropWidth: 200,
        autoCropHeight: 200,
        fixedBox: true
      },

      params: {
        token: '123456798',
        name: 'avatar'
      },
      headers: {
        smail: '*_~'
      },
      imgDataUrl: '',
      uploadShow: true,
      project_show: true,
      data_show: false,
      info_show: false,
      default_index: '1',
      project_info: {},
      projectInfo: [],
      autoProjects: [
        {
          id: '1',
          missionname: 'auto mission',
          type: 'Caption',
          cover: '',
          counts: 10,
          percent: 0,
          details: 'some information maybe useful',
          isAuto: 1,
          show: true
        }
      ],
      project_total: 0,
      info: {
        id: '',
        username: '',
        name: '',
        identify: '',
        email: '',
        phone: '',
        info: '',
        level: 0,
        level_d: '',
        points: 0, // 积分
        rate: 0, // 评分
        province: '',
        city: ''
      },
      personal_info: {
        id: '',
        username: '',
        name: '',
        identify: '',
        email: '',
        phone: '',
        info: '',
        level: 0,
        level_d: '',
        points: 0, // 积分
        rate: 0, // 评分
        province: '',
        city: ''
      },
      myData: {
        max: 20,
        finished: 36,
        unfinished: 14,
        caption: 10,
        detection: 16,
        segmentation: 6,
        attribute: 12,
        classification: 6,
        rate: [92.05, 85.15, 76.54, 96.87, 87.25]// 方、区、整、分、属
      },
      switchValue: {
        caption: true,
        detection: true,
        segmentation: true,
        attribute: true,
        classification: true,
        finished: true,
        unfinished: true
      },
      rules2: {
        newMail: [
          {validator: validateMail, trigger: 'blur'}
        ],
        newPhone: [
          {validator: validatePhone, trigger: 'blur'}
        ]
      }
    }
  },
  mounted () {
    var name = localStorage.getItem('username')
    var identify = localStorage.getItem('identify')

    if (identify == 'requester' && name != 'visitor' && name != 'undefined') {
      var xmlhttp = new XMLHttpRequest()
      var _this = this
      xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
          if (JSON.parse(xmlhttp.responseText) != null) {
            var item = JSON.parse(xmlhttp.responseText)
            _this.user = item
            var info2 = {
              id: item.id,
              username: item.username,
              name: item.name,
              identify: item.role,
              email: item.email,
              phone: item.phone,
              info: item.info,
              level: item.level,
              level_d: '',
              points: item.points, // 积分
              rate: item.rate, // 评分
              province: item.province,
              city: item.city,
              avatar: item.avatar
            }
            _this.info = info2
            _this.personal_info = info2
            _this.newDetailsInfo = _this.info.info
            _this.newName = _this.info.name
            _this.connectform.newMail = _this.info.email
            _this.connectform.newPhone = _this.info.phone
            _this.headImage = info2.avatar
            _this.example2.img = info2.avatar
            _this.projectInfo = []
            _this.getAllProject()
            _this.getAutoMissionFromBack()
          }
        }
      }
      let formData = new FormData()
      formData.append('username', localStorage.getItem('username'))
      xmlhttp.open('POST', 'http://localhost:8080/counts/user/getuser', true)
      xmlhttp.send(formData)

      var list = [].slice.call(document.querySelectorAll('pre code'))
      list.forEach((val, index) => {
        hljs.highlightBlock(val)
      })
    }
  },

  methods: {
    downloadData (mid, type) {
      console.log('click')
      var xmlhttp = new XMLHttpRequest()
      var _this = this
      xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
          if (xmlhttp.responseText != null) {
            var url = xmlhttp.responseText
            console.log('url ' + url)
            // window.open(url)
            _this.downloadHref = url
            document.getElementById('download_a').href = url
            document.getElementById('download_a').download = 'download.txt'
            document.getElementById('download_a').click()
          }
        }
      }
      var path = 'http://localhost:8080/counts/result/getResult'
      xmlhttp.open('POST', path, true)
      let formData = new FormData()
      formData.append('missionID', '' + mid)
      formData.append('type', '' + type)
      console.log('mid ' + mid + ' ' + type)
      xmlhttp.send(formData)
    },
    getAutoMissionFromBack () {
      var xmlhttp = new XMLHttpRequest()
      var _this = this
      xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
          if (JSON.parse(xmlhttp.responseText) != null) {
            _this.autoProjects = []
            var arrays = JSON.parse(xmlhttp.responseText)
            _this.project_total = arrays.length
            if (arrays.length != 0) {
              _this.hasProject = true
            }
            var cap = 0, seg = 0, dec = 0, attr = 0, cla = 0, fin = 0, unfin = 0
            for (var i = 0; i < arrays.length; i++) {
              _this.autoProjects.push({
                id: arrays[i].id,
                missionname: arrays[i].missionName,
                type: arrays[i].type,
                cover: '',
                counts: arrays[i].points,
                percent: 0,
                details: arrays[i].description,
                isAuto: 1,
                show: true
              })

              var type = arrays[i].type
              if (type == 'Classification') {
                cla++
              } else if (type == 'Detection') {
                dec++
              } else if (type == 'Segmentation') {
                seg++
              } else if (type == 'Caption') {
                cap++
              } else if (type == 'Attribute') {
                attr++
              }
            }

            var L = [cla, dec, seg, cap, attr]
            var m = _this.max(L)
            _this.myData.max += (parseInt('' + m / 10) + 1) * 10
            _this.project_total += arrays.length
            _this.myData.attribute += attr
            _this.myData.caption += cap
            _this.myData.classification += cla
            _this.myData.segmentation += seg
            _this.myData.detection += dec
            _this.myData.finished += fin
            _this.myData.unfinished += unfin

            for (var i = 0; i < _this.autoProjects.length; i++) {
              if (_this.autoProjects[i].cover = '') { console.log('here') }
              _this.getAutoFirstImage(_this.autoProjects[i].id, i)
            }
            _this.drawPieCharts()
            _this.drawRadarCharts()
          }
        }
      }
      let formData = new FormData()
      var str = localStorage.getItem('username')
      formData.append('username', str)
      var path = localStorage.getItem('server') + '/counts/mission/getAutoMission/requestor'
      xmlhttp.open('POST', path, true)
      xmlhttp.send(formData)
    },

    handleLabelTypeChange (val) {
      if (val[0] == 'man') {
        for (var i = 0; i < this.projectInfo.length; i++) {
          this.projectInfo[i].show = true
        }
        for (var i = 0; i < this.autoProjects.length; i++) {
          this.autoProjects[i].show = false
        }
      } else if (val[0] == 'auto') {
        for (var i = 0; i < this.projectInfo.length; i++) {
          this.projectInfo[i].show = false
        }
        for (var i = 0; i < this.autoProjects.length; i++) {
          this.autoProjects[i].show = true
        }
      } else {
        for (var i = 0; i < this.projectInfo.length; i++) {
          this.projectInfo[i].show = true
        }
        for (var i = 0; i < this.autoProjects.length; i++) {
          this.autoProjects[i].show = true
        }
      }
    },
    getAutoFirstImage (missionid, i) {
      var cover = ''
      var _this = this
      var xmlhttp = new XMLHttpRequest()
      xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
          cover = xmlhttp.responseText
          _this.autoProjects[i].cover = cover
        }
      }

      let formData = new FormData()
      var str = '' + missionid
      formData.append('missionid', str)
      formData.append('username', localStorage.getItem('username'))
      var path = localStorage.getItem('server') + '/counts/image/get/firstautoimage'
      xmlhttp.open('POST', path, true)
      xmlhttp.send(formData)
    },
    goProjectDetails (id) {
      for (var i = 0; i < this.projectInfo.length; i++) {
        if (this.projectInfo[i].id == id) {
          if (this.projectInfo[i].isAuto == 1) {
            if (this.projectInfo[i].type == 'Caption') {
              localStorage.setItem('mt', '1')
            } else {
              localStorage.setItem('mt', '0')
            }
            var p = '#/' + localStorage.getItem('username') + '/' + id + '/result'
            window.open(p)
            return
          }
        }
      }
      localStorage.setItem('missionID', id)
      var path = '#/' + localStorage.getItem('username') + '/requester/' + id + '/projectDetails'
      window.open(path)
    },
    getCoverImg (missionid, i) {
      var cover = ''
      var _this = this
      var xmlhttp = new XMLHttpRequest()
      xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
          cover = xmlhttp.responseText
          _this.projectInfo[i].cover = cover
        }
      }
      let formData = new FormData()
      var str = '' + missionid
      formData.append('missionid', str)
      var path = localStorage.getItem('server') + '/counts/mission/get/firstimage'
      xmlhttp.open('POST', path, true)
      xmlhttp.send(formData)
    },
    getAllProject () {
      var xmlhttp = new XMLHttpRequest()
      var _this = this
      xmlhttp.onreadystatechange = function () {
        if (xmlhttp.responseText != null) {
          var arrays = JSON.parse(xmlhttp.responseText)
          _this.project_total = arrays.length
          _this.projectInfo = []
          if (arrays.length != 0) {
            _this.hasProject = true
          }
          var cap = 0, seg = 0, dec = 0, attr = 0, cla = 0, fin = 0, unfin = 0
          for (var i = 0; i < arrays.length; i++) {
            _this.projectInfo.push({
              id: arrays[i].id,
              missionname: arrays[i].missionName,
              dateStart: arrays[i].begin,
              dateEnd: arrays[i].end,
              type: arrays[i].type,
              cover: '',
              counts: arrays[i].points,
              type: arrays[i].type,
              percent: 0,
              isEnd: false,
              isContinue: true,
              details: arrays[i].description,
              isAuto: 0,
              show: true

            })

            var type = arrays[i].type
            if (type == 'Classification') {
              cla++
            } else if (type == 'Detection') {
              dec++
            } else if (type == 'Segmentation') {
              seg++
            } else if (type == 'Caption') {
              cap++
            } else if (type == 'Attribute') {
              attr++
            }
          }

          var L = [cla, dec, seg, cap, attr]
          var m = _this.max(L)
          _this.myData.max = (parseInt('' + m / 10) + 1) * 10
          _this.project_total = arrays.length
          _this.myData.attribute = attr
          _this.myData.caption = cap
          _this.myData.classification = cla
          _this.myData.segmentation = seg
          _this.myData.detection = dec
          _this.myData.finished = fin
          _this.myData.unfinished = unfin

          for (var i = 0; i < _this.projectInfo.length; i++) {
            var time = _this.projectInfo[i].dateEnd.split('-')
            var end = new Date(time[0], parseInt(time[1]) - 1, parseInt(time[2]) + 1)
            var now = new Date()
            if (now <= end) {
              _this.projectInfo[i].isEnd = false
              _this.projectInfo[i].isContinue = true
            } else {
              _this.projectInfo[i].isEnd = true
              _this.projectInfo[i].isContinue = false
            }
            _this.projectInfo[i].percent = (_this.projectInfo[i].imgFinished / (_this.projectInfo[i].imgToDo + _this.projectInfo[i].imgFinished)).toFixed(2)
            _this.getCoverImg(_this.projectInfo[i].id, i)
          }
          _this.drawPieCharts()
          _this.drawRadarCharts()
        }
      }
      let formData = new FormData()
      var str = localStorage.getItem('username')
      formData.append('requestorid', str)
      var path = localStorage.getItem('server') + '/counts/mission/findmission/requestorid'
      xmlhttp.open('POST', path, true)
      xmlhttp.send(formData)
    },

    createProject () {
      var path = '/' + localStorage.getItem('username') + '/requester/newProject'
      this.$router.push({path: path})
    },

    handleLocationChange (value) {
      if (value[0] == '110000' || value[0] == '120000' || value[0] == '310000' || value[0] == '500000') {
        this.newProvince = CodeToText[value[0]]
        this.newCity = CodeToText[value[0]]
      } else {
        this.newProvince = CodeToText[value[0]]
        this.newCity = CodeToText[value[1]]
      }
    },
    editDetailsSure () {
      if (this.newDetailsInfo != '') {
        this.personal_info.info = this.newDetailsInfo
      } else {
        this.personal_info = '未填写个人简述'
      }
      if (this.newCity != '') {
        this.personal_info.province = this.newProvince
        this.personal_info.city = this.newCity

        var xmlhttp = new XMLHttpRequest()
        var _this = this
        xmlhttp.onreadystatechange = function () {
          if (JSON.parse(xmlhttp.responseText).result == true || JSON.parse(xmlhttp.responseText).result.result == true) {
            _this.openSucc('Edit name successfully.')
          }
        }
        xmlhttp.open('POST', 'http://localhost:8080/counts/user/update/userdata', true)
        xmlhttp.setRequestHeader('Content-type', 'application/json; charset=utf-8')
        var user = this.user
        user.city = this.newCity
        user.info = this.newDetailsInfo
        xmlhttp.send(JSON.stringify(user))
      }
      this.editDetailsVisible = false
    },

    editDetailsDialogShow () {
      this.editDetailsVisible = true
    },
    max (L) {
      var m = L[0]
      for (var i = 0; i < L.length; i++) {
        if (L[i] > m) {
          m = L[i]
        }
      }
      return m
    },
    editNameSure () {
      if (this.newName != '') {
        this.personal_info.name = this.newName
        this.editNameVisible = false
        var xmlhttp = new XMLHttpRequest()
        var _this = this
        xmlhttp.onreadystatechange = function () {
          if (JSON.parse(xmlhttp.responseText).result == true || JSON.parse(xmlhttp.responseText).result.result == true) {
            _this.openSucc('Edit name successfully.')
          }
        }
        xmlhttp.open('POST', 'http://localhost:8080/counts/user/update/userdata', true)
        xmlhttp.setRequestHeader('Content-type', 'application/json; charset=utf-8')
        var user = this.user
        user.name = this.newName
        xmlhttp.send(JSON.stringify(user))
      } else {

      }
    },

    editNameDialogShow () {
      this.editNameVisible = true
    },

    editConnectSure () {
      this.personal_info.email = this.connectform.newMail
      this.personal_info.phone = this.connectform.newPhone
      this.editConnectVisible = false
      var xmlhttp = new XMLHttpRequest()
      var _this = this
      xmlhttp.onreadystatechange = function () {
        if (JSON.parse(xmlhttp.responseText).result == true || JSON.parse(xmlhttp.responseText).result.result == true) {
          _this.openSucc('Edit name successfully.')
        }
      }
      xmlhttp.open('POST', 'http://localhost:8080/counts/user/update/userdata', true)
      xmlhttp.setRequestHeader('Content-type', 'application/json; charset=utf-8')
      var user = this.user
      user.email = this.connectform.newMail
      user.phone = this.connectform.newPhone
      xmlhttp.send(JSON.stringify(user))
    },

    editConnectDialogShow () {
      this.editConnectVisible = true
    },

    cancel () {
      this.dialogVisible = false
    },
    // 实时预览函数
    realTime (data) {
      this.previews = data
    },

    saveHeadImg (type) {
      this.$refs.cropper2.getCropData((data) => {
        this.headImage = data
        var xmlhttp = new XMLHttpRequest()
        var _this = this
        xmlhttp.onreadystatechange = function () {
          if (JSON.parse(xmlhttp.responseText).result == true || JSON.parse(xmlhttp.responseText).result.result == true) {
            _this.openSucc('Edit name successfully.')
          }
        }
        xmlhttp.open('POST', 'http://localhost:8080/counts/user/update/userdata', true)
        xmlhttp.setRequestHeader('Content-type', 'application/json; charset=utf-8')
        var user = this.user
        user.avatar = this.headImage
        console.log('headimg' + this.headImage)
        xmlhttp.send(JSON.stringify(user))
      })

      this.dialogVisible = false
    },
    uploadImg (e, num) {
      // 上传图片
      // this.option.img
      var file = e.target.files[0]
      if (!/\.(gif|jpg|jpeg|png|bmp|GIF|JPG|PNG)$/.test(e.target.value)) {
        alert('图片类型必须是.gif,jpeg,jpg,png,bmp中的一种')
        return false
      }
      var reader = new FileReader()
      reader.onload = (e) => {
        let data
        if (typeof e.target.result === 'object') {
          // 把Array Buffer转化为blob 如果是base64不需要
          data = window.URL.createObjectURL(new Blob([e.target.result]))
        } else {
          data = e.target.result
        }
        if (num === 1) {
          this.option.img = data
        } else if (num === 2) {
          this.example2.img = data
        }
      }
      // 转化为base64
      // reader.readAsDataURL(file)
      // 转化为blob
      reader.readAsArrayBuffer(file)
    },
    imgLoad (msg) {
      console.log(msg)
    },
    allCancel () {
      this.switchValue.caption = false
      this.switchValue.detection = false
      this.switchValue.segmentation = false
      this.switchValue.attribute = false
      this.switchValue.classification = false
      this.switchValue.finished = false
      this.switchValue.unfinished = false
    },
    allSelected () {
      this.switchValue.caption = true
      this.switchValue.detection = true
      this.switchValue.segmentation = true
      this.switchValue.attribute = true
      this.switchValue.classification = true
      this.switchValue.finished = true
      this.switchValue.unfinished = true
    },
    handleSelect (key, keyPath) {
      if (key == 1) {
        this.project_show = true
        this.data_show = false
        this.info_show = false
      } else if (key == 2) {
        this.project_show = false
        this.data_show = true
        this.info_show = false
      } else if (key == 3) {
        this.project_show = false
        this.data_show = false
        this.info_show = true
      }
    },
    openProject (item) {
      localStorage.setItem('missionType', item.type)
      localStorage.setItem('missionID', item.id)
      var path = '/' + localStorage.getItem('username')
      this.$router.push({path: path + '/worker'})
    },

    drawPieCharts () {
      let myChart = echarts.init(document.getElementById('pie'))
      myChart.setOption({
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
          orient: 'vertical',
          x: 'left',
          data: ['已完成', '未完成']
        },
        color: [ '#248ce4', '#e97506'],
        series: [{
          name: '完成情况',
          type: 'pie',
          radius: '60%',
          center: ['50%', '60%'],
          data: [
            {value: this.myData.finished, name: '已完成'},
            {value: this.myData.unfinished, name: '未完成'}
          ]
        }
        ]
      })
    },
    drawRadarCharts () {
      let myChart = echarts.init(document.getElementById('radar'))
      myChart.setOption({
        tooltip: {
          trigger: 'item'
        },
        color: '#248CE4',
        lineStyle: {
          color: '#220de4'
        },
        polar: [
          {
            indicator: [
              {text: '方框标注', max: this.myData.max},
              {text: '区域标注', max: this.myData.max},
              {text: '整体描述', max: this.myData.max},
              {text: '图像分类', max: this.myData.max},
              {text: '属性标注', max: this.myData.max}
            ],
            radius: '70%',
            center: ['50%', '55%']
          }
        ],
        series: [
          {
            name: '',
            type: 'radar',
            itemStyle: {
              normal: {
                areaStyle: {
                  type: 'default'
                }
              }
            },
            data: [
              {
                value: [this.myData.detection, this.myData.segmentation, this.myData.caption,
                  this.myData.classification, this.myData.attribute],
                name: '类型分布'

              }
            ]
          }
        ]
      })
    }
  }
}

</script>

<style scoped>
  .imgOnClick {
    width: 100%;
    height: 60%;
    filter: alpha(Opacity=50);
    -moz-opacity: 0.6;
    opacity: 0.6;
    z-index: 100;
    background-color: #ffffff;
    display: none;
    position: absolute;
    left: 0px;
    top:5px;
  }

  .el_card{
    margin: 10px;
    padding: 5px;
    width: 260px;
    height:245px;
    float: left;
    position: relative;
  }
  .el_card:hover .imgOnClick{
    display:block;
  }
  .el_card:hover{

  }
  .time {
    left: 10px;
    font-size: 14px;
    color: #999;
  }
  .card_button{
    padding: 0px;
    float: right;
  }
  .image{
    width: 100%;
    display: block;
  }
  * {
    margin: 0;
    padding: 0;
  }

  .btn {
    display: inline-block;
    line-height: 1;
    white-space: nowrap;
    cursor: pointer;
    background: #fff;
    border: 1px solid #c0ccda;
    color: #1f2d3d;
    text-align: center;
    box-sizing: border-box;
    outline: none;
    margin:20px 10px 0px 0px;
    padding: 9px 15px;
    font-size: 14px;
    border-radius: 4px;
    color: #fff;
    background-color: #50bfff;
    border-color: #50bfff;
    transition: all .2s ease;
    text-decoration: none;
    user-select: none;
  }

  .show-info {
    margin-bottom: 50px;
  }

  .test {
    height: 300px;
    width: 400px;
  }

  .model img {
    display: block;
    margin: auto;
    max-width: 80%;
    user-select: none;
    background-position: 0px 0px, 10px 10px;
    background-size: 20px 20px;
    background-image: linear-gradient(45deg, #eee 25%, transparent 25%, transparent 75%, #eee 75%, #eee 100%),linear-gradient(45deg, #eee 25%, white 25%, white 75%, #eee 75%, #eee 100%);
  }

  @keyframes slide {
    0%  {
      background-position: 0 0;
    }
    100% {
      background-position: -100% 0;
    }
  }

  #edit_name{
    display: none;
  }

  #account_info:hover #edit_name{
    display: block;
  }
  #edit_details{
    display: none;
  }

  #details_info:hover #edit_details{
    display: block;
  }
  #edit_connect{
    display: none;
  }

  #connect_info:hover #edit_connect{
    display: block;
  }
  .createProject{
    border-radius: 100%;
    position: fixed;
    right: 20px;
    top: 365px;
    height: 50px;
    background-color: white;
    width: 50px;
  }
  .icon{
    visibility: inherit;
    width: 50px;
    position: absolute;
    top:-1px;
    left: -1px;
  }
  .icon2{
    visibility: inherit;
    width: 50px;
    position: absolute;
    top:-1px;
    left: -1px;
  }
  .createProject_Button{
    position: absolute;
    border-radius: 100%;
    left: 0px;
    top: 0px;
    width: 50px;
    height: 50px;
  }
  .myProject{
    float: right;
    background-color: #e9e9e9;
  }
  .nullProject{
    position: absolute;
    top: 300px;
    width: 70%;
    left: 0px;
  }
  .show_or_hide_div_button{
    border-radius: 0px;
    float: right;
    font-size: 20px;
    width: 20px;
    height: 500px;
  // background-color: #dfdfdf;
    top: 300px;
  }
  .project_detail{
    right: 0px;
    position: fixed;
    float: right;
    width: 600px;
    height: 550px;
    background-color: #e9e9e9;
  }
</style>
