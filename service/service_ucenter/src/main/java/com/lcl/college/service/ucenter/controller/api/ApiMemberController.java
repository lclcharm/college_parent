package com.lcl.college.service.ucenter.controller.api;

import com.lcl.college.common.base.result.R;
import com.lcl.college.common.base.result.ResultCodeEnum;
import com.lcl.college.common.base.util.JwtInfo;
import com.lcl.college.common.base.util.JwtUtils;
import com.lcl.college.common.base.util.RedisUtil;
import com.lcl.college.service.base.dto.MemberDto;
import com.lcl.college.service.base.exception.CollegeException;
import com.lcl.college.service.ucenter.entity.vo.UpdateVo;
import com.lcl.college.service.ucenter.entity.Member;
import com.lcl.college.service.ucenter.entity.vo.LoginVo;
import com.lcl.college.service.ucenter.entity.vo.RegisterVo;
import com.lcl.college.service.ucenter.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lcl
 * @date 2020/7/29 19:42
 **/
@Api(tags = "会员管理")
@RestController
@RequestMapping("/api/ucenter/member")
@Slf4j
public class ApiMemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation(value = "会员注册")
    @PostMapping("register")
    public R register(@Validated @RequestBody RegisterVo registerVo) {
        memberService.register(registerVo);
        return R.ok();
    }

    @ApiOperation(value = "会员登录")
    @PostMapping("login")
    public R login(@RequestBody LoginVo loginVo) {
        String token = memberService.login(loginVo);
        System.err.println("login:"+token);
        return R.ok().data("token", token);
    }

    @ApiOperation(value = "根据token获取登录信息")
    @GetMapping("get-login-info")
    public R getLoginInfo(HttpServletRequest request) {
        String jwtToken = request.getHeader("token");
        if(StringUtils.isEmpty(jwtToken)){
            return R.error().message("请登录");
        }
        System.err.println("jwtToken:"+jwtToken);
        try {
//            String token = JwtUtils.checkTokenExpireTimeAndGetNew(jwtToken, redisUtil);

            JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);

//            System.err.println("get-login-info"+token);
//            return R.ok().data("userInfo", jwtInfo).data("token",token);
            
            System.out.println("get-login-info::userInfo"+jwtInfo);
            return R.ok().data("userInfo", jwtInfo);
        } catch (Exception e) {
            log.error("解析用户信息失败，" + e.getMessage());
            throw new CollegeException(ResultCodeEnum.FETCH_USERINFO_ERROR);
        }
    }

    @ApiOperation("根据会员id查询会员信息")
    @GetMapping("inner/get-member-dto/{memberId}")
    public MemberDto getMemberDtoByMemberId(
            @ApiParam(value = "会员ID", required = true)
            @PathVariable String memberId) {
        MemberDto memberDto = memberService.getMemberDtoByMemberId(memberId);

        return memberDto;
    }

    @ApiOperation("根据会员id查询用戶所有信息")
    @GetMapping("inner/get-member/{id}")
    public R getMemberById(
            @ApiParam(value = "会员ID", required = true)
            @PathVariable String id) {
        System.err.println(id);
        Member member = memberService.getById(id);
        System.err.println(member);
        return R.ok().data("member",member);
    }

    @ApiOperation("更新用户信息")
    @PostMapping("auth/update-member")
    public R updateMember(@RequestBody UpdateVo updateVo){

        String jwt = memberService.updateMemberInfo(updateVo);
        System.err.println("jwtToken:"+jwt);
        return R.ok().data("token", jwt).message("更新信息成功");
    }
    @ApiOperation("更新用户头像")
    @PostMapping("auth/update-avatar")
    public R changeAvatar(@RequestBody UpdateVo updateVo){
        String jwt = memberService.updateAvatar(updateVo);
        return R.ok().data("token", jwt).message("更新头像成功");
    }

    @ApiOperation("更新用户密码")
    @PostMapping("auth/update-password")
    public R updatePassword(@RequestBody UpdateVo updateVo){
        String jwt = memberService.updatePassword(updateVo);
        return R.ok().data("token", jwt);
    }


}
