package com.music.bean;
/**
 * 用户信息实体类
 * @author HNB
 *
 */
public class UserModel {
	/** 用户编号 */
    private Integer id;
    /** 用户名 */
    private String username;
    /** 用户密码 */
    private String password;
    /** 用户角色 1.用户， 0.管理员 */
    private Integer role;
    /** 注册时间 */
    private String time;
    /** 用户图片地址 */
    private String picture;
    /** 用户手机号 */
    private String tel;
    /** 用户备注 */
    private String remark;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "UserModel [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role
				+ ", time=" + time + ", picture=" + picture + ", tel=" + tel + ", remark=" + remark + "]";
	}

    
   
}