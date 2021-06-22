package com.lcl.college.service.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lcl.college.service.edu.entity.Question;
import com.lcl.college.service.edu.entity.QuestionComment;

import java.util.List;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author lcl
 * @since 2020-06-23
 */
public interface QuestionCommentService extends IService<QuestionComment> {

    QuestionComment getBestComment(Question question);
    List<QuestionComment>  getQuestionCommentByQuestionId(String id);

    void addQuestionComment(QuestionComment questionComment);

    List<QuestionComment> getQuestionCommentsByMemberId(String id);
    boolean setBestAnswer(QuestionComment questionComment);
}
