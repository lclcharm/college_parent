package com.lcl.college.service.edu.controller.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lcl.college.common.base.result.R;
import com.lcl.college.service.edu.entity.Message;
import com.lcl.college.service.edu.service.MessageService;
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

@Api(tags = "系统消息")
@RestController
@RequestMapping("/api/edu/message")
@Slf4j
public class ApiMessageController {

    @Autowired
    private MessageService messageService;


    @ApiOperation("分页系统消息列表")
    @GetMapping("list/{page}/{limit}")
    public R index(@ApiParam(value = "当前页码", required = true)
                   @PathVariable Long page,

                   @ApiParam(value = "每页记录数", required = true)
                   @PathVariable Long limit) {
        Page<Message> pageParam = new Page<>(page, limit);
        Page<Message> messageIPage = messageService.pageQueryMessage(pageParam);

        List<Message> records = messageIPage.getRecords();
        long total = messageIPage.getTotal();
        long current = messageIPage.getCurrent();
        long pages = messageIPage.getPages();
        long size = messageIPage.getSize();
        boolean hasNext = messageIPage.hasNext();//下一页
        boolean hasPrevious = messageIPage.hasPrevious();//上一页

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



    @ApiOperation("根据ID查询系统消息")
    @GetMapping("message-info/{id}")
    public R getById(
            @ApiParam(value = "系统消息ID", required = true)
            @PathVariable String id) {

        Message message = messageService.queryMessageById(id);
        if (!StringUtils.isEmpty(message)) {
            return R.ok().data("item", message);
        }else{
            return R.ok().message("数据不存在");
        }
    }

}
