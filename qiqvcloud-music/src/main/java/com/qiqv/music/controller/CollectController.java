package com.qiqv.music.controller;

import com.qiqv.music.pojo.Collect;
import com.qiqv.music.service.CollectService;
import com.qiqv.music.utils.QiqvJSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("collect")
public class CollectController {

    @Autowired
    private CollectService collectService;

    /**
     * 收藏歌曲
     * @param collect
     * @return
     */
    @PostMapping("/addCollect")
    public QiqvJSONResult addCollect(@RequestBody Collect collect) {
        String errorMsg = this.collectDataCheck(collect);
        if(null == errorMsg){
            boolean hasCollect = collectService.isUserCollect(collect.getUserId(), collect.getSongId());
                if(!hasCollect){
                 boolean isSuccess = collectService.insert(collect);
                    if (isSuccess) {
                        return QiqvJSONResult.ok();
                    }
                 errorMsg="收藏失败";
            }else{
                errorMsg="已经收藏过了...";
            }
        }
        return QiqvJSONResult.errorMsg(errorMsg);
    }

    private String collectDataCheck(Collect collect) {
        if(collect == null){
            return "操作失败";
        }else if(collect.getSongId()==null && collect.getSongListId()== null){
            return "未选择收藏对象，操作失败";
        }else if(collect.getUserId() == null){
            return "缺少用户id，操作失败";
        }
        return null;
    }

    /**
     * 取消收藏歌曲
     * @param collectId
     * @return
     */
    @PostMapping("/delCollect")
    public QiqvJSONResult delCollect(Integer collectId) {
        if(collectId == null){
            return QiqvJSONResult.errorMsg("取消收藏失败");
        }
        if(null == collectId){
            boolean isSuccess = collectService.delete(collectId);
            if (isSuccess) {
                return QiqvJSONResult.ok();
            }
        }
        return QiqvJSONResult.errorMsg("取消收藏失败");
    }
    /**
     * 查询所有收藏信息
     * @return
     */
    @RequestMapping(path="/getAllCollect",method = RequestMethod.GET)
    public QiqvJSONResult getAllCollect() {
        List<Collect> collects = collectService.allCollect();
        if (collects != null) {
            return QiqvJSONResult.ok(collects);
        }
        return QiqvJSONResult.errorMsg("查询收藏信息失败");
    }

    /**
     * 查询用户收藏的所有歌曲
     * @return
     */
    @RequestMapping(path="/getUserCollect",method = RequestMethod.GET)
    public QiqvJSONResult getUserCollect(Integer userId) {
        if(userId == null){
            return QiqvJSONResult.errorMsg("缺少用户Id");
        }
        List<Collect> collects = collectService.getUserCollect(userId);
        if (collects != null) {
            return QiqvJSONResult.ok(collects);
        }
        return QiqvJSONResult.errorMsg("获取用户收藏歌曲列表失败");
    }

    /**
     *  根据歌曲id和用户id删除用户收藏记录
     */
    /**
     * 查询用户收藏的所有歌曲
     * @return
     */
    @PostMapping("/delUserCollect")
    public QiqvJSONResult delUserCollect(@RequestBody Collect collect) {
        String errorMsg = this.collectDataCheck(collect);
        if(errorMsg == null){
            boolean delSuccess = collectService.deleteCollectBySongIdAndUserId(collect.getSongId(), collect.getUserId());
            if(delSuccess){
                return QiqvJSONResult.ok();
            }
            errorMsg = "删除失败";
        }
        return QiqvJSONResult.errorMsg(errorMsg);
    }



}
