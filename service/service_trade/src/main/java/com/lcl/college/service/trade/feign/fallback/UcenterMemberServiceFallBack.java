package com.lcl.college.service.trade.feign.fallback;

import com.lcl.college.service.base.dto.MemberDto;
import com.lcl.college.service.trade.feign.UcenterMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author lcl
 * @date 2020/8/4 12:30
 **/
@Service
@Slf4j
public class UcenterMemberServiceFallBack implements UcenterMemberService {
    @Override
    public MemberDto getMemberDtoByMemberId(String memberId) {
        log.info("熔断保护");
        return null;
    }
}
