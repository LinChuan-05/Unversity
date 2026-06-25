package com.lixianda.controller;

import com.lixianda.entity.Exam;
import com.lixianda.entity.Result;
import com.lixianda.service.ExamManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/examManage")
public class ExamManageController {

    @Autowired
    private ExamManageService examManageService;

    @GetMapping("/list")
    public Result list() {
        List<Exam> list = examManageService.findAll();
        return Result.ok("ok", list);
    }

    @GetMapping("/{examId}")
    public Result getById(@PathVariable Integer examId) {
        Exam exam = examManageService.findById(examId);
        return exam != null ? Result.ok("ok", exam) : Result.fail(404, "科目不存在");
    }

    @PostMapping("/add")
    public Result add(Exam exam) {
        examManageService.add(exam);
        return Result.ok("科目添加成功");
    }

    @PutMapping("/update")
    public Result update(Exam exam) {
        examManageService.update(exam);
        return Result.ok("科目更新成功");
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam Integer examId) {
        examManageService.delete(examId);
        return Result.ok("科目已删除");
    }

    @PostMapping("/publish")
    public Result publish(@RequestParam Integer examId) {
        examManageService.updateStatus(examId, 1);
        return Result.ok("科目已发布");
    }

    @PostMapping("/unpublish")
    public Result unpublish(@RequestParam Integer examId) {
        examManageService.updateStatus(examId, 0);
        return Result.ok("科目已关闭");
    }
}
