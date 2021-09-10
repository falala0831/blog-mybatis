package com.bigbone.blog.service;

import com.bigbone.blog.dao.BlogDao;
import com.bigbone.blog.exception.NotFoundException;
import com.bigbone.blog.entity.Blog;
import com.bigbone.blog.entity.BlogQuery;
import com.bigbone.blog.entity.Type;
import com.bigbone.blog.util.MarkdownUtils;
import com.bigbone.blog.util.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 实现:博客管理
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;

    @Override
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        return blogDao.saveBlog(blog);
    }

    @Override
    public List<BlogQuery> getAllBlog() {
        return blogDao.getAllBlogQuery();
    }

    @Override
    public void deleteBlog(Long id) {
        blogDao.deleteBlog(id);
    }

    @Override
    public Blog getBlogById(Long id) {
        return blogDao.getBlogById(id);
    }

    @Override
    public int updateBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        return blogDao.updateBlog(blog);
    }

    @Override
    public List<BlogQuery> searchBlog(BlogQuery blog) {
        return blogDao.searchBlog(blog);
    }

    @Override
    public List<Blog> getFirstPageBlog() {
        return blogDao.getFirstPageBlog();
    }

    @Override
    public List<Blog> getSearchBlog(String query) {
        return blogDao.getSearchBlog(query);
    }

    @Override
    public List<Blog> getRecommendBlog() {
        return blogDao.getRecommendBlog();
    }

    @Override
    public Blog getDetailedBlog(Long id) {
        Blog blog = blogDao.getDetailedBlog(id);
        if (blog == null) {
            throw new NotFoundException("该博客不存在");
        }
        String content = blog.getContent();
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        //文章访问数量自增
        blogDao.updateViews(id);
        return blog;
    }

    @Override
    public int updateViews(Long id) {
        return blogDao.updateViews(id);
    }

    @Override
    public List<Blog> getByTypeId(Long typeId) {
        return blogDao.getByTypeId(typeId);
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogDao.findGroupYear();
        /*确保按照年份顺序显示*/
        Map<String, List<Blog>> map = new LinkedHashMap<>();
        for (String year : years) {
            map.put(year, blogDao.findByYear(year));
        }
        return map;
    }

    @Override
    public Long countBlog() {
        return blogDao.countBlog();
    }
}

