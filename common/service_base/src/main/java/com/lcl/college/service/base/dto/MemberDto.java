package com.lcl.college.service.base.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lcl
 * @date 2020/8/2 22:42
 **/
@Data
public class MemberDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;//会员id
    private String mobile;//手机号
    private String nickname;//昵称
    private String avatar;
}
