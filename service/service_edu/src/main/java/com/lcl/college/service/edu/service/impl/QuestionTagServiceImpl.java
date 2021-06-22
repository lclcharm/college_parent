package com.lcl.college.service.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcl.college.service.edu.mapper.QuestionTagMapper;
import com.lcl.college.service.edu.service.QuestionTagService;
import com.lcl.college.service.edu.entity.QuestionTag;
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
public class QuestionTagServiceImpl extends ServiceImpl<QuestionTagMapper, QuestionTag> implements QuestionTagService {


    @Override
    public List<QuestionTag> getTagIdsByQuestionId(String id) {
        List<String> tagIdsByQuestionId = baseMapper.getTagIdsByQuestionId(id);
        List<QuestionTag> questionTags=null;
        if(tagIdsByQuestionId!=null&&tagIdsByQuestionId.size()!=0){
            questionTags = baseMapper.selectBatchIds(tagIdsByQuestionId);
        }

        return questionTags;
    }
}
