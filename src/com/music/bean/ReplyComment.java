package com.music.bean;

public class ReplyComment {
    private Integer rId;

    private String rContent;

    private String rTime;

    private Integer commentsid;

    private String remark;

    private Integer usersid;
    
    private Users users;
    
    private Comments comments;

	@Override
	public String toString() {
		return "ReplyComment [rId=" + rId + ", rContent=" + rContent + ", rTime=" + rTime + ", commentsid=" + commentsid
				+ ", remark=" + remark + ", usersid=" + usersid + ", users=" + users + ", comments=" + comments + "]";
	}

	public Integer getrId() {
		return rId;
	}

	public void setrId(Integer rId) {
		this.rId = rId;
	}

	public String getrContent() {
		return rContent;
	}

	public void setrContent(String rContent) {
		this.rContent = rContent;
	}

	public String getrTime() {
		return rTime;
	}

	public void setrTime(String rTime) {
		this.rTime = rTime;
	}

	public Integer getCommentsid() {
		return commentsid;
	}

	public void setCommentsid(Integer commentsid) {
		this.commentsid = commentsid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getUsersid() {
		return usersid;
	}

	public void setUsersid(Integer usersid) {
		this.usersid = usersid;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Comments getComments() {
		return comments;
	}

	public void setComments(Comments comments) {
		this.comments = comments;
	}
    
  
}