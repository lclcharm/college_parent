package com.lcl.college.service.edu.service;

import com.lcl.college.service.edu.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lcl.college.service.edu.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author lcl
 * @since 2020-06-23
 */
public interface ChapterService extends IService<Chapter> {

    boolean removeChapterById(String id);

    List<ChapterVo> nestedList(String courseId);
}
