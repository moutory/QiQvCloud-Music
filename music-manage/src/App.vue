<template>
  <div id="app">
    <SongAudio/>
    <router-view></router-view>
  </div>
</template>

<script>
import SongAudio from './components/SongAudio';
export default {
  components: { SongAudio },
  name: 'App',
  comments: {
    SongAudio
  }
}
</script>

<style>
</style>
