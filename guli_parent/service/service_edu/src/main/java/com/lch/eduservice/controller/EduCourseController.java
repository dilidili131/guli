package com.lch.eduservice.controller;


import com.lch.commonUtils.Result;
import com.lch.eduservice.entity.vo.CourseInfo;
import com.lch.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author lch
 * @since 2022-06-07
 */
@RestController
@RequestMapping("/eduservice/eduCourse")
@CrossOrigin
@Api(description = "课程管理")
public class EduCourseController {

    @Autowired
    private EduCourseService courseService;

    @PostMapping("/addCourseInfo")
    @ApiOperation("添加课程信息")
    public Result addCourseInfo(@RequestBody CourseInfo courseInfo){

        courseService.saveCourseInfo(courseInfo);
        return Result.ok();
    }
}

