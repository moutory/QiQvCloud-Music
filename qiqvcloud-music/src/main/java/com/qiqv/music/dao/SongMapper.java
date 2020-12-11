package com.qiqv.music.dao;

import com.qiqv.music.pojo.Song;
import com.qiqv.music.pojo.SongExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SongMapper {
    int countByExample(SongExample example);

    int deleteByExample(SongExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Song record);

    int insertSelective(Song record);

    List<Song> selectByExample(SongExample example);

    Song selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Song record, @Param("example") SongExample example);

    int updateByExample(@Param("record") Song record, @Param("example") SongExample example);

    int updateByPrimaryKeySelective(Song record);

    int updateByPrimaryKey(Song record);
    /**
     *  根据歌手名模糊查询歌手信息
     * @param singerName
     * @return
     */
    List<Song> querySongByName(@Param("songName")String singerName);

    /**
     * 根据歌手性别查询歌手信息
     * @param singerId
     * @return
     */
    List<Song> querySongBySingerId(@Param("singerId")Integer singerId);
}
