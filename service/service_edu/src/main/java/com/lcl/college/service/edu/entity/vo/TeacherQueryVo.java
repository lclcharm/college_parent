package com.lcl.college.service.edu.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lcl
 * @date 2020/6/24 21:47
 **/
@Data
public class TeacherQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private Integer level;
    private String joinDateBegin;
    private String joinDateEnd;
}
