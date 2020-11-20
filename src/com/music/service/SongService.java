package com.music.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.music.bean.Song;
import com.music.bean.View_AllMessage;
import com.music.bean.view.SingerViewModel;
import com.music.bean.view.SongCountViewModel;
import com.music.bean.view.SongViewModel;
import com.music.dao.SingerDao;
import com.music.dao.SongDao;

@Service
public class SongService {
	@Resource
	private SongDao songDao;
	@Resource
	private SingerDao singerDao;
	@Resource
	private SearchService searchService;



	/**
	 * 根据歌曲id 获得歌曲，歌手的信息
	 * @param songId
	 * @return
	 */
	public Song getOneSong(Integer songId) {
		return songDao.getOneSong(songId);
	}

	

	
	/**
	 * 查询出所有的歌曲中的六笔最热歌曲
	 * 2018年11月26日12:46:15 蓝道良
	 */
	public List<Song> getSixSong(){
		return songDao.selectSixSong();
	}
	
	
	
	public int insertSongMsg(Song song){
		return songDao.insertSongMsg(song);
	}
	
	/**
	 * 修改歌曲信息
	 * @param string
	 */
    public Integer updateSong(Song song) {
        return this.songDao.updateSong1(song);
    }

    public List<Song> findList() {
         return songDao.findList();
    }

    
    
    /**
     * 获取全部歌曲信息
     * @return
     */
	public List<SongViewModel> findAll(Integer pageNum,Integer pageSize) {
		Integer startNum = (pageNum-1)*pageSize;
		return this.songDao.findAll(startNum,pageSize);
	}

	/**
	 * 获取歌曲个数
	 * @return
	 */
	public int findCount() {
		return this.songDao.findCount();
	}

	
	
	
	/**
	 * 数据更新
	 * @param songViewModel
	 * @return
	 */
	public int update(SongViewModel songViewModel) {
		return this.songDao.update(songViewModel);
	}
	
	/**
	 * 数据插入
	 * @param songViewModel
	 * @return
	 */
	public int insert(SongViewModel songViewModel) {
		// 插入原始数据
		songViewModel.setTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		songViewModel.setCount(0);
		songViewModel.setRemark("1");
		return this.songDao.insert(songViewModel);
	}
	
	/**
	 * 首页获取歌手歌曲总数和总歌曲播放次数
	 * @return
	 */
	public List<SongCountViewModel> findSongCount() {
		return this.songDao.findSongCount();
	}
	
	/**
	 * 根据关键字获取歌曲信息
	 * @param searchmsg
	 * @return
	 */
	public List<SongViewModel> findBySearch(String searchmsg){
		// 1.获取姓名与关键字内容相似的歌手信息
		String name="%"+searchmsg+"%";
		List<SingerViewModel> singList= this.singerDao.findByName(name);
		
		// 2.创建集合，保存歌曲信息
		List<SongViewModel> songList=new ArrayList<SongViewModel>();
		
		// 3.歌曲集合添加数据
		for(SingerViewModel singer:singList) {
			songList.addAll(this.songDao.findBySingerId(singer.getId()));
		}
		songList.addAll(this.songDao.findByName(name));
		
		
		// 4.搜索表数据处理
		if(this.searchService.searchHandler(searchmsg) != 1) {
			System.out.println("搜索次数修改异常！");
		}
		return songList;
	}

	/**
	 * 主键获取
	 * @param id
	 * @return
	 */
	public SongViewModel findByPrimaryKey(Integer id) {
		return this.songDao.findByPrimaryKey(id);
	}
	
	/**
	 * 榜单数据获取
	 * @param songTypeString
	 * @return
	 */
	public List<View_AllMessage> getSongByType(String songTypeString){
		Integer songTypeId = Integer.parseInt(songTypeString);
		return songDao.getSongByType(songTypeId);
	}
	
	/**
	 * 某歌手最火的六首歌
	 * @param songId
	 * @return
	 */
	public List<Song> getSixSongsBySongId(Integer songId){
		return songDao.selectSixSongsBySongId(songId);
	}
	
	/**
	 * 根据传入的类别编号获取最新的歌曲信息
	 * @param songTypeString
	 * @return
	 */
	public List<Song> getNewSongList(String songTypeString) {
		Integer songTypeid = Integer.parseInt(songTypeString);
		List<Song> songList = songDao.getNewSongListBySongType(songTypeid);
		return songList;
	}

	/**
	 * 根据歌手编号获取 歌手热门歌曲
	 * @param id
	 * @return
	 */
	public List<SongViewModel> findHot(Integer singerId) {
		return this.songDao.findHot(singerId);
	}
}
