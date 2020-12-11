<!-- 首页 -->
<template>
  <div class="home">
    <swiper></swiper>
    <div class="section" v-for="(item,index) in songsList" :key="index">
      <div class="section-title">{{item.name}}</div>
      <content-list :contentList="item.list"></content-list>
    </div>
  </div>
</template>

<script>
import Swiper from '../components/Swipper'
import ContentList from '../components/ContentList' 
import {getAllSongList,getAllSinger} from '../api/index'
export default {
  name: 'home',
  components: {
    Swiper,
    ContentList
  },
  created(){
    this.getSongList();
    this.getSinger();
  },
  data () {
    return {
      songsList: [
        {name: '歌单',list: []},
        {name: '歌手',list: []}
      ]
    }
  },
  methods:{
    getSongList(){
      var that = this;
      getAllSongList().then(res =>{
        if(res.code == 200){
          that.songsList[0]['list'] = res.data.slice(0,10);
        }else if(res.code == 500 && res.msg){
          that.$message.error(res.msg);
        }else{
          that.$message.error('查询歌单失败');
        }
      }).catch(err =>{
        console.log(err);
      })
    },
    getSinger(){
      var that = this;
      getAllSinger().then(res=>{
        if(res.code == 200){
          that.songsList[1].list = res.data.slice(0,10);
        }else if(res.code == 500 && res.msg){
          that.$message.error(res.msg);
        }else{
           that.$message.error('查询歌曲失败'); 
        }
      }).catch(err=>{
        console.log(err);
      })
    }
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/home.scss';
</style>
