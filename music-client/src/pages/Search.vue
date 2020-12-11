<template>
    <div class="search">
        <nav class="searchList-nav" ref="change">
            <span :class="{isActive: toggle =='Songs'}" @click="handleChangeView('Songs')" >歌曲</span>
            <span :class="{isActive: toggle =='SongLists'}" @click="handleChangeView('SongLists')">歌单</span>
        </nav>
        <component :is="currentView"></component>
    </div>
</template>
<script>
import SearchSongs from '../components/search/SearchSongs';
import SearchSongLists from '../components/search/SearchSongLists';

export default {
    name: 'search',
    components: {
        SearchSongs,
        SearchSongLists
    },
    data(){
        return {
            toggle: 'Songs',
            currentView: 'searchSongs'
        }
    },
    methods:{
        handleChangeView(destinationView){
            this.toggle = destinationView;
            this.currentView = 'Search' + destinationView;
        }
    }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/search.scss';
</style>