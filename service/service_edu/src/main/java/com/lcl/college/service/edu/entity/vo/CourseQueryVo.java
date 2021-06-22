package com.lcl.college.service.edu.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lcl
 * @date 2020/7/14 15:02
 **/
@Data
public class CourseQueryVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private String teacherId;
    private String subjectParentId;
    private String subjectId;
}
