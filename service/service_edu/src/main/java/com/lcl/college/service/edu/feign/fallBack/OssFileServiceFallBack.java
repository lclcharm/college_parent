package com.lcl.college.service.edu.feign.fallBack;

import com.lcl.college.common.base.result.R;
import com.lcl.college.service.edu.feign.OssFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author lcl
 * @date 2020/7/5 15:25
 **/
@Service
@Slf4j
public class OssFileServiceFallBack implements OssFileService {

    @Override
    public R removeFile(String url) {
        log.info("熔断保护");
        return R.error();
    }
}
