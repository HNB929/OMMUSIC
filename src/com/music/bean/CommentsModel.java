package com.music.bean;

import java.util.List;

public class CommentsModel {
	/** 评论主键 */
	private Integer id;
	/** 评论内容 */
	private String content;
	/** 评论时间 */
	private String time;
	/** 评论点赞次数 */
	private Integer count;
	/** 评论用户编号 */
	private Integer userid;
	/** 歌曲编号 */
	private Integer songid;
	/** 备注 */
	private String remark;
	
	
	/** 回复评论集合 */
	private List<ReplyCommentModel> replyCommentModelList;
	
	/** 评论用户名称 */
	private String userName;
	
	/** 评论用户头像地址 */
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
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getSongid() {
		return songid;
	}
	public void setSongid(Integer songid) {
		this.songid = songid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public List<ReplyCommentModel> getReplyCommentModelList() {
		return replyCommentModelList;
	}
	public void setReplyCommentModelList(List<ReplyCommentModel> replyCommentModelList) {
		this.replyCommentModelList = replyCommentModelList;
	}
	@Override
	public String toString() {
		return "CommentsModel [id=" + id + ", content=" + content + ", time=" + time + ", count=" + count + ", userid="
				+ userid + ", songid=" + songid + ", remark=" + remark + ", replyCommentModelList="
				+ replyCommentModelList + ", userName=" + userName + ", userPicture=" + userPicture + "]";
	}

	
	
	

}