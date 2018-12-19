package com.shimh.entity;


import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author: shimh
 * @create: 2018年11月
 **/
@Entity
@Table(name = "item")
@Where(clause="is_del = 0")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer item_id;

    @Column(name = "item_type")
    private int item_type; // 0 开放API 1 常规 2 单页

    @Column(name = "item_name", length = 20)
    private String item_name;

    @Column(name = "password", length = 10)
    private String password;

    @Column(name = "item_description", length = 200)
    private String item_description;

    @Column(name = "is_del")
    private int is_del = 0; //删除 0 正常 1 删除

    @Column(name = "top")
    private int top = 0; //是否置顶 1 置顶 0 否

    //@JSONField(format = "yyyy.MM.dd HH:mm")
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date create_time;

    //@JSONField(format = "yyyy.MM.dd HH:mm")
    @Column(name = "last_update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date last_update_time;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item", orphanRemoval = true)
    @OrderBy("s_number asc")
    private List<Catalog> catalogs;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item", orphanRemoval = true)
    @OrderBy("s_number asc")
    private List<Page> pages;



    public Integer getItem_id() {
        return item_id;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
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

    public Date getLast_update_time() {
        return last_update_time;
    }

    public void setLast_update_time(Date last_update_time) {
        this.last_update_time = last_update_time;
    }

    public int getItem_type() {
        return item_type;
    }

    public void setItem_type(int item_type) {
        this.item_type = item_type;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public List<Catalog> getCatalogs() {
        return catalogs;
    }

    public void setCatalogs(List<Catalog> catalogs) {
        this.catalogs = catalogs;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Item{" +
                "item_id=" + item_id +
                ", item_type=" + item_type +
                ", item_name='" + item_name + '\'' +
                ", password='" + password + '\'' +
                ", item_description='" + item_description + '\'' +
                ", is_del=" + is_del +
                ", top=" + top +
                ", create_time=" + create_time +
                ", last_update_time=" + last_update_time +
                ", catalogs=" + catalogs +
                ", pages=" + pages +
                '}';
    }
}
