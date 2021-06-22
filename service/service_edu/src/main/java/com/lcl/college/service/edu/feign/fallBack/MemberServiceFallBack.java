package com.lcl.college.service.edu.feign.fallBack;

import com.lcl.college.service.base.dto.MemberDto;
import com.lcl.college.service.edu.feign.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author lcl
 * @date 2021/3/11 13:46
 */

@Service
@Slf4j
public class MemberServiceFallBack implements MemberService {
    @Override
    public MemberDto getMemberById(String memberId) {
        MemberDto memberDto = new MemberDto();
        memberDto.setId(memberId);
        memberDto.setNickname("游客");
        memberDto.setAvatar("https://grad-file.oss-cn-chengdu.aliyuncs.com/avatar/default.jpg");
        return memberDto;
    }
}
