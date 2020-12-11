// 对请求方法进一步进行封装
import {get,post} from './http';
import Axios from 'axios';

export const getLoginStatus = (params)=>post(`/login/verifyUser`,params);

//========== 歌曲相关  =====================
export const getAllSongBySingerId = (singerId) => get(`/song/getAllSongBySingerId?singerId=${singerId}`);

export const addSong = (params) => post(`/song/addSong`,params);

export const updateSong = (params) => post(`/song/updateSong`,params);

export const delSong = (params) => post(`/song/delSong`,params);

export const getSongByName = (songName) => get(`/song/getSongByName?songName=${songName}`);

export const getAllSongById = (songId) => get(`/song/getAllSongById?songId=${songId}`);

export const getAllSong = () => get(`/song/getAllSong`);

export const getSongsLikeName = (keywords) => get(`/song/getSongsLikeName?songName=${keywords}`);

// 下载音乐
export const downloadMusic = (url) => Axios({
    method: 'get',
    url: url,
    responseType: 'blob'
})
// ============= 歌单相关 =============================
export const addSongList = (params) => post(`/songList/addSongList`,params);

export const delSongList = (params) => post(`/songList/delSongList`,params);

export const getAllSongList = () => get(`/songList/getAllSongList`);

export const updateSongList = (params) => post(`/songList/updateSongList`,params);

export const getSongListLikeTitle = (keywords) => get(`/songList/getSongListLikeTitle?keywords=${keywords}`);

export const getSongListLkeStyle = (styleName) => get(`/songList/getSongListLkeStyle?styleName=${styleName}`);
// =============操作歌单-歌曲关联相关===================
export const getAllSongOfList = (songListId) => get(`/listSong/getAllSongOfList?songListId=${songListId}`);

export const addListSong = (params) => post(`/listSong/addListSong`,params);

export const delListSong = (params) => post(`/listSong/delListSong`,params);

// ==================     用户相关      ==================================
export const getAllConsumer = () => get(`/consumer/getAllConsumer`);

export const addConsumer = (params) => post(`/consumer/addConsumer`,params);

export const login = (params) => post(`/consumer/login`,params);

export const queryUserById = (userId) => get(`/consumer/queryUserById?userId=${userId}`);
// 更新用户信息
export const updateConsumer = (params) => post(`/consumer/updateConsumer`,params);

//===================   歌手相关   ======================================

export const getAllSinger = () => get(`/singer/getAllSingers`);

export const getSingerBySexClass = (sex) => get(`/singer/getSingerBySexClass?sex=${sex}`);
//===================   评价相关   ======================================
// 查询指定歌单的平均评分
export const getAverageRank = (songListId) => get(`/rank/getAverageRank?songListId=${songListId}`);
// 用于对指定歌单评分
export const rateSongList = (params) => post(`/rank/rateSongList`,params);

//===================   评论相关   ======================================
export const saveComment = (params) => post(`/comment/saveComment`,params);
//点赞评论
export const setLikeComment = (params) => post(`/comment/likeComment`,params);

export const getAllComment = (playId,type) => {
    if(type==0){
        return get(`/comment/queryCommentBySongId?songId=${playId}`)
    }else{
        return get(`/comment/queryCommentBySongListId?songListId=${playId}`)
    }
}
//====================  收藏相关  =========================
export const addCollect = (params) => post(`/collect/addCollect`,params);
// 获取歌手收藏列表
export const getUserCollect = (userId) => get(`/collect/getUserCollect?userId=${userId}`);



