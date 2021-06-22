package com.lcl.college.service.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lcl.college.service.edu.entity.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 问题 Mapper 接口
 * </p>
 *
 * @author lcl
 * @since 2021-06-23
 */
@Repository
public interface QuestionMapper extends BaseMapper<Question> {
    @Insert("insert into edu_question_tag_relation(QUESTION_ID,QUESTION_TAG_ID) values(#{questionId},#{id})")
    boolean insertByQuestionId(@Param("questionId") String questionId,@Param("id") String id);
}
