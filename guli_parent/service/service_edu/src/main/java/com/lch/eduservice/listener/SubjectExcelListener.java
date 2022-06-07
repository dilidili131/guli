package com.lch.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lch.eduservice.entity.EduSubject;
import com.lch.eduservice.entity.excel.SubjectData;
import com.lch.eduservice.service.EduSubjectService;
import com.lch.serviceBase.exceptionHandler.GuLiException;

import java.util.Map;

/**
 * @description:
 * @author: lch
 * @time: 2022/6/4 20:15
 */

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    public EduSubjectService subjectService;

    public SubjectExcelListener() {}
    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    /**
     * @description:逐行读取excel
     * @param subjectData
     * @param analysisContext
     * @return: void
     * @author: lch
     * @time: 2022/6/4 20:32
     */
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {


        if(subjectData == null){
            throw new GuLiException(20001,"文件数据为空");
        }
        //一行一行读取，每次读取有两个值，第一个值一级分类，第二个值为二级分类
        EduSubject oneSubject = this.existOneSubject(subjectData.getOneSubjectName(), subjectService);
        if(oneSubject == null){
            oneSubject = new EduSubject();
            oneSubject.setParentId("0");
            oneSubject.setTitle(subjectData.getOneSubjectName());
            subjectService.save(oneSubject);
        }
        String pid = oneSubject.getId();
        EduSubject twoSubject = this.existTwoSubject(subjectData.getTwoSubjectName(), subjectService, pid);
        if(twoSubject == null){
            twoSubject = new EduSubject();
            twoSubject.setParentId(pid);
            twoSubject.setTitle(subjectData.getTwoSubjectName());
            subjectService.save(twoSubject);

        }

    }
    //判断一级分类不能重复添加
    private EduSubject existOneSubject(String name,EduSubjectService subjectService){
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",name);
        queryWrapper.eq("parent_id","0");
        EduSubject one = subjectService.getOne(queryWrapper);
        return one;
    }
    //判断二级分类不能重复添加
    private EduSubject existTwoSubject(String name,EduSubjectService subjectService,String pid){
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",name);
        queryWrapper.eq("parent_id",pid);
        EduSubject two = subjectService.getOne(queryWrapper);
        return two;
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
