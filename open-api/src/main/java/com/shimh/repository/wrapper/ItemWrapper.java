package com.shimh.repository.wrapper;

import com.shimh.entity.Item;

import java.util.List;

/**
 * @author: shimh
 * @create: 2018年11月
 **/
public interface ItemWrapper {
    List<Item> myList();

    void top(Integer top_item_id);

}
