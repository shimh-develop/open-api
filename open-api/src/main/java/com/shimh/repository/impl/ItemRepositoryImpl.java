package com.shimh.repository.impl;

import com.shimh.entity.Item;
import com.shimh.entity.Page;
import com.shimh.repository.wrapper.ItemWrapper;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author: shimh
 * @create: 2018年11月
 **/
public class ItemRepositoryImpl implements ItemWrapper {


    @PersistenceContext
    private EntityManager em;

    protected Session getSession() {
        return em.unwrap(Session.class);
    }

    @Override
    public List<Item> myList() {

        StringBuilder hql = new StringBuilder("from Item where item_type != 0 and is_del = 0 order by top desc");

        Query query = getSession().createQuery(hql.toString());

        return query.list();

    }

    @Override
    public void top(Integer top_item_id) {
        String sql = "update item set top = IF(item_id = :item_id,1,0)";

        NativeQuery nativeQuery = getSession().createNativeQuery(sql);

        nativeQuery.setParameter("item_id", top_item_id);

        nativeQuery.executeUpdate();

    }

}
