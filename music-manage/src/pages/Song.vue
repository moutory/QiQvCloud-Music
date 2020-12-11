<template>
    <div class="table">
        <div class="container">
            <div class="handle-box">
                <el-button type="danger" size="mini" @click="batchDelDialogVisible=true">批量删除</el-button>
                <el-input size="mini" class="handle-input" placeholder="请输入歌曲名" v-model="input_word"></el-input>
                <el-button type="primary" size="mini" @click="centerDialogVisible = true">添加歌曲</el-button>
            </div>
        </div>
        
        <el-table size="mini" border style="width:100%" height="500px" :data="data" @selection-change="handleSelectItem">
            <!-- 选项框 -->
            <el-table-column type="selection" width="40px"></el-table-column>
            <el-table-column label="歌曲图片" width="110px" align="center">
                <template slot-scope="scope">
                    <div class="song-img">
                        <img :src="getUrl(scope.row.songPic)" style="width: 100%" />
                    </div>
                    <div class="play" @click="setSongUrl(scope.row.songUrl,scope.row.songName)">
                        <div v-if="toggle == scope.row.songName">
                            <svg class="icon">
                                <use xlink:href="#icon-zanting"></use>
                            </svg>
                        </div>
                        <div v-if="toggle != scope.row.songName">
                            <svg class="icon">
                                <use xlink:href="#icon-bofanganniu"></use>
                            </svg>
                        </div>
                    </div>
                </template>
            </el-table-column>
        <!-- 歌曲名称 -->
            <el-table-column prop="songName" label="歌曲名称" width="120px" align="center"></el-table-column>
           
            <!-- 专辑 -->
            <el-table-column label="专辑" align="center" width="150px" prop="songIntroduction">
                
            </el-table-column>
            <!-- 歌词-->
            <el-table-column label="歌词" align="center">
                <template slot-scope="scope">
                    <ul style="height:100px;overflow:scroll">
                        <li v-for="(item,index) in parseLyric(scope.row.songLyric)" :key="index">
                            {{item}}
                        </li>
                    </ul>
                </template>
            </el-table-column>
            <!-- 歌曲管理 -->
            <el-table-column label="资源更新" width="110px" align="center">
                <template slot-scope="scope">
                    <el-upload :action="uploadUrl(scope.row.id)" :before-upload="checkUploadFile"
                    :on-success="handleUploadSongPicSuccess">
                        <el-button size="mini">更新图片</el-button>
                    </el-upload>
                    <br/>
                    <el-upload :action="uploadSongUrl(scope.row.id)" :before-upload="checkSongFileType"
                    :on-success="handleUploadSongSuccess">
                        <el-button size="mini">更新歌曲</el-button>
                    </el-upload>
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

        <!-- 添加歌曲的弹框 -->
        <el-dialog title="添加歌曲" :visible.sync="centerDialogVisible" width="400px" center>
            <el-form :model="registerForm" ref="registerForm" label-width="80px" id="ad-song">
                <div>
                    <label>歌名</label>
                    <el-input type="text" name="songName" placeholder="输入歌名"></el-input>
                </div>
                <div>
                    <label>专辑</label>
                    <el-input type="text" name="songIntroduction" placeholder="输入专辑"></el-input>
                </div>
                <div>
                    <label>歌词</label>
                    <el-input type="textarea" name="songLyric" placeholder="输入歌词"></el-input>
                </div>
                <div>
                    <label>歌曲上传</label>
                    <el-input type="file" name="file"></el-input>
                </div>
            </el-form>
            <span slot="footer">
                <el-button size="mini" @click="centerDialogVisible=false">取消</el-button>
                <el-button size="mini" @click="addSong">添加歌曲</el-button>
            </span>
        </el-dialog>

        <!-- 编辑歌曲的弹框 -->
        <el-dialog title="修改歌曲信息" :visible.sync="editDialogVisible" width="400px" center>
            <el-form :model="editForm" ref="editForm" label-width="80px">
                <el-form-item prop="songName" label="歌曲名" size="mini">
                    <el-input v-model="editForm.songName" placeholder="歌手-歌曲名"></el-input>
                </el-form-item>
                <el-form-item prop="songIntroduction" label="专辑" size="mini">
                    <el-input type="text" v-model="editForm.songIntroduction" placeholder="专辑名称">专辑名称</el-input>
                </el-form-item>
               <el-form-item prop="songLyric" label="歌词" size="mini">
                   <el-input v-model="editForm.songLyric" placeholder="歌词" type="textarea"></el-input>
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
            <el-button size="mini" type="danger" @click="delSong">确认</el-button>
            </span>
        </el-dialog>
        <!-- 批量删除歌曲 -->
        <el-dialog title="删除歌曲" :visible.sync="batchDelDialogVisible" width="300px" center>
            <div>删除后不可恢复，是否删除？</div>
            <span slot="footer">
            <el-button size="mini" @click="batchDelDialogVisible=false">取消</el-button>
            <el-button size="mini" type="danger" @click="delSelSong">确认</el-button>
            </span>
        </el-dialog>
    </div>
