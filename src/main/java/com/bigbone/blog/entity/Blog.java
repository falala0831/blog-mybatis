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
public class Blog {
    private Long id;
    private String title; //标题
    private String content; //内容
    private String firstPicture; //首图
    private String flag; //标记
    private String description; // 博客描述
    private Integer views; //浏览量
    private boolean appreciation; // 赞赏是否开启
    private boolean shareStatement; //版权是否开启
    private boolean commentabled; //评论是否开启
    private boolean published; //是否发布
    private boolean recommend; //推荐是否开启
    private Date createTime; //创建时间
    private Date updateTime; //更新时间
    private Type type; //博客所属分类
    private User user; //博客所属用户
    private Long typeId; //分类id
    private Long userId; //用户id
    private List<Comment> comments = new ArrayList<>(); // 博客包含的评论
}
