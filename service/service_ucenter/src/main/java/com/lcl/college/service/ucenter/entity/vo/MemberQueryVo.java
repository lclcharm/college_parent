package com.lcl.college.service.ucenter.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lcl
 * @date 2021/3/9 17:08
 */
@Data
public class MemberQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nickname;
    private Integer sex;
    private String joinDateBegin;
    private String joinDateEnd;

}
