package com.lcl.college.service.ucenter.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: lcl
 * @Date: 2021/2/3 19:05
 * @Version: 1.0
 **/
@Data
public class UpdateVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String nickname;
    private String mobile;
    private String email;
    private String code;
    private String oldPassword;
    private String newPassword;
    private String avatar;
    private Integer sex;
    private Integer age;
    private String sign;

}
