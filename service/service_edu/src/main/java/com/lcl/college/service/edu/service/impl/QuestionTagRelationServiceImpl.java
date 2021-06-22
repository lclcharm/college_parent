package com.lcl.college.service.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcl.college.service.edu.entity.QuestionTagRelation;
import com.lcl.college.service.edu.mapper.QuestionTagRelationMapper;
import com.lcl.college.service.edu.service.QuestionTagRelationService;
import org.springframework.stereotype.Service;

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
public class QuestionTagRelationServiceImpl extends ServiceImpl<QuestionTagRelationMapper, QuestionTagRelation> implements QuestionTagRelationService {


    @Override
    public boolean insertQuestionTagRelation(List<QuestionTagRelation> questionTagRelationList) {
        boolean b = false;
        int insert = 0;

        for (QuestionTagRelation questionTagRelation : questionTagRelationList) {
            insert = baseMapper.insert(questionTagRelation);
        }
        if (insert>0){
            return true;
        }

            return false;
    }
}
