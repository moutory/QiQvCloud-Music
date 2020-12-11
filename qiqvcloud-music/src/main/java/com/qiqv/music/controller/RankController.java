package com.qiqv.music.controller;

import com.qiqv.music.pojo.Rank;
import com.qiqv.music.service.RankService;
import com.qiqv.music.utils.QiqvJSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rank")
public class RankController {

    @Autowired
    private RankService rankService;

    @PostMapping("/rateSongList")
    public QiqvJSONResult rateSongList(@RequestBody Rank rank){
        String errorMsg = this.rankDataCheck(rank);
        boolean isSuccess = false;
        if(errorMsg == null){
            try{
              isSuccess = rankService.rateSongList(rank);
            }catch (Exception e){
                if(e instanceof DuplicateKeyException){
                    return QiqvJSONResult.errorMsg("您已经评价过了...");
                }
            }
            if(isSuccess){
                return QiqvJSONResult.ok();
            }
            return QiqvJSONResult.errorMsg("评价失败");
        }
        return QiqvJSONResult.errorMsg(errorMsg);
    }

    private String rankDataCheck(Rank rank) {
        if(rank == null){
            return "评分失败";
        }
        if(rank.getScore()>10 || rank.getScore()<0){
            return "不合法的评分";
        }
        if(rank.getConsumerId()==0 || null == rank.getConsumerId()){
            return "缺少用户Id";
        }
        if(rank.getSongListId() == null){
            return "缺少歌单Id";
        }
        return null;
    }

    /**
     * 获取歌单评分
     * @param songListId
     * @return
     */
    @RequestMapping(path = "/getAverageRank",method = RequestMethod.GET)
    public QiqvJSONResult getAverageRank(Integer songListId){
        if(songListId == null){
            return QiqvJSONResult.errorMsg("缺少歌单id，查询评分失败");
        }
        Integer avgRank = rankService.getAverageRankBySongListId(songListId);
        if(avgRank == null){
            return QiqvJSONResult.errorMsg("查询歌单评分失败");
        }
        return QiqvJSONResult.ok(avgRank);
    }


}
