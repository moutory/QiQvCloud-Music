import Vue from 'vue'
import App from './App'
import router from './router/index'
import ElementUI from 'element-ui'
import store from './store/index'
import 'element-ui/lib/theme-chalk/index.css'
import './assets/css/main.css'
import 'babel-polyfill'
import VCharts from 'v-charts'

Vue.use(ElementUI)
Vue.use(VCharts)
/* 我的项目 */

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
