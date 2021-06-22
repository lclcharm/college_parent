package com.lcl.college.service.ucenter.controller.admin;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lcl.college.common.base.result.R;
import com.lcl.college.service.base.dto.MemberDto;
import com.lcl.college.service.ucenter.entity.Member;
import com.lcl.college.service.ucenter.entity.vo.MemberQueryVo;
import com.lcl.college.service.ucenter.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author lcl
 * @date 2020/8/5 0:36
 **/
@Api(tags = "用户管理")
@RestController
@RequestMapping("/admin/ucenter/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @ApiOperation(value = "根据日期统计注册人数")
    @GetMapping(value = "count-register-num/{day}")
    public R countRegisterNum(
            @ApiParam(name = "day", value = "统计日期")
            @PathVariable String day) {
        Integer num = memberService.countRegisterNum(day);
        return R.ok().data("registerNum", num);
    }


    @ApiOperation("所有用户列表")
    @GetMapping("list")
    public R listAll() {
        return R.ok().data("items", memberService.list());
    }

    @ApiOperation(value = "根据ID删除用户", notes = "根据ID删除用户，逻辑删除")
    @DeleteMapping("remove/{id}")
    public R removeById(@ApiParam(value = "用户ID", required = true) @PathVariable String id) {
        //删除用户头像
//        memberService.removeAvatarById(id);

        //删除用户
        boolean b = memberService.removeById(id);
        if (b) {
            return R.ok().message("删除成功");
        } else {
            return R.error().message("数据不存在");
        }
    }

    @ApiOperation(value = "根据ID批量删除用户")
    @DeleteMapping("batch-remove")
    public R removeRows(
            @ApiParam(value = "用户ID", required = true) @RequestBody List<String> idList) {

        boolean result = memberService.removeByIds(idList);
        if (result) {
            return R.ok().message("删除成功");
        } else {
            return R.error().message("删除失败");
        }
    }

    @ApiOperation("分页查询用户列表")
    @GetMapping("list-page/{page}/{limit}")
    public R listPage(@ApiParam(value = "当前页码", required = true) @PathVariable Long page,
                      @ApiParam(value = "每页记录数", required = true) @PathVariable Long limit,
                      @ApiParam(value = "查询对象条件") MemberQueryVo memberQueryVo
    ) {
        Page<Member> pageParam = new Page<>(page, limit);
        Page<Member> memberPage = memberService.selectPage(pageParam, memberQueryVo);
        List<Member> records = memberPage.getRecords();
        long total = memberPage.getTotal();
        return R.ok().data("total", total).data("rows", records);
    }


    @ApiOperation("新增用户")
    @PutMapping("save-member-info")
    public R save(@ApiParam(value = "用户对象", required = true) @RequestBody Member member) {
        boolean result = memberService.save(member);
        if (result) {
            return R.ok().message("保存成功");
        } else {
            return R.error().message("保存失败");
        }
    }

    @ApiOperation("更新用户")
    @PutMapping("update-member-info")
    public R updateById(@ApiParam(value = "用户对象", required = true) @RequestBody Member member) {

        boolean result = memberService.updateById(member);
        if (result) {
            return R.ok().message("修改成功");
        } else {
            return R.error().message("修改失败");
        }
    }

    @ApiOperation("根据id获取用户信息")
    @GetMapping("get/{id}")
    public R getById(@ApiParam(value = "用户ID", required = true) @PathVariable String id) {
        Member member = memberService.getById(id);
        if (member != null) {
            return R.ok().data("item", member);
        } else {
            return R.error().message("数据不存在");
        }
    }

    @ApiOperation("根据名字左关键词自动联想")
    @GetMapping("list/name/{key}")
    public R selectNameListByKey(
            @ApiParam(value = "查询关键字", required = true)
            @PathVariable String key) {
        List<Map<String, Object>> nameList = memberService.selectNameListByKey(key);
        return R.ok().data("nameList", nameList);
    }

    @ApiOperation("根据用户id查询用户信息")
    @GetMapping("get-member-dto/{memberId}")
    public MemberDto getMemberDtoByMemberId(
            @ApiParam(value = "用户ID", required = true)
            @PathVariable String memberId) {
        MemberDto memberDto = memberService.getMemberDtoByMemberId(memberId);
        return memberDto;
    }

    @ApiOperation("根据id冻结用户")
    @PutMapping("disable-member-info/{id}")
    public R disableMemberInfo(@ApiParam(value = "用户ID", required = true) @PathVariable String id) {

        UpdateWrapper<Member> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("is_disabled", 1);
        updateWrapper.eq("id",id);
        boolean update = memberService.update(updateWrapper);
        if (update) {
            return R.ok().message("已冻结");
        } else {
            return R.error().message("冻结失败");
        }
    }

    @ApiOperation("根据id解冻用户")
    @PutMapping("dedisable-member-info/{id}")
    public R dedisableMemberInfo(@ApiParam(value = "用户ID", required = true) @PathVariable String id) {

        UpdateWrapper<Member> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("is_disabled", 0);
        updateWrapper.eq("id",id);
        boolean update = memberService.update(updateWrapper);
        if (update) {
            return R.ok().message("已解冻");
        } else {
            return R.error().message("解冻失败");
        }
    }
}