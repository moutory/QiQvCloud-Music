package com.qiqv.music.controller;

import com.qiqv.music.pojo.Comment;
import com.qiqv.music.service.CommentService;
import com.qiqv.music.utils.QiqvJSONResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 添加评论
     * @param comment
     * @return
     */
    @PostMapping("/saveComment")
    public QiqvJSONResult saveComment(@RequestBody Comment comment){
        String errorMsg = this.commentDataCheck(comment);
        if(errorMsg == null) {
            if(null == comment.getCreateTime()){
                comment.setCreateTime(new Date());
            }
            boolean isSuccess = commentService.insertComment(comment);
            if(isSuccess){
                return QiqvJSONResult.ok();
            }
                return QiqvJSONResult.errorMsg("评论失败");
        }
        return QiqvJSONResult.errorMsg(errorMsg);
    }

    /**
     * 修改评论
     * @param comment
     * @return
     */
    @PostMapping("/updateComment")
    public QiqvJSONResult updateComment(@RequestBody Comment comment){
        String errorMsg = this.commentDataCheck(comment);
        if(errorMsg == null) {
            boolean isSuccess = commentService.updateComment(comment);
            if(isSuccess){
                return QiqvJSONResult.ok();
            }
            return QiqvJSONResult.errorMsg("修改评论失败");
        }
        return QiqvJSONResult.ok(errorMsg);
    }

    /**
     * 删除评论
     * @param comment
     * @return
     */
    @PostMapping("/delComment")
    public QiqvJSONResult delComment(@RequestBody Comment comment){
        if(comment.getId()==null){
            return QiqvJSONResult.errorMsg("请选择要删除的评论");
        }

        boolean isSuccess = commentService.deleteCommentById(comment.getId());
        if(isSuccess){
            return QiqvJSONResult.ok();
        }
        return QiqvJSONResult.errorMsg("删除评论失败");
    }

    /**
     * 根据id查询评论
     * @param commentId
     * @return
     */
    @RequestMapping(path="/qureyCommentById",method = RequestMethod.GET)
    public QiqvJSONResult qureyCommentById(Integer commentId) {
        if (commentId == null) {
            return QiqvJSONResult.errorMsg("请选择要查询的评论");
        }
        Comment comment = commentService.selectByPrimaryKey(commentId);
        if (comment == null) {
            return QiqvJSONResult.errorMsg("该条评论不存在");
        }
        return QiqvJSONResult.ok(comment);
    }

    /**
     * 查询所有评论
     * @return
     */
    @RequestMapping(path="/allComment",method = RequestMethod.GET)
    public QiqvJSONResult allComment() {
        List<Comment> comments = commentService.allComment();
        if (comments == null || comments.isEmpty()) {
            return QiqvJSONResult.errorMsg("还没有人评论哦~");
        }
        return QiqvJSONResult.ok(comments);
    }

    /**
     * 查询指定歌曲的所有评论
     * @return
     */
    @RequestMapping(path="/queryCommentBySongId",method = RequestMethod.GET)
    public QiqvJSONResult queryCommentBySongId(Integer songId) {
        if (songId == null) {
            return QiqvJSONResult.errorMsg("缺少歌曲Id");
        }
        List<Comment> comments = commentService.commentOfSongId(songId);
        if (comments == null || comments.isEmpty()) {
            return QiqvJSONResult.errorMsg("还没有人评论哦~");
        }
        return QiqvJSONResult.ok(comments);
    }

    /**
     * 查询指定歌单的所有评论
     * @return
     */
    @RequestMapping(path="/queryCommentBySongListId",method = RequestMethod.GET)
    public QiqvJSONResult queryCommentBySongListId(Integer songListId) {
        if (songListId == null) {
            return QiqvJSONResult.errorMsg("缺少歌单Id");
        }
        List<Comment> comments = commentService.commentOfSongListId(songListId);
        if (comments == null || comments.isEmpty()) {
            return QiqvJSONResult.errorMsg("还没有人评论哦~");
        }
        return QiqvJSONResult.ok(comments);
    }

    /**
     * 给某个评论点赞
     * @param comment
     * @return
     */
    @PostMapping("/likeComment")
    public QiqvJSONResult likeComment(@RequestBody Comment comment){
        if(comment.getId()==null){
            return QiqvJSONResult.errorMsg("请选择要点赞的评论");
        }
        boolean isSuccess = commentService.updateComment(comment);
        if(isSuccess){
            return QiqvJSONResult.ok();
        }
        return QiqvJSONResult.errorMsg("点赞失败");
    }

    /**
     * 批量删除评论
     * @param batchDelForm
     * @return
     */
    @PostMapping("/batchDeleteSelComments")
    public QiqvJSONResult batchDeleteSelComments(@RequestBody Integer[] batchDelForm){
        if(batchDelForm == null || batchDelForm.length == 0){
            return QiqvJSONResult.errorMsg("请输入批量删除的id号");
        }
        boolean isSuccess = commentService.batchDeleteByCommentId(batchDelForm);
        if(isSuccess){
            return QiqvJSONResult.ok();
        }
        return QiqvJSONResult.errorMsg("批量删除失败");
     }




    private String commentDataCheck(Comment comment) {
        if(comment == null){
            return "评论失败";
        }
        if(StringUtils.isBlank(comment.getContent())){
            return "评论内容为空";
        }
        if(comment.getUserId()==0 || null == comment.getUserId()){
            return "缺少用户Id";
        }
        if(comment.getType() == null){
            return "未指定评论对象";
        }
        if(comment.getSongId() == null && comment.getSongListId() == null){
            return "未指定评论对象";
        }
        return null;
    }

}
