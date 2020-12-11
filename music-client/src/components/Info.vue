<template>
    <div>
        <div class="info">
            <div class="title">
                <span>编辑个人资料</span>
            </div>
            <hr/>
            <div class="personal">
                 <el-form class="demo-ruleForm" label-width="70px" :model="editForm" ref="editForm" :rules="rules">
                <el-form-item prop="username" label="用户名">
                    <el-input placeholder="用户名" v-model="editForm.username"></el-input>
                </el-form-item>
                <el-form-item prop="password" label="密码">
                    <el-input placeholder="密码" v-model="editForm.password" type="password"></el-input>
                </el-form-item>
                <el-form-item label="性别" prop="sex">
                    <input type="radio" name="sex" value="0" v-model="editForm.sex">&nbsp;男&nbsp;&nbsp;
                    <input type="radio" name="sex" value="1" v-model="editForm.sex">&nbsp;女
                </el-form-item>
                <el-form-item prop="phoneNum" label="手机号">
                    <el-input placeholder="手机号" v-model="editForm.phoneNum"></el-input>
                </el-form-item>
                <el-form-item prop="email" label="邮箱">
                    <el-input placeholder="邮箱" v-model="editForm.email"></el-input>
                </el-form-item>
                <el-form-item prop="birth" label="生日">
                    <el-date-picker v-model="editForm.birth" placeholder="请选择出生日期" style="width: 100%" :editable="false" ></el-date-picker>
                </el-form-item>
                <el-form-item prop="location" label="地区">
                    <el-select v-model="editForm.location" placeholder="居住地" style="width: 100%">
                        <el-option v-for="item in cites" :label="item.label" :value="item.value" :key="item.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item prop="introduction" label="个性签名">
                    <el-input placeholder="说点什么吧..." v-model="editForm.introduction"></el-input>
                </el-form-item>
                <div class="btn">
                   <div @click="editSave">保存</div>
                   <div @click="goBack(-1)">返回</div>
                </div>
            </el-form>
            </div>
        </div>
    </div>
</template>
<script>
import {rules,cities} from '../assets/data/form';
import {mixin} from '../mixins/index';
import {queryUserById,updateConsumer} from '../api/index';
export default {
    name: 'user-info',
    mixins: [mixin],
    data(){
        return {
            editForm: {
                id: '',
                username:'',
                password:'',
                sex:'',
                phoneNum:'',
                email:'',
                birth:'',
                introduction:'',
                location:'',
            },
            cites: [],          //所有的地区 --省
            rules: {}            //表单提交的规则
        }
    },
     created(){
        this.rules = rules;
        this.cites = cities;
    },
    mounted(){
        this.getUserMsg();
    },
    methods: {
        /**
         * 获取用户信息
         */
        getUserMsg(){
            if(this.loginIn && this.userId){
                queryUserById(this.userId).then( res => {
                     if(res.code == 200 && res.data){
                        this.editForm.birth = this.attachBirth(res.data.birth);
                        this.editForm.phoneNum = res.data.phoneNum;
                        this.editForm.location = res.data.location;
                        this.editForm.sex = res.data.sex;
                        this.editForm.username = res.data.username;
                        this.editForm.introduction = this.attachUserIntroduction(res.data.introduction);
                        this.editForm.email = res.data.email;
                        this.editForm.password = res.data.password;
                        this.editForm.id = res.data.id;
                        
                    }else if(res.code==500 && res.msg){
                        this.$message.error(res.msg);
                    }else{
                        this.$message.error('查询用户信息失败');
                    }
                }).catch( err => {
                    console.log(err);
                })
            }else{
                username= '';
                userSex= '';
                userBirth= '';
                userLocation= '';
                userPhone= '';
                this.$message.warning('请先登录');

            }
        },
        /** 保存编辑 */
        editSave(){
            this.$refs['editForm'].validate(valid =>{
                if(valid){
                var consumerJson = JSON.stringify(this.editForm);
                updateConsumer(consumerJson)
                    .then(res=>{
                        if(res.code == 200){
                            this.getUserMsg();
                            this.notify('修改成功','success')
                        }else if(res.code == 500){
                            this.notify('修改失败','error');
                        }
                    })
                    .catch(err=>{
                        console.log(err);
                    });
                }else{
                    this.$message.error("数据有误，请修改后再提交");
                }
            })
        },
        goBack(index){
            this.$router.go(index);
        }
    }
}
</script>
<style lang="scss" scoped>
@import '../assets/css/info.scss';
</style>