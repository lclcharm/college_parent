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
@TableName("edu_article")
@ApiModel(value="文章对象", description="文章对象")
public class Article extends BaseEntity {
    private static final long serialVersionUID=1L;

    /**文章标题*/
    @ApiModelProperty(value = "文章标题")
    private String title;
    /**文章摘要*/
    @ApiModelProperty(value = "文章摘要")
    private String summary;//
    /**文章关键字*/
    @ApiModelProperty(value = "文章关键字")
    private String keyWord;
    /**文章图片URL*/
    @ApiModelProperty(value = "文章图片URL")
    private String imageUrl;
    /**文章来源*/
    @ApiModelProperty(value = "文章来源")
    private String source;
    /**文章作者*/
    @ApiModelProperty(value = "文章作者")
    private String author;

    /**文章类型*/
    @ApiModelProperty(value = "文章类型")
    private Integer articleType;
    /**文章点击量*/
    @ApiModelProperty(value = "文章点击量")
    private Integer clickNum;
    /**文章点赞量*/
    @ApiModelProperty(value = "文章点赞数")
    private Integer praiseCount;
    /** 排序值 */
    @ApiModelProperty(value = "排序值")
    private Integer sort;

    /**文章评论数*/
    @ApiModelProperty(value = "文章评论数")
    private Integer commentNum;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;

}
