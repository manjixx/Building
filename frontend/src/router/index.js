import Vue from 'vue'
import Router from 'vue-router'
import Home from '../components/Home'
// 导入刚才编写的组件
import Login from '../components/Login'
import Register from '../components/Register'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    // 下面都是固定的写法
    {
      path: '/',
      name: 'Default',
      redirect: '/admin',
      component: Home
    },
    // {
    //   // home页面并不需要被访问，只是作为其它组件的父组件
    //   path: '/admin',
    //   name: 'Home',
    //   component: Home,
    //   redirect: '/admin'
    // },
    {
      path: '/admin',
      name: 'AdminIndex',
      component: () => import('../components/admin/AdminIndex'),
      meta: {
        requireAuth: true
      },
      children: [
        {
          path: '/admin/questionnaire/feedback',
          name: 'Feedback',
          component: () => import('../components/admin/questionnaire/Feedback'),
          meta: {
            requireAuth: true
          }
        }
      ]
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    }
  ]
})

// 用于创建默认路由
export const createRouter = routes => new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Default',
      redirect: '/home',
      component: Home
    },
    {
      // home页面并不需要被访问，只是作为其它组件的父组件
      path: '/home',
      name: 'Home',
      component: Home,
      redirect: '/admin'
    },
    {
      path: '/admin',
      name: 'AdminIndex',
      component: () => import('../components/admin/AdminIndex'),
      meta: {
        requireAuth: true
      },
      children: [
        {
          path: '/admin/questionnaire/feedback',
          name: 'Feedback',
          component: () => import('../components/admin/questionnaire/Feedback'),
          meta: {
            requireAuth: true
          }
        }
      ]
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    }
  ]
})
