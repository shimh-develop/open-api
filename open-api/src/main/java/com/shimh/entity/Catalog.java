package com.shimh.entity;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author: shimh
 * @create: 2018年11月
 **/
@Entity
@Table(name = "catalog")
@Where(clause="is_del = 0")
public class Catalog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cat_id;

    @Column(name = "cat_name", length = 20)
    private String cat_name;

    @Column(name = "s_number")
    private Integer s_number; // 排序

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentCatalog", orphanRemoval = true)
    private List<Catalog> catalogs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Catalog parentCatalog;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "catalog", orphanRemoval = true)
    @OrderBy("s_number asc")
    private List<Page> pages;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "is_del")
    private int is_del = 0; //删除 0 正常 1 删除

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date create_time;


    public Integer getCat_id() {
        return cat_id;
    }

    public void setCat_id(Integer cat_id) {
        this.cat_id = cat_id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public Integer getS_number() {
        return s_number;
    }

    public void setS_number(Integer s_number) {
        this.s_number = s_number;
    }

    public List<Catalog> getCatalogs() {
        return catalogs;
    }

    public void setCatalogs(List<Catalog> catalogs) {
        this.catalogs = catalogs;
    }

    public Catalog getParentCatalog() {
        return parentCatalog;
    }

    public void setParentCatalog(Catalog parentCatalog) {
        this.parentCatalog = parentCatalog;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getIs_del() {
        return is_del;
    }

    public void setIs_del(int is_del) {
        this.is_del = is_del;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "cat_id=" + cat_id +
                ", cat_name='" + cat_name + '\'' +
                ", s_number=" + s_number +
                ", catalogs=" + catalogs +
                ", parentCatalog=" + parentCatalog +
                ", pages=" + pages +
                ", item=" + item +
                '}';
    }
}
