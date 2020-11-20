package com.music.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.music.bean.CommentsModel;

public interface CommentsDao {
	
	/**
	 * 插入评论信息
	 * @param commentsModel
	 * @return
	 */
	int insert(CommentsModel commentsModel);
	
	/**
	 * 根据歌曲编号获取评论信息
	 * @param songId
	 * @return
	 */
	List<CommentsModel> findBySongId(@Param("songId")Integer songId);
	
	/**
	 * 主键获取
	 * @param commentId
	 * @return
	 */
	CommentsModel findByPrimaryKey(@Param("commentId")Integer commentId);
	
	/**
	 * 数据更新
	 * @param commentsModel
	 * @return
	 */
	int update(CommentsModel commentsModel);
}