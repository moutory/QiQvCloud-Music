package com.qiqv.music.service;

import com.qiqv.music.pojo.Rank;

public interface RankService {

    /**
     * 用户添加对歌单的评价
     * @param rank
     * @return
     */
    boolean rateSongList(Rank rank);

    /**
     * 根据歌单id查询歌单的平均分
     * @param songListId
     * @return
     */
    Integer getAverageRankBySongListId(Integer songListId);
}
