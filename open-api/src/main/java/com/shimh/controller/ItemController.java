package com.shimh.controller;

import com.alibaba.fastjson.support.spring.annotation.FastJsonFilter;
import com.alibaba.fastjson.support.spring.annotation.FastJsonView;
import com.shimh.common.annotation.LogAnnotation;
import com.shimh.common.base.Result;
import com.shimh.common.constant.ResultCode;
import com.shimh.entity.Catalog;
import com.shimh.entity.Item;
import com.shimh.entity.Page;
import com.shimh.service.ItemService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: shimh
 * @create: 2018年11月
 **/
@RestController
@RequestMapping(value = "/api/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/add")
    @RequiresAuthentication
    public Result add(Item item) {

        Integer item_id = itemService.addOrEdit(item);

        Result r = Result.success();
        r.simple().put("item_id", item_id);
        return r;
    }

    @PostMapping("/update")
    @RequiresAuthentication
    public Result update(Item item) {

        Integer item_id = itemService.addOrEdit(item);

        return Result.success();
    }

    @PostMapping("/delete")
    @RequiresAuthentication
    public Result delete(Integer item_id) {

        itemService.deleteByIsDelete("item", "item_id", item_id);

        Result r = Result.success();
        return r;
    }


    @GetMapping("/myList")
    @FastJsonView(exclude = {@FastJsonFilter(clazz = Item.class, props = {"catalogs", "pages"})})
    public Result myList() {
        List<Item> items = itemService.myList();
        return Result.success(items);
    }

    @GetMapping("/getOpenApi")
    @FastJsonView(exclude = {@FastJsonFilter(clazz = Item.class, props = {"catalogs", "pages"})})
    public Result getOpenApi() {
        Item item = itemService.getOpenApi();
        return Result.success(item);
    }

    @GetMapping("/getOpenApiInfo")
    @FastJsonView(exclude = {@FastJsonFilter(clazz = Catalog.class, props = {"item", "parentCatalog"}),
            @FastJsonFilter(clazz = Page.class, props = {"catalog", "item"})}
    )
    public Result getOpenApiInfo() {
        Item item = itemService.getOpenApi();
        return Result.success(item);
    }

    @GetMapping("/detail")
    @FastJsonView(exclude = {@FastJsonFilter(clazz = Item.class, props = {"catalogs", "pages"})})
    public Result detail(Integer item_id) {
        Item item = itemService.detail(item_id);
        return Result.success(item);
    }

    @PostMapping("/info")
    @FastJsonView(exclude = {@FastJsonFilter(clazz = Catalog.class, props = {"item", "parentCatalog"}),
            @FastJsonFilter(clazz = Page.class, props = {"catalog", "item"})}
    )
    public Result info(Integer item_id, String keyword) {
        Item item = itemService.info(item_id, keyword);

        return Result.success(item);
    }

    @PostMapping("/top")
    @RequiresAuthentication
    public Result top(String action, Integer item_id) {

        itemService.top(action, item_id);

        Result r = Result.success();
        return r;
    }



}
