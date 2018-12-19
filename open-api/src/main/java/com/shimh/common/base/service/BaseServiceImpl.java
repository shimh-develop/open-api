package com.shimh.common.base.service;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaUpdate;
import java.io.Serializable;
import java.util.List;

/**
 * @author: shimh
 * @create: 2018年11月
 **/
public abstract class BaseServiceImpl <T,ID extends Serializable,R extends JpaRepository<T,ID>> implements BaseService<T,ID,R> {

    @Autowired
    protected R repository;

    @PersistenceContext
    private EntityManager em;

    protected Session getSession() {
        return em.unwrap(Session.class);
    }

    public T getById(ID id) {
        return repository.findById(id).orElse(null);
    }
    public R getRepository() {
        return repository;
    }

    @Transactional
    public T update(T entity) {
        return repository.save(entity);
    }

    @Transactional
    public T save(T entity) {
        return repository.save(entity);
    }
    
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    public boolean exists(ID id) {
        return repository.existsById(id);
    }

    public List<T> findAll(Example<T> example) {
        return repository.findAll(example);
    }
    public List<T> findAll() {
        return repository.findAll();
    }

    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional
    public void deleteByIsDelete(String tableName, String IdName, ID id) {

        String sql = "update " + tableName + " set is_del = 1 where " + IdName + " = :id";

        NativeQuery nativeQuery = getSession().createNativeQuery(sql);

        nativeQuery.setParameter("id", id);

        nativeQuery.executeUpdate();
    }

}
