package com.qiqv.music.service;

import com.qiqv.music.pojo.ListSong;

import java.util.List;

/**
 *  区别于SongList歌单，ListSong表示歌单中的歌曲
 *  歌曲有两种归类方式，按歌手区分，按歌单区分
 */
public interface ListSongService {

    /**
     *  添加歌曲进入歌单
     */
    boolean addListSong(ListSong listSong);

    /**
     * 更新歌单中的歌曲信息
     * @param listSong
     * @return
     */
    boolean updateListSong(ListSong listSong);

    /**
     * 删除歌单中的歌曲信息
     * @param listSongId
     * @return
     */
    boolean deleteListSongById(Integer listSongId);

    /**
     * 根据id主键查询歌曲
     * @param listSongId
     * @return
     */
    ListSong queryListSongById(Integer listSongId);

    /**
     * 查询歌单里面所有歌曲
     * @return
     */
    List<ListSong> getAllSong();

    /**
     * 根据歌曲id和歌单id删除关联关系
     * @param songId,songListId
     * @return
     */
    boolean delBySongIdAndSongListId(Integer songId,Integer songListId);

    /**
     * 根据歌单id查询歌单的所有歌曲id
     * @param songListId
     * @return
     */
    List<ListSong> querySongBySongListId(Integer songListId);



}
