package com.lcl.college.service.edu.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lcl.college.common.base.result.R;
import com.lcl.college.common.base.result.ResultCodeEnum;
import com.lcl.college.service.base.exception.CollegeException;
import com.lcl.college.service.edu.entity.Article;
import com.lcl.college.service.edu.entity.ArticleContent;
import com.lcl.college.service.edu.entity.vo.ArticleQueryVo;
import com.lcl.college.service.edu.entity.vo.ArticleVo;
import com.lcl.college.service.edu.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lcl
 * @date 2021/3/8 21:49
 */

@Api(tags = "文章管理")
@RestController
@RequestMapping("/admin/edu/article")
@Slf4j
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @ApiOperation("新增文章")
    @PutMapping("save-article-info")
    @Transactional
    public R save(
            @ApiParam(value = "文章对象", required = true)
            @RequestBody ArticleVo articleVo) {

        Article article = new Article();
        ArticleContent articleContent  = new ArticleContent();
        BeanUtils.copyProperties(articleVo, article);
        BeanUtils.copyProperties(articleVo, articleContent);

        boolean articleResult = articleService.save(article);
        articleContent.setId(article.getId());
        int contentResult = articleService.addArticleContent(articleContent);
        if (articleResult && contentResult > 0) {
            return R.ok().message("添加文章成功");
        } else {
            return R.error().message("添加文章失败");
        }
    }

    @ApiOperation("修改文章")
    @PutMapping("update-article-info")
    public R update(
            @ApiParam(value = "文章对象", required = true)
            @RequestBody ArticleVo articleVo) {
        Article article = new Article();
        ArticleContent articleContent  = new ArticleContent();
        BeanUtils.copyProperties(articleVo, article);
        BeanUtils.copyProperties(articleVo, articleContent);

        int articleResult = articleService.updateArticle(article);
        articleContent.setId(article.getId());
        int contentResult = articleService.updateArticleContent(articleContent);
        if (articleResult > 0 && contentResult > 0) {
            return R.ok().message("修改文章成功");
        } else {
            return R.error().message("修改文章失败");
        }

    }

    @ApiOperation("根据id批量删除文章")
    @DeleteMapping("batch-remove")
    public R deleteBatch(
            @ApiParam(value = "文章对象", required = true)
            @RequestBody List<String> idList) {
        try {
//            articleService.deleteArticleByIds(idList);
            articleService.removeByIds(idList);
            return R.ok().message("修改文章成功");
        } catch (Exception e) {
            throw new CollegeException(ResultCodeEnum.ARTICLE_DELETE_ERROR);
        }

    }
    @ApiOperation("根据id删除文章")
    @DeleteMapping("remove/{id}")
    public R remove(
            @ApiParam(value = "文章对象", required = true)
            @PathVariable String id) {
        try {
            articleService.removeById(id);
            return R.ok().message("修改文章成功");
        } catch (Exception e) {
            throw new CollegeException(ResultCodeEnum.ARTICLE_DELETE_ERROR);
        }

    }

    @ApiOperation("分页文章列表")
    @GetMapping("list/{page}/{limit}")
    public R index(@ApiParam(value = "当前页码", required = true)
                   @PathVariable Long page,

                   @ApiParam(value = "每页记录数", required = true)
                   @PathVariable Long limit,

                   @ApiParam(value = "查询对象") ArticleQueryVo articleQueryVo) {

        Page<Article> pageParam = new Page<>(page, limit);
        IPage<Article> articleIPage = articleService.pageQueryArticle(pageParam,articleQueryVo);
        List<Article> records = articleIPage.getRecords();
        long total = articleIPage.getTotal();
        return R.ok().data("total", total).data("rows", records);
    }

    @ApiOperation("根据ID查询文章")
    @GetMapping("article-info/{id}")
    public R getById(
            @ApiParam(value = "文章ID", required = true)
            @PathVariable String id) {

        Article article = articleService.queryArticleById(id);
        String content = "";
        if (!StringUtils.isEmpty(article)) {
            content = articleService.queryArticleContentByArticleId(id);
            ArticleVo articleVo = new ArticleVo();
            BeanUtils.copyProperties(article,articleVo);
            articleVo.setContent(content);
            return R.ok().data("item", articleVo);
        }else{
            return R.ok().message("数据不存在");
        }
    }


}
