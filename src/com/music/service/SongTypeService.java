package com.music.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.music.bean.view.SongTypeViewModel;
import com.music.dao.SongTypeDao;

@Service
public class SongTypeService {
	@Resource
	private SongTypeDao songTypeDao;

	/**
	 * 获取全部歌曲类型信息
	 * @return
	 */
	public List<SongTypeViewModel> findList(String remark) {
		return this.songTypeDao.findList(remark);
	}
	
	/**
	 * 主键获取歌曲类别信息
	 * @param songTypeId
	 * @return
	 */
	public SongTypeViewModel findByPrimaryKey(Integer songTypeId) {
		return this.songTypeDao.findByPrimaryKey(songTypeId);
	}

	
	/**
	 * 数据更新
	 * @param songTypeViewModel
	 * @return
	 */
	public int update(SongTypeViewModel songTypeViewModel) {
		return this.songTypeDao.update(songTypeViewModel);
	}
	
	/**
	 * 数据插入
	 * @param songTypeViewModel
	 * @return
	 */
	public int insert(SongTypeViewModel songTypeViewModel) {
		return this.songTypeDao.insert(songTypeViewModel);
	}
	
	
}
