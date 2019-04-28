package com.yxw.demo.service;

import com.yxw.demo.entity.Course;
import com.yxw.demo.hibernate.dao.CourseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class CourseService {

     @Autowired
     private CourseDAO courseDAO;


     public Course getById(Long courseId){
          return courseDAO.get(courseId);
     }
     @Transactional
     public void save(Course student){
          courseDAO.save(student);
     }

}
