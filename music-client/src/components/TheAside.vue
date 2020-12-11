<template>
    <transition class="slide-fade">
        <div class="the-aside" v-show="showAside">
            <h2 class="title">播放列表</h2>
            <ul class="menus">
                <li v-for="(item,index) in listOfSongs" :key="index"  
                :class="{'is-play': songId == item.id}"
                @click="toPlay(item.id,item.songUrl,item.songPic,item.songLyric,item.songName,index)">
                    {{getSongName(item.songName)}}
                </li>
            </ul>
        </div>
    </transition>
</template>
<script>
import {mapGetters} from 'vuex';
import {getUserCollect} from '../api/index';
export default {
    name: 'the-aside',
    computed: {
        ...mapGetters([
            'showAside',
            'listOfSongs',
            'songId',
            'loginIn'
        ])
    },
    mounted(){
        var that = this;
        document.addEventListener('click',function(){
            that.$store.commit('setShowAside',false);
        },true)
    },
    methods: {
        getSongName(val){
            let result = val.split('-');
            if(result[1]){
                return result[1];
            }else{
                return '未知';
            }
        },
        getSingerName(val){
            let result = val;
            if(result){
                return result.split('-')[0];
            }else{
                return '未知'
            }
        },
         // 播放音乐
        toPlay: function(id , songUrl , songPic , songLyric , songName , index){
            console.log(songUrl)
            this.$store.commit('setSongId',id);
            this.$store.commit('setSongUrl',this.$store.state.configure.HOST + songUrl);
            this.$store.commit('setSongPic',this.$store.state.configure.HOST +songPic);
            this.$store.commit('setSongLyric',parseLyric(songLyric));
            this.$store.commit('setSongName',this.getSongName(songName));
            this.$store.commit('setSingerName',this.getSingerName(songName));
            this.$store.commit('setListIndex',index);
            this.$store.commit('setIsActive',false);
             // 获取收藏状态
            if(this.loginIn){
                getUserCollect(this.userId).then( res => {
                    if(res.code == 200 && res.data){
                        if(res.data.length<=0){
                                 return;
                             }
                            for(let item of res.data){
                                if(item.songId == id){
                                    this.$store.commit('setIsActive',true);
                                }
                            }
                        }else if(res.code==500 && res.msg){
                            this.$message.error(res.msg);
                        }else{
                            this.$message.error('查询歌曲收藏状态失败');
                        }
                }).catch( err => {
                    console.log(err);
                })
            }
        },
        /**
         * 解析歌词
         * 如果歌词格式不正确，不进行转换
         * 如果歌词前面部分格式不正确，则只取正确部分的歌词
         * 
         *  返回结果格式为二维数组，前一位存储时间，后一位存储歌词
         */
        parseLyric(lyric){
            let lines = lyric.split('\n');
            let pattern =  /\[\d{2}:\d{2}.(\d{3}|\d{2})\]/g;
            let result = [];
            // 对于歌词格式不正确的歌词，直接返回
            if(!/\[. + \]/.test(lyric)){
                return [[0,lyric]]
            }
            // 去除前面不正确的行
            while(!pattern.test(lines[0])){
                lines = lines.slice(1);
            }
            // 对格式正确的歌词生成二维数组
            for(let item of lines){
                let time = item.match(pattern);
                let itemLyric = item.replace(pattern,'');
                for(let item1 of time){
                    let t = item1.slice(1,-1).split(":");   //取出时间，换算成数组
                    if(value!=''){
                        result.push([parseInt(t[0],10)*60 + parseFloat(t[1]),value]);
                    }                    
                } 
            }
            // 根据时间进行排序
            result.sort(function(a,b){
                return a[0] - b[0];
            });
            return result;
        }
    }
}
</script>
<style  lang="scss" scoped>
@import '../assets/css/the-aside.scss';
</style>