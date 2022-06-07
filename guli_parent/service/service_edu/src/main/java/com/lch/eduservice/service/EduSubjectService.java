package com.lch.eduservice.service;

import com.lch.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lch.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author lch
 * @since 2022-06-03
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file, EduSubjectService eduSubjectService);

    List<OneSubject> getAllSubject();
}
