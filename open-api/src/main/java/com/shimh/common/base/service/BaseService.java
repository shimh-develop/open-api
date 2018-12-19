package com.shimh.common.base.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @author: shimh
 * @create: 2018年11月
 **/
public interface BaseService <T,ID extends Serializable,R extends JpaRepository<T,ID>>{

    T getById(ID id);
    R getRepository();

    T update(T entity);

    T save(T entity);

    void deleteById(ID id);

    boolean exists(ID id);

    List<T> findAll(Example<T> example);
    List<T> findAll();

    Page<T> findAll(Pageable pageable);

    void deleteByIsDelete(String tableName, String IdName, ID id);

}
