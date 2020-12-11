<template>
    <div class="my-music">
        <div class="album-slide">
            <div class="album-img">
                <img :src="attachImageUrl(avator)">
            </div>
            <ul class="album-info">
                <li>昵称：{{username}}</li>
                <li>性别：{{userSex}}</li>
                <li>籍贯：{{userLocation}}</li>
                <li>生日：{{userBirth}}</li>
                <li>手机：{{userPhone}}</li>
            </ul>
        </div>
        <div class="album-content">
            <div class="album-title">
                个性签名：{{userIntroduction}}
            </div>
            <div class="songs-body">
                <album-content :songList="collectList">
                    <template slot="title">我的收藏</template>
                </album-content>
            </div>
        </div>
    </div>
</template>
<script>
import {mapGetters} from 'vuex';
import {mixin} from '../mixins/index';
import {queryUserById,getAllSongById,getUserCollect} from '../api/index'
import AlbumContent from '../components/AlbumContent';
export default {
  components: { AlbumContent },
    name: 'my-music',
    mixins: [mixin],
    computed: {
        ...mapGetters([
            'avator',
            'loginIn',
            'userId',
        ])
    },
    data(){
        return {
            username: '',
            userSex: '',
            userBirth: '',
            userLocation: '',
            userPhone: '',
            userIntroduction:'',
            userCollect: [],    // 用户收藏的歌曲id
            collectList: [],    // 歌曲对象
        }
    },
    created(){
        this.getUserMsg();
        this.getUserCollect();
    },
    methods: {
        getUserMsg(){
            if(this.loginIn && this.userId){
                queryUserById(this.userId).then( res => {
                     if(res.code == 200 && res.data){
                        this.userBirth = this.attachBirth(res.data.birth);
                        this.userPhone = res.data.phoneNum;
                        this.userLocation = res.data.location;
                        this.userSex = this.attachUserSex(res.data.sex);
                        this.username = res.data.username;
                        this.userIntroduction = this.attachUserIntroduction(res.data.introduction);
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
        getUserCollect(){
            if(this.loginIn && this.userId){
                getUserCollect(this.userId).then( res => {
                    if(res.code == 200 && res.data){
                        this.userCollect = res.data;
                        for(let item of this.userCollect){
                            this.getCollectList(item.songId);
                        }
                    }else if(res.code==500 && res.msg){
                        this.$message.error(res.msg);
                    }else{
                        this.$message.error('查询收藏歌曲列表失败');
                    }
                }).catch( err => {
                    console.log(err);
                })
            }
        },
        getCollectList(songId){
            if(songId){
                getAllSongById(songId).then( res => {
                    if(res.code == 200 && res.data){
                        this.collectList.push(res.data);
                    }else if(res.code==500 && res.msg){
                        this.$message.error(res.msg);
                    }else{
                        this.$message.error('查询歌曲信息失败');
                    }
                }).catch( err => {
                    console.log(err);
                })
            }else{
                this.$message.error('缺少歌曲Id');
            }
        }
    }
}
</script>
<style lang="scss" scoped>
@import '../assets/css/my-music.scss';
</style>