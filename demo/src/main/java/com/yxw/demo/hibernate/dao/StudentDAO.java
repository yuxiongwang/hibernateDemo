package com.yxw.demo.hibernate.dao;


import com.yxw.demo.dto.StudentDTO;
import com.yxw.demo.entity.Student;
import com.yxw.demo.hibernate.helper.GenericDAO;
import org.springframework.data.domain.Page;


public interface StudentDAO extends GenericDAO<Student> {
     Page<StudentDTO> getResult(int index, int pageSize, Long studentId );
}
