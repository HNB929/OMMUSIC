package com.music.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.music.bean.PlayList;
import com.music.bean.UserModel;
import com.music.bean.view.SongViewModel;
import com.music.dao.SongDao;
@Service
public class PlayListService {
	@Resource
	private SongDao songDao;
	
	@Resource
	private SongService songService;

	static List<PlayList> playListList = new ArrayList<PlayList>();

	
	/**
	 * 获取当前用户的播放列表大小
	 * @param user
	 * @return
	 */
	public Integer getOriginalLength(UserModel userModel) {
		if(playListList.size() != 0) {
			// 判断播放列表集合是否存在该用户
			for (PlayList playList : playListList) {
				if(playList.getUserModel() == null) {
					return 0;
				}
				if (playList.getUserModel().getId().equals(userModel.getId())) {
					// 存在该用户，获取该用户播放列表大小
					return playList.getSongList().size();
				}
			}
		}
		return 0;

	}

	

	



	
	
	
	
	
	
	
	
	
	
	/**
	 * 根据传入的用户信息和集合信息往播放列表中加入数据
	 * @param userModel
	 * @param songIds
	 */
	public void addList(UserModel userModel, String songIdStrings) {
		// 1.判断歌曲编号字符串是否以“/”结尾
		if((songIdStrings.charAt(songIdStrings.length()-1)) == '/') {
			songIdStrings = songIdStrings.substring(0, songIdStrings.length() - 1);
		}
		// 2.歌曲编号字符串转化为数组
		String[] songIdArr = songIdStrings.split("/");
		
		// 3.去除重复歌曲保存在set集合中
		Set<Integer> songIdSet = new TreeSet<>();
		for(String songIds: songIdArr) {
			songIdSet.add(Integer.parseInt(songIds));
		}
		
		
		// 4.判断播放集合里面是否存在该用户
		boolean flag = false;
		for (PlayList playList : playListList) {
			if (userModel.getId().intValue() == playList.getUserModel().getId().intValue()) {
			    // 4.1.播放列表集合存在用户信息，获取用户的播放列表,播放列表添加歌曲
				List<SongViewModel> songList = playList.getSongList();
				// 4.2.遍历传入的歌曲id数组
				for (Integer id : songIdSet) {
					boolean flag1 = false;
					// 遍历原有的播放列表
					for (int j = 0; j < songList.size() ; j++) {
						if (songList.get(j).getId().equals(id)) {
						    // 当前歌曲和和播放列表相同的歌曲
							flag1 = true;
						}
					}
					if (!flag1) {
						SongViewModel songViewModel = this.songService.findByPrimaryKey(id);
						if(songViewModel != null) {
							songList.add(songViewModel);
						}
						//歌曲的播放次数+1
						songViewModel.setCount(songViewModel.getCount() + 1);
						this.songService.update(songViewModel);
					}
				}
				flag = true;
			}
		}
		
		// 播放列表集合不存在用户(创建该用户的播放列表)
		if (!flag) {
			System.out.println("用户在播放列表集合中不存在，正在创建用户的播放列表，并将当前歌曲列表加入到该用户的播放列表");
			PlayList playList = new PlayList();
			//创建新的播放列表
			List<SongViewModel> songList = new ArrayList<SongViewModel>();
			for (Integer id : songIdSet) {
				SongViewModel songViewModel = this.songService.findByPrimaryKey(id);
				if(songViewModel != null) {
					songList.add(songViewModel);
				}
				//歌曲的播放次数+1
				songViewModel.setCount(songViewModel.getCount() + 1);
				this.songService.update(songViewModel);
			}
			playList.setSongList(songList);
			playList.setUserModel(userModel);
			playListList.add(playList);
		}
	}
	
	/**
	 * 根据用户获取用户歌曲列表
	 * @param userModel
	 * @return
	 */
	public List<SongViewModel> getSongList(UserModel userModel) {
		// 遍历
		for (PlayList playList : playListList) {
			if (userModel.getId().equals(playList.getUserModel().getId())) {
			    // 存在当前用户的播放列表
				return playList.getSongList();
			}
		}
		return null;
	}
	
	/**
	 * 获取歌曲字符串第一首歌曲的索引
	 * @param userModel
	 * @param songIdStrings
	 * @return
	 */
	public int getIndex(UserModel userModel, String songIdStrings) {
		if((songIdStrings.charAt(songIdStrings.length()-1)) == '/') {
			songIdStrings = songIdStrings.substring(0, songIdStrings.length() - 1);
		}
		String[] songIdArr = songIdStrings.split("/");
		Integer id = Integer.parseInt(songIdArr[0]);
		// 遍历全部播放列表，获取用户播放列表
		for(PlayList playList : playListList) {
			if(userModel.getId().intValue() == playList.getUserModel().getId().intValue()) {
				//获取播放列表
				List<SongViewModel> songList = playList.getSongList();
				//遍历用户播放列表
				for(int i = 0 ; i < songList.size() ; i++) {
					if(songList.get(i).getId().equals(id)) {
						return i;
					}
				}
			}
		}
		return -1;
	}

	/**
	 * 根据用户和歌曲编号移除播放列表中的歌曲
	 * @param user
	 * @param songId
	 */
	public int removeSong(UserModel userModel, Integer songId) {
		//获取用户的播放列表
		for(PlayList playList : playListList) {
			//查询到用户的播放列表
			if(playList.getUserModel().getId().intValue() == userModel.getId().intValue()) {
				
				List <SongViewModel> songList = playList.getSongList();
				
				for(int i = 0 ; i < songList.size() ; i++) {
					SongViewModel songViewModel = songList.get(i);
					if(songViewModel.getId().intValue() == songId) {
						//播放列表移除歌曲
						playList.getSongList().remove(songViewModel);
						return i;
					}
				}
			}
		}
		return -1;
	}
}
