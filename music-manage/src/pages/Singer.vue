<template>
    <div class="table">
        <div class="container">
            <div class="handle-box">
                <el-button type="danger" size="mini" @click="batchDelDialogVisible=true">批量删除</el-button>
                <el-input size="mini" class="handle-input" placeholder="请输入歌手名" v-model="input_word"></el-input>
                <el-button type="primary" size="mini" @click="centerDialogVisible = true">添加歌手</el-button>
            </div>
        </div>
        <!-- 头像 -->
        <el-table size="mini" border style="width:100%" height="500px" :data="data" @selection-change="handleSelectItem">
            <!-- 选项框 -->
            <el-table-column type="selection" width="40px"></el-table-column>
            <el-table-column label="歌手图片" width="110px" align="center">
                <template slot-scope="scope">
                    <div class="singer-img">
                        <img :src="getUrl(scope.row.singerPic)" style="width: 100%" />
                    </div>
                    <el-upload :action="uploadUrl(scope.row.id)" :before-upload="checkUploadFile"
                    :on-success="handleAvatorSuccess">
                        <el-button size="mini">更新头像</el-button>
                    </el-upload>
                </template>
            </el-table-column>
        <!-- 歌手名称 -->
            <el-table-column prop="singerName" label="歌手名称" width="120px" align="center"></el-table-column>
            <!-- 歌手性别 -->
            <el-table-column label="性别" width="50px" align="center">
                <template slot-scope="scope">
                    {{getCNSex(scope.row.singerSex)}}
                </template>
            </el-table-column>
            <!-- 生日 -->
            <el-table-column label="出生日期" width="120px" align="center">
                <template slot-scope="scope">
                    {{attachBirth(scope.row.singerBirth)}}
                </template>
            </el-table-column>
            <!-- 地区 -->
            <el-table-column prop="singerLocaltion" label="地区" width="100px" align="center"></el-table-column>
            <!-- 简介 -->
            <el-table-column label="简介" align="center">
                <template slot-scope="scope">
                    <p style="height:100px;overflow:scroll">{{scope.row.singerIntroduction}}</p>
                </template>
            </el-table-column>
            <!-- 歌曲管理 -->
            <el-table-column label="歌曲管理" width="110px" align="center">
                <template slot-scope="scope">
                <el-button size="mini" @click="songEdit(scope.row.id,scope.row.singerName)">歌曲管理</el-button>
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

        <!-- 添加歌手的弹框 -->
        <el-dialog title="添加歌手" :visible.sync="centerDialogVisible" width="500px" center>
            <el-form :model="registerForm" ref="registerForm" label-width="80px">
                <el-form-item prop="singerName" label="歌手名" size="mini">
                    <el-input v-model="registerForm.singerName" placeholder="歌手名"></el-input>
                </el-form-item>
                <!-- 性别 -->
                <el-form-item label="性别" size="mini">
                    <el-radio-group v-model="registerForm.singerSex">
                        <el-radio :label="0">男</el-radio>
                        <el-radio :label="1">女</el-radio>
                        <el-radio :label="2">组合</el-radio>
                        <el-radio :label="3">不详</el-radio>
                    </el-radio-group>
                </el-form-item>
                <!-- 生日 -->
                <el-form-item prop="singerBirth" label="出生日期" size="mini">
                    <el-date-picker type="date" placeholder="选择日期" v-model="registerForm.singerBirth" style="width:100%"></el-date-picker>
                </el-form-item>
                <!-- 地区 -->
                <el-form-item prop="singerLocaltion" label="地区" size="mini">
                    <el-input v-model="registerForm.singerLocaltion" placeholder="地区"></el-input>
                </el-form-item>
                <!-- 简介 -->
                <el-form-item prop="singerIntroduction" label="简介" size="mini">
                    <el-input v-model="registerForm.singerIntroduction" placeholder="简介" type="textarea"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer">
                <el-button size="mini" @click="centerDialogVisible=false">取消</el-button>
                <el-button size="mini" @click="addSinger">添加歌手</el-button>
            </span>
        </el-dialog>

        <!-- 编辑歌手的弹框 -->
        <el-dialog title="修改歌手信息" :visible.sync="editDialogVisible" width="500px" center>
            <el-form :model="editForm" ref="editForm" label-width="80px">
                <el-form-item prop="singerName" label="歌手名" size="mini">
                    <el-input v-model="editForm.singerName" placeholder="歌手名"></el-input>
                </el-form-item>
                <!-- 性别 -->
                <el-form-item label="性别" size="mini">
                    <el-radio-group v-model="editForm.singerSex">
                        <el-radio :label="0">男</el-radio>
                        <el-radio :label="1">女</el-radio>
                        <el-radio :label="2">组合</el-radio>
                        <el-radio :label="3">不详</el-radio>
                    </el-radio-group>
                </el-form-item>
                <!-- 生日 -->
                <el-form-item prop="singerBirth" label="出生日期" size="mini">
                    <el-date-picker type="date" placeholder="选择日期" v-model="editForm.singerBirth" style="width:100%"></el-date-picker>
                </el-form-item>
                <!-- 地区 -->
                <el-form-item prop="singerLocaltion" label="地区" size="mini">
                    <el-input v-model="editForm.singerLocaltion" placeholder="地区"></el-input>
                </el-form-item>
                <!-- 简介 -->
                <el-form-item prop="singerIntroduction" label="简介" size="mini">
                    <el-input v-model="editForm.singerIntroduction" placeholder="简介" type="textarea"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer">
                <el-button size="mini" @click="editDialogVisible = false">取消</el-button>
                <el-button size="mini" @click="editSave">确认修改</el-button>
            </span>
        </el-dialog>

        <el-dialog title="删除歌手" :visible.sync="delDialogVisible" width="300px" center>
            <div>删除后不可恢复，是否删除？</div>
            <span slot="footer">
            <el-button size="mini" @click="delDialogVisible=false">取消</el-button>
            <el-button size="mini" type="danger" @click="delSinger">确认</el-button>
            </span>
        </el-dialog>
        <!-- 批量删除歌手 -->
        <el-dialog title="删除歌手" :visible.sync="batchDelDialogVisible" width="300px" center>
            <div>删除后不可恢复，是否删除？</div>
            <span slot="footer">
            <el-button size="mini" @click="batchDelDialogVisible=false">取消</el-button>
            <el-button size="mini" type="danger" @click="delAll">确认</el-button>
            </span>
        </el-dialog>
    </div>
