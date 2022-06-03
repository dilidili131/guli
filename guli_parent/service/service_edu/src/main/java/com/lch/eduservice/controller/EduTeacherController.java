package com.lch.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lch.commonUtils.Result;
import com.lch.eduservice.entity.EduTeacher;
import com.lch.eduservice.entity.vo.TeacherQuery;
import com.lch.eduservice.service.EduTeacherService;
import com.lch.serviceBase.exceptionHandler.GuLiException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author lch
 * @since 2022-05-13
 */
@Api(description = "讲师管理")
@RestController
@CrossOrigin
@RequestMapping("/eduService/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;


    /**
     * @description: 查询讲师列表所有数据
     * @param
     * @return: java.util.List<com.lch.eduservice.entity.EduTeacher>
     * @author: lch
     * @time: 2022/5/13 13:23
     */
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("/findAll")
    public Result findAllTeacher(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return Result.ok().data("list",list);
    }

    /**
     * @description:逻辑删除讲师
     * @param id
     * @return: boolean
     * @author: lch
     * @time: 2022/5/13 13:59
     */
    @ApiOperation("逻辑删除讲师")
    @DeleteMapping("/removeTeacherById/{id}")
    public Result removeTeacher(
            @ApiParam(name = "id",value = "讲师id",required = true) @PathVariable String id){
        boolean flag = eduTeacherService.removeById(id);
        if(flag){
            return Result.ok().data("flag",flag);
        }else{
            return Result.error();
        }
    }


    @ApiOperation("教师列表分页")
    @GetMapping("/pageTeacher/{current}/{limit}")
    public Result pageListTeacher(
            @ApiParam(name = "current",value = "页面当前值",required = true) @PathVariable long current,
            @ApiParam(name = "limit",value = "页面可容纳数量",required = true) @PathVariable long limit
    ){
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        eduTeacherService.page(pageTeacher,null);

        long total = pageTeacher.getTotal();
        List<EduTeacher> eduTeachers = pageTeacher.getRecords();
        Map<String,Object> map = new HashMap<>();
        map.put("total",total);
        map.put("lists",eduTeachers);
        return Result.ok().data(map);
    }


    /**
     * @description:教师列表条件查询分页
     * @param current
     * @param limit
     * @param teacherQuery
     * @return: com.lch.commonUtils.Result
     * @author: lch
     * @time: 2022/5/13 16:48
     */
    @ApiOperation("教师列表条件查询分页")
    @PostMapping("/pageTeacherCondition/{current}/{limit}")
    public Result pageTeacherCondition(
            @ApiParam(name = "current",value = "页面当前值",required = true) @PathVariable long current,
            @ApiParam(name = "limit",value = "页面可容纳数量",required = true) @PathVariable long limit,
            @RequestBody(required = false) TeacherQuery teacherQuery
    ){


        //分页
        Page<EduTeacher> page = new Page<>(current,limit);
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();

        //查询条件
        String name = teacherQuery.getName();
        Integer level= teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if(!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)){
            wrapper.le("gmt_create",end);
        }
        wrapper.orderByDesc("gmt_create");
        eduTeacherService.page(page, wrapper);

        //返回值
        long total = page.getTotal();
        List<EduTeacher> eduTeachers = page.getRecords();

        Map<String,Object> map = new HashMap<>();
        map.put("total",total);
        map.put("rows",eduTeachers);
        return Result.ok().data(map);
    }

    @ApiOperation("添加教师接口")
    @PostMapping("/addTeacher")
    public Result addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);
        if(save){
            return Result.ok();
        }else{
            return Result.error();
        }
    }

    @ApiOperation("根据id查询教师信息")
    @GetMapping("getTeacherById/{id}")
    public Result getTeacherById(@PathVariable String id){
        EduTeacher teacher = eduTeacherService.getById(id);
        return Result.ok().data("teacher",teacher);
    }

    @ApiOperation("修改讲师")
    @PostMapping("/updateTeacher")
    public Result updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean flag = eduTeacherService.updateById(eduTeacher);
        if(flag){
            return Result.ok();
        }else{
            return Result.error();
        }
    }

    @ApiOperation("自定义异常测试")
    @GetMapping("/testException")
    public Result tesrException(){
        try{
            int i=10/0;
        }catch (Exception e){
            throw new GuLiException(20001,"执行了自定义处理异常");
        }
        return Result.ok();
    }
}

