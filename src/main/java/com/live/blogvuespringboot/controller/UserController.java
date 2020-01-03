package com.live.blogvuespringboot.controller;

import com.live.blogvuespringboot.model.pojo.User;
import com.live.blogvuespringboot.service.Impl.UserServiceImpl;
import com.live.blogvuespringboot.utils.FormatUtil;
import com.live.blogvuespringboot.utils.Result;
import com.live.blogvuespringboot.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserServiceImpl userService;


    @PostMapping("/login")
    public Result Login(User user) {
        if (!FormatUtil.checkStringNull(user.getName(), user.getPassword()))
            return Result.create(StatusCode.ERROR, "参数错误");
        Map map = userService.login(user);
        if (map != null) {
            //登录成功更新登录表
            return Result.create(StatusCode.OK, "登录成功", map);
        } else {
            return Result.create(StatusCode.LOGINERROR, "登录失败，用户名或密码错误");
        }

    }
}
