package com.lcl.college.service.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lcl.college.service.edu.entity.vo.QuestionQueryVo;
import com.lcl.college.service.edu.entity.Question;
import com.lcl.college.service.edu.entity.QuestionTag;

import java.util.List;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author lcl
 * @since 2020-06-23
 */
public interface QuestionService extends IService<Question> {

    List<Question> queryHotQuestions();

    Page<Question> getQuestionList(Long page, Long limit, QuestionQueryVo questionQueryVo);

    Question getQuestionById(String id);

    boolean saveRelationByQuestionId(String questionId,List<QuestionTag> questionTags);


    List<Question> getQuestionsByMemberId(String id);

    Page<Question> getQuestionListByMemberId(Long page, Long limit, String id);
}
