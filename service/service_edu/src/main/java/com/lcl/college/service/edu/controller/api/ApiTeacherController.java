package com.lcl.college.service.edu.controller.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lcl.college.common.base.result.R;
import com.lcl.college.common.base.util.JwtInfo;
import com.lcl.college.common.base.util.JwtUtils;
import com.lcl.college.service.edu.entity.Article;
import com.lcl.college.service.edu.entity.Teacher;
import com.lcl.college.service.edu.entity.TeacherEvaluate;
import com.lcl.college.service.edu.entity.vo.ArticleQueryVo;
import com.lcl.college.service.edu.service.TeacherEvaluateService;
import com.lcl.college.service.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lcl
 * @date 2020/7/27 18:44
 **/
@Api(tags = "讲师")
@RestController
@RequestMapping("/api/edu/teacher")
public class ApiTeacherController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private TeacherEvaluateService teacherEvaluateService;

    @ApiOperation(value = "所有讲师列表")
    @GetMapping("list")
    public R listAll() {
        List<Teacher> list = teacherService.list();
        return R.ok().data("items", list).message("获取讲师列表成功");
    }

    @ApiOperation(value = "获取讲师")
    @GetMapping("get/{id}")
    public R get(
            @ApiParam(value = "讲师ID", required = true)
            @PathVariable String id
    ) {
        Map<String, Object> map = teacherService.selectTeacherInfoById(id);
        return R.ok().data(map);
    }


    @ApiOperation("分页获取评论列表")
    @GetMapping("evaluate-page/{page}/{limit}/{id}")
    public R index(@ApiParam(value = "当前页码", required = true)
                   @PathVariable Long page,
                   @ApiParam(value = "每页记录数", required = true)
                   @PathVariable Long limit,
                   @ApiParam(value = "查询讲师")
                       @PathVariable String id) {
        Page<TeacherEvaluate> pageParam = new Page<>(page, limit);
        Page<TeacherEvaluate> teacherEvaluatePage = teacherEvaluateService.pageQueryEvaluate(pageParam, id);
        List<TeacherEvaluate> records = teacherEvaluatePage.getRecords();
        long total = teacherEvaluatePage.getTotal();
        long current = teacherEvaluatePage.getCurrent();
        long pages = teacherEvaluatePage.getPages();
        long size = teacherEvaluatePage.getSize();
        boolean hasNext = teacherEvaluatePage.hasNext();//下一页
        boolean hasPrevious = teacherEvaluatePage.hasPrevious();//上一页

        //把分页数据获取出来，放到map集合
        Map<String, Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);
        return R.ok().data("total", total).data("items", map);
    }

    @ApiOperation(value = "获取讲师评价")
    @GetMapping("evaluate-list/{id}")
    public R getTeacherComment(
            @ApiParam(value = "讲师ID", required = true)
            @PathVariable String id) {
        List<TeacherEvaluate> teacherEvaluates = teacherEvaluateService.selectEvaluateByTeacherId(id);
        return R.ok().data("items", teacherEvaluates);
    }

    @ApiOperation(value = "添加讲师评价")
    @PutMapping("evaluate-add")
    public R addTeacherComment(
            @ApiParam(value = "讲师信息", required = true)
            @RequestBody TeacherEvaluate teacherEvaluate,
            HttpServletRequest request) {

        JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);

        if (StringUtils.isEmpty(jwtInfo) || StringUtils.isEmpty(jwtInfo.getId())) {
            return R.error().message("请登录后再试");
        }
        teacherEvaluate.setAvatar(jwtInfo.getAvatar());
        teacherEvaluate.setMemberId(jwtInfo.getId());
        teacherEvaluate.setNickname(jwtInfo.getNickname());

        boolean b = false;
        int i = teacherEvaluateService.selectIsEvaluate(teacherEvaluate);
        if (i > 0) {
            b = teacherEvaluateService.updateEvaluate(teacherEvaluate);
        } else {
            b = teacherEvaluateService.addEvaluate(teacherEvaluate);
        }
        if (b)
            return R.ok().message("感谢你的评价");
        else {
            return R.error().message("评价错误");
        }
    }


}
