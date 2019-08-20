<template>
  <div>
    <body>


    <div
      v-cloak
      ref="auiWrapper"
      class="aui-wrapper"
      :class="[
      'aui-header--' + headerSkin,
      'aui-aside--' + asideSkin,
      {
        'aui-wrapper--center': wrapperCenter,
        'aui-header--fixed': headerFixed,
        'aui-aside--fixed': asideFixed,
        'aui-aside--fold': asideFold,
        'aui-aside--top': asideTop,
        'aui-control--fixed': controlFixed,
        'aui-control--open': controlOpen,
        'aui-main-tabs__header--fixed': mainTabsHeaderFixed
      }
    ]"
      v-loading.fullscreen.lock="loading"
      element-loading-text="拼命加载中"
    >
      <template v-if="!loading">
        <header class="aui-header">
          <div class="aui-header__hd">
            <a class="aui-brand aui-brand--lg" href="javascript:;">MY-COURSE</a>
            <a class="aui-brand aui-brand--sm" href="javascript:;">M-C</a>
          </div>
          <div class="aui-header__bd">
            <!-- aui-header__menu left -->
            <el-menu class="aui-header__menu aui-header__menu--left" mode="horizontal">
              <el-menu-item v-if="!asideTop" index="1" @click="asideFold = !asideFold">
                <svg class="icon-svg aui-header__icon-menu aui-header__icon-menu--rz180" aria-hidden="true"><use xlink:href="#icon-outdent"></use></svg>
              </el-menu-item>
            </el-menu>
            <!-- aui-header__menu right -->
            <el-menu class="aui-header__menu aui-header__menu--right" mode="horizontal">


              <el-menu-item>
                <template slot="title">
                  <img :src="user.avator" class="aui-avatar" >
                  <span>{{user.name}}</span>
                </template>
              </el-menu-item>
              <el-menu-item index="5">
                <svg class="icon-svg aui-header__icon-menu" aria-hidden="true"><use xlink:href= "#icon-delete"></use></svg>
              </el-menu-item>

              <el-menu-item index="6">
                <a href="./#/login">
                  <svg class="icon-svg aui-header__icon-menu" aria-hidden="true"><use xlink:href= "#icon-poweroff"></use></svg>
                </a>
              </el-menu-item>


              <el-menu-item index="1" @click="initSetting">
                <svg class="icon-svg aui-header__icon-menu" aria-hidden="true"><use xlink:href="#icon-setting"></use></svg>
              </el-menu-item>
            </el-menu>
            <!-- aui-search -->
            <div class="aui-search" :class="{ 'aui-search--show': searchVisible }">
              <div class="aui-search__inner">
                <el-input class="aui-search__input" v-model="search" placeholder="搜索关键字" clearable>
                  <svg slot="prepend" class="icon-svg" aria-hidden="true"><use xlink:href="#icon-search"></use></svg>
                </el-input>
              </div>
            </div>
          </div>
        </header>
        <!-- aui-aside -->
        <aside class="aui-aside">
          <div class="aui-aside__inner">
            <el-menu
              v-if="asideMenuVisible"
              class="aui-aside__menu"
              :default-active="asideMenuActive"
              :collapse="asideFold"
              :unique-opened="true"
              :collapse-transition="false"
              :mode="asideTop ? 'horizontal' : 'vertical'">
              <el-menu-item index="home" @click="showMainPage">
                <svg class="icon-svg aui-aside__menu-icon" aria-hidden="true"><use xlink:href="#icon-home"></use></svg>
                <span slot="title">首页</span>
              </el-menu-item>

              <el-menu-item index="icon" @click="showCreateCourse">
                <svg class="icon-svg aui-aside__menu-icon" aria-hidden="true"><use xlink:href="#icon-gift"></use></svg>
                <span slot="title">创建课程</span>
              </el-menu-item>

              <el-menu-item index="basic" @click="showPublishCourse">
                <svg class="icon-svg aui-aside__menu-icon" aria-hidden="true"><use xlink:href="#icon-fire"></use></svg>
                <span slot="title">发布课程</span>
              </el-menu-item>

              <el-menu-item index="chart" @click="showCount">
                <svg class="icon-svg aui-aside__menu-icon" aria-hidden="true"><use xlink:href="#icon-barchart"></use></svg>
                <span slot="title">统计信息</span>
              </el-menu-item>

              <!-- <el-submenu index="form" :popper-append-to-body="false">
                <template slot="title">
                  <svg class="icon-svg aui-aside__menu-icon" aria-hidden="true"><use xlink:href="#icon-file-text"></use></svg>
                  <span>表单页面</span>
                </template>
                <el-menu-item index="form-basic">
                  <svg class="icon-svg aui-aside__menu-icon" aria-hidden="true"><use xlink:href="#icon-fire"></use></svg>
                  <span>基础表单</span>
                </el-menu-item>
              </el-submenu>

              <el-submenu index="list" :popper-append-to-body="false">
                <template slot="title">
                  <svg class="icon-svg aui-aside__menu-icon" aria-hidden="true"><use xlink:href="#icon-unorderedlist"></use></svg>
                  <span>列表页面</span>
                </template>
                <el-menu-item index="list-basic">
                  <svg class="icon-svg aui-aside__menu-icon" aria-hidden="true"><use xlink:href="#icon-fire"></use></svg>
                  <span>基础列表</span>
                </el-menu-item>
              </el-submenu> -->

              <!-- <el-submenu index="detail" :popper-append-to-body="false">
                <template slot="title">
                  <svg class="icon-svg aui-aside__menu-icon" aria-hidden="true"><use xlink:href="#icon-detail"></use></svg>
                  <span>详情页面</span>
                </template>
                <el-menu-item index="detail-basic">
                  <svg class="icon-svg aui-aside__menu-icon" aria-hidden="true"><use xlink:href="#icon-fire"></use></svg>
                  <span>基础详情</span>
                </el-menu-item>
              </el-submenu>

              <el-submenu index="error" :popper-append-to-body="false">
                <template slot="title">
                  <svg class="icon-svg aui-aside__menu-icon" aria-hidden="true"><use xlink:href="#icon-error"></use></svg>
                  <span>异常页面</span>
                </template>
                <el-menu-item index="error-403">
                  <svg class="icon-svg aui-aside__menu-icon" aria-hidden="true"><use xlink:href="#icon-fire"></use></svg>
                  <span>403</span>
                </el-menu-item>
                <el-menu-item index="error-404">
                  <svg class="icon-svg aui-aside__menu-icon" aria-hidden="true"><use xlink:href="#icon-fire"></use></svg>
                  <span>404</span>
                </el-menu-item>
                <el-menu-item index="error-505">
                  <svg class="icon-svg aui-aside__menu-icon" aria-hidden="true"><use xlink:href="#icon-fire"></use></svg>
                  <span>505</span>
                </el-menu-item>
              </el-submenu> -->
            </el-menu>
          </div>
        </aside>
        <!-- aui-control -->
        <aside class="aui-control">
          <div v-if="!headerFixed && controlFixed" class="aui-control__toggle" @click="initSetting">
            <svg class="icon-svg" aria-hidden="true"><use xlink:href="#icon-setting"></use></svg>
          </div>
          <div class="aui-control__inner">
            <div class="aui-control__bd">
              <el-tabs class="aui-tabs aui-tabs--flex" v-model="controlTabsActive">
                <el-tab-pane label="setting" name="setting">

                  <dl class="aui-control__setting">

                    <dd><el-switch
                      style="display: block"
                      v-model="asideTop"
                      active-color="#13ce66"
                      inactive-color="#ff4949"
                      active-text="宽屏模式"
                    >
                    </el-switch></dd>
                  </dl>
                  <dl class="aui-control__setting">

                    <dd><el-switch
                      style="display: block"
                      v-model="controlFixed"
                      :disabled="wrapperCenter"
                      active-color="#13ce66"
                      inactive-color="#ff4949"
                      active-text="固定标签"
                    >
                    </el-switch></dd>
                  </dl>

                  <!--这是空白，免得上面的滑块出现溢出-->
                  <dl class="aui-control__setting">
                  </dl>

                  <dl class="aui-control__setting">

                    <dd>邮箱</dd>
                    <dd>
                      <el-input v-model="modify.email" placeholder="password" class="set_input_width" ></el-input>
                    </dd>
                    <dd>密码</dd>
                    <dd>
                      <el-input v-model="modify.password" placeholder="mail" class="set_input_width" ></el-input>
                    </dd>

                    <dd>
                      <div >
                        <VimageClip
                          width="140px"
                          height="140px"
                          backgroundColor="#ff6633"
                          :hd=false
                          :control="true"
                          :buttonValue='{ clipText: "裁剪", clipClass: "el-button",resetText: "重置", resetClass:"el-button"}'
                          @imageClipper="handleclip"

                        />
                      </div>
                    </dd>

                  </dl>
                  <dl class="aui-control__setting">
                  </dl>
                  <dl class="aui-control__setting">

                    <dd>
                      <div style="text-align: center;padding-right:40px">
                        <el-button @click="submitChange">提交</el-button>
                      </div>
                    </dd>

                  </dl>


                </el-tab-pane>

              </el-tabs>
            </div>
          </div>
        </aside>


        <!-- aui-main -->
        <main class="aui-main" v-show="main_show">
          <!-- standard 标准 -->
          <div v-show="mainType === 'standard'" class="aui-main__hd">
            <el-breadcrumb separator="/">
              <el-breadcrumb-item>my-course</el-breadcrumb-item>
              <el-breadcrumb-item>首页</el-breadcrumb-item>
            </el-breadcrumb>
          </div>
          <div v-show="mainType === 'standard'" class="aui-main__bd">
            <div class="aui-page page-index">
              <main class="aui-page-main">
                <el-row :gutter="15">
                  <el-col :span="16">
                    <!-- 安全预警、安全防护、待办事项 -->
                    <div class="index-section">
                      <el-row :gutter="15">


                        <el-col :span="24">
                          <div class="block">
                            <span class="demonstration">讲座预告</span>
                            <el-carousel height="150px">
                              <el-carousel-item>
                                <!--<h3>{{ item }}</h3>-->
                                <img style="width:100%;height:100%" src="../assets/lecture1.jpg">
                              </el-carousel-item>
                              <el-carousel-item>
                                <!--<h3>{{ item }}</h3>-->
                                <img style="width:100%;height:100%" src="../assets/lecture2.jpg">
                              </el-carousel-item>
                              <el-carousel-item>
                                <!--<h3>{{ item }}</h3>-->
                                <img style="width:100%;height:100%" src="../assets/lecture3.jpg">
                              </el-carousel-item>

                            </el-carousel>
                          </div>

                        </el-col>

                      </el-row>
                    </div>
                    <!-- 最近使用的产品 -->
                    <div class="index-section">
                      <div class="aui-panel index-recent">
                        <div class="aui-panel__hd clearfix">
                          <span>我的课程</span>


                        </div>
                        <!--已选课程-->
                        <el-collapse v-model="activeNames" @change="handleChange">

                          <div  v-for="(array,key) of personalCenterInfo.courses" :key="key">
                            <el-collapse-item v-bind:title="key" v-bind:name="key">
                              <div class="aui-panel__bd">
                                <el-row :gutter="15">
                                  <div  v-for="item in array" >
                                    <el-col :span="20">
                                      <a class="index-block index-block--level">
                                        <div class="index-block__bd" @click="gotoCourseDetail(item.id)" >{{item.name}}&nbsp;&nbsp;&nbsp;&nbsp;主讲人:{{item.teachername}}</div>
                                      </a>
                                    </el-col>
                                  </div>
                                </el-row>
                              </div>
                            </el-collapse-item>
                          </div>


                        </el-collapse>

                      </div>
                    </div>
                    <!-- 我的资源、我的消费、推荐返利 -->

                  </el-col>
                  <el-col :span="8">
                    <!-- 公告 -->

                    <!-- 管理助手 -->
                    <div class="index-section">
                      <div class="aui-panel index-assistant">
                        <div class="aui-panel__hd clearfix">
                          <span>最近讨论</span>

                        </div>
                        <div class="aui-panel__bd">
                          <div v-for="item in personalCenterInfo.topics.slice(0,3)">
                            <div class="index-assistant__item">

                              <div class="index-assistant__item-hd">
                                <img :src="item.avator" class="course_cover">
                              </div>
                              <div class="index-assistant__item-bd">
                                <h4 class="index-assistant__item-title">【主题】：{{item.title}}</h4>
                                <p class="index-assistant__item-intro">【发帖人】：{{item.userName}}</p>
                                <el-button type="primary" plain size="mini">参与讨论</el-button>
                              </div>
                            </div>
                          </div>

                        </div>
                      </div>
                    </div>




                  </el-col>
                </el-row>
              </main>
            </div>
          </div>
          <!-- tabs 标签页 -->
          <el-dropdown v-show="mainType === 'tabs'" class="aui-main-tabs__tool">
            <i class="el-icon-arrow-down"></i>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>关闭当前标签页</el-dropdown-item>
              <el-dropdown-item>关闭其它标签页</el-dropdown-item>
              <el-dropdown-item>关闭全部标签页</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
          <el-tabs v-show="mainType === 'tabs'" class="aui-main-tabs aui-tabs" v-model="mainTabsActive">
            <el-tab-pane label="home" name="home" :closable="false" class="aui-main-tabs__pane--full">
              <svg slot="label" class="aui-content--tabs-icon-nav icon-svg" aria-hidden="true"><use xlink:href="#icon-home"></use></svg>
              <!-- <include src="./templates/pages/index.html" path="."></include> -->
            </el-tab-pane>
            <el-tab-pane label="标签页2" name="2" closable>标签页2</el-tab-pane>
            <el-tab-pane label="标签页3" name="3" closable>标签页3</el-tab-pane>
            <el-tab-pane label="标签页5" name="5" closable>
              <span slot="label"><i class="el-icon-date"></i> 标签页5</span>
            </el-tab-pane>
            <el-tab-pane label="标签页6" name="6" closable>标签页6</el-tab-pane>
            <el-tab-pane label="标签页7" name="7" closable>标签页7</el-tab-pane>
            <el-tab-pane label="标签页8" name="8" closable>标签页8</el-tab-pane>
            <el-tab-pane label="标签页9" name="9" closable>标签页9</el-tab-pane>
            <el-tab-pane label="标签页10" name="10" closable>标签页10</el-tab-pane>
          </el-tabs>
        </main>

        <main class="aui-main" v-show="createCourse_show">

          <newProject v-on:gotoMainPage="gotoMainPage"></newProject>
        </main>

        <main class="aui-main" v-show="publishCourse_show">
          <newpublish></newpublish>
        </main>

        <main class="aui-main" v-show="count_show">

          <div id="userPercent" style="width:400px; height:410px; left: 200px"></div>
          <div id="topicType" style="width:400px; height:410px; left: 200px"></div>
          <div id="broadcastPercent" style="width:400px; height:410px; left: 200px"></div>



        </main>
        <!-- aui-footer -->
        <footer class="aui-footer">
          <p><a>MyCourse</a>j2ee XGY</p>
        </footer>

      </template>
    </div>
    </body>
  </div>
