package com.yxw.demo.service;

import com.yxw.demo.entity.Score;
import com.yxw.demo.hibernate.dao.ScoreDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ScoreService {

     @Autowired
     private ScoreDAO scoreDAO;

     @Transactional
     public void save(Score student){
          scoreDAO.save(student);
     }


}
