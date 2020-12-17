package com.qiqv.music.service.impl;

import com.qiqv.music.dao.CommentLikeMapper;
import com.qiqv.music.dao.CommentMapper;
import com.qiqv.music.pojo.Comment;
import com.qiqv.music.pojo.CommentExample;
import com.qiqv.music.pojo.CommentLike;
import com.qiqv.music.pojo.CommentLikeExample;
import com.qiqv.music.pojo.vo.CommentVO;
import com.qiqv.music.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CommentLikeMapper commentLikeMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean insertComment(Comment comment) {
        return commentMapper.insertSelective(comment)>0;
    }

    /**
     *  满足条件后，更新up数量并新增一条Comment_like记录
     * @param comment
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean likeComment(CommentVO comment) {
        CommentLike commentLike = new CommentLike();
        commentLike.setCommentId(comment.getId());
        commentLike.setFlowerId(comment.getFlowerId());
        commentLike.setCreateTime(new Date());
        commentLikeMapper.insertSelective(commentLike);
        return commentMapper.updateByPrimaryKeySelective(comment)>0;
    }

    /**
     *  满足条件后，更新up数量并新增一条Comment_like记录
     * @param comment
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean updateComment(Comment comment) {
        return commentMapper.updateByPrimaryKeySelective(comment)>0;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean deleteCommentById(Integer commentId) {
        return commentMapper.deleteByPrimaryKey(commentId)>0;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Comment selectByPrimaryKey(Integer commentId) {
        return commentMapper.selectByPrimaryKey(commentId);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Comment> allComment() {
        CommentExample commentExample = new CommentExample();
        commentExample.setOrderByClause("create_time desc");
        return commentMapper.selectByExample(commentExample);
    }

    /**
     * 根据歌曲Id查询评论
     * @param songId
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Comment> commentOfSongId(Integer songId) {
        CommentExample commentExample = new CommentExample();
        commentExample.setOrderByClause("create_time desc");
        CommentExample.Criteria criteria = commentExample.createCriteria();
        criteria.andSongIdEqualTo(songId);
        return commentMapper.selectByExample(commentExample);
    }

    /**
     * 根据歌单Id查询评论
     * @param songListId
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Comment> commentOfSongListId(Integer songListId) {
        CommentExample commentExample = new CommentExample();
        commentExample.setOrderByClause("create_time desc");
        CommentExample.Criteria criteria = commentExample.createCriteria();
        criteria.andSongListIdEqualTo(songListId);
        return commentMapper.selectByExample(commentExample);
    }

    /**
     * 根据评论id批量删除评论
     * @param batchDelForm
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean batchDeleteByCommentId(Integer[] batchDelForm) {
        int i = 0;
        for(Integer commentId :batchDelForm){
            commentMapper.deleteByPrimaryKey(commentId);
            i++;
        }
        return i==batchDelForm.length;
    }

    @Override
    public String isLegalLike(Integer commentId,Integer commentOwnerId,Integer flowerId){
        boolean isDifUser = commentOwnerId != flowerId;
        CommentLikeExample commentLikeExample = new CommentLikeExample();
        CommentLikeExample.Criteria clCriteria = commentLikeExample.createCriteria();
        clCriteria.andCommentIdEqualTo(commentId);
        clCriteria.andFlowerIdEqualTo(flowerId);
        boolean firstLike = commentLikeMapper.selectByExample(commentLikeExample).isEmpty();
        if(!firstLike){
            return "您已经点过赞了";
        }else if(!isDifUser){
            return "不能点赞自己的评论";
        }else{
            return null;
        }

    }


}
