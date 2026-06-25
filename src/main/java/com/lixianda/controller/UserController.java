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
        List<Users> list = userService.findAll();
        return Result.ok("ok", list);
    }

    @PostMapping("/add")
    public Result add(@RequestParam String userName, @RequestParam String password,
                      @RequestParam String sex, @RequestParam String email,
                      @RequestParam(defaultValue = "") String className) {
        Users user = new Users(null, userName, password, sex, email, "student", className);
        int result = userService.register(user);
        if (result == 1) {
            return Result.ok("注册成功");
        }
        return Result.fail(500, "注册失败");
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam Integer userId, HttpSession session) {
        Users currentUser = (Users) session.getAttribute("user");
        int result = userService.delete(userId, currentUser);
        if (result == 1) {
            return Result.ok("删除成功");
        } else if (result == -1) {
            return Result.fail(400, "不能删除自己");
        }
        return Result.fail(500, "删除失败");
    }

    /** 管理员：获取班级列表 */
    @GetMapping("/classes")
    public Result classes() {
        return Result.ok("ok", userService.findAllClasses());
    }

    /** 管理员：获取某班级的学生及成绩 */
    @GetMapping("/classStudents")
    public Result classStudents(@RequestParam String className) {
        return Result.ok("ok", userService.findClassStudentsWithScores(className));
    }
}
