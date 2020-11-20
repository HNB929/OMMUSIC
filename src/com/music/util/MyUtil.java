package com.music.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.util.StringUtils;

public class MyUtil {
	/**
	 * 根据传入的json格式数据返回一个map集合
	 * 
	 * @return
	 */
	public static Map<String, String> getMap(String json) {
		// 创建一个map对象
		Map<String, String> map = new HashMap<String, String>();
		// 截取字符串的前面和后面的｛｝
		String json1 = json.substring(1, json.length() - 1).replace(" ", "");
		// 根据，将字符创转为数组
		String[] jsons = json1.split(",");
		// 遍历数组
		for (int i = 0; i < jsons.length; i++) {
			// 根据 ： 将数组的每一条数据再次截取成数组
			String[] arr = jsons[i].split(":");
			// 遍历新的数组，将数组中的数据加入到map分别作为键和值
			for (int j = 0; j < arr.length; j++) {
				// 除去每一项的双引号
				map.put(arr[0].substring(1, arr[0].length() - 1), arr[1].substring(1, arr[1].length() - 1));
			}
		}
		for (Entry<String, String> m : map.entrySet()) {
			System.out.println("key= " + m.getKey() + " ,value= " + m.getValue());
		}
		return map;
	}
	
	/**
	 * 判断手机号格式是否正确
	 * @param mobile
	 */
	public static boolean judgeMobile(String mobile) {
		String regex = "^[1]\\d{10}$";
		if (StringUtils.isEmpty(mobile)) {
			return false;
		}
		mobile = mobile.trim();
		if(mobile.matches(regex)) {
			return true;
		}
		return false;
	}
	
}
