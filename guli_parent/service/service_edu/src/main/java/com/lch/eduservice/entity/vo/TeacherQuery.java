package com.lch.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description:
 * @author: lch
 * @time: 2022/5/13 15:10
 */
@ApiModel(value = "teacher查询对象",description = "讲好思查询对象封装")
@Data
public class TeacherQuery {
    @ApiModelProperty("教师名称，模糊查询")
    private String name;
    @ApiModelProperty("头衔， 1高级讲师，2首席讲师")
    private Integer level;
    @ApiModelProperty(value = "查询开始时间",example = "2019-01-01 10:10:10")
    private String begin;
    @ApiModelProperty(value = "查询结束时间",example = "2019-01-01 10:10:10")
    private String end;
}
