package com.music.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import com.music.bean.PageShow;
import com.music.bean.Singer;
import com.music.bean.Song;
import com.music.bean.view.SingerAndSongViewModel;
import com.music.bean.view.SingerViewModel;
import com.music.bean.view.SongViewModel;
import com.music.dao.SingerDao;
import com.music.dao.SongDao;

@Service(value = "singerService")
public class SingerService {
	@Resource
	private SingerDao singerDao;
	@Resource
	private SongDao songDao;
	@Resource
	private SongService songService;

	// 根据歌手id查询歌手信息
	public Singer getOneSinger(Integer singerId) {
		return singerDao.getOneSinger(singerId);
	}

	// 根据歌手id查询歌手所有歌曲信息
	public List<Song> getSongBySinger(Integer singerId) {
		return songDao.getSongBySinger(singerId);
	}
	/**
	 * 根据歌手id分页查询歌手所有歌曲信息
	 * @param singerId
	 * @param currPage
	 * @return
	 */
	public PageShow<Song> getPageSongBySinger(Integer singerId,Integer currPage) {
	    
		PageShow<Song> page = new PageShow<Song>();
		//获取总笔数
		int total=songDao.getSongCount(singerId);
		page.setTotal(total);
		//设置当前页
		page.setCurrPage(currPage);

		int startNum= (page.getCurrPage()-1)*6;
		int pageSize= 6;
		
		
		List<Song> songList=songDao.getPageSongBySinger(singerId,startNum,pageSize);
		page.setList(songList);
		return page;
	}
	
	
	// 根据歌手id查询歌手热度前五
	public List<Song> getSongBySingerByCount(Integer singerId) {
		return songDao.getSongBySingerByCount(singerId);
	}

	// 查询所有歌手根据播放次数排序
	public List<Singer> getAllSingerByCount() {
		return singerDao.getAllSingerByCount();
	}
	//根据首字母查找歌手
	public List<Singer> getSingerByFirst(String firstName){
		return singerDao.getSingerByFirstName(firstName);
	}
	//根据歌手类别查询歌手
	public List<Singer> getSingerByKind(String type){
		return singerDao.getSingerByKind(type);
	}

	
	
	/**
	 * 获取歌手信息
	 * @return
	 */
	public List<SingerViewModel> findList(String remark) {
		return this.singerDao.findList(remark);
	}

	/**
	 * 主键获取
	 * @param singerId
	 * @return
	 */
	public SingerViewModel findByPrimaryKey(Integer singerId) {
		return this.singerDao.findByPrimaryKey(singerId);
	}

	/**
	 * 数据更新
	 * @param singerViewModel
	 * @return
	 */
	public Integer update(SingerViewModel singerViewModel) {
		return this.singerDao.update(singerViewModel);
	}

	/**
	 * 数据插入
	 * @param singerViewModel
	 * @return
	 */
	public int insert(SingerViewModel singerViewModel) {
		singerViewModel.setRemark("1");
		return this.singerDao.insert(singerViewModel);
	}
	
	/**
	 * 根据歌手的全部歌曲之和降序获取歌手信息
	 * @param currPage
	 * @return
	 */
	public PageShow<Singer> getAllSingerByCountPage(Integer currPage){
		PageShow<Singer> page=new PageShow<Singer>();
		//获取总笔数
		int total=singerDao.getSingerCount();
		//System.out.println(total);
		page.setTotal(total);
		//设置当前页
		page.setCurrPage(currPage);
		
		int startNum=(page.getCurrPage()-1)*6;
		int endNum=(page.getCurrPage())*6;
		
		
		List<Singer> singerList=singerDao.getPageSingerByCount(startNum,endNum);
		
		page.setList(singerList);
		return page;
	}

	/**
	 * 多条件获取歌手和对应歌曲信息
	 * @param pageNum
	 * @param firstName
	 * @param sex
	 * @param singerTypeId
	 * @return
	 */
	public List<SingerAndSongViewModel> findSingerInfo(Integer pageNum, String firstName, String sex,
			Integer singerTypeId) {
		
		// 1. 获取歌曲信息
		int startNum = (pageNum - 1) * 6;
		List<SingerAndSongViewModel> singerAndSongViewModelList = this.singerDao.findSingerInfo(startNum,firstName,sex,singerTypeId);
		System.out.println("singerAndSongViewModelList = "+singerAndSongViewModelList);
		
		if(singerAndSongViewModelList != null && singerAndSongViewModelList.size() > 0) {
			for (SingerAndSongViewModel singerAndSongViewModel : singerAndSongViewModelList) {
				// 获取歌手热门歌曲
				List<SongViewModel> songViewModelList = this.songService.findHot(singerAndSongViewModel.getId());
				singerAndSongViewModel.setSongList(songViewModelList);
			}
		}
		
		return singerAndSongViewModelList;
	}

	/**
	 * 获取全部歌手个数
	 * @return
	 */
	public int findAllCount(String firstName, String sex, Integer singerTypeId) {
		return this.singerDao.findAllCount(firstName,sex,singerTypeId);
	}
	
}
