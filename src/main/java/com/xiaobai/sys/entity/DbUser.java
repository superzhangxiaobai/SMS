package com.xiaobai.sys.entity;

/*
 * 测试用户对象
 */
public class DbUser {
	private String username;
	private String password;
	private Integer access_level;
	private String description;

	public Integer getAccess_level() {
		return access_level;
	}

	public void setAccess_level(Integer access_level) {
		this.access_level = access_level;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String toString() {
		if(access_level==0)
		{

			return "username["+username+"], password["+password+"], access_level[role_admin], description["+description+"]";
		}
		return "username["+username+"], password["+password+"], access_level[role_user], description["+description+"]";
	}
}
