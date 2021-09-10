package com.bigbone.blog.dao;

import com.bigbone.blog.entity.Blog;
import com.bigbone.blog.entity.BlogQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public interface BlogDao{
    //保存新增博客
    int saveBlog(Blog blog);

    //查询文章管理列表
    List<BlogQuery> getAllBlogQuery();

    // 删除博客
    void deleteBlog(Long id);

    //更新博客
    int updateBlog(Blog blog);

    //查询编辑修改的文章
    Blog getBlogById(Long id);

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

    //计算博客总数
    Long countBlog();

    //获取所有博客年份
    List<String> findGroupYear();

    //按照年份查询博客
    List<Blog> findByYear(String year);
}
