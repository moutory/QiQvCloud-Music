<template>
    <div class="header">
        <div class="collapse-btn" @click="collapseChange">
            <i class="el-icon-menu"></i>
        </div>
        <div class="logo">
            music后台管理
        </div>
        <!-- 全屏按钮 -->
        <div class="header-right">
            <div class="btn-fullscreen" @click="handleFullScreen">
                <el-tooltip :content="isFullScreen?'取消全屏':'全屏'" placement="bottom">
                    <i class="el-icon-rank"></i>
                </el-tooltip>
            </div>
            <div class="user-avator">
                <img src="../assets/img/user.jpg">
            </div>
            <el-dropdown class="user-name" trigger="click" @command="handleCommand">
                <span class="el-dropdown-link">
                    {{userName}}
                    <i class="el-icon-caret-bottom"></i>
                </span>
                <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item command="logout">退出登录</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
        </div>
    </div>
</template>
<style scoped>
.header {
    position: relative;
    background-color:  #253041;
    box-sizing: border-box;
    height: 70px;
    width: 100%;
    font-size: 22px;
    color: #ffffff
}
.collapse-btn {
    float: left;
    padding: 0 21px;
    cursor: pointer;
    line-height: 70px;
}
.logo {
    float: left;
    line-height: 70px;
}
.header-right{
    float: right;
    padding-right: 50px;
    display: flex;
    align-items: center;
    height: 70px;
}
.btn-fullscreen{
    transform:rotate(45deg);
    margin-right: 5px;
    font-size: 24px;
}
/* 右上角的头像 */
.user-avator{
    margin-left: 20px;
}
.user-avator img{
    display: block;
    width: 40px;
    height: 40px;
    border-radius: 50%;
}

/* 退出登录 */
.user-name{
    margin-left: 10px;
}
.el-dropdown-link{
    color: #ffffff;
    cursor: pointer;
}
</style>>
<script>
import bus from '../assets/js/bus';
export default {
    data(){
        return {
            collapse: false,
            isFullScreen: false
        }
    },
    methods: {
        collapseChange(){
            this.collapse = !this.collapse;
            bus.$emit('collapse',this.collapse);
        },
        handleFullScreen(){
            if(this.isFullScreen){ 
                // 根据各个浏览器的api，退出全屏
                if(document.exitFullscreen){
                    document.exitFullscreen();
                }else if(document.webkitCancelFullScreen){ //safari 、Chrome
                    document.webkitCancelFullScreen();
                }else if(document.mozCancelFullScreen){ // 火狐
                    document.mozCancelFullScreen();
                }else if(document.msExitFullScreen){  //ie
                    document.msExitFullScreen();
                }
            }else{
                // 根据各个浏览器的api，进入全屏
                let element = document.documentElement;
                if(element.requestFullscreen){
                    element.requestFullscreen();
                }else if(element.webkitRequestFullScreen){  //safari 、Chrome
                    element.webkitRequestFullScreen();
                }else if(element.mozRequestFullScreen){ //火狐
                    element.mozRequestFullScreen();
                }else if(element.msRequestFullScreen){ //ie
                    element.msRequestFullScreen();
                }
            }
                this.isFullScreen = !this.isFullScreen;
        },
        /* 清除缓存中的用户名 */
        handleCommand(command){
            if(command == "logout"){
                localStorage.removeItem('username');
                this.$router.push('/');
            }
        }
    },
    /* 获取缓存中的用户名 */
    computed: {
       userName(){
           return localStorage.getItem('userName');
       }
    }
}
</script>
