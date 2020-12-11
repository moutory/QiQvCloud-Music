package com.qiqv.music.dao;

import com.qiqv.music.pojo.SongList;
import com.qiqv.music.pojo.SongListExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SongListMapper {
    int countByExample(SongListExample example);

    int deleteByExample(SongListExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SongList record);

    int insertSelective(SongList record);

    List<SongList> selectByExample(SongListExample example);

    SongList selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SongList record, @Param("example") SongListExample example);

    int updateByExample(@Param("record") SongList record, @Param("example") SongListExample example);

    int updateByPrimaryKeySelective(SongList record);

    int updateByPrimaryKey(SongList record);
}
