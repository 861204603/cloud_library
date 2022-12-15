package com.sdut.cloud_library.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sdut.cloud_library.common.R;
import com.sdut.cloud_library.entity.User;
import com.sdut.cloud_library.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;
    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @PostMapping("/login")
    public R<User> login(HttpServletRequest request,@RequestBody User user) {
        //1、将页面提交的密码password进行md5加密处理
        String userPassword = user.getUserPassword();
        userPassword = DigestUtils.md5DigestAsHex(userPassword.getBytes());
        //2、根据页面提交的用户名username查询数据库
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, user.getUserName());
        User u = userService.getOne(queryWrapper);

        //3、如果没有查询到则返回登录失败结果
        if (u == null) {
            return R.error("登录失败");
        }

        //4、密码比对，如果不一致则返回登录失败结果
        if (!u.getUserPassword().equals(userPassword)) {
            return R.error("登录失败");
        }

        return R.success(u);
    }

}
