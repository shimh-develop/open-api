package com.shimh.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 *
 * @author shimh
 * <p>
 * 2018年1月23日
 */
@Entity
@Table(name = "sys_user")
@Where(clause="is_del = 0")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "username", length = 20)
    private String username;

    /**
     * 使用md5(username + original password + salt)加密存储
     */
    @Column(name = "password", length = 64)
    private String password;

    /**
     * 加密密码时使用的种子
     */
    private String salt;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;


    /**
     * 最后一次登录时间
     */
    @Column(name = "last_login")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;

    /**
     * 是否是管理员
     */
    private Boolean admin = false;

    @Column(name = "is_del")
    private int is_del = 0; //删除 0 正常 1 删除

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public int getIs_del() {
        return is_del;
    }

    public void setIs_del(int is_del) {
        this.is_del = is_del;
    }
}
