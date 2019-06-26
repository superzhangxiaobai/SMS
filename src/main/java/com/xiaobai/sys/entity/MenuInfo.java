package com.xiaobai.sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_menu")
public class MenuInfo {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY,generator="Mysql")
    private Integer id;
    @Column(name = "menuname")
    private String menuname;
    @Column(name = "url")
    private String url;
    @Column(name = "icon")
    private String icon;
    @Column(name = "pid")
    private Integer pid;
    @Column(name = "isEnable")
    private Integer isEnable;
    @Column(name = "status")
    private Integer status=1;
    @Column(name = "type")
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // 创建时间
    @Column(name = "createtime",updatable = false)
    private Date createtime;
    // 创建人
    @Column(name = "creator",updatable = false)
    private String creator;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
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

    @Override
    public String toString() {
        return "MenuInfo{" +
            "id=" + id +
            ", menuname='" + menuname + '\'' +
            ", url='" + url + '\'' +
            ", icon='" + icon + '\'' +
            ", pid=" + pid +
            ", isEnable=" + isEnable +
            ", status=" + status +
            ", type='" + type + '\'' +
            ", createtime=" + createtime +
            ", creator='" + creator + '\'' +
            '}';
    }
}
