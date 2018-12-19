package com.shimh.service;

import com.shimh.common.base.service.BaseService;
import com.shimh.entity.Catalog;
import com.shimh.repository.CatalogRepository;

/**
 * @author: shimh
 * @create: 2018年11月
 **/
public interface CatalogService extends BaseService<Catalog, Integer, CatalogRepository>{
    Integer addOrEdit(Catalog catalog);
}
