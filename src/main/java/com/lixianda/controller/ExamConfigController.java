package com.lixianda.controller;

import com.lixianda.entity.ExamConfig;
import com.lixianda.entity.Result;
import com.lixianda.service.ExamConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/examConfig")
public class ExamConfigController {

    @Autowired
    private ExamConfigService examConfigService;

    @GetMapping
    public Result get() {
        ExamConfig config = examConfigService.get();
        return Result.ok("ok", config);
    }

    @PutMapping
    public Result update(@RequestParam Integer duration) {
        int result = examConfigService.update(duration);
        if (result >= 1) {
            return Result.ok("考试时长已更新为 " + duration + " 分钟");
        }
        return Result.fail(400, "设置失败，时长需在 1-180 分钟之间");
    }
}
