package com.qiqv.music.dao;

import com.qiqv.music.pojo.ListSong;
import com.qiqv.music.pojo.ListSongExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ListSongMapper {
    int countByExample(ListSongExample example);

    int deleteByExample(ListSongExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ListSong record);

    int insertSelective(ListSong record);

    List<ListSong> selectByExample(ListSongExample example);

    ListSong selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ListSong record, @Param("example") ListSongExample example);

    int updateByExample(@Param("record") ListSong record, @Param("example") ListSongExample example);

    int updateByPrimaryKeySelective(ListSong record);

    int updateByPrimaryKey(ListSong record);
}
