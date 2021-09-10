package com.bigbone.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Type {
    private Long id;
    private String name; //分类名
    private List<Blog> blogs = new ArrayList<>(); //含该分类的博客
}
