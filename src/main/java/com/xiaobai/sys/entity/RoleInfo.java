package com.xiaobai.sys.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="t_role")
public class RoleInfo {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY,generator="Mysql")
    private Long id;
    @Column(name = "role")
    private String role;
    @Column(name = "rolename")
    private String rolename;
    @Column(name = "menus")
    private String menus;
    @Column(name = "status")
    private Integer status;
    @Column(name = "memo")
    private String memo;
    // 创建时间
    @Column(name = "createtime",updatable = false)
    private Date createtime;
    // 创建人
    @Column(name = "creator",updatable = false)
    private String creator;

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getRole() {
        return role;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMenus() {
        return menus;
    }

    public void setMenus(String menus) {
        this.menus = menus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
