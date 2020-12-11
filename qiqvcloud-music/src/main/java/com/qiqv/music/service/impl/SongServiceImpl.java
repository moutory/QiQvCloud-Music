package com.qiqv.music.service.impl;

import com.qiqv.music.dao.SongMapper;
import com.qiqv.music.pojo.Song;
import com.qiqv.music.pojo.SongExample;
import com.qiqv.music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongMapper songMapper;

    @Override
    public boolean addSong(Song song) {
        return songMapper.insertSelective(song)>0;
    }

    @Override
    public boolean updateSong(Song song) {
        return songMapper.updateByPrimaryKeySelective(song)>0;
    }

    @Override
    public boolean deleteSongById(Integer songId) {
        return songMapper.deleteByPrimaryKey(songId)>0;
    }

    @Override
    public Song querySongById(Integer songId) {
        return songMapper.selectByPrimaryKey(songId);
    }

    @Override
    public List<Song> getAllSong() {
        SongExample songExample = new SongExample();
        return songMapper.selectByExample(songExample);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Song> querySongByName(String songName) {
        SongExample songExample = new SongExample();
        SongExample.Criteria criteria = songExample.createCriteria();
        criteria.andSongNameEqualTo(songName);
        return songMapper.selectByExample(songExample);
    }

    @Override
    public List<Song> querySongBySingerId(Integer singerId) {
        List<Song> songs = songMapper.querySongBySingerId(singerId);
        if(null!=songs && !songs.isEmpty()){
            return songs;
        }
        return null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Song> getSongsLikeName(String songName) {
        SongExample songExample = new SongExample();
        SongExample.Criteria criteria = songExample.createCriteria();
        criteria.andSongNameLike("%" + songName + "%");
        List<Song> songs = songMapper.selectByExample(songExample);
        return songs;
    }
}
