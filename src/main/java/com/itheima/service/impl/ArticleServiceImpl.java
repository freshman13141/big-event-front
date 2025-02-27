package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.ArticleMapper;
import com.itheima.pojo.Article;
import com.itheima.pojo.PageBean;
import com.itheima.service.ArticleService;
import com.itheima.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public void add(Article article) {
        //补充属性值
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        article.setCreateUser(userId);
        articleMapper.add(article);

    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {

        //开启分页查询
        PageHelper.startPage(pageNum,pageSize);

        //调用mapper
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        // List<Article> as = articleMapper.list(userId,categoryId,state);
        // Page<Article> p = (Page<Article>) as;
        Page<Article> p = (Page<Article>) articleMapper.list(userId,categoryId,state);

        //创建PageBean对象
        // PageBean<Article> pb = new PageBean<>();
        // pb.setTotal(p.getTotal());
        // pb.setItems(p.getResult());
        // return pb;
        return new PageBean<>(p.getTotal(),p.getResult());
    }



    @Override
    public Article getArticleById(Integer id) {
        Article article = articleMapper.getArticleById(id);
        return article;
    }

    @Override
    public void delete(Integer id) {
        articleMapper.delete(id);
    }

    @Override
    public void update(Article article) {
        article.setUpdateTime(LocalDateTime.now());
        articleMapper.update(article);
    }
}
