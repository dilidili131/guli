package com.lch.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lch.eduservice.entity.EduSubject;
import com.lch.eduservice.entity.excel.SubjectData;
import com.lch.eduservice.entity.subject.OneSubject;
import com.lch.eduservice.listener.SubjectExcelListener;
import com.lch.eduservice.mapper.EduSubjectMapper;
import com.lch.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author lch
 * @since 2022-06-03
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file, EduSubjectService eduSubjectService) {
        try {
            InputStream in = file.getInputStream();
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<OneSubject> getAllSubject() {
        //获取所有第一分类
        QueryWrapper<EduSubject> queryWrapperOne = new QueryWrapper<>();
        queryWrapperOne.eq("parent_id","0");
        List<EduSubject> subjectsOneList = baseMapper.selectList(queryWrapperOne);
        //获取所有二级分类
        QueryWrapper<EduSubject> queryWrapperTwo = new QueryWrapper<>();
        queryWrapperTwo.ne("parent_id","0");
        List<EduSubject> subjectsTwoList = baseMapper.selectList(queryWrapperTwo);
        //封装一级分类
        //List<OneSubject>
        return null;
    }
}
