<template>
  <div>
    <div style="position: absolute; left: 50%; top: 70px; margin-left: -350px">
      <span style="position: absolute; left: 0px; width: 70px; top: 10px">创建课程</span>
      <el-steps simple align-center style="width: 600px; position: absolute; left: 70px;background-color: white" :active="active" finish-status="success">
        <el-step icon="el-icon-info" style="margin-left: 40px;" title="基本信息"></el-step>
        <el-step icon="el-icon-edit" title="个性订制"></el-step>
        <el-step icon="el-icon-upload" title="课件上传"></el-step>
      </el-steps>
    </div>
    <div v-show="firstStep" style="position: absolute; top: 100px;width: 1000px; margin-left: -500px;left: 50%">
      <div class="form_div">
        <div style="position: absolute; left: 32px; top: -10px;">
          <span style="position: absolute; left: 0px;padding-top: 5px; font-size: 14px;width: 70px; text-align: left">学期选择</span>

          <el-select v-model="ruleForm.term" placeholder="请选择" style="left: 70px">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>


        </div>
        <div v-show="isAutoLabel">
          <span style="position: absolute; left: 32px;top: 40px;font-size: 14px">课程标题</span>
          <el-input style="position: absolute; left: 100px;top: 40px;font-size: 14px;width: 390px" v-model="autoForm.missionName" ></el-input>
          <span style="position: absolute; left: 32px;top: 92px;font-size: 14px">任务详情</span>
          <el-input style="position: absolute; left: 100px;top: 92px;font-size: 14px;width: 390px" type="textarea" :rows="5" placeholder="输入任务的具体要求" v-model="autoForm.contents"></el-input>
          <span style="position: absolute; left: 32px;top: 240px;font-size: 14px">授课方式</span>
          <el-select @change="labelChange2" style="position: absolute; left:100px;top: 240px;" v-model="autoForm.type" placeholder="选择标注方式">
            <div v-for="item in options" :key="item.value">
              <el-option :label="item.label" :value="item.value" :disabled="item.disabled"></el-option>
            </div>
          </el-select>
          <el-button style="position: absolute; left:350px;top: 233px;" @click="labelDetailsShow" class="label_info_button" icon="el-icon-info" type="text"></el-button>
          <el-button style="position: absolute; left:360px;top: 233px;" type="text"  v-show="isClassBtnShow" @click="handleClassBtn">添加分类</el-button>

        </div>
        <el-form v-show="!isAutoLabel" class="form" style="position:absolute;top: 50px" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px">
          <el-form-item label="课程标题" prop="topic" required>
            <el-input v-model="ruleForm.topic" ></el-input>
          </el-form-item>
          <el-form-item label="授课地点" prop="location" required>
            <el-input type="textarea" :rows="2" placeholder="输入任务的具体要求" v-model="ruleForm.location"></el-input>
          </el-form-item>
          <el-form-item label="课程时间" required>
            <el-col :span="11">
              <el-form-item prop="dateStart" required>
                <el-date-picker value-format="yyyy-MM-dd" type="date" placeholder="起始日期" v-model="ruleForm.dateStart" style="width: 100%;"></el-date-picker>
              </el-form-item>
            </el-col>
            <el-col class="line" :span="2">-</el-col>
            <el-col :span="11">
              <el-form-item prop="dateEnd" required>
                <el-date-picker value-format="yyyy-MM-dd" type="date" placeholder="截止日期" v-model="ruleForm.dateEnd" style="width: 100%;"></el-date-picker>
              </el-form-item>
            </el-col>
          </el-form-item>
          <el-form-item label="授课时间" prop="time" required>
            <el-input v-model="ruleForm.time" ></el-input>

          </el-form-item>
          <div style="position: absolute; top:220px; left: 360px;">
            <el-button type="text"  v-show="isClassBtnShow" @click="handleClassBtn">添加分类</el-button>
            <el-button type="text" v-show="isAttrBtnShow" @click="handleAttrBtn">添加属性</el-button>
          </div>
        </el-form>
        <router-view></router-view>
      </div>
    </div>

    <div v-show="secondStep" style="position: absolute; top: 100px;width: 1000px; margin-left: -500px;left: 50%">
      <span style="font-size: 13px; width: 400px; position: absolute; left: 250px; top: 30px; text-align: left">授课人数</span>
      <el-input-number size="mini" style="position: absolute; left: 315px; top: 25px;" v-model="ruleForm.workerNumber" :min="20" :max="100"></el-input-number>
      <span style="font-size: 13px; width: 400px; position: absolute; left: 460px; top: 30px; text-align: left">可得学分</span>
      <el-input-number size="mini" style="position: absolute; left: 520px; top: 25px;" v-model="ruleForm.workerPoints" :min="1" :max="5"></el-input-number>

      <div>
        <span style="font-size: 13px; width: 400px; position: absolute; left: 250px; top: 70px; text-align: left">添加标签 <span style="font-size: 11px"> 准确的标签能让你的项目找到更适合的工人</span></span>
        <tag-selector style="position: absolute; left: 250px; top: 95px;"
                      v-bind:refresh-hot-tag="newHotTag" v-bind:candidate-label-list="hotTagList"
                      v-bind:selected-labels-list="selectedTagList" selected-div-width="440" selected-div-height="110">
        </tag-selector>
      </div>
    </div>

    <div v-show="thirdStep" style="position: absolute; top: 100px;width: 1000px; margin-left: -500px;left: 50%">
      <div style="position: absolute; left: 250px; top: 20px;" class="project_open">
        <div class="upload_wrap" @drop="drop($event)" @dragenter="dragenter($event)" @dragover="dragover($event)">
          <div style="position: absolute; left: 100px; top: 230px;">点击上传文件或将文件拖拽到此处</div>
        </div>
        <div>
          <img style="position: absolute; left: 180px; top: 90px; width: 90px;cursor: pointer;" @click="fileClick"
               @drop="drop($event)" @dragenter="dragenter($event)" @dragover="dragover($event)"
               src="../assets/upload.png">
        </div>
        <div class="upload_wrap_text">
          共{{imgList.length}}张课件，{{bytesToSize(this.size)}}
        </div>
        <el-button style="position: absolute; top:300px; left: 360px;" type="text" @click="imgClear()">清除全部课件</el-button>
        <input @change="fileChange($event)" type="file" id="upload_file" multiple style="display: none"/>
      </div>
    </div>

    <div style="position: absolute; top: 480px; width: 600px; left: 57% ;margin-left: -330px">
      <button v-bind:disabled="isCancelBtnDisabled" @click="handleCancelButton" id="cancelBtn" class="stepBtn">取消</button>
      <button v-bind:disabled="isBeforeBtnDisabled"  @click="handleBeforeBtn" class="stepBtn">上一步</button>
      <button v-bind:disabled="isNextBtnDisabled" @click="handleNextBtn" class="stepBtn">下一步</button>
      <button v-bind:disabled="isSureBtnDisabled" @click="submitCourse" id="sureBtn" class="stepBtn">确定</button>
    </div>



    <el-dialog title="填写所有的分类" :visible.sync="classDialogVisible" width="600px">
      <el-form style="height: 220px; ">
        <div v-for="item in classArray" :key="item" >
          <el-input style="width: 120px; float:left; margin: 5px" v-model="item.value"></el-input>
        </div>
        <el-button @click="addClass" type="text" icon="el-icon-circle-plus-outline" style="float: left; font-size: 24px" size="medium"></el-button>
        <el-button @click="classDialog_sureBtn" type="primary" style="font-size: 12px;position: absolute; right: 40px; bottom: 10px;">确定</el-button>
        <span style="color: red; position: absolute;left: 30px; bottom: 10px">最多可以填写10个分类</span>
      </el-form>
    </el-dialog>
    <el-dialog title="填写所有的属性" :visible.sync="attrDialogVisible" width="600px">
      <el-form style="height: 220px; ">
        <div v-for="item in attrArray" :key="item" >
          <el-input style="width: 120px; float:left; margin: 5px" v-model="item.value"></el-input>
        </div>
        <el-button @click="addAttribute" type="text" icon="el-icon-circle-plus-outline" style="float: left; font-size: 24px" size="medium"></el-button>
        <el-button @click="attrDialog_sureBtn" type="primary" style="font-size: 12px;position: absolute; right: 40px; bottom: 10px;">确定</el-button>
        <span  style="color: red; position: absolute;left: 30px; bottom: 10px">最多可以填写10个属性</span>
      </el-form>
    </el-dialog>
    <div v-show="isLabelDetailsShow" style="position: absolute; right: 10px; top: 70px; width: 300px; height: 340px; color: grey;">
      <span style="font-size: 20px;position:absolute; top: 5px; left: 110px;">标注详情</span>
      <span style="font-size: 16px; position: absolute; top: 40px; left: 12px; color: #4CAF50">整体描述</span>
      <p style="font-size: 14px; position: absolute; top: 50px; left: 12px; text-align: left">针对图像中具体情景及标注需求，为每张图像生成中文描述语句。</p>
      <span style="font-size: 16px; position: absolute; top: 105px; left: 12px;color: #4CAF50">方框标注</span>
      <p style="font-size: 14px; position: absolute; top: 115px; left: 12px; text-align: left">根据标注需求，对图像中的目标物体进行画框</p>
      <span style="font-size: 16px; position: absolute; top: 150px; left: 12px;color: #4CAF50">区域标注</span>
      <p style="font-size: 14px; position: absolute; top: 160px; left: 12px; text-align: left">根据标注需求，使⽤连续的折线标注图像中目标物体的轮廓，返回所有轮廓组成点的坐标。</p>
      <span style="font-size: 16px; position: absolute; top: 215px; left: 12px;color: #4CAF50">图像分类</span>
      <p style="font-size: 14px; position: absolute; top: 225px; left: 12px; text-align: left">根据图像内容，选择给出选项中合适的一项。</p>
      <span style="font-size: 16px; position: absolute; top: 260px; left: 12px;color: #4CAF50">属性标注</span>
      <p style="font-size: 14px; position: absolute; top: 270px; left: 12px; text-align: left">根据图像内容和给定的属性，填写属性值。</p>
    </div>

    <div v-show="doAutoLabel">
      <div class="modal" style="display: block;">  </div>
      <my-loading style="position: absolute;left: 50%; margin-left: -82px;top: 180px;" type="rainbow"></my-loading>
      <span style="position: absolute;left: 50%; margin-left: -60px;top: 310px;font-size: 18px" >正在执行自动化标注</span>
      <span style="position: absolute;left: 50%; margin-left: -75px;top: 335px;font-size: 12px;color: #747474" >执行完成后将会发送邮件至您的邮箱</span>
      <el-button type="primary" size="small" style="position: absolute;left: 50%;margin-left: -35px;top: 358px;font-size: 16px" @click="backStageRun">后台执行</el-button>
    </div>

  </div>
