package com.itheima.controller;

import com.auth0.jwt.JWT;
import com.itheima.pojo.Article;
import com.itheima.pojo.Category;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;

import com.itheima.service.ArticleService;
import com.itheima.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    //发布文章
    @PostMapping
    public Result add(@RequestBody @Validated Article article){
        articleService.add(article);
        return Result.success();
    }
    @GetMapping
    public Result<PageBean<Article>> list(//分页展示 PageBean对象，每个PageBean里面是article集合,有一个属性total
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false)Integer categoryId,
            @RequestParam(required = false)String state
    ){
       PageBean<Article> pb = articleService.list(pageNum,pageSize,categoryId,state);
       return Result.success(pb);
    }
    @GetMapping("/detail")
    public Result<Article> getArticleById(Integer id){//获取文章详情
        Article article = articleService.getArticleById(id);
        return Result.success(article);
    }
    @PutMapping
    public Result update(@RequestBody @Validated Article article){//更新文章
        articleService.update(article);
        return Result.success();
    }
    @DeleteMapping
    public Result delete(Integer id){//删除文章
        articleService.delete(id);
        return Result.success();
    }
}
