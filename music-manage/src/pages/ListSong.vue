<template>
    <div class="table">
        <div class="crumbs">
            <i class="el-icon-tickets"></i>歌单歌曲信息
        </div>
        <div class="container">
            <div class="handle-box">
                <el-button type="danger" size="mini" @click="batchDelDialogVisible=true">批量删除</el-button>
                <el-input size="mini" class="handle-input" placeholder="请输入歌曲名" v-model="input_word"></el-input>
                <el-button type="primary" size="mini" @click="centerDialogVisible = true">添加歌曲</el-button>
            </div>
        </div>
        
        <el-table size="mini" border style="width:100%" height="500px" :data="tableData" @selection-change="handleSelectItem">
            <!-- 选项框 -->
            <el-table-column type="selection" width="40px"></el-table-column>
            <!-- 歌手-歌曲名  -->
            <el-table-column prop="songName" align="center" label="歌手-歌曲名"></el-table-column>
            <!-- 操作 -->
            <el-table-column label="操作" width="150px" align="center">
                <template slot-scope="scope">
                    <el-button size="mini" type="danger" @click="handleSongDelete(scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <!-- 添加歌曲的弹框 -->
        <el-dialog title="添加歌曲" :visible.sync="centerDialogVisible" width="400px" center>
            <el-form :model="registerForm" ref="registerForm" label-width="80px" id="ad-songList">
                <el-form-item prop="singerName" label="歌手" size="mini">
                    <el-input v-model="registerForm.singerName" placeholder="歌手名"></el-input>
                </el-form-item>
                <el-form-item prop="songName" label="歌曲名" size="mini">
                    <el-input v-model="registerForm.songName" placeholder="歌曲名"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer">
                <el-button size="mini" @click="centerDialogVisible=false">取消</el-button>
                <el-button size="mini" @click="getSongId">添加歌曲</el-button>
            </span>
        </el-dialog>

        <!-- 编辑歌曲的弹框 -->
        <el-dialog title="修改歌曲信息" :visible.sync="editDialogVisible" width="400px" center>
            <el-form :model="editForm" ref="editForm" label-width="80px">
                <el-form-item prop="songListName" label="歌曲名" size="mini">
                    <el-input v-model="editForm.songListName" placeholder="歌手-歌曲名"></el-input>
                </el-form-item>
                <el-form-item prop="songListIntroduction" label="专辑" size="mini">
                    <el-input type="text" v-model="editForm.songListIntroduction" placeholder="专辑名称">专辑名称</el-input>
                </el-form-item>
               <el-form-item prop="songListLyric" label="歌词" size="mini">
                   <el-input v-model="editForm.songListLyric" placeholder="歌词" type="textarea"></el-input>
               </el-form-item>
            </el-form>
            <span slot="footer">
                <el-button size="mini" @click="editDialogVisible = false">取消</el-button>
                <el-button size="mini" @click="editSave">确认修改</el-button>
            </span>
        </el-dialog>

        <el-dialog title="删除歌曲" :visible.sync="delDialogVisible" width="300px" center>
            <div>删除后不可恢复，是否删除？</div>
            <span slot="footer">
            <el-button size="mini" @click="delDialogVisible=false">取消</el-button>
            <el-button size="mini" type="danger" @click="delListSong">确认</el-button>
            </span>
        </el-dialog>
        <!-- 批量删除歌曲 -->
        <el-dialog title="删除歌曲" :visible.sync="batchDelDialogVisible" width="300px" center>
            <div>删除后不可恢复，是否删除？</div>
            <span slot="footer">
            <el-button size="mini" @click="batchDelDialogVisible=false">取消</el-button>
            <el-button size="mini" type="danger" @click="delSelSongList">确认</el-button>
            </span>
        </el-dialog>
    </div>
