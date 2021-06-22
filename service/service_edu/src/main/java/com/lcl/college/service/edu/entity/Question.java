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
@TableName("edu_question")
@ApiModel(value="问答对象", description="问答对象")
public class Question extends BaseEntity {
    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "创建人id")
    private String cusId;
    /**文章标题*/
    @ApiModelProperty(value = "问答标题")
    private String title;


    @ApiModelProperty(value = "问答内容")
    private String content;

    @ApiModelProperty(value = "问答类型 1课程问答 2学习分享")
    private Integer type;
    @ApiModelProperty(value = "状态 0可回复1不可回复（采纳最佳答案后改为1 ）")
    private Integer status;

    @ApiModelProperty(value = "回复数量")
    private Integer replyCount;
    @ApiModelProperty(value = "问答浏览数量")
    private Integer browseCount;

    @ApiModelProperty(value = "点赞数量")
    private Integer praiseCount;

}
