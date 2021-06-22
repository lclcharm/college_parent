package com.lcl.college.service.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lcl.college.service.edu.entity.Article;
import com.lcl.college.service.edu.entity.ArticleContent;
import com.lcl.college.service.edu.entity.vo.ArticleQueryVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lcl
 * @date 2021/3/8 22:15
 */
@Repository
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 创建文章
     * @param article 文章实体
     * @return 返回文章ID
     */
    public int createArticle(Article article);

    /**
     * 添加文章内容
     * @param content 文章内容实体
     * @return
     */
    public int addArticleContent(ArticleContent content);

    /**
     * 修改文章信息
     * @param article 文章实体
     */
    public void updateArticle(Article article);

    /**
     * 修改文章内容
     * @param content
     * @return
     */
    public int updateArticleContent(ArticleContent content);

    /**
     * 删除文章
     * @param articleIds 文章ID串 如：(1,2,3,4)
     */
    public void deleteArticleByIds(String articleIds);

    /**
     * 删除文章内容
     * @param articleIds 文章ID串 如：(1,2,3,4)
     */
    public void deleteArticleContentByArticleIds(String articleIds);

    /**
     * 通过文章ID查询文章信息
     * @param articleId 文章ID
     * @return Article文章实体信息
     */
    public Article queryArticleById(String articleId);

    /**
     * 通过文章ID查询文章内容
     * @param articleId 文章内容
     * @return String类型文章内容
     */
    public String queryArticleContentByArticleId(String articleId);

    /**
     * 分页查询文章列表
     * @param query 查询条件
     * @param page 分页条件
     * @return List<Article>文章列表
     */
//    public List<Article> queryArticlePage(ArticleQueryVo query,PageEntity page);

    /**
     * 修改累加文章点击量
     */
    public void updateArticleNum(Map<String,String> map);

    /**
     * 公共多条件查询文章资讯列表,用于前台
     */
    public List<Article> queryArticleList(ArticleQueryVo queryArticle);

    /**
     * 获取所有文章总记录数
     * @return 总记录数
     */
    public int queryAllArticleCount();

    List<Article> getArticleRankList();
}
