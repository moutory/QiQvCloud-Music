<template>
    <div class="play-bar" :class="{show: !toggle}">
            <!-- 界面收缩按键 -->
            <div class="item-up" @click="toggle = !toggle" :class="{turn :!toggle}">
                <svg class="icon">
                    <use xlink:href="#icon-jiantou-xia-cuxiantiao"></use>
                </svg>
            </div>
        <div class="kongjian">
            <!-- 上一首 -->
            <div class="item" @click="prev">
                <svg class="icon">
                    <use xlink:href="#icon-ziyuanldpi"></use>
                </svg>
            </div>
            <!-- 播放 -->
            <div class="item" >
                <svg class="icon" @click="togglePlay">
                    <use :xlink:href="playButtonUrl"></use>
                </svg>
            </div>
            <!-- 下一首 -->
            <div class="item" @click="next">
                <svg class="icon">
                    <use xlink:href="#icon-ziyuanldpi1"></use>
                </svg>
            </div>
            <!-- 专辑图片 -->
            <div class="item-img" @click="toLyric">
                <img :src="songPic" />
            </div>
            <!-- 播放进度 -->
            <div class="playing-speed">
                <!-- 播放开始时间 -->
                <div class="current-time">{{playCurrentTime}}</div>
                <!-- 歌曲进度框 -->
                <div class="progress-box">
                    <div class="item-song-title">
                        <div>{{this.songName}}</div>
                        <div>{{this.singerName}}</div>
                    </div>
                    <div class="progress" ref="progress" @mousemove="editProgress" @mouseleave="stopProgress">
                        <!-- 进度条 -->
                        <div class="bg" ref="bg" @click="updatemove">
                            <div ref="curProgress" class="cur-Progress" :style="{width:curLength + '%' }"></div>
                        </div>
                        <!-- 进度点 -->
                        <div class="idot" ref="idot" :style="{left: curLength+'%'}" @mousedown="mousedown" @mouseup="mouseup"></div>
                    </div>
                </div>
                <!-- 播放结束时间 -->
                <div class="left-time">{{songTime}}</div>
                <!-- 音量 -->
                <div class="item item-volume">
                    <!-- 音量为0，切换为静音图标 -->
                    <svg v-if="musicVolume == 0" class="icon">
                        <use xlink:href="#icon-yinliangjingyinheix"></use>
                    </svg>
                    <svg  v-else class="icon">
                        <use xlink:href="#icon-yinliang1"></use>
                    </svg>
                    <el-slider class="volume" v-model="musicVolume" :vertical="true"></el-slider>
                </div>
                <!-- 收藏 -->
                <div class="item" @click="postCollect">
                    <svg class="icon" :class="{active: isActive}">
                        <use xlink:href="#icon-xihuan-shi"></use>
                    </svg>
                </div>
                <!-- 下载 -->
                <div class="item" @click="downloadMusic">
                    <svg class="icon">
                        <use xlink:href="#icon-xiazai"></use>
                    </svg>
                </div>
                <!-- 当前播放的歌曲列表 -->
                <div class="item" @click="showAside">
                    <svg class="icon">
                        <use xlink:href="#icon-liebiao"></use>
                    </svg>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import {mapGetters} from 'vuex';
