package com.lcl.college.service.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lcl.college.service.edu.entity.Article;
import com.lcl.college.service.edu.entity.ArticleContent;
import com.lcl.college.service.edu.entity.Message;
import com.lcl.college.service.edu.entity.vo.ArticleQueryVo;

import java.util.List;
import java.util.Map;

/**
 * @author lcl
 * @date 2021/3/8 22:07
 */
public interface MessageService extends IService<Message> {
   /**
    * 创建系统消息
    *
    * @param message 消息实体
    * @return 返回文章ID
    */
   int saveMessage(Message message);

   Page<Message> pageQueryMessage(Page pageParam, Message message);

   Message queryMessageById(String id);

   int updateMessage(Message message);

   Page<Message> pageQueryMessage(Page pageParam);
}
