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
import {login} from '../api/index';
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
                password: ''
            },
            rules: []
        }
    },
    created(){
        this.rules = rules;
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
                    var loginFormJson = JSON.stringify(this.loginForm);
                    login(loginFormJson).then(res => {
                        if(res.code == 200 && res.data){
                            let consumer = res.data;
                            that.notify('登录成功','success');
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
                    this.$message.error('用户名或密码格式有误！！')
                }
            });
        },
        changeActiveIndex(name){
            if(name){
                this.$store.commit('setActiveName',name);
            }
        }
    }
}
</script>
<style lang="scss" scoped>
@import '../assets/css/login-in.scss';
</style>

