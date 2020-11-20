package com.music.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.music.bean.Singer;
import com.music.bean.view.SingerAndSongViewModel;
import com.music.bean.view.SingerViewModel;

public interface SingerDao {
	// 删除歌手信息
	int deleteSinger(Integer id);

	// 插入歌手信息
	int insertSinger(Singer singer);

	// 获取单个歌手信息
	Singer getOneSinger(Integer id);

	// 获取所有歌手信息根据播放次数排序
	List<Singer> getAllSingerByCount();

	/**
	 * 根据歌手全部歌曲的点赞次数之和 降序排序获取歌手信息
	 * @param prevPage
	 * @param currPage
	 * @return
	 */
	List<Singer> getPageSingerByCount(
			@Param(value = "startNum") Integer startNum, 
			@Param(value = "endNum") Integer endNum);

	// 根据歌手首字母获取歌手信息
	List<Singer> getSingerByFirstName(String firstName);

	// 根据歌手类别，歌手首字母获取歌手信息
	List<Singer> getSingerBySingerTypeAndFirstNameAndSex(Integer singerTypeId, String firstName, String sex);
	
	//根据歌手类型获取歌手信息
	List<Singer> getSingerByKind(String type);
	
	
	// 获取所有歌手数量
	int getSingerCount();



	
	/**
	 * 获取歌手信息
	 * @return
	 */
	List<SingerViewModel> findList(@Param("remark")String remark);

	/**
	 * 主键获取
	 * @param singerId
	 * @return
	 */
	SingerViewModel findByPrimaryKey(@Param("singerId")Integer singerId);

	/**
	 * 数据更新
	 * @return
	 */
	Integer update(SingerViewModel singerViewModel);

	
	/**
	 * 数据插入
	 * @param singerViewModel
	 * @return
	 */
	int insert(SingerViewModel singerViewModel);

	/**
	 * 根据关键字获取歌手信息集合
	 * @param singerName
	 * @return
	 */
	List<SingerViewModel> findByName(@Param("singerName")String singerName);

	
	/**
	 * 多条件获取歌手信息
	 * @param startNum
	 * @param firstName
	 * @param sex
	 * @param singerTypeId
	 * @return
	 */
	List<SingerAndSongViewModel> findSingerInfo(
			@Param("startNum")int startNum, 
			@Param("firstName")String firstName, 
			@Param("sex")String sex, 
			@Param("singerTypeId")Integer singerTypeId);

	/**
	 * 获取全部歌手个数
	 * @return
	 */
	int findAllCount(
			@Param("firstName")String firstName, 
			@Param("sex")String sex, 
			@Param("singerTypeId")Integer singerTypeId);
}