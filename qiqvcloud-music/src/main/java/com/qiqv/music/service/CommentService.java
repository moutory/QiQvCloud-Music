package com.qiqv.music.service;

import com.qiqv.music.pojo.Comment;
import com.qiqv.music.pojo.vo.CommentVO;

import java.util.List;

public interface CommentService {
    /**
     *增加
     */
    public boolean insertComment(Comment comment);

    /**
     *修改
     */
    public boolean updateComment(Comment comment);

    /**
     *修改
     */
    public boolean likeComment(CommentVO commentVO);

    /**
     * 删除
     */
    public boolean deleteCommentById(Integer id);

    /**
     * 根据主键查询整个对象
     */
    public Comment selectByPrimaryKey(Integer id);

    /**
     * 查询所有评论
     */
    public List<Comment> allComment();

    /**
     * 查询某个歌曲下的所有评论
     */
    public List<Comment> commentOfSongId(Integer songId);

    /**
     * 查询某个歌单下的所有评论
     */
    public List<Comment> commentOfSongListId(Integer songListId);

    /**
     * 根据评论id批量删除评论
     * @param batchDelForm
     * @return
     */
    boolean batchDeleteByCommentId(Integer[] batchDelForm);
    /**
     * 判断点赞行为是否合法,若合法，返回null
     */
    String isLegalLike(Integer commentId,Integer commentOwnerId,Integer flowerId);
}
