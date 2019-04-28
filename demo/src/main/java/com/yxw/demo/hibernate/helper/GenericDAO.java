package com.yxw.demo.hibernate.helper;


public interface GenericDAO<T> {

     T get(Long t);

     void save(T t);


}
