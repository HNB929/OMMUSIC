package com.music.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.music.bean.UserModel;
import com.music.bean.Users;

public interface UsersDao {
	//注册用户
	int insertUsers(Users user);
	//查询出已注册的所有手机号
	List<String> selectMobile();
	//查询出该用户的信息
	Users loginVerify(String tel);
	
	/**
	 * 数据插入
	 * @param userModel
	 * @return
	 */
	int insert(UserModel userModel);
	
	/**
	 * 根据手机号获取数据
	 * @param tel
	 * @return
	 */
	UserModel findByTel(@Param("tel")String tel);

}