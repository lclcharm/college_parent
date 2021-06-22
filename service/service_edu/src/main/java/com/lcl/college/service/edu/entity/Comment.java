package com.lcl.college.service.edu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lcl.college.service.base.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 评论
 * </p>
 *
 * @author lcl
 * @since 2020-06-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("edu_comment")
@ApiModel(value="Comment对象", description="评论")
public class Comment extends BaseEntity {

    private static final long serialVersionUID=1L;



    @ApiModelProperty(value = "会员id")
    private String memberId;

    @ApiModelProperty(value = "父级id")
    private String parentId;

    @ApiModelProperty(value = "评论目标id")
    private String targetId;

    @ApiModelProperty(value = "会员昵称")
    private String nickname;

    @ApiModelProperty(value = "会员头像")
    private String avatar;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "点赞数量")
    private Integer praiseCount;
    @ApiModelProperty(value = "回复数量")
    private Integer replyCount;

    @ApiModelProperty(value = "评论类型 1文章 2课程")
    private Integer type;


}
