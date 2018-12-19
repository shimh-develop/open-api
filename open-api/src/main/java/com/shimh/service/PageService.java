package com.shimh.service;

import com.shimh.common.base.service.BaseService;
import com.shimh.entity.Page;
import com.shimh.repository.PageRepository;
import com.sun.xml.internal.bind.v2.model.core.ID;

/**
 * @author: shimh
 * @create: 2018年11月
 **/
public interface PageService extends BaseService <Page, Integer, PageRepository>{
    Page info(Integer page_id);

    Integer addOrEdit(Page page);
}
