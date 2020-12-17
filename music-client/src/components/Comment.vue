<template>
    <div>
        <div class="comment">
            <h2>评论</h2>
            <div class="comment-msg">
                <div class="comment-img">
                    <img :src="attachImageUrl(avator)">
                </div>
                <el-input class="comment-input" type="textarea" :rows="2" placeholder="请输入评论..." v-model="commentForm.content"></el-input>
            </div>
            <el-button type="primary" class="sub-btn" @click="postComment">提交评论</el-button>
        </div>
        <p>精彩评论：共{{commentData.length}}条评论</p>
        <ul class="popular" v-for="(item,index) in commentData" :key="index">
            <li>
                <div class="popular-img">
                    <img :src="attachImageUrl(userPic[index])"/>
                </div>
                <div class="popular-msg">
                    <ul>
                        <li class="name">{{userName[index]}}</li>
                        <li class="time">{{item.createTime}}</li>
                        <li class="content">{{item.content}}</li>
                    </ul>
                </div>
                <div class="up" ref="up" @click="postUp(item.id,item.up,item.userId,index)">
                    <svg class="icon">
                        <use xlink:href="#icon-zan"></use>
                    </svg>
                    {{item.up}}
                </div>
            </li>
        </ul>
    </div>
</template>
<script>
import {mixin} from '../mixins/index';
import {mapGetters} from 'vuex';
import {getAllComment,saveComment,queryUserById,setLikeComment} from '../api/index';
export default {
    name: 'common',
    mixins: [mixin],
    props: [
        'playId', // 歌曲或者歌单Id
        'type'    // 0歌曲，1歌单
    ],
    computed: {
        ...mapGetters ([
            'avator', //用户头像
            'loginIn',
            'userId',
            'flowerId'
        ])
    },
    data(){
        return {
            commentForm: {
                content: '',
                userId: '' ,        //用户id
                songListId: '',     // 歌单id
                songId: ''          // 歌曲id
            },
            updateCommentForm:{
                id: '',
                up: 0,
                flowerId: '',
            },
            commentData: [],    //评论内容
            userPic: [],        //用户头像
            userName: []        //用户昵称
        }
    },
    mounted(){
        //加载已有的评论
        this.getComment();
    },
    methods: {
        postComment(){
            var that = this;
            if(that.loginIn && that.userId){
                if(that.type == 0){
                    that.commentForm.songId = that.playId;
                }else{
                    that.commentForm.songListId = that.playId;
                }
                that.commentForm.userId = that.userId;
                that.commentForm.type = that.type;
                var commentJson = JSON.stringify(that.commentForm);
                saveComment(commentJson).then(res => {
                    if(res.code == 200 ){
                        that.notify('评论成功','success');
                        this.commentForm.content='';
                        that.getComment();
                    }else if(res.code == 500 && res.msg){
                        that.$message.error(res.msg);    
                    }else{
                        that.$message.error('评论失败')
                    }
                }).catch(err => {
                    console.log(err);
                })
            }else{
                that.notify('请先登录!!','error')
            }
        },
        getComment(){
            var that = this;
            if(that.playId && that.type){
                getAllComment(that.playId,that.type).then(res => {
                    if(res.code == 200 && res.data){
                        that.commentData = res.data;
                        for(let item of that.commentData){
                            that.getUsers(item.userId);
                        }
                    }else if(res.code == 500 && res.msg){
                        that.$message.error(res.msg);
                    }else{
                        that.$message.error('查询评论失败');
                    }
                }).catch(err => {
                    console.log(err);
                })
            }else{
                that.$message.error('请选择评论查询对象');
            }
        },
        getUsers(userId){
            if(userId){
                queryUserById(userId).then(res => {
                    if(res.code == 200 && res.data){
                       this.userPic.push(res.data.avator);
                       this.userName.push(res.data.username);
                    }else if(res.code == 500 && res.msg){
                        that.$message.error(res.msg);    
                    }else{
                        that.$message.error('查询评论用户信息失败')
                    }
                }).catch( err => {
                    console.log(err);
                })
            }else{
                this.$message.error('获取评论用户信息失败...');
            }
        },
        /**
         * 给某个评论点赞
         */
        postUp(commentId,upNumber,commentOwerId,index){
            var that = this;
            if(that.loginIn){
                if(commentId){
                    if(commentOwerId == that.userId){
                        this.$message.error('您不能给自己的评论点赞');
                        return;
                    }
                    that.updateCommentForm.id = commentId;
                    that.updateCommentForm.up = upNumber+1;
                    that.updateCommentForm.userId = commentOwerId;
                    that.updateCommentForm.flowerId = that.userId;
                    var commentJson = JSON.stringify(this.updateCommentForm);
                    setLikeComment(commentJson).then(res => {
                        if(res.code == 200 ){
                        that.$refs.up[index].children[0].style.color = '#2796cd';
                        that.getComment();
                        }else if(res.code == 500 && res.msg){
                            that.$message.error(res.msg);    
                        }else if(res.code == 401 && res.msg){
                            that.$message.error(res.msg);    
                        }else{
                            that.$message.error('点赞评论失败')
                        }
                    }).catch( err => {
                        console.log(err);
                    })
                }else{
                    this.$message.error('请选择点赞的评论');
                }
            }else{
                this.notify('请先登录','error');
            }
        }
    }
}
</script>
<style lang="scss" scoped>
@import '../assets/css/comment.scss';
</style>