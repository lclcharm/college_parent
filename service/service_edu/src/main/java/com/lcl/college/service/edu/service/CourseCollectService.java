package com.lcl.college.service.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lcl.college.service.edu.entity.CourseCollect;
import com.lcl.college.service.edu.entity.vo.CourseCollectVo;

import java.util.List;

/**
 * <p>
 * 课程收藏 服务类
 * </p>
 *
 * @author lcl
 * @since 2020-06-23
 */
public interface CourseCollectService extends IService<CourseCollect> {

    boolean isCollect(String courseId, String memberId);

    void saveCourseCollect(String courseId, String memberId);

    List<CourseCollectVo> selectListByMemberId(String memberId);

    boolean removeCourseCollect(String courseId, String memberId);
}
