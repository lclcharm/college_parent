package com.lcl.college.service.edu.entity.vo;

import com.lcl.college.service.base.model.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author lcl
 * @date 2021/3/9 11:32
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ArticleVo extends BaseEntity {

    /**文章标题*/
    private String title;
    /**文章摘要*/
    private String summary;//
    /**文章关键字*/
    @ApiModelProperty(value = "文章关键字")
    private String keyWord;
    /**文章图片URL*/
    private String imageUrl;
    /**文章来源*/
    private String source;
    /**文章作者*/
    private String author;
    /**文章类型*/
    private Integer articleType;
//    /**文章点击量*/
    private Integer clickNum;
//    /**文章点赞量*/
    private Integer praiseCount;
    /** 排序值 */
    private Integer sort;
/*文章对应的内容*/
    private String content;
    /*评论数量*/
    private Integer commentNum;

}
