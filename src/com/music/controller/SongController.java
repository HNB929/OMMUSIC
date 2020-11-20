package com.music.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.music.bean.Song;
import com.music.bean.View_AllMessage;
import com.music.bean.view.SongCountViewModel;
import com.music.bean.view.SongViewModel;
import com.music.service.SongService;

@Controller
public class SongController {
	@Resource
	private SongService songService;

	/**
	 * 歌曲的基本信息
	 * @param songId
	 * @return
	 */
	@RequestMapping("/basicMessage")
	@ResponseBody
	public Song getOneSongBySongId(Integer songId) {
		Song song = songService.getOneSong(songId);
		return song;
	}

	

	/**
	 * 首页，获取歌手的歌曲数和歌曲总的播放次数
	 * 
	 * @return
	 */
	@RequestMapping("/song/findAllSongAndSongCount")
	@ResponseBody
	public Map<String, Object> findSongCount() {
		Map<String, Object> returnMap = new HashMap<>();
		try {
			List<SongCountViewModel> songCountList = this.songService.findSongCount();
			if (songCountList == null || songCountList.size() < 1) {
				returnMap.put("success", false);
				returnMap.put("msg", "数据为空！");
				return returnMap;
			}
			System.out.println("songCountList = " + songCountList);
			returnMap.put("success", true);
			returnMap.put("msg", "操作成功！");
			returnMap.put("data", songCountList);
			return returnMap;
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("success", false);
			returnMap.put("msg", e.getMessage());
			return returnMap;
		}
	}

