<template>
    <div class="table">
        <div class="crumbs">
            <i class="el-icon-tickets">评论列表</i>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-button type="danger" size="mini" @click="batchDelDialogVisible=true">批量删除</el-button>
                <el-input size="mini" class="handle-input" placeholder="请输入歌曲名" v-model="input_word"></el-input>
            </div>
            <el-table size="mini" border style="width:100%" height="500px" :data="commentData" @selection-change="handleSelectItem">
                <el-table-column type="selection" width="40"></el-table-column>
                <el-table-column prop="username" label="用户名" align="center"></el-table-column>
                <el-table-column prop="content" label="评论内容" align="center"></el-table-column>
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
            <el-button size="mini" type="danger" @click="delSelComments">确认</el-button>
            </span>
        </el-dialog>
        </div>
    </div>
</template>
<script>
import {queryCommentBySongListId,queryUserById,delComment,batchDeleteSelComments} from '../api/index';
import {mixin} from '../mixins/index'
export default {
    name: 'comment',
    mixins: [mixin],
    data(){
        return {
            commentData: [],
            temCommentData: [],
            delDialogVisible: false,     // 删除窗口是否可见
            batchDelDialogVisible: false, //批量删除窗口是否可见
            delForm: {
                id: '',
                userId: '',              // 评论的用户id
                songListId: '',          // 歌单id
                username: '',           // 评论的用户名
                songId: '' ,            // 评论的歌曲id，暂时用不上
            },
            batchDelForm: [],           // 记录批量删除评论的id数组
            input_word: '',              // 筛选条件
            multipleSelection: [] ,      // 存储用户选择打钩的项目
        }
    },
    created(){
        this.getCommentOfSongList();
    },
    watch: {
        input_word: function(){
            if(this.input_word == ''){
                this.commentData = this.temCommentData;
            }else{
                /* 先清空原有table，根据关键字在前端过滤一下 */
                this.commentData=[];
                for(let item of this.temCommentData){
                    if(item.songName.includes(this.input_word)){
                        this.commentData.push(item);
                    }
                }
            }
        }
    },
    methods: {
        getCommentOfSongList(){
            this.commentData = [];
            this.temCommentData = [];
            queryCommentBySongListId(this.$route.query.songListId).then( res => {
                if(res.code == 200 && res.data){
                    this.temCommentData = [];
                    this.commentData= [];
                    if(res.data.length<0){
                        this.$message.success('该歌单暂时没有评论哦~');
                        return;
                    }
                    for(let item of res.data){
                        this.getUserData(item);
                        
                    }
                }else if(res.code ==500 && res.msg){
                    this.notify(res.msg,'error');
                }else {
                    this.notify('查询评论信息失败','error');
                }
            }).catch( err => {
                console.log(err);
            })
        },
        getUserData(item){
            queryUserById(item.userId).then( res => {
                if(res.code == 200){
                    let o = item;
                    o.username = res.data.username;
                    this.temCommentData.push(o);
                    this.commentData.push(o);
                    console.log(this.temCommentData)
                    console.log(this.commentData)
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
                id: row.id
            }
            this.delDialogVisible = true;
        },
        // 删除该条记录
        delUserCollect(){
            console.log('第二步的删除单条记录')
            var delFromJson = JSON.stringify(this.delForm);
            delComment(delFromJson).then( res => {
                if(res.code == 200){
                    this.notify('删除该条记录成功','success');
                    console.log('批量删除记录成功')
                    this.getCommentOfSongList();
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
         * 批量删除会员评论信息
         */
        delSelComments(){
            for(let item of this.multipleSelection){
                this.batchDelForm.push(item.id);
            }
            this.batchDeleteSelComments();
            this.multipleSelection=[];
            this.batchDelDialogVisible=false;
        },
        batchDeleteSelComments(){
            console.log(this.batchDelForm);
            var batchDelFormJson = JSON.stringify(this.batchDelForm);
            batchDeleteSelComments(batchDelFormJson).then( res => {
                if(res.code == 200){
                    this.notify('删除该条记录成功','success');
                    console.log('删除当条记录成功')
                    this.getCommentOfSongList();
                }else if(res.code ==500 && res.msg){
                    this.notify(res.msg,'error');
                }else {
                    this.notify('获取歌曲信息失败','error');
                }
            }).catch( err => {
                console.log(err);
            })
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