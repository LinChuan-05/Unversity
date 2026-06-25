package com.lixianda.service;

import com.lixianda.entity.Exam;
import com.lixianda.mapper.ExamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamManageService {

    @Autowired
    private ExamMapper examMapper;

    public List<Exam> findAll() {
        return examMapper.findAll();
    }

    public List<Exam> findAllPublished() {
        return examMapper.findAllPublished();
    }

    public Exam findById(Integer examId) {
        return examMapper.findById(examId);
    }

    public int add(Exam exam) {
        return examMapper.insert(exam);
    }

    public int update(Exam exam) {
        return examMapper.update(exam);
    }

    public int delete(Integer examId) {
        return examMapper.deleteById(examId);
    }

    public int updateStatus(Integer examId, Integer status) {
        return examMapper.updateStatus(examId, status);
    }
}
