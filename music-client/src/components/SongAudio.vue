<template>
    <div class="song-audio">
        <audio ref="player"
            :src="songUrl"
            controls="controls"
            preload="true"
            @canplay="startPlay"
            @ended="ended"
            @timeupdate="timeupdate"
        >
        </audio>
    </div>
</template>
<script>
import {mapGetters} from 'vuex'
export default {
    name : 'song-audio',
    computed: {
        ...mapGetters([
            'songId',
            'songUrl',
            'isPlay',
            'autoNext',
            'currentTime',
            'volume',
            'changeTime',
        ])
    },
    watch:{
        isPlay(){
            this.togglePlay();
        },
        /**
         *  根据play-bar的音量调整songAudio的音量
         */
        volume(val){
            this.$refs.player.volume = val;
        },
        /**
         *   根据play-bar组件的进度定位，更新songAudio的进度
         */
        changeTime(){
            this.$refs.player.currentTime = this.changeTime;
        }

    },
    methods:{
        startPlay(){
            let player = this.$refs.player;
            this.$store.commit('setDuration',player.duration);
            // 开始播放
            player.play();
            this.$store.commit('setIsPlay',true);
        },
        ended(){
            this.$store.commit('setIsPlay',false);
            this.$store.commit('setCurrentTime',0);
            this.$store.commit('setAutoNext',!this.autoNext);
        },
        timeupdate(){
            this.$store.commit('setCurrentTime',this.$refs.player.currentTime);
        },
        togglePlay(){
            let player = this.$refs.player;
            if(this.isPlay){
                player.play();
            }else{
                player.pause();
            }
        }
    }
}
</script>
<style scoped>
.song-audio{
    display: inline;
}
</style>
