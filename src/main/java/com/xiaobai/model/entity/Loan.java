package com.xiaobai.model.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_loan")
public class Loan {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY,generator="Mysql")
    private Long id;
    @Column(name = "userid")
    private Integer userid;
    @Column(name = "createtime",updatable = false)
    private Date createtime;//数据库已设置默认值
    @Column(name = "creator")
    private String creator;
    @Column(name = "status")
    private Integer status;
    @Column(name = "loan")
    private Double loan;
    @Column(name = "realloan")
    private Double realloan;
    @Column(name = "ispay")
    private Integer ispay;
    @Column(name = "loaner")
    private String loaner;
    @Column(name = "confirmor")
    private String confirmor;
    @Column(name = "memo")
    private String memo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", userid=" + userid +
                ", createtime=" + createtime +
                ", creator='" + creator + '\'' +
                ", status=" + status +
                ", loan=" + loan +
                ", realloan=" + realloan +
                ", ispay=" + ispay +
                ", loaner='" + loaner + '\'' +
                ", confirmor='" + confirmor + '\'' +
                ", memo='" + memo + '\'' +
                '}';
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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

    public Double getLoan() {
        return loan;
    }

    public void setLoan(Double loan) {
        this.loan = loan;
    }

    public Double getRealloan() {
        return realloan;
    }

    public void setRealloan(Double realloan) {
        this.realloan = realloan;
    }

    public Integer getIspay() {
        return ispay;
    }

    public void setIspay(Integer ispay) {
        this.ispay = ispay;
    }

    public String getLoaner() {
        return loaner;
    }

    public void setLoaner(String loaner) {
        this.loaner = loaner;
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
