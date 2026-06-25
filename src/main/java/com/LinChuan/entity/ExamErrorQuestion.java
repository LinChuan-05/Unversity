package com.LinChuan.entity;

import java.util.Date;

public class ExamErrorQuestion {
    private Integer errorId;
    private Integer userId;
    private Integer questionId;
    private Integer errorTimes;
    private Date lastExamTime;
    private Integer isMaster;
    private String userAnswer;
    private String rightAnswer;

    public Integer getErrorId() { return errorId; }
    public void setErrorId(Integer errorId) { this.errorId = errorId; }
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public Integer getQuestionId() { return questionId; }
    public void setQuestionId(Integer questionId) { this.questionId = questionId; }
    public Integer getErrorTimes() { return errorTimes; }
    public void setErrorTimes(Integer errorTimes) { this.errorTimes = errorTimes; }
    public Date getLastExamTime() { return lastExamTime; }
    public void setLastExamTime(Date lastExamTime) { this.lastExamTime = lastExamTime; }
    public Integer getIsMaster() { return isMaster; }
    public void setIsMaster(Integer isMaster) { this.isMaster = isMaster; }
    public String getUserAnswer() { return userAnswer; }
    public void setUserAnswer(String userAnswer) { this.userAnswer = userAnswer; }
    public String getRightAnswer() { return rightAnswer; }
    public void setRightAnswer(String rightAnswer) { this.rightAnswer = rightAnswer; }
}
