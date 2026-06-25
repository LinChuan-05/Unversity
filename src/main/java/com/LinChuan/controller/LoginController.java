package com.LinChuan.controller;

import com.LinChuan.entity.Result;
import com.LinChuan.entity.Users;
import com.LinChuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestParam String userName, @RequestParam String password, HttpSession session) {
        Users user = userService.login(userName, password);
        if (user != null) {
            session.setAttribute("user", user);
            return Result.ok("登录成功", user);
        }
        return Result.fail(401, "用户名或密码错误");
    }

    @GetMapping("/currentUser")
    public Result currentUser(HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        if (user != null) {
            return Result.ok("ok", user);
        }
        return Result.fail(401, "未登录");
    }

    @PostMapping("/logout")
    public Result logout(HttpSession session) {
        session.invalidate();
        return Result.ok("已退出");
    }
}
