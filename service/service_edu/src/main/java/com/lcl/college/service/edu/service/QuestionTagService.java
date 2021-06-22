package com.lcl.college.service.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
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
public interface QuestionTagService extends IService<QuestionTag> {

    List<QuestionTag> getTagIdsByQuestionId(String id);
}
