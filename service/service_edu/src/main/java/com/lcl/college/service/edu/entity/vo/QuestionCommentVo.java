package com.lcl.college.service.edu.entity.vo;

import lombok.Data;

/**
 * @author lcl
 * @date 2021/3/11 12:01
 */

@Data
public class QuestionCommentVo {

    private String id;
    private String cusId;
    private String questionId;

    private String content;

    private Integer best;

    private Integer replyCount;
    private Integer praiseCount;
    private String parentId;

    private String gmtCreate;
    private String gmtModified;

    private String cusName;
    private String cusAvatar;
}
