package com.lcl.college.service.cms.util;

import com.alibaba.fastjson.JSONException;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;


/**
 * @author lcl
 * @date 2021/4/7 15:57
 */
@Component
public class TxCloudSmsUtil {

    @Autowired
    SmsProperties smsProperties;

    /**
     * 指定模板 ID 单发短信
     */
    public String sendSms(String mobile,String code) {
        String rep = "error";
        try {
            // 数组具体的元素个数和模板中变量个数必须一致，例如示例中templateId:5678对应一个变量，参数数组中元素个数也必须是一个
            String[] params = {code};
            SmsSingleSender smsSingleSender = new SmsSingleSender(smsProperties.getAppId(), smsProperties.getAppKey());
            // 签名参数未提供或者为空时，会使用默认签名发送短信
            SmsSingleSenderResult smsSingleSenderResult = smsSingleSender.sendWithParam("86",mobile,
                    smsProperties.getTemplateId(), params, smsProperties.getSmsSign(), "", "");
            System.out.println(smsSingleSenderResult);
            // 如果返回码不是0，说明配置有错，返回错误信息
            if (smsSingleSenderResult.result == 0) {
                rep = "success";
            } else {
                rep = smsSingleSenderResult.errMsg;
            }
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }catch (Exception e) {
            // 网络IO错误
            e.printStackTrace();
        }
        return rep;
    }
}
