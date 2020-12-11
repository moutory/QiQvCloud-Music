<template>
    <div class="search-song-Lists">
        <content-list :contentList="AlbumData"></content-list>
    </div>
</template>
<script>
import  ContentList from '../../components/ContentList';
import {mixin} from '../../mixins';
import {getSongListLikeTitle} from '../../api/index'
export default {
    name : 'search-song-lists',
    mixins: [mixin],
    components: {
        ContentList
    },
    mounted(){
        this.getSearchResult();
    },
    data(){
        return {
            AlbumData: []
        }
    },
    methods: {
        getSearchResult(){
            let keyword = this.$route.query.keywords;
            console.log(keyword)
            if(!keyword || keyword=='' ){
                this.notify('您输入的内容为空','warning');
            }else{
                getSongListLikeTitle(keyword).then(res => {
                    if(res.code == 200 && res.data){
                        if(res.data.length){
                            this.AlbumData = res.data;
                        }else{
                            this.notify('歌单中空空如也...','success');
                        }
                    }else if(res.code == 500 && res.msg){
                        that.$message.error(res.msg);
                    }else{
                        that.$message.error('查询歌单失败...');
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
@import '../../assets/css/search-song-lists.scss';
</style>