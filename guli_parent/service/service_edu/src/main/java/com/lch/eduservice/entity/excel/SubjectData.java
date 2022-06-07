package com.lch.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @description:
 * @author: lch
 * @time: 2022/6/4 20:07
 */
@Data
public class SubjectData {

    @ExcelProperty(index = 0)
    private String oneSubjectName;
    @ExcelProperty(index = 1)
    private String twoSubjectName;
}