</template>

<script>
  import '../icons/iconfont.js'
  import VimageClip from 'vue-imgclip'

import newProject from '@/components/NewProject'
import newpublish from '@/components/newpublish'
  import echarts from 'echarts'
  export default {

    name: "bench",
    components: {
      VimageClip,
      newProject,
      newpublish
    },

    data () {
      return {


        count_show:false,
        user:{},


        lectureList:[
          "../assets/lecture1.jpg",
          "../assets/lecture2.png",
          "../assets/lecture3.png",
          "../assets/lecture.png",
        ],
        allpublishedCourse:{},

        personalCenterInfo:{},

        currentAddCourseId:'',
        modify:{
          username:'',
          password:'',
          email:'',
          avator:'',
        },

        main_show:true,
        createCourse_show:false,
        publishCourse_show:false,

        course_num:{
          course_ing:0,
          course_ed:1,
          course_future:3
        },
        broadcast0:[{title:"成绩出来啦"}],
        broadcast1:[{title:"实验出来啦"}],
        broadcast2:[{title:"作业出来啦"}],
        broadcast3:[{title:"其他其他其他"}],

        ensureAddCourse:false,
        addCourseVisible:false,
        moreScoreVisible:false,
        moreExperimentVisible:false,
        moreHomeworkVisible:false,
        moreOtherVisible:false,

        // 滚动条, 滚动高度
        scrollbarHeight: 0,
        // DOM Element对象
        DOM: {},
        // 加载中
        loading: true,
        // 容器, 居中
        wrapperCenter: false,
        // 头部, 皮肤 (white 白色 / colorful 鲜艳)
        headerSkin: 'colorful',
        // 头部, 固定状态
        headerFixed: false,
        // 侧边, 皮肤 (white 白色 / dark 黑色)
        asideSkin: 'dark',
        // 侧边, 固定状态
        asideFixed: false,
        // 侧边, 折叠状态
        asideFold: false,
        // 侧边, 至头部状态
        asideTop: false,
        // 侧边, 菜单显示状态 (控制台“至头部”操作时, el-menu组件需根据mode属性重新渲染)
        asideMenuVisible: true,
        // 侧边, 菜单选中
        asideMenuActive: 'home',
        // 搜索, 显示状态
        searchVisible: false,
        // 搜索, 值
        search: '',
        // 控制台, 固定状态
        controlFixed: false,
        // 控制台, 打开状态
        controlOpen: true,
        // 控制台, 标签页选中
        controlTabsActive: 'setting',
        // 主内容, 展示类型 (standard 标准 / tabs 标签页)
        mainType: 'standard',
        // 主标签页, 列表
        mainTabs: [],
        // 主标签页, 选中
        mainTabsActive: 'home',
        // 主标签页, 头部固定状态
        mainTabsHeaderFixed: false,
        // 皮肤, 默认值
        skin: 'orange',
        // 皮肤, 列表
        skinList: [
          { name: 'blue'     , color: '#3E8EF7', remark: '蓝色' },
          { name: 'brown'    , color: '#997B71', remark: '棕色' },
          { name: 'cyan'     , color: '#0BB2D4', remark: '青色' },
          { name: 'gray'     , color: '#757575', remark: '灰色' },
          { name: 'green'    , color: '#11C26D', remark: '绿色' },
          { name: 'indigo'   , color: '#667AFA', remark: '靛青色' },
          { name: 'orange'   , color: '#EB6709', remark: '橙色' },
          { name: 'pink'     , color: '#F74584', remark: '粉红色' },
          { name: 'purple'   , color: '#9463F7', remark: '紫色' },
          { name: 'red'      , color: '#FF4C52', remark: '红色' },
          { name: 'turquoise', color: '#17B3A3', remark: '蓝绿色' },
          { name: 'yellow'   , color: '#FCB900', remark: '黄色' }
        ],
        indexNoticeTabsActive: '1',
        indexPersonalTabsActive: '1'
      }
    },
    watch: {
      scrollbarHeight: function (val) {
        this.yLayoutFixedHandle();
      },
      wrapperCenter: function (val) {
        this.headerFixed = false;
        this.asideFixed = false;
        this.controlFixed = false;
        this.mainTabsHeaderFixed = false;
      },
      headerFixed: function (val) {
        this.yLayoutFixedHandle();
      },
      asideFixed: function (val) {
        this.yLayoutFixedHandle();
      },
      asideTop: function (val) {
        this.yLayoutFixedHandle();
        this.asideMenuVisible = false;
        this.$nextTick(function () {
          this.asideMenuVisible = true;
        });
      },
      controlFixed: function (val) {
        this.yLayoutFixedHandle();
      },
      mainTabsHeaderFixed: function (val) {
        this.yLayoutFixedHandle();
      }
    },
    beforeCreate () {
      vm = this;
    },
    created () {
      this.user = JSON.parse(localStorage.getItem("user"))
      this.modify.password = this.user.password
      this.modify.username = this.user.name
      this.modify.email = this.user.email

      this.modify.avator = this.user.avator
      console.log(this.user.name)

      var xmlhttp = new XMLHttpRequest();

      var _this = this
      xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
          _this.personalCenterInfo = JSON.parse(xmlhttp.responseText);
        }
      }

      let formData = new FormData()
      formData.append('name', this.user.name)
      xmlhttp.open('POST', 'http://localhost:8080/j2ee/personalcenter/getInfo', true)
      xmlhttp.send(formData)

      this.loading = false;
      this.$nextTick(function () {
        this.scrollbarHandle(true);
        this.getDOM();
        this.searchHandle();
      });
    },
    methods: {

      drawUserPercent(){


        var myChart = echarts.init(document.getElementById('userPercent'))

        var xmlhttp = new XMLHttpRequest();

        xmlhttp.onreadystatechange = function () {
          if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

            var data = JSON.parse(xmlhttp.responseText)

            data = Math.floor(data)
            var option = {
              tooltip : {
                formatter: "{a} <br/>{b} : {c}%"
              },

              series: [
                {
                  name: '业务指标',
                  type: 'gauge',
                  detail: {formatter:'{value}%'},
                  data: [{value: data, name: '学生比例'}]
                }
              ]
            };
            myChart.setOption(option)
          }
        }

        xmlhttp.open('POST', 'http://localhost:8080/j2ee/chart/get/studentpercent', true)
        xmlhttp.send()

      },

      drawTopicType(){
        var myChart = echarts.init(document.getElementById('topicType'))

        var option = {
          tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
          },
          legend: {
            orient: 'vertical',
            x: 'left',
            data:['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
          },
          series: [
            {
              name:'访问来源',
              type:'pie',
              radius: ['50%', '70%'],
              avoidLabelOverlap: false,
              label: {
                normal: {
                  show: false,
                  position: 'center'
                },
                emphasis: {
                  show: true,
                  textStyle: {
                    fontSize: '30',
                    fontWeight: 'bold'
                  }
                }
              },
              labelLine: {
                normal: {
                  show: false
                }
              },
              data:[
                {value:335, name:'直接访问'},
                {value:310, name:'邮件营销'},
                {value:234, name:'联盟广告'},
                {value:135, name:'视频广告'},
                {value:1548, name:'搜索引擎'}
              ]
            }
          ]
        };

        var xmlhttp = new XMLHttpRequest();

        xmlhttp.onreadystatechange = function () {
          if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

            var data = JSON.parse(xmlhttp.responseText)
            var option = {
              tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b}: {c} ({d}%)"
              },
              legend: {
                orient: 'vertical',
                x: 'left',
                data:['分享','精华','提问','实验']
              },
              series: [
                {
                  name:'帖子分类',
                  type:'pie',
                  radius: ['50%', '70%'],
                  avoidLabelOverlap: false,
                  label: {
                    normal: {
                      show: false,
                      position: 'center'
                    },
                    emphasis: {
                      show: true,
                      textStyle: {
                        fontSize: '30',
                        fontWeight: 'bold'
                      }
                    }
                  },
                  labelLine: {
                    normal: {
                      show: false
                    }
                  },
                  data:data
                }
              ]
            };
            myChart.setOption(option)
          }
        }

        xmlhttp.open('POST', 'http://localhost:8080/j2ee/chart/get/topicpercent/all', true)
        xmlhttp.send()


      },

      drawBroadcastPercent(){


        var myChart = echarts.init(document.getElementById('broadcastPercent'))


        var xmlhttp = new XMLHttpRequest();

        xmlhttp.onreadystatechange = function () {
          if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

            var data = JSON.parse(xmlhttp.responseText)

            var max=0;
            for (var i = 0; i < data.length; i++) {

              if(max<data[i]) max= data[i]
            }

            var option = {
              title: {
                text: '公告比重雷达图'
              },
              tooltip: {},
              legend: {
                data: ['预算分配（Allocated Budget）', '实际开销（Actual Spending）']
              },
              radar: {
                // shape: 'circle',
                name: {
                  textStyle: {
                    color: '#fff',
                    backgroundColor: '#999',
                    borderRadius: 3,
                    padding: [3, 5]
                  }
                },
                indicator: [
                  { name: '成绩', max: max},
                  { name: '作业', max: max},
                  { name: '实验', max: max},
                  { name: '其他', max: max},


                ]
              },
              series: [{
                name: '预算 vs 开销（Budget vs spending）',
                type: 'radar',
                areaStyle: {normal: {}},
                data : [

                  {
                    value : data,
                    name : '公告占比'
                  }
                ]
              }]
            };

            myChart.setOption(option)
          }
        }

        xmlhttp.open('POST', 'http://localhost:8080/j2ee/chart/get/broadcastpercent/all', true)
        xmlhttp.send()



      },

      showCount(){
        this.count_show=true
        this.main_show=false
        this.createCourse_show=false
        this.publishCourse_show=false
        this.drawBroadcastPercent()
        this.drawTopicType()
        this.drawUserPercent()
      },

      showPublishCourse(){
        this.main_show=false;
        this.createCourse_show=false;
        this.publishCourse_show= true;
        this.count_show=false
      },
      showMainPage(){
        this.main_show= true;
        this.createCourse_show=false;
        this.publishCourse_show=false;
        this.count_show=false
      },

      showCreateCourse(){
        this.main_show=false
        this.createCourse_show=true
        this.publishCourse_show=false
        this.count_show=false
      },
      gotoMainPage:function(value){

        this.main_show = true;
        this.createCourse_show=false;
      },
      //添加选中确定的课程到数据库
      saveAddCourse(){
        this.ensureAddCourse = false;



      },
      gotoCourseDetail(id){

        localStorage.setItem("courseid",id);
        if(localStorage.getItem("role")==='teacher'){
          this.$router.push({path: '/CourseDetail_T'})
        }else {
          this.$router.push({path: '/publish'})
        }
      },
      getAllCourse(){
        this.addCourseVisible=true;

        var xmlhttp = new XMLHttpRequest();

        var _this = this
        xmlhttp.onreadystatechange = function () {
          if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

            _this.allpublishedCourse = JSON.parse(xmlhttp.responseText);
          }
        }

        xmlhttp.open('POST', 'http://localhost:8080/j2ee/course/publishedcourse/all', true)
        xmlhttp.send()

      },

      addCourse(id){

        this.ensureAddCourse = true;
        this.currentAddCourseId = id;
        console.log("III"+id)
      },

      // 滚动条, 滚动距离
      scrollbarHandle: function (isInit) {
        if (isInit && typeof(isInit) === 'boolean') {
          window.addEventListener('scroll', this.scrollbarHandle);
        }
        this.scrollbarHeight = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop;
      },
      // 获取DOM Element对象
      getDOM: function () {
        this.DOM.header  = this.$refs.auiWrapper.querySelector('.aui-header');
        this.DOM.aside   = this.$refs.auiWrapper.querySelector('.aui-aside');
        this.DOM.control = this.$refs.auiWrapper.querySelector('.aui-control');
        this.DOM.main    = this.$refs.auiWrapper.querySelector('.aui-main');
        this.DOM.mainTabsTool   = this.DOM.main.querySelector('.aui-main-tabs__tool');
        this.DOM.mainTabsHeader = this.DOM.main.querySelector('.aui-main-tabs > .el-tabs__header');
      },
      // Y轴布局固定
      yLayoutFixedHandle: function () {
        var _offsetHeight = this.scrollbarHeight >= this.DOM.header.offsetHeight ? 0 : this.DOM.header.offsetHeight - this.scrollbarHeight;
        // 侧边
        if (!this.headerFixed && this.asideTop && this.asideFixed) {
          this.DOM.aside.style.top = _offsetHeight + 'px';
        } else {
          this.DOM.aside.style.removeProperty('top');
        }
        // 控制台
        if (!this.headerFixed && this.controlFixed) {
          this.DOM.control.style.top = _offsetHeight + 'px';
        } else {
          this.DOM.control.style.removeProperty('top');
        }
        // 主标签页, 头部
        if (!this.headerFixed && this.mainTabsHeaderFixed) {
          if (this.asideTop && this.asideFixed) {
            _offsetHeight += this.DOM.aside.offsetHeight;
          }
          if (this.asideTop && !this.asideFixed) {
            _offsetHeight = this.scrollbarHeight >= this.DOM.header.offsetHeight + this.DOM.aside.offsetHeight ? 0 : this.DOM.header.offsetHeight + this.DOM.aside.offsetHeight - this.scrollbarHeight;
          }
          this.DOM.mainTabsTool.style.top = _offsetHeight + 'px';
          this.DOM.mainTabsHeader.style.top = _offsetHeight + 'px';
        } else {
          this.DOM.mainTabsTool.style.removeProperty('top');
          this.DOM.mainTabsHeader.style.removeProperty('top');
        }
      },
      // 搜索, 显示／隐藏
      searchHandle: function () {
        this.$refs.auiWrapper.querySelector('.aui-search__toggle').addEventListener('click', function (e) {
          e.cancelBubble = true;
        });
        this.$refs.auiWrapper.querySelector('.aui-search').addEventListener('click', function (e) {
          e.cancelBubble = true;
        });
        window.addEventListener('click', function (e) {
          if (this.searchVisible) {
            this.searchVisible = false;
          }
        });
      },
      // 跳转页面
      gotoPageHandle: function (url) {
        if (!/\S/.test(url) || location.href.indexOf(url.replace(/^(\.*\/)*/, '')) !== -1) {
          return false;
        }
        window.location.href = url;
      },
      // 皮肤切换
      skinChangeHandle: function (val) {
        var styleList = [
          {
            id: 'J_elementTheme',
            url: './element-theme/' + val + '/index.css?t=' + new Date().getTime()
          },
          {
            id: 'J_auiSKin',
            url: './css/aui-' + val + '.min.css?t=' + new Date().getTime()
          }
        ];
        for (var i = 0; i < styleList.length; i++) {
          var el = document.querySelector('#' + styleList[i].id);
          if (el) {
            el.href = styleList[i].url;
            continue;
          }
          el = document.createElement('link');
          el.id = styleList[i].id;
          el.href = styleList[i].url;
          el.rel = 'stylesheet';
          document.querySelector('head').appendChild(el);
        }
      },

      handleclip: function(data) {
        console.log("handle")
        this.modify.avator = data.dataUrl
      },

      initSetting(){
        this.controlOpen = !this.controlOpen

        this.modify.email = this.user.email;
        this.modify.username = this.user.name
        this.modify.password = this.user.password
      },

      submitChange(){


        this.user.name = this.modify.username;
        this.user.email= this.modify.email
        this.user.password = this.modify.password
        this.user.avator = this.modify.avator
        localStorage.setItem("user",JSON.stringify(this.user))
        var xmlhttp = new XMLHttpRequest()
        xmlhttp.onreadystatechange = function () {
          if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            if (JSON.parse(xmlhttp.responseText).result == true || JSON.parse(xmlhttp.responseText).result.result == true) {

            } else {
              _this.openWarn('Fail to log in')

            }
          }
        }

        xmlhttp.open('POST', 'http://localhost:8080/j2ee/user/update', true)
        xmlhttp.setRequestHeader('Content-type', 'application/json; charset=utf-8')
        xmlhttp.send(JSON.stringify(this.user))


      }
    }
  }
</script>

<style scoped>
  @import "../style/aui-orange.min.css";
  /*@import "../element-theme/orange/index.css";*/

  .set_input_width {
    width: 80%;
  }
  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 150px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }


  .course_cover {
    max-width: 100%;
    max-height: 100%;
  }

  .none_backgroud{
    background-color:rgba(255,255,255,0);
    border:none;
  }
  .index-assistant__item-hd  img{
    border-radius:50%;
    padding-top: 25px;

  }

  .moreScore{
    padding-right: 80px;
  }


  .none{


  }

</style>
