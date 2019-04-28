package com.yxw.demo.hibernate.dao.impl;


import com.yxw.demo.entity.Course;
import com.yxw.demo.entity.Score;
import com.yxw.demo.hibernate.dao.CourseDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class CourseDAOImpl  implements CourseDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Course get(Long t) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
        Root<Course> root = criteriaQuery.from(Course.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("courseNo"),t));
        List<Course> list = entityManager.createQuery(criteriaQuery).getResultList();
        if(list != null && list.size()>0){
            return list.get(0);
        }
        return null;
    }


    @Override
    public void save(Course t) {
        entityManager.persist(t);
    }


}
