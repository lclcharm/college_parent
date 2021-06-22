package com.lcl.college.service.vod.util;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.vod.v20180717.VodClient;

/**
 * @author lcl
 * @date 2020/7/21 22:48
 **/
public class VodSDKUtils {

    public static VodClient initVodClient(String secretId, String secretKey) {
        Credential cred = new Credential(secretId, secretKey);
        VodClient vodClient = new VodClient(cred, "ap-chongqing");
        return vodClient;
    }
}
