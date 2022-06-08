package com.lch.eduservice.service;

import com.lch.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lch.eduservice.entity.vo.CourseInfo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author lch
 * @since 2022-06-07
 */
public interface EduCourseService extends IService<EduCourse> {

    void saveCourseInfo(CourseInfo courseInfo);
}
