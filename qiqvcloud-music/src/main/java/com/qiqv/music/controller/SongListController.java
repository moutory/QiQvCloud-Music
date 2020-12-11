package com.qiqv.music.controller;

import com.qiqv.music.pojo.SongList;
import com.qiqv.music.service.SongListService;
import com.qiqv.music.utils.QiqvJSONResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songList")
public class SongListController {

    @Autowired
    private SongListService songListService;

    /**
     * 添加歌单
     * @return
     */
    @PostMapping("/addSongList")
    public QiqvJSONResult addSongList(@RequestBody SongList songList){
        if(null == songList || StringUtils.isBlank(songList.getSonglistTitle())){
            return QiqvJSONResult.errorMsg("添加歌单失败");
        }
        if(StringUtils.isBlank(songList.getSonglistPic())){
            songList.setSonglistPic("/img/songListPic/123.jpg");
        }
        boolean addSuccess = songListService.addSongList(songList);
        if(addSuccess){
            return QiqvJSONResult.ok();
        }
        return QiqvJSONResult.errorMsg("添加歌单失败");
    }

    /**
     * 删除歌单
     */
    @PostMapping("/delSongList")
    public QiqvJSONResult delSongList(@RequestBody SongList songList){
        if(null == songList || songList.getId()==null){
            return QiqvJSONResult.errorMsg("删除歌单失败");
        }
        boolean delSuccess = songListService.deleteSongListById(songList.getId());
        if(delSuccess){
            return QiqvJSONResult.ok();
        }
        return QiqvJSONResult.errorMsg("删除歌单失败");
    }


    /**
     * 更新歌单
     * @return
     */
    @PostMapping("/updateSongList")
    public QiqvJSONResult updateSongList(@RequestBody SongList songList){
        if(null == songList || songList.getId()==null){
            return QiqvJSONResult.errorMsg("更新歌单失败");
        }
        boolean updateSuccess = songListService.updateSongList(songList);
        if(updateSuccess){
            return QiqvJSONResult.ok();
        }
        return QiqvJSONResult.errorMsg("更新歌单失败");
    }

    /**
     *  查询所有歌单
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,path = "/getAllSongList")
    public QiqvJSONResult getAllSongList (){
        List<SongList> allSongList = songListService.getAllSongList();
        return QiqvJSONResult.ok(allSongList);
    }

    /**
     *  根据歌单名模糊查询所有歌单
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,path = "/getSongListLikeTitle")
    public QiqvJSONResult getSongListLikeTitle (String keywords){
        List<SongList> allSongList = songListService.likeSongListByTitle(keywords);
        return QiqvJSONResult.ok(allSongList);
    }

    /**
     *  根据风格模糊查询所有歌单
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,path = "/getSongListLkeStyle")
    public QiqvJSONResult getSongListLkeStyle (String styleName){
        List<SongList> allSongList = songListService.likeSongListByStyle(styleName);
        return QiqvJSONResult.ok(allSongList);
    }


}
