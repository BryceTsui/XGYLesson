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
              <el-menu-item index="2">
                <a href="https://www.renren.io/community" target="_blank">
                  <svg class="icon-svg aui-header__icon-menu" aria-hidden="true"><use xlink:href="#icon-earth"></use></svg>
                </a>
              </el-menu-item>
              <el-menu-item index="3">
                <svg class="icon-svg aui-header__icon-menu" aria-hidden="true"><use xlink:href="#icon-sync"></use></svg>
              </el-menu-item>
              <el-menu-item index="4">
                <svg class="icon-svg aui-header__icon-menu" aria-hidden="true"><use xlink:href="#icon-fullscreen"></use></svg>
              </el-menu-item>
              <el-menu-item index="1" @click="searchVisible = !searchVisible" class="aui-search__toggle">
                <svg class="icon-svg aui-header__icon-menu" aria-hidden="true"><use xlink:href="#icon-search"></use></svg>
              </el-menu-item>

              <el-submenu index="3" :popper-append-to-body="false">
                <template slot="title">
                  <img class="aui-avatar" src="../images/avatar.png">
                  <span>admin</span>
                </template>
                <el-menu-item index="3-1">修改密码</el-menu-item>
                <el-menu-item index="3-2">退出</el-menu-item>
              </el-submenu>
              <el-menu-item index="1" @click="controlOpen = !controlOpen">
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
              <el-menu-item index="home" @click="gotoPageHandle('@@path/index.html')">
                <svg class="icon-svg aui-aside__menu-icon" aria-hidden="true"><use xlink:href="#icon-home"></use></svg>
                <span slot="title">首页</span>
              </el-menu-item>

              <el-menu-item index="icon" @click="gotoPageHandle('@@path/pages/icon.html')">
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
          <div v-if="!headerFixed && controlFixed" class="aui-control__toggle" @click="controlOpen = !controlOpen">
            <svg class="icon-svg" aria-hidden="true"><use xlink:href="#icon-setting"></use></svg>
          </div>
          <div class="aui-control__inner">
            <div class="aui-control__bd">
              <el-tabs class="aui-tabs aui-tabs--flex" v-model="controlTabsActive">
                <el-tab-pane label="Layout" name="layout">
                  <dl class="aui-control__setting">
                    <dt>Wrapper</dt>
                    <dd><el-checkbox v-model="wrapperCenter">Center 居中</el-checkbox></dd>
                  </dl>
                  <dl class="aui-control__setting">
                    <dt>Header</dt>
                    <dd><el-checkbox v-model="headerFixed" :disabled="wrapperCenter">Fixed 固定</el-checkbox></dd>
                    <dd><el-checkbox v-model="headerSkin" true-label="colorful" false-label="white">Colorful 鲜艳</el-checkbox></dd>
                  </dl>
                  <dl class="aui-control__setting">
                    <dt>Aside</dt>
                    <dd><el-checkbox v-model="asideFixed" :disabled="wrapperCenter">Fixed 固定</el-checkbox></dd>
                    <dd><el-checkbox v-model="asideSkin" true-label="dark" false-label="white">Dark 鲜艳</el-checkbox></dd>
                    <dd><el-checkbox v-model="asideTop">Top 至头部</el-checkbox></dd>
                  </dl>
                  <dl class="aui-control__setting">
                    <dt>Control</dt>
                    <dd><el-checkbox v-model="controlFixed" :disabled="wrapperCenter">Fixed 固定</el-checkbox></dd>
                  </dl>
                  <dl class="aui-control__setting">
                    <dt>Main</dt>
                    <dd><el-checkbox v-model="mainType" true-label="tabs" false-label="standard">Tabs 标签页</el-checkbox></dd>
                  </dl>
                  <dl class="aui-control__setting">
                    <dt>Main-tabs</dt>
                    <dd><el-checkbox v-model="mainTabsHeaderFixed" :disabled="wrapperCenter || mainType !== 'tabs'">Header Fixed 固定</el-checkbox></dd>
                  </dl>
                </el-tab-pane>
                <el-tab-pane label="Skins" name="skins">
                  <dl class="aui-control__setting">
                    <dt>Skins</dt>
                    <dd v-for="item in skinList" :key="item.name">
                      <el-radio v-model="skin" :label="item.name" @change="skinChangeHandle">
                        <span class="t-capitalize">{{ item.name }}</span> {{ item.remark }}
                      </el-radio>
                    </dd>
                  </dl>
                </el-tab-pane>
              </el-tabs>
            </div>
          </div>
        </aside>

        <!-- aui-main -->
        <main class="aui-main">
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
                                  <div class="index-block__bd"><em class="index-block__value">0</em></div>
                                  <div class="index-block__hd">课件</div>
                                </div>
                              </el-col>
                              <el-col :span="8">
                                <div class="index-block">
                                  <div class="index-block__bd"><em class="index-block__value">0</em></div>
                                  <div class="index-block__hd">未提交作业</div>
                                </div>
                              </el-col>
                              <el-col :span="8">
                                <div class="index-block">
                                  <div class="index-block__bd"><em class="index-block__value">0</em></div>
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
                                <div class="index-block__hd">上课时间</div>
                                <div class="index-block__bd"><em class="index-block__value">0</em>件</div>
                              </div>
                              <div class="index-block index-block--level">
                                <div class="index-block__hd">授课地点</div>
                                <div class="index-block__bd"><a href="#">购买网络安全</a></div>
                              </div>
                            </div>
                          </div>
                        </el-col>
                        <el-col :span="7">
                          <div class="aui-panel index-undo none_backgroud">
                            <img src="@/images/avatar.png" class="course_cover">
                          </div>
                        </el-col>
                      </el-row>
                    </div>
                    <!-- 最近使用的产品 -->
                    <div class="index-section">
                      <div class="aui-panel index-notice">
                        <div class="aui-panel__bd">
                          <el-tabs class="aui-tabs aui-tabs--flex" v-model="indexNoticeTabsActive">
                            <el-tab-pane label="全部公告" name="1">
                              <ul class="index-notice__list">
                                <li><a href="https://www.renren.io/detail/10832" target="_blank">【升级】renren-cloud 1.1 发布：功能增强</a></li>
                                <li><a href="https://www.renren.io/detail/10781" target="_blank">【升级】renren-security2.0 专业版发布：全新架构升级</a></li>
                                <li><a href="https://www.renren.io/detail/10313" target="_blank">【协议】renren-security【企业版】授权协议</a></li>
                              </ul>
                              <p class="index-notice__more"><a href="#">更多</a></p>
                            </el-tab-pane>
                            <el-tab-pane label="成绩公告" name="2">
                              <ul class="index-notice__list">
                                <li><a href="#">【升级】11月11日.com/.net注册局维护通知</a></li>
                                <li><a href="#">【升级】11月3日、4日商标局服务器维护停止商标递交申请通知</a></li>
                                <li><a href="#">【升级】11月MQ升级计划通知</a></li>
                              </ul>
                              <p class="index-notice__more"><a href="#">更多</a></p>
                            </el-tab-pane>
                            <el-tab-pane label="实验通知" name="3">
                              <ul class="index-notice__list">
                                <li><a href="#">【漏洞预警】最新Apache Struts远程代码执行漏洞(CVE-2016-1000031)</a></li>
                                <li><a href="#">【漏洞预警】Git服务系统 Gogs 和 Gitea 远程命令执行高危漏洞</a></li>
                                <li><a href="#">【漏洞预警】Gitlab Wiki API 远程代码执行漏洞CVE-2018-18649</a></li>
                              </ul>
                              <p class="index-notice__more"><a href="#">更多</a></p>
                            </el-tab-pane>
                            <el-tab-pane label="作业通告" name="4">
                              <ul class="index-notice__list">
                                <li><a href="#">【备案】云虚拟主机产品备案策略调整通知</a></li>
                                <li><a href="#">【备案】关于上海市网站公安备案公告</a></li>
                                <li><a href="#">【备案】轻量应用服务器产品备案策略调整通知</a></li>
                              </ul>
                              <p class="index-notice__more"><a href="#">更多</a></p>
                            </el-tab-pane>
                            <el-tab-pane label="其他" name="5">
                              <ul class="index-notice__list">
                                <li><a href="#">【其他】云盾内容安全商业化通知</a></li>
                                <li><a href="#">【异常】北京时间2018年10月25日虚拟主机服务异常通告【已恢复】</a></li>
                                <li><a href="#">【其他】OSS开放支持HTTP/2通知</a></li>
                              </ul>
                              <p class="index-notice__more"><a href="#">更多</a></p>
                            </el-tab-pane>
                          </el-tabs>
                        </div>
                      </div>
                    </div>
                    <!-- 我的资源、我的消费、推荐返利 -->
                    <div class="index-section">
                      <div class="aui-panel index-personal">
                        <div class="aui-panel__bd">
                          <el-tabs class="aui-tabs" v-model="indexPersonalTabsActive">
                            <el-tab-pane class="index-product" label=" 课件" name="1">
                             <div>
                               <FileDownload></FileDownload>
                             </div>

                            </el-tab-pane>
                            <el-tab-pane class="index-consume" label="作业" name="2">
                              <ul class="index-notice__list">
                                <li><a href="https://www.renren.io/detail/10832" target="_blank">作业1 Hibernate+EJB</a></li>
                                <li><a href="https://www.renren.io/detail/10781" target="_blank">作业2 Spring boot</a></li>
                                <li><a href="https://www.renren.io/detail/10313" target="_blank">作业3 Spring boot+ vue</a></li>
                              </ul>
                            </el-tab-pane>
                            <el-tab-pane class="index-ambassador" label="推荐返利" name="3">
                              <div class="index-ambassador__hd">
                                <h3 class="index-ambassador__title">推荐返利</h3>
                                <p class="index-ambassador__intro">成为云大使，即可享受推荐下单返利，推广任务奖励，云知识云技能培训等阿里云对云大使的多种支持。优秀者更可享受阿里云官方认证与阿里云参观学习等更多专项支持。</p>
                              </div>
                              <div class="index-ambassador__bd">
                                <el-row :gutter="15">
                                  <el-col :span="12">
                                    <div class="index-ambassador__item">
                                      <svg class="index-ambassador__item-icon icon-svg" aria-hidden="true"><use xlink:href="#icon-gift-fill"></use></svg>
                                      <h4 class="index-ambassador__item-title">专享优厚回报</h4>
                                      <p class="index-ambassador__item-intro">不但提供推广返利，更有任务奖金，排名大奖，大使折扣，新产品内测机会等专属奖励。</p>
                                    </div>
                                  </el-col>
                                  <el-col :span="12">
                                    <div class="index-ambassador__item">
                                      <svg class="index-ambassador__item-icon icon-svg" aria-hidden="true"><use xlink:href="#icon-trophy-fill"></use></svg>
                                      <h4 class="index-ambassador__item-title">与阿里云共同成长</h4>
                                      <p class="index-ambassador__item-intro">阿里云将长期扶持云大使尤其是其中优秀者的长期发展，提供相应培训，能力认证等。</p>
                                    </div>
                                  </el-col>
                                </el-row>
                                <div class="index-ambassador__ft">
                                  <el-button type="primary">立即加入</el-button>
                                </div>
                              </div>
                            </el-tab-pane>
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
                            <a href="#" class="index-block index-block--level">
                              <div class="index-block__hd">考试</div>
                              <div class="index-block__bd"><em class="index-block__value">60</em></div>
                            </a>
                            <a href="#" class="index-block index-block--level">
                              <div class="index-block__hd">平时</div>
                              <div class="index-block__bd"><em class="index-block__value">39</em></div>
                            </a>
                            <a href="#" class="index-block index-block--level">
                              <div class="index-block__hd">总分</div>
                              <div class="index-block__bd"><em class="index-block__value">99</em></div>
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
                          <a href="#" class="f-right fz-xs">查看更多</a>
                        </div>
                        <div class="aui-panel__bd">
                          <div class="index-assistant__item">
                            <div class="index-assistant__item-hd">
                              <img src="@/images/avatar.png" class="course_cover">
                            </div>
                            <div class="index-assistant__item-bd">
                              <h4 class="index-assistant__item-title">双11巅峰钜惠</h4>
                              <p class="index-assistant__item-intro">云产品拼购1折起，拉新赢300万现金红包</p>
                              <el-button type="primary" plain size="mini">参与讨论</el-button>
                            </div>
                          </div>
                          <div class="index-assistant__item">
                            <div class="index-assistant__item-hd">
                              <img src="@/images/avatar.png" class="course_cover">
                            </div>
                            <div class="index-assistant__item-bd">
                              <h4 class="index-assistant__item-title">2018广东云栖大会</h4>
                              <p class="index-assistant__item-intro">11月22日广州南丰朗豪酒店，报名进行中</p>
                              <el-button type="primary" plain size="mini">参与讨论</el-button>
                            </div>
                          </div>
                          <div class="index-assistant__item">
                            <div class="index-assistant__item-hd">
                              <img src="@/images/avatar.png" class="course_cover">
                            </div>
                            <div class="index-assistant__item-bd">
                              <h4 class="index-assistant__item-title">推荐好友送云服务器</h4>
                              <p class="index-assistant__item-intro">邀请好友7.1折起购短信包，即可获赠云服务器</p>
                              <el-button type="primary" plain size="mini">参与讨论</el-button>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>

                    <!-- 定制资讯 -->
                    <div class="index-section">
                      <el-button class="aui-button--dashed index-btn__custom">
                        <svg class="icon-svg fz-base" aria-hidden="true"><use xlink:href="#icon-time-circle"></use></svg>
                        <span class="va-middle">老师信息</span>
                      </el-button>
                    </div>
                    <!-- 二维码 -->
                    <div class="index-section">
                      <div class="aui-panel index-code">
                        <div class="aui-panel__bd">
                          <div class="index-code__item">
                            <i class="index-code__item-img"></i>
                            <div class="index-code__item-inner">
                              <h4 class="index-code__item-title">杰克马</h4>
                              <p class="index-code__item-intro">职称：副教授<br>邮箱：ceo@alibaba.com<br>办公室：软件学院888室</p>
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
        <!-- aui-footer -->
        <footer class="aui-footer">
          <p><a href="https://www.renren.io/" target="_blank">人人开源</a>2018 © renren.io</p>
        </footer>

      </template>
    </div>
    </body>
  </div>
</template>

<script>
  import '../icons/iconfont.js'
  import FileDownload from '@/components/FileDownload'

  export default {
    name: "CourseDetail",
    components: {FileDownload},
    data () {
      return {
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
        controlTabsActive: 'layout',
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
      this.loading = false;
      this.$nextTick(function () {
        this.scrollbarHandle(true);
        this.getDOM();
        this.searchHandle();
      });
    },
    methods: {
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
      }
    }
  }
</script>

<style scoped>
  @import "../style/aui-orange.min.css";
  /*@import "../element-theme/orange/index.css";*/

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
</style>
