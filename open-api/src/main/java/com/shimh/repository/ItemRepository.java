package com.shimh.repository;

import com.shimh.entity.Item;
import com.shimh.repository.wrapper.ItemWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author: shimh
 * @create: 2018年11月
 **/
public interface ItemRepository extends JpaRepository<Item, Integer>, ItemWrapper {

    @Query("from Item where item_type = :item_type")
    Item getByItemType(@Param("item_type") int item_type);
}