import {downloadMusic,addCollect,getUserCollect} from '../api/index'
export default {
    name: 'music-play-bar',
    
    data(){
        return {
            playCurrentTime: '00:00',
            curLength: 0,
            progressLength: 0, // 进度条这个元素组件的总长度
            songTime: '00:00', // 歌曲总长度
            musicVolume: 50 ,// 音量大小（比例）
            tag: false,     // 进度条拖拽开始/结束的状态
            mouseStartX: 0 , // 拖拽开始的距离(拖拽点在浏览器页面的X坐标)
            toggle: true, // 是否显示play-bar
            collectForm: {
                userId: '',
                songId: '',
                type: 0
            }
        }
    },
    /**  对于computed，我们更多时候是拿来做复杂的运算。
     *   此处的话，模板的元素部分属性来源自vuex中的状态，所以
     *   用computed来进行间接获取。由于播放器状态变化后不涉及
     *   其他动作，所以区别于watch使用
     *   或者说，playbar的这些属性，是通过song-audio组件传过来的
     *   并不属于play-bar本身的属性
     */
    computed:{
        ...mapGetters([
            'playButtonUrl',
            'isPlay',
            'songPic',
            'songName',
            'singerName',
            'duration',
            'currentTime', // 当前歌曲的播放进度（比如总时长是300s，当前播放到200s）
            'listIndex', // 当前歌曲在歌单中的位置
            'listOfSongs',
            'songUrl',
            'songId',
            'songLyric',
            'autoNext',
            'isActive', //当前歌曲是否被收藏
            'loginIn',  //登录状态
            'userId',   //用户id
        ])
    },
    watch: {
        isPlay(){
            if(this.isPlay){
                this.$store.commit('setPlayButtonUrl','#icon-zanting');
            }else{
                this.$store.commit('setPlayButtonUrl','#icon-bofang');
            }
        },
        /**
         *  这里是play-bar运行的起始
         *  要理解，song.js记录了一首歌应该有的状态
         *  play-bar是根据歌曲当前的属性来直接或者间接转为自己需要的属性
         */
        currentTime(){
            this.playCurrentTime = this.formatSeconds(this.currentTime);
            this.songTime = this.formatSeconds(this.duration);
            // 所以最大长度就是100
            this.curLength = (this.currentTime/this.duration)*100;
            
        },
        musicVolume(){
            this.$store.commit('setVolume',this.musicVolume/100);
        },
        /**
         *  播放结束后自动下一首
         *  歌曲结束后，autoNext的值发生改变。
         *  监听到值改变后，就跳转到下一首
         */
        autoNext(){
            this.next();
        }
    },
    mounted(){
        this.progressLength = this.$refs.progress.getBoundingClientRect().width;
        document.querySelector('.item-volume').addEventListener('click',function(e){
            document.querySelector('.volume').classList.add('show-volume');
            e.stopPropagation();
        },false);
        document.querySelector('.volume').addEventListener('click',function(e){
            e.stopPropagation();
        },false);
        // 对所有点击事件监听，有的话就隐藏掉音量组件
        document.addEventListener('click',function(){
            document.querySelector('.volume').classList.remove('show-volume');
        },false)
    },
    methods:{
        togglePlay(){
            if(this.isPlay){
                this.$store.commit('setIsPlay',false);
            }else{
                this.$store.commit('setIsPlay',true);
            }
        },
        mousedown(e){
            this.tag = true; // 标志开始拖拽
            this.mouseStartX = e.clientX;
        },
        //拖拽结束
        mouseup(){
            this.tag = false;
        },
        /**
         * 鼠标移出进度条后，不再允许拖拽
         *  这里主要是避免点击进度点后在进度条外放开后
         *  进度监听事件不再执行而拖拽状态没有停止，会导致鼠标进去后一直保持拖拽状态
         */
        stopProgress(){
            this.tag=false;
        },
        // 调整歌曲进度
        editProgress(e){
            if(!this.duration){
                return false
            }
            //console.log(this.tag)
            if(this.tag){
                let movementX = e.clientX - this.mouseStartX; //拖拽的距离
                let curLength = this.$refs.curProgress.getBoundingClientRect().width; // 原来进度条的位置
                let newPercent = ((movementX + curLength) / this.progressLength) *100;
                if(newPercent>100){
                    newPercent = 100; //最多拖拽到歌曲结束
                }
                this.curLength = newPercent;
                this.mouseStartX = e.clientX;
                this.changeTime(newPercent);
            }
        },
        // 更改歌曲进度
        changeTime(percent){
            let newCurTime = (percent * 0.01) * this.duration;
            this.$store.commit('setChangeTime',newCurTime);
        },
        // 点击进度条调整位置
        updatemove(e){
            if(!this.tag){
                // 进度条的左侧坐标，一般是固定的
                let curLength = this.$refs.bg.offsetLeft;
                // e.clintX 为鼠标点击处在浏览器的X坐标
                let newPercent = ((e.clientX - curLength) / this.progressLength) * 100;
                if(newPercent > 100){
                    newPercent = 100;
                }else if(newPercent < 0){
                    newPercent = 100;
                }
                this.curLength = newPercent;
                this.changeTime(newPercent);
            }
        },
        /**
         *  该方法将秒数转换为 时分秒
         *  用于music-bar的属性参数使用
         */
        formatSeconds(seconds){
            let theTime = parseInt(seconds);
            let result = ''; // 返回值
            let hour = parseInt(seconds/3600);
            let min = parseInt((seconds / 60) % 60); // 这里使用 % 是因为只有余数才是 <60min的时间
            let second = parseInt(seconds % 60);
            if(hour > 0){
                if( hour < 10){
                    result = '0' + hour + ':'
                }else{
                    result = hour = ":"
                }
            }
            if(min > 0){
                if(min < 10){
                    result += '0' + min + ':';
                }else{
                    result += min + ':';
                }
            }else{
                result += '00:'
            }
            if(second > 0){
                if(second < 10){
                    result += '0' + second 
                }else{
                    result += second
                }
            }else{
                result += '00'
            }
            return result;
        },
        /**
         *  点击显示播放列表
         */
        showAside(){
            this.$store.commit('setShowAside',true);
        },
        /**
         *  播放上一首歌曲
         *  如果已经是第一首，则切换到歌单的最后一首
         */
        prev(){
            //歌单有歌曲的时候，点击才有效
            if(this.listOfSongs){
                // 歌单中歌曲数量大于一首时，才可以播放上一首
                if(this.listIndex != -1 && this.listOfSongs.length > 0){
                    if( this.listIndex > 0){
                        this.$store.commit('setListIndex',this.listIndex-1); 
                    }else{
                        this.$store.commit('setListIndex',this.listOfSongs.length-1);
                    }
                    // 将要播放的url传入
                    this.toPlay(this.listOfSongs[this.listIndex].songUrl);
                }
            }
        },
        /**
         *   播放下一首歌曲
         *   如果已经是最后一首，则调到第一首
         */
        next(){
            //歌单有歌曲的时候，点击才有效
            if(this.listOfSongs){
            // 歌单中歌曲数量大于一首时，才可以播放下一首
                if(this.listIndex != -1 && this.listOfSongs.length > 0){
                    if( this.listIndex < this.listOfSongs.length-1){
                        this.$store.commit('setListIndex',this.listIndex+1); 
                    }else{
                        this.$store.commit('setListIndex',0);
                    }
                    // 将要播放的url传入
                    this.toPlay(this.listOfSongs[this.listIndex].songUrl);
                }
            }
        },
        /**
         * 播放歌曲
         *  如果播放的歌曲是当前歌曲，则不重置播放进度
         */
        toPlay: function(url){
            if( url && url != this.songUrl){
                this.$store.commit('setSongId',this.listOfSongs[this.listIndex].id);
                this.$store.commit('setSongUrl',this.$store.state.configure.HOST + url);
                this.$store.commit('setSongPic',this.$store.state.configure.HOST + this.listOfSongs[this.listIndex].songPic);
                this.$store.commit('setSongLyric', this.parseLyric(this.listOfSongs[this.listIndex].songLyric));
                this.$store.commit('setSongName',this.getSongName(this.listOfSongs[this.listIndex].songName));
                this.$store.commit('setSingerName',this.getSingerName(this.listOfSongs[this.listIndex].songName));
                this.$store.commit('setIsActive',false);
                // 获取收藏状态
                if(this.loginIn){
                    getUserCollect(this.userId).then( res => {
                         if(res.code == 200 && res.data){
                             if(res.data.length<=0){
                                 return;
                             }
                            for(let item of res.data){
                                if(item.songId == this.songId){
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
            }
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
        toLyric(){
            this.$router.push({path: `/lyric`})
        },
        /**
         * 解析歌词
         * 如果歌词格式不正确，不进行转换
         * 如果歌词前面部分格式不正确，则只取正确部分的歌词
         * 
         *  返回结果格式为二维数组，前一位存储时间，后一位存储歌词
         */
        parseLyric(lyric){
            let lines = lyric.split('\n');
            let pattern =  /\[\d{2}:\d{2}.(\d{3}|\d{2})\]/g;
            let result = [];
            // 对于歌词格式不正确的歌词，直接返回
            if(!/\[. + \]/.test(lyric)){
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
                    if(value!=''){
                        result.push([parseInt(t[0],10)*60 + parseFloat(t[1]),value]);
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
         *  下载当前歌曲
         */
        downloadMusic(){
            // 当前存在播放歌曲，下载事件才有效
            if(this.songUrl){
                downloadMusic(this.songUrl).then(res =>{
                    if(res.status == 200){
                        let content = res.data;
                        let eleLink = document.createElement('a');
                        eleLink.download = `${this.singerName}-${this.songName}.mp3`;
                        eleLink.style.display = 'none';
                        let blob = new Blob([content]);
                        eleLink.href = URL.createObjectURL(blob);
                        // 把链接地址加到document中
                        document.body.appendChild(eleLink);
                        // 触发点击
                        eleLink.click();
                        document.body.removeChild(eleLink);
                    }else if(res.status==500 && res.statusText){
                        this.$message.error(res.statusText);
                    }else{
                        this.$message.error('下载失败');
                    }
                }).catch(err => {
                    console.log(err);
                })
            }
        },
        /**
         * 收藏或者取消收藏某首歌曲
         * TODO 暂时没有取消收藏功能
         */
        postCollect(){
            if(this.loginIn){
                this.collectForm.userId = this.userId;
                this.collectForm.songId = this.songId;
                var collectFormJson = JSON.stringify(this.collectForm);
                addCollect(collectFormJson).then(res => {
                    if(res.code == 200){
                        this.$store.commit('setIsActive',true);
                        this.$message.success('收藏成功');
                    }else if(res.code==500 && res.msg){
                        this.$message.error(res.msg);
                    }else{
                        this.$message.error(this.isActive?'取消收藏失败':'收藏失败');
                    }
                }).catch( err => {
                    console.log(err);
                })
            }else{
                this.$message.error('请先登录');
            }
        }
    }

}
</script>
<style  lang="scss" scoped>
@import '../assets/css/play-bar.scss';
</style>