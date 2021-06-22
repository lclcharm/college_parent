/*
package com.lcl.college.service.vod.service.impl;

import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.vod.v20180717.VodClient;
import com.tencentcloudapi.vod.v20180717.models.DeleteMediaRequest;
import ResultCodeEnum;
import CollegeException;
import com.lcl.college.service.vod.service.VideoService;
import VodProperties;
import VodSDKUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

*/
/**
 * @author lcl
 * @date 2020/7/18 17:13
 **//*

@Service
@Slf4j
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VodProperties vodProperties;

    @Override
    public void removeVideo(String videoId) {
        // 初始化腾讯云vod实例
        VodClient vodClient = VodSDKUtils.initVodClient(vodProperties.getSecretId(), vodProperties.getSecretKey());
        // 删除的对象
        DeleteMediaRequest request = new DeleteMediaRequest();
        request.setFileId(videoId);
        try {
            vodClient.DeleteMedia(request);
        } catch (TencentCloudSDKException e) {
            throw new CollegeException(ResultCodeEnum.VIDEO_DELETE_TENCENT_ERROR);
        }
    }

    @Override
    public void removeVideoByIdList(List<String> videoIdList) {

        // 初始化腾讯云vod实例
        VodClient vodClient = VodSDKUtils.initVodClient(vodProperties.getSecretId(), vodProperties.getSecretKey());
        // 删除的对象
        DeleteMediaRequest request = new DeleteMediaRequest();
        videoIdList.forEach(id -> {
            request.setFileId(id);
            try {
                vodClient.DeleteMedia(request);
            } catch (TencentCloudSDKException e) {
                throw new CollegeException(ResultCodeEnum.VIDEO_DELETE_TENCENT_ERROR);
            }
        });
    }

}
*/
