package com.lcl.college.service.edu.feign;

import com.lcl.college.common.base.result.R;
import com.lcl.college.service.edu.feign.fallBack.OssFileServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author lcl
 * @date 2020/7/3 23:38
 **/
@Service
@FeignClient(value = "service-oss",fallback = OssFileServiceFallBack.class)
public interface OssFileService {

    @DeleteMapping("/admin/oss/file/remove")
    R removeFile(@RequestBody String url);

}
