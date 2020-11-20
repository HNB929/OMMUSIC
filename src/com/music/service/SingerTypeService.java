package com.music.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.music.bean.view.SingerTypeViewModel;
import com.music.dao.SingerTypeDao;

@Service
public class SingerTypeService {
	@Resource
	private SingerTypeDao singerTypeDao;
	
	/**
	 * 获取全部
	 * @return
	 */
	public List<SingerTypeViewModel> findAll() {
		return this.singerTypeDao.findAll();
	}
	
	/**
	 * 主键获取
	 * @param songTypeId
	 * @return
	 */
	public SingerTypeViewModel findByPrimaryKey(Integer songTypeId) {
		return this.singerTypeDao.findByPrimaryKey(songTypeId);
	}

	
	/**
	 * 数据更新
	 * @param songTypeViewModel
	 * @return
	 */
	public int update(SingerTypeViewModel singerTypeViewModel) {
		return this.singerTypeDao.update(singerTypeViewModel);
	}
	
	/**
	 * 数据插入
	 * @param songTypeViewModel
	 * @return
	 */
	public int insert(SingerTypeViewModel singerTypeViewModel) {
		return this.singerTypeDao.insert(singerTypeViewModel);
	}
	
	/**
	 * 获取全部有效的歌手类型信息
	 * @return
	 */
	public List<SingerTypeViewModel> findIsValid() {
		return this.singerTypeDao.findIsValid();
	}
}
