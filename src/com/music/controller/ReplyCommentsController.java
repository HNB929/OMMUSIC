package com.music.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.music.bean.ReplyCommentModel;
import com.music.bean.UserModel;
import com.music.service.ReplyCommentsService;

/**
 * 回复评论控制器
 * @author HNB
 *
 */
@Controller
public class ReplyCommentsController {
	@Resource
	private ReplyCommentsService replyCommentsService;
	
	
	
	
	/**
	 * 添加回复评论
	 * @param commentId
	 * @param repComment
	 * @param session
	 * @return
	 */
	@RequestMapping("/replyComment/insert")
	@ResponseBody
	public Map<String, Object> insert(Integer commentId,String repComment,HttpSession session) {
		Map<String, Object> returnMap = new HashMap<>();
		try {
			System.out.println("commentId = "+ commentId);
			System.out.println("repComment = "+ repComment);
			// 1.非空判断
			if (StringUtils.isEmpty(commentId)) {
				returnMap.put("success", false);
				returnMap.put("msg","评论编号为空！");
				return returnMap;
			}
			if (StringUtils.isEmpty(repComment)) {
				returnMap.put("success", false);
				returnMap.put("msg","请输入评论内容！");
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
			ReplyCommentModel replyCommentModel = new ReplyCommentModel();
			//设置属性
			replyCommentModel.setContent(repComment);
			replyCommentModel.setCommentsid(commentId);
			replyCommentModel.setUserid(userModel.getId());
			
			// 4.插入评论信息
			if(this.replyCommentsService.insert(replyCommentModel) == 1) {
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
}
