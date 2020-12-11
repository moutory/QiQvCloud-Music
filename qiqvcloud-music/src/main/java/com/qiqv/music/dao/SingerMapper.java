package com.qiqv.music.dao;

import com.qiqv.music.pojo.Singer;
import com.qiqv.music.pojo.SingerExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SingerMapper {
    int countByExample(SingerExample example);

    int deleteByExample(SingerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Singer record);

    int insertSelective(Singer record);

    List<Singer> selectByExample(SingerExample example);

    Singer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Singer record, @Param("example") SingerExample example);

    int updateByExample(@Param("record") Singer record, @Param("example") SingerExample example);

    int updateByPrimaryKeySelective(Singer record);

    int updateByPrimaryKey(Singer record);

    /**
     *  根据歌手名模糊查询歌手信息
     * @param singerName
     * @return
     */
    List<Singer> querySingerByName(@Param("singerName")String singerName);

    /**
     * 根据歌手性别查询歌手信息
     * @param sex
     * @return
     */
    List<Singer> querySingerBySex(@Param("sex")Integer sex);
}
