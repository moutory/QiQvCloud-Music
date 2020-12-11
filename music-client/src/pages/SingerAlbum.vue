<template>
    <div class="singer-album">
        <div class="album-slide">
            <div class="singer-img">
                <img :src="attachImageUrl(temList.singerPic)">
            </div>
            <ul class="info">
                <li>{{attachSex(temList.singerSex)}}</li>
                <li>生日：{{attachBirth(temList.singerBirth)}}</li>
                <li>故乡：{{temList.singerLocaltion}}</li>
            </ul>
        </div>
        <div class="album-content">
            <div class="intro">
                <h2>{{temList.singerName}}</h2>
                <span>{{temList.singerIntroduction}}</span>
            </div>
            <div class="content">
                <album-content :songList="listOfSongs">
                    <template slot="title">歌曲</template>
                </album-content>
            </div>
        </div>
    </div>
</template>
<script>
import {getAllSongBySingerId } from '../api/index';
import {mixin} from '../mixins/index';
import {mapGetters} from 'vuex';
import AlbumContent from '../components/AlbumContent'
export default {
    name: 'singer-album',
    components: {
        AlbumContent
    },
    mixins: [mixin],
    computed: {
        ...mapGetters([
            'loginIn',
            'temList',  //当前的歌手对象
            'listOfSongs', //当前的歌曲列表，当切换到新的
        ])
    },
    created(){
        this.singerId = this.$route.params.id;
        // 获取歌手的所有歌曲
        this.getSongBySingerId(this.temList.id);
        
    },
    data(){
        return {
            singerId: '',
        }
    },
    methods: {
        /**
         * 根据歌手Id获取歌曲
         */
        getSongBySingerId(singerId){
            getAllSongBySingerId(singerId).then(res => {
                if(res.code == 200 && res.data){
                    this.$store.commit('setListOfSongs',res.data);  
                }else if(res.code == 500 && res.msg){
                    that.$message.error(res.msg);
                }else{
                    that.$message.error('获取歌手相关歌曲失败...');
                }
            }).catch( err => {
                console.log(err);
            })
        },
        /**
         *  格式化歌手性别
         */
        attachSex(val){
            if(val == 0){
                return '男';
            }else if(val == 1){
                return '女';
            }else if(val == 2){
                return '组合';
            }else{
                return '不详';
            }
        },
        
    }
}
</script>
<style lang="scss" scoped>
@import '../assets/css/singer-album.scss';
</style>