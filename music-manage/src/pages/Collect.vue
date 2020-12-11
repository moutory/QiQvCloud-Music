<template>
    <div class="table">
        <div class="crumbs">
            <i class="el-icon-tickets">收藏列表</i>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-button type="danger" size="mini" @click="batchDelDialogVisible=true">批量删除</el-button>
                <el-input size="mini" class="handle-input" placeholder="请输入歌曲名" v-model="input_word"></el-input>
            </div>
            <el-table size="mini" border style="width:100%" height="500px" :data="musicData" @selection-change="handleSelectItem">
                <el-table-column type="selection" width="40"></el-table-column>
                <el-table-column prop="songName" label="歌手-歌名" align="center"></el-table-column>
                <el-table-column label="操作" width="150" align="center">
                    <template slot-scope="scope">
                        <el-button size="mini" type="danger" @click="handleDelete(scope.row)">删除</el-button>
                    </template>
                </el-table-column>
        </el-table>
        <el-dialog title="删除用户" :visible.sync="delDialogVisible" width="300px" center>
            <div>删除后不可恢复，是否删除？</div>
            <span slot="footer">
            <el-button size="mini" @click="delDialogVisible=false">取消</el-button>
            <el-button size="mini" type="danger" @click="delUserCollect">确认</el-button>
            </span>
        </el-dialog>
        <!-- 批量删除用户 -->
        <el-dialog title="删除用户" :visible.sync="batchDelDialogVisible" width="300px" center>
            <div>删除后不可恢复，是否删除？</div>
            <span slot="footer">
            <el-button size="mini" @click="batchDelDialogVisible=false">取消</el-button>
            <el-button size="mini" type="danger" @click="delSelCollects">确认</el-button>
            </span>
        </el-dialog>
        </div>
    </div>
</template>
<script>
import {getUserCollect,getAllSongById,delUserCollect} from '../api/index';
import {mixin} from '../mixins/index'
export default {
    name: 'collect',
    props: ['id'],
    mixins: [mixin],
    data(){
        return {
            musicData: [],
            temMusicData: [],
            delDialogVisible: false,     // 删除窗口是否可见
            batchDelDialogVisible: false, //批量删除窗口是否可见
            delForm: {
                songId: '',              // 歌曲id
                userId: '',              // 用户id
            },
            input_word: '',              // 筛选条件
            multipleSelection: [] ,      // 存储用户选择打钩的项目
        }
    },
    created(){
        this.getUserCollectData();
    },
    watch: {
        input_word: function(){
            if(this.input_word == ''){
                this.musicData = this.temMusicData;
            }else{
                /* 先清空原有table，根据关键字在前端过滤一下 */
                this.musicData=[];
                for(let item of this.temMusicData){
                    if(item.songName.includes(this.input_word)){
                        this.musicData.push(item);
                    }
                }
            }
        }
    },
    methods: {
        getUserCollectData(){
            this.musicData = [];
            this.temMusicData = [];
            getUserCollect(this.$route.query.userId).then( res => {
                if(res.code == 200){
                    for(let item of res.data){
                        this.getMusicData(item.songId);
                    }
                }else if(res.code ==500 && res.msg){
                    this.notify(res.msg,'error');
                }else {
                    this.notify('删除失败','error');
                }
            }).catch( err => {
                console.log(err);
            })
        },
        getMusicData(songId){
            getAllSongById(songId).then( res => {
                if(res.code == 200){
                    this.musicData.push(res.data);
                    this.temMusicData.push(res.data);
                }else if(res.code ==500 && res.msg){
                    this.notify(res.msg,'error');
                }else {
                    this.notify('获取歌曲信息失败','error');
                }
            }).catch( err =>{
                console.log(err);
            })
        },
        handleDelete(row){
            this.delForm={
                songId: row.id
            }
            this.delDialogVisible = true;
        },
        // 删除该条记录
        delUserCollect(){
            this.delForm.userId = this.$route.query.userId;
            var delFromJson = JSON.stringify(this.delForm);
            delUserCollect(delFromJson).then( res => {
                if(res.code == 200){
                    this.notify('删除该条记录成功','success');
                    this.getUserCollectData();
                }else if(res.code ==500 && res.msg){
                    this.notify(res.msg,'error');
                }else {
                    this.notify('获取歌曲信息失败','error');
                }
            }).catch( err => {
                console.log(err);
            })
             this.delDialogVisible = false;
        },
        /**
         * 批量删除会员收藏信息
         */
        delSelCollects(){
            for(let item of this.multipleSelection){
                this.delForm={
                    songId: item.id
                }
                this.delUserCollect(item);
            }
            this.batchDelDialogVisible=false;
        }
    }
}
</script>
<style scoped>
    .handle-box{
        margin-bottom: 20px;
    }
    .handle-input{
        width: 300px;
        display: inline-block;
    }
</style>