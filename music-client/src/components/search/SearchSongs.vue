<template>
    <div class="search-songs">
        <album-content :songList="listOfSongs"></album-content>
    </div>
</template>
<script>
import {mixin} from '../../mixins/index';
import {mapGetters} from 'vuex';
import AlbumContent from '../AlbumContent.vue';
export default {
    name: 'search-songs',
    mixins: [mixin],
    components:{
        AlbumContent
    },
    computed:{
        ...mapGetters([
            'listOfSongs'
        ])
    },
    mounted(){
        this.getSongsLikeName();
    }
}
</script>
<style lang="scss" scoped>
@import '../../assets/css/search-songs.scss';
</style>