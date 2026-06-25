package com.lixianda.mapper;

import org.apache.ibatis.annotations.*;

@Mapper
public interface ExamErrorQuestionMapper {

    @Insert("INSERT INTO exam_error_question (user_id, question_id, user_answer, right_answer) " +
            "VALUES (#{userId}, #{questionId}, #{userAnswer}, #{rightAnswer}) " +
            "ON DUPLICATE KEY UPDATE error_times = error_times + 1, last_exam_time = NOW(), user_answer = #{userAnswer}")
    int upsert(@Param("userId") Integer userId, @Param("questionId") Integer questionId,
               @Param("userAnswer") String userAnswer, @Param("rightAnswer") String rightAnswer);
}
