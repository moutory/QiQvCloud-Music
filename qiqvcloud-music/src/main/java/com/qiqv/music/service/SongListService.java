package com.qiqv.music.service;

import com.qiqv.music.pojo.SongList;

import java.util.List;

public interface SongListService {

    /**
     *  添加歌单
     */
    boolean addSongList(SongList songList);

    /**
     * 更新歌单信息
     * @param songList
     * @return
     */
    boolean updateSongList(SongList songList);

    /**
     * 删除歌单信息
     * @param id
     * @return
     */
    boolean deleteSongListById(Integer id);

    /**
     * 根据id主键查询歌单
     * @param id
     * @return
     */
    SongList querySongListById(Integer id);

    /**
     * 查询所有歌单
     * @return
     */
    List<SongList> getAllSongList();

    /**
     * 根据歌单名称模糊查询歌单信息
     * @param songListTitle
     * @return
     */
    List<SongList> likeSongListByTitle(String songListTitle);

    /**
     * 根据歌单标题精准查询歌单
     * @param songListTitle
     * @return
     */
    List<SongList> querySongListByTitle(String songListTitle);

    /**
     * 根据风格模糊查询歌单
     * @param style
     * @return
     */
    List<SongList> likeSongListByStyle(String style);
}
