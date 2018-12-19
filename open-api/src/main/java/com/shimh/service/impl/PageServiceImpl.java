package com.shimh.service.impl;

import com.shimh.common.base.service.BaseService;
import com.shimh.common.base.service.BaseServiceImpl;
import com.shimh.common.constant.Base;
import com.shimh.entity.Item;
import com.shimh.entity.Page;
import com.shimh.repository.PageRepository;
import com.shimh.service.ItemService;
import com.shimh.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author: shimh
 * @create: 2018年11月
 **/
@Service
public class PageServiceImpl extends BaseServiceImpl<Page, Integer, PageRepository> implements PageService {

    @Autowired
    private ItemService itemService;

    @Override
    public Page info(Integer page_id) {

        return this.getById(page_id);
    }

    @Override
    @Transactional
    public Integer addOrEdit(Page page) {

        if (null == page.getS_number()) {
            page.setS_number(Base.DEFAULT_S_NUMBER);
        }

        if (null != page.getPage_id()) {

        } else {
            page.setCreate_time(new Date());
        }

        Item item = page.getItem();
        if (null != item && null != item.getItem_id()) {
            Item new_item = itemService.getById(item.getItem_id());
            // 单页项目 更新下项目名称
            if (Base.SINGLE_PAGE_ITEM_TYPE == new_item.getItem_type()) {
                new_item.setItem_name(page.getPage_title());
                itemService.update(new_item);
            }
        }

        return save(page).getPage_id();
    }
}

