package com.qiqv.music.service.impl;

import com.qiqv.music.dao.SingerMapper;
import com.qiqv.music.pojo.Singer;
import com.qiqv.music.pojo.SingerExample;
import com.qiqv.music.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SingerServiceImpl implements SingerService {

    @Autowired
    private SingerMapper singerMapper;
    /*
    *   对于写操作，事务的传播属性为required
    *   对于读操作，事务的传播属性为support
    * */

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean addSinger(Singer singer) {
        return singerMapper.insertSelective(singer)>0;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean updateSinger(Singer singer) {
        return singerMapper.updateByPrimaryKeySelective(singer)>0;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean deleteSingerById(Integer singerId) {
        return singerMapper.deleteByPrimaryKey(singerId)>0;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Singer querySingerById(Integer singerId) {
        return singerMapper.selectByPrimaryKey(singerId);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Singer> getAllSinger() {
        SingerExample singerExample = new SingerExample();
        return singerMapper.selectByExample(singerExample);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Singer> querySingerByName(String singerName) {
        return singerMapper.querySingerByName(singerName);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Singer> querySingerBySex(Integer sex) {
        return singerMapper.querySingerBySex(sex);
    }
}
