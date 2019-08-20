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
            <a class="aui-brand aui-brand--lg" href="javascript:;">renren-aui</a>
            <a class="aui-brand aui-brand--sm" href="javascript:;">aui</a>
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
              <el-submenu index="2" :popper-append-to-body="false" class="not-arrow t-center">
                <template slot="title">
                  <el-button size="mini">简体中文</el-button>
                </template>
                <el-menu-item index="2-1">简体中文</el-menu-item>
                <el-menu-item index="2-2">繁體中文</el-menu-item>
                <el-menu-item index="2-3">English</el-menu-item>
              </el-submenu>
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
                <svg class="icon-svg aui-aside__menu-icon" aria-hidden="true"><use xlink:href="#icon-gift"></use></svg>
                <span slot="title">图标</span>
              </el-menu-item>

              <el-submenu index="basic" :popper-append-to-body="false">
                <template slot="title">
                  <svg class="icon-svg aui-aside__menu-icon" aria-hidden="true"><use xlink:href="#icon-crown"></use></svg>
                  <span>基础页面</span>
                </template>
                <el-menu-item index="basic-login" @click="gotoPageHandle('@@path/pages/login.html')">
                  <svg class="icon-svg aui-aside__menu-icon" aria-hidden="true"><use xlink:href="#icon-fire"></use></svg>
                  <span>登录</span>
                </el-menu-item>
                <el-menu-item index="basic-login-v2" @click="gotoPageHandle('@@path/pages/login-v2.html')">
                  <svg class="icon-svg aui-aside__menu-icon" aria-hidden="true"><use xlink:href="#icon-fire"></use></svg>
                  <span>登录 - v2</span>
                </el-menu-item>
                <el-menu-item index="basic-register" @click="gotoPageHandle('@@path/pages/register.html')">
                  <svg class="icon-svg aui-aside__menu-icon" aria-hidden="true"><use xlink:href="#icon-fire"></use></svg>
                  <span>注册</span>
                </el-menu-item>
                <el-menu-item index="basic-register-v2" @click="gotoPageHandle('@@path/pages/register-v2.html')">
                  <svg class="icon-svg aui-aside__menu-icon" aria-hidden="true"><use xlink:href="#icon-fire"></use></svg>
                  <span>注册 - v2</span>
                </el-menu-item>
                <el-menu-item index="basic-forget" @click="gotoPageHandle('@@path/pages/forget.html')">
                  <svg class="icon-svg aui-aside__menu-icon" aria-hidden="true"><use xlink:href="#icon-fire"></use></svg>
                  <span>找回密码</span>
                </el-menu-item>
                <el-menu-item index="basic-forget-v2" @click="gotoPageHandle('@@path/pages/forget-v2.html')">
                  <svg class="icon-svg aui-aside__menu-icon" aria-hidden="true"><use xlink:href="#icon-fire"></use></svg>
                  <span>找回密码 - v2</span>
                </el-menu-item>
              </el-submenu>

              <el-submenu index="chart" :popper-append-to-body="false">
                <template slot="title">
                  <svg class="icon-svg aui-aside__menu-icon" aria-hidden="true"><use xlink:href="#icon-barchart"></use></svg>
                  <span>图表</span>
                </template>
                <el-menu-item index="chart-echarts" @click="gotoPageHandle('@@path/pages/echarts.html')">
                  <svg class="icon-svg aui-aside__menu-icon" aria-hidden="true"><use xlink:href="#icon-fire"></use></svg>
                  <span>Echarts</span>
                </el-menu-item>
              </el-submenu>

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
        <main class="aui-main" v-show="home_show">
          <!-- standard 标准 -->
          <div v-show="mainType === 'standard'" class="aui-main__hd">
            <el-breadcrumb separator="/">
              <el-breadcrumb-item>renren-aui</el-breadcrumb-item>
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
                            <div class="aui-panel__hd">安全预警</div>
                            <el-row :gutter="10" class="aui-panel__bd">
                              <el-col :span="8">
                                <div class="index-block">
                                  <div class="index-block__bd"><em class="index-block__value">0</em></div>
                                  <div class="index-block__hd">紧急事件</div>
                                </div>
                              </el-col>
                              <el-col :span="8">
                                <div class="index-block">
                                  <div class="index-block__bd"><em class="index-block__value">0</em></div>
                                  <div class="index-block__hd">漏洞</div>
                                </div>
                              </el-col>
                              <el-col :span="8">
                                <div class="index-block">
                                  <div class="index-block__bd"><em class="index-block__value">0</em></div>
                                  <div class="index-block__hd">攻击</div>
                                </div>
                              </el-col>
                            </el-row>
                          </div>
                        </el-col>
                        <el-col :span="7">
                          <div class="aui-panel index-security-protect">
                            <div class="aui-panel__hd">安全防护</div>
                            <div class="aui-panel__bd">
                              <div class="index-block index-block--level">
                                <div class="index-block__hd">主机安全</div>
                                <div class="index-block__bd"><em class="index-block__value">0</em>件</div>
                              </div>
                              <div class="index-block index-block--level">
                                <div class="index-block__hd">WEB 攻击</div>
                                <div class="index-block__bd"><a href="#">购买网络安全</a></div>
                              </div>
                            </div>
                          </div>
                        </el-col>
                        <el-col :span="7">
                          <div class="aui-panel index-undo">
                            <div class="aui-panel__hd">待办事项</div>
                            <div class="aui-panel__bd">
                              <a href="#" class="index-block index-block--level">
                                <div class="index-block__hd">工单</div>
                                <div class="index-block__bd"><em class="index-block__value">0</em></div>
                              </a>
                              <a href="#" class="index-block index-block--level">
                                <div class="index-block__hd">续费</div>
                                <div class="index-block__bd"><em class="index-block__value">0</em></div>
                              </a>
                              <a href="#" class="index-block index-block--level">
                                <div class="index-block__hd">未支付订单</div>
                                <div class="index-block__bd"><em class="index-block__value">0</em></div>
                              </a>
                            </div>
                          </div>
                        </el-col>
                      </el-row>
                    </div>
                    <!-- 最近使用的产品 -->
                    <div class="index-section">
                      <div class="aui-panel index-recent">
                        <div class="aui-panel__hd clearfix">
                          <span>最近使用的产品</span>
                          <a href="#" class="f-right fz-xs">
                            <span class="va-middle">操作日志</span>
                            <svg class="icon-svg fz-base" aria-hidden="true"><use xlink:href="#icon-time-circle"></use></svg>
                          </a>
                        </div>
                        <div class="aui-panel__bd">
                          <el-row :gutter="15">
                            <el-col :span="8">
                              <a href="#" class="index-block index-block--level">
                                <div class="index-block__bd">Web 应用防火墙（网络安全）</div>
                              </a>
                            </el-col>
                            <el-col :span="8">
                              <a href="#" class="index-block index-block--level">
                                <div class="index-block__bd">负载均衡</div>
                              </a>
                            </el-col>
                            <el-col :span="8">
                              <a href="#" class="index-block index-block--level">
                                <div class="index-block__bd">云服务器 ECS</div>
                              </a>
                            </el-col>
                            <el-col :span="8">
                              <a href="#" class="index-block index-block--level">
                                <div class="index-block__bd">对象存储 OSS</div>
                              </a>
                            </el-col>
                            <el-col :span="8">
                              <a href="#" class="index-block index-block--level">
                                <div class="index-block__bd">云监控</div>
                              </a>
                            </el-col>
                          </el-row>
                        </div>
                      </div>
                    </div>
                    <!-- 我的资源、我的消费、推荐返利 -->
                    <div class="index-section">
                      <div class="aui-panel index-personal">
                        <div class="aui-panel__bd">
                          <el-tabs class="aui-tabs" v-model="indexPersonalTabsActive">
                            <el-tab-pane class="index-product" label="我的资源" name="1">
                              <el-row :gutter="15">
                                <el-col :span="8">
                                  <dl class="index-product__list">
                                    <dt>数据库</dt>
                                    <dd><a href="#">云数据库 HybridDB for MySQL</a></dd>
                                    <dd><a href="#">云数据库 HBase 版</a></dd>
                                    <dd><a href="#">云数据库 HybridDB for PostgreSQL</a></dd>
                                    <dd><a href="#">数据传输服务 DTS</a></dd>
                                  </dl>
                                  <dl class="index-product__list">
                                    <dt>分析</dt>
                                    <dd><a href="#">云数据库 HybridDB for PostgreSQL</a></dd>
                                  </dl>
                                  <dl class="index-product__list">
                                    <dt>大数据（数加）</dt>
                                    <dd><a href="#">DataWorks</a></dd>
                                    <dd><a href="#">机器学习</a></dd>
                                    <dd><a href="#">数据集成</a></dd>
                                    <dd><a href="#">阿里云 Elasticsearch</a></dd>
                                  </dl>
                                </el-col>
                                <el-col :span="8">
                                  <dl class="index-product__list">
                                    <dt>存储与CDN</dt>
                                    <dd><a href="#">CDN</a></dd>
                                  </dl>
                                  <dl class="index-product__list">
                                    <dt>域名与网站（万网）</dt>
                                    <dd><a href="#">域名</a></dd>
                                    <dd><a href="#">云解析 DNS</a></dd>
                                    <dd><a href="#">弹性 Web 托管</a></dd>
                                  </dl>
                                </el-col>
                                <el-col :span="8">
                                  <dl class="index-product__list">
                                    <dt>网络</dt>
                                    <dd><a href="#">CDN</a></dd>
                                  </dl>
                                  <dl class="index-product__list">
                                    <dt>应用服务</dt>
                                    <dd><a href="#">云效</a></dd>
                                  </dl>
                                  <dl class="index-product__list">
                                    <dt>我的服务</dt>
                                    <dd><a href="#">尊享服务</a></dd>
                                  </dl>
                                </el-col>
                              </el-row>
                              <a href="#" class="index-product__fold">
                                <span class="va-middle">收起更多产品</span>
                                <svg class="icon-svg fz-base" aria-hidden="true"><use xlink:href="#icon-down"></use></svg>
                              </a>
                            </el-tab-pane>
                            <el-tab-pane class="index-consume" label="我的消费" name="2">
                              <el-row :gutter="15">
                                <el-col :span="12">
                                  <div class="index-consume__hd">账户概览</div>
                                  <el-row :gutter="10" class="index-consume__bd">
                                    <el-col :span="12">
                                      <div class="index-block index-consume__block">
                                        <div class="index-block__hd">账户余额（元）</div>
                                        <div class="index-block__bd"><em class="index-block__value">0.00</em></div>
                                        <div class="index-block__ft"><el-button type="primary">充值</el-button></div>
                                      </div>
                                    </el-col>
                                    <el-col :span="12">
                                      <a href="#" class="index-block index-block--level">
                                        <div class="index-block__hd">代金券</div>
                                        <div class="index-block__bd"><em class="index-block__value">0</em>张</div>
                                      </a>
                                      <a href="#" class="index-block index-block--level">
                                        <div class="index-block__hd">储值卡</div>
                                        <div class="index-block__bd"><em class="index-block__value">0</em>张</div>
                                      </a>
                                      <a href="#" class="index-block index-block--level">
                                        <div class="index-block__hd">优惠券</div>
                                        <div class="index-block__bd"><em class="index-block__value">0</em>张</div>
                                      </a>
                                    </el-col>
                                  </el-row>
                                </el-col>
                                <el-col :span="12">
                                  <div class="index-consume__hd">近期消费</div>
                                  <div class="index-consume__bd">
                                    <div class="index-block index-consume__block">
                                      <div class="index-block__hd">11 月消费</div>
                                      <div class="index-block__bd"><em class="index-block__value">0.00</em></div>
                                      <div class="index-block__ft">参加 <a href="#">最新活动</a>，发现云计算新旅程</div>
                                    </div>
                                  </div>
                                </el-col>
                              </el-row>
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
                            <el-tab-pane label="升级" name="2">
                              <ul class="index-notice__list">
                                <li><a href="#">【升级】11月11日.com/.net注册局维护通知</a></li>
                                <li><a href="#">【升级】11月3日、4日商标局服务器维护停止商标递交申请通知</a></li>
                                <li><a href="#">【升级】11月MQ升级计划通知</a></li>
                              </ul>
                              <p class="index-notice__more"><a href="#">更多</a></p>
                            </el-tab-pane>
                            <el-tab-pane label="安全" name="3">
                              <ul class="index-notice__list">
                                <li><a href="#">【漏洞预警】最新Apache Struts远程代码执行漏洞(CVE-2016-1000031)</a></li>
                                <li><a href="#">【漏洞预警】Git服务系统 Gogs 和 Gitea 远程命令执行高危漏洞</a></li>
                                <li><a href="#">【漏洞预警】Gitlab Wiki API 远程代码执行漏洞CVE-2018-18649</a></li>
                              </ul>
                              <p class="index-notice__more"><a href="#">更多</a></p>
                            </el-tab-pane>
                            <el-tab-pane label="备案" name="4">
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
                    <!-- 管理助手 -->
                    <div class="index-section">
                      <div class="aui-panel index-assistant">
                        <div class="aui-panel__hd clearfix">
                          <span>管理助手</span>
                          <a href="#" class="f-right fz-xs">查看更多</a>
                        </div>
                        <div class="aui-panel__bd">
                          <div class="index-assistant__item">
                            <div class="index-assistant__item-hd">
                              <svg class="icon-svg" aria-hidden="true"><use xlink:href="#icon-robot"></use></svg>
                            </div>
                            <div class="index-assistant__item-bd">
                              <h4 class="index-assistant__item-title">双11巅峰钜惠</h4>
                              <p class="index-assistant__item-intro">云产品拼购1折起，拉新赢300万现金红包</p>
                              <el-button type="primary" plain size="mini">立即拼团</el-button>
                            </div>
                          </div>
                          <div class="index-assistant__item">
                            <div class="index-assistant__item-hd">
                              <svg class="icon-svg" aria-hidden="true"><use xlink:href="#icon-instagram"></use></svg>
                            </div>
                            <div class="index-assistant__item-bd">
                              <h4 class="index-assistant__item-title">2018广东云栖大会</h4>
                              <p class="index-assistant__item-intro">11月22日广州南丰朗豪酒店，报名进行中</p>
                              <el-button type="primary" plain size="mini">立即报名</el-button>
                            </div>
                          </div>
                          <div class="index-assistant__item">
                            <div class="index-assistant__item-hd">
                              <svg class="icon-svg" aria-hidden="true"><use xlink:href="#icon-apartment"></use></svg>
                            </div>
                            <div class="index-assistant__item-bd">
                              <h4 class="index-assistant__item-title">推荐好友送云服务器</h4>
                              <p class="index-assistant__item-intro">邀请好友7.1折起购短信包，即可获赠云服务器</p>
                              <el-button type="primary" plain size="mini">立即前往</el-button>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                    <!-- 新产品快报 -->
                    <div class="index-section">
                      <div class="aui-panel index-information">
                        <div class="aui-panel__hd">新产品快报</div>
                        <div class="aui-panel__bd">
                          <ul class="index-information__list">
                            <li><strong>[新产品]</strong>OSS发布同城冗余存储产品（公测）</li>
                            <li><strong>[新地域/可用区]</strong>SQL Server 2008r2 在杭州可用区F+G上线</li>
                            <li><strong>[新地域/可用区]</strong>数据库备份DBS新增开通香港地域</li>
                          </ul>
                          <p class="index-information__more"><a href="#">更多</a></p>
                        </div>
                      </div>
                    </div>

                    <!-- 定制资讯 -->
                    <div class="index-section">
                      <el-button class="aui-button--dashed index-btn__custom">
                        <svg class="icon-svg fz-base" aria-hidden="true"><use xlink:href="#icon-time-circle"></use></svg>
                        <span class="va-middle">定制资讯</span>
                      </el-button>
                    </div>
                    <!-- 二维码 -->
                    <div class="index-section">
                      <div class="aui-panel index-code">
                        <div class="aui-panel__bd">
                          <div class="index-code__item">
                            <i class="index-code__item-img"></i>
                            <div class="index-code__item-inner">
                              <h4 class="index-code__item-title">阿里云 APP</h4>
                              <p class="index-code__item-intro">随时随地移动管控<br>独有的行业咨询、在线直播<br>更安全，更便捷</p>
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

        <!-- create Course -->
        <main class="aui-main" v-show="createCourse_show">
          <div>
            <div style="position: absolute; left: 50%; top: 70px; margin-left: -350px">
              <span style="position: absolute; left: 0px; width: 70px; top: 10px">发布任务</span>
              <el-steps simple align-center style="width: 600px; position: absolute; left: 70px;background-color: white" :active="active" finish-status="success">
                <el-step icon="el-icon-info" style="margin-left: 40px;" title="基本信息"></el-step>
                <el-step icon="el-icon-edit" title="个性订制"></el-step>
                <el-step icon="el-icon-upload" title="数据上传"></el-step>
              </el-steps>
            </div>
            <div v-show="firstStep" style="position: absolute; top: 100px;width: 1000px; margin-left: -430px;left: 50%">
              <div class="form_div">
                <div style="position: absolute; left: 32px; top: -10px;">
                  <span style="position: absolute; left: 0px;padding-top: 5px; font-size: 14px;width: 70px; text-align: left">标注类型</span>
                  <div id="manLabel" style="position: absolute; left: 68px;" class="labelType" @click="selectManLabel">人工标注</div>
                  <div id="autoLabel" style="position: absolute; left: 178px;" class="labelType" @click="selectAutoLabel">自动化标注</div>
                </div>
                <div v-show="isAutoLabel">
                  <span style="position: absolute; left: 32px;top: 40px;font-size: 14px">任务标题</span>
                  <el-input style="position: absolute; left: 100px;top: 40px;font-size: 14px;width: 390px" v-model="autoForm.missionName" ></el-input>
                  <span style="position: absolute; left: 32px;top: 92px;font-size: 14px">任务详情</span>
                  <el-input style="position: absolute; left: 100px;top: 92px;font-size: 14px;width: 390px" type="textarea" :rows="5" placeholder="输入任务的具体要求" v-model="autoForm.contents"></el-input>
                  <span style="position: absolute; left: 32px;top: 240px;font-size: 14px">标注方式</span>
                  <el-select @change="labelChange2" style="position: absolute; left:100px;top: 240px;" v-model="autoForm.type" placeholder="选择标注方式">
                    <div v-for="item in options" :key="item.value">
                      <el-option :label="item.label" :value="item.value" :disabled="item.disabled"></el-option>
                    </div>
                  </el-select>
                  <el-button style="position: absolute; left:350px;top: 233px;" @click="labelDetailsShow" class="label_info_button" icon="el-icon-info" type="text"></el-button>
                  <el-button style="position: absolute; left:360px;top: 233px;" type="text"  v-show="isClassBtnShow" @click="handleClassBtn">添加分类</el-button>

                </div>
                <el-form v-show="!isAutoLabel" class="form" style="position:absolute;top: 50px" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px">
                  <el-form-item label="任务标题" prop="topic" required>
                    <el-input v-model="ruleForm.topic" ></el-input>
                  </el-form-item>
                  <el-form-item label="任务内容" prop="contents" required>
                    <el-input type="textarea" :rows="2" placeholder="输入任务的具体要求" v-model="ruleForm.contents"></el-input>
                  </el-form-item>
                  <el-form-item label="任务时间" required>
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
                  <el-form-item label="标注方式" prop="type" required>
                    <el-select @change="labelChange" style="position: absolute; left: 0px;" v-model="ruleForm.type" placeholder="选择标注方式">
                      <div v-for="item in options" :key="item.value">
                        <el-option :label="item.label" :value="item.value" :disabled="item.disabled"></el-option>
                      </div>
                    </el-select>
                    <el-button @click="labelDetailsShow" class="label_info_button" icon="el-icon-info" type="text"></el-button>
                  </el-form-item>
                  <div style="position: absolute; top:220px; left: 360px;">
                    <el-button type="text"  v-show="isClassBtnShow" @click="handleClassBtn">添加分类</el-button>
                    <el-button type="text" v-show="isAttrBtnShow" @click="handleAttrBtn">添加属性</el-button>
                  </div>
                </el-form>
                <router-view></router-view>
              </div>
            </div>

            <div v-show="secondStep" style="position: absolute; top: 100px;width: 1000px;margin-left: -430px;left: 50%">
              <span style="font-size: 13px; width: 400px; position: absolute; left: 250px; top: 30px; text-align: left">标注人数</span>
              <el-input-number size="mini" style="position: absolute; left: 315px; top: 25px;" v-model="ruleForm.workerNumber" :min="20" :max="100"></el-input-number>
              <span style="font-size: 13px; width: 400px; position: absolute; left: 460px; top: 30px; text-align: left">奖励积分</span>
              <el-input-number size="mini" style="position: absolute; left: 520px; top: 25px;" v-model="ruleForm.workerPoints" :min="5" :max="20"></el-input-number>
              <span style="position:absolute; left:560px;top: 58px; font-size: 14px; color: #ffca06">总积分：{{ruleForm.workerPoints*ruleForm.workerNumber}}</span>
              <div>
                <span style="font-size: 13px; width: 400px; position: absolute; left: 250px; top: 70px; text-align: left">添加标签 <span style="font-size: 11px"> 准确的标签能让你的项目找到更适合的工人</span></span>
                <tag-selector style="position: absolute; left: 250px; top: 95px;"
                              v-bind:refresh-hot-tag="newHotTag" v-bind:candidate-label-list="hotTagList"
                              v-bind:selected-labels-list="selectedTagList" selected-div-width="440" selected-div-height="110">
                </tag-selector>
              </div>
            </div>

            <div v-show="thirdStep" style="position: absolute; top: 100px;width: 1000px; margin-left: -450px;left: 50%">
              <div style="position: absolute; left: 250px; top: 20px;" class="project_open">
                <div class="upload_wrap" @drop="drop($event)" @dragenter="dragenter($event)" @dragover="dragover($event)">
                  <div style="position: absolute; left: 100px; top: 230px;">点击上传图片或将文件拖拽到此处</div>
                </div>
                <div>
                  <img style="position: absolute; left: 180px; top: 90px; width: 90px;cursor: pointer;" @click="fileClick"
                       @drop="drop($event)" @dragenter="dragenter($event)" @dragover="dragover($event)"
                       src="../assets/upload.png">
                </div>
                <div class="upload_wrap_text">
                  共{{imgList.length}}张图片，{{bytesToSize(this.size)}}
                </div>
                <el-button style="position: absolute; top:300px; left: 360px;" type="text" @click="imgClear()">清除全部图片</el-button>
                <input @change="fileChange($event)" type="file" id="upload_file" multiple style="display: none"/>
              </div>
            </div>

            <div style="position: absolute; top: 480px; width: 600px; left: 50%;margin-left: -200px">
              <button v-bind:disabled="isCancelBtnDisabled" @click="handleCancelButton" id="cancelBtn" class="stepBtn">取消</button>
              <button v-bind:disabled="isBeforeBtnDisabled"  @click="handleBeforeBtn" class="stepBtn">上一步</button>
              <button v-bind:disabled="isNextBtnDisabled" @click="handleNextBtn" class="stepBtn">下一步</button>
              <button v-bind:disabled="isSureBtnDisabled" @click="submitForm" id="sureBtn" class="stepBtn">确定</button>
            </div>

            <div style="position: absolute; top: 57px; left: 0px; width: 20%; height: 450px; border-bottom-left-radius: 1px; border-color: grey" >
              <div style="position: absolute; left: 50%; margin-left: -29px; height: 50px;">
                图片预览
              </div>
              <happy-scroll class="happy_scroll" color="rgba(51,51,51,0.2)" hide-vertical="false">
                <div v-for="(item,index) of imgList" :key="item">
                  <div class="upload_warp_img_div_top">
                    <div class="upload_warp_img_div_text">
                      {{item.file.name}}
                    </div>
                    <img src="../assets/del.png" class="upload_warp_img_div_del" @click="fileDel(index)">
                  </div>
                  <img style="width: 100%;" :src="item.file.src">
                </div>
              </happy-scroll>
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
        </main>

        <!-- BBS-->
        <main class="aui-main" v-show="bbs_show">
         <BBSHome></BBSHome>
        </main>
        <!-- BBSTopic -->
        <main class="aui-main" v-show="bbs_topic_show">
          <BBSTopic></BBSTopic>
        </main>

        <main class="aui-main" v-show="course_table_show">


          <el-collapse v-model="activeNames" @change="handleChange">
            <el-collapse-item title="一致性 Consistency" name="1">
              <div>与现实生活一致：与现实生活的流程、逻辑保持一致，遵循用户习惯的语言和概念；</div>
              <div>在界面中一致：所有的元素和结构需保持一致，比如：设计样式、图标和文本、元素的位置等。</div>
            </el-collapse-item>
            <el-collapse-item title="反馈 Feedback" name="2">
              <div>控制反馈：通过界面样式和交互动效让用户可以清晰的感知自己的操作；</div>
              <div>页面反馈：操作后，通过页面元素的变化清晰地展现当前状态。</div>
            </el-collapse-item>
            <el-collapse-item title="效率 Efficiency" name="3">
              <div>简化流程：设计简洁直观的操作流程；</div>
              <div>清晰明确：语言表达清晰且表意明确，让用户快速理解进而作出决策；</div>
              <div>帮助用户识别：界面简单直白，让用户快速识别而非回忆，减少用户记忆负担。</div>
            </el-collapse-item>
            <el-collapse-item title="可控 Controllability" name="4">
              <div>用户决策：根据场景可给予用户操作建议或安全提示，但不能代替用户进行决策；</div>
              <div>结果可控：用户可以自由的进行操作，包括撤销、回退和终止当前操作等。</div>
            </el-collapse-item>
          </el-collapse>

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
  import JSZip from 'jszip'
  import TagSelector from './tagSelector/tagSelector'
  import MyLoading from './myLoading/myLoading'
  import BBSHome from "@/components/bbsHome.vue";
  import BBSTopic from "@/components/BBSTopic"


  export default {
    components: {TagSelector, MyLoading,BBSHome,BBSTopic},
    name: "bench",
    data () {
      var validateTopic = (rule, value, callback) => {
        var len = 0
        for (var i = 0; i < value.length; i++) {
          if (value.charCodeAt(i) > 127 || value.charCodeAt(i) == 94) {
            len += 2
          } else {
            len++
          }
        }
        if (len > 20) {
          callback(new Error('项目标题的长度不能超过10个汉字或20个字母'))
        }
        if (len == 0) {
          callback(new Error('标题不能为空'))
        }
      }
      return {
        //课程折叠面板
        activeNames: ['1'],
        autoForm: {
          type: '',
          contents: '',
          missionName: '',
          points: 10
        },
        doAutoLabel: false,
        isAutoLabel: false,
        hotTagList: ['dog', 'cat', 'mouse', 'horse', 'caption', 'detection', 'animal', 'car',
          'fruit', 'vegetable'],
        selectedTagList: [],
        firstStep: true,
        secondStep: false,
        thirdStep: false,
        isCancelBtnDisabled: false,
        isNextBtnDisabled: false,
        isBeforeBtnDisabled: true,
        isSureBtnDisabled: true,

        active: 0,
        rules: {
          topic: [
            {validator: validateTopic, trigger: 'blur'}
          ]
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
          topic: '',
          contents: '',
          type: '',
          dateStart: '',
          dateEnd: '',
          workerNumber: 20,
          workerPoints: 5
        },
        options: [
          {
            value: 'Caption',
            label: '整体标注',
            info: '描述整张图片里的内容'

          },
          {
            value: 'Detection',
            label: '方框描述',
            info: '把图片中指定类型的元素用矩形标注出来'
          },
          {
            value: 'Segmentation',
            label: '区域标注',
            info: '描画图片中指定类型的元素的轮廓'

          },
          {
            value: 'Classification',
            label: '图像分类',
            info: '对图片进行分类'

          },
          {
            value: 'Attribute',
            label: '属性描述',
            info: ' 标注图片中出现元素的属性'
          }
        ],
        value: '',
        home_show:false,
        createCourse_show : true,
        bbs_show:false,
        bbs_topic_show: false,
        course_table_show:true,
        course_table_show:false,
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
      },
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
      this.loading = false;
      this.$nextTick(function () {
        this.scrollbarHandle(true);
        this.getDOM();
        this.searchHandle();
      });
    },
    methods: {
      handleChange(val) {
        console.log(val);
      },

      backStageRun () {
        this.doAutoLabel = false
        this.goMyProject()
      },
      selectManLabel () {
        for (var i = 0; i < this.options.length; i++) {
          this.options[i].disabled = false
        }
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
        this.options[2].disabled = true
        this.options[4].disabled = true
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

        if (this.active < 3) {
          this.active++
        }
        if (this.isAutoLabel == true) { this.active = 2 }
        if (this.active == 2) {
          this.isSureBtnDisabled = false
          this.isNextBtnDisabled = true
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
      submitForm () {
        if (this.isAutoLabel) {
          this.submitAutoForm()
        } else {
          this.submitManForm()
        }
      },

      submitAutoForm () {
        if (this.imgFileList.length == 0) {
          this.openWarn('您尚未上传任何图片。')
          return
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
        var type = _this.autoForm.type
        xmlhttp.onreadystatechange = function () {
          if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            console.log('missionID' + xmlhttp.responseText)
            missionID = xmlhttp.responseText
            if (type == 'Detection') {
              _this.uploadAutoDectionImg(missionID)
            } else if (type == 'Caption') {
              _this.uploadAutoCaptionImg(missionID)
            } else if (type == 'Classification') {
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
          autoMission.types = _this.selectsArray
        }
        // console.log(this.selectsArray)
        xmlhttp.open('POST', 'http://localhost:8080/counts/mission/addAutoMission', true)
        xmlhttp.setRequestHeader('Content-type', 'application/json; charset=utf-8')
        // console.log(JSON.stringify(autoMission))
        xmlhttp.send(JSON.stringify(autoMission))
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
          this.openWarn('您尚未上传任何图片。')
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
            sum: _this.imgFileList.length
          }
          if (mission.type == 'Classification' || mission.type == 'Attribute') {
            mission.selects = _this.selectsArray
          }
          xmlhttp.open('POST', 'http://localhost:8080/counts/image/addmission', true)
          xmlhttp.setRequestHeader('Content-type', 'application/json; charset=utf-8')
          xmlhttp.send(JSON.stringify(mission))
        }
      },

      uploadAutoCaptionImg (missionID) {
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
          console.log('imglist' + this.imgFileList)
          xmlhttp.open('POST', 'http://localhost:8080/counts/image/uploadAutoCaptionImg', true)
          xmlhttp.send(formData)
        }
      },
      uploadAutoClassificationImg (missionID) {
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
      uploadAutoDectionImg (missionID) {
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
          // console.log("uploadSetection")
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
          this.openInfo('您尚未上传任何图片')
          return
        }
        this.$confirm('确定要清除所有的图片吗？', 'Confirm', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.imgList = []
          this.imgFileList = []
          this.size = 0
          this.openSucc('图片清除成功')
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
        this.$confirm('确认要删除该图片吗？', 'Confirm', {
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
        // 判断是否为图片文件
        var _this = this
        console.log('file ' + file.type)
        if (file.type.indexOf('image') == -1) {
          // 如果是压缩包
          // console.log('name '+file)
          if (file.type.indexOf('x-zip-compressed') != -1) {
            var zip = new JSZip()
            zip.loadAsync(file).then(function (file) {
              var files = file.files
              console.log(files)
              var imgArr = []
              var image = {}
              for (var i in files) {
                console.log(i)
                imgArr.push(i)
              }
              for (var j = 0; j < imgArr.length; j++) {
                var fileName = imgArr[j]
                var buffer = zip.file(imgArr[j]).async('arraybuffer')
                var str = _this._arrayBufferToBase64(buffer)
                var pIndex = fileName.indexOf('.')
                var type = fileName.substr(pIndex + 1)
                var re = 'data:image/' + type + ';base64,'
                image[fileName] = re + str
              }
              console.log(image)
            })
          }
        } else {
          this.size = this.size + file.size
          this.imgFileList.push(file)
          let reader = new FileReader()
          let image = new Image()
          let _this = this
          reader.readAsDataURL(file)
          reader.onload = function () {
            file.src = this.result
            image.onload = function () {
              let width = image.width
              let height = image.height
              file.width = width
              file.height = height
              _this.imgList.push({
                file
              })
            }
            image.src = file.src
          }
        }
      },
      _arrayBufferToBase64 (buffer) {
        var binary = ''
        var bytes = new Uint8Array(buffer)
        var len = bytes.byteLength
        for (var i = 0; i < len; i++) {
          binary += String.fromCharCode(bytes[i])
        }
        return window.btoa(binary)
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
