package com.lcl.college.service.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lcl.college.service.edu.entity.Article;
import com.lcl.college.service.edu.entity.ArticleContent;
import com.lcl.college.service.edu.entity.vo.ArticleQueryVo;

import java.util.List;
import java.util.Map;

/**
 * @author lcl
 * @date 2021/3/8 22:07
 */
public interface ArticleService extends IService<Article> {
   /**
    * 创建文章
    *
    * @param article 文章实体
    * @return 返回文章ID
    */
   int createArticle(Article article);

   /**
    * 添加文章内容
    *
    * @param content 文章内容实体
    */
   public int addArticleContent(ArticleContent content);

   /**
    * 修改文章信息
    *
    * @param article 文章实体
    * @return
    */
   public int updateArticle(Article article);

   /**
    * 修改文章内容
    *
    * @param content
    * @return
    */
   public int updateArticleContent(ArticleContent content);

   /**
    * 删除文章
    *
    * @param articleIds 文章ID数组
    */
   public void deleteArticleByIds(List<String> articleIds);

   /**
    * 通过文章ID查询文章信息
    *
    * @param articleId 文章ID
    * @return Article文章实体信息
    */
   public Article queryArticleById(String articleId);

   /**
    * 通过文章ID查询文章内容
    *
    * @param articleId 文章内容
    * @return String类型文章内容
    */
   public String queryArticleContentByArticleId(String articleId);

   /**
    * 分页查询文章列表
    *//*
    public List<Article> queryArticlePage(QueryArticle query, PageEntity page);

    */
   public Page<Article> pageQueryArticle(Page page, ArticleQueryVo articleQueryVo);

   /**
    * 修改累加文章点击量
    */
   public void updateArticleNum(Map<String, String> map);

   /**
    * 公共多条件查询文章资讯列表,用于前台
    */
   public List<Article> queryArticleList(ArticleQueryVo articleQueryVo);


   /**
    * 获取所有文章总记录数
    *
    * @return 总记录数
    */
   public int queryAllArticleCount();

   List<Article> getArticleRankList();

   boolean addCommentCount(String id);

}
