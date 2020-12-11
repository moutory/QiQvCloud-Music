// 对请求方法进一步进行封装
import {get,post} from './http';

export const getLoginStatus = (params)=>post(`/login/verifyUser`,params);

export const addSinger = (params) => post(`/singer/addSinger`,params);

export const getAllSinger = (params) => get(`/singer/getAllSingers`);

export const updateSinger = (params) => post(`/singer/updateSinger`,params);

export const delSinger = (params) => post(`/singer/delSinger`,params);

//========== 歌曲相关  =====================
export const getAllSongBySingerId = (singerId) => get(`/song/getAllSongBySingerId?singerId=${singerId}`);

export const addSong = (params) => post(`/song/addSong`,params);

export const updateSong = (params) => post(`/song/updateSong`,params);

export const delSong = (params) => post(`/song/delSong`,params);

export const getSongByName = (songName) => get(`/song/getSongByName?songName=${songName}`);

export const getAllSongById = (songId) => get(`/song/getAllSongById?songId=${songId}`);

export const getAllSong = () => get(`/song/getAllSong`);

// ============= 歌单相关 =============================
export const addSongList = (params) => post(`/songList/addSongList`,params);

export const delSongList = (params) => post(`/songList/delSongList`,params);

export const getAllSongList = () => get(`/songList/getAllSongList`);

export const updateSongList = (params) => post(`/songList/updateSongList`,params);

// =============操作歌单-歌曲关联相关===================
export const getAllSongOfList = (songListId) => get(`/listSong/getAllSongOfList?songListId=${songListId}`);

export const addListSong = (params) => post(`/listSong/addListSong`,params);

export const delListSong = (params) => post(`/listSong/delListSong`,params);

// ==================     用户相关      ==================================
export const getAllConsumer = () => get(`/consumer/getAllConsumer`);

export const addConsumer = (params) => post(`/consumer/addConsumer`,params);

export const updateConsumer = (params) => post(`/consumer/updateConsumer`,params);

export const delConsumer = (params) => post(`/consumer/delConsumer`,params);

export const queryUserById = (userId) => get(`/consumer/queryUserById?userId=${userId}`);


// ====================       收藏相关         =====================================
export const getUserCollect = (userId) => get(`/collect/getUserCollect?userId=${userId}`);

export const delUserCollect = (params) => post(`/collect/delUserCollect`,params);

// ====================       评论相关         =====================================
export const queryCommentBySongListId = (songListId) => get(`/comment/queryCommentBySongListId?songListId=${songListId}`);

export const delComment = (params) => post(`/comment/delComment`,params);
// 批量删除评论
export const batchDeleteSelComments = (params) => post(`/comment/batchDeleteSelComments`,params);
