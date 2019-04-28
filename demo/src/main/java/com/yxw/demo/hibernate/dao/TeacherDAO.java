package com.yxw.demo.hibernate.dao;

import com.yxw.demo.dto.TeacherDTO;
import com.yxw.demo.entity.Teacher;
import com.yxw.demo.hibernate.helper.GenericDAO;

import java.util.List;


public interface TeacherDAO extends GenericDAO<Teacher> {
     List<TeacherDTO> getResult(Long teacherId, boolean flag);
}
