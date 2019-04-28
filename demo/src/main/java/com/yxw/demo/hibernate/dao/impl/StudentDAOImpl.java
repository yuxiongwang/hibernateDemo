package com.yxw.demo.hibernate.dao.impl;


import com.yxw.demo.dto.StudentDTO;
import com.yxw.demo.entity.Course;
import com.yxw.demo.entity.Score;
import com.yxw.demo.entity.Student;
import com.yxw.demo.hibernate.dao.StudentDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
@Transactional
public class StudentDAOImpl  implements StudentDAO {

    @Autowired
    private EntityManager entityManager;


    @Override
    public Student get(Long t) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
        Root<Student> root = criteriaQuery.from(Student.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("studentNo"),t));
        List<Student> list = entityManager.createQuery(criteriaQuery).getResultList();
        if(list != null && list.size()>0){
            return list.get(0);
        }
        return null;
    }
    /**
     *
     * @param studentId
     * @return
     */
    @Override
    public Page<StudentDTO> getResult(int index, int pageSize, Long studentId ){
        //新建一个页面，存放页面信息
        Pageable pageable = new PageRequest(index, pageSize);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<StudentDTO> criteriaQuery = criteriaBuilder.createQuery(StudentDTO.class);
        Root<Score> root = criteriaQuery.from(Score.class);
        //链接表
        Join<Course, Score> join = root.join("course", JoinType.LEFT);
        Join<Student, Score> join3= root.join("student", JoinType.LEFT);
        //插入查询条件
        criteriaQuery.where(criteriaBuilder.equal(join3.get("studentNo"), studentId));
        //搜索字段
        criteriaQuery.multiselect(join3.get("studentName"),join.get("courseName"),root.get("score"));

        List<StudentDTO> list = entityManager.createQuery(criteriaQuery).getResultList();
        TypedQuery<StudentDTO> createQuery = entityManager.createQuery(criteriaQuery);
        //查询记录的第几个开始
        createQuery.setFirstResult((index-1)*pageSize);
        //每页的数量
        createQuery.setMaxResults(pageSize);
        return new PageImpl<StudentDTO>(createQuery.getResultList(), pageable, list.size());
    }

    @Override
    public void save(Student t) {
        entityManager.persist(t);
    }


}
