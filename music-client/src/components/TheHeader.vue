<template>
    <div class="the-header">
    <div class="header-logo" @click="goHome">
        <svg class="icon">
            <use xlink:href="#icon-erji"></use>
        </svg>
        <span>music</span>
    </div>
    <ul class="navbar">
        <li :class="{active: item.name == activeName}" v-for="item in navMsg" :key="item.path" @click="goPage(item.path,item.name)">
            {{item.name}}
        </li>
        <li>
            <div class="header-search">
                <input type="text" placeholder="搜索音乐" @keyup.enter="goSearch()" v-model="keywords">
                <div class="search-btn" @click="goSearch()">
                    <svg class="icon">
                        <use xlink:href="#icon-sousuo"></use>
                    </svg>
                </div>
            </div>
        </li>
        <!-- 右侧菜单 -->
        <li v-for="item in loginMsg" :key="item.path" @click="goPage(item.path,item.name)" 
            :class="{active: item.name == activeName}"
            v-show="!loginIn">
            {{item.name}}
        </li>
    </ul>
        <div class="header-right" v-show="loginIn">
            <div id="user">
                <img :src="attachImageUrl(avator)"/>
            </div>
            <!-- 导航栏下拉框 -->
           <ul class="menu">
               <li v-for="(item,index) in menuList" :key="index" @click="goMenuList(item.path)">
                   {{item.name}}
               </li>
           </ul>
            <div>
                {{username}}
            </div>

        </div>
    </div>

</template>

<script>
import {navMsg,loginMsg,menuList} from '../assets/data/header'
import {mapGetters} from 'vuex'
export default {
    name: 'the-header',
    data(){
        return{
            navMsg: [], // 左侧导航栏
            loginMsg: [], // 右侧登录注册功能栏
            menuList: [], // 用户下拉菜单
            keywords: ''
        }
    },
    created(){
        this.navMsg = navMsg;
        this.loginMsg = loginMsg;
        this.menuList = menuList;
    },
    computed:{
        ...mapGetters([
            'activeName',
            'loginIn',
            'username',
            'avator'
        ])
    },
    mounted(){
        document.querySelector('#user').addEventListener('click',function(e){
            document.querySelector('.menu').classList.add('show');
            e.stopPropagation();        //阻止冒泡
        },false);
        document.querySelector('.menu').addEventListener('click',function(e){
            e.stopPropagation();     // 点击菜单内部时，阻止事件冒泡。这样菜单就不会关闭
        })
        document.addEventListener('click',function(e){
            document.querySelector('.menu').classList.remove('show');
        },false);
    },
    methods:{
        goHome(){
            this.$router.push({path:'/'});
        },
        goPage(path,name){
            if(!this.loginIn && path == '/my-music'){
                this.$message.warning('登录后才能访问该页面!!!');
                return;
            }
            this.$store.commit('setActiveName',name);
            this.$router.push({path: path});
        },
        goSearch(){
            this.$router.push({path: '/search',query: {keywords: this.keywords}})
        },
        /**
         * 
         */
        attachImageUrl(picUrl){
            return picUrl ?this.$store.state.configure.HOST + picUrl:picUrl;
        },
        /**
         *  点击下拉框内容触发，如果是退出，则清除用户当前缓存
         *  否则，则跳转到对应页面处理
         */
        goMenuList(path){
            if(path == 0){
                this.$store.commit('setLoginIn',false);
                this.$store.commit('setIsActive',false);//退出后，歌曲收藏关系重新初始化为未收藏
                this.$router.go(0);
            }else{
                this.$router.push({path : path});
            }
        }
    }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/the-header.scss'
</style>