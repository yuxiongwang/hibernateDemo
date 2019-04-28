package com.yxw.demo.service;


import com.yxw.demo.dto.TeacherDTO;
import com.yxw.demo.entity.Teacher;
import com.yxw.demo.hibernate.dao.TeacherDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class TeacherService {


    @Autowired
    private TeacherDAO teacherDAO;


    @Transactional
    public void save(Teacher teacher){
        teacherDAO.save(teacher);
    }
    /**
     * 查询教师
     * @param teacherNo
     */
    public Teacher getByTeacherNo(Long teacherNo){
        return teacherDAO.get(teacherNo);
    }

    /**
     * 教师本人成绩
     * @return
     */
    public List<TeacherDTO> getCourse(Long teacherId){
        return teacherDAO.getResult(teacherId,false);

    }

    /**
     * 学科成绩(学科)
     * @return
     */
    public List<TeacherDTO> getCourse() {
        return teacherDAO.getResult(null, false);
    }

    /**
     * 学科成绩(教师学科)
     * @return
     */
    public List<TeacherDTO> getCourseAndTeacherName(){
        return teacherDAO.getResult(null,true);
    }


}