</template>

<script>
  import JSZip from 'jszip'
  import TagSelector from './tagSelector/tagSelector'
  import MyLoading from './myLoading/myLoading'
  export default {
    components: {TagSelector, MyLoading},
    name: 'NewProject',
    watch: {
      active(){
        if(this.active==0){
          this.firstStep=true;
          this.secondStep =false;
          this.thirdStep = false;
        }
        if(this.active==1){
          this.firstStep=false;
          this.secondStep =true;
          this.thirdStep = false;
        }
        if(this.active==2){
          this.firstStep=false;
          this.secondStep =false;
          this.thirdStep = true;
        }
      }
    },
    data () {
      var validateTopic = (rule, value, callback) => {
        var len = 0;
        for (var i=0; i<value.length; i++) {
          if (value.charCodeAt(i)>127 || value.charCodeAt(i)==94) {
            len += 2;
          } else {
            len ++;
          }
        }
        if(len>20){
          callback(new Error('项目标题的长度不能超过10个汉字或20个字母'))
        }
        if(len==0){
          callback(new Error('标题不能为空'))
        }
      }
      return {

        value: '',


        autoForm:{
          type: '',
          contents: '',
          missionName: '',
          points: 10,
        },
        doAutoLabel:false,
        isAutoLabel:false,
        hotTagList:['dog', 'cat', 'mouse', 'horse', 'caption', 'detection', 'animal', 'car',
          'fruit', 'vegetable'],
        selectedTagList:[],
        firstStep:true,
        secondStep:false,
        thirdStep:false,
        isCancelBtnDisabled: false,
        isNextBtnDisabled: false,
        isBeforeBtnDisabled: true,
        isSureBtnDisabled: true,

        active:0,
        rules: {
          topic: [
            {validator: validateTopic, trigger: 'blur'}
          ],
        },
        isLabelDetailsShow: false,
        isClassBtnShow: false,
        isAttrBtnShow: false,
        isWarnShow: false,
        classDialogVisible: false,
        attrDialogVisible: false,
        classNumber: 4,
        classValue: '',
        selectsArray: [],
        classArray: [{
          value: ''
        }],
        attrNumber: 4,
        attrValue: '',
        attrArray: [{
          value: ''
        }],
        imgList: [],
        imgFileList: [],
        size: 0,
        ruleForm: {
          time:'',
          topic: '',
          location: '',
          term: '',
          dateStart: '',
          dateEnd: '',
          workerNumber: 20,
          workerPoints: 5
        },
        options: [
          {value: '19 SPRING', label: '19 SPRING',}, {value: '19 SUMMER', label: '19 SUMMER',}, {value: '19 FALL', label: '19 FALL',}, {value: '19 WINTER', label: '19 WINTER',},
          {value: '20 SPRING', label: '20 SPRING',}, {value: '20 SUMMER', label: '20 SUMMER',}, {value: '20 FALL', label: '20 FALL',}, {value: '20 WINTER', label: '20 WINTER',},
          {value: '21 SPRING', label: '21 SPRING',}, {value: '21 SUMMER', label: '21 SUMMER',}, {value: '21 FALL', label: '21 FALL',}, {value: '21 WINTER', label: '21 WINTER',},
          {value: '22 SPRING', label: '22 SPRING',}, {value: '22 SUMMER', label: '22 SUMMER',}, {value: '22 FALL', label: '22 FALL',}, {value: '22 WINTER', label: '22 WINTER',},
        ],
        value: ''
      }
    },
    created () {
      for (var i = 1; i < this.classNumber; i++) {
        var m = {
          value: this.classValue
        }
        this.classArray.push(m)
      }
      for (var i = 1; i < this.attrNumber; i++) {
        var m = {
          value: this.attrValue
        }
        this.attrArray.push(m)
      }
    },
    methods: {

      submitCourse(){



        this.$emit("gotoMainPage",false);
        var xmlhttp = new XMLHttpRequest()
        var _this = this
          xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

              var course = JSON.parse(xmlhttp.responseText);

              console.log(course.id)
              var xmlhttp2 = new XMLHttpRequest();

              xmlhttp2.onreadystatechange = function () {
                if (xmlhttp2.readyState == 4 && xmlhttp2.status == 200) {

                }
              }

              let formData = new FormData()
              for (var i = 0; i < _this.imgFileList.length; i++) {
                formData.append('multipartFiles', _this.imgFileList[i])
              }
              formData.append('courseid', course.id)
              xmlhttp2.open('POST', 'http://localhost:8080/j2ee/file/upload', true)
              xmlhttp2.send(formData)
            }
          }

        var course ={
          name:this.ruleForm.topic,
          teachername:localStorage.getItem("username"),
          classroom:this.ruleForm.location,
          term:this.ruleForm.term,
          starttime:this.ruleForm.dateStart,
          endtime:this.ruleForm.dateEnd,
          studentnum:this.ruleForm.workerNumber,
          creditnum:this.ruleForm.workerPoints,
          time:this.ruleForm.time

        }

        xmlhttp.open('POST', 'http://localhost:8080/j2ee/course/create', true)
        xmlhttp.setRequestHeader('Content-type', 'application/json; charset=utf-8')
        console.log(JSON.stringify(course))
        xmlhttp.send(JSON.stringify(course))


      },
      backStageRun(){
        this.doAutoLabel=false;
        this.goMyProject()
      },
      selectManLabel(){
        for(var i=0;i<this.options.length;i++){
          this.options[i].disabled=false;
        }
        this.isAutoLabel=false;
        document.getElementById('manLabel').style.backgroundColor = '#1d86ff'
        document.getElementById('manLabel').style.color = 'white'
        document.getElementById('manLabel').style.border='0'
        document.getElementById('autoLabel').style.backgroundColor = 'white'
        document.getElementById('autoLabel').style.color = 'black'
        document.getElementById('autoLabel').style.border='1px solid #999'
      },
      selectAutoLabel(){
        this.isAutoLabel=true;
        this.options[2].disabled=true;
        this.options[4].disabled=true;
        document.getElementById('autoLabel').style.backgroundColor = '#1d86ff'
        document.getElementById('autoLabel').style.color = 'white'
        document.getElementById('autoLabel').style.border='0'
        document.getElementById('manLabel').style.backgroundColor = 'white'
        document.getElementById('manLabel').style.color = 'black'
        document.getElementById('manLabel').style.border='1px solid #999'
      },
      handleBeforeBtn(){
        this.isNextBtnDisabled=false;
        if(this.active>0){
          this.active--;
        }
        if(this.isAutoLabel==true)
          this.active=0
        if(this.active==0){
          this.isBeforeBtnDisabled=true
        }
      },
      newHotTag () {
        var preList = ['dog', 'cat', 'mouse', 'horse', 'caption', 'detection', 'animal', 'car',
          'fruit', 'vegetable', 'phone', 'medicine','women','star','actor','actress','Asian','cartoon','comic',
          '二次元','演员','区域标注','跑车','赛车','饮料瓶','computer','计算机','鼠标','apple','bird']
        this.hotTagList=[]
        while(true){
          if(this.hotTagList.length<11){
            var index = this.random_Num(0,preList.length);
            var test = true;
            for(var j=0;j<this.hotTagList.length;j++){
              if(preList[index]==this.hotTagList[j]){
                test = false;
              }
            }
            if(test == true){
              this.hotTagList.push(preList[index])
            }
          }else{
            return;
          }
        }
        //this.hotTagList.push('new hot tag')
      },
      handleNextBtn(){
        console.log("kk"+JSON.stringify(this.ruleForm))
        this.isBeforeBtnDisabled=false;

        if(this.active<3){
          this.active++;
        }
        if(this.isAutoLabel==true)
          this.active=2;
        if(this.active==2){
          this.isSureBtnDisabled=false;
          this.isNextBtnDisabled=true
        }
      },
      labelDetailsShow () {
        this.isLabelDetailsShow = !this.isLabelDetailsShow

      },
      handleClassBtn () {
        this.classDialogVisible = true
      },
      handleAttrBtn () {
        this.attrDialogVisible = true
      },
      labelChange () {
        if (this.ruleForm.type == 'Classification') {
          this.isClassBtnShow = true
          this.isAttrBtnShow = false
          this.classDialogVisible = true
        } else if (this.ruleForm.type == 'Attribute') {
          this.isClassBtnShow = false
          this.isAttrBtnShow = true
          this.attrDialogVisible = true
        } else {
          this.isClassBtnShow = false
          this.isAttrBtnShow = false
          this.classDialogVisible = false
          this.attrDialogVisible = false
        }
      },
      labelChange2 () {
        if (this.autoForm.type == 'Classification') {
          this.isClassBtnShow = true
          this.isAttrBtnShow = false
          this.classDialogVisible = true
        } else {
          this.isClassBtnShow = false
          this.isAttrBtnShow = false
          this.classDialogVisible = false
          this.attrDialogVisible = false
        }
      },
      addClass () {
        if (this.classNumber < 10) {
          this.classNumber++
          this.classArray.push({value: ''})
        } else {
          this.isWarnShow = true
        }
      },
      addAttribute () {
        if (this.attrNumber < 10) {
          this.attrNumber++
          this.attrArray.push({value: ''})
        } else {
          this.isWarnShow = true
        }
      },
      attrDialog_sureBtn () {
        this.attrDialogVisible = false
      },
      classDialog_sureBtn () {
        this.classDialogVisible = false
      },

      openSucc (text) {
        this.$notify({
          title: '成功',
          message: text,
          type: 'success',
          duration: 2000,
          position: 'top-left'
        })
      },
      openInfo (text) {
        this.$notify({
          title: '消息',
          message: text,
          type: 'info',
          duration: 2000,
          position: 'top-left'
        })
      },
      openWarn (text) {
        this.$notify({
          title: '警告',
          message: text,
          type: 'warning',
          duration: 2000,
          position: 'top-left'
        })
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
        this.$router.push({path: path + '/requester'})
      },
      goMyProject () {
        var path = '/' + localStorage.getItem('username')
        this.$router.push({path: path + '/requester'})
        window.location.reload()
      },
      submitForm(){
        if(this.isAutoLabel){
          this.submitAutoForm()
        }else{
          this.submitManForm()
        }
      },

      submitAutoForm(){
        if (this.imgFileList.length == 0) {
          this.openWarn('您尚未上传任何课件。')
          return;
        }
        if (this.autoForm.type == 'Classification') {
          for (var i = 0; i < this.classArray.length; i++) {
            if (this.LTrim(this.RTrim(this.classArray[i].value)) != '') {
              this.selectsArray.push(this.LTrim(this.RTrim(this.classArray[i].value)))
            }
          }
        }
        var xmlhttp = new XMLHttpRequest()
        var _this = this
        var username = localStorage.getItem('username')
        var missionID = 0
        var type = _this.autoForm.type;
        xmlhttp.onreadystatechange = function () {
          if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            console.log('missionID'+xmlhttp.responseText)
            missionID = xmlhttp.responseText
            if(type=='Detection'){
              _this.uploadAutoDectionImg(missionID)
            }else if(type=='Caption'){
              _this.uploadAutoCaptionImg(missionID)
            }else if(type == 'Classification'){
              _this.uploadAutoClassificationImg(missionID)
            }
            _this.openSucc('Submit successfully')
            _this.goMyProject()
          }
        }
        var autoMission = {
          requestorID: username,
          missionName: _this.autoForm.missionName,
          description: _this.autoForm.contents,
          type: _this.autoForm.type,
          points: _this.autoForm.points,
          types: [],
          size: _this.imgFileList.length
        }
        if (autoMission.type == 'Classification') {
          autoMission.types =  _this.selectsArray
        }
       // console.log(this.selectsArray)
        xmlhttp.open('POST', 'http://localhost:8080/counts/mission/addAutoMission', true)
        xmlhttp.setRequestHeader('Content-type', 'application/json; charset=utf-8')
       // console.log(JSON.stringify(autoMission))
        xmlhttp.send(JSON.stringify(autoMission));
      },

      submitManForm () {
        if (this.ruleForm.type == 'Classification') {
          for (var i = 0; i < this.classArray.length; i++) {
            if (this.LTrim(this.RTrim(this.classArray[i].value)) != '') {
              this.selectsArray.push(this.LTrim(this.RTrim(this.classArray[i].value)))
            }
          }
        } else if (this.ruleForm.type == 'Attribute') {
          for (var i = 0; i < this.attrArray.length; i++) {
            if (this.LTrim(this.RTrim(this.attrArray[i].value)) != '') {
              this.selectsArray.push(this.LTrim(this.RTrim(this.attrArray[i].value)))
            }
          }
        }
        var isValid = true

        if (this.imgFileList.length == 0) {
          this.openWarn('您尚未上传任何课件。')
          isValid = false
        }
        if (isValid == true) {
          var xmlhttp = new XMLHttpRequest()
          var _this = this
          var username = localStorage.getItem('username')
          var missionID = 0
          xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
              missionID = xmlhttp.responseText
              _this.uploadImg(missionID)
              _this.openSucc('Submit successfully')
              _this.goMyProject()
            }
          }
          let formData = new FormData()
          var mission = {
            requestorID: username,
            missionName: _this.ruleForm.topic,
            description: _this.ruleForm.contents,
            begin: _this.ruleForm.dateStart,
            end: _this.ruleForm.dateEnd,
            type: _this.ruleForm.type,
            points: _this.ruleForm.workerPoints,
            maxNumber: _this.ruleForm.workerNumber,
            selects: [],
            tags: _this.selectedTagList,
            sum:_this.imgFileList.length
          }
          if (mission.type == 'Classification' || mission.type == 'Attribute') {
            mission.selects =  _this.selectsArray
          }
          xmlhttp.open('POST', 'http://localhost:8080/counts/image/addmission', true)
          xmlhttp.setRequestHeader('Content-type', 'application/json; charset=utf-8')
          xmlhttp.send(JSON.stringify(mission))
        }
      },

      uploadAutoCaptionImg(missionID){
        var xmlhttp = new XMLHttpRequest()
        var _this = this
        if (missionID != 0) {
          xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
              console.log(missionID)
            }
          }
          let formData = new FormData()
          var str = '' + missionID
          formData.append('missionID', str)
          for (var i = 0; i < this.imgFileList.length; i++) {
            formData.append('file', _this.imgFileList[i])
          }
          console.log('imglist'+this.imgFileList)
          xmlhttp.open('POST', 'http://localhost:8080/counts/image/uploadAutoCaptionImg', true)
          xmlhttp.send(formData)
        }
      },
      uploadAutoClassificationImg(missionID){
        var xmlhttp = new XMLHttpRequest()
        var _this = this
        if (missionID != 0) {
          xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
              console.log(missionID)
            }
          }
          let formData = new FormData()
          var str = '' + missionID
          formData.append('missionID', str)
          for (var i = 0; i < this.imgFileList.length; i++) {
            formData.append('file', _this.imgFileList[i])
          }
          xmlhttp.open('POST', 'http://localhost:8080/counts/image/uploadAutoClassificationImg', true)
          xmlhttp.send(formData)
        }
      },
      uploadAutoDectionImg(missionID){
        var xmlhttp = new XMLHttpRequest()
        var _this = this
        if (missionID != 0) {
          xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
              console.log(missionID)
            }
          }
          let formData = new FormData()
          var str = '' + missionID
          formData.append('missionID', str)
          for (var i = 0; i < this.imgFileList.length; i++) {
            formData.append('file', _this.imgFileList[i])
          }
          //console.log("uploadSetection")
          xmlhttp.open('POST', 'http://localhost:8080/counts/image/uploadAutoDetectionImg', false)
          xmlhttp.send(formData)
        }
      },
      uploadImg (missionID) {
        var xmlhttp = new XMLHttpRequest()
        var _this = this
        if (missionID != 0) {
          xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
              console.log(missionID)
            }
          }
          let formData = new FormData()
          var str = '' + missionID
          formData.append('mission', str)
          for (var i = 0; i < this.imgFileList.length; i++) {
            formData.append('file', _this.imgFileList[i])
          }
          xmlhttp.open('POST', 'http://localhost:8080/counts/image/uploadImg', true)
          xmlhttp.send(formData)
        }
      },

      resetForm (formName) {
        console.log(this.ruleForm.dateStart)
        this.ruleForm.workerNumber = 20
        this.ruleForm.workerPoints = 5
        this.isClassBtnShow = false
        this.isAttrBtnShow = false
        this.$refs[formName].resetFields()
        this.imgList = []
        this.imgFileList = []
        this.size = 0
        this.openSucc('重置成功')
      },

      imgClear () {
        if (this.imgList.length == 0) {
          this.openInfo('您尚未上传任何课件')
          return
        }
        this.$confirm('确定要清除所有的课件吗？', 'Confirm', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.imgList = []
          this.imgFileList = []
          this.size = 0
          this.openSucc('课件清除成功')
        })
      },
      fileClick () {
        document.getElementById('upload_file').click()
      },
      fileChange (el) {
        if (!el.target.files[0].size) return
        this.fileList(el.target)
        el.target.value = ''
      },
      fileList (fileList) {
        let files = fileList.files
        for (let i = 0; i < files.length; i++) {
          // 判断是否为文件夹
          if (files[i].type != '') {
            this.fileAdd(files[i])
          } else {
            // 文件夹处理
            this.folders(fileList.items[i])
          }
        }
      },
      folders (files) {
        let _this = this
        // 判断是否为原生file
        if (files.kind) {
          files = files.webkitGetAsEntry()
        }
        files.createReader().readEntries(function (file) {
          for (let i = 0; i < file.length; i++) {
            if (file[i].isFile) {
              _this.foldersAdd(file[i])
            } else {
              _this.folders(file[i])
            }
          }
        })
      },
      foldersAdd (entry) {
        let _this = this
        entry.file(function (file) {
          _this.fileAdd(file)
        })
      },
      fileDel (index) {
        this.$confirm('确认要删除该课件吗？', 'Confirm', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.size = this.size - this.imgList[index].file.size// 总大小
          this.imgList.splice(index, 1)
          this.imgFileList.splice(index, 1)
          this.openSucc('删除成功')
        })
      },
      fileAdd (file) {
        if (this.limit !== undefined) this.limit--
        if (this.limit !== undefined && this.limit < 0) return

        var _this = this;
        console.log('file '+file.type)

          this.size = this.size + file.size
          this.imgFileList.push(file)
          let reader = new FileReader()
          let image = new Image()
          reader.readAsDataURL(file)

        _this.imgList.push({
          file
        })

      },
      _arrayBufferToBase64(buffer) {
        var binary = '';
        var bytes = new Uint8Array(buffer);
        var len = bytes.byteLength;
        for (var i = 0; i < len; i++) {
          binary += String.fromCharCode(bytes[i]);
        }
        return window.btoa(binary);
      },
      bytesToSize (bytes) {
        if (bytes === 0) return '0 B'
        let k = 1000, // or 1024
          sizes = ['B', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'],
          i = Math.floor(Math.log(bytes) / Math.log(k))
        return (bytes / Math.pow(k, i)).toPrecision(3) + ' ' + sizes[i]
      },
      dragenter (el) {
        el.stopPropagation()
        el.preventDefault()
      },
      dragover (el) {
        el.stopPropagation()
        el.preventDefault()
      },
      drop (el) {
        el.stopPropagation()
        el.preventDefault()
        this.fileList(el.dataTransfer)
      }
    }
  }
