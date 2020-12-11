package com.qiqv.music.service;

import com.qiqv.music.pojo.Song;

import java.util.List;

public interface SongService {
    /**
     *  添加歌曲
     */
    boolean addSong(Song Song);

    /**
     * 更新歌曲信息
     * @param Song
     * @return
     */
    boolean updateSong(Song Song);

    /**
     * 删除歌曲信息
     * @param SongId
     * @return
     */
    boolean deleteSongById(Integer SongId);

    /**
     * 根据id主键查询歌曲
     * @param id
     * @return
     */
    Song querySongById(Integer id);

    /**
     * 查询所有歌曲
     * @return
     */
    List<Song> getAllSong();

    /**
     * 根据歌曲名称查询歌曲信息
     * @param SongName
     * @return
     */
    List<Song> querySongByName(String SongName);

    /**
     * 根据歌手Id查询歌曲
     * @param singerId
     * @return
     */
    List<Song> querySongBySingerId(Integer singerId);

    /**
     * 根据歌曲名模糊查询所有歌曲
     * @param songName
     * @return
     */
    List<Song> getSongsLikeName(String songName);

}
