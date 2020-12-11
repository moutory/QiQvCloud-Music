<template>
  <div id="app">    
    <the-header></the-header>
    <router-view class="music-content"/>   
    <song-audio/>
    <the-aside/>
    <scroll-top/>
    <music-play-bar/>
    <the-footer></the-footer>
  </div>
</template>

<script>
import TheHeader from './components/TheHeader';
import TheFooter from './components/TheFooter';
import ScrollTop from './components/ScrollTop';
import SongAudio from './components/SongAudio';
import MusicPlayBar from './components/MusicPlayBar.vue';
import TheAside from './components/TheAside'

export default {
  name: 'App',
  components: {
    TheHeader,
    TheFooter,
    ScrollTop,
    SongAudio,
    MusicPlayBar,
    TheAside
    
  }
}
</script>

<style  lang="scss" scoped>
@import './assets/css/app.scss';
</style>
