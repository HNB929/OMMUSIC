package com.music.bean.view;

import java.util.List;

public class SingerAndSongViewModel {
	/** 歌手编号 */
    private Integer id;
    /** 歌手姓名 */
    private String name;
    /** 性别 */
    private String sex;
    /** 图片地址 */
    private String picture;
    /** 类型编号 */
    private Integer singer_typeid;
    /** 首字母 */
    private String firstname;
    
    /** 歌手类别名称 */
    private String singerTypeName;
    
    /** 歌手歌曲列表 */
    private List<SongViewModel> songList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Integer getSinger_typeid() {
		return singer_typeid;
	}

	public void setSinger_typeid(Integer singer_typeid) {
		this.singer_typeid = singer_typeid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSingerTypeName() {
		return singerTypeName;
	}

	public void setSingerTypeName(String singerTypeName) {
		this.singerTypeName = singerTypeName;
	}

	public List<SongViewModel> getSongList() {
		return songList;
	}

	public void setSongList(List<SongViewModel> songList) {
		this.songList = songList;
	}

	@Override
	public String toString() {
		return "SingerAndSongViewModel [id=" + id + ", name=" + name + ", sex=" + sex + ", picture=" + picture
				+ ", singer_typeid=" + singer_typeid + ", firstname=" + firstname + ", singerTypeName=" + singerTypeName
				+ ", songList=" + songList + "]";
	}
    

}