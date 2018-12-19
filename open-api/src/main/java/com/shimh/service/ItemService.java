package com.shimh.service;

import com.shimh.common.base.service.BaseService;
import com.shimh.entity.Item;
import com.shimh.repository.ItemRepository;

import java.util.List;

/**
 * @author: shimh
 * @create: 2018年11月
 **/
public interface ItemService extends BaseService<Item, Integer, ItemRepository> {

    List<Item> myList();

    void top(String action, Integer item_id);

    Item detail(Integer item_id);

    Item info(Integer item_id, String search);

    Integer addOrEdit(Item item);

    Item getOpenApi();
}
