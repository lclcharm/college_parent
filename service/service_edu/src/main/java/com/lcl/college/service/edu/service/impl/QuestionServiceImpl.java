package com.lcl.college.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcl.college.service.edu.entity.vo.QuestionQueryVo;
import com.lcl.college.service.edu.mapper.QuestionMapper;
import com.lcl.college.service.edu.service.QuestionService;
import com.lcl.college.service.edu.entity.Question;
import com.lcl.college.service.edu.entity.QuestionTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public List<Question> queryHotQuestions() {
        QueryWrapper<Question> questionQueryWrapper = new QueryWrapper<>();
        questionQueryWrapper.orderByDesc("BROWSE_COUNT");
        questionQueryWrapper.last("limit 0,10");
        return baseMapper.selectList(questionQueryWrapper);
    }

    @Override
    public Page<Question> getQuestionList(Long page, Long limit, QuestionQueryVo questionQueryVo) {
        Page<Question> pageParam = new Page<>(page, limit);
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        Integer status = questionQueryVo.getStatus();
        Integer type = questionQueryVo.getType();
        Integer orderby = questionQueryVo.getOrderby();

        if (!StringUtils.isEmpty(type)) {
            if (type == 1 || type == 2)
                queryWrapper.eq("type", type);
        }

        if (!StringUtils.isEmpty(orderby)) {
            if(orderby==1)
                queryWrapper.orderByDesc("BROWSE_COUNT","gmt_create");
            else
                queryWrapper.orderByDesc("gmt_create");
        }
        if (!StringUtils.isEmpty(status)) {
            if(status==0)
                queryWrapper.eq("status",0);
        }
//        queryWrapper.orderByDesc("gmt_create");
        return baseMapper.selectPage(pageParam, queryWrapper);
    }

    @Override
    public Question getQuestionById(String id) {
        //更新浏览量
        Question question = baseMapper.selectById(id);
        question.setBrowseCount(question.getBrowseCount() + 1);
        
        baseMapper.updateById(question);
        System.err.println("------咿呀呀"+question.getBrowseCount());
        return baseMapper.selectById(id);
    }

    @Override
    public boolean saveRelationByQuestionId(String questionId,List<QuestionTag> questionTags) {
        for (QuestionTag questionTag : questionTags) {
            questionMapper.insertByQuestionId(questionId, questionTag.getId());
        }

        return false;
    }

    @Override
    public List<Question> getQuestionsByMemberId(String id) {
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cus_id", id);

        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public Page<Question> getQuestionListByMemberId(Long page, Long limit, String id) {

        Page<Question> pageParam = new Page<>(page, limit);
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("cus_id", id);

        queryWrapper.orderByDesc("gmt_create");

        return baseMapper.selectPage(pageParam, queryWrapper);

    }
}
