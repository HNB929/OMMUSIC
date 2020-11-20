package com.music.bean;

/**
 * 回复评论实体类
 * @author HNB
 *
 */
/**
 * @author HNB
 *
 */
public class ReplyCommentModel {
	/** 回复评论编号 */
    private Integer id;
    /** 回复评论内容 */
    private String content;
    /** 回复评论时间 */
    private String time;
    /** 对应评论编号 */
    private Integer commentsid;
    /** 对应用户编号 */
    private Integer userid;
    /** 备注信息 */
    private String remark;
    
    /** 用户名称 */
    private String userName;
    /** 用户图片地址 */
    private String userPicture;
    
    
	public String getUserPicture() {
		return userPicture;
	}
	public void setUserPicture(String userPicture) {
		this.userPicture = userPicture;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getCommentsid() {
		return commentsid;
	}
	public void setCommentsid(Integer commentsid) {
		this.commentsid = commentsid;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "ReplyCommentModel [id=" + id + ", content=" + content + ", time=" + time + ", commentsid=" + commentsid
				+ ", userid=" + userid + ", remark=" + remark + ", userName=" + userName + ", userPicture="
				+ userPicture + "]";
	}
    
    
  
}