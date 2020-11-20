package com.music.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.music.bean.UserModel;
import com.music.bean.Users;
import com.music.dao.UsersDao;
import com.music.util.DateAndTimeUtils;

@Service
public class UsersService {
	@Resource
	private UsersDao usersDao;
	
	//登录验证，根据手机号获取用户对象
	public Users loginVerify(String tel) {
		return usersDao.loginVerify(tel);
	}
	//用户注册
	public Integer registe(Users u) {
		return usersDao.insertUsers(u);
	}
	
	//手机号验证是否注册
	public List<String> selectMobile(){
		return usersDao.selectMobile();
	}
	
	
	
	
	
	/**
	 * 数据插入
	 * @param userModel
	 * @return
	 */
	public int insert(UserModel userModel) {
		// 1.属性赋默认值
		userModel.setRole(1);
		userModel.setTime(DateAndTimeUtils.getTime());
		userModel.setPicture("img/user.jpg");
		userModel.setRemark("1");
		// 2.执行插入
		return this.usersDao.insert(userModel);
	}
	
	/**
	 * 根据手机号获取数据
	 * @param trim
	 * @return
	 */
	public UserModel findByTel(String tel) {
		return this.usersDao.findByTel(tel);
	}
	
}
