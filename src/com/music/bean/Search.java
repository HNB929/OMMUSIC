package com.music.bean;

public class Search {
    private Integer searchId;

    private String searchContent;

    private Integer searchCount;

    private String remark;

	public Integer getSearchId() {
		return searchId;
	}

	public void setSearchId(Integer searchId) {
		this.searchId = searchId;
	}

	public String getSearchContent() {
		return searchContent;
	}

	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}

	public Integer getSearchCount() {
		return searchCount;
	}

	public void setSearchCount(Integer searchCount) {
		this.searchCount = searchCount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Search [searchId=" + searchId + ", searchContent=" + searchContent + ", searchCount=" + searchCount
				+ ", remark=" + remark + "]";
	}
    
    
}