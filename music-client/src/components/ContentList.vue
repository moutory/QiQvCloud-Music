<!-- 这个组件用于根据歌手/歌曲或者其他条目对数据进行排列 -->
<template>
    <div class="content-list">
        <ul class="section-content">
            <li class="content-item" v-for="(item,index) in contentList" :key="index">
                <div class="kuo" @click="goAlum(item,item.singerName)">
                    <img class="item-img" :src="attachImageUrl(item.songlistPic || item.singerPic)">
                    <div class="mask">
                        <svg class="icon">
                            <use xlink:href="#icon-bofang"></use>
                        </svg>
                    </div>
                </div>
                <p class="item-name">{{item.songlistTitle||item.singerName}}</p>
            </li>
        </ul>
    </div>
</template>
<script>
import { mixin } from '../mixins'
export default {
    name: 'content-list',
    mixins: [mixin],
    props: ['contentList'],
    methods:{
        goAlum(item,type){
            this.$store.commit("setTemList",item);
            // 因为只有song才有name，从而区分是歌手还是歌单
            if(type){
                this.$router.push({path: `singer-album/${item.id}`});
            }else{
                this.$router.push({path: `song-list-album/${item.id}`});
            }
        }
    }
}
</script>
<style lang="scss" scoped>
@import '../assets/css/content-list.scss';
</style>