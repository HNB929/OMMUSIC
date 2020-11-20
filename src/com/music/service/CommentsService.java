package com.music.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.music.bean.CommentsModel;
import com.music.bean.ReplyCommentModel;
import com.music.dao.CommentsDao;
import com.music.util.DateAndTimeUtils;

@Service
public class CommentsService {
	@Resource
	private CommentsDao commentsDao;
	
	@Resource
	private ReplyCommentsService replyCommentsService;
	
	/**
	 * 新增评论
	 * @param commentsModel
	 * @return
	 */

	public int insert(CommentsModel commentsModel) {
		commentsModel.setTime(DateAndTimeUtils.getTime());
		commentsModel.setCount(0);
		commentsModel.setRemark("1");
		return this.commentsDao.insert(commentsModel);
	}

	/**
	 * 获取歌曲的全部评论信息以及对应的回复评论信息
	 * @return
	 */
	public List<CommentsModel> showAllComment(Integer songId) {
		// 1.获取歌曲全部的评论信息
		List<CommentsModel> commentsModelList = this.commentsDao.findBySongId(songId);
		System.out.println("commentsModelList = "+ commentsModelList);
		
		// 2.遍历评论集合获取评论对应的全部回复评论信息
		if (commentsModelList != null && commentsModelList.size() > 0) {
			for (CommentsModel commentsModel : commentsModelList) {
				List<ReplyCommentModel> replyCommentModelList = this.replyCommentsService.findByCommentsId(commentsModel.getId());
				if(replyCommentModelList != null && replyCommentModelList.size() > 0 ) {
					commentsModel.setReplyCommentModelList(replyCommentModelList);
				}
			}
		}
		
		// 3.返回数据集合
		return commentsModelList;
	}

	/**
	 * 主键获取
	 * @param commentId
	 * @return
	 */
	public CommentsModel findByPrimaryKey(Integer commentId) {
		return this.commentsDao.findByPrimaryKey(commentId);
	}

	/**
	 * 数据更新
	 * @param commentsModel
	 * @return
	 */
	public int update(CommentsModel commentsModel) {
		return this.commentsDao.update(commentsModel);
	}
	
}	
