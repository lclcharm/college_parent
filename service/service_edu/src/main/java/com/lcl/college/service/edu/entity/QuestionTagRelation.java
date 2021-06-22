package com.lcl.college.service.edu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lcl.college.service.base.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author lcl
 * @date 2021/3/8 21:51
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("edu_question_tag_relation")
@ApiModel(value="问答标签关系对象", description="问答标签关系对象")
public class QuestionTagRelation extends BaseEntity {
    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "问答id")
    private String questionId;

    @ApiModelProperty(value = "问答标签id")
    private String questionTagId;


}
