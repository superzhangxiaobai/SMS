package com.xiaobai.model.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_sign")
public class Sign {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY,generator="Mysql")
    private Long id;
    @Column(name = "userid")
    private Long userid;
    @Column(name = "signdate")
    private Date signdate;
    @Column(name = "createtime",updatable = false)
    private Date createtime;
    @Column(name = "creator")
    private String creator;
    @Column(name = "status")
    private Integer status;
    @Column(name = "iseffective")
    private Integer iseffective;
    @Column(name = "workhour")
    private Double workhour;
    @Column(name = "workplace")
    private String workplace;
    @Column(name = "projectid")
    private Integer projectid;
    @Column(name = "confirmor")
    private String confirmor;
    @Column(name = "memo")
    private String memo;

    @Override
    public String toString() {
        return "Sign{" +
                "id=" + id +
                ", userid=" + userid +
                ", signdate=" + signdate +
                ", createtime=" + createtime +
                ", creator='" + creator + '\'' +
                ", status=" + status +
                ", iseffective=" + iseffective +
                ", workhour=" + workhour +
                ", workplace='" + workplace + '\'' +
                ", projectid=" + projectid +
                ", confirmor='" + confirmor + '\'' +
                ", memo='" + memo + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Date getSigndate() {
        return signdate;
    }

    public void setSigndate(Date signdate) {
        this.signdate = signdate;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIseffective() {
        return iseffective;
    }

    public void setIseffective(Integer iseffective) {
        this.iseffective = iseffective;
    }

    public Double getWorkhour() {
        return workhour;
    }

    public void setWorkhour(Double workhour) {
        this.workhour = workhour;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public String getConfirmor() {
        return confirmor;
    }

    public void setConfirmor(String confirmor) {
        this.confirmor = confirmor;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
