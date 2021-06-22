package com.lcl.college.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcl.college.service.edu.entity.TeacherEvaluate;
import com.lcl.college.service.edu.mapper.SubjectMapper;
import com.lcl.college.service.edu.mapper.TeacherEvaluateMapper;
import com.lcl.college.service.edu.service.SubjectService;
import com.lcl.college.service.edu.service.TeacherEvaluateService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lcl
 * @date 2021/5/7 16:54
 */

@Service
public class TeacherEvaluateServiceImpl extends ServiceImpl<TeacherEvaluateMapper, TeacherEvaluate> implements TeacherEvaluateService {


    @Override
    public boolean addEvaluate(TeacherEvaluate teacherEvaluate) {

        int insert = baseMapper.insert(teacherEvaluate);
        if (insert > 0)
            return true;
        else return false;
    }

    @Override
    public boolean updateEvaluate(TeacherEvaluate teacherEvaluate) {
        UpdateWrapper<TeacherEvaluate> teacherEvaluateUpdateWrapper = new UpdateWrapper<>();
        teacherEvaluateUpdateWrapper.eq("teacher_id", teacherEvaluate.getTeacherId());
        teacherEvaluateUpdateWrapper.eq("member_id", teacherEvaluate.getMemberId());

        int i = baseMapper.update(teacherEvaluate,teacherEvaluateUpdateWrapper);
        if (i > 0)
            return true;
        else return false;
    }

    @Override
    public List<TeacherEvaluate> selectEvaluateByTeacherId(String teacherId) {
        QueryWrapper<TeacherEvaluate> teacherEvaluateQueryWrapper = new QueryWrapper<>();

        teacherEvaluateQueryWrapper.eq("teacher_id",teacherId);
        teacherEvaluateQueryWrapper.orderByDesc("gmt_modified");
        List<TeacherEvaluate> teacherEvaluates = baseMapper.selectList(teacherEvaluateQueryWrapper);
        return teacherEvaluates;
    }

    @Override
    public int selectIsEvaluate(TeacherEvaluate teacherEvaluate) {
        String memberId = teacherEvaluate.getMemberId();
        String teacherId = teacherEvaluate.getTeacherId();
        QueryWrapper<TeacherEvaluate> teacherEvaluateQueryWrapper = new QueryWrapper<>();

        teacherEvaluateQueryWrapper.eq("member_id",memberId);
        teacherEvaluateQueryWrapper.eq("teacher_id",teacherId);

        Integer integer = baseMapper.selectCount(teacherEvaluateQueryWrapper);
        return integer;

    }

    @Override
    public Page<TeacherEvaluate> pageQueryEvaluate(Page pageParam, String id) {
        QueryWrapper<TeacherEvaluate> teacherEvaluateQueryWrapper = new QueryWrapper<>();
        teacherEvaluateQueryWrapper.orderByDesc("gmt_modified");
        teacherEvaluateQueryWrapper.eq("teacher_id", id);
        Page page = baseMapper.selectPage(pageParam, teacherEvaluateQueryWrapper);

        return page;
    }
}
