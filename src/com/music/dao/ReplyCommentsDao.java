package com.music.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.music.bean.ReplyCommentModel;

/**
 * 回复评论dao
 * @author HNB
 *
 */
public interface ReplyCommentsDao {

	/**
	 * 根据评论编号获取全部回复评论信息
	 * @param commentsId
	 * @return
	 */
	List<ReplyCommentModel> findByCommentsId(@Param("commentsId")Integer commentsId);

	/**
	 * 数据插入
	 * @param replyCommentModel
	 * @return
	 */
	int insert(ReplyCommentModel replyCommentModel);

}