package com.LinChuan.service;

import com.LinChuan.entity.ResetRequest;
import com.LinChuan.mapper.ResetRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ResetRequestService {

    @Autowired
    private ResetRequestMapper resetRequestMapper;

    public int submit(Integer userId, Integer examId, Integer score, String reason) {
        // 已有待处理的同一请求，不重复提交
        ResetRequest existing = resetRequestMapper.findPending(userId, examId);
        if (existing != null) {
            return -1;
        }
        ResetRequest req = new ResetRequest();
        req.setUserId(userId);
        req.setExamId(examId);
        req.setScore(score);
        req.setReason(reason);
        return resetRequestMapper.insert(req);
    }

    public List<Map<String, Object>> findByUserId(Integer userId) {
        return resetRequestMapper.findByUserId(userId);
    }

    public List<Map<String, Object>> findAllRequests() {
        return resetRequestMapper.findAllRequests();
    }

    public int approve(Integer requestId) {
        return resetRequestMapper.updateStatus(requestId, "approved");
    }

    public int reject(Integer requestId) {
        return resetRequestMapper.updateStatus(requestId, "rejected");
    }
}
