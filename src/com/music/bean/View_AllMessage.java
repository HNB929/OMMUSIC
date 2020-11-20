package com.music.bean;

public class View_AllMessage {
    /** 歌曲编号 */
	private Integer songid;
	private String songname;
	private Integer songcount;
	private String songtime;
	private Integer singerid;
	private String singername;
	private String picture;
	private Integer singertypeid;
	private String singertypename;
	private Integer songtypeid;
	private String songtypename;
	private String uptime;
	private Song song;
	
	public Song getSong() {
		return song;
	}
	public void setSong(Song song) {
		this.song = song;
	}
	@Override
	public String toString() {
		return "View_AllMessage [songid=" + songid + ", songname=" + songname + ", songcount=" + songcount
				+ ", songtime=" + songtime + ", singerid=" + singerid + ", singername=" + singername + ", picture="
				+ picture + ", singertypeid=" + singertypeid + ", singertypename=" + singertypename + ", songtypeid="
				+ songtypeid + ", songtypename=" + songtypename + ", uptime=" + uptime + "]";
	}
	public String getUptime() {
		return uptime;
	}
	public void setUptime(String uptime) {
		this.uptime = uptime;
	}
	public Integer getSongid() {
		return songid;
	}
	public void setSongid(Integer songid) {
		this.songid = songid;
	}
	public String getSongname() {
		return songname;
	}
	public void setSongname(String songname) {
		this.songname = songname;
	}
	public Integer getSongcount() {
		return songcount;
	}
	public void setSongcount(Integer songcount) {
		this.songcount = songcount;
	}
	public String getSongtime() {
		return songtime;
	}
	public void setSongtime(String songtime) {
		this.songtime = songtime;
	}
	public Integer getSingerid() {
		return singerid;
	}
	public void setSingerid(Integer singerid) {
		this.singerid = singerid;
	}
	public String getSingername() {
		return singername;
	}
	public void setSingername(String singername) {
		this.singername = singername;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Integer getSingertypeid() {
		return singertypeid;
	}
	public void setSingertypeid(Integer singertypeid) {
		this.singertypeid = singertypeid;
	}
	public String getSingertypename() {
		return singertypename;
	}
	public void setSingertypename(String singertypename) {
		this.singertypename = singertypename;
	}
	public Integer getSongtypeid() {
		return songtypeid;
	}
	public void setSongtypeid(Integer songtypeid) {
		this.songtypeid = songtypeid;
	}
	public String getSongtypename() {
		return songtypename;
	}
	public void setSongtypename(String songtypename) {
		this.songtypename = songtypename;
	}

}
