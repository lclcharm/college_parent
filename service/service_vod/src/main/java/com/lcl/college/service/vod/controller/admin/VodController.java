package com.lcl.college.service.vod.controller.admin;

import com.lcl.college.common.base.result.R;
import com.lcl.college.common.base.result.ResultCodeEnum;
import com.lcl.college.common.base.util.ExceptionUtils;
import com.lcl.college.service.base.exception.CollegeException;
import com.lcl.college.service.vod.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @Author: lcl
 * @Date: 2021/1/22 16:38
 * @Version: 1.0
 **/
@Api(tags = "阿里云vod管理")
@RestController
@RequestMapping("/admin/vod/file")
@Slf4j
public class VodController {

    @Autowired
    private VodService vodService;

    @ApiOperation("视频上传")
    @PostMapping("upload")
    public R uploadVideo(@RequestParam("file")MultipartFile file){
        try {
            String fileName = file.getOriginalFilename();
            InputStream inputStream = file.getInputStream();
            String videoId = vodService.uploadVideo(fileName, inputStream);
            return R.ok().data("videoId", videoId).message("上传视频成功");
        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new CollegeException(ResultCodeEnum.VIDEO_UPLOAD_TOMCAT_ERROR);
        }
    }

    @ApiOperation("视频删除")
    @DeleteMapping("delete/{id}")
    public R deleteVideo(@PathVariable("id") String vodId){
        try {
            vodService.deleteVideo(vodId);
            return R.ok().message("删除视频成功");
        }catch (Exception e){
            log.error(ExceptionUtils.getMessage(e));
            throw new CollegeException(ResultCodeEnum.VIDEO_DELETE_ALIYUN_ERROR);
        }
    }
}
