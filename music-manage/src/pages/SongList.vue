<template>
    <div class="table">
        <div class="container">
            <div class="handle-box">
                <el-button type="danger" size="mini" @click="batchDelDialogVisible=true">批量删除</el-button>
                <el-input size="mini" class="handle-input" placeholder="请输入歌单名" v-model="input_word"></el-input>
                <el-button type="primary" size="mini" @click="centerDialogVisible = true">添加歌单</el-button>
            </div>
        </div>
        <!-- 头像 -->
        <el-table size="mini" border style="width:100%" height="500px" :data="data" @selection-change="handleSelectItem">
            <!-- 选项框 -->
            <el-table-column type="selection" width="40px"></el-table-column>
            <el-table-column label="歌单图片" width="110px" align="center">
                <template slot-scope="scope">
                    <div class="songList-img">
                        <img :src="getUrl(scope.row.songlistPic)" style="width: 100%" />
                    </div>
                    <el-upload :action="uploadUrl(scope.row.id)" :before-upload="checkUploadFile"
                    :on-success="handleAvatorSuccess">
                        <el-button size="mini">更新歌单图片</el-button>
                    </el-upload>
                </template>
            </el-table-column>
        <!-- 歌单名称 -->
            <el-table-column prop="songlistTitle" label="歌单名称" width="120px" align="center"></el-table-column>
            <!-- 简介 -->
            <el-table-column label="简介" align="center">
                <template slot-scope="scope">
                    <p style="height:100px;overflow:scroll;align:center" >{{scope.row.songlistIntroduction}}</p>
                </template>
            </el-table-column>
            <el-table-column prop="songlistStyle" label="风格" width="120" align="center"></el-table-column>
            <!-- 歌单管理 -->
            <el-table-column label="歌曲管理" width="110px" align="center">
                <template slot-scope="scope">
                <el-button size="mini" @click="songEdit(scope.row.id)">歌曲管理</el-button>
                </template>
            </el-table-column>
            <!-- 评论 -->
            <el-table-column label="歌曲评论" width="110px" align="center">
                <template slot-scope="scope">
                <el-button size="mini" @click="goCommentPage(scope.row.id)">评论管理</el-button>
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

        <!-- 添加歌单的弹框 -->
        <el-dialog title="添加歌单" :visible.sync="centerDialogVisible" width="500px" center>
            <el-form :model="registerForm" ref="registerForm" label-width="80px">
                <el-form-item prop="songlistTitle" label="歌单名" size="mini">
                    <el-input v-model="registerForm.songlistTitle" placeholder="歌单名"></el-input>
                </el-form-item>
                <!-- 简介 -->
                <el-form-item prop="songlistIntroduction" label="简介" size="mini">
                    <el-input v-model="registerForm.songlistIntroduction" placeholder="简介" type="textarea"></el-input>
                </el-form-item>
                <!-- 风格 -->
                <el-form-item prop="songlistStyle" label="风格" size="mini">
                    <el-input v-model="registerForm.songlistStyle" placeholder="风格"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer">
                <el-button size="mini" @click="centerDialogVisible=false">取消</el-button>
                <el-button size="mini" @click="addSongList">添加歌单</el-button>
            </span>
        </el-dialog>

        <!-- 编辑歌单的弹框 -->
        <el-dialog title="修改歌单信息" :visible.sync="editDialogVisible" width="500px" center>
            <el-form :model="editForm" ref="editForm" label-width="80px">
                <el-form-item prop="songlistTitle" label="歌单名" size="mini">
                    <el-input v-model="editForm.songlistTitle" placeholder="歌单名"></el-input>
                </el-form-item>
                <!-- 风格  -->
                <el-form-item prop="songlistStyle" label="风格" size="mini">
                    <el-input v-model="editForm.songlistStyle"></el-input>
                </el-form-item>
                <!-- 简介 -->
                <el-form-item prop="songlistIntroduction" label="简介" size="mini">
                    <el-input v-model="editForm.songlistIntroduction" placeholder="简介" type="textarea"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer">
                <el-button size="mini" @click="editDialogVisible = false">取消</el-button>
                <el-button size="mini" @click="editSave">确认修改</el-button>
            </span>
        </el-dialog>

        <el-dialog title="删除歌单" :visible.sync="delDialogVisible" width="300px" center>
            <div>删除后不可恢复，是否删除？</div>
            <span slot="footer">
            <el-button size="mini" @click="delDialogVisible=false">取消</el-button>
            <el-button size="mini" type="danger" @click="delSongList">确认</el-button>
            </span>
        </el-dialog>
        <!-- 批量删除歌单 -->
        <el-dialog title="删除歌单" :visible.sync="batchDelDialogVisible" width="300px" center>
            <div>删除后不可恢复，是否删除？</div>
            <span slot="footer">
            <el-button size="mini" @click="batchDelDialogVisible=false">取消</el-button>
            <el-button size="mini" type="danger" @click="delSelSongList">确认</el-button>
            </span>
        </el-dialog>
    </div>
