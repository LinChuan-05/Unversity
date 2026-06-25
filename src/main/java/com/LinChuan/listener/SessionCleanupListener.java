package com.LinChuan.listener;

import com.LinChuan.service.ExamConcurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Session 销毁时自动释放考试并发名额
 */
@WebListener
@Component
public class SessionCleanupListener implements HttpSessionListener {

    private static ExamConcurrencyService examConcurrencyService;

    @Autowired
    public void setExamConcurrencyService(ExamConcurrencyService service) {
        SessionCleanupListener.examConcurrencyService = service;
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {}

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        if (examConcurrencyService != null) {
            examConcurrencyService.leave(se.getSession().getId());
        }
    }
}
