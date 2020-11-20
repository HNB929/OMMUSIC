package com.music.bean.view;

/**
 * @author HNB
 *
 */
public class SongViewModel {
	/** 歌曲编号 */
    private Integer id;
    /** 歌曲名称 */
    private String name;
    /** 上架时间 */
    private String time;
    /** 歌词地址 */
    private String lyric;
    /** 音频地址 */
    private String mp3;
    /** 播放次数） */
    private Integer count;
    /** 时长 */
    private String duration;
    /** 歌手编号 */
    private Integer singerid;
    /** 歌曲类型编号 */
    private Integer song_typeid;
    /** 备注 */
    private String remark;
    
    
    /** 歌曲类别名称 */
    private String songTypeName;
    
    /** 歌手姓名 */
    private String singerName;
    
    /** 歌手图片地址 */
    private String singerPicture;
    
    

	public String getSingerPicture() {
		return singerPicture;
	}

	public void setSingerPicture(String singerPicture) {
		this.singerPicture = singerPicture;
	}

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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
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

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
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

	public Integer getSong_typeid() {
		return song_typeid;
	}

	public void setSong_typeid(Integer song_typeid) {
		this.song_typeid = song_typeid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSongTypeName() {
		return songTypeName;
	}

	public void setSongTypeName(String songTypeName) {
		this.songTypeName = songTypeName;
	}

	public String getSingerName() {
		return singerName;
	}

	public void setSingerName(String singerName) {
		this.singerName = singerName;
	}

	@Override
	public String toString() {
		return "SongViewModel [id=" + id + ", name=" + name + ", time=" + time + ", lyric=" + lyric + ", mp3=" + mp3
				+ ", count=" + count + ", duration=" + duration + ", singerid=" + singerid + ", song_typeid="
				+ song_typeid + ", remark=" + remark + ", songTypeName=" + songTypeName + ", singerName=" + singerName
				+ ", singerPicture=" + singerPicture + "]";
	}
    
    
    
    
}