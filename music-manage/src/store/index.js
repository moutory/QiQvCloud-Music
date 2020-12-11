/* 使用Vuex，封裝静态资源获取路径给全局使用 */
import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)

const store = new Vuex.Store({
    state: {
        HOST: 'http://localhost:8888',
        isPlay: false, //是否正在播放中
        url: '', //歌曲地址
        id: '' //歌曲id
    },
    getters: {
        isPlay: state => state.isPlay,
        url: state=>state.url,
        id: state=>state.id
    },
    mutations: {
        setIsPlay: (state,isPlay) => {state.isPlay = isPlay},
        setUrl: (state,url)=>{state.url = url},
        setId: (state,id)=>{state.id=id}
    }
})

export default store;