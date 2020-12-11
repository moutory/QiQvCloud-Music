<template>
    <div>
        <login-logo/>
        <div class="signUp">
            <div class="signUp-head">
                <span>用户注册</span>
            </div>
            <!-- 注册表单 -->
            <el-form class="demo-ruleForm" label-width="70px" :model="registerForm" ref="registerForm" :rules="rules">
                <el-form-item prop="username" label="用户名">
                    <el-input placeholder="用户名" v-model="registerForm.username"></el-input>
                </el-form-item>
                <el-form-item prop="password" label="密码">
                    <el-input placeholder="密码" v-model="registerForm.password" type="password"></el-input>
                </el-form-item>
                <el-form-item prop="sex" label="性别">
                    <el-radio-group v-model="registerForm.sex">
                        <el-radio :label="0">男</el-radio>
                        <el-radio :label="1">女</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item prop="phoneNum" label="手机号">
                    <el-input placeholder="手机号" v-model="registerForm.phoneNum"></el-input>
                </el-form-item>
                <el-form-item prop="email" label="邮箱">
                    <el-input placeholder="邮箱" v-model="registerForm.email"></el-input>
                </el-form-item>
                <el-form-item prop="birth" label="生日">
                    <el-date-picker v-model="registerForm.birth" placeholder="请选择出生日期" style="width: 100%"></el-date-picker>
                </el-form-item>
                <el-form-item prop="location" label="地区">
                    <el-select v-model="registerForm.location" placeholder="居住地" style="width: 100%">
                        <el-option v-for="item in cites" :label="item.label" :value="item.value" :key="item.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item prop="introduction" label="个性签名">
                    <el-input placeholder="说点什么吧..." v-model="registerForm.introduction"></el-input>
                </el-form-item>
                <div class="login-btn">
                    <el-button @click="goBack(-1)">取消</el-button>
                    <el-button @click="signUp" type="primary">确定</el-button>
                </div>
            </el-form>
        </div>
    </div>
</template>
<script>
import LoginLogo from '../components/LoginLogo';
import {rules,cities} from '../assets/data/form';
import {addConsumer} from '../api/index';
import {mixin} from '../mixins/index'
export default {
    name: 'sign-up',
    mixins: [mixin],
    components: {
        LoginLogo
    },
    data(){
        return {
            registerForm: {
                username:'',
                password:'',
                sex:'',
                phoneNum:'',
                email:'',
                birth:'',
                introduction:'',
                location:'',
                avator: '',
                createby: ''
            },
            cites: [],          //所有的地区 --省
            rules: {}            //表单提交的规则
        }
    },
    created(){
        this.rules = rules;
        this.cites = cities;
    },
    methods: {
        goBack(index){
            this.$router.go(index);
        },
        signUp(){
            var that = this;
            that.$refs['registerForm'].validate(valid =>{
                if(valid){
                    that.registerForm.createby = 'PC-State';
                    that.registerForm.avator = '/img/avatorImages/user.jpg';
                    var registerFormJSON = JSON.stringify(that.registerForm);
                    addConsumer(registerFormJSON).then(res => {
                        if(res.code == 200){
                            that.notify('注册成功','success');
                            setTimeout(function(){
                                that.$router.push({path: '/'})
                            },2000)
                        }else if(res.code ==500 && res.msg){
                            that.$message.error(res.msg);
                        }else{
                            that.$message.error('注册失败');
                        }
                    }).catch(err =>{
                        console.log(err);
                    })
                }else{
                    this.$message.error('格式不正确，请重新输入');
                }
             })
        }
    }
}
</script>
<style lang="scss" scoped>
@import '../assets/css/sign-up.scss';
</style>