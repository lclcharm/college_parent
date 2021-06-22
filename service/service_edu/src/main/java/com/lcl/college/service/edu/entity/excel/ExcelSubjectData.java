package com.lcl.college.service.edu.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author lcl
 * @date 2020/7/11 13:35
 **/
@Data
public class ExcelSubjectData {

    @ExcelProperty(value = "一级分类")
    private String levelOneTitle;
    @ExcelProperty(value = "二级分类")
    private String levelTwoTitle;
}
