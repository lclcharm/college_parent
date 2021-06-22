package com.lcl.college.service.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lcl.college.service.edu.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lcl.college.service.edu.entity.vo.CommentQueryVo;

import java.util.List;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author lcl
 * @since 2020-06-23
 */
public interface CommentService extends IService<Comment> {
    List<Comment> queryCommentByIdType(CommentQueryVo commentQueryVo);

    List<Comment> queryCommentBackByIdType(CommentQueryVo commentQueryVo);

    Page<Comment> queryPageCommentByIdType(Page pageParam,CommentQueryVo commentQueryVo);

}
