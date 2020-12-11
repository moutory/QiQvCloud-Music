<template>
    <div class="song-audio">
        <audio id="player"
               :src="url"
               controls="controls"
               preload="true"
               @canplay="startPlay"
               @ended="ended"
               >
               
        </audio>
    </div>
</template>
<script>
import {mapGetters} from 'vuex';
export default {
    name: 'song-audio',
    computed: {
        ...mapGetters([
            'id',
            'url',
            'isPlay'
        ])
    },
    watch: {
        isPlay: function(){
            this.togglePlay();
        }

    },
    methods:{
        startPlay(){
            let player = document.querySelector('#player');
            player.play();
        },
        ended(){
            this.isPlay = false;
        },
        // 开始，暂停
        togglePlay(){
            let player = document.querySelector('#player');
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
        display: none;
    }
</style>