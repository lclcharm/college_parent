package com.lcl.college.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcl.college.service.edu.entity.Article;
import com.lcl.college.service.edu.entity.Message;
import com.lcl.college.service.edu.entity.vo.ArticleQueryVo;
import com.lcl.college.service.edu.mapper.MessageMapper;
import com.lcl.college.service.edu.service.MessageService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author lcl
 * @date 2021/3/8 22:12
 */

@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {


    /**
     * 创建系统消息
     *
     * @param message 消息实体
     * @return 返回系统消息ID
     */
    @Override
    public int saveMessage(Message message) {

        return baseMapper.insert(message);
    }

    @Override
    public Page<Message> pageQueryMessage(Page pageParam, Message message) {
        //条件构造器
        QueryWrapper<Message> wrapper = new QueryWrapper<>();


        wrapper.orderByDesc("gmt_create");
        return baseMapper.selectPage(pageParam, wrapper);
    }

    /**
     * 通过系统消息ID查询系统消息信息
     *
     * @param id 消息ID
     * @return Message消息实体信息
     */
    @Override
    public Message queryMessageById(String id) {
        //修改系统消息点击量
        Message message = baseMapper.selectById(id);

        return message;
    }

    @Override
    public int updateMessage(Message message) {

        return baseMapper.updateById(message);
    }


    @Override
    public Page<Message> pageQueryMessage(Page pageParam) {
        //条件构造器
        QueryWrapper<Message> wrapper = new QueryWrapper<>();


        wrapper.orderByDesc("gmt_create");
        return baseMapper.selectPage(pageParam, wrapper);
    }

}
