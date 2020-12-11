<template>
    <div class="song-list-album">
        <div class="album-slide">
            <div class="album-img">
                <img :src="attachImageUrl(temList.songlistPic)">
            </div>
            <div class="album-info">
                <h2>简介：</h2>
                <span>
                    {{temList.songlistIntroduction}}
                </span>
            </div>
        </div>
        <div class="album-content">
            <div class="album-title">
                <p>{{temList.songlistTitle}}</p>
            </div>
            <div class="album-score">
                <div>
                    <h3>歌单评分：</h3>
                    <div>
                        <el-rate v-model="average" disabled></el-rate>
                    </div>
                </div>
                <span>{{average*2}}</span>
                <div>
                    <h3>评价：</h3>
                    <div @click="setRank">
                        <el-rate v-model="rankForm.score" allow-half show-text></el-rate>
                    </div>
                </div>
            </div>
            <div class="songs-body">
                <album-content :songList="listOfSongs">
                    <template slot="title">歌单</template>
                </album-content>
                <comment :playId="songListId" :type="1"/>
            </div>
        </div>
    </div>
</template>
<script>
import {mapGetters} from 'vuex';
import {mixin} from '../mixins/index';
import {getAllSongOfList,getAllSongById,getAverageRank,rateSongList} from '../api/index';
import AlbumContent from '../components/AlbumContent';
import Comment from '../components/Comment';
export default {
    name: 'song-list-album',
    mixins: [mixin],
    components: {
        AlbumContent,
        Comment
    },
    data(){
        return {
            songListId: '',
            songOfSongList: [],  //歌单中的所有歌曲
            average: 0 , //歌单的平均分
            rankForm:{
                score: 0, //用户提交的歌单评分
                songListId: '', //歌单id
                consumerId: '' //用户Id
            }
        }
    },
     computed:{
        ...mapGetters([
            'temList',
            'listOfSongs',
            'loginIn', // 用户是否已经登录
            'userId'
        ])
    },
    created(){
        this.songListId = this.$route.params.id;
        this.getSongs();
        this.getAverageRank();
    },
    methods: {
        getSongs(){
            var that = this;
            getAllSongOfList(this.songListId).then( res => {
                if(res.code == 200 && res.data){
                    for(let item of res.data){
                        that.getSongOfSongList(item.songId);
                    }
                    this.$store.commit('setListOfSongs',that.songOfSongList); //切换当前的播放列表
                }else if(res.code == 500 && res.msg){
                    this.$message.error(res.msg);
                }else{
                    this.$message.error('查询歌单详情信息失败...');
                }
            }).catch( err => {
                console.log(err);
            })
        },
        getSongOfSongList(songId){
            getAllSongById(songId).then( res => {
                if(res.code == 200 && res.data){
                    this.songOfSongList.push(res.data);
                }else if(res.code == 500 && res.msg){
                    this.$message.error(res.msg);
                }else{
                    this.$message.error('查询歌曲信息失败...');
                }
            }).catch( err => {
                console.log(err);
            })
        },
        /**
         *  评价歌单
         */
        setRank(){
            var that = this;
            // 用户未登录，则不允许评分
            if(this.loginIn){
                if(this.rankForm.score){
                    that.rankForm.score = 2*that.rankForm.score;
                    that.rankForm.consumerId = that.userId;
                    that.rankForm.songListId = that.songListId;
                    var rankJson = JSON.stringify(that.rankForm);
                    rateSongList(rankJson).then(res => {
                        if(res.code == 200 && res.data){
                            console.log(res.data);
                            that.notify('评分成功','success');
                            that.getAverageRank();
                        }else if(res.code == 500 && res.msg){
                            that.$message.error(res.msg);
                        }else{
                            that.$message.error('歌单评价失败...');
                        }
                    }).catch(err => {
                        console.log(err);
                    })
                }else{
                    that.$message.error('请选择您的分数！')
                }
            }else{
                that.score = 0;
                that.$message.error('您尚未登录，请登录后再评价');
            }
        },
        /**
         *  获取歌单平均评分
         */
        getAverageRank(){
            getAverageRank(this.songListId).then(res => {
                if(res.code == 200 && res.data){
                   console.log(res.data)
                   this.average = res.data/2;
                }else if(res.code == 500 && res.msg){
                    this.$message.error(res.msg);
                }else{
                    this.$message.error('查询歌单平均分信息失败...');
                }
            }).catch(err => {
                console.log(err);
            })
        }
    }
}
</script>
<style lang="scss" scoped>
@import '../assets/css/song-list-album.scss';
</style>