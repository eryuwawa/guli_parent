package com.atguigu.eduservice.controller;


import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.TeacherQuery;
import com.atguigu.eduservice.service.EduTeacherService;
import com.atguigu.servicebase.config.exception.GuliException;
import com.atguigu.util.GuiguResult;
import com.baomidou.mybatisplus.extension.api.R;
import com.sun.media.jfxmedia.logging.Logger;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author atguigu_somc
 * @since 2021-05-22
 */
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    @Autowired
    EduTeacherService eduTeacherService;

    @GetMapping("/all")
    public GuiguResult getAllTeacher(){

//        List<EduTeacher> eduTeacherList = eduTeacherService.getAllTeacher();
        List<EduTeacher> teacherList = eduTeacherService.list(null);

        return GuiguResult.ok().data("teacherList", teacherList);
    }

    //获取查询讲师的分页数据
    @GetMapping("/page/{page}/{rows}")
    public GuiguResult getTeacherPage(@PathVariable Long page,
                                      @PathVariable Long  rows){

//        Map teacherPage = eduTeacherService.getTeacherPage(page, rows);
        GuiguResult teacherPage = eduTeacherService.getTeacherPage(page, rows);

        return teacherPage;
    }

    //带分页的条件查询
    @PostMapping("/pagequery/{page}/{rows}")
    public GuiguResult getTeacherPageByQuery(
                                        @RequestBody(required = false) TeacherQuery teacherQuery,
                                        @PathVariable Long page,
                                        @PathVariable Long rows){

        GuiguResult teacherPage = eduTeacherService.getTeacherPageByQuery(page, rows, teacherQuery);

        return teacherPage;
    }


    @DeleteMapping("/{id}}")
    public GuiguResult deleteTeacherById(@PathVariable String id){

        boolean removeById = eduTeacherService.removeById(id);

        return removeById == true ? GuiguResult.ok():GuiguResult.error();
    }

    @ApiOperation(value = "新增讲师")
    @PostMapping
    public GuiguResult save(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher teacher){
        eduTeacherService.save(teacher);
        return GuiguResult.ok();
    }

    //根据id查询讲师-develop edit
    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("{id}")
    public GuiguResult getById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){
        EduTeacher teacher = eduTeacherService.getById(id);

        try {
            int a = 10/0;
        }catch(Exception e) {
            throw new GuliException(20001,"出现自定义异常");
        }
        return GuiguResult.ok().data("item", teacher);
    }

    //根据id修改讲师数据
    @ApiOperation(value = "根据ID修改讲师")
    @PutMapping("{id}")
    public GuiguResult updateById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id,
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher teacher) {
        teacher.setId(Long.parseLong(id));
        eduTeacherService.updateById(teacher);
        return GuiguResult.ok();
    }

}

