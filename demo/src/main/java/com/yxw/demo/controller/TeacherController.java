package com.yxw.demo.controller;

import com.yxw.demo.entity.Teacher;
import com.yxw.demo.utils.Constant;
import com.yxw.demo.utils.Result;
import com.yxw.demo.utils.ResultUtils;
import com.yxw.demo.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class TeacherController {
    private static Logger log = LoggerFactory.getLogger(TeacherController.class);

    @Autowired
    private TeacherService teacherService;

    /**
     *成绩查询
     */
    @GetMapping("/getScore")
    public Result<Object> getScore(Long teacherNo){
        log.info("请求参数：teacherNo:"+teacherNo);
        if(teacherNo==null){
            log.info(Constant.NO_PARAMETER);
            return ResultUtils.lack();
        }
        Teacher teacher=teacherService.getByTeacherNo(teacherNo);
        if(teacher == null){
            return ResultUtils.fail();
        }
        if(teacher.getStatus()==1){
            //主任查询所有
            return ResultUtils.success(teacherService.getCourse());
        }
         return ResultUtils.success(teacherService.getCourse(teacherNo));
    }

    /**
     * 学科最低分(教师学科)
     */
    @GetMapping("/getCourseAndTeacherName")
    public Result<Object> getCourseAndTeacherName(Long teacherNo){
        log.info("请求参数：teacherId:"+teacherNo);
        if( teacherNo==null ){
            log.info("必填参数为空");
            return ResultUtils.lack();
        }
        Teacher teacher=teacherService.getByTeacherNo(teacherNo);
        if(teacher != null && teacher.getStatus()==1 ){
            return ResultUtils.success(teacherService.getCourseAndTeacherName());
        }
        return ResultUtils.fail();

    }



    @PostMapping("/addTeacher")
    public Result<Object> addTeacher(@RequestBody Teacher teacherDto){
        if(null == teacherDto || null == teacherDto.getTeacherNo()){
            log.info("必填参数TeacherNo为空");
            return ResultUtils.lack();
        }
        Teacher teacher=teacherService.getByTeacherNo(teacherDto.getTeacherNo());
        if(teacher!=null){
            log.info("该教师已存在..........");
            return ResultUtils.exist();
        }
        teacherService.save(teacherDto);
        return ResultUtils.success(null);
    }



}
