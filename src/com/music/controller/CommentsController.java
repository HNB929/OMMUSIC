package com.music.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.music.bean.CommentsModel;
import com.music.bean.UserModel;
import com.music.service.CommentsService;

/**
 * 评论控制器
 * @author HNB
 *
 */
@Controller
public class CommentsController {
	@Resource
	private CommentsService commentsService;

	
	/**
	 * 新增评论
	 * @param content
	 * @param session
	 */
	@RequestMapping(value="/comments/addComments")
	@ResponseBody
	public Map<String, Object> addComments(String content,Integer songId,HttpSession session) {
		Map<String, Object> returnMap = new HashMap<>();
		try {
			System.out.println("content = "+ content);
			System.out.println("songId = "+ songId);
			// 1.非空判断
			if (StringUtils.isEmpty(content)) {
				returnMap.put("success", false);
				returnMap.put("msg","请输入评论内容！");
				return returnMap;
			}
			if (StringUtils.isEmpty(songId)) {
				returnMap.put("success", false);
				returnMap.put("msg","歌曲编号为空！");
				return returnMap;
			}
			
			// 2.获取session中的user对象
			UserModel userModel = (UserModel) (session.getAttribute("user"));
			if(userModel == null) {
				returnMap.put("success", false);
				returnMap.put("msg","用户未登录！");
				return returnMap;
			}
			
			// 3.创建评论对象，设置属性值
			CommentsModel commentsModel = new CommentsModel();
			//设置属性
			commentsModel.setContent(content);
			commentsModel.setSongid(songId);
			commentsModel.setUserid(userModel.getId());
			
			// 4.插入评论信息
			if(this.commentsService.insert(commentsModel) == 1) {
				returnMap.put("success", true);
				returnMap.put("msg","评论发表成功！");
				return returnMap;
			}
			returnMap.put("success", false);
			returnMap.put("msg","操作失败！");
			return returnMap;
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("success", false);
			returnMap.put("msg", e.getMessage());
			return returnMap;
		}
	}
	
	
	/**
	 * 获取歌曲的全部评论和对应的回复评论
	 * @param songId
	 * @return
	 */
	@RequestMapping("/comments/showAllComment")
	@ResponseBody
	public Map<String, Object> showAllComment(Integer songId) {
		Map<String, Object> returnMap = new HashMap<>();
		try {
			System.out.println("songId = "+ songId);
			// 1.非空判断
			if (StringUtils.isEmpty(songId)) {
				returnMap.put("success", false);
				returnMap.put("msg","歌曲编号为空！");
				return returnMap;
			}
			
			// 2.根据歌曲编号获取歌曲对应的评论信息以及回复评论信息
			List<CommentsModel> list = this.commentsService.showAllComment(songId);
			
			System.out.println("list"+list);
			
			returnMap.put("success", true);
			returnMap.put("msg","操作成功！");
			returnMap.put("data",list);
			returnMap.put("allComments",list.size());
			return returnMap;
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("success", false);
			returnMap.put("msg", e.getMessage());
			return returnMap;
		}
	}
	
	
	/**
	 * 点赞事件
	 * @param commentId
	 * @return
	 */
	@RequestMapping("/comments/thumbs")
	@ResponseBody
	public Map<String, Object> doThumbs(Integer commentId) {
		Map<String, Object> returnMap = new HashMap<>();
		try {
			System.out.println("commentId = "+ commentId);
			// 1.非空判断
			if (StringUtils.isEmpty(commentId)) {
				returnMap.put("success", false);
				returnMap.put("msg","评论编号为空！");
				return returnMap;
			}
			
			// 2.评论编号获取评论信息
			CommentsModel commentsModel = this.commentsService.findByPrimaryKey(commentId);
			if (commentsModel == null) {
				returnMap.put("success", false);
				returnMap.put("msg","评论信息不存在！");
				return returnMap;
			}
			
			// 3.属性重新赋值
			commentsModel.setCount(commentsModel.getCount() + 1);
			
			// 4.执行更新
			if(this.commentsService.update(commentsModel) == 1) {
				returnMap.put("success", true);
				returnMap.put("msg","操作成功！");
				return returnMap;
			}
			returnMap.put("success", false);
			returnMap.put("msg","操作失败！");
			return returnMap;
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("success", false);
			returnMap.put("msg", e.getMessage());
			return returnMap;
		}
	}
}
