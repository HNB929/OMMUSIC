package com.music.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.music.bean.Song;
import com.music.bean.View_AllMessage;
import com.music.bean.view.SongCountViewModel;
import com.music.bean.view.SongViewModel;

public interface SongDao {

    //歌曲的相信信息
    Song getOneSong(Integer id);

    /***
     * 修改歌曲信息
     * @param song
     * @return
     */
    int updateSong(Song song);


    
    //根据上架时间获取歌曲信息
    List<View_AllMessage> getSongByTime();
    
    //根据类别获取歌曲信息
    List<Song> getSongBySongType(Integer songTypeId);
    
    //根据歌手获取歌曲信息
    List<Song> getSongBySinger(Integer singerId);
    //分页查询
    //多个参数用注解指定
    
    
    List<Song> getPageSongBySinger(
        @Param("singerId")Integer singerId,
    	@Param("startNum")int startNum,
    	@Param("pageSize")int pageSize);
    
    
    
    
    //获取单个歌手所有歌曲的笔数
    int getSongCount(Integer singerId);
   /**
    * 插入歌曲信息
    * @param song
    * @return
    */
    int insertSongMsg(Song song);
    
    //根据歌手id查询歌手热度前五
    List<Song> getSongBySingerByCount(Integer singerTypeId);
  
    /**
     * 获取华语歌曲榜
     * @return
     */
    List<View_AllMessage> getSongBySingertypeName();

    

    /**
     * 获取歌曲类型
     * @return
     */
    List<String> selectAllSongType();




    Integer updateSong1(Song song);

    List<Song> findList();
    
    
    
    
    
    /**
     * 获取全部歌曲信息
     * @return
     */
	List<SongViewModel> findAll(@Param("startNum")Integer startNum,@Param("pageSize")Integer pageSize);
	
	/**
	 * 获取歌曲个数
	 * @return
	 */
	int findCount();
	
	
	/**
	 * 数据更新
	 * @param songViewModel
	 * @return
	 */
	int update(SongViewModel songViewModel);
    
	
	/**
	 * 数据插入
	 * @param songViewModel
	 * @return
	 */
	int insert(SongViewModel songViewModel);
	
	/**
	 * 首页获取歌手歌曲总数和总歌曲播放次数
	 * @return
	 */
	List<SongCountViewModel> findSongCount();

    /**
     * 根据歌手编号获取歌曲信息
     * @param id
     * @return
     */
    List<SongViewModel> findBySingerId(@Param("singerId")Integer singerId);
    
    /**
     * 传入对象根据对象的歌曲名字查询对象集合
     * @param song
     * @return
     */
    List<SongViewModel> findByName(@Param("songName")String songName);
    
    /**
     * 主键获取歌曲信息
     * @param id
     * @return
     */
	SongViewModel findByPrimaryKey(@Param("id")Integer id);
	
    /**
     * 榜单数据获取
     * @param songTypeId
     * @return
     */
    List<View_AllMessage> getSongByType(Integer songTypeId);
    
	/**
	 * 某歌手最火的六首歌
	 * @param songId
	 * @return
	 */
    List<Song> selectSixSongsBySongId(Integer songId);
    

    /**
     * 系统最火的六首歌
     * @return
     */
    List<Song> selectSixSong();
    
    /**
     * 根据传入的类别编号获取最新的歌曲信息
     * @param songTypeid
     * @return
     */
	List<Song> getNewSongListBySongType(Integer songTypeid);
	
	/**
	 * 根据歌手编号获取歌手热门歌曲
	 * @param singerId
	 * @return
	 */
	List<SongViewModel> findHot(@Param("singerId")Integer singerId);
}