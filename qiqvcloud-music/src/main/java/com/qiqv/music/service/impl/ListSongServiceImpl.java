package com.qiqv.music.service.impl;

import com.qiqv.music.dao.ListSongMapper;
import com.qiqv.music.pojo.ListSong;
import com.qiqv.music.pojo.ListSongExample;
import com.qiqv.music.service.ListSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ListSongServiceImpl implements ListSongService {

    @Autowired
    private ListSongMapper listSongMapper;


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean addListSong(ListSong listSong) {
        return listSongMapper.insertSelective(listSong)>0;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean updateListSong(ListSong listSong) {
        return listSongMapper.updateByPrimaryKeySelective(listSong)>0;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean deleteListSongById(Integer listSongId) {
        return listSongMapper.deleteByPrimaryKey(listSongId)>0;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ListSong queryListSongById(Integer listSongId) {
        return listSongMapper.selectByPrimaryKey(listSongId);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ListSong> getAllSong() {
        ListSongExample listSongExample = new ListSongExample();
        return listSongMapper.selectByExample(listSongExample);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean delBySongIdAndSongListId(Integer songId, Integer songListId) {
        ListSongExample listSongExample = new ListSongExample();
        ListSongExample.Criteria criteria = listSongExample.createCriteria();
        criteria.andSongIdEqualTo(songId);
        criteria.andSongListIdEqualTo(songListId);
        return listSongMapper.deleteByExample(listSongExample)>0;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ListSong> querySongBySongListId(Integer songListId) {
        ListSongExample listSongExample = new ListSongExample();
        ListSongExample.Criteria criteria = listSongExample.createCriteria();
        criteria.andSongListIdEqualTo(songListId);
        return listSongMapper.selectByExample(listSongExample);
    }
}
