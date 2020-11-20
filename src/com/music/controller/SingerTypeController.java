package com.music.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.music.bean.view.SingerTypeViewModel;
import com.music.service.SingerTypeService;

@Controller
@RequestMapping("/singerType")
public class SingerTypeController {
	@Resource
	private SingerTypeService singerTypeService;

	/**
	 * 获取全部歌曲类别信息
	 * 
	 * @return
	 */
	@RequestMapping("/findAll")
	@ResponseBody
	public List<SingerTypeViewModel> findAll() {
		try {
			List<SingerTypeViewModel> singerTypeList = this.singerTypeService.findAll();
			return singerTypeList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 修改歌曲类别信息
	 * 
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Map<String, Object> update(SingerTypeViewModel singerTypeViewModel) {
		Map<String, Object> returnMap = new HashMap<>();
		try {
			// 1. 非空判断
			Integer songTypeId = singerTypeViewModel.getId();
			if (songTypeId == null) {
				returnMap.put("success", false);
				returnMap.put("msg", "类别主键为空！");
				return returnMap;
			}
			if (StringUtils.isEmpty(singerTypeViewModel.getName())) {
				returnMap.put("success", false);
				returnMap.put("msg", "类别名称为空！");
				return returnMap;
			}
			if (StringUtils.isEmpty(singerTypeViewModel.getRemark())) {
				returnMap.put("success", false);
				returnMap.put("msg", "remark为空！");
				return returnMap;
			}

			// 2.主键获取商品类别信息
			if (this.singerTypeService.findByPrimaryKey(songTypeId) == null) {
				returnMap.put("success", false);
				returnMap.put("msg", "类型信息不存在！");
				return returnMap;
			}

			if (this.singerTypeService.update(singerTypeViewModel) == 1) {
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
			return returnMap;

		}
	}

	/**
	 * 数据插入
	 * 
	 * @return
	 */
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String, Object> insert(SingerTypeViewModel singerTypeViewModel) {
		Map<String, Object> returnMap = new HashMap<>();
		try {
			// 1. 非空判断
			if (StringUtils.isEmpty(singerTypeViewModel.getName())) {
				returnMap.put("success", false);
				returnMap.put("msg", "类别名称为空！");
				return returnMap;
			}
			if (StringUtils.isEmpty(singerTypeViewModel.getRemark())) {
				returnMap.put("success", false);
				returnMap.put("msg", "remark为空！");
				return returnMap;
			}

			if (this.singerTypeService.insert(singerTypeViewModel) == 1) {
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
			return returnMap;
		}
	}

	/**
	 * 获取全部歌曲类别信息
	 * 
	 * @return
	 */
	@RequestMapping("/findIsValid")
	@ResponseBody
	public Map<String, Object> findIsValid() {
		Map<String, Object> returnMap = new HashMap<>();
		try {
			List<SingerTypeViewModel> singerTypeList = this.singerTypeService.findIsValid();
			returnMap.put("success", true);
			returnMap.put("msg", "操作成功！");
			returnMap.put("data", singerTypeList);
			return returnMap;
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("success", false);
			returnMap.put("msg", e.getMessage());
			return returnMap;
		}
	}
}
