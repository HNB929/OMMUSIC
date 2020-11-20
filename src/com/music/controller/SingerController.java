package com.music.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.music.bean.PageShow;
import com.music.bean.Singer;
import com.music.bean.Song;
import com.music.bean.view.SingerAndSongViewModel;
import com.music.bean.view.SingerViewModel;
import com.music.service.SingerService;

/**
 * 歌手 控制器
 * 
 * @author user
 * @date 2019/03/31
 */
@Controller
public class SingerController {
	@Resource
	private SingerService singerService;

	/**
	 * 
	 * @param model
	 * @param singerId
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/singerInfo", method = RequestMethod.GET)
	public String showSingerInfo(Model model, Integer singerId, HttpServletResponse response) {
		System.out.println("singerId = " + singerId);
		Singer singer = singerService.getOneSinger(singerId);
		model.addAttribute("singer", singer);

		return "front/singerInfo";
	}

	/**
	 * 根据歌手编号获取歌手歌曲信息
	 */
	@RequestMapping(value = "/songBySinger", method = RequestMethod.GET)
	@ResponseBody
	public List<Song> showSongBySinger(Integer singerId) {

		List<Song> songList = singerService.getSongBySinger(singerId);
		return songList;
	}

	/**
	 * 分页显示歌手的所有歌曲
	 * 
	 * @param singerId
	 * @param currPage
	 * @return
	 */
	@RequestMapping(value = "/pageSongBySinger", method = RequestMethod.GET)
	@ResponseBody
	public PageShow<Song> showPageSongBySinger(Integer singerId, Integer currPage) {
		PageShow<Song> page = singerService.getPageSongBySinger(singerId, currPage);
		return page;
	}

	/**
	 * 显示全部歌手根据热度 分页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/allSingerByCount", method = RequestMethod.POST)
	@ResponseBody
	public List<Singer> getAllSingerByCount() {
		List<Singer> singerList = singerService.getAllSingerByCount();
		return singerList;

	}

	/**
	 * 根据歌手显示音乐热度前五
	 * 
	 * @param singerId
	 * @return
	 */
	@RequestMapping(value = "/songBySingerByCount", method = RequestMethod.GET)
	@ResponseBody
	public List<Song> getSongBySingerByCount(String singerId) {
		List<Song> songList = singerService.getSongBySingerByCount(Integer.parseInt(singerId));

		return songList;
	}

	/**
	 * 根据首字母查找歌手
	 * 
	 * @param firstName
	 * @return
	 */
	@RequestMapping(value = "/singerByFirstName", method = RequestMethod.GET)
	@ResponseBody
	public List<Singer> getSingerByFirstName(String firstName) {
		List<Singer> singerList = singerService.getSingerByFirst(firstName);

		return singerList;

	}

	/**
	 * 根据类别获取歌手信息
	 * @param type
	 * @return
	 */
	@RequestMapping("/singerByKind")
	@ResponseBody
	public List<Singer> getSingerByKind(String type) {
		return singerService.getSingerByKind(type);
	}

	/**
	 * 获取全部歌手信息
	 * 
	 * @return
	 */
	@RequestMapping("/singer/findAll")
	@ResponseBody
	public Map<String, Object> findAll() {
		Map<String, Object> returnMap = new HashMap<>();
		try {
			String isValid = null;
			List<SingerViewModel> singerViewModelList = this.singerService.findList(isValid);
			returnMap.put("success", true);
			returnMap.put("msg", "操作成功！");
			returnMap.put("data", singerViewModelList);
			return returnMap;
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("success", false);
			returnMap.put("msg", e.getMessage());
			return returnMap;
		}
	}

	/**
	 * 获取有效歌手信息
	 * 
	 * @return
	 */
	@RequestMapping("/singer/findIsValid")
	@ResponseBody
	public Map<String, Object> findIsValid() {
		Map<String, Object> returnMap = new HashMap<>();
		try {
			String isValid = "1";
			List<SingerViewModel> singerViewModelList = this.singerService.findList(isValid);
			returnMap.put("success", true);
			returnMap.put("msg", "操作成功！");
			returnMap.put("data", singerViewModelList);
			return returnMap;
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("success", false);
			returnMap.put("msg", e.getMessage());
			return returnMap;
		}
	}

	/**
	 * 主键获取
	 * 
	 * @return
	 */
	@RequestMapping("/singer/findByPrimaryKey")
	@ResponseBody
	public Map<String, Object> findByPrimaryKey(Integer singerId) {
		Map<String, Object> returnMap = new HashMap<>();
		try {
			// 1.非空判断
			if (singerId == null) {
				returnMap.put("success", false);
				returnMap.put("msg", "singerId 参数为空！");
				return returnMap;
			}

			SingerViewModel singerViewModel = this.singerService.findByPrimaryKey(singerId);
			returnMap.put("success", true);
			returnMap.put("msg", "操作成功！");
			returnMap.put("data", singerViewModel);
			return returnMap;

		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("success", false);
			returnMap.put("msg", e.getMessage());
			return returnMap;
		}
	}

