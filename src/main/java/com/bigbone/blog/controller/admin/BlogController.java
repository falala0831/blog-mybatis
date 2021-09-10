package com.bigbone.blog.controller.admin;

import com.bigbone.blog.entity.Blog;
import com.bigbone.blog.entity.BlogQuery;
import com.bigbone.blog.entity.User;
import com.bigbone.blog.service.BlogService;
import com.bigbone.blog.service.TypeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.sound.sampled.Line;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {
    /*博客发布页面*/
    private static final String INPUT = "admin/blogs-input";
    /*博客显示页面*/
    private static final String LIST = "admin/blogs";
    /*重定向到博客显示页面*/
    private static final String REDIRECT_LIST = "redirect:/admin/blogs";


    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;

    /*博客列表*/
    @GetMapping("/blogs")
    public String blogs(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        //按照排序字段 倒序 排序
        String orderBy = "update_time desc";
        PageHelper.startPage(pageNum,10,orderBy);
        List<BlogQuery> list = blogService.getAllBlog();
        PageInfo<BlogQuery> pageInfo = new PageInfo<BlogQuery>(list);
        //由于前端下拉框需要type数据，因此type数据需要初始化
        model.addAttribute("types",typeService.getAllType());
        model.addAttribute("pageInfo",pageInfo);
        return LIST;
    }

    /*查询过后局部更新页面*/
    @PostMapping("/blogs/search")
    public String search(BlogQuery blog, Model model,
                         @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        List<BlogQuery> blogBySearch = blogService.searchBlog(blog);
        PageHelper.startPage(pageNum, 10);
        PageInfo<BlogQuery> pageInfo = new PageInfo<BlogQuery>(blogBySearch);
        model.addAttribute("pageInfo", pageInfo);
        /*返回admin/blogs文件里的一个片段*/
        return "admin/blogs :: blogList";
    }

    /*跳转博客新增页面*/
    @GetMapping("/blogs/input")
    public String input(Model model) {
        model.addAttribute("types", typeService.getAllType());
        model.addAttribute("blog", new Blog());
        return INPUT;
    }


    //跳转编辑页面
    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        Blog blog = blogService.getBlogById(id);
        model.addAttribute("types", typeService.getAllType());
        model.addAttribute("blog",blog);
        return INPUT;
    }

    //编辑修改文章
    @PostMapping("/blogs/{id}")
    public String editPost(Blog blog, RedirectAttributes attributes) {
        int b = blogService.updateBlog(blog);
        if(b == 0){
            attributes.addFlashAttribute("message", "修改失败");
        }else {
            attributes.addFlashAttribute("message", "修改成功");
        }
        return REDIRECT_LIST;
    }

    //编辑新文章
    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session) {
        //新增的时候需要传递blog对象，blog对象需要有user
        blog.setUser((User) session.getAttribute("user"));
        //设置blog的type
        blog.setType(typeService.getType(blog.getType().getId()));
        //设置blog中typeId属性
        blog.setTypeId(blog.getType().getId());
        //设置用户id
        blog.setUserId(blog.getUser().getId());

        int b = blogService.saveBlog(blog);
        if(b == 0){
            attributes.addFlashAttribute("message", "新增失败");
        }else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return REDIRECT_LIST;
    }


    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return REDIRECT_LIST;
    }



}
