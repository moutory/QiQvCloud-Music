package com.qiqv.music.service.impl;

import com.qiqv.music.dao.RankMapper;
import com.qiqv.music.pojo.Rank;
import com.qiqv.music.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RankServiceImpl implements RankService {

    @Autowired
    private RankMapper rankMapper;



    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean rateSongList(Rank rank) {
        return rankMapper.insertSelective(rank)>0;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Integer getAverageRankBySongListId(Integer songListId) {
        Integer avgRank = rankMapper.getAverageRankBySongListId(songListId);
        //如果还没有评分过，就显示5分。满分10分
        if(avgRank==null){
            return 5;
        }
        return avgRank;
    }
}
