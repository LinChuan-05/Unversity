package com.lixianda.mapper;

import com.lixianda.entity.Exam;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ExamMapper {

    @Select("SELECT * FROM exam ORDER BY examId")
    List<Exam> findAll();

    @Select("SELECT * FROM exam WHERE paper_status = 1 ORDER BY examId")
    List<Exam> findAllPublished();

    @Select("SELECT * FROM exam WHERE examId = #{examId} LIMIT 1")
    Exam findById(@Param("examId") Integer examId);

    @Insert("INSERT INTO exam(name, duration, description, maxAttempts, questionCount, paper_status) VALUES(#{name}, #{duration}, #{description}, #{maxAttempts}, #{questionCount}, 0)")
    // 新科目默认草稿状态
    @Options(useGeneratedKeys = true, keyProperty = "examId")
    int insert(Exam exam);

    @Update("UPDATE exam SET name=#{name}, duration=#{duration}, description=#{description}, maxAttempts=#{maxAttempts}, questionCount=#{questionCount} WHERE examId=#{examId}")
    int update(Exam exam);

    @Delete("DELETE FROM exam WHERE examId = #{examId}")
    int deleteById(@Param("examId") Integer examId);

    @Update("UPDATE exam SET paper_status = #{status} WHERE examId = #{examId}")
    int updateStatus(@Param("examId") Integer examId, @Param("status") Integer status);
}
