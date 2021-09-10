package com.bigbone.blog.service;

import com.bigbone.blog.entity.Blog;
import com.bigbone.blog.entity.BlogQuery;

import java.util.List;
import java.util.Map;

/**
 *接口:博客管理
 */
public interface BlogService {
    //保存新增博客
    int saveBlog(Blog blog);

    //查询文章管理列表
    List<BlogQuery> getAllBlog();

    //删除博客
    void deleteBlog(Long id);

    //查询编辑修改的文章
    Blog getBlogById(Long id);

    //编辑修改文章
    int updateBlog(Blog blog);

    //搜索博客管理列表
    List<BlogQuery> searchBlog(BlogQuery blog);

    //查询首页最新博客列表信息
    List<Blog> getFirstPageBlog();

    //查询首页最新推荐信息
    List<Blog> getRecommendBlog();

    //搜索博客列表
    List<Blog> getSearchBlog(String query);

    //查询博客详情
    Blog getDetailedBlog(Long id);

    //文章访问更新
    int updateViews(Long id);

    //根据TypeId获取博客列表，在分类页进行的操作
    List<Blog> getByTypeId(Long typeId);

    // 存放key值为年份，value值为对应年份的Blog
    Map<String,List<Blog>> archiveBlog();

    // 计算博客总数
    Long countBlog();
}

