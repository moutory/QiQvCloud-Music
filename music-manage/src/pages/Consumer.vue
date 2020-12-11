<template>
    <div class="table">
        <div class="container">
            <div class="handle-box">
                <el-button type="danger" size="mini" @click="batchDelDialogVisible=true">批量删除</el-button>
                <el-input size="mini" class="handle-input" placeholder="请输入用户名" v-model="input_word"></el-input>
                <el-button type="primary" size="mini" @click="centerDialogVisible = true">添加用户</el-button>
            </div>
        </div>
        <!-- 头像 -->
        <el-table size="mini" border style="width:100%" height="500px" :data="data" @selection-change="handleSelectItem">
            <!-- 选项框 -->
            <el-table-column type="selection" width="40px"></el-table-column>
            <el-table-column label="用户图片" width="110px" align="center">
                <template slot-scope="scope">
                    <div class="consumer-img">
                        <img :src="getUrl(scope.row.avator)" style="width: 100%" />
                    </div>
                    <el-upload :action="uploadUrl(scope.row.id)" :before-upload="checkUploadFile"
                    :on-success="handleAvatorSuccess">
                        <el-button size="mini">更新头像</el-button>
                    </el-upload>
                </template>
            </el-table-column>
        <!-- 用户名称 -->
            <el-table-column prop="username" label="用户名称" width="120px" align="center"></el-table-column>
            <!-- 用户性别 -->
            <el-table-column label="性别" width="50px" align="center">
                <template slot-scope="scope">
                    {{getCNSex(scope.row.sex)}}
                </template>
            </el-table-column>
            <!-- 生日 -->
            <el-table-column label="出生日期" width="120px" align="center">
                <template slot-scope="scope">
                    {{attachBirth(scope.row.birth)}}
                </template>
            </el-table-column>
            <!-- 地区 -->
            <el-table-column prop="location" label="地区" width="100px" align="center"></el-table-column>
            <!-- 简介 -->
            <el-table-column label="简介" align="center">
                <template slot-scope="scope">
                    <p style="height:100px;overflow:scroll">{{scope.row.introduction}}</p>
                </template>
            </el-table-column>
            <!-- 收藏 -->
            <el-table-column label="用户收藏歌单" align="center">
                <template slot-scope="scope">
                    <el-button size="mini" @click="toCollectPage(scope.row.id)">收藏</el-button>
                </template>
            </el-table-column>
            <!-- 操作 -->
            <el-table-column label="操作" width="150px" align="center">
                <template slot-scope="scope">
                    <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
                    <el-button size="mini" type="danger" @click="handleDelete(scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <div class="pagination">
            <el-pagination
                background
                layout ="total,prev,pager,next"
                :current-page="currentPage"
                :page-size="pageSize"
                :total="tableData.length"
                @current-change="handleCurrentChange"
            >
            </el-pagination>
        </div>

        <!-- 添加用户的弹框 -->
        <el-dialog title="添加用户" :visible.sync="centerDialogVisible" width="500px" center>
            <el-form :model="registerForm" ref="registerForm" label-width="80px" :rules="rules">
                <!-- 用户名 -->
                <el-form-item prop="username" label="用户名" size="mini">
                    <el-input v-model="registerForm.username" placeholder="用户名"></el-input>
                </el-form-item>
                <!-- 密码 -->
                <el-form-item prop="password" label="密码">
                    <el-input type="password" placeholder="密码" v-model="registerForm.password"></el-input>
                </el-form-item>
                <!-- 性别 -->
                <el-form-item label="性别" size="mini">
                    <input type="radio" name="sex" value="0" v-model="registerForm.sex">&nbsp;男&nbsp;&nbsp;
                    <input type="radio" name="sex" value="1" v-model="registerForm.sex">&nbsp;女
                </el-form-item>
                <!-- 手机号 -->
                <el-form-item prop="phoneNum" label="手机号" size="mini">
                    <el-input v-model="registerForm.phoneNum" placeholder="手机号"></el-input>
                </el-form-item>
                <!-- 电子邮箱  -->
                <el-form-item prop="email" label="邮箱" size="mini">
                    <el-input v-model="registerForm.email" placeholder="邮箱"></el-input>
                </el-form-item>
                <!-- 生日 -->
                <el-form-item prop="birth" label="出生日期" size="mini">
                    <el-date-picker type="date" placeholder="选择日期" v-model="registerForm.birth" style="width:100%"></el-date-picker>
                </el-form-item>
                <!-- 地区 -->
                <el-form-item prop="location"  label="地区" size="mini">
                    <el-input v-model="registerForm.location" placeholder="地区"></el-input>
                </el-form-item>
                <!-- 简介 -->
                <el-form-item  prop="introduction" label="签名" size="mini">
                    <el-input v-model="registerForm.introduction" placeholder="简介" type="textarea"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer">
                <el-button size="mini" @click="centerDialogVisible=false">取消</el-button>
                <el-button size="mini" @click="addConsumer">添加用户</el-button>
            </span>
        </el-dialog>

        <!-- 编辑用户的弹框 -->
        <el-dialog title="修改用户信息" :visible.sync="editDialogVisible" width="500px" center >
            <el-form :model="editForm" ref="editForm" label-width="80px" :rules="rules">
                <!-- 用户名 -->
                <el-form-item prop="username" label="用户名" size="mini">
                    <el-input v-model="editForm.username" placeholder="用户名"></el-input>
                </el-form-item>
                <!-- 密码 -->
                <el-form-item prop="password" label="密码" size="mini">
                    <el-input v-model="editForm.password" placeholder="请输入密码" type="password"></el-input>
                </el-form-item>
                <!-- 性别 -->
                <el-form-item label="性别" size="mini">
                    <input type="radio" name="sex" value="0" v-model="editForm.sex">&nbsp;男&nbsp;&nbsp;
                    <input type="radio" name="sex" value="1" v-model="editForm.sex">&nbsp;女
                </el-form-item>
                <!-- 手机号 -->
                <el-form-item prop="phoneNum" label="手机号" size="mini">
                    <el-input v-model="editForm.phoneNum" placeholder="请输入手机号"></el-input>
                </el-form-item>
                <!-- 电子邮箱 -->
                <el-form-item prop="email" label="邮箱" size="mini">
                    <el-input v-model="editForm.email" placeholder="请输入邮箱"></el-input>
                </el-form-item>
                <!-- 生日 -->
                <el-form-item prop="birth" label="出生日期" size="mini">
                    <el-date-picker type="date" placeholder="选择日期" v-model="editForm.birth" style="width:100%"></el-date-picker>
                </el-form-item>
                <!-- 地区 -->
                <el-form-item prop="location" label="居住地区" size="mini">
                    <el-input v-model="editForm.location" placeholder="地区"></el-input>
                </el-form-item>
                <!-- 简介 -->
                <el-form-item prop="introduction" label="签名" size="mini">
                    <el-input v-model="editForm.introduction" placeholder="签名" type="textarea"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer">
                <el-button size="mini" @click="editDialogVisible = false">取消</el-button>
                <el-button size="mini" @click="editSave">确认修改</el-button>
            </span>
        </el-dialog>

        <el-dialog title="删除用户" :visible.sync="delDialogVisible" width="300px" center>
            <div>删除后不可恢复，是否删除？</div>
            <span slot="footer">
            <el-button size="mini" @click="delDialogVisible=false">取消</el-button>
            <el-button size="mini" type="danger" @click="delConsumer">确认</el-button>
            </span>
        </el-dialog>
        <!-- 批量删除用户 -->
        <el-dialog title="删除用户" :visible.sync="batchDelDialogVisible" width="300px" center>
            <div>删除后不可恢复，是否删除？</div>
            <span slot="footer">
            <el-button size="mini" @click="batchDelDialogVisible=false">取消</el-button>
            <el-button size="mini" type="danger" @click="delSelConsumer">确认</el-button>
            </span>
        </el-dialog>
    </div>
