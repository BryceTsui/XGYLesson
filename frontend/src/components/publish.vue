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
              <el-menu-item index="2" @click="ensureQuitVisible=true">
                <a>
                  <svg class="icon-svg aui-header__icon-menu" aria-hidden="true"><use xlink:href="#icon-error-fill"></use></svg>
                </a>
              </el-menu-item>


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

              <el-menu-item index="basic" @click="showBBSHome">
                <svg class="icon-svg aui-aside__menu-icon" aria-hidden="true"><use xlink:href="#icon-fire"></use></svg>
                <span slot="title">讨论区</span>
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
                        <el-col :span="10">
                          <div class="aui-panel index-security-warning">
                            <div class="aui-panel__hd">提醒事项</div>
                            <el-row :gutter="10" class="aui-panel__bd">
                              <el-col :span="8">
                                <div class="index-block">
                                  <div class="index-block__bd"><em class="index-block__value">{{CourseDetail.ppt}}</em></div>
                                  <div class="index-block__hd">课件</div>
                                </div>
                              </el-col>
                              <el-col :span="8">
                                <div class="index-block">
                                  <div class="index-block__bd"><em class="index-block__value">{{CourseDetail.unfinished_homework}}</em></div>
                                  <div class="index-block__hd">未提交作业</div>
                                </div>
                              </el-col>
                              <el-col :span="8">
                                <div class="index-block">
                                  <div class="index-block__bd"><em class="index-block__value">{{CourseDetail.new_topic}}</em></div>
                                  <div class="index-block__hd">讨论区上新</div>
                                </div>
                              </el-col>
                            </el-row>
                          </div>
                        </el-col>
                        <el-col :span="7">
                          <div class="aui-panel index-security-protect">
                            <div class="aui-panel__hd">课程提醒</div>
                            <div class="aui-panel__bd">
                              <div class="index-block index-block--level">
                                <div class="index-block__hd">开课时间</div>
                                <div class="index-block__bd"><em class="index-block__value">{{CourseDetail.time}}</em></div>
                              </div>
                              <div class="index-block index-block--level">
                                <div class="index-block__hd">授课地点</div>
                                <div class="index-block__bd"><a>{{CourseDetail.location}}</a></div>
                              </div>
                            </div>
                          </div>
                        </el-col>
                        <el-col :span="7">
                          <div class="aui-panel index-undo none_backgroud">
                            <avatar class="course_cover" :username="CourseDetail.name":rounded="false":size="130"></avatar>
                          </div>
                        </el-col>
                      </el-row>
                    </div>
                    <!-- 最近使用的产品 -->
                    <div class="index-section">
                      <div class="aui-panel index-notice">
                        <div class="aui-panel__bd">
                          <el-tabs class="aui-tabs aui-tabs--flex" v-model="indexNoticeTabsActive">

                            <el-tab-pane label="成绩公告" name="1">
                              <div v-for="item in CourseDetail.broadcast0">
                                <ul class="index-notice__list" @click="showB0(item)">
                                  <li><a >【成绩】{{item.title}}</a></li>
                                </ul>
                              </div>


                            </el-tab-pane>
                            <el-tab-pane label="实验通知" name="2">
                              <div v-for="item in CourseDetail.broadcast1">
                                <ul class="index-notice__list" @click="showB1(item)">
                                  <li><a >【实验】{{item.title}}</a></li>
                                </ul>
                              </div>


                            </el-tab-pane>
                            <el-tab-pane label="作业通告" name="3">
                              <div v-for="item in CourseDetail.broadcast2">
                                <ul class="index-notice__list"@click="showB2(item)">
                                  <li><a>【作业】{{item.title}}</a></li>
                                </ul>
                              </div>

                            </el-tab-pane>
                            <el-tab-pane label="其他" name="4">
                              <div v-for="item in CourseDetail.broadcast3">
                                <ul class="index-notice__list" @click="showB3(item)">
                                  <li><a>【其他】{{item.title}}</a></li>
                                </ul>
                              </div>
                            </el-tab-pane>
                          </el-tabs>
                        </div>
                      </div>
                    </div>

                    <el-dialog
                      title="公告详情"
                      :visible.sync="broadcastDialogVisible"
                      width="50%"
                    >

                      <div>
                        <span><p v-html='currentBroadCast.content' /></span>
                      </div>
                      <span slot="footer" class="dialog-footer">
                            <el-button type="primary" @click="broadcastDialogVisible=false">确定</el-button>
                             </span>
                    </el-dialog>
                    <!-- 我的资源、我的消费、推荐返利 -->
                    <div class="index-section">
                      <div class="aui-panel index-personal">
                        <div class="aui-panel__bd">
                          <el-tabs class="aui-tabs" v-model="indexPersonalTabsActive">
                            <el-tab-pane class="index-product" label=" 课件" name="1">
                              <div>
                                <FileDownload :data="CourseDetail.resources":courseid="CourseDetail.id"></FileDownload>
                              </div>

                            </el-tab-pane>
                            <el-tab-pane class="index-consume" label="作业" name="2">
                              <div v-for="item in CourseDetail.homeworks">
                                <ul class="index-notice__list" @click="showHomeworkDetail(item)">
                                  <li><a>【作业】{{item.name}}</a></li>
                                </ul>
                              </div>

                            </el-tab-pane>

                            <el-dialog
                              title="作业详情"
                              :visible.sync="homeworkDialogVisible"
                              width="50%"
                             >

                              <span>作业内容：{{currentHomework.content}}</span>
                              <div>
                              <span>开始时间：{{currentHomework.starttime}}&nbsp;&nbsp;</span>
                              <span>结束时间：{{currentHomework.endtime}}</span>
                              </div>

                              <div>
                                <span>&nbsp;</span>
                              </div>
                              <div><span>作业列表：</span></div>

                              <el-tag
                                v-for="item in homeworkResourseList"
                                :key="item"
                                closable
                                :type="warning"
                                @close="handleClose(item)"
                              >
                                <a :href="'http://localhost:8080/j2ee/file/homework/download?studentname='+user.name+'&homeworkid='+item.homeworkid+'&name='+item.filename" > {{item.filename}}</a>
                              </el-tag>
                              <div>
                                <span>&nbsp;</span>
                              </div>
                              <div>
                                <div class="upload_wrap" @drop="drop($event)" @dragenter="dragenter($event)" @dragover="dragover($event)">
                                  <div style="padding-left: 45px">点击上传文件或将文件拖拽到此处</div>
                                  <div style=" padding-left:100px;">
                                    <img style="width: 90px;cursor: pointer;" @click="fileClick"
                                         @drop="drop($event)" @dragenter="dragenter($event)" @dragover="dragover($event)"
                                         src="../assets/upload.png">
                                  </div>
                                </div>

                                <div class="upload_wrap_text">
                                  共{{imgList.length}}张课件，{{bytesToSize(this.size)}}
                                </div>
                                <el-button  type="text" @click="imgClear()">清除全部文件</el-button>
                                <input @change="fileChange($event)" type="file" id="upload_file" multiple style="display: none"/>
                              </div>
                              <span slot="footer" class="dialog-footer">
                            <el-button @click="homeworkDialogVisible = false">取消</el-button>
                            <el-button type="primary" @click="submitHomework">上传 </el-button>
                             </span>
                            </el-dialog>

                          </el-tabs>
                        </div>
                      </div>
                    </div>
                  </el-col>
                  <el-col :span="8">
                    <!-- 公告 -->
                    <div class="index-section">
                      <div class="aui-panel index-notice">
                        <div class="aui-panel index-undo">
                          <div class="aui-panel__hd">成绩</div>
                          <div class="aui-panel__bd">
                            <a class="index-block index-block--level">
                              <div class="index-block__hd">考试</div>
                              <div class="index-block__bd"><em class="index-block__value">{{CourseDetail.score_exam}}</em></div>
                            </a>
                            <a  class="index-block index-block--level">
                              <div class="index-block__hd">平时</div>
                              <div class="index-block__bd"><em class="index-block__value">{{CourseDetail.score_common}}</em></div>
                            </a>
                            <a class="index-block index-block--level">
                              <div class="index-block__hd">总分</div>
                              <div class="index-block__bd"><em class="index-block__value">{{CourseDetail.score_all}}</em></div>
                            </a>
                          </div>
                        </div>
                      </div>
                    </div>
                    <!-- 管理助手 -->
                    <div class="index-section">
                      <div class="aui-panel index-assistant">
                        <div class="aui-panel__hd clearfix">
                          <span>最近讨论</span>

                        </div>
                        <div class="aui-panel__bd">
                          <div v-for="item in CourseDetail.topics.slice(0,3)">
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

        <main class="aui-main" v-show="bbsHomeVisible">
          <BBSHome :list='list'></BBSHome >
        </main>

        <!-- aui-footer -->
        <footer class="aui-footer">
          <p><a >MyCourse</a>j2ee XGY</p>
        </footer>

        <el-dialog title="确认退选" :visible.sync="ensureQuitVisible">

          <span>您确认退选该课程吗？</span>
          <el-button @click="quit">确认</el-button>
          <el-button @click="ensureQuitVisible=false">取消</el-button>
        </el-dialog>

      </template>
    </div>
    </body>
  </div>
