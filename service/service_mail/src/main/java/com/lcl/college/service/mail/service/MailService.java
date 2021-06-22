package com.lcl.college.service.mail.service;

/**
 * @author lcl
 * @date 2021/2/19 17:04
 **/
public interface MailService {
    void sendCode(String email, String subject, String checkCode, String template) throws Exception;
}
