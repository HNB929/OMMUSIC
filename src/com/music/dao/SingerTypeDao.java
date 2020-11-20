package com.music.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.music.bean.view.SingerTypeViewModel;

public interface SingerTypeDao {
	/**
	 * 获取全部歌曲类型信息
	 * @returns
	 */
	List<SingerTypeViewModel> findAll();

	/**
	 * 主键获取
	 * @return
	 */
	SingerTypeViewModel findByPrimaryKey(@Param("singerTypeId")Integer singerTypeId);

	
	/**
	 * 数据更新
	 * @param songTypeViewModel
	 * @return
	 */
	int update(SingerTypeViewModel singerTypeViewModel);
	
	/**
	 * 数据插入
	 * @param songTypeViewModel
	 * @return
	 */
	int insert(SingerTypeViewModel singerTypeViewModel);

	/**
	 * 获取全部有效的歌手信息
	 * @return
	 */
	List<SingerTypeViewModel> findIsValid();
}