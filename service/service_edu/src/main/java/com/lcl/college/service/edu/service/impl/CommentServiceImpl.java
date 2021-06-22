package com.lcl.college.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lcl.college.service.edu.entity.Comment;
import com.lcl.college.service.edu.entity.vo.CommentQueryVo;
import com.lcl.college.service.edu.mapper.CommentMapper;
import com.lcl.college.service.edu.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author lcl
 * @since 2020-06-23
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Override
    public List<Comment> queryCommentByIdType(CommentQueryVo commentQueryVo) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        String targetId = commentQueryVo.getTargetId();
        Integer type = commentQueryVo.getType();
        System.err.println("------------------------");
        System.err.println(targetId);
        System.err.println(type);
        System.err.println(commentQueryVo.getId());
        System.err.println(commentQueryVo.getParentId());

        queryWrapper.eq("target_id",targetId);
        queryWrapper.eq("type", type);
        queryWrapper.eq("parent_id", 0);

        queryWrapper.orderByDesc("gmt_create");
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<Comment> queryCommentBackByIdType(CommentQueryVo commentQueryVo) {
        String id = commentQueryVo.getId();
        String targetId = commentQueryVo.getTargetId();
        Integer type = commentQueryVo.getType();

        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("target_id",targetId);
        queryWrapper.eq("type", type);
        queryWrapper.eq("parent_id", id);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public Page<Comment> queryPageCommentByIdType(Page pageParam, CommentQueryVo commentQueryVo) {

        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        String targetId = commentQueryVo.getTargetId();
        Integer type = commentQueryVo.getType();
//        System.err.println("------------------------");
//        System.err.println(targetId);
//        System.err.println(type);
//        System.err.println(commentQueryVo.getId());
//        System.err.println(commentQueryVo.getParentId());

        queryWrapper.eq("target_id",targetId);
        queryWrapper.eq("type", type);
        queryWrapper.eq("parent_id", 0);

        queryWrapper.orderByDesc("gmt_create");

        return baseMapper.selectPage(pageParam, queryWrapper);
    }
}
