package com.qiqv.music.service;

import com.qiqv.music.pojo.Singer;

import java.util.List;

public interface SingerService {

    /**
     *  添加歌手
     */
    boolean addSinger(Singer singer);

    /**
     * 更新歌手信息
     * @param singer
     * @return
     */
    boolean updateSinger(Singer singer);

    /**
     * 删除歌手信息
     * @param singerId
     * @return
     */
    boolean deleteSingerById(Integer singerId);

    /**
     * 根据id主键查询歌手
     * @param id
     * @return
     */
    Singer querySingerById(Integer id);

    /**
     * 查询所有歌手
     * @return
     */
    List<Singer> getAllSinger();

    /**
     * 根据歌手名称模糊查询歌手信息
     * @param singerName
     * @return
     */
    List<Singer> querySingerByName(String singerName);

    /**
     * 根据性别查询歌手信息
     * @param sex
     * @return
     */
    List<Singer> querySingerBySex(Integer sex);
}
