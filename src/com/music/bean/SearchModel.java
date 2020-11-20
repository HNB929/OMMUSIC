package com.music.bean;

/**
 * 搜索表实体类
 * @author HNB
 *
 */
/**
 * @author HNB
 *
 */
/**
 * @author HNB
 *
 */
public class SearchModel {
	/** 搜索编号 */
    private Integer id;
    /** 搜索内容 */
    private String content;
    /** 内容搜索次数 */
    private Integer count;
    /** 备注 */
    private String remark;
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
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "SearchModel [id=" + id + ", content=" + content + ", count=" + count + ", remark=" + remark + "]";
	}
    

}