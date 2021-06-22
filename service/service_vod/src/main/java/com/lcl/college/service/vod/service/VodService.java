package com.lcl.college.service.vod.service;

import java.io.InputStream;
import java.util.List;

/**
 * @Author: lcl
 * @Date: 2021/1/22 15:16
 * @Version: 1.0
 **/
public interface VodService {

    /**
     * 上传视频
     * @param originalFileName：上传文件的原始名字
     * @param inputStream：文件流
     * @return ：存储在 vod 后的 videoId
     */
    String uploadVideo(String originalFileName, InputStream inputStream);

    /**
     * 删除 vod 中的视频
     * @param vodId：videoId
     * @return ：删除是否成功
     */
    boolean deleteVideo(String vodId);

    /**
     * 获得播放地址
     * @param vodId：播放路径 id
     * @return ：播放地址
     */
    List<String> getPlayUrl(String vodId) throws Exception;
}
