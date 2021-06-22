package com.lcl.college.service.vod.controller.api;

import com.lcl.college.common.base.result.R;
import com.lcl.college.common.base.result.ResultCodeEnum;
import com.lcl.college.common.base.util.ExceptionUtils;
import com.lcl.college.service.base.exception.CollegeException;
import com.lcl.college.service.vod.service.VodService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: lcl
 * @Date: 2021/1/26 21:46
 * @Version: 1.0
 **/
@Api("视频点播")
@RestController
@RequestMapping("/api/vod/media")
@Slf4j
public class ApiVodController {

    @Autowired
    private VodService vodService;

    @GetMapping("get-play-url/{vodId}")
    public R getPlayUrl(@PathVariable("vodId") String vodId){
        try {
            List<String> urls = vodService.getPlayUrl(vodId);
            return R.ok().data("urls", urls);
        }catch (Exception e){
            log.error(ExceptionUtils.getMessage(e));
            throw new CollegeException(ResultCodeEnum.FETCH_PLAYURL_ERROR);
        }
    }

}
