package com.music.bean;

import java.util.List;

public class SingerType {
    private Integer singerTypeId;

    private String singerTypeName;

    private String remark;
    //相应类别的歌手集合
    private List<Singer> singerList;
	public Integer getSingerTypeId() {
		return singerTypeId;
	}
	public void setSingerTypeId(Integer singerTypeId) {
		this.singerTypeId = singerTypeId;
	}
	public String getSingerTypeName() {
		return singerTypeName;
	}
	public void setSingerTypeName(String singerTypeName) {
		this.singerTypeName = singerTypeName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public List<Singer> getSingerList() {
		return singerList;
	}
	public void setSingerList(List<Singer> singerList) {
		this.singerList = singerList;
	}
    
    

}