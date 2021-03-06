package com.xiaobai.sys.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户表,包含基础信息
 */
@Entity
@Table(name = "t_user")
public class UserInfo implements Serializable {
	// 记录id
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY,generator="Mysql")
	private Long id;
	// 用户登录名
	@Column(name = "loginname")
	private String loginname;
	// 用户昵称
	@Column(name = "username")
	private String username;
	// 登录密码
	@Column(name = "password")
	private String password;
	// 加密盐
	@Column(name = "salt")
	private String salt;
	// 用户电话
	@Column(name = "tel")
	private String tel;
	// 身份证号
	@Column(name = "cardid")
	private String cardid;
	//图片地址
	@Column(name = "cardpic")
	private String cardpic;
	// 邮箱地址
	@Column(name = "email")
	private String email;
	// 家庭地址
	@Column(name = "address")
	private String address;
	// 创建时间
	@Column(name = "createtime",updatable = false)
	private Date createtime;
	// 创建人
	@Column(name = "creator",updatable = false)
	private String creator;
	// 备注
	@Column(name = "memo")
	private String memo;
    // 角色id
    @Column(name = "roles")
    private String roles;
	// 状态
	@Column(name = "status")
	private Integer status;
	// 是否激活
	@Column(name = "isactive")
	private Integer isactive;
	public UserInfo(){}
	public UserInfo(Long id,String loginname) {
		this.id = id;
		this.loginname = loginname;
	}

	public String getCardpic() {
		return cardpic;
	}

	public void setCardpic(String cardpic) {
		this.cardpic = cardpic;
	}

	public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsactive() {
		return isactive;
	}

	public void setIsactive(Integer isactive) {
		this.isactive = isactive;
	}
}
