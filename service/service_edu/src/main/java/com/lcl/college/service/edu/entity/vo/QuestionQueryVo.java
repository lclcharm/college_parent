package com.lcl.college.service.edu.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lcl
 * @date 2020/6/24 21:47
 **/
@Data
public class QuestionQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;


    private String title;
    private Integer status;
    private Integer type;//分类0全部 1课程问答 2 学习分享
    private Integer orderby;//分类 0 根据时间倒排 1 根据点击量排序

}
