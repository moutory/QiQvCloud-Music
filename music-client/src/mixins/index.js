import {getSongsLikeName,getUserCollect} from '../api/index';
import {mapGetters} from 'vuex';
export const mixin ={
    computed: {
        ...mapGetters([
            'loginIn',
            'userId'
        ])
    },
    methods: {
        // 获取图片地址
        attachImageUrl(imgUrl){
            return imgUrl?this.$store.state.configure.HOST +imgUrl:this.$store.state.configure.HOST + '/img/songListPic/123.jpg';
        },
        getSongsLikeName(){
            if(this.$route.query.keywords){
                // 向后台发起请求
                getSongsLikeName(this.$route.query.keywords).then(res=>{
                    if(res.code == 200){
                        if(res.data.length){
                            this.$store.commit('setListOfSongs',res.data);
                        }else{
                            this.$store.commit('setListOfSongs',[]);
                            this.notify('暂无符合条件的歌曲...','warning');
                        }
                    }else if(res.code ==500 && res.msg){
                        this.notify(res.msg,'warning');
                    }else{
                        this.notify('查询歌曲出错...','error');
                    }
                }).catch(err =>{
                    console.log(err);
                })
            }else{
                this.$store.commit('setListOfSongs',[]);
                this.notify('您输入的内容为空','warning');
            }
        },
        notify(title,type){
            this.$notify({
                title: title,
                type: type
            })
        },
        getSingerName(val){
            let result = val;
            if(result){
                return result.split('-')[0];
            }else{
                return '未知'
            }
        },
        getSongName(val){
            let result = val.split('-');
            if(result[1]){
                return result[1];
            }else{
                return '未知';
            }
        },
        // 播放音乐
        toPlay: function(id , songUrl , songPic , songLyric , songName , index){
            this.$store.commit('setSongId',id);
            this.$store.commit('setSongUrl',this.$store.state.configure.HOST + songUrl);
            this.$store.commit('setSongPic',this.$store.state.configure.HOST +songPic);
            this.$store.commit('setSongLyric',this.parseLyric(songLyric));
            this.$store.commit('setSongName',this.getSongName(songName));
            this.$store.commit('setSingerName',this.getSingerName(songName));
            this.$store.commit('setListIndex',index);
            this.$store.commit('setIsActive',false);
            // 获取收藏状态
            if(this.loginIn){
                getUserCollect(this.userId).then( res => {
                    if(res.code == 200 && res.data){
                        if(res.data.length<=0){
                            return;
                        }
                        for(let item of res.data){
                            if(item.songId == id){
                                this.$store.commit('setIsActive',true);
                            }
                        }
                    }else if(res.code==500 && res.msg){
                        this.$message.error(res.msg);
                    }else{
                        this.$message.error('查询歌曲收藏状态失败');
                    }
                }).catch( err => {
                    console.log(err);
                })
            }
        },
        /**
         * 解析歌词
         * 如果歌词格式不正确，不进行转换
         * 如果歌词前面部分格式不正确，则只取正确部分的歌词
         * 
         *  返回结果格式为二维数组，前一位存储时间，后一位存储歌词
         */
        parseLyric(lyric){
            console.log('hhh')
            let lines = lyric.split('\n');
            let pattern =  /\[\d{2}:\d{2}.(\d{3}|\d{2})\]/g;
            let result = [];
            // 对于歌词格式不正确的歌词，直接返回
            if(!(/\[.+\]/.test(lyric))){
                return [[0,lyric]]
            }
            // 去除前面不正确的行
            while(!pattern.test(lines[0])){
                lines = lines.slice(1);
            }
            // 对格式正确的歌词生成二维数组
            for(let item of lines){
                let time = item.match(pattern);
                let itemLyric = item.replace(pattern,'');
                for(let item1 of time){
                    let t = item1.slice(1,-1).split(":");   //取出时间，换算成数组
                    if(itemLyric!=''){
                        result.push([parseInt(t[0],10)*60 + parseFloat(t[1]),itemLyric]);
                    }                    
                } 
            }
            // 根据时间进行排序
            result.sort(function(a,b){
                return a[0] - b[0];
            });
            return result;
        },
        /**
         * 出生日期格式化：取前10位
         */
        attachBirth(val){
            return val?val.substr(0,10):'未知';
        },
        /**
         * 用户性别转换
         */
        attachUserSex(val){
            if(val == 0){
                return '男';
            }else if(val == 1){
                return '女';
            }else if(val == 2){
                return '组合';
            }else{
                return '不详';
            }
        },
        /**
         * 
         */
        attachUserIntroduction(val){
            if(!val ||val == ''){
                return '懒...个性签名什么都没有说'
            }else{
                return val;
            }
        }
    }
}