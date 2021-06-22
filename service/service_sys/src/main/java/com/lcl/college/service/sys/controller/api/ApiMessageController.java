package com.lcl.college.service.sys.controller.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lcl.college.common.base.result.R;
import com.lcl.college.service.edu.entity.vo.ArticleQueryVo;
import com.lcl.college.service.edu.service.MessageService;
import com.lcl.college.service.edu.entity.Article;
import com.lcl.college.service.edu.entity.vo.ArticleVo;
import com.lcl.college.service.sys.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lcl
 * @date 2021/3/8 21:49
 */

@Api(tags = "文章")
@RestController
@RequestMapping("/api/edu/article")
@Slf4j
public class ApiMessageController {

    @Autowired
    private MessageService messageService;


    @ApiOperation("分页文章列表")
    @GetMapping("list/{page}/{limit}")
    public R index(@ApiParam(value = "当前页码", required = true)
                   @PathVariable Long page,

                   @ApiParam(value = "每页记录数", required = true)
                   @PathVariable Long limit,

                   @ApiParam(value = "查询对象") ArticleQueryVo articleQueryVo) {
        Page<Article> pageParam = new Page<>(page, limit);
        Page<Article> articleIPage = messageService.pageQueryArticle(pageParam,articleQueryVo);
        List<Article> records = articleIPage.getRecords();
        long total = articleIPage.getTotal();
        long current = articleIPage.getCurrent();
        long pages = articleIPage.getPages();
        long size = articleIPage.getSize();
        boolean hasNext = articleIPage.hasNext();//下一页
        boolean hasPrevious = articleIPage.hasPrevious();//上一页

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

//    @ApiOperation("分页文章列表")
//    @GetMapping("list")
//    public R listPage(@ApiParam("查询条件") ArticleQueryVo articleQueryVo){
//        Page<Article> pageParam = new Page<>(articleQueryVo.getCurrentPage(), articleQueryVo.getSize());
//        Page<Article> articleIPage = messageService.pageQueryArticle(pageParam,articleQueryVo);
//        List<Article> records = articleIPage.getRecords();
//        long total = articleIPage.getTotal();
//        return R.ok().data("total", total).data("rows", records);
//    }

    @ApiOperation("文章热榜")
    @GetMapping("ranklist")
    public R articleRankList() {

        List<Article> articles = messageService.getArticleRankList();

        return R.ok().data("ranks", articles);
    }

    @ApiOperation("根据ID查询文章")
    @GetMapping("article-info/{id}")
    public R getById(
            @ApiParam(value = "文章ID", required = true)
            @PathVariable String id) {

        Article article = messageService.queryArticleById(id);
        String content = "";
        if (!StringUtils.isEmpty(article)) {
            content = messageService.queryArticleContentByArticleId(id);
            ArticleVo articleVo = new ArticleVo();
            BeanUtils.copyProperties(article,articleVo);
            articleVo.setContent(content);
            return R.ok().data("item", articleVo);
        }else{
            return R.ok().message("数据不存在");
        }
    }
    @ApiOperation("增加文章评论数")
    @PutMapping("comment-addcount/{id}")
    public R addCommentCount(
            @ApiParam(value = "文章ID", required = true)
            @PathVariable String id) {
        boolean result = messageService.addCommentCount(id);
        if (result) {
            return R.ok().message("修改文章成功");
        } else {
            return R.error().message("修改文章失败");
        }

    }


}
