package com.lixianda.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 考试并发控制：限制同时在线考试人数 ≤ 50
 */
@Service
public class ExamConcurrencyService {

    private static final int MAX_CONCURRENT = 50;

    // key=sessionId, value=deadline(ms)
    private final ConcurrentHashMap<String, Long> activeExams = new ConcurrentHashMap<>();

    public synchronized boolean tryEnter(String sessionId, long deadline) {
        cleanExpired();
        if (activeExams.size() >= MAX_CONCURRENT) {
            return false;
        }
        activeExams.put(sessionId, deadline);
        return true;
    }

    public void leave(String sessionId) {
        activeExams.remove(sessionId);
    }

    public int getOnlineCount() {
        cleanExpired();
        return activeExams.size();
    }

    private void cleanExpired() {
        long now = System.currentTimeMillis();
        activeExams.entrySet().removeIf(e -> now > e.getValue());
    }
}
