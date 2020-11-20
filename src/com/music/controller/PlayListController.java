package com.music.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.music.bean.UserModel;
import com.music.bean.view.SongViewModel;
import com.music.service.PlayListService;

@Controller
public class PlayListController {
    @Resource
    private PlayListService playListService;

    /**
     * 加载用户的播放列表
     * 
     * @param content
     * @param reqeust
     * @return
     */
    @RequestMapping(value = "/loadPlay", method = RequestMethod.POST)
    @ResponseBody
    public List<SongViewModel> loadPlay(HttpServletRequest reqeust) {
        UserModel userModel = (UserModel)reqeust.getSession().getAttribute("user");
        if(userModel == null) {
            System.out.println("------>用户为空，创建默认用户！");
            userModel = new UserModel();
            userModel.setId(1);
        }
        
        // 根据用户获取播放列表信息
        List<SongViewModel> songList = playListService.getSongList(userModel);
        if (songList == null || songList.size() == 0) {
            return null;
        }
        return songList;
    }


    
    
    
    
    
    
    
    
    
    /**
     * 获取前端界面传入的歌曲列表,添加到用户的播放列表
     * 
     * @param songIdStrings
     * @param reqeust
     * @return
     */
    @RequestMapping("/playList/addListAndPlay")
    @ResponseBody
    public Map<String, Object> addListAndPLay(String songIdStrings, HttpServletRequest reqeust) {
    	Map<String, Object> returnMap = new HashMap<>();
        try {
			System.out.println("addListAndPLay --> songIdStrings=" + songIdStrings);
			// 1.非空判断
			if(StringUtils.isEmpty(songIdStrings)) {
				returnMap.put("success", false);
				returnMap.put("msg", "songIdStrings 参数为空！");
				return returnMap;
			}
			
			// 2.获取当前登录用户，判断用户是否存在
			UserModel userModel = (UserModel)reqeust.getSession().getAttribute("user");
//			if(userModel == null) {
//				returnMap.put("success", false);
//				returnMap.put("msg", "用户未登录");
//				return returnMap;
//			}
			if(userModel == null) {
				System.out.println("用户未登录，创建默认用户！");
				userModel = new UserModel();
				userModel.setId(1);
			}

			// 3.根据前端界面的歌曲id字符串添加歌曲到用户的播放列表
			this.playListService.addList(userModel, songIdStrings);
			
			// 4.获取用户歌曲列表
			List<SongViewModel> songList = this.playListService.getSongList(userModel);
			
			// 5.获取加入到播放列表第一首歌曲索引
			int songIndex = this.playListService.getIndex(userModel, songIdStrings);
			
			// 6.保存数据返回
			returnMap.put("success", true);
			returnMap.put("msg", "操作成功！");
			returnMap.put("data", songList);
			returnMap.put("songIndex", songIndex);
			return returnMap;
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("success", false);
			returnMap.put("msg", e.getMessage());
			return returnMap;
		}

    }
    
    /**
     * 将歌曲从用户播放列表移除
     * 
     * @param songId
     * @param request
     * @return
     */
    @RequestMapping(value = "/playList/removeSong")
    @ResponseBody
    public Map<String, Object> removeSong(Integer songId, HttpServletRequest request) {
    	Map<String, Object> returnMap = new HashMap<>();
        try {
        	System.out.println("removeSong-->songId = "+ songId);
        	
        	// 1.非空判断
        	if(songId == null) {
        		returnMap.put("success", false);
				returnMap.put("msg", "songId 参数为空！");
				return returnMap;
        	}
        	
        	// 2.获取用户信息
			UserModel userModel = (UserModel)request.getSession().getAttribute("user");
//			if(userModel == null) {
//				returnMap.put("success", false);
//				returnMap.put("msg", "用户未登录");
//				return returnMap;
//			}
			if(userModel == null) {
				System.out.println("用户未登录，创建默认用户！");
				userModel = new UserModel();
				userModel.setId(1);
			}
			
			// 3.移除歌曲播放列表
			int songIndex = this.playListService.removeSong(userModel, songId);
			
			// 4.获取播放列表
			List<SongViewModel> songList = this.playListService.getSongList(userModel);
			
			System.out.println("removeSong-->songList = "+songList);
			returnMap.put("success", true);
			returnMap.put("msg", "操作成功！");
			returnMap.put("data", songList);
			returnMap.put("songIndex", songIndex);
			return returnMap;
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("success", false);
			returnMap.put("msg", e.getMessage());
			return returnMap;
		}
    }
    

    /**
     * 获取前端界面传入的歌曲列表,添加到用户的播放列表
     * 
     * @param songIdStrings
     * @param reqeust
     * @return
     */
    @RequestMapping("/playList/getPLayList")
    @ResponseBody
    public Map<String, Object> getPLayList( HttpServletRequest reqeust) {
    	Map<String, Object> returnMap = new HashMap<>();
        try {
			// 1.获取当前登录用户，判断用户是否存在
			UserModel userModel = (UserModel)reqeust.getSession().getAttribute("user");
			if(userModel == null) {
				System.out.println("用户未登录，创建默认用户！");
				userModel = new UserModel();
				userModel.setId(1);
			}

			
			// 2.获取用户歌曲列表
			List<SongViewModel> songList = this.playListService.getSongList(userModel);
			
			// 6.保存数据返回
			returnMap.put("success", true);
			returnMap.put("msg", "操作成功！");
			returnMap.put("data", songList);
			return returnMap;
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("success", false);
			returnMap.put("msg", e.getMessage());
			return returnMap;
		}

    }
}
