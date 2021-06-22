package com.lcl.college.service.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcl.college.common.base.result.ResultCodeEnum;
import com.lcl.college.common.base.util.*;
import com.lcl.college.service.base.dto.MemberDto;
import com.lcl.college.service.base.exception.CollegeException;
import com.lcl.college.service.ucenter.entity.Member;
import com.lcl.college.service.ucenter.entity.vo.LoginVo;
import com.lcl.college.service.ucenter.entity.vo.MemberQueryVo;
import com.lcl.college.service.ucenter.entity.vo.RegisterVo;
import com.lcl.college.service.ucenter.entity.vo.UpdateVo;
import com.lcl.college.service.ucenter.mapper.MemberMapper;
import com.lcl.college.service.ucenter.service.MemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author lcl
 * @since 2020-07-29
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

 /*   @Autowired
    OssFileService ossFileService;*/
    /**
     * 会员注册
     *
     * @param registerVo
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void register(RegisterVo registerVo) {

        String nickname = registerVo.getNickname();
        String mobile = registerVo.getMobile();
        String email = registerVo.getEmail();
        String password = registerVo.getPassword();
        String code = registerVo.getCode();

        //手机号和邮箱不能同时为空
        if (StringUtils.isEmpty(mobile) && StringUtils.isEmpty(email)) {
            throw new CollegeException(ResultCodeEnum.PARAM_ERROR);
        }
//        if (StringUtils.isEmpty(email)) {
//            throw new CollegeException(ResultCodeEnum.PARAM_ERROR);
//        }

        //优先校验手机，没有手机则校验邮箱
        //注意: 这里需要使用 '|' 要两边都能检测到
        if (!StringUtils.isEmpty(mobile) | FormUtils.isMobile(mobile)) {
            //校验验证码
            String checkCode = redisTemplate.opsForValue().get(mobile);
            if (!code.equals(checkCode)) {
                throw new CollegeException(ResultCodeEnum.CODE_ERROR);
            }
        } else

            if (!StringUtils.isEmpty(email) | FormUtils.isMobile(email)) {
            //校验验证码
            String checkCode = redisTemplate.opsForValue().get(email);
            if (!code.equals(checkCode)) {
                throw new CollegeException(ResultCodeEnum.CODE_ERROR);
            }
        }

        //是否被注册的条件
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(mobile)){
            queryWrapper.lambda().eq(Member::getMobile,mobile);
        }
        if (!StringUtils.isEmpty(email)){
            queryWrapper.lambda().or().eq(Member::getEmail,email);
        }
        Integer count = baseMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new CollegeException(ResultCodeEnum.REGISTER_USERINFO_ERROR);
        }

        //注册
        Member member = new Member();
        member.setNickname(nickname);
        member.setMobile(mobile);
        member.setEmail(email);
        member.setPassword(MD5.encrypt(password));
        member.setDisabled(false);
        //默认头像
        member.setAvatar("https://grad-file.oss-cn-chengdu.aliyuncs.com/avatar/default.jpg");
//        member.setAvatar("https://edu-college.oss-cn-shenzhen.aliyuncs.com/avatar/2020/07/27/20200727205056.jpg");
        baseMapper.insert(member);
    }

    @Override
    public String login(LoginVo loginVo) {

        String userInfo = loginVo.getUserInfo();
        String password = loginVo.getPassword();

        //使用邮箱或者手机号登录
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Member::getMobile, userInfo).or().eq(Member::getEmail, userInfo);
        System.out.println(userInfo);

        Member member = baseMapper.selectOne(queryWrapper);

        if (member == null) {
            throw new CollegeException(ResultCodeEnum.LOGIN_ERROR);
        }

        //校验密码
        if (!MD5.encrypt(password).equals(member.getPassword())) {
            throw new CollegeException(ResultCodeEnum.LOGIN_ERROR);
        }

        //检验用户是否被禁用
        if (member.getDisabled()) {
            throw new CollegeException(ResultCodeEnum.LOGIN_DISABLED_ERROR);
        }

        JwtInfo jwtInfo = new JwtInfo();
        jwtInfo.setId(member.getId());
        jwtInfo.setNickname(member.getNickname());
        jwtInfo.setAvatar(member.getAvatar());
        String jwtToken = JwtUtils.getJwtToken(jwtInfo, 1800);

        //在 redis 中设置token，作为 refresh_token 的依据
        redisUtil.set(member.getId(), jwtToken,60*60*24*10); //10天

        return jwtToken;
    }

    @Override
    public Member getByOpenid(String openid) {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid", openid);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public MemberDto getMemberDtoByMemberId(String memberId) {
        Member member = baseMapper.selectById(memberId);
        MemberDto memberDto = new MemberDto();
        BeanUtils.copyProperties(member, memberDto);
        return memberDto;
    }

    @Override
    public Integer countRegisterNum(String day) {
        return baseMapper.selectRegisterNumByDay(day);
    }





    @Override
    public Page<Member> selectPage(Page<Member> pageParam, MemberQueryVo memberQueryVo) {
        //条件构造器
        QueryWrapper<Member> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("gmt_create");
        //普通的排序分页查询
        if (memberQueryVo == null) {

            return baseMapper.selectPage(pageParam, wrapper);
        }

        //获取对象参数
        String name = memberQueryVo.getNickname();
        Integer sex = memberQueryVo.getSex();
        String joinDateBegin = memberQueryVo.getJoinDateBegin();
        String joinDateEnd = memberQueryVo.getJoinDateEnd();
        
        //条件
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("nickname", name);
        }

        if (!StringUtils.isEmpty(sex)) {
            wrapper.eq("sex", sex);
        }

        if (!StringUtils.isEmpty(joinDateBegin)) {
            wrapper.ge("gmt_create", joinDateBegin);
        }

        if (!StringUtils.isEmpty(joinDateEnd)) {
            wrapper.le("gmt_create", joinDateEnd);
        }
        System.err.println(name+"-----------------------------------");
        System.out.println(baseMapper.selectPage(pageParam, wrapper));
        return baseMapper.selectPage(pageParam, wrapper);
    }

/*    @Override
    public boolean removeAvatarById(String id) {
        //根据ID查询讲师Avatar头像url地址
        Member member = baseMapper.selectById(id);

        if (member != null) {
            String avatar = member.getAvatar();
            if (!StringUtils.isEmpty(avatar)) {
                R r = ossFileService.removeFile(avatar);
                return r.getSuccess();
            }
        }
        return false;
    }*/

    @Override
    public Map<String, Object> selectMemberInfoById(String id) {
        //获取讲师信息
        Member member = baseMapper.selectById(id);
        //根据讲师id获取讲师课程
//        List<Course> courseList = MemberMapper.selectList(new QueryWrapper<Course>().eq("id", id));
        Map<String, Object> map = new HashMap<>();
        map.put("teacher", member);
//        map.put("courseList", courseList);
        return map;
    }

    @Override
    public List<Map<String, Object>> selectNameListByKey(String key) {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("nickname");
        queryWrapper.likeRight("nickname", key);

        return baseMapper.selectMaps(queryWrapper);
    }


    @Override
    public String updateMemberInfo(UpdateVo updateVo) {
/*      String id = updateVo.getId();
        String nickname = updateVo.getNickname();
        Integer age = updateVo.getAge();
        Integer sex = updateVo.getSex();


       *//* QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("password", MD5.encrypt(oldPassword))
                .eq("id", id)
                .eq("mobile", mobile);
        Member member = baseMapper.selectOne(queryWrapper);
        //手机号和旧密码是否正确
        if(member == null){
            throw new CollegeException(28019,"手机号或密码错误");
        }
        //验证码是否过期
        if(redisUtil.get(mobile) == null){
            throw new CollegeException(28029,"请重新获取验证码");
        }
        //获取验证码并校验
        String checkCode = (String)redisUtil.get(mobile);
        if(!code.equals(checkCode)){
            throw new CollegeException(ResultCodeEnum.CODE_ERROR);
        }

        System.out.println(avatar);
        System.out.println(nickname);*//*
        Member newMember = new Member();
//        newMember.setPassword(MD5.encrypt(newPassword));
        newMember.setNickname(nickname);
        newMember.setId(id);
        newMember.setAge(age);
        newMember.setSex(sex);*/



        Member newMember = new Member();
        BeanUtils.copyProperties(updateVo,newMember);

        baseMapper.updateById(newMember);
        //登录，设置有效荷载的用户信息，生成 token
        JwtInfo jwtInfo = new JwtInfo();
        jwtInfo.setId(newMember.getId());
        jwtInfo.setNickname(newMember.getNickname());
        jwtInfo.setAvatar(updateVo.getAvatar());
        String jwtToken = JwtUtils.getJwtToken(jwtInfo, 1800);
        //更新缓存
        redisUtil.set(newMember.getId(), jwtToken,60*60*24*10);
        return jwtToken;
    }

    @Override
    public String updatePassword(UpdateVo updateVo) {
        String id = updateVo.getId();
        String email = updateVo.getEmail();
        String code = updateVo.getCode();
        String oldPassword = updateVo.getOldPassword();
        String newPassword = updateVo.getNewPassword();


        if (StringUtils.isEmpty(email)) {
            throw new CollegeException(ResultCodeEnum.PARAM_ERROR);
        }
        if(oldPassword==null){
            oldPassword="123456";
        }

        if (!StringUtils.isEmpty(email) && FormUtils.isEmail(email)) {
            //校验验证码
            String checkCode = redisTemplate.opsForValue().get(email);
            if (!code.equals(checkCode)) {
                throw new CollegeException(ResultCodeEnum.CODE_ERROR);
            }
        }

        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("password", MD5.encrypt(oldPassword))
                .eq("id", id)
                .eq("email", email);
        Member member = baseMapper.selectOne(queryWrapper);

        //手机号和旧密码是否正确
        if(member == null){
            throw new CollegeException(28019,"邮箱或密码错误");
        }

        //获取验证码并校验
        String checkCode = String.valueOf(redisUtil.get(email));
        if(!code.equals(checkCode)){
            throw new CollegeException(ResultCodeEnum.CODE_ERROR);
        }
        Member newMember = new Member();
        newMember.setEmail(email);
        newMember.setPassword(MD5.encrypt(newPassword));
        newMember.setId(id);

        baseMapper.updateById(newMember);
        //登录，设置有效荷载的用户信息，生成 token
        JwtInfo jwtInfo = new JwtInfo();
        jwtInfo.setId(newMember.getId());
        jwtInfo.setNickname(newMember.getNickname());
        jwtInfo.setAvatar(newMember.getAvatar());
        String jwtToken = JwtUtils.getJwtToken(jwtInfo, 1800);
        //更新缓存
        redisUtil.set(member.getId(), jwtToken,60*60*24*10);
        return jwtToken;
    }

    @Override
    public String updateAvatar(UpdateVo updateVo) {
        String id = updateVo.getId();
        String avatar= updateVo.getAvatar();
        String nickname = updateVo.getNickname();
        Member member = new Member();
        member.setId(id);
        member.setAvatar(avatar);
        member.setNickname(nickname);

        baseMapper.updateById(member);
        //登录，设置有效荷载的用户信息，生成 token
        JwtInfo jwtInfo = new JwtInfo();
        jwtInfo.setId(member.getId());
        jwtInfo.setNickname(member.getNickname());
        jwtInfo.setAvatar(member.getAvatar());
        String jwtToken = JwtUtils.getJwtToken(jwtInfo, 60*30);
        //更新缓存
        redisUtil.set(member.getId(), jwtToken,60*60*24*10);
        return jwtToken;
    }


}
