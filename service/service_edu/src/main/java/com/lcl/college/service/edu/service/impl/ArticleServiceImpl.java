package com.lcl.college.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcl.college.service.edu.entity.Article;
import com.lcl.college.service.edu.entity.ArticleContent;
import com.lcl.college.service.edu.entity.vo.ArticleQueryVo;
import com.lcl.college.service.edu.mapper.ArticleMapper;
import com.lcl.college.service.edu.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author lcl
 * @date 2021/3/8 22:12
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;


    /**
     * 创建文章
     *
     * @param article 文章实体
     * @return 返回文章ID
     */
    @Override
    public int createArticle(Article article) {
        int insert = baseMapper.insert(article);
        return insert;
    }

    /**
     * 添加文章内容
     *
     * @param content 文章内容实体
     */
    @Override
    public int addArticleContent(ArticleContent content) {
        return articleMapper.addArticleContent(content);
    }

    /**
     * 修改文章信息
     *
     * @param article 文章实体
     * @return
     */
    @Override
    public int updateArticle(Article article) {

        return baseMapper.updateById(article);
    }

    /**
     * 修改文章内容
     *
     * @param content
     * @return
     */
    @Override
    public int updateArticleContent(ArticleContent content) {

        return articleMapper.updateArticleContent(content);
    }

    /**
     * 删除文章
     *
     * @param articleIds 文章ID数组
     */
    @Override
    public void deleteArticleByIds(List<String> articleIds) {

        if (articleIds != null && articleIds.size() > 0) {
            StringBuffer sb = new StringBuffer();
            for (String id : articleIds) {
                sb.append(id + ",");
            }
            sb.substring(0, sb.length() - 1);
            articleIds.toString();
            String idStr = sb.toString();
            articleMapper.deleteArticleByIds(idStr);
            articleMapper.deleteArticleContentByArticleIds(idStr);
        }

    }

    /**
     * 通过文章ID查询文章信息
     *
     * @param articleId 文章ID
     * @return Article文章实体信息
     */
    @Override
    public Article queryArticleById(String articleId) {
        //修改文章点击量
        Article art = baseMapper.selectById(articleId);
        art.setClickNum(art.getClickNum() + 1);
        System.err.println("------咿呀呀");
        baseMapper.updateById(art);
        Article article = baseMapper.selectById(articleId);
        return article;
    }

    /**
     * 通过文章ID查询文章内容
     *
     * @param articleId 文章内容
     * @return String类型文章内容
     */
    @Override
    public String queryArticleContentByArticleId(String articleId) {

        return articleMapper.queryArticleContentByArticleId(articleId);

    }

    /**
     * 分页查询文章列表
     *
     * @param pageParam
     * @param articleQueryVo
     */
    @Override
    public Page<Article> pageQueryArticle(Page pageParam, ArticleQueryVo articleQueryVo) {
        //条件构造器
        QueryWrapper<Article> wrapper = new QueryWrapper<>();

        //普通的排序分页查询
        if (pageParam == null) {
            return baseMapper.selectPage(pageParam, wrapper);
        }

        //获取对象参数
        Integer orderby = articleQueryVo.getOrderby();
        String queryKey = articleQueryVo.getQueryKey();
        Integer articleType = articleQueryVo.getArticleType();
        LocalDateTime joinDateBegin = articleQueryVo.getJoinDateBegin();
        LocalDateTime joinDateEnd = articleQueryVo.getJoinDateEnd();

        //条件
        if (!StringUtils.isEmpty(queryKey)) {
            wrapper.like("title", queryKey);
        } else if (!StringUtils.isEmpty(queryKey)) {
            wrapper.like("source", queryKey);
        } else if (!StringUtils.isEmpty(queryKey)) {
            wrapper.like("AUTHOR", queryKey);
        }

        if (!StringUtils.isEmpty(articleType)) {
            wrapper.eq("article_type", articleType);
        }

        if (!StringUtils.isEmpty(joinDateBegin)) {
            wrapper.ge("gmt_create", joinDateBegin);
        }

        if (!StringUtils.isEmpty(joinDateEnd)) {
            wrapper.le("gmt_create", joinDateEnd);
        }
        if (StringUtils.isEmpty(orderby)) {
            orderby =0 ;
            if (orderby == 0) {
                //排序
                wrapper.orderByDesc("gmt_create");
            }
            if(orderby == 1){
                //排序
                wrapper.orderByDesc("CLICK_NUM");
            }
        }
        return baseMapper.selectPage(pageParam, wrapper);
    }


    /**
     * 修改累加文章点击量
     *
     * @param map
     */
    @Override
    public void updateArticleNum(Map<String, String> map) {
        articleMapper.updateArticleNum(map);
    }

    /**
     * 公共多条件查询文章资讯列表,用于前台
     *
     * @param articleQueryVo
     */
    @Override
    public List<Article> queryArticleList(ArticleQueryVo articleQueryVo) {
        return articleMapper.queryArticleList(articleQueryVo);
    }


    /**
     * 获取所有文章总记录数
     *
     * @return 总记录数
     */
    @Override
    public int queryAllArticleCount() {
        return articleMapper.queryAllArticleCount();
    }

    /**
     * 获取所有文章热榜
     *
     * @return 总记录数
     */
    @Override
    public List<Article> getArticleRankList() {

        return articleMapper.getArticleRankList();
    }

    @Override
    public boolean addCommentCount(String id) {
        Article article = baseMapper.selectById(id);
        System.err.println("===============");
        article.setCommentNum(article.getCommentNum()+1);
        System.err.println(article);
        int i = baseMapper.updateById(article);
        if(i>0){
            return true;
        }
        return false;
    }


}
