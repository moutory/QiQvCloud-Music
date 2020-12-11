package com.qiqv.music.dao;

import com.qiqv.music.pojo.Consumer;
import com.qiqv.music.pojo.ConsumerExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConsumerMapper {
    int countByExample(ConsumerExample example);

    int deleteByExample(ConsumerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Consumer record);

    int insertSelective(Consumer record);

    List<Consumer> selectByExample(ConsumerExample example);

    Consumer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Consumer record, @Param("example") ConsumerExample example);

    int updateByExample(@Param("record") Consumer record, @Param("example") ConsumerExample example);

    int updateByPrimaryKeySelective(Consumer record);

    int updateByPrimaryKey(Consumer record);
}
