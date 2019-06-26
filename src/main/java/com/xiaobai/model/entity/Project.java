package com.xiaobai.model.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_project")
public class Project {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY,generator="Mysql")
    private Long id;
    @Column(name = "project")
    private String project;
    @Column(name = "workplace")
    private String workplace;//用于签到
    @Column(name = "createtime",updatable = false)
    private Date createtime;
    @Column(name = "creator")
    private String creator;
    @Column(name = "status")
    private Integer status;
    @Column(name = "worktypeid")
    private Integer worktypeid;
    @Column(name = "estimateduration")
    private Double estimateduration;
    @Column(name = "realduration")
    private Double realduration;
    @Column(name = "projectamount")
    private Double projectamount;
    @Column(name = "realamount")
    private Double realamount;
    @Column(name = "ispay")
    private Integer ispay;
    @Column(name = "confirmor")
    private String confirmor;
    @Column(name = "memo")
    private String memo;

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", project='" + project + '\'' +
                ", workplace='" + workplace + '\'' +
                ", createtime=" + createtime +
                ", creator='" + creator + '\'' +
                ", status=" + status +
                ", worktypeid=" + worktypeid +
                ", estimateduration=" + estimateduration +
                ", realduration=" + realduration +
                ", projectamount=" + projectamount +
                ", realamount=" + realamount +
                ", ispay=" + ispay +
                ", confirmor='" + confirmor + '\'' +
                ", memo='" + memo + '\'' +
                '}';
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
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

    public Integer getWorktypeid() {
        return worktypeid;
    }

    public void setWorktypeid(Integer worktypeid) {
        this.worktypeid = worktypeid;
    }

    public Double getEstimateduration() {
        return estimateduration;
    }

    public void setEstimateduration(Double estimateduration) {
        this.estimateduration = estimateduration;
    }

    public Double getRealduration() {
        return realduration;
    }

    public void setRealduration(Double realduration) {
        this.realduration = realduration;
    }

    public Double getProjectamount() {
        return projectamount;
    }

    public void setProjectamount(Double projectamount) {
        this.projectamount = projectamount;
    }

    public Double getRealamount() {
        return realamount;
    }

    public void setRealamount(Double realamount) {
        this.realamount = realamount;
    }

    public Integer getIspay() {
        return ispay;
    }

    public void setIspay(Integer ispay) {
        this.ispay = ispay;
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
