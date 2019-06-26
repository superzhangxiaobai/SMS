package com.xiaobai.model.entity;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "t_monthsalary")
public class Monthsalary {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY,generator="Mysql")
    private Long id;
    @Column(name = "month")
    private String month;
    @Column(name = "createtime",updatable = false)
    private Date createtime;
    @Column(name = "creator")
    private String creator;
    @Column(name = "status")
    private Integer status;
    @Column(name = "userid")
    private Integer userid;
    @Column(name = "countsign")
    private Double countsign;
    @Column(name = "counteffective")
    private Double counteffective;
    @Column(name = "ispay")
    private Integer ispay;
    @Column(name = "salary")
    private Double salary;
    @Column(name = "sumloan")
    private Double sumloan;
    @Column(name = "realpay")
    private Double realpay;
    @Column(name = "balance")
    private Double balance;
    @Column(name = "confirmor")
    private String confirmor;
    @Column(name = "memo")
    private String memo;

    @Override
    public String toString() {
        return "Monthsalary{" +
                "id=" + id +
                ", month='" + month + '\'' +
                ", createtime=" + createtime +
                ", creator='" + creator + '\'' +
                ", status=" + status +
                ", userid=" + userid +
                ", countsign=" + countsign +
                ", counteffective=" + counteffective +
                ", ispay=" + ispay +
                ", salary=" + salary +
                ", sumloan=" + sumloan +
                ", realpay=" + realpay +
                ", balance=" + balance +
                ", confirmor='" + confirmor + '\'' +
                ", memo='" + memo + '\'' +
                '}';
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
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

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Double getCountsign() {
        return countsign;
    }

    public void setCountsign(Double countsign) {
        this.countsign = countsign;
    }

    public Double getCounteffective() {
        return counteffective;
    }

    public void setCounteffective(Double counteffective) {
        this.counteffective = counteffective;
    }

    public Integer getIspay() {
        return ispay;
    }

    public void setIspay(Integer ispay) {
        this.ispay = ispay;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getSumloan() {
        return sumloan;
    }

    public void setSumloan(Double sumloan) {
        this.sumloan = sumloan;
    }

    public Double getRealpay() {
        return realpay;
    }

    public void setRealpay(Double realpay) {
        this.realpay = realpay;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
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
