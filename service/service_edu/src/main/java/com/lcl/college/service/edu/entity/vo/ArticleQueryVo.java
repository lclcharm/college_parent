package com.lcl.college.service.edu.entity.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author lcl
 * @date 2021/3/8 23:03
 */
@Data
public class ArticleQueryVo {
    private static final long serialVersionUID = -3888944756009060820L;
    private String queryKey;
    private Integer articleType;
    private Integer orderby; //排序条件 0时间倒序 1浏览量倒序
    private Integer count;//查询数据量 0不限制	大于0限制

//    @DateTimeFormat(pattern="yyyy-MM-dd  ")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDateTime joinDateBegin;//查询 开始添加时间
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDateTime joinDateEnd;//查询 结束添加时间
}
