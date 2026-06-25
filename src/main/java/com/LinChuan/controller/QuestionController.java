package com.LinChuan.controller;

import com.LinChuan.entity.Question;
import com.LinChuan.entity.Result;
import com.LinChuan.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/find")
    public Result find(@RequestParam(required = false) Integer examId) {
        List<Question> list;
        if (examId != null) {
            list = questionService.findByExamId(examId);
        } else {
            list = questionService.findAll();
        }
        return Result.ok("ok", list);
    }

    @GetMapping("/findById")
    public Result findById(@RequestParam Integer questionId) {
        Question q = questionService.findById(questionId);
        return q != null ? Result.ok("ok", q) : Result.fail(404, "试题不存在");
    }

    @PostMapping("/add")
    public Result add(Question question) {
        questionService.add(question);
        return Result.ok("添加成功");
    }

    @PutMapping("/update")
    public Result update(Question question) {
        questionService.update(question);
        return Result.ok("更新成功");
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam Integer questionId) {
        questionService.delete(questionId);
        return Result.ok("删除成功");
    }
}
