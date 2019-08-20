
import Vue from 'vue' // 引入vue模块
import App from './App' // 引入vue组件
import router from './router' // 引入路由配置文件
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

import DropdownPlugin from './m/dropdown'

import NavbarPlugin from './m/navbar'

import ContextMenuPlugin from './m/context-menu'

import MButton from '@/m/button'

import MSwitch from '@/m/switch'

import MAlert from '@/m/alert'

import MCheckbox from '@/m/checkbox'

import MInput from '@/m/input'

import MLoading from '@/m/loading'

import Mkeyboard from '@/m/keyboard'

import MNumberkeyboard from '@/m/number-keyboard'

import MBox from '@/m/box'

import MBackTop from '@/m/back-top'

import MLoader from '@/m/loader'

import MContainer from '@/m/container'


import moment from 'moment'
import tab from '@/components/bbs/tab'

import VueCropper from 'vue-cropper'
import vueKanban from 'vue-kanban'
import './assets/kanban.scss'

import tinymce from 'vue-tinymce-editor'

Vue.component('tinymce', tinymce)
//bbs
Vue.config.productionTip = false
Vue.prototype.$moment = moment
Vue.prototype.$tab = tab

Vue.use(VueCropper)
Vue.use(vueKanban)

Vue.use(DropdownPlugin)

Vue.use(NavbarPlugin)

Vue.use(ContextMenuPlugin)

Vue.use(MButton)

Vue.use(MSwitch)

Vue.use(MAlert)

Vue.use(MCheckbox)

Vue.use(MInput)

Vue.use(MLoading)

Vue.use(Mkeyboard)

Vue.use(MNumberkeyboard)

Vue.use(MBox)

Vue.use(MBackTop)

Vue.use(MLoader)

Vue.use(MContainer)

Vue.config.productionTip = false // 关闭生产模式下给出的提示
Vue.use(ElementUI)
new Vue({ // 创建一个 Vue 的根实例
  el: '#app', // 挂载id,这个实例下所有的内容都会在index.html 一个id为app的div下显示
  router, // 注入路由配置。
  template: '<App/>', // 配置根模板 即打开页面显示那个组件
  components: { App } // 注入组件
})