	/**
	 * 数据更新
	 * 
	 * @return
	 */
	@RequestMapping("/singer/update")
	@ResponseBody
	public Map<String, Object> update(SingerViewModel singerViewModel) {
		Map<String, Object> returnMap = new HashMap<>();
		try {
			System.out.println("singerViewModel = " + singerViewModel);
			// 1.非空判断
			if (singerViewModel.getId() == null) {
				returnMap.put("success", false);
				returnMap.put("msg", "id 参数为空！");
				return returnMap;
			}

			if (this.singerService.update(singerViewModel) == 1) {
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
	@RequestMapping("/singer/insert")
	@ResponseBody
	public Map<String, Object> insert(MultipartFile pictureFile, SingerViewModel singerViewModel,
			HttpServletRequest request) {
		Map<String, Object> returnMap = new HashMap<>();
		try {
			System.out.println("pictureFile = " + pictureFile);
			System.out.println("singerViewModel = " + singerViewModel);

			// 1.非空判断
			String singerName = singerViewModel.getName();
			if (StringUtils.isEmpty(singerName)) {
				returnMap.put("success", false);
				returnMap.put("msg", "name 参数为空！");
				return returnMap;
			}
			if (StringUtils.isEmpty(singerViewModel.getSex())) {
				returnMap.put("success", false);
				returnMap.put("msg", "sex 参数为空！");
				return returnMap;
			}
			String representative = singerViewModel.getRepresentative();
			if (StringUtils.isEmpty(representative)) {
				returnMap.put("success", false);
				returnMap.put("msg", "representative 参数为空！");
				return returnMap;
			}
			String firstName = singerViewModel.getFirstname();
			if (StringUtils.isEmpty(firstName)) {
				returnMap.put("success", false);
				returnMap.put("msg", "firstname 参数为空！");
				return returnMap;
			}
			if (StringUtils.isEmpty(pictureFile)) {
				returnMap.put("success", false);
				returnMap.put("msg", "pictureFile 参数为空！");
				return returnMap;
			}

			// 2.根据file对象获取文件信息
			String oldName = pictureFile.getOriginalFilename();
			String suffix = oldName.substring(oldName.lastIndexOf("."));

			// 获取项目绝对路径
			String realPath = request.getSession().getServletContext().getRealPath("/front/");
			String realPath2 = request.getSession().getServletContext().getRealPath("/manager/");
			String newPictureName = "src/data/img/" + singerName + "" + suffix;
			System.out.println("realPath = " + realPath); 
			// 执行文件上传
			FileUtils.copyInputStreamToFile(pictureFile.getInputStream(), new File(realPath, newPictureName));
			FileUtils.copyInputStreamToFile(pictureFile.getInputStream(), new File(realPath2, newPictureName));

			// 3.重新设置属性值
			singerViewModel.setPicture(newPictureName);
			singerViewModel.setRepresentative("《" + representative + "》");
			singerViewModel.setFirstname(firstName.trim().toUpperCase());

			// 4.执行插入
			if (this.singerService.insert(singerViewModel) == 1) {
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
	 * 
	 * @param currPage
	 * @return
	 */
	@RequestMapping(value = "/pageSingerByCount", method = RequestMethod.GET)
	@ResponseBody
	public PageShow<Singer> getAllSingerByCount(String currPage) {
		System.out.println(currPage);
		int currPage2 = Integer.parseInt(currPage);
		PageShow<Singer> page = singerService.getAllSingerByCountPage(currPage2);

		return page;

	}

	/**
	 * 歌手界面 ， 获取歌手信息
	 * 
	 * @param pageNum
	 *            页面
	 * @param firstName
	 *            首字母
	 * @param sexNum
	 *            性别
	 * @param singerTypeId
	 *            歌手类别
	 * @return
	 */
	@RequestMapping(value = "/singer/findSingerInfo")
	@ResponseBody
	public Map<String, Object> findSingerInfo(Integer pageNum, String firstName, Integer sexNum, Integer singerTypeId) {
		Map<String, Object> returnMap = new HashMap<>();
		try {
			System.out.println("pageNum = " + pageNum);
			System.out.println("firstName = " + firstName);
			System.out.println("sexNum = " + sexNum);
			System.out.println("singerTypeId = " + singerTypeId);

			// 1.非空判断
			if (pageNum == null || pageNum < 1) {
				pageNum = 1;
			}
			// 2.判断性别是否合法
			String sex = null;
			if (!StringUtils.isEmpty(sexNum)) {
				if (1 == sexNum.intValue()) {
					sex = "男";
				} else if (2 == sexNum.intValue()) {
					sex = "女";
				}
			}
			if (!StringUtils.isEmpty(firstName)) {
				firstName = firstName.toUpperCase();
			}

			// 3.执行查询
			List<SingerAndSongViewModel> list = this.singerService.findSingerInfo(pageNum, firstName, sex,
					singerTypeId);

			// 4.获取全部歌手个数
			int allTotal = this.singerService.findAllCount(firstName, sex, singerTypeId);

			returnMap.put("success", true);
			returnMap.put("msg", "操作成功！");
			returnMap.put("data", list);
			returnMap.put("pageNum", pageNum);
			returnMap.put("pageSize", 6);
			int currentTotal = 0;
			if (list != null && list.size() > 0) {
				currentTotal = list.size();
			}
			returnMap.put("currentTotal", currentTotal);

			int allPage = 0;
			if (allTotal % 6 == 0) {
				allPage = allTotal / 6;
			} else {
				allPage = (allTotal / 6) + 1;
			}
			returnMap.put("allPage", allPage);
			returnMap.put("firstName", firstName);
			returnMap.put("sexNum", sexNum);
			returnMap.put("singerTypeId", singerTypeId);
			return returnMap;
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("success", false);
			returnMap.put("msg", e.getMessage());
			return returnMap;
		}

	}
}
