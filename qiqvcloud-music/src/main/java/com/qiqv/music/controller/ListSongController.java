package com.qiqv.music.controller;

import com.qiqv.music.pojo.ListSong;
import com.qiqv.music.service.ListSongService;
import com.qiqv.music.utils.QiqvJSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 这个类用户处理歌单和歌曲关联关系
 */
@RestController
@RequestMapping("/listSong")
public class ListSongController {

    @Autowired
    private ListSongService listSongService;

    @RequestMapping(path = "/getAllSongOfList",method = RequestMethod.GET)
    public QiqvJSONResult getAllSongOfList(Integer songListId){
        if(null == songListId){
            return QiqvJSONResult.errorMsg("请选择歌单");
        }
        List<ListSong> listSongs = listSongService.querySongBySongListId(songListId);
        if(null == listSongs || listSongs.isEmpty()){
            return QiqvJSONResult.errorMsg("该歌单尚未有歌曲");
        }
        return QiqvJSONResult.ok(listSongs);
    }

    @PostMapping("/addListSong")
    public QiqvJSONResult addListSong(@RequestBody ListSong listSong){
        if(null == listSong.getSongId() || null == listSong.getSongListId()){
            return QiqvJSONResult.errorMsg("查不到该歌曲");
        }
        boolean addSuccess = listSongService.addListSong(listSong);
        if(addSuccess){
            return QiqvJSONResult.ok();
        }
        return QiqvJSONResult.errorMsg("添加失败");
    }

    @PostMapping("delListSong")
    public QiqvJSONResult delListSong(@RequestBody ListSong listSong){
        if(null == listSong.getSongId() || null == listSong.getSongListId()){
            return QiqvJSONResult.errorMsg("缺少歌曲Id或者歌单Id");
        }
        boolean delSuccess = listSongService.delBySongIdAndSongListId(listSong.getSongId(), listSong.getSongListId());
        if(delSuccess){
            return QiqvJSONResult.ok();
        }
        return QiqvJSONResult.errorMsg("删除失败");
    }



}
