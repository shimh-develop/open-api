package com.shimh.repository;

import com.shimh.BaseTest;
import com.shimh.entity.Catalog;
import com.shimh.entity.Item;
import com.shimh.entity.Page;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author: shimh
 * @create: 2018年11月
 **/
public class RepositoryTest extends BaseTest {

//    @Autowired
//    private ItemRepository itemRepository;
//
//    @Autowired
//    private PageRepository pageRepository;
//
//    @Autowired
//    private CatalogRepository catalogRepository;
//
//
//    @Test
//    public  void itemPage() {
//        Item old_item = itemRepository.getOne(4);
//
//        Page rootPage = new Page();
//
//        rootPage.setPage_title("rootPage");
//        rootPage.setItem(old_item);
//
//        pageRepository.save(rootPage);
//
//
//        Item new_item = itemRepository.getOne(4);
//
//        List<Page> pages = new_item.getPages();
//        pages.forEach(page -> System.out.println(page.getPage_title()));
//
//    }
//
//    @Test
//    public  void catalogPage() {
//        Catalog catalog = new Catalog();
//        catalog.setCat_name("catalog1");
//        catalog = catalogRepository.save(catalog);
//
//        Page catalogPage = new Page();
//        catalogPage.setPage_title("catalog1 page1");
//        catalogPage.setCatalog(catalog);
//        pageRepository.save(catalogPage);
//
//        Catalog new_catalog = catalogRepository.getOne(catalog.getCat_id());
//
//        List<Page> pages = new_catalog.getPages();
//        pages.forEach(page -> System.out.println(page.getPage_title()));
//
//    }
//
//    @Test
//    @Transactional
//    public  void getCatalogPage() {
//
//        int id = 36;
//        Catalog new_catalog = catalogRepository.getOne(id);
//
//        List<Page> pages = new_catalog.getPages();
//        pages.forEach(page -> System.out.println(page.getPage_title()));
//
//    }
//
//    @Test
//    public  void catalogCatalog() {
//        int id = 36;
//        Catalog parent_catalog = catalogRepository.getOne(id);
//
//        Catalog catalog = new Catalog();
//        catalog.setCat_name("catalog1 catalog11");
//        catalog.setParentCatalog(parent_catalog);
//        catalogRepository.save(catalog);
//
//    }




}
