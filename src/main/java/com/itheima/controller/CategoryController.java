package com.itheima.controller;

import com.itheima.pojo.Category;
import com.itheima.pojo.Result;
import com.itheima.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //新增分类
    @PostMapping
    public Result add(@RequestBody @Validated(Category.Add.class) Category category){
        categoryService.add(category);
        return Result.success();
    }
    //展示所有分类
    @GetMapping
    public Result<List<Category>> list(){
        List<Category> cs = categoryService.list();
        return Result.success(cs);
    }
    //获取分类详情
    @GetMapping("/detail")
    public Result<Category> detail(Integer id){
        Category c = categoryService.findById(id);
        return Result.success(c);
    }
    //更新分类
    @PutMapping
    public Result update(@RequestBody @Validated(Category.Update.class) Category category){
        categoryService.update(category);
        return Result.success();
    }
    //删除分类
    @DeleteMapping
    public Result deleteById(Integer id){
        categoryService.deleteById(id);
        return Result.success();
    }
}
