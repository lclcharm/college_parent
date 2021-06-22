package com.lcl.college.service.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lcl.college.service.edu.entity.QuestionTagRelation;

import java.util.List;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author lcl
 * @since 2020-06-23
 */
public interface QuestionTagRelationService extends IService<QuestionTagRelation> {

    boolean insertQuestionTagRelation(List<QuestionTagRelation> questionTagRelationList);
}
