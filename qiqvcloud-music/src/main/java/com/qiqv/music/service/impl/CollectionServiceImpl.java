package com.qiqv.music.service.impl;

import com.qiqv.music.dao.CollectMapper;
import com.qiqv.music.pojo.Collect;
import com.qiqv.music.pojo.CollectExample;
import com.qiqv.music.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class CollectionServiceImpl implements CollectService {

    @Autowired
    private CollectMapper collectMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean insert(Collect collect) {
        return collectMapper.insertSelective(collect)>0;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean delete(Integer collectId) {
        return collectMapper.deleteByPrimaryKey(collectId)>0;
    }

    @Override
    public boolean deleteCollectBySongIdAndUserId(Integer songId, Integer userId) {
        CollectExample collectExample = new CollectExample();
        CollectExample.Criteria criteria = collectExample.createCriteria();
        criteria.andSongIdEqualTo(songId);
        criteria.andUserIdEqualTo(userId);
        return collectMapper.deleteByExample(collectExample)>0;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Collect> allCollect() {
        CollectExample collectExample = new CollectExample();
        return collectMapper.selectByExample(collectExample);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Collect> getUserCollect(Integer userId) {
        CollectExample collectExample = new CollectExample();
        CollectExample.Criteria criteria = collectExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        return collectMapper.selectByExample(collectExample);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean isUserCollect(Integer userId, Integer songId) {
        CollectExample collectExample = new CollectExample();
        CollectExample.Criteria criteria = collectExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andSongIdEqualTo(songId);
        return !collectMapper.selectByExample(collectExample).isEmpty();
    }
}
