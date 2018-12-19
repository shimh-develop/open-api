package com.shimh.controller;

import com.alibaba.fastjson.support.spring.annotation.FastJsonFilter;
import com.alibaba.fastjson.support.spring.annotation.FastJsonView;
import com.shimh.common.base.Result;
import com.shimh.entity.Catalog;
import com.shimh.entity.Item;
import com.shimh.service.CatalogService;
import com.shimh.service.ItemService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author: shimh
 * @create: 2018年11月
 **/
@RestController
@RequestMapping(value = "/api/catalog")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private ItemService itemService;


    @PostMapping("/catListGroup")
    @FastJsonView(include = {@FastJsonFilter(clazz = Item.class, props = {"item_id", "catalogs"}),
            @FastJsonFilter(clazz = Catalog.class, props = {"cat_id", "s_number", "create_time", "cat_name", "catalogs"})}
    )
    public Result catListGroup(Integer item_id) {
        Item item = itemService.getById(item_id);
        return Result.success(item);
    }

    @PostMapping("/save")
    @RequiresAuthentication
    public Result add(@RequestBody Catalog catalog) {

        Integer cat_id = catalogService.addOrEdit(catalog);

        Result r = Result.success();
        r.simple().put("cat_id", cat_id);
        return r;
    }

    @PostMapping("/delete")
    @RequiresAuthentication
    public Result delete(Integer cat_id) {

        Catalog catalog = catalogService.getById(cat_id);

        if(catalog.getCatalogs().size() > 0 || catalog.getPages().size() > 0) {
            return Result.error("不允许删除非空目录");
        }
        catalogService.deleteByIsDelete("catalog", "cat_id", cat_id);
        return Result.success();

    }

}