</template>
<script>
import {getAllSongBySingerId,updateSong,delSong} from '../api/index';
import {mapGetters} from 'vuex';
import '@/assets/js/iconfont.js';
import {mixin} from '../mixins/index';
export default {
    mixins: [mixin],
    data(){
        return {
            tableData: [],
            tempData: [],
            singerId: '',
            singerName: '',
            centerDialogVisible: false,
            editDialogVisible: false, // 编辑窗口是否可见
            delDialogVisible: false, // 删除弹窗
            batchDelDialogVisible:false, // 批量删除弹窗
            delForm:{
                id: ''
            },
            registerForm:{
                songName: '',
                songLyric: '',
                songIntroduction: '',
                insertBy: ''
            },
            // 编辑窗口
            editForm:{
                id: '',
                songName: '',
                songLyric: '',
                songIntroduction: '',
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
    computed:{
        ...mapGetters([
            'isPlay'
        ]),
        // 计算当前搜索结果表里的数据
        data(){
            if(this.tableData){
            return this.tableData.slice((this.currentPage-1)* this.pageSize,this.currentPage*this.pageSize);
            }
        }
    },
    created(){
        this.singerId = this.$route.query.singerId;
        this.singerName = this.$route.query.singerName;
        this.getAllSongsData();
    },
    destroyed() {
        this.$store.commit('setIsPlay',false);
    },
    methods: {
        addSong(){
            let that = this;
            var songForm = new FormData(document.getElementById('ad-song'));
            songForm.append('singerId',this.singerId);
            var insertBy = localStorage.getItem('userName');
            if(insertBy){
                songForm.append('insertBy',insertBy);
            }
            if(!songForm.get('SongLyric')){
                songForm.set('songLyric','[00:00:00]暂无歌词');
            }
            songForm.set('songName',this.singerName + '-' + songForm.get('songName'));
            // 定义请求
            var req = new XMLHttpRequest();
            req.onreadystatechange = function(){
                //req.readyState == 4 获取到返回的完整数据
                //req.status == 200 和后台正常交互完成
                if(req.readyState == 4 && req.status == 200){
                    let res = JSON.parse(req.response);
                    if(res.code == 200){
                        that.clearRegisterForm();
                        that.getAllSongsData();
                        that.notify('添加成功','success');
                    }else if(res.code == 500){
                        that.notify('添加失败','error');
                    }else{
                        that.notify('添加失败','error');
                    }
                }
            }
            req.open('post',`${that.$store.state.HOST}/song/addSong`,false);
            req.send(songForm);
            this.centerDialogVisible = false;
        },
        getAllSongsData(){
            // 清空原有的记录
            this.tableData=[];
            this.tempData=[];
            getAllSongBySingerId(this.singerId).then(res =>{
                this.tableData = res.data;
                this.tempData = res.data;
            })
        },
        /* 更新专辑图片 */
        uploadUrl(id){
            return `${this.$store.state.HOST}/song/updateSongPic?id=${id}`;
        },
        /* 更新歌曲 */
        uploadSongUrl(id){
            return `${this.$store.state.HOST}/song/updateSongUrl?id=${id}`;
        },
        /**更新歌曲成功后回调函数 */
        handleUploadSongSuccess(res){
            var that = this;
            if(res.code == 200){
                that.getAllSongsData();
                that.notify('更新成功','success');
            }else if(res.code ==500){
                that.notify('更新失败','error');
            }else{
                that.notify('更新失败','error');
            }
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
                songName: row.songName,
                songLyric: row.songLyric,
                songIntroduction: row.songIntroduction,
            }
        },
        /** 保存编辑 */
        editSave(){
            let username = localStorage.getItem('userName');
            if(username){
            this.editForm.updatetBy=username;
            }
            var songJson = JSON.stringify(this.editForm);
            updateSong(songJson)
            .then(res=>{
                if(res.code == 200){
                    this.getAllSongsData();
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
        /** 删除歌曲 */
        delSong(){
            if(this.delForm.id){
            delSong(JSON.stringify(this.delForm))
            .then(res=>{
                if(res.code == 200){
                    this.getAllSongsData();
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
        /**批量删除歌曲 */
        delSelSong(){
            var that = this;
            for(let item of that.multipleSelection){
                that.delForm={
                    id: item.id
                }
                that.delSong(item);
            }
            that.batchDelDialogVisible=false;
        },
        clearRegisterForm(){
            this.registerForm={
                songName: '',
                songLyric: '',
                songIntroduction: '',
                insertBy: ''
            }
        },
        parseLyric(songLyric){
            let textLines = songLyric.split('\n');
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
        setSongUrl(songUrl,songName){
            this.toggle = songName;
            this.$store.commit('setUrl',this.$store.state.HOST + songUrl);
            if(this.isPlay){
                this.$store.commit('setIsPlay',false);
            }else{
                this.$store.commit('setIsPlay',true);
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
                    if(item.songName.includes(this.input_word)){
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
.song-img {
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