package com.lcl.college.service.edu.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lcl.college.common.base.result.R;
import com.lcl.college.common.base.result.ResultCodeEnum;
import com.lcl.college.service.base.exception.CollegeException;
import com.lcl.college.service.edu.entity.Article;
import com.lcl.college.service.edu.entity.ArticleContent;
import com.lcl.college.service.edu.entity.Message;
import com.lcl.college.service.edu.entity.vo.ArticleQueryVo;
import com.lcl.college.service.edu.entity.vo.ArticleVo;
import com.lcl.college.service.edu.service.ArticleService;
import com.lcl.college.service.edu.service.MessageService;
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
 * @date 2021/5/6 22:24
 */

@Api(tags = "系统消息管理")
@RestController
@RequestMapping("/admin/edu/message")
@Slf4j
public class MessageController {
    @Autowired
    private MessageService messageService;

    @ApiOperation("新增系统消息")
    @PutMapping("save-message-info")
    @Transactional
    public R save(
            @ApiParam(value = "消息对象", required = true)
            @RequestBody Message message) {

        int result = messageService.saveMessage(message);
        if (result > 0) {
            return R.ok().message("添加系统消息成功");
        } else {
            return R.error().message("添加系统消息失败");
        }
    }


    @ApiOperation("分页系统消息列表")
    @GetMapping("list/{page}/{limit}")
    public R index(@ApiParam(value = "当前页码", required = true)
                   @PathVariable Long page,

                   @ApiParam(value = "每页记录数", required = true)
                   @PathVariable Long limit,

                   @ApiParam(value = "查询对象") Message message) {
        Page<Message> pageParam = new Page<>(page, limit);
        IPage<Message> messageIPage = messageService.pageQueryMessage(pageParam, message);


        List<Message> records = messageIPage.getRecords();
        long total = messageIPage.getTotal();
        return R.ok().data("total", total).data("rows", records);
    }

    @ApiOperation("根据ID查询系统消息")
    @GetMapping("message-info/{id}")
    public R getById(
            @ApiParam(value = "文章ID", required = true)
            @PathVariable String id) {

        Message message = messageService.queryMessageById(id);
        if (!StringUtils.isEmpty(message)) {
            return R.ok().data("item", message);
        } else {
            return R.ok().message("数据不存在");
        }
    }


    @ApiOperation("根据id删除系统消息")
    @DeleteMapping("remove/{id}")
    public R remove(
            @ApiParam(value = "系统消息对象", required = true)
            @PathVariable String id) {

        boolean b = messageService.removeById(id);
        if (b) {
            return R.ok().message("修改系统消息成功");
        } else
            return R.error().message("修改系统消息失败");
    }

    @ApiOperation("修改消息")
    @PutMapping("update-message-info")
    public R update(
            @ApiParam(value = "文章消息", required = true)
            @RequestBody Message message) {

        int result = messageService.updateMessage(message);
        if (result > 0) {
            return R.ok().message("修改消息成功");
        } else {
            return R.error().message("修改消息失败");
        }
    }

    @ApiOperation("根据id批量删除系统消息")
    @DeleteMapping("batch-remove")
    public R deleteBatch(
            @ApiParam(value = "消息对象", required = true)
            @RequestBody List<String> idList) {
        boolean b = messageService.removeByIds(idList);
        if (b)
            return R.ok().message("批量删除消息成功");
        else
            return R.error().message("批量删除消息失败");

    }
}
