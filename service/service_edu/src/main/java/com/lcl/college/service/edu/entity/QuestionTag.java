package com.lcl.college.service.edu.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
@TableName("edu_question_tag")
@ApiModel(value="问答对象", description="问答对象")
public class QuestionTag extends BaseEntity {
    private static final long serialVersionUID=1L;

    /**文章标题*/
    @ApiModelProperty(value = "标签名")
    private String questionTagName;

    @ApiModelProperty(value = "状态0默认1删除")
    private Integer status;
  @ApiModelProperty(value = "父级标签id")
    private Integer parentId;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;


}
