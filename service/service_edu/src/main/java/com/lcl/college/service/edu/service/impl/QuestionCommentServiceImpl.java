package com.lcl.college.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcl.college.service.edu.entity.Question;
import com.lcl.college.service.edu.entity.QuestionComment;
import com.lcl.college.service.edu.mapper.QuestionCommentMapper;
import com.lcl.college.service.edu.mapper.QuestionMapper;
import com.lcl.college.service.edu.service.QuestionCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * <p>
 * 问答实现
 * </p>
 *
 * @author lcl
 * @since 2020-06-23
 */
@Service
public class QuestionCommentServiceImpl extends ServiceImpl<QuestionCommentMapper, QuestionComment> implements QuestionCommentService {

    @Autowired
    QuestionMapper questionMapper;

    @Override
    public QuestionComment getBestComment(Question question) {

        QueryWrapper<QuestionComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("QUESTION_ID", question.getId());
        if (question.getStatus() == 1) {
            queryWrapper.eq("is_best", 1);
        }
        queryWrapper.orderByDesc("gmt_create");
        queryWrapper.last("limit 0,1");
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public List<QuestionComment> getQuestionCommentByQuestionId(String id) {

        QueryWrapper<QuestionComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("question_id", id);
        queryWrapper.orderByDesc("gmt_create");
        List<QuestionComment> questionComments = baseMapper.selectList(queryWrapper);

        return questionComments;
    }

    @Override
    @Transactional
    public void addQuestionComment(QuestionComment questionComment) {

        baseMapper.insert(questionComment);
        Question question = questionMapper.selectById(questionComment.getQuestionId());
        question.setReplyCount(question.getReplyCount() + 1);
        questionMapper.updateById(question);
    }

    @Override
    @Transactional
    public List<QuestionComment> getQuestionCommentsByMemberId(String id) {
        QueryWrapper<QuestionComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cus_id", id);

        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public boolean setBestAnswer(QuestionComment questionComment) {

        QuestionComment questionComment1 = baseMapper.selectById(questionComment.getId());
        int best = questionComment1.getBest();
        if (best == 0) {
            questionComment1.setBest(1);
        } else {
            questionComment1.setBest(0);
        }
        int i = baseMapper.updateById(questionComment1);
        if (i > 0)
            return true;
        else
            return false;
    }


}
