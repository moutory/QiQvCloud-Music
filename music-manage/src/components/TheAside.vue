<template>
    <div class="sidebar">
        <el-menu
         class="sidebar-el-menu"
         :default-active="onRoutes"
         :collapse="collapse"
         background-color="#334256"
         text-color="#ffffff"
        active-text-color="#20a0ff"
        router
        >
        <template v-for="item in items">
            <template>
                <el-menu-item :index="item.index" :key="item.index">
                    <i :class="item.icon"></i>
                    {{item.title}}
                </el-menu-item>
            </template>
        </template>
        </el-menu>
    </div>
</template>
<style  scoped>
.sidebar {
    display: block;
    position: absolute;
    top: 70px;
    bottom: 0;
    left: 0;
    overflow-y: scroll;
 background-color: #334256;
}
.sidebar-el-menu{
    widows: 150px;
}
</style>
<script>
import bus from '../assets/js/bus'
export default {
    data(){
        return{
            collapse:false,
            items:[
                {
                icon: 'el-icon-element',
                index: 'Info',
                title: '系统首页'
                },
                {
                icon: 'el-icon-element',
                index: 'Consumer',
                title: '用户管理'
                },
                {
                icon: 'el-icon-element',
                index: 'Singer',
                title: '歌手管理'
                },
                {
                icon: 'el-icon-element',
                index: 'SongList',
                title: '歌单管理'
                }
            ]
        }
    },
    computed: {
        onRoutes(){
            return this.$route.path.replace('/','');
        }
    },
    created() {
        bus.$on('collapse',msg =>{
            this.collapse=msg;
        })
    }
}
</script>