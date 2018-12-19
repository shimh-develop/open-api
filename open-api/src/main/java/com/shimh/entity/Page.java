package com.shimh.entity;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

/**
 * @author: shimh
 * @create: 2018年11月
 **/
@Entity
@Table(name = "page")
@Where(clause="is_del = 0")
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer page_id;

    @Column(name = "page_title", length = 20)
    private String page_title;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Type(type = "text")
    private String page_content;

    @Column(name = "is_del")
    private int is_del = 0; //删除 0 正常 1 删除

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date create_time;

    @Column(name = "s_number")
    private Integer s_number; // 排序

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catalog_id")
    private Catalog catalog;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;


    public Integer getPage_id() {
        return page_id;
    }

    public void setPage_id(Integer page_id) {
        this.page_id = page_id;
    }

    public Integer getS_number() {
        return s_number;
    }

    public void setS_number(Integer s_number) {
        this.s_number = s_number;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getPage_title() {
        return page_title;
    }

    public void setPage_title(String page_title) {
        this.page_title = page_title;
    }

    public String getPage_content() {
        return page_content;
    }

    public void setPage_content(String page_content) {
        this.page_content = page_content;
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
        return "Page{" +
                "page_id=" + page_id +
                ", page_title='" + page_title + '\'' +
                ", page_content='" + page_content + '\'' +
                ", is_del=" + is_del +
                ", create_time=" + create_time +
                ", s_number=" + s_number +
                ", catalog=" + catalog +
                ", item=" + item +
                '}';
    }
}
