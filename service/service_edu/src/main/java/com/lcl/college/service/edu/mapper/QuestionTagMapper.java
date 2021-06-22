package com.lcl.college.service.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lcl.college.service.edu.entity.QuestionTag;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 讲师 Mapper 接口
 * </p>
 *
 * @author lcl
 * @since 2020-06-23
 */
@Repository
public interface QuestionTagMapper extends BaseMapper<QuestionTag> {
    @Select("select question_tag_id from edu_question_tag_relation where question_id =#{id}")
    List<String> getTagIdsByQuestionId(@Param("id") String id);
}
