package com.lch.eduservice.controller;


import com.lch.commonUtils.Result;
import com.lch.eduservice.entity.subject.OneSubject;
import com.lch.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author lch
 * @since 2022-06-03
 */
@RestController
@RequestMapping("/eduService/subject")
@CrossOrigin
@Api(description = "课程管理")
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    //添加课程分类
    @PostMapping("/addSubject")
    @ApiOperation("添加课程分类")
    public Result addSubject(MultipartFile file){
        eduSubjectService.saveSubject(file,eduSubjectService);
        return Result.ok();
    }

    @GetMapping("/getAllSubject")
    @ApiOperation("获取所有分类")
    public Result getAllSubject(){
        List<OneSubject> list = eduSubjectService.getAllSubject();
        return Result.ok().data("list",list);
    }
}

