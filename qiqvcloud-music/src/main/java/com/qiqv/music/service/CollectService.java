package com.qiqv.music.service;

import com.qiqv.music.pojo.Collect;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectService {
        /**
         *增加
         */
        public boolean insert(Collect collect);

        /**
         * 删除
         */
        public boolean delete(Integer id);
        /**
         * 根据用户id和歌曲id删除用户收藏歌曲
         */
        boolean deleteCollectBySongIdAndUserId(Integer songId,Integer userId);
        /**
         * 查询所有收藏
         */
        public List<Collect> allCollect();

        /**
         * 查询某个用户的收藏列表
         */
        public List<Collect> getUserCollect(Integer userId);

        /**
         * 查询某个用户是否已经收藏了某个歌曲
         */
        public boolean isUserCollect(@Param("userId") Integer userId, @Param("songId") Integer songId);
}
