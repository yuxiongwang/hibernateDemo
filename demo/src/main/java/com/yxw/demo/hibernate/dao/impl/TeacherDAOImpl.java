package com.yxw.demo.hibernate.dao.impl;


import com.yxw.demo.dto.TeacherDTO;
import com.yxw.demo.entity.Course;
import com.yxw.demo.entity.Score;
import com.yxw.demo.entity.Teacher;
import com.yxw.demo.hibernate.dao.TeacherDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
@Transactional
public class TeacherDAOImpl  implements TeacherDAO {


    @Autowired
    private EntityManager entityManager;

    @Override
    public Teacher get(Long t) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Teacher> criteriaQuery = criteriaBuilder.createQuery(Teacher.class);
        Root<Teacher> root = criteriaQuery.from(Teacher.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("teacherNo"),t));
        List<Teacher> list = entityManager.createQuery(criteriaQuery).getResultList();
        if(list != null && list.size()>0){
            return list.get(0);
        }
        return null;
    }
    /**
     *
     * @param teacherId
     * @param flag（true:包含教师姓名，false 不包含）
     * @return
     */
    public List<TeacherDTO> getResult(Long teacherId, boolean flag){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TeacherDTO> criteriaQuery = criteriaBuilder.createQuery(TeacherDTO.class);
        Root<Score> root = criteriaQuery.from(Score.class);
        //拼接表
        Join<Course, Score> join = root.join("course", JoinType.LEFT);
        Join<Teacher, Score> join2 = root.join("teacher", JoinType.LEFT);
        if(teacherId!=null){
            criteriaQuery.where(criteriaBuilder.equal(join2.get("teacherNo"), teacherId));
        }
        if(flag){
            //拼接搜索字段
            criteriaQuery.multiselect(join2.get("teacherName"),join.get("courseName"),criteriaBuilder.avg(root.get("score")),
                    criteriaBuilder.max(root.get("score")), criteriaBuilder.min(root.get("score")));
            criteriaQuery.groupBy(join2.get("teacherName"));
        }else{
            criteriaQuery.multiselect(join.get("courseName"),criteriaBuilder.avg(root.get("score")),
                    criteriaBuilder.max(root.get("score")), criteriaBuilder.min(root.get("score")));
        }
        //分组
        criteriaQuery.groupBy(join.get("courseName"));
        List<TeacherDTO> list = entityManager.createQuery(criteriaQuery).getResultList();
        return list;
    }

    @Override
    public void save(Teacher t) {
        entityManager.persist(t);
    }


}
