package com.bigbone.blog.controller;

import com.bigbone.blog.entity.Blog;
import com.bigbone.blog.entity.Comment;
import com.bigbone.blog.service.BlogService;
import com.bigbone.blog.service.CommentService;
import com.bigbone.blog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private CommentService commentService;

    /*加载初始首页*/
    @GetMapping("/")
    public String index(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<Blog> firstPageBlog = blogService.getFirstPageBlog();
        PageInfo<Blog> blogPageInfo = new PageInfo<>(firstPageBlog);
        model.addAttribute("pageInfo", blogPageInfo);
        /*列出规定size的types值*/
        model.addAttribute("types", typeService.getAllType());
        /*列出规定size的推荐blog*/
        model.addAttribute("recommendBlogs", blogService.getRecommendBlog());
        return "index";
    }

    /*搜索框回车后查询结果返回的页面*/
    @PostMapping("/search")
    public String search(Model model,
                         @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                         @RequestParam String query) {
        PageHelper.startPage(pageNum, 10);
        List<Blog> searchBlog = blogService.getSearchBlog(query);
        PageInfo<Blog> blogPageInfo = new PageInfo<>(searchBlog);
        model.addAttribute("pageInfo", blogPageInfo);
        model.addAttribute("query", query);
        return "search";
    }

    /*点击跳转到对应的博客详情区域*/
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {
        /*将Markdown转为HTML*/
        model.addAttribute("blog", blogService.getDetailedBlog(id));
        model.addAttribute("blogId", id);
        List<Comment> comments = commentService.listCommentByBlogId(id);
        model.addAttribute("comments", comments);
        System.out.println(comments);
        return "blog";
    }

//    @GetMapping("/footer/newblog")
//    public String newblogs(Model model) {
//        model.addAttribute("newblogs", blogService.listRecommendBlogTop(3));
//        return "_fragments :: newblogList";
//    }
}