</template>
<script>
import {addSongList,getAllSongList,updateSongList,delSongList} from '../api/index';
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
                songlistTitle: '',
                songlistStyle: '',
                songlistIntroduction: '',
                insertBy: ''
            },
            // 编辑窗口
            editForm:{
                id: '',
                songlistTitle: '',
                songlistStyle: '',
                songlistIntroduction: '',
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
        addSongList(){
            let username = localStorage.getItem('userName');
            if(username){
            this.registerForm.insertBy=username;
            }
            var songListJson = JSON.stringify(this.registerForm);
            addSongList(songListJson)
            .then(res=>{
                if(res.code == 200){
                    this.getAllSongListData();
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
        getAllSongListData(){
            // 清空原有的记录
            this.tableData=[];
            this.tempData=[];
            getAllSongList().then(res =>{
                this.tableData = res.data;
                this.tempData = res.data;
            })
        },
        uploadUrl(id){
            return `${this.$store.state.HOST}/songList/updateSongListPic?id=${id}`
        },
        /** 转向歌曲管理页面 */
        songEdit(songListId){
            this.$router.push({path:'/ListSong',query:{songListId}})
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
                songlistTitle : row.songlistTitle,
                songlistStyle: row.songlistStyle,
                songlistIntroduction : row.songlistIntroduction,
            }
        },
        /** 保存编辑 */
        editSave(){
            let username = localStorage.getItem('userName');
            if(username){
            this.editForm.updatetBy=username;
            }
            var songListJson = JSON.stringify(this.editForm);
            console.log('hh');
            updateSongList(songListJson)
            .then(res=>{
                if(res.code == 200){
                    this.getAllSongListData();
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
        /** 删除歌单 */
        delSongList(){
            if(this.delForm.id){
            console.log(JSON.stringify(this.delForm));
            delSongList(JSON.stringify(this.delForm))
            .then(res=>{
                if(res.code == 200){
                    this.getAllSongListData();
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
                songlistTitle: '',
                songlistStyle: '',
                songlistIntroduction: '',
                insertBy: ''
            }
        },
        /**批量删除歌单 */
        delSelSongList(){
            for(let item of this.multipleSelection){
                this.delForm={
                    id: item.id
                }
                this.delSongList(item);
            }
            this.batchDelDialogVisible=false;
        },
        /**
         * 跳转到歌曲评论页面
         */
        goCommentPage(songListId){
            if(songListId){
                this.$router.push({path: '/Comment',query: {songListId}});
            }else{
                this.$message.warning('请选择查看的歌单');
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
                    if(item.songlistTitle.includes(this.input_word)){
                        this.tableData.push(item);
                    }
                }
            }
        }
    },
    created(){
        this.getAllSongListData();
    }
    
}
</script>
<style scoped>
.handle-box{
    margin-bottom: 20px;
}

/* 歌单头像 */
.songList-img {
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