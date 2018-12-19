package com.shimh.service.impl;

import com.shimh.common.base.service.BaseService;
import com.shimh.common.base.service.BaseServiceImpl;
import com.shimh.common.constant.Base;
import com.shimh.entity.Catalog;
import com.shimh.entity.Item;
import com.shimh.entity.Page;
import com.shimh.repository.ItemRepository;
import com.shimh.service.ItemService;
import com.shimh.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: shimh
 * @create: 2018年11月
 **/
@Service
public class ItemServiceImpl extends BaseServiceImpl<Item, Integer, ItemRepository> implements ItemService {

    @Autowired
    private PageService pageService;


    @Override
    @Transactional(readOnly = true)
    public List<Item> myList() {

        return repository.myList();
    }

    @Override
    @Transactional
    public void top(String action, Integer item_id) {
        Integer top_item_id = item_id;
        if("cancel".equals(action)) { // 取消所有
            top_item_id = -1;
        }
        repository.top(top_item_id);

    }

    @Override
    public Item detail(Integer item_id) {
        return this.getById(item_id);
    }

    @Override
    public Item info(Integer item_id, String search) {
        Item item = getById(item_id);

        if (null != search && !"".equals(search)) {
            List<Page> temp_pages = new ArrayList<>();
            for(Page p: item.getPages()) {
                if(p.getPage_title().indexOf(search) > -1) {
                    temp_pages.add(p);
                }
            }
            addPage(temp_pages, item.getCatalogs(), search);
            item.setPages(temp_pages);

            item.setCatalogs(null);

        }

        return item;
    }

    private void addPage(List<Page> temp_pages, List<Catalog> catalogs, String search) {

        if(null != catalogs && catalogs.size() > 0) {
            for (Catalog c : catalogs) {
                for(Page p: c.getPages()) {
                    if(p.getPage_title().indexOf(search) > -1) {
                        temp_pages.add(p);
                    }
                }
                if(null != c.getCatalogs() && c.getCatalogs().size() > 0) {
                    addPage(temp_pages, c.getCatalogs(), search);
                }
            }
        }
    }

    @Override
    @Transactional
    public Integer addOrEdit(Item item) {
        if(null != item.getItem_id()) {
            Item old_item = getById(item.getItem_id());
            old_item.setItem_name(item.getItem_name());
            old_item.setItem_description(item.getItem_description());
            old_item.setPassword(item.getPassword());
            old_item.setLast_update_time(new Date());

            //单页项目 更新文章名称
            if(Base.SINGLE_PAGE_ITEM_TYPE == old_item.getItem_type()) {
                if(null != old_item.getPages()) {
                    Page page = old_item.getPages().get(0);
                    page.setPage_title(item.getItem_name());
                }

            }

            return save(old_item).getItem_id();
        } else {

            Date now = new Date();

            item.setCreate_time(now);
            item.setLast_update_time(now);
            Item new_item = repository.save(item);

            // 单页项目 创建一个默认的页面
            if (Base.SINGLE_PAGE_ITEM_TYPE == item.getItem_type()) {
                Page page = new Page();
                page.setPage_title(item.getItem_name());
                page.setCreate_time(new Date());

                if (null == page.getS_number()) {
                    page.setS_number(Base.DEFAULT_S_NUMBER);
                }
                page.setItem(new_item);
                pageService.save(page);
            }

            return new_item.getItem_id();


        }

    }

    @Override
    public Item getOpenApi() {
        int item_type = 0;
        return repository.getByItemType(item_type);
    }
}
