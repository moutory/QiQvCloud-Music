<template>
    <div class="content">
        <h1 class="title">
            <slot name="title"></slot>
            <hr/>
        </h1>
        <ul>
            <li>
               <div class="song-item">
                   <span class="item-index"></span>
                   <span class="item-title">歌曲名</span>
                   <span class="item-name">歌手</span>
                   <span class="item-intro">专辑</span>
               </div> 
            </li>
            <li v-for="(item,index) in songList" :key="index">
                <div class="song-item" @click="toPlay(item.id,item.songUrl,item.songPic,item.songLyric,item.songName,index)">
                   <span class="item-index">{{index +1}}</span>
                   <span class="item-title">{{getSongName(item.songName)}}</span>
                   <span class="item-name">{{getSingerName(item.songName)}}</span>
                   <span class="item-intro">{{item.songIntroduction}}</span>
               </div> 
            </li>
        </ul>
    </div>
</template>
<script>
import {mixin} from '../mixins'
export default {
    name: 'album-content',
    mixins:[mixin],
    props:[
        'songList'
    ]
}
</script>
<style lang="scss" scoped>
@import '../assets/css/album-content.scss';
</style>