</template>
<script>
import {addSinger,getAllSinger,updateSinger,delSinger} from '../api/index';
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
                singerName: '',
                singerSex: '',
                singerPic: '',
                singerBirth: '',
                singerLocaltion: '',
                singerIntroduction: '',
                insertBy: ''
            },
            // 编辑窗口
            editForm:{
                id: '',
                singerName: '',
                singerSex: '',
                singerBirth: '',
                singerLocaltion: '',
                singerIntroduction: '',
                updateBy: ''
            },
            input_word: '',
            //初始页
            currentPage: 1,
            pageSize: 5,
            multipleSelection:[]
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
        addSinger(){
            this.registerForm.singerPic = '/img/singerPic/hhh.jpg';
            let username = localStorage.getItem('userName');
            if(username){
            this.registerForm.insertBy=username;
            }
            var singerJson = JSON.stringify(this.registerForm);
            addSinger(singerJson)
            .then(res=>{
                if(res.code == 200){
                    this.getAllSingersData();
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
        },
        getAllSingersData(){
            // 清空原有的记录
            this.tableData=[];
            this.tempData=[];
            getAllSinger().then(res =>{
                this.tableData = res.data;
                this.tempData = res.data;
            })
        },
        uploadUrl(id){
            return `${this.$store.state.HOST}/singer/updateSingerPic?id=${id}`
        },
        /** 转向歌曲管理页面 */
        songEdit(singerId,singerName){
            this.$router.push({path:'/Song',query:{singerId,singerName}})
        },
        handleCurrentChange(value){
            this.currentPage=value;
        },
        /* 弹出编辑弹框 */
        handleEdit(row){
            this.editDialogVisible = true;
            /* 将该行的值传到editForm */
            this.editForm = {
                id : row.id,
                singerName : row.singerName,
                singerBirth : row.singerBirth,
                singerLocaltion : row.singerLocaltion,
                singerIntroduction : row.singerIntroduction,
                singerSex : row.singerSex
            }
        },
        /** 保存编辑 */
        editSave(){
            let username = localStorage.getItem('userName');
            if(username){
            this.editForm.updatetBy=username;
            }
            var singerJson = JSON.stringify(this.editForm);
            console.log('hh');
            updateSinger(singerJson)
            .then(res=>{
                if(res.code == 200){
                    this.getAllSingersData();
                    this.notify('修改成功',"success");
                }else if(res.code == 500){
                    this.notify('修改失败','error');
                }
            })
            .catch(err=>{
                console.log(err);
            });
            this.editDialogVisible = false;
            

        },
        /** 删除歌手 */
        delSinger(){
            if(this.delForm.id){
            console.log(JSON.stringify(this.delForm));
            delSinger(JSON.stringify(this.delForm))
            .then(res=>{
                if(res.code == 200){
                    this.getAllSingersData();
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
                singerName: '',
                singerSex: '',
                singerPic: '',
                singerBirth: '',
                singerLocaltion: '',
                singerIntroduction: '',
                insertBy: ''
            }
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
                    if(item.singerName.includes(this.input_word)){
                        this.tableData.push(item);
                    }
                }
            }
        }
    },
    created(){
        this.getAllSingersData();
    }
    
}
</script>
<style scoped>
.handle-box{
    margin-bottom: 20px;
}

/* 歌手头像 */
.singer-img {
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