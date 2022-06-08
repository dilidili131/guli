package com.lch.eduservice.service.impl;

import com.lch.eduservice.entity.EduCourse;
import com.lch.eduservice.entity.EduCourseDescription;
import com.lch.eduservice.entity.vo.CourseInfo;
import com.lch.eduservice.mapper.EduCourseMapper;
import com.lch.eduservice.service.EduCourseDescriptionService;
import com.lch.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lch.serviceBase.exceptionHandler.GuLiException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author lch
 * @since 2022-06-07
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService courseDescriptionService;

    @Override
    public void saveCourseInfo(CourseInfo courseInfo) {
        //1 课程表添加课程基本信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfo,eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if(insert <= 0){
            throw new GuLiException(20001,"添加课程信息失败");
        }
        //2 课程简介表添加简介信息
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfo.getDescription());
        eduCourseDescription.setId(eduCourse.getId());
        courseDescriptionService.save(eduCourseDescription);
    }
}
