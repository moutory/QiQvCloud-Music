package com.qiqv.music.controller;

import com.qiqv.music.pojo.Song;
import com.qiqv.music.service.SongService;
import com.qiqv.music.utils.QiqvJSONResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    private SongService songService;

    @PostMapping("/addSong")
    public QiqvJSONResult addSong(Song song, @RequestParam("file") MultipartFile file){
        if(song==null || StringUtils.isBlank(song.getSongName())){
            return QiqvJSONResult.errorMsg("添加歌曲失败");
        }
        //将上传的歌曲保存到 /song 目录下
        if(null == file || file.isEmpty() ||StringUtils.isBlank(file.getOriginalFilename())){
            return QiqvJSONResult.errorMsg("歌曲无效，请重新上传");
        }
        String newFileName = System.currentTimeMillis() + file.getOriginalFilename();
        String songSavePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song" + System.getProperty("file.separator");
        File filePath = new File(songSavePath);
        if(!filePath.exists()){
            filePath.mkdirs();
        }
        File finalSongFile = new File(songSavePath + System.getProperty("file.separator") + newFileName);
        try {
            file.transferTo(finalSongFile);
            String relevantPath = System.getProperty("file.separator") + "song" + System.getProperty("file.separator") + newFileName;
            song.setSongUrl(relevantPath);
            song.setSongPic("/img/songPic/tubiao.jpg");
            song.setInsertAt(new Date());
            boolean addSuccess = songService.addSong(song);
            if(addSuccess){
                return QiqvJSONResult.ok();
            }else{
                return QiqvJSONResult.errorMsg("添加失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return QiqvJSONResult.errorMsg("添加失败");
    }

    @RequestMapping(path = "getAllSongBySingerId",method = RequestMethod.GET)
    public QiqvJSONResult getSongBySingerId(Integer singerId){
        if(null == singerId){
            return QiqvJSONResult.errorMsg("请选择歌手");
        }
        List<Song> songs = songService.querySongBySingerId(singerId);
        if(null != songs && !songs.isEmpty()){
            return QiqvJSONResult.ok(songs);
        }
        return QiqvJSONResult.ok();
    }

    @PostMapping("updateSongPic")
    public QiqvJSONResult updateSongPic(@RequestParam("file")MultipartFile file,@RequestParam("id") Integer songId){
        if(null == songId || file.isEmpty() || StringUtils.isBlank(file.getOriginalFilename())){
            return QiqvJSONResult.errorMsg("请选择要删除的图片");
        }
        String newFileName = System.currentTimeMillis() + file.getOriginalFilename();
        String songPicSavePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img" + System.getProperty("file.separator")
                + "songPic" + System.getProperty("file.separator");
        File songPicFile = new File(songPicSavePath);
        if(!songPicFile.exists()){
            songPicFile.mkdirs();
        }
        File finalSongPic = new File(songPicSavePath + newFileName);
        try {
            file.transferTo(finalSongPic);
            String relevantPath = "/img/songPic/" + newFileName;
            Song song = new Song();
            song.setId(songId);
            song.setSongPic(relevantPath);
            song.setUpdateAt(new Date());
            boolean updateSuccess = songService.updateSong(song);
            if(updateSuccess){
                return QiqvJSONResult.ok();
            }else{
                return QiqvJSONResult.errorMsg("更新失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return QiqvJSONResult.errorMsg("更新失败");
    }

    /**
     * 更新歌曲
     * @param file
     * @param songId
     * @return
     */
    @PostMapping("updateSongUrl")
    public QiqvJSONResult updateSongUrl(@RequestParam("file")MultipartFile file,@RequestParam("id") Integer songId){
        if(null == songId || file.isEmpty() || StringUtils.isBlank(file.getOriginalFilename())){
            return QiqvJSONResult.errorMsg("请选择要删除的图片");
        }
        String newFileName = System.currentTimeMillis() + file.getOriginalFilename();
        String songSavePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song" + System.getProperty("file.separator");
        File songFile = new File(songSavePath);
        if(!songFile.exists()){
            songFile.mkdirs();
        }
        File finalsong = new File(songSavePath + newFileName);
        try {
            file.transferTo(finalsong);
            String relevantPath = "/song/" + newFileName;
            Song song = new Song();
            song.setId(songId);
            song.setSongUrl(relevantPath);
            song.setUpdateAt(new Date());
            boolean updateSuccess = songService.updateSong(song);
            if(updateSuccess){
                return QiqvJSONResult.ok();
            }else{
                return QiqvJSONResult.errorMsg("更新失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return QiqvJSONResult.errorMsg("更新失败");
    }

    @PostMapping("updateSong")
    public QiqvJSONResult updateSong(@RequestBody Song song){
        if(null == song || song.getId()==null){
            return QiqvJSONResult.errorMsg("更新失败");
        }
        boolean updateSuccess = songService.updateSong(song);
        if(updateSuccess){
            return QiqvJSONResult.ok();
        }else{
            return QiqvJSONResult.errorMsg("更新失败");
        }
    }

    @PostMapping("delSong")
    public QiqvJSONResult delSong(@RequestBody Song song){
        if(null == song || song.getId()==null){
            return QiqvJSONResult.errorMsg("更新失败");
        }
        boolean delSuccess = songService.deleteSongById(song.getId());
        if(delSuccess){
            return QiqvJSONResult.ok();
        }else{
            return QiqvJSONResult.errorMsg("删除失败");
        }
    }

    @RequestMapping(path = "getSongByName",method = RequestMethod.GET)
    public QiqvJSONResult getSongByName(String songName){
        if(StringUtils.isBlank(songName)){
            return QiqvJSONResult.errorMsg("请提供歌曲名称");
        }
        List<Song> songs = songService.querySongByName(songName);
        if(songs.isEmpty()){
            return QiqvJSONResult.errorMsg("查询不到该歌曲");
        }
        return QiqvJSONResult.ok(songs);
    }


    @RequestMapping(method = RequestMethod.GET,path ="getAllSongById" )
    public QiqvJSONResult getAllSongById(Integer songId){
        if(null == songId){
            return QiqvJSONResult.errorMsg("请传入歌曲Id");
        }
        Song song = songService.querySongById(songId);
        if(null != song){
            return QiqvJSONResult.ok(song);
        }
        return QiqvJSONResult.errorMsg("查询不到歌曲，歌曲id:"+songId);
    }

    /**
     * 查询所有歌曲
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,path ="getAllSong" )
    public QiqvJSONResult getAllSong(){
        List<Song> songs = songService.getAllSong();
        if(null != songs ){
            return QiqvJSONResult.ok(songs);
        }
        return QiqvJSONResult.errorMsg("查询歌曲失败");
    }

    /**
     * 根据歌名模糊查询歌曲
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,path ="/getSongsLikeName" )
    public QiqvJSONResult getSongsLikeName(String songName){
        if(StringUtils.isBlank(songName)){
            return QiqvJSONResult.errorMsg("请输入关键字");
        }
        List<Song> songs = songService.getSongsLikeName(songName);
        if(null != songs){
            return QiqvJSONResult.ok(songs);
        }
        return QiqvJSONResult.errorMsg("查询歌曲失败");
    }







}
