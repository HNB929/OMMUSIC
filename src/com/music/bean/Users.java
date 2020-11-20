package com.music.bean;

import java.util.List;

public class Users {
    private Integer usersId;

    private String username;

    private String password;

    private Integer role;

    private String registerTime;

    private String picture;

    private String remark;

    private String tel;
    //对应用户的全部评论
    private List<Comments> commentsList;
    
    //对应用户的全部评论回复
    private List<ReplyComment> replyCommentList;

	public Integer getUsersId() {
		return usersId;
	}

	public void setUsersId(Integer usersId) {
		this.usersId = usersId;
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

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public List<Comments> getCommentsList() {
		return commentsList;
	}

	public void setCommentsList(List<Comments> commentsList) {
		this.commentsList = commentsList;
	}

	public List<ReplyComment> getReplyCommentList() {
		return replyCommentList;
	}

	public void setReplyCommentList(List<ReplyComment> replyCommentList) {
		this.replyCommentList = replyCommentList;
	}

	@Override
	public String toString() {
		return "Users [usersId=" + usersId + ", username=" + username + ", password=" + password + ", role=" + role
				+ ", registerTime=" + registerTime + ", picture=" + picture + ", remark=" + remark + ", tel=" + tel
				+ ", commentsList=" + commentsList + ", replyCommentList=" + replyCommentList + "]";
	}
    
}