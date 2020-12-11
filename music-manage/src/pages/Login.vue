<template>
  <div class="login-wrap">
    <div class="ms-title">music 后台管理登录</div>
    <div class="ms-login">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm">
        <el-form-item prop="username">
          <el-input v-model="ruleForm.username" placeholder="用户名"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input type="password" v-model="ruleForm.password" placeholder="密码"></el-input>
        </el-form-item>
        <div class="login-btn">
          <el-button type="primary" @click="submitForm">登录</el-button>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
import {getLoginStatus} from '../api/index';
import {mixin} from '../mixins/index';
export default {
  mixins:[mixin],
  data:function(){
    return {
      ruleForm:{
        username: 'admin',
        password: '123456'
      },
      rules: {
        username:[
          {required:true,message:'请输入用户名',trigger:'blur'}
        ],
        password:[
          {required:true,message:'请输入密码',trigger:'blur'}
        ]
      }
    }
  },
  methods:{
    submitForm(){
        let params = new URLSearchParams();
        params.append('username',this.ruleForm.username);
        params.append('password',this.ruleForm.password);
        getLoginStatus(params)
        .then((res)=>{
          if(res.code == 200){
            localStorage.setItem('userName',this.ruleForm.username);
            this.$router.push('/Info');
            this.notify("登录成功","success");
            /* 将用户名存到本地缓存中 */
          }else {
            this.notify(res.msg,"error");
          }
        })        
    }
  }
}
</script>

<style scoped>
.login-wrap {
  position: relative;
  background: url("../assets/img/background.jpg");
  background-attachment: fixed;
  background-position: center;
  background-size: cover;
  width: 100%;
  height: 100%;
}
.ms-title {
  position: absolute;
  top: 50%;
  width: 100%;
  margin-top: -230px;
  text-align: center;
  font-size: 30px;
  font-weight: 600;
  color: #fff;
}
.ms-login {
 position: absolute;
 left: 50%;
 top: 50%;
 width: 300px;
 height: 160px;
 background: #fff;
 margin-left: -190px;
 margin-top: -150px;
 padding: 40px; 
 border-radius:5px;
}
.login-btn{
 text-align: center;
}
.login-btn button{
  width: 100%;
  height: 36px;
}
</style>
