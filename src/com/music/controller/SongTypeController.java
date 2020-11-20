package com.music.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.music.bean.view.SongTypeViewModel;
import com.music.service.SongTypeService;

@Controller
@RequestMapping("/songType")
public class SongTypeController {
	@Resource
	private SongTypeService songTypeService;

	/**
	 * 获取全部歌曲类别信息
	 * @return
	 */
	@RequestMapping("/findAll")
	@ResponseBody
	public List<SongTypeViewModel> findAll(){
		try {
			String isValid  = null;
			List<SongTypeViewModel> songTypeList = this.songTypeService.findList(isValid);
			return songTypeList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取有效歌曲类别信息
	 * @return
	 */
	@RequestMapping("/findIsValid")
	@ResponseBody
	public List<SongTypeViewModel> findIsValid(){
		try {
			String isValid  = "1";
			List<SongTypeViewModel> songTypeList = this.songTypeService.findList(isValid);
			return songTypeList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 修改歌曲类别信息
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(SongTypeViewModel songTypeViewModel){
		Map<String,Object> returnMap = new HashMap<>();
		try {
			// 1. 非空判断
			Integer songTypeId = songTypeViewModel.getId();
			if(songTypeId == null) {
				returnMap.put("success", false);
				returnMap.put("msg", "类别主键为空！");
				return returnMap;
			}
			if(StringUtils.isEmpty(songTypeViewModel.getName())) {
				returnMap.put("success", false);
				returnMap.put("msg", "类别名称为空！");
				return returnMap;
			}
			if(StringUtils.isEmpty(songTypeViewModel.getRemark())) {
				returnMap.put("success", false);
				returnMap.put("msg", "remark为空！");
				return returnMap;
			}
			
			// 2.主键获取商品类别信息
			if(this.songTypeService.findByPrimaryKey(songTypeId) == null) {
				returnMap.put("success", false);
				returnMap.put("msg", "类型信息不存在！");
				return returnMap;
			}
			
			if(this.songTypeService.update(songTypeViewModel) == 1) {
				returnMap.put("success", true);
				returnMap.put("msg", "操作成功！");
				return returnMap;
			}
			returnMap.put("success", false);
			returnMap.put("msg", "操作失败！");
			return returnMap;
		} catch (Exception e) {
			e.printStackTrace();
			 returnMap.put("success", false);
			 returnMap.put("msg", e.getMessage());
			return returnMap ;
			
		}
	}
	
	/**
	 * 数据插入
	 * @return
	 */
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insert(SongTypeViewModel songTypeViewModel){
		Map<String,Object> returnMap = new HashMap<>();
		try {
			// 1. 非空判断
			if(StringUtils.isEmpty(songTypeViewModel.getName())) {
				returnMap.put("success", false);
				returnMap.put("msg", "类别名称为空！");
				return returnMap;
			}
			if(StringUtils.isEmpty(songTypeViewModel.getRemark())) {
				returnMap.put("success", false);
				returnMap.put("msg", "remark为空！");
				return returnMap;
			}
			
			if(this.songTypeService.insert(songTypeViewModel) == 1) {
				returnMap.put("success", true);
				returnMap.put("msg", "操作成功！");
				return returnMap;
			}
			returnMap.put("success", false);
			returnMap.put("msg", "操作失败！");
			return returnMap;
		} catch (Exception e) {
			e.printStackTrace();
			 returnMap.put("success", false);
			 returnMap.put("msg", e.getMessage());
			return returnMap ;
		}
	}
}
