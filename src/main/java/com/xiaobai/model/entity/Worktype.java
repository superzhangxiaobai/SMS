package com.xiaobai.model.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_worktype")
public class Worktype {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY,generator="Mysql")
    private Long id;
    @Column(name = "worktype")
    private String worktype;
    @Column(name = "counttype")
    private String counttype;
    @Column(name = "createtime",updatable = false)
    private Date createtime;
    @Column(name = "creator")
    private String creator;
    @Column(name = "status")
    private Integer status;
    @Column(name = "salary")
    private Double salary;
    @Column(name = "memo")
    private String memo;

    @Override
    public String toString() {
        return "Worktype{" +
                "id=" + id +
                ", worktype='" + worktype + '\'' +
                ", counttype='" + counttype + '\'' +
                ", createtime=" + createtime +
                ", creator='" + creator + '\'' +
                ", status=" + status +
                ", salary=" + salary +
                ", memo='" + memo + '\'' +
                '}';
    }

    public String getWorktype() {
        return worktype;
    }

    public void setWorktype(String worktype) {
        this.worktype = worktype;
    }

    public String getCounttype() {
        return counttype;
    }

    public void setCounttype(String counttype) {
        this.counttype = counttype;
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

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
