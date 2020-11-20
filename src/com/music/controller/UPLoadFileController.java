package com.music.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.music.bean.Song;
import com.music.service.SongService;

@Controller
public class UPLoadFileController {
	@Resource
	private SongService songService;

	@RequestMapping(value="/upSong")
	public String doFilr(@RequestParam  MultipartFile[] upsongFile,HttpSession session,HttpServletRequest request) throws IllegalStateException, IOException{
		Song song = new Song();
		for(int i = 0 ; i < upsongFile.length ; i++) {
			 String songName= upsongFile[i].getOriginalFilename();
			//System.out.println(songName);
			
			if(songName.endsWith("mp3")) {
				/*前半部份路径，将webContent下的（front/src/data/mp3）文件设为绝对路径*/
				String leftPath = session.getServletContext().getRealPath("/front/src/data/mp3");
				File file = new File(leftPath,songName);
				upsongFile[i].transferTo(file);
				song.setMp3("src/data/mp3/"+songName);
			}else if(songName.endsWith("lrc")) {
				//-----------------------
				String leftPath = session.getServletContext().getRealPath("/front/src/data/lrc");
				File file = new File(leftPath,songName);
				upsongFile[i].transferTo(file);
				song.setLyric("src/data/lrc/"+songName);
			}
		}
		
		
		/*for(MultipartFile item : upsongFile) {
			if(item.getSize()>0) {
				获取文件名作为保存到服务器的名称
				String songName = item.getOriginalFilename();
				if(songName.endsWith("mp3")) {
					前半部份路径，将webContent下的（front/src/data/mp3）文件设为绝对路径
					String leftPath = session.getServletContext().getRealPath("/front/src/data/mp3");
					File file = new File(leftPath,songName);
					item.transferTo(file);
					
				}else if(songName.endsWith("lrc")) {
					//-----------------------
					String leftPath = session.getServletContext().getRealPath("/front/src/data/lrc");
					File file = new File(leftPath,songName);
					item.transferTo(file);
				}
			}	
		}*/
		String songNames=request.getParameter("songName");
		//System.out.println(songNames);
		SimpleDateFormat smf = new SimpleDateFormat("yyy年MM月dd日 : hh:mm:ss");
		
		String releaseTime = smf.format(new Date());
		String singerId = request.getParameter("singerid");
		
		//System.out.println(singerId);
		int singerid = Integer.parseInt(singerId);
		String songTypeid = request.getParameter("songTypeid");
		
		//System.out.println(songTypeid);
		int songTypeId = Integer.parseInt(songTypeid);
		
		
		//将传过来的值set到song中
		song.setSongName(songNames);
		song.setReleaseTime(releaseTime);
		song.setSingerid(singerid);
		song.setSongTypeid(songTypeId);
		
		int num = songService.insertSongMsg(song);
		//System.out.println(song);
		if(num > 0 ) {
			request.setAttribute("msg", "插入成功！！！！");
		}else {
			request.setAttribute("msg", "插入失败！！！");
		}	
		return "manager/black";
	}
}
