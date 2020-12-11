import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      component: resolve => require(['../pages/Login.vue'], resolve)
    },
    {
      path: '/Home',
      component: resolve => require(['../components/Home.vue'], resolve),
      children: [
        {
          path: '/Info',
          component: resolve => require(['../pages/Info.vue'], resolve)
        },
        {
          path: '/Consumer',
          component: resolve => require(['../pages/Consumer.vue'], resolve)
        },
        {
          path: '/Singer',
          component: resolve => require(['../pages/Singer.vue'], resolve)
        },
        {
          path: '/SongList',
          component: resolve => require(['../pages/SongList.vue'], resolve)
        },
        {
          path: '/Song',
          component: resolve => require(['../pages/Song.vue'], resolve)
        },
        {
          path: '/ListSong',
          component: resolve => require(['../pages/ListSong.vue'],resolve)
        },
        {
          path: '/Collect',
          component: resolve => require(['../pages/Collect.vue'],resolve)
        },
        {
          path: '/Comment',
          component: resolve => require(['../pages/Comment.vue'],resolve)
        },
      ]
    }
    
  ]
})
