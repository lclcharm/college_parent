package com.lcl.college.service.edu.feign.fallBack;

import com.lcl.college.common.base.result.R;
import com.lcl.college.service.edu.feign.VodMediaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lcl
 * @date 2020/7/22 0:30
 **/
@Service
@Slf4j
public class VodMediaServiceFallBack implements VodMediaService {
    @Override
    public R removeVideo(String vodId) {
        log.info("熔断保护");
        return R.error();
    }

    @Override
    public R removeVideoByIdList(List<String> videoIdList) {
        log.info("熔断保护");
        return R.error().message("调用超时");
    }
}
