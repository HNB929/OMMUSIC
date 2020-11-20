package com.music.bean;

import java.util.List;

import com.music.bean.view.SongViewModel;

public class PlayList {
	/** 用户对象 */
	private UserModel userModel;
	/** 歌曲列表 */
	private List<SongViewModel> songList;
	
	
	public UserModel getUserModel() {
		return userModel;
	}
	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}
	public List<SongViewModel> getSongList() {
		return songList;
	}
	public void setSongList(List<SongViewModel> songList) {
		this.songList = songList;
	}
	@Override
	public String toString() {
		return "PlayList [userModel=" + userModel + ", songList=" + songList + "]";
	}
	
	
	
}
