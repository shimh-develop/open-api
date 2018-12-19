package com.shimh.service.impl;

import com.shimh.common.base.service.BaseService;
import com.shimh.common.base.service.BaseServiceImpl;
import com.shimh.common.constant.Base;
import com.shimh.entity.Catalog;
import com.shimh.repository.CatalogRepository;
import com.shimh.service.CatalogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.plugin.cache.OldCacheEntry;

import java.util.Date;

/**
 * @author: shimh
 * @create: 2018年11月
 **/
@Service
public class CatalogServiceImpl extends BaseServiceImpl<Catalog, Integer, CatalogRepository> implements CatalogService {

    @Transactional
    @Override
    public Integer addOrEdit(Catalog catalog) {

        if(null == catalog.getS_number()) {
            catalog.setS_number(Base.DEFAULT_S_NUMBER);
        }

        if(null != catalog.getCat_id()) {
            Catalog old_catalog = this.getById(catalog.getCat_id());
            old_catalog.setCat_name(catalog.getCat_name());
            old_catalog.setS_number(catalog.getS_number());

            if(null != catalog.getParentCatalog()) {
                old_catalog.setItem(null);
                old_catalog.setParentCatalog(catalog.getParentCatalog());

            } else {
                old_catalog.setParentCatalog(null);
                old_catalog.setItem(catalog.getItem());
            }
            return this.update(old_catalog).getCat_id();
        } else {
            catalog.setCreate_time(new Date());
            return this.save(catalog).getCat_id();
        }

    }
}
