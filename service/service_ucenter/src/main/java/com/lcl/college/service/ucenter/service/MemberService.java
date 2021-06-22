package com.lcl.college.service.ucenter.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lcl.college.service.base.dto.MemberDto;
import com.lcl.college.service.ucenter.entity.vo.UpdateVo;
import com.lcl.college.service.ucenter.entity.Member;
import com.lcl.college.service.ucenter.entity.vo.LoginVo;
import com.lcl.college.service.ucenter.entity.vo.MemberQueryVo;
import com.lcl.college.service.ucenter.entity.vo.RegisterVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author lcl
 * @since 2020-07-29
 */
public interface MemberService extends IService<Member> {
    void register(RegisterVo registerVo);

    String  login(LoginVo loginVo);

    /**
     * 根据openid返回用户信息
     * @param openid
     * @return
     */
    Member getByOpenid(String openid);

    MemberDto getMemberDtoByMemberId(String memberId);

    Integer countRegisterNum(String day);

    Page<Member> selectPage(Page<Member> pageParam, MemberQueryVo memberQueryVo);

    /**
     * 根据用户id删除头像
     *
     * @param id
     * @return
     */

//    boolean removeAvatarById(String id);


    Map<String, Object> selectMemberInfoById(String id);

    List<Map<String, Object>> selectNameListByKey(String key);

    public String updatePassword(UpdateVo updateVo);
    String updateMemberInfo(UpdateVo updateVo);

    String updateAvatar(UpdateVo updateVo);
}
