package com.yxw.demo.hibernate.dao.impl;


import com.yxw.demo.entity.Score;

import com.yxw.demo.entity.Teacher;
import com.yxw.demo.hibernate.dao.ScoreDAO;

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
public class ScoreDAOImpl  implements ScoreDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Score get(Long t) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Score> criteriaQuery = criteriaBuilder.createQuery(Score.class);
        Root<Score> root = criteriaQuery.from(Score.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("id"),t));
        List<Score> list = entityManager.createQuery(criteriaQuery).getResultList();
        if(list != null && list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public void save(Score t) {
        entityManager.persist(t);
    }
}
