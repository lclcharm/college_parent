package com.lcl.college.service.edu.feign;

import com.lcl.college.service.base.dto.MemberDto;
import com.lcl.college.service.edu.feign.fallBack.MemberServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author lcl
 * @date 2020/7/3 23:38
 **/
@Service
@FeignClient(value = "service-ucenter",fallback = MemberServiceFallBack.class)
public interface MemberService {

    @GetMapping("/api/ucenter/member/inner/get-member-dto/{memberId}")
    MemberDto getMemberById(@PathVariable("memberId") String memberId);

}
