package com.itheima.controller;

import com.auth0.jwt.JWT;
import com.itheima.pojo.Result;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.utils.JwtUtil;
import com.itheima.utils.Md5Util;
import com.itheima.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    //注册
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username,@Pattern(regexp = "^\\S{5,16}$") String password){
        User u = userService.findByUserName(username);
        if (u==null){
            // 没被占用
            userService.register(username, password);
            return Result.success();
        }else {
            // 被占用
            return Result.error("用户名已被占用");
        }
    }
    //登录
    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{5,16}$") String username,@Pattern(regexp = "^\\S{5,16}$") String password){
        User u = userService.findByUserName(username);
        if (u!=null){
            // 存在该用户
            if (userService.login(username, password)){
                // 密码正确
                Map<String,Object> claims = new HashMap<>();
                claims.put("id",u.getId());
                claims.put("username",u.getUsername());
                String token = JwtUtil.genToken(claims);
                //把token存储到redis
                ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
                operations.set(token,token,1, TimeUnit.HOURS);
                return Result.success(token);     // 登录成功
            }else {
                // 密码错误
                return Result.error("密码错误");
            }
        }else {
            // 不存在该用户
            return Result.error("用户名不存在");
        }
    }
    //获取用户信息
    @GetMapping("/userInfo")
    public Result<User> userInfo(/*@RequestHeader(name = "Authorization") String token*/){
       /* Map<String, Object> map = JwtUtil.parseToken(token);
        String username = (String) map.get("username");*/
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }
    //更新用户信息
    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success();
    }
    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }
    //更新密码
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> params,@RequestHeader("Authorization") String token){
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");

        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)){
            return Result.error("缺少必要的参数");
        }
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User loginUser = userService.findByUserName(username);
        if (!loginUser.getPassword().equals(Md5Util.getMD5String(oldPwd))){
            return Result.error("原密码错误");
        }
        if (!rePwd.equals(newPwd)){
            return Result.error("两次填写的新密码不一样");
        }
        userService.updatePwd(newPwd);
        //删除redis中原密码的token
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete(token);
        return Result.success();
    }
}
