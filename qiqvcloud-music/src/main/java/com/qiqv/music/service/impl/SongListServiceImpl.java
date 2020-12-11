package com.qiqv.music.service.impl;

import com.qiqv.music.dao.SongListMapper;
import com.qiqv.music.pojo.SongList;
import com.qiqv.music.pojo.SongListExample;
import com.qiqv.music.service.SongListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongListServiceImpl implements SongListService {

    @Autowired
    private SongListMapper songListMapper;

    @Override
    public boolean addSongList(SongList songList) {
        return songListMapper.insertSelective(songList)>0;
    }

    @Override
    public boolean updateSongList(SongList songList) {
        return songListMapper.updateByPrimaryKeySelective(songList)>0;
    }

    @Override
    public boolean deleteSongListById(Integer id) {
        return songListMapper.deleteByPrimaryKey(id)>0;
    }

    @Override
    public SongList querySongListById(Integer id) {
        return songListMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SongList> getAllSongList() {
        SongListExample songListExample = new SongListExample();
        return songListMapper.selectByExample(songListExample);
    }

    @Override
    public List<SongList> querySongListByTitle(String songListTitle) {
        SongListExample songListExample = new SongListExample();
        SongListExample.Criteria criteria = songListExample.createCriteria();
        criteria.andSonglistTitleEqualTo(songListTitle);
        return songListMapper.selectByExample(songListExample);
    }

    @Override
    public List<SongList> likeSongListByTitle(String songListTitle) {
        SongListExample songListExample = new SongListExample();
        SongListExample.Criteria criteria = songListExample.createCriteria();
        criteria.andSonglistTitleLike("%" + songListTitle + "%");
        return songListMapper.selectByExample(songListExample);
    }

    @Override
    public List<SongList> likeSongListByStyle(String style) {
        SongListExample songListExample = new SongListExample();
        SongListExample.Criteria criteria = songListExample.createCriteria();
        criteria.andSonglistStyleLike("%" + style + "%");
        return songListMapper.selectByExample(songListExample);
    }
}
