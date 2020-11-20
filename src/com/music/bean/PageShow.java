package com.music.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 跟分页查询相关的类
 * 
 * @author Administrator
 * 
 */
public class PageShow<T> {

	private int pageSize=6;// 页面容量
	private int total;// 总笔数
	private int allPage;// 总页数
	private int currPage;// 当前页
	private int prevPage;// 上一页
	private int nextPage;// 下一页

	// 分页信息
	private List<T> list = new ArrayList<T>();

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;

		// 计算总页数 12笔，5笔 --》 3页； 12笔，6笔 --》 2页
		if (this.total % this.pageSize == 0) {
			this.allPage = this.total / this.pageSize;
		} else {
			this.allPage = (this.total / this.pageSize) + 1;
		}

	}

	public int getAllPage() {
		return allPage;
	}

	public int getCurrPage() {
		return currPage;
	}

	/**
	 * 设置当前页
	 * 
	 * @param currPage
	 */
	public void setCurrPage(int currPage) {
		if (this.allPage == 1) {// 只有一页
			this.prevPage = 1;
			this.currPage = 1;
			this.nextPage = 1;
		}else if(currPage<=1){ //此时，在第一页
			this.prevPage = 1;
			this.currPage = 1;
			this.nextPage = 2;	
		}else if(currPage>=this.allPage){ //此时是在最后一页
			this.prevPage = this.allPage-1;
			this.currPage = this.allPage;
			this.nextPage = this.allPage;	
		}else{	
			this.currPage = currPage;
			this.prevPage = currPage - 1;
			this.nextPage = currPage + 1;
		}


	}

	public int getPrivPage() {
		return prevPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}

}