</template>
<script>
import {getAllSongOfList,addListSong,getSongByName,getAllSongById,delListSong} from '../api/index';
import {mapGetters} from 'vuex';
import '@/assets/js/iconfont.js';
import {mixin} from '../mixins/index';
export default {
    mixins: [mixin],
    data(){
        return {
            tableData: [],
            tempData: [],
            songListId: '',
            centerDialogVisible: false,
            editDialogVisible: false, // 编辑窗口是否可见
            delDialogVisible: false, // 删除弹窗
            batchDelDialogVisible:false, // 批量删除弹窗
            delForm:{
                songId: '',
                songListId: ''
            },
            registerForm:{
                singerName: '',
                songName: '',
                songId: '',
                songListId: '',
                insertBy: ''
            },
            // 编辑窗口
            editForm:{
                id: '',
                songListName: '',
                songListLyric: '',
                songListIntroduction: '',
                updateBy: ''
            },
            input_word: '',
            //初始页
            currentPage: 1,
            pageSize: 5,
            multipleSelection:[],
            toggle: false //播放器状态
        }
    },
    created(){
        this.songListId = this.$route.query.songListId;
        this.getAllSongListData();
    },
    destroyed() {
        this.$store.commit('setIsPlay',false);
    },
    methods: {
        getSongId(){
            var that = this;
            var finalSongName = that.registerForm.singerName + '-' + that.registerForm.songName;
            getSongByName(finalSongName).then(
                res => {
                    if(res.code == 200 && res.data){
                        console.log(res);
                        that.addListSong(res.data[0].id);
                    }else if(res.code == 500 && res.code.msg!=''){
                        this.$message.error(res.msg);
                        return;
                    }else{
                        this.$message.error('添加失败');
                        return;
                    }
                    
                }
            )
        },
        addListSong(songId){
            let that = this;
            that.registerForm.songId = songId;
            that.registerForm.songListId = this.songListId;
            var insertBy = localStorage.getItem('userName');
            if(insertBy){
                that.registerForm.insertBy = insertBy;
            }
            
           var songListJson = JSON.stringify(that.registerForm);
            addListSong(songListJson)
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
            var that = this;
            // 清空原有的记录
            that.tableData=[];
            that.tempData=[];
            getAllSongOfList(that.songListId).then(res =>{
               if(res.code == 200 && res.data){
                   for(let item of res.data){
                       that.getAllSongById(item.songId);
                   }
               }else if(res.code == 500 && res.msg != ''){
                   that.$message.error(res.msg);
               }else{
                   that.$message.error('查询歌单歌曲失败');
               }
            })
        },
        /** 根据歌曲id查询歌单内所有歌曲*/     
        getAllSongById(songId){
            var that = this;
            getAllSongById(songId).then(
                res => {
                    if(res.code == 200 && res.data){
                        that.tableData.push(res.data);
                        that.tempData.push(res.data);
                    }else if (res.code == 500 && res.msg != ''){
                        that.$message.error(res.msg);
                    }else{
                        that.$message.error('查询歌曲失败,歌曲id:'+songId);
                    }
                }
            ).catch(error =>{
                console.log(error);
            });
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
                songListName: row.songListName,
                songListLyric: row.songListLyric,
                songListIntroduction: row.songListIntroduction,
            }
        },
        /** 保存编辑 */
        editSave(){
            let username = localStorage.getItem('userName');
            if(username){
            this.editForm.updatetBy=username;
            }
            var songListJson = JSON.stringify(this.editForm);
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
        /** 删除歌曲 
         *  这里要注意一点，和之前的案例不同，这里的delFormId是歌曲id，不是listSongId，不可以直接删除
        */
        delListSong(){
            var that = this;
            if(that.delForm.id || that.songListId){
                that.delForm.songListId = that.songListId;
                delListSong(JSON.stringify(this.delForm))
                .then(res=>{
                    if(res.code == 200){
                        this.getAllSongListData();
                        this.notify('删除成功','success');
                    }else if(res.code ==500 && res.msg){
                        this.notify(res.msg,'error');
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
        /**批量删除歌曲 */
        delSelSongList(){
            var that = this;
            for(let item of that.multipleSelection){
                that.delForm={
                    songId: item.id
                }
                that.delListSong(item);
            }
            that.batchDelDialogVisible=false;
        },
        clearRegisterForm(){
            this.registerForm={
                singerName: '',
                songName: '',
                songId: '',
                songListId: '',
                insertBy: ''
            }
        },
        parseLyric(songListLyric){
            let textLines = songListLyric.split('\n');
            let pattern =  /\[\d{2}:\d{2}:(\d{3}|\d{2})\]/g;
            let result = [];
            for(let textline of textLines){
                let value = textline.replace(pattern,'');
                result.push(value);
            }
            return result;
        },
        /**
         * 切换歌曲
         */
        setSongListUrl(songListUrl,songListName){
            this.toggle = songListName;
            this.$store.commit('setUrl',this.$store.state.HOST + songListUrl);
            if(this.isPlay){
                this.$store.commit('setIsPlay',false);
            }else{
                this.$store.commit('setIsPlay',true);
            }
        },
        handleSongDelete(row){
            this.delForm={
                songId: row.id
            }
            this.delDialogVisible = true;
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
                    if(item.songListName.includes(this.input_word)){
                        this.tableData.push(item);
                    }
                }
            }
        }
    }
    
}
</script>
<style scoped>
.handle-box{
    margin-bottom: 20px;
}

/* 歌曲头像 */
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
/**播放器样式 */
.play{
    position: absolute;
    z-index: 100;
    width: 80px;
    height: 80px;
    display: flex;
    align-items: center;
    justify-content: center;
    cursor: pointer;
    top: 18px;
    left: 15px;
}

.icon {
    width: 2em;
    height: 2em;
    color: white;
    fill: currentColor;
    overflow: hidden;
}
</style>