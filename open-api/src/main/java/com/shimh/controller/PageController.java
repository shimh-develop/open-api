package com.shimh.controller;

import com.alibaba.fastjson.support.spring.annotation.FastJsonFilter;
import com.alibaba.fastjson.support.spring.annotation.FastJsonView;
import com.shimh.common.base.Result;
import com.shimh.common.constant.Base;
import com.shimh.entity.Catalog;
import com.shimh.entity.Item;
import com.shimh.entity.Page;
import com.shimh.service.PageService;
import org.apache.shiro.SecurityUtils;
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
@RequestMapping(value = "/api/page")
public class PageController {

    @Autowired
    private PageService pageService;

    @PostMapping("/info")
    @FastJsonView(include = {@FastJsonFilter(clazz = Catalog.class, props = {"cat_id", "parentCatalog"}),
            @FastJsonFilter(clazz = Item.class, props = {"item_id"})}
    )
    public Result info(Integer page_id) {

        Page page = pageService.info(page_id);
        return Result.success(page);
    }

    @PostMapping("/save")
    @RequiresAuthentication
    public Result add(@RequestBody Page page) {

        Integer page_id = pageService.addOrEdit(page);

        Result r = Result.success();
        r.simple().put("page_id", page_id);
        return r;
    }


    @PostMapping("/delete")
    @RequiresAuthentication
    public Result delete(Integer page_id) {

        pageService.deleteByIsDelete("page", "page_id", page_id);

        Result r = Result.success();
        return r;
    }



}
