package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.TeacherQuery;
import com.atguigu.eduservice.mapper.EduTeacherMapper;
import com.atguigu.eduservice.service.EduTeacherService;
import com.atguigu.util.GuiguResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author atguigu_somc
 * @since 2021-05-22
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {


    @Autowired
    EduTeacherMapper eduTeacherMapper;

//
//    public List<EduTeacher> getAllTeacher(){
//
//        List<EduTeacher> eduTeacherList = eduTeacherMapper.selectList(null);
//
//        return eduTeacherList;
//    }

    @Override
    public GuiguResult getTeacherPage(Long  page, Long  rows) {
        Page<EduTeacher> objectPage = new Page<>(page, rows);

        eduTeacherMapper.selectPage(objectPage, null);

        List<EduTeacher> teacherList = objectPage.getRecords();
        long total = objectPage.getTotal();

        return GuiguResult.ok().data("teacherList", teacherList).data("total", total);

    }


/*
    @Override
    public Map getTeacherPage(Integer page, Integer rows) {
        Page<EduTeacher> objectPage = new Page<>(page, rows);

        eduTeacherMapper.selectPage(objectPage, null);


        List<EduTeacher> teacherList = objectPage.getRecords();
        long total = objectPage.getTotal();
//        Map<String, Object> map = new HashMap<>();
//        map.put("teacherList", teacherList);
//        map.put("total", total);

//        return map;
    }
*/

    @Override
    public GuiguResult getTeacherPageByQuery(Long page, Long rows, TeacherQuery teacherQuery) {

        Page<EduTeacher> objectPage = new Page<>(page, rows);

        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
//        wrapper.eq("level", teacherQuery.getLevel());
//        wrapper.like("name", "%" + teacherQuery.getName() + "%");
//        wrapper.between("gmtCreate", teacherQuery.getBegin(), teacherQuery.getEnd());

        queryWrapper.orderByAsc("sort");
        if (teacherQuery == null){
            eduTeacherMapper.selectPage(objectPage, queryWrapper);

            List<EduTeacher> teacherList = objectPage.getRecords();
            long total = objectPage.getTotal();

            return GuiguResult.ok().data("teacherList", teacherList).data("total", total);
        }
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }
        if (null != level) {
            queryWrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            queryWrapper.le("gmt_create", end);
        }

        eduTeacherMapper.selectPage(objectPage, queryWrapper);

        List<EduTeacher> teacherList = objectPage.getRecords();
        long total = objectPage.getTotal();

        return GuiguResult.ok().data("teacherList", teacherList).data("total", total);
    }
}
