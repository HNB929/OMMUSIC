package com.music.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.music.bean.SearchModel;

public interface SearchDao {
    
    
    /**
     * 根据搜索内容获取搜索信息
     * @param searchContent
     * @return
     */
    SearchModel findByContent(@Param("searchContent")String searchContent);
    
	/**
	 * 数据插入
	 * @param searchModel
	 * @return
	 */
    int insert(SearchModel searchModel);
    
    /**
     * 数据更新
     * @param searchModel
     * @return
     */
	Integer update(SearchModel searchModel);
	
    /**
     * 获取热门搜索信息
     * @return
     */
    List<SearchModel> findHot();
}