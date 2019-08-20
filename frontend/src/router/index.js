import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Register from '@/components/Register'
import Login from '@/components/Login'
import NewProject from '@/components/NewProject'
import Requestor from '@/components/Requestor'
import PersonalCenter from '@/components/PersonalCenter'
import test2 from '@/components/test2'
import bench from '@/components/bench'
import newLogin from '@/components/newLogin'
import newRegister from '@/components/newRegister'
import testbench from '@/components/testbench'
import test from '@/components/test'
import bbsHome from '@/components/bbsHome'
import BBSTopic from '@/components/BBSTopic'
import FileDownload from '@/components/FileDownload'
import CourseDetail from '@/components/CourseDetail'
import teacherbench from '@/components/teacherbench'
import publish from '@/components/publish'
import CourseDetail_T from '@/components/CourseDetail_T'
import admin from '@/components/admin'

Vue.use(Router)

// export default new Router({
//   routes: [
//     {
//       path: '/',
//       name: 'HelloWorld',
//       component: HelloWorld
//     }
//   ]
// })

export default new Router({ // 创建 router 实例，然后传 `routes` 配置
  routes: [

    {
      path: '/6',
      name: 'Home', // 路径名
      component: Home
    },
    {
      path: '/2',
      name: 'Register', // 路径名
      component: Register
    },
    {
      path: '/3',
      name: 'Login', // 路径名
      component: Login
    },
    {
      path: '/4',
      name: 'NewProject', // 路径名
      component: NewProject
    },
    {
      path: '/5',
      name: 'Requestor', // 路径名
      component: Requestor
    },
    {
      path: '/',
      name: 'PersonalCenter', // 路径名
      component: PersonalCenter
    },

    {
      path: '/8',
      name: 'test2', // 路径名
      component: test2
    },
    {
      path: '/9',
      name: 'bench', // 路径名
      component: bench
    },
    {
      path: '/login',
      name: 'newLogin', // 路径名
      component: newLogin
    },
    {
      path: '/register',
      name: 'newRegister', // 路径名
      component: newRegister
    },
    {
      path: '/testbench',
      name: 'testbench', // 路径名
      component: testbench
    },

    {
      path: '/test',
      name: 'test', // 路径名
      component: test
    },
    {
      path: '/bbsHome',
      name: 'bbsHome', // 路径名
      component: bbsHome
    },
    {
      path: '/BBSTopic',
      name: 'BBSTopic', // 路径名
      component: BBSTopic
    },
    {
      path: '/FileDownload',
      name: 'FileDownload', // 路径名
      component: FileDownload
    },
    {
      path: '/CourseDetail',
      name: 'CourseDetail', // 路径名
      component: CourseDetail
    },

    {
      path: '/teacherbench',
      name: 'teacherbench', // 路径名
      component: teacherbench
    },

    {
      path: '/publish',
      name: 'publish', // 路径名
      component: publish
    },
    {
      path: '/CourseDetail_T',
      name: 'CourseDetail_T', // 路径名
      component:  CourseDetail_T
    },
    {
      path: '/admin',
      name: 'admin', // 路径名
      component:  admin
    }





  ]
})
