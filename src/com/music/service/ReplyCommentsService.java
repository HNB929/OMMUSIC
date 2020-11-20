package com.music.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.music.bean.ReplyCommentModel;
import com.music.dao.ReplyCommentsDao;
import com.music.util.DateAndTimeUtils;

/**
 * 回复评论service
 * @author HNB
 *
 */
@Service
public class ReplyCommentsService {
	@Resource
	private ReplyCommentsDao replyCommentsDao;

	/**
	 * 根据评论编号获取全部回复评论信息
	 * @param commentsId
	 * @return
	 */
	public List<ReplyCommentModel> findByCommentsId(Integer commentsId) {
		return this.replyCommentsDao.findByCommentsId(commentsId);
	}

	/**
	 * 数据插入
	 * @param replyCommentModel
	 * @return
	 */
	public int insert(ReplyCommentModel replyCommentModel) {
		// 1.属性赋值
		replyCommentModel.setTime(DateAndTimeUtils.getTime());
		replyCommentModel.setRemark("1");
		return this.replyCommentsDao.insert(replyCommentModel);
	}
	
	
}	
