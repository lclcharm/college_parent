package com.lcl.college.service.edu.controller.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lcl.college.common.base.result.R;
import com.lcl.college.common.base.util.JwtInfo;
import com.lcl.college.common.base.util.JwtUtils;
import com.lcl.college.service.edu.entity.vo.CommentQueryVo;
import com.lcl.college.service.edu.service.ArticleService;
import com.lcl.college.service.edu.service.CommentService;
import com.lcl.college.service.edu.entity.Article;
import com.lcl.college.service.edu.entity.Comment;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lcl
 * @date 2021/3/8 21:49
 */

@Api(tags = "评论")
@RestController
@RequestMapping("/api/edu/comment")
@Slf4j
public class ApiCommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleService articleService;

    @ApiOperation("新增评论")
    @PutMapping("save-comment")
    public R save(
            @ApiParam(value = "评论对象", required = true)
            @RequestBody Comment comment,
            HttpServletRequest request) {

        JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);

        if (StringUtils.isEmpty(jwtInfo) || StringUtils.isEmpty(jwtInfo.getId())) {
            return R.error().message("未登录");
        }
        comment.setAvatar(jwtInfo.getAvatar());
        comment.setMemberId(jwtInfo.getId());
       comment.setNickname(jwtInfo.getNickname());
        boolean result = commentService.save(comment);
        Article article = articleService.queryArticleById(comment.getTargetId());
        article.setCommentNum(article.getCommentNum() + 1);

        boolean b = articleService.updateById(article);

        if (result && b) {
            return R.ok().message("添加评论成功");
        } else {
            return R.error().message("添加评论失败");
        }
    }

    @ApiOperation("根据课程或文章ID和类型查询所有评论")
    @GetMapping("list-comment")
    public R getComment(
            @ApiParam(value = "类型 1文章 2课程", required = true)
                    CommentQueryVo commentQueryVo) {
        List<Comment> comments = commentService.queryCommentByIdType(commentQueryVo);
        return R.ok().data("comments", comments);
    }

    @ApiOperation("根据课程或文章ID和类型查询所有评论回复")
    @GetMapping("list-comment-back")
    public R getCommentBack(
            @ApiParam(value = "类型 1文章 2课程", required = true)
                    CommentQueryVo commentQueryVo) {
        List<Comment> comments = commentService.queryCommentBackByIdType(commentQueryVo);
        return R.ok().data("commentBacks", comments);

    }

    @ApiOperation("根据课程或文章ID和类型分页查询所有评论")
    @GetMapping("list/{page}/{limit}")
    public R getPageComments(
            @ApiParam(value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(value = "类型 1文章 2课程", required = true)
                    CommentQueryVo commentQueryVo) {

        Page<Comment> pageParam = new Page<>(page, limit);

        Page<Comment> commentPage = commentService.queryPageCommentByIdType(pageParam, commentQueryVo);
        List<Comment> records = commentPage.getRecords();
        long total = commentPage.getTotal();
        long current = commentPage.getCurrent();
        long pages = commentPage.getPages();
        long size = commentPage.getSize();
        boolean hasNext = commentPage.hasNext();//下一页
        boolean hasPrevious = commentPage.hasPrevious();//上一页

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




}
