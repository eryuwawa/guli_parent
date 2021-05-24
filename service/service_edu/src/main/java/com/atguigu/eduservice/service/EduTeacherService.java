package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.TeacherQuery;
import com.atguigu.util.GuiguResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author atguigu_somc
 * @since 2021-05-22
 */
public interface EduTeacherService extends IService<EduTeacher> {
//    Map getTeacherPage(Integer page, Integer rows);

    GuiguResult getTeacherPage(Long  page, Long  rows);

    GuiguResult getTeacherPageByQuery(Long page, Long rows, TeacherQuery teacherQuery);


    //public List<EduTeacher> getAllTeacher();

}
