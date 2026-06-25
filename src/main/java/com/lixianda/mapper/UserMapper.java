package com.lixianda.mapper;

import com.lixianda.entity.Users;
import org.apache.ibatis.annotations.*;
import java.util.List;
import java.util.Map;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM users WHERE userName = #{userName} AND password = #{password} LIMIT 1")
    @Results({
        @Result(property = "userId", column = "userId"),
        @Result(property = "userName", column = "userName"),
        @Result(property = "password", column = "password"),
        @Result(property = "sex", column = "sex"),
        @Result(property = "email", column = "email"),
        @Result(property = "role", column = "role"),
        @Result(property = "className", column = "className")
    })
    Users login(@Param("userName") String userName, @Param("password") String password);

    @Insert("INSERT INTO users(userName, password, sex, email, role, className) VALUES(#{userName}, #{password}, #{sex}, #{email}, #{role}, #{className})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insert(Users user);

    @Select("SELECT * FROM users")
    List<Users> findAll();

    @Delete("DELETE FROM users WHERE userId = #{userId}")
    int deleteById(@Param("userId") Integer userId);

    // 级联删除：先清除关联数据
    @Delete("DELETE FROM exam_record WHERE userId = #{userId}")
    int deleteRecordsByUserId(@Param("userId") Integer userId);

    @Delete("DELETE FROM reset_request WHERE userId = #{userId}")
    int deleteRequestsByUserId(@Param("userId") Integer userId);

    /** 管理员：按班级分组的学生列表 */
    @Select("SELECT DISTINCT className FROM users WHERE role='student' AND className != '' ORDER BY className")
    List<String> findAllClasses();

    /** 管理员：获取某班级所有学生，含成绩汇总 */
    @Select("SELECT u.userId, u.userName, u.sex, u.email, u.className, " +
            "ex.examId, ex.name as examName, " +
            "COALESCE(SUM(er.score), 0) as totalScore, COUNT(er.recordId) as answerCount, MAX(er.examTime) as lastExamTime " +
            "FROM users u CROSS JOIN exam ex " +
            "LEFT JOIN exam_record er ON er.userId = u.userId AND er.examId = ex.examId " +
            "WHERE u.role = 'student' AND u.className = #{className} " +
            "GROUP BY u.userId, u.userName, u.sex, u.email, u.className, ex.examId, ex.name " +
            "ORDER BY u.userName, ex.name")
    List<Map<String, Object>> findClassStudentsWithScores(@Param("className") String className);
}
