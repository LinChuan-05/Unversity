package com.lixianda.controller;

import com.lixianda.entity.Result;
import com.lixianda.entity.Users;
import com.lixianda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/find")
    public Result find() {
        return Result.ok("ok", userService.findAll());
    }

    @PostMapping("/add")
    public Result add(@RequestParam String userName, @RequestParam String password,
                      @RequestParam String sex, @RequestParam String email,
                      @RequestParam(required = false) String realName,
                      @RequestParam(required = false) Integer classId) {
        Users user = new Users();
        user.setUserName(userName);
        user.setPassword(password);
        user.setSex(sex);
        user.setEmail(email);
        user.setRealName(realName);
        user.setClassId(classId);
        user.setRole("student");
        user.setStatus(1);
        int result = userService.register(user);
        if (result == 1) return Result.ok("注册成功");
        return Result.fail(500, "注册失败");
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam Integer userId, HttpSession session) {
        Users currentUser = (Users) session.getAttribute("user");
        int result = userService.delete(userId, currentUser);
        if (result == 1) return Result.ok("删除成功");
        if (result == -1) return Result.fail(400, "不能删除自己");
        return Result.fail(500, "删除失败");
    }

    @GetMapping("/classes")
    public Result classes() {
        return Result.ok("ok", userService.findAllClasses());
    }

    @GetMapping("/classStudents")
    public Result classStudents(@RequestParam String className) {
        return Result.ok("ok", userService.findClassStudentsWithScores(className));
    }
}
