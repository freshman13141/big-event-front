package com.itheima.mapper;

import com.itheima.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Insert("INSERT INTO category(category_name, category_alias, create_user, create_time, update_time)" +
            "VALUES(#{categoryName},#{categoryAlias},#{createUser},#{createTime},#{updateTime})")
    void add(Category category);
    //获取所有文章分类（当前已登录用户所创建的）
    @Select("SELECT * FROM category WHERE create_user=#{userId}")
    List<Category> list(Integer userId);
    //根据分类的id查找分类详情（id是自增的，用户不会主动使用，主要是为了完成编辑文章分类详情时，能够根据id来回显文章分类数据）
    //由于用户要先显示所有文章分类，然后才有编辑文章分类的按钮，所以显示的都是已登录用户创建的分类
    @Select("SELECT * FROM category WHERE id=#{id}")
    Category findById(Integer id);

    @Update("UPDATE category SET category_name=#{categoryName},category_alias=#{categoryAlias},update_time=#{updateTime} WHERE id=#{id}")
    void update(Category category);

    @Delete("DELETE FROM category WHERE id=#{id}")
    void deleteById(Integer id);
}