</script>

<style scoped>
  .label_info_button {
    color: #9c9c9c;
    position: absolute;
    left: 240px;
    top: 0px;
  }
  .label_info_button:hover{
    color:grey;
  }
  .form_div{
    top: 40px;
    position: absolute;
    width: 700px;
    left: 50%;
    margin-left: -300px;
  }
  .form{
    position: absolute;
    width: 500px;
  }
  .upload_wrap{
    position: absolute;
    top: 0px;
    width: 450px;
    left: 0px;
    height: 300px;
    border: 1px solid #a8a8a8;
    color: #999;
  }
  .upload_wrap_text {
    width: 400px;
    text-align: left;
    position: absolute;
    left:5px;
    top:310px;
    font-size: 14px;
  }

  .upload_warp_left {

    cursor: pointer;
  }
  .upload_warp_img_div_del {
    width: 18px;
    float: left;
  }

  .happy_scroll{
    position: absolute;
    top: 30px;
    left: 0px;
    width:100%;
  }
  .upload_warp_img_div_top {
    top: 0;
    width: 100%;
    height: 30px;
    background-color: rgba(101, 83, 108, 0.4);
    line-height: 28px;
    text-align: left;
    color: #fff;
    font-size: 12px;
    text-indent: 10px;
  }
  .upload_warp_img_div_text {
    white-space: nowrap;
    width: 92%;
    overflow: hidden;
    text-overflow: ellipsis;
    float: left;
  }
  .stepBtn{
    border-radius: 3px;
    height: 25px;
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
  .modal {
    position: fixed;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    background-color: white;
    filter:alpha(opacity:80);
    opacity:0.8;
    -moz-opacity:0.8;
  }
</style>
