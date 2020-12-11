<template>
    <div>
        <el-row :gutter="20" class="mgb20">
            <el-col :span="6">
                <el-card>
                    <div class="grid-content">
                    <div class="grid-cont-center">
                        <div class="grid-num">{{consumerCount}}</div>
                        <div>用户总数</div>
                    </div>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="6">
                <el-card>
                    <div class="grid-content">
                    <div class="grid-cont-center">
                        <div class="grid-num">{{songCount}}</div>
                        <div>歌曲总数</div>
                    </div>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="6">
                <el-card>
                    <div class="grid-content">
                    <div class="grid-cont-center">
                        <div class="grid-num">{{singerCount}}</div>
                        <div>歌手总数</div>
                    </div>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="6">
                <el-card>
                    <div class="grid-content">
                    <div class="grid-cont-center">
                        <div class="grid-num">{{songListCount}}</div>
                        <div>歌单总数</div>
                    </div>
                    </div>
                </el-card>
            </el-col>
        </el-row>
        <el-row :gutter="20" class="mgb20">
            <el-col :span="12">
                <h3 class="mgb20">用户性别比例</h3>
                <div style="background-color:white">
                    <ve-pie :data="consumerSex" :theme="options"></ve-pie>
                </div>
            </el-col>
            <el-col :span="12">
                <h3 class="mgb20">歌单类型分布</h3>
                <div style="background-color:white">
                    <ve-histogram :data="songListStyle" :theme="options"></ve-histogram>
                </div>
            </el-col>
        </el-row>
        <el-row :gutter="20" class="mgb20">
            <el-col :span="12">
                <h3 class="mgb20">歌手性别比例</h3>
                <div style="background-color:white">
                    <ve-pie :data="singerSex" ></ve-pie>
                </div>
            </el-col>
            <el-col :span="12">
                <h3 class="mgb20">歌单类型分布</h3>
                <div style="background-color:white">
                    <ve-histogram :data="singerLocaltion" :theme="options"></ve-histogram>
                </div>
            </el-col>
        </el-row>
    </div>
</template>

<script>
import {getAllConsumer,getAllSinger,getAllSong,getAllSongList} from '../api/index'
export default {
    data(){
        return{
            consumerCount: 0, // 用户总数
            songCount: 0,    // 歌曲总数
            singerCount: 0,       // 歌手总数
            songListCount: 0, // 歌单总数
            consumerSex:{           //按性别分类的用户数
                columns: ['性别','总数'],
                rows: [
                    {'性别': '男','总数': 0},
                    {'性别': '女','总数': 0}
                ]
            },
            songListStyle:{
                columns: ['风格','总数'],
                rows: [
                    {'风格':'舒缓','总数':0},
                    {'风格':'躁动','总数':0},
                    {'风格':'激昂','总数':0},
                    {'风格':'安静','总数':0},
                    {'风格':'情歌','总数':0},
                    {'风格':'伤心','总数':0}
                ]
            },
            singerSex: {
                columns: ['性别','总数'],
                rows: [
                    {'性别':'男','总数':0},
                    {'性别':'女','总数':0}
                ]
            },
            singerLocaltion: {
                columns: ['地区','总数'],
                rows: [
                    {'地区':'中国','总数': 0},
                    {'地区':'欧美','总数': 0},
                    {'地区':'东南亚','总数': 0},
                    {'地区':'日韩','总数': 0},
                    {'地区':'俄罗斯','总数': 0},
                    {'地区':'其他','总数': 0}
                ]
            },
            options: {
                color: ['#87cefa','#ffc0cb']
            }
        }
    },
    mounted(){
        this.getConsumer();
        this.getSong();
        this.getSinger();
        this.getSongList();
    },
    methods:{
        getConsumer(){
            var that = this;
            getAllConsumer().then(res =>{
                var consumer = res.data;
                if(consumer){
                    that.consumerCount = consumer.length;
                    that.consumerSex.rows[0]['总数'] = this.getSex(0,consumer); // 查询男性的用户数
                    that.consumerSex.rows[1]['总数'] = this.getSex(1,consumer); // 查询女性的用户数
                }else{
                    this.$message.error('查询用户相关参数失败');
                }
            })
        },
        getSong(){
            var that = this;
            getAllSong().then(res => {
                var song = res.data;
                if(song){
                    that.songCount = song.length;
                }else{
                    this.$message.error('查询歌曲相关参数失败');
                }
            })
        },
        getSinger(){
            var that = this;
            getAllSinger().then(res => {
                var singer = res.data;
                if(singer){
                    that.singerCount = singer.length;
                    that.singerSex.rows[0]['总数'] = that.getSingerSex(0,singer);
                    that.singerSex.rows[1]['总数'] = that.getSingerSex(1,singer);
                    that.singerLocaltion.rows[0]['总数'] = that.getSingerLocation('中国',singer);
                    that.singerLocaltion.rows[1]['总数'] = that.getSingerLocation('欧美',singer);
                    that.singerLocaltion.rows[2]['总数'] = that.getSingerLocation('东南亚',singer);
                    that.singerLocaltion.rows[3]['总数'] = that.getSingerLocation('日韩',singer);
                    that.singerLocaltion.rows[4]['总数'] = that.getSingerLocation('俄罗斯',singer);
                    that.singerLocaltion.rows[5]['总数'] = that.getSingerLocation('其他',singer);
                }else{
                    this.$message.error('查询歌手相关参数失败');
                }
            })
        },
        getSongList(){
            var that = this;
            getAllSongList().then(res => {
                var songList = res.data;
                if(songList){
                    that.songListCount = songList.length;
                    that.songListStyle.rows[0]['总数'] = this.getStyle('舒缓',songList);
                    that.songListStyle.rows[1]['总数'] = this.getStyle('躁动',songList);
                    that.songListStyle.rows[2]['总数'] = this.getStyle('激昂',songList);
                    that.songListStyle.rows[3]['总数'] = this.getStyle('安静',songList);
                    that.songListStyle.rows[4]['总数'] = this.getStyle('情歌',songList);
                    that.songListStyle.rows[5]['总数'] = this.getStyle('伤心',songList);
                    
                }else{
                    this.$message.error('查询歌单相关参数失败');
                }
            })
        },
        /**
         * 传入性别和用户集合
         * 返回用户集合中满足性别要求的个数
         */
        getSex(sex,val){
            let count = 0;
            for(let item of val){
                if(sex == item.sex){
                    count++;
                }
            }
            return count;
        },
        /**
         * 传入风格和歌单集合
         * 返回满足条件的歌单数量
         */
        getStyle(style,val){
            let count = 0;
            for(let item of val){
                if(style == item.songlistStyle){
                    count++;
                }
            }
            return count;
        },
         /**
         * 传入性别和歌手集合
         * 返回满足条件的歌手数量
         */
        getSingerSex(singerSex,val){
            let count = 0;
            for(let item of val){
                if(singerSex == item.singerSex){
                    count++;
                }
            }
            return count;
        },
         /**
         * 传入歌手地区和歌手集合
         * 返回满足条件的歌手数量
         */
        getSingerLocation(singerLocaltion,val){
            let count = 0;
            for(let item of val){
                if(singerLocaltion == item.singerLocaltion){
                    count++;
                }
            }
            return count;
        }
        
    }
}
</script>

<style scoped>
.grid-content{
    display: flex;
    align-items: center;
    height: 50px;
}
.grid-cont-center{
    flex: 1;
    text-align: center;
    font-size: 14px;
    color: darkgray;
}
.grid-num{
    font-size: 30px;
    font-weight: bold;
}
</style>