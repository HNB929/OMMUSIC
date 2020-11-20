package com.music.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.music.bean.UserModel;
import com.music.msg.IndustrySMS;
import com.music.service.UsersService;
import com.music.util.MyUtil;

@Controller
public class UsersController {
	//map存储手机号和验证码
	private static Map<String,String> phoneMsgMap = new HashMap<String,String>();
	
	@Resource
	private UsersService userService;
	
	
	/**
	 * 用户注册
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping("user/register")
	@ResponseBody
	public Map<String, Object> userRegister(UserModel userModel,String confirmPassword){
		System.out.println("-->usersController/userRegister");
		Map<String, Object> returnMap = new HashMap<>();
		try {
			System.out.println("userModel = "+userModel);
			System.out.println("confirmPassword = "+confirmPassword);
			// 1.非空判断
			String username = userModel.getUsername();
			if (StringUtils.isEmpty(username)) {
				returnMap.put("success", false);
				returnMap.put("msg", "请输入用户名！");
				return returnMap;
			}
			String password = userModel.getPassword();
			if (StringUtils.isEmpty(password)) {
				returnMap.put("success", false);
				returnMap.put("msg", "请输入密码!");
				return returnMap;
			}
			String tel = userModel.getTel();
			if (StringUtils.isEmpty(tel)) {
				returnMap.put("success", false);
				returnMap.put("msg", "请输入手机号!");
				return returnMap;
			}
			
			// 2.判断确认密码和密码是否相同
			if(!password.equals(confirmPassword)) {
				returnMap.put("success", false);
				returnMap.put("msg", "确认密码和密码不一致！");
				return returnMap;
			}
			
			// 3.判断用户名长度是否合法
			if(username.length() < 3 ) {
				returnMap.put("success", false);
				returnMap.put("msg", "用户名长度小于3位");
				return returnMap;
			}
			
			// 4.判断手机号格式是否正确
			if(!MyUtil.judgeMobile(tel)) {
				returnMap.put("success", false);
				returnMap.put("msg", "手机号格式错误！");
				return returnMap;
			}
			
			// 5.判断手机号是否存在
			if (this.userService.findByTel(tel.trim()) != null) {
				returnMap.put("success", false);
				returnMap.put("msg", "手机号已经注册！");
				return returnMap;
			}
			// 5.执行注册
			if(this.userService.insert(userModel) == 1) {
				returnMap.put("success", true);
				returnMap.put("msg", "注册成功！");
				return returnMap;
			}
			returnMap.put("success", false);
			returnMap.put("msg", "注册失败！");
			return returnMap;
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("success",false);
			returnMap.put("msg",e.getMessage());
			return returnMap;
		}
	}


	/**
	 * 用户
	 * @param tel
	 * @param password
	 * @param response
	 * @param session
	 * @throws IOException
	 */
	@RequestMapping("user/login")
	@ResponseBody
	public Map<String, Object> userLogin(String tel,String password,HttpSession session){
		System.out.println("-->usersController/userLogin");
		Map<String, Object> returnMap = new HashMap<>();
		try {
			// 1.非空判断
			if (StringUtils.isEmpty(tel)) {
				returnMap.put("success", false);
				returnMap.put("msg", "请输入手机号！");
				return returnMap;
			}
			if (StringUtils.isEmpty(password)) {
				returnMap.put("success", false);
				returnMap.put("msg", "请输入密码!");
				return returnMap;
			}
			
			// 2.根据手机号获取用户信息
			UserModel userModel = this.userService.findByTel(tel.trim());
			if(userModel == null) {
				returnMap.put("success", false);
				returnMap.put("msg", "用户不存在！");
				return returnMap;
			}
			
			// 3.判断密码是否正确
			if(!password.equals(userModel.getPassword())) {
				returnMap.put("success", false);
				returnMap.put("msg", "密码错误！");
				return returnMap;
			}
			
			session.setAttribute("user", userModel);
			returnMap.put("success", true);
			returnMap.put("msg", "登录成功！");
			returnMap.put("data", userModel);
			return returnMap;
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("success",false);
			returnMap.put("msg",e.getMessage());
			return returnMap;
		}
	}
	
	/**
	 * 退出登录（清除session)
	 * @throws IOException 
	 */
	@RequestMapping("/removeUser")
	public void removeUser(HttpSession session,HttpServletResponse response) throws IOException {
		System.out.println("usersController/removeUser");
		session.invalidate();
		response.getWriter().print("<script type=\"text/javascript\">window.top.location.href=\"front/index.jsp\";</script>");
	}
	
	
	
	/**
	 * 获取验证码
	 * @param phone
	 * @return
	 */
	@RequestMapping("/getCode")
	@ResponseBody
	public Map<String, Object> getCode(String phone) {
		Map<String, Object> returnMap = new HashMap<>();
		try {
			// 1.非空判断
			if(StringUtils.isEmpty(phone)) {
				returnMap.put("success", false);
				returnMap.put("msg", "请输入手机号！");
				return returnMap;
			}
			// 2.发送短信验证码
			IndustrySMS industrySMS = new IndustrySMS();
			String codeNum = industrySMS.execute(phone);
			
			// 3.保存短信发送记录
			phoneMsgMap.put(phone, codeNum);
			
			// 4.返回信息
			returnMap.put("success", true);
			returnMap.put("msg", "验证码发送成功！");
			returnMap.put("data", phone);
			return returnMap;
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("success",false);
			returnMap.put("msg",e.getMessage());
			return returnMap;
		}
	}
	
	/**
	 * 验证码登录验证
	 * @param phonenumber
	 * @param userpwd
	 * @param response
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/codeLogin")
	@ResponseBody
	public Map<String, Object> codeLogin(String phonenumber,String userpwd,HttpSession session) {
		Map<String, Object> returnMap = new HashMap<>();
		try {
			// 1. 非空判断
			if(StringUtils.isEmpty(phonenumber)) {
				returnMap.put("success", false);
				returnMap.put("msg", "请输入手机号！");
				return returnMap;
			}
			if(StringUtils.isEmpty(userpwd)) {
				returnMap.put("success", false);
				returnMap.put("msg", "请输入验证码！");
				return returnMap;
			}
			// 2.根据手机号获取用户信息，判断用户是否存在
			UserModel userModel = this.userService.findByTel(phonenumber.trim());
			if(userModel == null) {
				returnMap.put("success", false);
				returnMap.put("msg", "用户不存在！");
				return returnMap;
			}
			
			// 3.根据手机号获取验证码的值，判断是否相等
			String code = phoneMsgMap.get(phonenumber);
			if(StringUtils.isEmpty(code)) {
				returnMap.put("success", false);
				returnMap.put("msg", "短信验证码未发送！");
				return returnMap;
			}
			if(!code.equals(userpwd.trim())) {
				returnMap.put("success", false);
				returnMap.put("msg", "验证码错误！");
				return returnMap;
			}
			// 4.保存对象
			session.setAttribute("user", userModel);
			returnMap.put("success", true);
			returnMap.put("msg", "登录成功！");
			returnMap.put("data", userModel);
			return returnMap;
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("success",false);
			returnMap.put("msg",e.getMessage());
			return returnMap;
		}
	}
	
}
