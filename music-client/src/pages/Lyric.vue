<template>
    <div class="song-lyric">
        <h1 class="lyric-title">歌词</h1>
        <!-- 有歌词 -->
        <ul class="has-lyric" v-if="songLyr.length" key="index">
            <li v-for="(item,index) in songLyr" v-bind:key="index">
                {{item[1]}}
            </li>
        </ul>
        <!-- 没有歌词 -->
        <ul v-else class="no-lyric" key="no-lyric">
            <span>暂无歌词</span>
        </ul>
    </div>
</template>
<script>
import {mapGetters} from 'vuex';
import {mixin} from '../mixins/index'
export default {
    name: 'song-lyric',
    mixins: [mixin],
    data(){
        return {
            /**
             * 这里新建一个歌词变量是因为这样比较方便后续只维护歌词页面的歌词
             *  而不会影响到歌词在其他地方的使用
             */
            songLyr: []
        }
    },
    computed: {
        ...mapGetters([
            'songLyric',
            'currentTime',
            'songId',
            'listOfSongs', // 当前歌单
            'listIndex'
        ])
    },
    watch: {
        /**
         *   监控当前的歌曲进度，当前歌词高亮显示
         *   该方法本质上是取歌曲当前的播放时间，匹配到当前的歌词后
         *   将所有歌词字体先进行普通化，再对当前歌词进行特殊话
         *   这里要注意两点：
         *    1、 for循环中的第一个if，使得只有当前时间小于播放时间部分的歌词会被筛选到。即如果当前进度是15s，那么只有1s、2s...15s这部分的歌词
         *        在循环中
         *    2、 第二个for循环使得后面的操作会覆盖掉前面的操作。也就是说，最终对字体设置生效的，只有当前和时间匹配的歌词
         */
        currentTime: function(){
            if(this.songLyr.length > 0){
                for(let i = 0 ;i < this.songLyr.length ; i++){
                    if(this.currentTime >= this.songLyr[i][0]){
                        for(let j = 0 ; j < this.songLyr.length ; j++){
                            document.querySelectorAll('.has-lyric li')[j].style.color = '#000';
                            document.querySelectorAll('.has-lyric li')[j].style.fontSize = '15px';
                        }
                        if( i >= 0){
                            document.querySelectorAll('.has-lyric li')[i].style.color = '#95d2f6';
                            document.querySelectorAll('.has-lyric li')[i].style.fontSize = '25px';
                        }
                    }
                }
            }
        },
        /**
         * 根据当前歌曲更新歌词
         */
        songId: function(){
            this.songLyr = this.parseLyric(this.listOfSongs[this.listIndex].songLyric);
        }
    },
    created(){
        this.songLyr = this.songLyric;
    }
}
</script>
<style lang="scss" scoped>
@import '../assets/css/lyric.scss';
</style>