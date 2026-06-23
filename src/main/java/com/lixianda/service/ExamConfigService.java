package com.lixianda.service;

import com.lixianda.entity.ExamConfig;
import com.lixianda.mapper.ExamConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamConfigService {

    @Autowired
    private ExamConfigMapper examConfigMapper;

    public ExamConfig get() {
        return examConfigMapper.get();
    }

    public int update(Integer duration) {
        if (duration == null || duration < 1 || duration > 180) {
            return -1;
        }
        return examConfigMapper.upsert(duration);
    }
}
