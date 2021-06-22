package com.lcl.college.service.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lcl.college.service.edu.entity.Teacher;
import com.lcl.college.service.edu.entity.TeacherEvaluate;

import java.util.List;

/**
 * @author lcl
 * @date 2021/5/7 16:53
 */
public interface TeacherEvaluateService  extends IService<TeacherEvaluate> {
     boolean addEvaluate(TeacherEvaluate teacherEvaluate);
     boolean updateEvaluate(TeacherEvaluate teacherEvaluate);
     List<TeacherEvaluate> selectEvaluateByTeacherId(String teacherId);
    int selectIsEvaluate(TeacherEvaluate teacherEvaluate);

    Page<TeacherEvaluate> pageQueryEvaluate(Page pageParam,String id);
}
