package com.lcl.college.service.edu.entity.vo;

import com.lcl.college.service.edu.entity.QuestionComment;
import com.lcl.college.service.edu.entity.QuestionTag;
import lombok.Data;

import java.util.List;

/**
 * @author lcl
 * @date 2021/3/11 12:01
 */

@Data
public class QuestionVo {

    private String id;
    private String cusId;       //评论人的id
    private String title;
    private String content;
    private Integer type;
    private Integer status;
    private Integer replyCount;
    private Integer browseCount;

    private Integer praiseCount;

    private String gmtCreate;
    private String gmtModified;

    private String cusName;
    private String cusAvatar;
    private QuestionComment questionComment;//问答评论
    private List<QuestionComment> questionComments;//问答评论list
    private List<QuestionTag> questionTags;//问答和问答标签关联list

    private List<QuestionCommentVo> questionCommentVos;
}
