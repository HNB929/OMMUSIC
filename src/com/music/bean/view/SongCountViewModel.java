package com.music.bean.view;

/**
 * 前端首页获取歌手歌曲总数和总歌曲播放次数之和
 * @author HNB
 *
 */
public class SongCountViewModel{
	/** 歌手编号 */
	private Integer singerId;
	/** 歌手姓名 */
	private String singerName;
	/** 歌手歌曲总数 */
	private Integer songCount;
	/** 歌曲歌曲总播放次数 */
	private Integer sumCount;
	
	
	public Integer getSingerId() {
		return singerId;
	}
	public void setSingerId(Integer singerId) {
		this.singerId = singerId;
	}
	public String getSingerName() {
		return singerName;
	}
	public void setSingerName(String singerName) {
		this.singerName = singerName;
	}
	public Integer getSongCount() {
		return songCount;
	}
	public void setSongCount(Integer songCount) {
		this.songCount = songCount;
	}
	public Integer getSumCount() {
		return sumCount;
	}
	public void setSumCount(Integer sumCount) {
		this.sumCount = sumCount;
	}
	@Override
	public String toString() {
		return "SongCountViewModel [singerId=" + singerId + ", singerName=" + singerName + ", songCount=" + songCount
				+ ", sumCount=" + sumCount + "]";
	}

	
	
}
