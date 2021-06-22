package com.lcl.college.service.cms.feign.impl;

import com.lcl.college.common.base.result.R;
import com.lcl.college.service.cms.feign.OssFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author lcl
 * @date 2020/7/28 17:33
 **/
@Service
@Slf4j
public class OssFileServiceFallBack implements OssFileService {

    @Override
    public R removeFile(String url) {
        log.info("熔断保护");
        return R.error().message("调用超时");
    }
}
