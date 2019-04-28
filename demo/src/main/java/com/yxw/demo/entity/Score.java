package com.yxw.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name="t_score")
@Entity
@Data
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 成绩
     */
    private int score;

    @ManyToOne
    @JoinColumn(name="studentNo",referencedColumnName="studentNo")
    private Student student;

    @ManyToOne
    @JoinColumn(name="courseNo",referencedColumnName="courseNo")
    private Course course;

    @ManyToOne
    @JoinColumn(name="teacherNo",referencedColumnName="teacherNo")
    private Teacher teacher;


}
