package com.lcl.college.service.cms.service;

import com.aliyuncs.exceptions.ClientException;

/**
 * @author lcl
 * @date 2020/7/29 18:44
 **/
public interface SmsService {
    void send(String mobile, String checkCode) throws ClientException;
}
