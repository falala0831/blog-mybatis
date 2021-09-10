package com.bigbone.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private Long id;
    private String nickname; //昵称
    private String email;   //邮箱
    private String content; //评论内容
    private String avatar;  //头像
    private Date createTime;    //创建时间
    private Long blogId;
    private Long parentCommentId;
    private String parentNickname;
    private Blog blog;
    private List<Comment> replyComments = new ArrayList<>();
    private Comment parentComment;
    private boolean adminComment;
}
