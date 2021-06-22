package com.lcl.college.service.edu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 文章内容
 * @author www.inxedu.com
 */

@Data

@TableName("edu_article_content")
@ApiModel(value="文章内容对象", description="文章内容")
public class ArticleContent{
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "文章id")
	private String id;
	/**文章对应的内容*/
	@ApiModelProperty(value = "文章对应的内容")
	private String content;
}
