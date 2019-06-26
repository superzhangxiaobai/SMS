package com.xiaobai.sys.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_role_menu")
public class RoleMenu {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY,generator="Mysql")
    private Long id;
    @Column(name = "roleid")
    private Long roleid;
    @Column(name = "menuid")
    private Long menuid;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    public Long getMenuid() {
        return menuid;
    }

    public void setMenuid(Long menuid) {
        this.menuid = menuid;
    }
}
