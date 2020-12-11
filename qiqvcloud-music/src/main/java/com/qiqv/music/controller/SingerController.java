package com.qiqv.music.controller;

import com.qiqv.music.pojo.Singer;
import com.qiqv.music.service.SingerService;
import com.qiqv.music.utils.QiqvJSONResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/singer")
public class SingerController {

    @Autowired
    private SingerService singerService;

    @PostMapping("/addSinger")
    public QiqvJSONResult addSinger(@RequestBody Singer singer){
        boolean isSuccess = singerService.addSinger(singer);
        if(isSuccess){
            return QiqvJSONResult.ok();
        }else{
            return QiqvJSONResult.errorMsg("添加失败");
        }
    }

    @RequestMapping(method = RequestMethod.GET,path = "/getAllSingers")
    public QiqvJSONResult getAllSingers(){
        List<Singer> singers = singerService.getAllSinger();
        if(singers!=null && singers.size()>0){
            return QiqvJSONResult.ok(singers);
        }else{
            return QiqvJSONResult.errorMsg("获取歌手信息失败...");
        }
    }

    /**
     * 更新歌手头像
     * 1、根据图片大小判断上传的图片是否有效
     * 2、根据当前时间毫秒值+图片名称
     * 3、将头像上传到项目img目录
     * 3、生成新的头像路径持久化到数据库
     * @return
     */
    @PostMapping("updateSingerPic")
    public QiqvJSONResult updateSingerPic(@RequestParam("file") MultipartFile singerPic,@RequestParam("id")int id){
        if(singerPic == null || singerPic.isEmpty() || StringUtils.isBlank(singerPic.getOriginalFilename())){
            return QiqvJSONResult.errorMsg("图片无效，上传失败");
        }
        String newFileName = System.currentTimeMillis() + singerPic.getOriginalFilename();
        String fileSavePath =  System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                + System.getProperty("file.separator") + "singerPic";
        File filePath = new File(fileSavePath);
        if(!filePath.exists()){
            filePath.mkdirs();
        }
        File finalPicFile = new File(fileSavePath + System.getProperty("file.separator") + newFileName);
        try {
            singerPic.transferTo(finalPicFile);
            String relevantPicPath = "/img/singerPic/" + newFileName;
            Singer singer = new Singer();
            //TODO 对id进行校验
            singer.setId(id);
            singer.setSingerPic(relevantPicPath);
            singerService.updateSinger(singer);
            return QiqvJSONResult.ok();
        } catch (IOException e) {
            e.printStackTrace();
            return QiqvJSONResult.errorMsg("上传失败");
        }
    }

    @PostMapping("updateSinger")
    public QiqvJSONResult updateSinger(@RequestBody  Singer singer){
        if(singer==null || singer.getId()==null){
            return QiqvJSONResult.errorMsg("参数错误，更新失败");
        }
        boolean updateFlag = singerService.updateSinger(singer);
        if(updateFlag){
            return QiqvJSONResult.ok();
        }else{
            return QiqvJSONResult.errorMsg("未知错误，更新失败");
        }
    }

    @PostMapping("delSinger")
    public QiqvJSONResult delSinger(@RequestBody Singer singer){
        if(singer==null || singer.getId()==null){
            return QiqvJSONResult.errorMsg("请选择歌手");
        }
        boolean delSuccess = singerService.deleteSingerById(singer.getId());
        if(delSuccess){
            return  QiqvJSONResult.ok();
        }else{
            return QiqvJSONResult.errorMsg("删除失败...");
        }
    }

    /**
     * 根据歌手性别查询歌手
     * @param sex
     * @return
     */
    @RequestMapping(path = "getSingerBySexClass",method = RequestMethod.GET)
    public QiqvJSONResult getSingerBySexClass(Integer sex){
        if(sex < 0 || sex > 3){
            return QiqvJSONResult.errorMsg("不可能的歌手类别");
        }
        List<Singer> singers = singerService.querySingerBySex(sex);
        if(singers!=null){
            return QiqvJSONResult.ok(singers);
        }
        return QiqvJSONResult.errorMsg("查询歌手失败");
    }


}
