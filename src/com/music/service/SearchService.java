package com.music.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.music.bean.SearchModel;
import com.music.dao.SearchDao;
import com.music.dao.SingerDao;
import com.music.dao.SongDao;
/**
 * search service
 * @author HNB
 *
 */
@Service
public class SearchService {
	@Resource
	private SearchDao searchDao;
	@Resource
	private SingerDao singerDao;
	@Resource
	private SongDao songDao;

	/**
	 * 搜索表数据处理
	 * @param searchmsg
	 */
	public Integer searchHandler(String searchContent) {
		// 1.根据查询内容获取查询对象
		SearchModel searchModel = this.searchDao.findByContent(searchContent);
		
		// 2.判断搜索对象是否为空
		if(searchModel == null) {
			// 2.1 数据插入
			searchModel = new SearchModel();
			searchModel.setContent(searchContent);
			searchModel.setCount(1);
			return this.insert(searchModel);
		}else {
			searchModel.setCount(searchModel.getCount() + 1);
			return this.update(searchModel);
		}
	}

	/**
	 * 数据插入
	 * @param searchModel
	 * @return
	 */
	public Integer insert(SearchModel searchModel) {
		// 1.属性赋值
		searchModel.setRemark("1");
		// 2.执行插入
		return this.searchDao.insert(searchModel);
	}
	
	/**
	 * 数据更新
	 * @param searchModel
	 */
	private Integer update(SearchModel searchModel) {
		return this.searchDao.update(searchModel);
	}
	
	/**
	 * 获取热门搜索信息
	 * @return
	 */
	public List<SearchModel> findHot(){
		List<SearchModel> searchModelList = this.searchDao.findHot();
		return searchModelList;
	}
}
