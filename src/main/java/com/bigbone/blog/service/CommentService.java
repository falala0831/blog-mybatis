package com.bigbone.blog.service;

import com.bigbone.blog.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentService {
    int saveComment(Comment comment);

    List<Comment> listCommentByBlogId(Long blogId);

    //删除评论
    void deleteComment(Long id);
}