</template>
<script>
import {addConsumer,getAllConsumer,updateConsumer,delConsumer} from '../api/index';
import {mixin} from '../mixins/index';
export default {
    mixins: [mixin],
    data(){
        return {
            tableData: [],
            tempData: [],
            centerDialogVisible: false,
            editDialogVisible: false, // 编辑窗口是否可见
            delDialogVisible: false, // 删除弹窗
            batchDelDialogVisible:false, // 批量删除弹窗
            delForm:{
                id: ''
            },
            registerForm:{
                username: '',
                password: '',
                sex: '',
                phoneNum: '',
                email: '',          
                avator: '',
                birth: '',
                location: '',
                introduction: '',
                createBy: ''
            },
            // 编辑窗口
            editForm:{
                id: '',
                username: '',
                password: '',
                sex: '',
                phoneNum: '',
                email: '',          
                avator: '',
                birth: '',
                location: '',
                introduction: '',
                updateBy: ''
            },
            input_word: '',
            //初始页
            currentPage: 1,
            pageSize: 5,
            multipleSelection:[],
            // 新建用户规则
            rules:{
                username:[
                    {required: true , message: '请输入用户名' , trigger: 'blur'}
                ],
                password:[
                    {required: true , message: '请输入密码' , trigger: 'blur'}
                ],
                phoneNum:[
                    {required: true , message: '请输入联系方式' , trigger: 'blur'}
                ],
                email:[
                    {required: true , message: '请输入邮箱' , trigger: 'blur'},
                    {type: 'email' , message:'请输入正确的邮箱',trigger: ['blur','change']}
                ],
                location:[
                    {required: true , message: '请输入居住地' , trigger: 'blur'}
                ],
                introduction:[
                    {required: true , message: '请输入个性签名' , trigger: 'blur'}
                ]
                
            }
        }
    },
    computed:{
        // 计算当前搜索结果表里的数据
        data(){
            if(this.tableData){
            return this.tableData.slice((this.currentPage-1)* this.pageSize,this.currentPage*this.pageSize);
            }
        }
    },
    methods: {
        addConsumer(){
            // 先校验提交的数据是否满足条件
            this.$refs['registerForm'].validate(valid =>{
                if(valid){
                    this.registerForm.avator = '/img/avatorImages/user.jpg';
                    let username = localStorage.getItem('userName');
                    if(username){
                    this.registerForm.createBy=username;
                    }
                    var consumerJson = JSON.stringify(this.registerForm);
                    addConsumer(consumerJson)
                    .then(res=>{
                        if(res.code == 200){
                            this.getAllConsumersData();
                            this.clearRegisterForm();
                            this.notify('添加成功',"success");
                        }else if(res.code == 500){
                            this.notify('添加失败','error');
                        }
                    })
                    .catch(err=>{
                        console.log(err);
                    });
                    this.centerDialogVisible = false;
                }else{
                    this.$message.error('数据格式有误，请重新确认');
                }
            })
        },
        getAllConsumersData(){
            // 清空原有的记录
            this.tableData=[];
            this.tempData=[];
            getAllConsumer().then(res =>{
                this.tableData = res.data;
                this.tempData = res.data;
            })
        },
        uploadUrl(id){
            return `${this.$store.state.HOST}/consumer/updateConsumerPic?id=${id}`
        },
        /** 转向歌曲管理页面 */
        songEdit(consumerId,username){
            this.$router.push({path:'/Song',query:{consumerId,username}})
        },
        handleCurrentChange(value){
            this.currentPage=value;
        },
        /* 弹出编辑弹框 */
        handleEdit(row){
            this.editDialogVisible = true;
            /* 将该行的值传到editForm */
            this.editForm = {
                id: row.id,
                username: row.username,
                password: row.password,
                sex: row.sex,
                phoneNum: row.phoneNum,
                email: row.email,
                birth: row.birth,
                introduction: row.introduction,
                location: row.location
            }
        },
        /** 保存编辑 */
        editSave(){
            this.$refs['registerForm'].validate(valid =>{
                if(valid){
                let username = localStorage.getItem('userName');
                if(username){
                this.editForm.updatetBy=username;
                }
                var consumerJson = JSON.stringify(this.editForm);
                console.log('hh');
                updateConsumer(consumerJson)
                .then(res=>{
                    if(res.code == 200){
                        this.getAllConsumersData();
                        this.notify('修改成功',"success");
                    }else if(res.code == 500){
                        this.notify('修改失败','error');
                    }
                })
                .catch(err=>{
                    console.log(err);
                });
                this.editDialogVisible = false;
                }else{
                    this.$message.error("数据有误，请修改后再提交");
                }
            })
        },
        /** 删除用户 */
        delConsumer(){
            if(this.delForm.id){
            console.log(JSON.stringify(this.delForm));
            delConsumer(JSON.stringify(this.delForm))
            .then(res=>{
                if(res.code == 200){
                    this.getAllConsumersData();
                    this.notify('删除成功','success');
                }else if(res.code ==500){
                    this.notify('删除失败','error');
                }else {
                    this.notify('删除失败','error');
                }
            })
            .catch(err =>{
                console.log(err);
            });
            }else{
                alert('未知错误，请重新选择...');
            }
            this.delDialogVisible=false;
            
        },
        clearRegisterForm(){
            this.registerForm={
                username: '',
                sex: '',
                avator: '',
                birth: '',
                location: '',
                introduction: '',
                createBy: ''
            }
        },
        delSelConsumer(){
            for(let item of this.multipleSelection){
                this.delForm={
                    id: item.id
                }
                this.delConsumer(item);
            }
            this.batchDelDialogVisible=false;
        },
        /**
            跳转到用户收藏歌单列表
         */
        toCollectPage(userId){
            this.$router.push({path: `/collect`,query:{userId}});
        }
        
       
    },
    watch:{
        input_word: function(){
            if(this.input_word == ''){
                this.tableData = this.tempData;
            }else{
                /* 先清空原有table，根据关键字在前端过滤一下 */
                this.tableData=[];
                for(let item of this.tempData){
                    if(item.username.includes(this.input_word)){
                        this.tableData.push(item);
                    }
                }
            }
        }
    },
    created(){
        this.getAllConsumersData();
    }
    
}
</script>
<style scoped>
.handle-box{
    margin-bottom: 20px;
}

/* 用户头像 */
.consumer-img {
    width: 100%;
    height: 80px;
    border-radius: 5px;
    margin-bottom: 5px;
    overflow: hidden;
}
/* 歌名搜索框 */
.handle-input{
    width: 300px;
    display: inline-block;
}
/** 分页样式 */
.pagination{
    display: flex;
    justify-content: center;
}
</style>