package com.lcl.college.service.edu.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("edu_question_comment")
@ApiModel(value="问答评论对象", description="问答评论对象")
public class QuestionComment extends BaseEntity {
    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "评论人id")
    private String cusId;
    @ApiModelProperty(value = "问答id")
    private String questionId;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "是否最佳答案 0否1是")
    @TableField("is_best")
    private Integer best;

    @ApiModelProperty(value = "回复数量")
    private Integer replyCount;
    @ApiModelProperty(value = "点赞数量")
    private Integer praiseCount;

    @ApiModelProperty(value = "父级评论id")
    private String parentId;

}
