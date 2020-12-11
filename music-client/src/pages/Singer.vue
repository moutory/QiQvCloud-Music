<template>
    <div class="singer">
        <ul class="singer-header">
            <li v-for="(item,index) in singerClass" :key="index" 
            @click="handleChangeView(item.name,item.type)" :class="{active: item.name == activeName}">
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
                    :total="singerListData.length"
                    @current-change="handlePageChange">
                </el-pagination>
            </div>
        </div>
    </div>
</template>
<script>
import {mapGetters} from 'vuex';
import {mixin} from '../mixins/index';
import {getAllSinger,getSingerBySexClass} from '../api/index'
import {singerClass} from '../assets/data/singer';
import ContentList from '../components/ContentList.vue';
export default {
  components: { ContentList },
    name: 'singer',
    mixins: [mixin],
    computed:{
       currentPageData(){
           return this.singerListData.slice((this.currentPage-1)*this.pageSize,this.currentPage*this.pageSize);
       }
    },
    mounted(){
        this.singerClass = singerClass;
        this.getSingerList();
    },
    data(){
        return {
            singerClass: [],   // 所有歌手的分类
            singerListData: [],   // 所有歌手内容
            currentPage: 1,     // 当前页码
            pageSize: 15,       // 每页的显示数量
            activeName: '全部歌手', //当前选中的筛选条件
        }
    },
    methods: {
        getSingerList(){
            getAllSinger().then(res => {
                if(res.code == 200 ){
                    this.singerListData = res.data;
                }else if(res.code == 500 && res.msg){
                    that.$message.error(res.msg);    
                }else{
                    that.$message.error('查询歌手失败')
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
         * 根据风格筛选歌手
         */
        handleChangeView(sexClass,type){
            this.activeName = sexClass;
            this.singerListData = [];
            if(sexClass == '全部歌手'){
                this.getSingerList();
            }else{
                getSingerBySexClass(type).then( res => {
                    if(res.code == 200 ){
                        this.singerListData = res.data;
                        this.currentPage = 1;
                    }else if(res.code == 500 && res.msg){
                        that.$message.error(res.msg);    
                    }else{
                        that.$message.error('查询'+styleName+'歌手失败')
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
@import '../assets/css/singer.scss';
</style>
