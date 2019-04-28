package com.yxw.demo.service;

import com.yxw.demo.dto.StudentDTO;
import com.yxw.demo.entity.Student;
import com.yxw.demo.hibernate.dao.StudentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class StudentService {

    @Autowired
    private StudentDAO studentDAO;


    /**
     * 查询教师
     * @param studentNo
     */
    public Student getById(Long studentNo){
        return studentDAO.get(studentNo);
    }
    @Transactional
    public void save(Student student){
        studentDAO.save(student);
    }


    public Page<StudentDTO> findMyScore(int index, int pageSize,Long studentId){

        return  studentDAO.getResult(index,pageSize,studentId);
    }
}