	/**
	 * 获取歌曲信息
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/song/findAll")
	@ResponseBody
	public Map<String, Object> findAll(Integer pageNum, Integer pageSize) {
		Map<String, Object> returnMap = new HashMap<>();
		try {
			// 1.非空判断
			if (pageNum == null) {
				pageNum = 1;
			}
			if (pageSize == null) {
				pageSize = 10;
			}
			// 2.分页获取歌曲信息
			List<SongViewModel> songViewModelList = this.songService.findAll(pageNum, pageSize);

			// 3.获取歌曲个数
			int total = this.songService.findCount();
			returnMap.put("success", true);
			returnMap.put("msg", "操作成功！");
			returnMap.put("pageNum", pageNum);
			returnMap.put("pageSize", pageSize);
			returnMap.put("total", total);
			returnMap.put("allPage", total / pageSize + 1);
			returnMap.put("data", songViewModelList);
			return returnMap;
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("success", false);
			returnMap.put("msg", e.getMessage());
			return returnMap;
		}
	}

	/**
	 * 修改歌曲信息
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/song/update")
	@ResponseBody
	public Map<String, Object> update(SongViewModel songViewModel) {
		Map<String, Object> returnMap = new HashMap<>();
		try {
			System.out.println(songViewModel);
			// 1.非空判断
			Integer id = songViewModel.getId();
			if (id == null) {
				returnMap.put("success", false);
				returnMap.put("msg", "id 参数为空");
				return returnMap;
			}
			if (StringUtils.isEmpty(songViewModel.getName())) {
				returnMap.put("success", false);
				returnMap.put("msg", "name 参数为空");
				return returnMap;
			}
			if (StringUtils.isEmpty(songViewModel.getTime())) {
				returnMap.put("success", false);
				returnMap.put("msg", "time 参数为空");
				return returnMap;
			}
			if (StringUtils.isEmpty(songViewModel.getLyric())) {
				returnMap.put("success", false);
				returnMap.put("msg", "lyric 参数为空");
				return returnMap;
			}
			if (StringUtils.isEmpty(songViewModel.getMp3())) {
				returnMap.put("success", false);
				returnMap.put("msg", "mp3 参数为空");
				return returnMap;
			}
			if (songViewModel.getCount() == null) {
				returnMap.put("success", false);
				returnMap.put("msg", "count 参数为空");
				return returnMap;
			}
			if (StringUtils.isEmpty(songViewModel.getDuration())) {
				returnMap.put("success", false);
				returnMap.put("msg", "duration 参数为空");
				return returnMap;
			}
			if (songViewModel.getSingerid() == null) {
				returnMap.put("success", false);
				returnMap.put("msg", "singerid 参数为空");
				return returnMap;
			}
			if (songViewModel.getSong_typeid() == null) {
				returnMap.put("success", false);
				returnMap.put("msg", "song_typeid 参数为空");
				return returnMap;
			}

			// 2.执行更新
			if (this.songService.update(songViewModel) == 1) {
				returnMap.put("success", true);
				returnMap.put("msg", "更新成功！");
				return returnMap;
			}
			returnMap.put("success", false);
			returnMap.put("msg", "更新失败！");
			return returnMap;
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("success", false);
			returnMap.put("msg", e.getMessage());
			return returnMap;
		}
	}

	/**
	 * 添加歌曲信息
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/song/insert")
	@ResponseBody
	public Map<String, Object> insert(MultipartFile mp3File, MultipartFile lyricFile, SongViewModel songViewModel,
			HttpServletRequest request) {
		Map<String, Object> returnMap = new HashMap<>();
		try {
			// 1.非空判断
			if (StringUtils.isEmpty(songViewModel.getName())) {
				returnMap.put("success", false);
				returnMap.put("msg", "name 参数为空");
				return returnMap;
			}
			if (StringUtils.isEmpty(songViewModel.getDuration())) {
				returnMap.put("success", false);
				returnMap.put("msg", "duration 参数为空");
				return returnMap;
			}
			if (songViewModel.getSingerid() == null) {
				returnMap.put("success", false);
				returnMap.put("msg", "singerid 参数为空");
				return returnMap;
			}
			if (songViewModel.getSong_typeid() == null) {
				returnMap.put("success", false);
				returnMap.put("msg", "song_typeid 参数为空");
				return returnMap;
			}

			if (mp3File == null) {
				returnMap.put("success", false);
				returnMap.put("msg", "音频文件为空");
				return returnMap;
			}
			if (lyricFile == null) {
				returnMap.put("success", false);
				returnMap.put("msg", "歌词文件为空");
				return returnMap;
			}

			// 2.处理音频文件和歌词文件
			String oldMp3Name = mp3File.getOriginalFilename();
			String oldLyricName = lyricFile.getOriginalFilename();

			String mp3Suffix = oldMp3Name.substring(oldMp3Name.lastIndexOf("."));
			String lyricSuffix = oldLyricName.substring(oldLyricName.lastIndexOf("."));
			String realPath = request.getSession().getServletContext().getRealPath("/front/");
//			String realPath = "C:\\javaProjectData\\OMmusic";

			String mp3 = "src/data/mp3/" + songViewModel.getName() + "" + mp3Suffix;
			String lyric = "src/data/lrc/" + songViewModel.getName() + "" + lyricSuffix;

			// 2.1 执行文件上传
			FileUtils.copyInputStreamToFile(mp3File.getInputStream(), new File(realPath, mp3));
			FileUtils.copyInputStreamToFile(lyricFile.getInputStream(), new File(realPath, lyric));

			// 2.执行插入
			songViewModel.setMp3(mp3);
			songViewModel.setLyric(lyric);
			if (this.songService.insert(songViewModel) == 1) {
				returnMap.put("success", true);
				returnMap.put("msg", "操作成功！");
				return returnMap;
			}

			returnMap.put("success", false);
			returnMap.put("msg", "插入失败！");
			return returnMap;
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("success", false);
			returnMap.put("msg", e.getMessage());
			return returnMap;
		}
	}

	/**
	 * 根据歌曲编号获取页面中歌词信息
	 * 
	 * @param songId
	 * @param request
	 * @return
	 */
	@RequestMapping("/lyric")
	@ResponseBody
	public List<String> getLyric(Integer songId, HttpServletRequest request) {
		// 存放每行歌词的list
		List<String> lyricList = new ArrayList<String>();
		FileInputStream fileInputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		try {

			// 根据歌曲编号获取歌曲信息
			Song song = this.songService.getOneSong(songId);
			String file = song.getLyric();// 歌词地址

			// 读取每一行必须用字符流，读取中文用字节流，字节流读取文件，以utf-8编码转成字符流，利用缓冲读取
			// 获取路径
			String url = request.getSession().getServletContext().getRealPath("");
			// 文件输入流
			fileInputStream = new FileInputStream(new File(url + "front/" + file));
			// 字符字节转换流
			inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
			// 缓冲
			bufferedReader = new BufferedReader(inputStreamReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				// 截取掉前面的时间
				String s1 = line.substring(10);
				lyricList.add(s1);// 添加到集合
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭流资源
			try {
				bufferedReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				try {
					inputStreamReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}finally {
					try {
						fileInputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
		}
		return lyricList;
	}

	/**
	 * 根据搜索内容 获取歌曲列表
	 * @param searchmsg
	 * @return
	 */
	@RequestMapping(value="/song/findBySearch")
	@ResponseBody
	public Map<String, Object> findBySearch(String searchmsg) {
		Map<String, Object> returnMap = new HashMap<>();
		try {
			System.out.println("searchmsg = "+searchmsg);
			// 1.非空判断
			if (StringUtils.isEmpty(searchmsg)) {
				returnMap.put("success", false);
				returnMap.put("msg", "请输入搜索内容！");
				return returnMap;
			}
			
			// 2.执行数据获取
			List<SongViewModel> songList= this.songService.findBySearch(searchmsg);
			
			// 3.保存数据返回
			returnMap.put("success", true);
			returnMap.put("msg", "操作成功！");
			returnMap.put("data", songList);
			returnMap.put("allSong", songList.size());
			return returnMap;
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("success", false);
			returnMap.put("msg", e.getMessage());
			return returnMap;
		}
	}
	
	/**
	 * 榜单数据获取
	 * @param songTypeString
	 * @return
	 */
	@RequestMapping("/getSongByType")
	@ResponseBody
	public List<View_AllMessage> getSongByType(String songTypeString) {
		// 传入的类型编号
		List<View_AllMessage> songList = songService.getSongByType(songTypeString);
		return songList;

	}
	
	/**
	 * 获取当前歌手最火的六首歌
	 * @param songId
	 * @param session
	 * @return
	 */
	@RequestMapping("/sixSongBySongId")
	@ResponseBody
	public List<Song> sixSongBySongId(Integer songId, HttpSession session) {
		List<Song> sixSongsBySongId = songService.getSixSongsBySongId(songId);
		return sixSongsBySongId;
	}

	/**
	 * 获取全系统最火的六首歌
	 * @param songId
	 * @param session
	 * @return
	 */
	@RequestMapping("/sixSong")
	@ResponseBody
	public List<Song> sixSong(Integer songId, HttpSession session) {
		List<Song> sixSong = songService.getSixSong();
		return sixSong;
	}
	
	/**
	 * 根据传入的类别编号获取最新的歌曲信息
	 * @param songTypeString
	 * @return
	 */
	@RequestMapping("/getNewSongListBySongType")
	@ResponseBody
	public List<Song> getNewSongList(String songTypeString) {
		// 传入的类型编号
		List<Song> songList = songService.getNewSongList(songTypeString);
		return songList;
	}
}
