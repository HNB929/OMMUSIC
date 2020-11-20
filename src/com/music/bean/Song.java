package com.music.bean;

import java.util.List;

public class Song {
    private Integer songId;
    private String songName;
    private String releaseTime;
    private String lyric;
    private String mp3;
    private Integer playCount;
    private String duration;
    private Integer singerid;
    private Integer songTypeid;
    private String remark;
    private Singer singer;
    private SongType songType;
    //对应歌曲的全部评论
    private List<Comments> commentsList;
    
	public Integer getSongId() {
		return songId;
	}
	public void setSongId(Integer songId) {
		this.songId = songId;
	}
	public String getSongName() {
		return songName;
	}
	public void setSongName(String songName) {
		this.songName = songName;
	}
	public String getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}
	public String getLyric() {
		return lyric;
	}
	public void setLyric(String lyric) {
		this.lyric = lyric;
	}
	public String getMp3() {
		return mp3;
	}
	public void setMp3(String mp3) {
		this.mp3 = mp3;
	}
	public Integer getPlayCount() {
		return playCount;
	}
	public void setPlayCount(Integer playCount) {
		this.playCount = playCount;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public Integer getSingerid() {
		return singerid;
	}
	public void setSingerid(Integer singerid) {
		this.singerid = singerid;
	}
	public Integer getSongTypeid() {
		return songTypeid;
	}
	public void setSongTypeid(Integer songTypeid) {
		this.songTypeid = songTypeid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Singer getSinger() {
		return singer;
	}
	public void setSinger(Singer singer) {
		this.singer = singer;
	}
	public SongType getSongType() {
		return songType;
	}
	public void setSongType(SongType songType) {
		this.songType = songType;
	}
	public List<Comments> getCommentsList() {
		return commentsList;
	}
	public void setCommentsList(List<Comments> commentsList) {
		this.commentsList = commentsList;
	}
	@Override
	public String toString() {
		return "Song [songId=" + songId + ", songName=" + songName + ", releaseTime=" + releaseTime + ", lyric=" + lyric
				+ ", mp3=" + mp3 + ", playCount=" + playCount + ", duration=" + duration + ", singerid=" + singerid
				+ ", songTypeid=" + songTypeid + ", remark=" + remark + ", singer=" + singer + ", songType=" + songType
				+ ", commentsList=" + commentsList + "]";
	}
    

}