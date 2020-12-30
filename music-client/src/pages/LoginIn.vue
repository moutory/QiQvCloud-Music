<template>
    <div>
        <login-logo/>
        <div class="login">
            <div class="login-head">
                <span>用户登录</span>
            </div>
            <!-- 登录表单 -->
            <el-form class="demo-ruleForm" label-width="70px" :model="loginForm" ref="loginForm" :rules="rules">
                <el-form-item prop="username" label="用户名">
                    <el-input placeholder="用户名" v-model="loginForm.username"></el-input>
                </el-form-item>
                <el-form-item prop="password" label="密码">
                    <el-input placeholder="密码" v-model="loginForm.password" type="password"></el-input>
                </el-form-item>
                
                <el-form-item prop="verifyCode" label="验证码">
                    <el-input placeholder="请输入验证码" v-model="loginForm.verifyCode" > </el-input>
                    <div class="verify">
                        <img :src="verifyCodePicUrl">
                        <el-button type="text" @click="resetVerifyCode" :disabled="isDisable" >看不清楚换一张</el-button>
                    </div>
                </el-form-item>
                
                <div class="login-btn">
                    <el-button @click="goSignUp">注册</el-button>
                    <el-button @click="handleLoginIn" type="primary">确定</el-button>
                </div>
            </el-form>
        </div>
    </div>
</template>
<script>
import LoginLogo from '../components/LoginLogo';
import {rules} from '../assets/data/form';
import {login,getVerifyCode} from '../api/index';
import {mixin} from '../mixins/index'
export default {
    name: 'login-in',
    components: {
        LoginLogo
    },
    mixins: [mixin],
    data(){
        return {
            loginForm: {
                username: '',
                password: '',
                verifyCode: '',
            },
            verifyCodePicUrl:'',
            isDisable: false,
            rules: []
        }
    },
    created(){
        this.rules = rules;
        this.getVerifyCodePic();
    },
    methods: {
        goSignUp(){
            this.changeActiveIndex('注册');
            this.$router.push({path: `/sign-up`})
        },
        handleLoginIn(){
            var that = this;
            that.$refs['loginForm'].validate(valid =>{
                if(valid){
                    var flag = that.checkVerifyCode(that.loginForm.verifyCode);
                    if(!flag){
                        return;
                    }
                    var loginFormJson = JSON.stringify(this.loginForm);
                    login(loginFormJson).then(res => {
                        if(res.code == 200 && res.data){
                            let consumer = res.data;
                            that.notify('登录成功','success');
                            that.$store.commit('setUserToken',consumer.userToken);
                            console.log(window.sessionStorage.getItem('userToken'));
                            that.$store.commit('setLoginIn',true);
                            that.$store.commit('setAvator',consumer.avator);
                            that.$store.commit('setUsername',consumer.username);
                            that.$store.commit('setUserId',consumer.id);
                            setTimeout(function(){
                                that.$router.push({path: '/'})
                            },1000)
                        }else if(res.code ==500 && res.msg){
                            that.$message.error(res.msg);
                        }else{
                            that.$message.error('登录失败');
                        }
                    }).catch(err => {
                        console.log(err);
                    })
                }else{
                    this.$message.error('输入信息格式有误！！')
                }
            });
        },
        changeActiveIndex(name){
            if(name){
                this.$store.commit('setActiveName',name);
            }
        },
        // 重新生成验证码
        resetVerifyCode(){
            this.isDisable=true;
            this.getVerifyCodePic();
            setTimeout(() => {
                this.isDisable=false;
            },1500)
        },
        // 校验验证码格式是否正确
        checkVerifyCode(verifyCode){
            var pattern = /[0-9A-Za-z]{4}/g;
            console.log(verifyCode)
            if(!verifyCode || verifyCode == ''){
                this.$message.error('请输入验证码');
                return false;
            }else if(verifyCode.length < 4){
                this.$message.error('验证码不得小于4位');
                return false;
            }else if(!pattern.exec(verifyCode)){
                this.$message.error('验证码不合法');
                return false;
            }else{
                return true;
            }
        },
        // 获取验证码信息
        getVerifyCodePic(){
            var that = this;
            getVerifyCode().then(res => {
                 if(res.code == 200 && res.data){
                    that.loginForm.verifyKey = res.data.key;
                    that.verifyCodePicUrl = "data:image/png;base64," + res.data.image;
                }else if(res.code ==500 && res.msg){
                    that.$message.error(res.msg);
                }else{
                    that.$message.error('获取验证码失败');
                }
            }).catch(err => {
                console.log(err);
            })
        },
        // 节流函数
        throttle(func, delay) {
            let timeout
            return function() {
                clearTimeout(timeout) // 如果持续触发，那么就清除定时器，定时器的回调就不会执行。
                timeout = setTimeout(() => {
                func.apply(this, arguments)
                }, delay)
            }
        },

    }
}
</script>
<style lang="scss" scoped>
@import '../assets/css/login-in.scss';
.verify {
    margin-top: 10px;
}
</style>

