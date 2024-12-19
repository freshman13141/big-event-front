package com.itheima.mapper;

import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByUserName(String username);

    @Insert("INSERT INTO user(username, password,create_time, update_time)"+
            "VALUES(#{username}, #{password}, NOW(), NOW())"
    )
    void addUser(String username, String password);
    @Update("UPDATE user SET nickname=#{nickname},email=#{email},update_time=#{updateTime} WHERE id=#{id}")
    void update(User user);

    @Update("UPDATE user SET user_pic=#{avatarUrl},user.update_time=now() WHERE id=#{id}")
    void updateAvatar(String avatarUrl, Integer id);

    @Update("UPDATE user SET password=#{md5String},update_time=now() WHERE id=#{id}")
    void updatePwd(String md5String, Integer id);
}