</template>

<script>
  import '../icons/iconfont.js'
  import VimageClip from 'vue-imgclip'
  import BBSHome from "@/components/bbsHome.vue";
  import FileDownload from '@/components/FileDownload'
  import Avatar from 'vue-avatar'
  export default {
    name: "bench",
    components: {
      VimageClip,BBSHome,FileDownload,Avatar
    },

    data () {
      return {

        broadcastDialogVisible:false,

        homeworkResourseList:[
          {filename:123},
        ],
        imgList:[],
        imgFileList: [],
        size: 0,

        currentHomework:{},
        homeworkDialogVisible:false,
        ensureQuitVisible:false,
        list:[],

        user:{},


        lectureList:[
          "../assets/lecture1.jpg",
          "../assets/lecture2.png",
          "../assets/lecture3.png",
          "../assets/lecture.png",
        ],
        allpublishedCourse:{},

        personalCenterInfo:{},

        CourseDetail:{},
        currentAddCourseId:'',
        currentBroadCast:{},
        modify:{
          username:'',
          password:'',
          email:'',
          avator:'',
        },

        main_show:true,


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
        bbsHomeVisible:false,

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


      var xmlhttp2 = new XMLHttpRequest();

      var _this2 = this
      xmlhttp2.onreadystatechange = function () {
        if (xmlhttp2.readyState == 4 && xmlhttp2.status == 200) {
          _this2.CourseDetail = JSON.parse(xmlhttp2.responseText);
        }
      }

      let formData2 = new FormData()
      formData2.append('username', this.user.name)
      console.log("currentCourseId "+localStorage.getItem('courseid'))
      formData2.append('courseid',localStorage.getItem('courseid'))
      xmlhttp2.open('POST', 'http://localhost:8080/j2ee/coursedetail/getInfo', true)
      xmlhttp2.send(formData2)

      this.loading = false;
      this.$nextTick(function () {
        this.scrollbarHandle(true);
        this.getDOM();
        this.searchHandle();
      });
    },


    methods: {

      showMainPage(){
        this.main_show = true;
        this.bbsHomeVisible =false;
      },
      showB0(item){
        this.broadcastDialogVisible = true;
        this.currentBroadCast= item;
      },

      showB1(item){
        this.broadcastDialogVisible = true;
        this.currentBroadCast= item;
      },

      showB2(item){
        this.broadcastDialogVisible = true;
        this.currentBroadCast= item;
      },

      showB3(item){
        this.broadcastDialogVisible = true;
        this.currentBroadCast= item;
      },

      handleClose(tag){



      },

      submitHomework(){

        var xmlhttp = new XMLHttpRequest();

        var _this = this
        xmlhttp.onreadystatechange = function () {
          if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

            _this.showHomeworkDetail(_this.currentHomework)
          }
        }
        let formData = new FormData()
        formData.append('studentname', this.user.name)
        formData.append('homeworkid', this.currentHomework.id)
        for (var i = 0; i < _this.imgFileList.length; i++) {
          formData.append('file', _this.imgFileList[i])
        }
        xmlhttp.open('POST', 'http://localhost:8080/j2ee/file//homework/upload', true)
        xmlhttp.send(formData)
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
      },


      showHomeworkDetail(homework){

        this.currentHomework = homework;
        this.homeworkDialogVisible = true;

        var xmlhttp = new XMLHttpRequest();
        var _this = this
        xmlhttp.onreadystatechange = function () {
          if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            _this.homeworkResourseList = JSON.parse(xmlhttp.responseText);
          }
        }
        let formData = new FormData()
        formData.append('studentname', this.user.name)
        formData.append('homeworkid', this.currentHomework.id)
        xmlhttp.open('POST', 'http://localhost:8080/j2ee/file/homework/resourse', true)
        xmlhttp.send(formData)
      },
      quit(){
        var xmlhttp = new XMLHttpRequest();
        var _this = this
        xmlhttp.onreadystatechange = function () {
          if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
          }
        }

        let formData = new FormData()
        formData.append('name', this.user.name)
        formData.append('courseid', this.CourseDetail.id)
        xmlhttp.open('POST', 'http://localhost:8080/j2ee/selectedcourse/quit', true)
        xmlhttp.send(formData)
        this.$router.push({path: '/9'})


      },

      showBBSHome(){

        console.log("getget")
        var xmlhttp = new XMLHttpRequest();

        var _this = this
        xmlhttp.onreadystatechange = function () {
          if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            console.log("lalala")
            _this.list = JSON.parse(xmlhttp.responseText)
          }
        }

        let formData = new FormData()
        formData.append('courseid', localStorage.getItem("courseid"))
        formData.append('type', 'all')
        xmlhttp.open('POST', 'http://localhost:8080/j2ee/bbs/gettopicvo', true)
        xmlhttp.send(formData)

        this.main_show = false;
        this.bbsHomeVisible = true;



      },
      reload(){
        this.user = JSON.parse(localStorage.getItem("user"))
        this.modify.password = this.user.password
        this.modify.username = this.user.name
        this.modify.email = this.user.email

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

      //添加选中确定的课程到数据库
      saveAddCourse(){
        this.ensureAddCourse = false;

        var xmlhttp = new XMLHttpRequest();

        var _this = this
        xmlhttp.onreadystatechange = function () {
          if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

            _this.reload();

          }
        }

        let formData = new FormData()
        formData.append('courseId', this.currentAddCourseId)
        formData.append('studentName', this.user.name)
        xmlhttp.open('POST', 'http://localhost:8080/j2ee/selectedcourse/pick', true)
        xmlhttp.send(formData)



      },
      gotoCourseDetail(id){

        localStorage.setItem("courseid",id);
        this.$router.push({path: '/CourseDetail'})
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

        let formData = new FormData()
        formData.append('teachername', this.user.name)
        xmlhttp.open('POST', 'http://localhost:8080/j2ee/course/publishedcourse/teachername', true)
        xmlhttp.send(formData)

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
              _this.openWarn('Fail to modify')

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


  .upload_wrap{

    width: 300px;
    height: 150px;
    border: 1px solid #a8a8a8;
    color: #999;

  }
  .upload_wrap_text {
    width: 300px;
    text-align: left;
    left:5px;
    top:310px;
    font-size: 14px;
  }

</style>
