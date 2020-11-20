package com.music.bean;

import java.util.List;

public class Comments {
	private Integer commentsId;

	private String commentsContent;

	private String commentsTime;

	private Integer greatCount;

	private Integer userid;

	private Integer songid;

	private String remark;
	
	private Users users;
	
	private Song song;
	
	//对应评论的全部回复
	private List<ReplyComment> replyCommentList;

	public Integer getCommentsId() {
		return commentsId;
	}

	public void setCommentsId(Integer commentsId) {
		this.commentsId = commentsId;
	}

	public String getCommentsContent() {
		return commentsContent;
	}

	public void setCommentsContent(String commentsContent) {
		this.commentsContent = commentsContent;
	}

	public String getCommentsTime() {
		return commentsTime;
	}

	public void setCommentsTime(String commentsTime) {
		this.commentsTime = commentsTime;
	}

	public Integer getGreatCount() {
		return greatCount;
	}

	public void setGreatCount(Integer greatCount) {
		this.greatCount = greatCount;
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

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Song getSong() {
		return song;
	}

	public void setSong(Song song) {
		this.song = song;
	}

	public List<ReplyComment> getReplyCommentList() {
		return replyCommentList;
	}

	public void setReplyCommentList(List<ReplyComment> replyCommentList) {
		this.replyCommentList = replyCommentList;
	}

	@Override
	public String toString() {
		return "Comments [commentsId=" + commentsId + ", commentsContent=" + commentsContent + ", commentsTime="
				+ commentsTime + ", greatCount=" + greatCount + ", userid=" + userid + ", songid=" + songid
				+ ", remark=" + remark + ", users=" + users + ", song=" + song + ", replyCommentList="
				+ replyCommentList + "]";
	}
	
	
	

}