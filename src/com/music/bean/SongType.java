package com.music.bean;

import java.util.List;

public class SongType {
    private Integer songTypeId;

    private String songTypeName;

    private String remark;
    //对应类别的全部歌曲
    private List<Song> songList;
    
    
    
	public Integer getSongTypeId() {
		return songTypeId;
	}
	public void setSongTypeId(Integer songTypeId) {
		this.songTypeId = songTypeId;
	}
	public String getSongTypeName() {
		return songTypeName;
	}
	public void setSongTypeName(String songTypeName) {
		this.songTypeName = songTypeName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public List<Song> getSongList() {
		return songList;
	}
	public void setSongList(List<Song> songList) {
		this.songList = songList;
	}
	@Override
	public String toString() {
		return "SongType [songTypeId=" + songTypeId + ", songTypeName=" + songTypeName + ", remark=" + remark
				+ ", songList=" + songList + "]";
	}
 
}