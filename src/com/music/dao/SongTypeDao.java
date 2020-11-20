package com.music.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.music.bean.view.SongTypeViewModel;

public interface SongTypeDao {
	/**
	 * 获取全部歌曲类型信息
	 * @returns
	 */
	List<SongTypeViewModel> findList(@Param("remark")String remark);

	/**
	 * 主键获取
	 * @return
	 */
	SongTypeViewModel findByPrimaryKey(@Param("songTypeId")Integer songTypeId);

	
	/**
	 * 数据更新
	 * @param songTypeViewModel
	 * @return
	 */
	int update(SongTypeViewModel songTypeViewModel);
	
	/**
	 * 数据插入
	 * @param songTypeViewModel
	 * @return
	 */
	int insert(SongTypeViewModel songTypeViewModel);
}