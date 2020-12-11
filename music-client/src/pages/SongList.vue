<template>
    <div class="song-list">
        <ul class="song-list-header">
            <li v-for="(item,index) in songListStyle" :key="index" 
            @click="handleChangeView(item.name)" :class="{active: item.name == activeName}">
                {{item.name}}
            </li>
        </ul>
        <div>
            <content-list :contentList="currentPageData"></content-list>
            <div class="pagination">
                <el-pagination 
                    background layout="total,prev,pager,next" 
                    :currentPage="currentPage"
                    :page-size="pageSize"
                    :total="songListData.length"
                    @current-change="handlePageChange">
                </el-pagination>
            </div>
        </div>
    </div>
</template>
<script>
import {mapGetters} from 'vuex';
import {mixin} from '../mixins/index';
import {getAllSongList,getSongListLkeStyle} from '../api/index'
import {songListStyle} from '../assets/data/songList';
import ContentList from '../components/ContentList.vue';
export default {
  components: { ContentList },
    name: 'song-list',
    mixins: [mixin],
    computed:{
       currentPageData(){
           return this.songListData.slice((this.currentPage-1)*this.pageSize,this.currentPage*this.pageSize);
       }
    },
    mounted(){
        this.songListStyle = songListStyle;
        this.getSongList();
    },
    data(){
        return {
            songListStyle: [],   // 所有歌单的分类
            songListData: [],   // 所有歌单内容
            currentPage: 1,     // 当前页码
            pageSize: 15,       // 每页的显示数量
            activeName: '全部歌单', //当前选中的筛选条件
        }
    },
    methods: {
        getSongList(){
        getAllSongList().then(res => {
            if(res.code == 200 ){
                this.songListData = res.data;
            }else if(res.code == 500 && res.msg){
                that.$message.error(res.msg);    
            }else{
                that.$message.error('查询歌单失败')
            }
        }).catch( err => {
            console.log(err);
        })
    },
        /**
         *  修改页码的时候触发，更新
         */
        handlePageChange(val){
            this.currentPage = val;
        },
        /**
         * 根据风格筛选歌单
         */
        handleChangeView(styleName){
            this.activeName = styleName;
            this.songListData = [];
            if(styleName == '全部歌单'){
                this.getSongList();
            }else{
                getSongListLkeStyle(styleName).then( res => {
                    if(res.code == 200 ){
                        this.songListData = res.data;
                        this.currentPage = 1;
                    }else if(res.code == 500 && res.msg){
                        that.$message.error(res.msg);    
                    }else{
                        that.$message.error('查询'+styleName+'歌单失败')
                    }
                }).catch( err => {
                    console.log(err);
                })
            }
        }
    }
}
</script>
<style lang="scss" scoped>
@import '../assets/css/song-list.scss';
</style>
