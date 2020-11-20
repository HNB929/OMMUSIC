<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta charset="utf-8" />
		<title>歌手详细</title>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
		<link href="" type="image/x-icon" rel="shortcut icon">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/front/singerInfo_files/reset.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/front/singerInfo_files/jquery.mCustomScrollbar.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/front/singerInfo_files/common.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/front/singerInfo_files/player.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/front/singerInfo_files/artistContent.css">
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/front/singerInfo_files/ugc.css">
		<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
		<script src="http://localhost:8080/OMmusic/front/src/js/play.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			$(function() {
				// 1.娱乐歌曲界面切换
				$("#tab_info").click(function() {
					$("#tab_music").removeClass("clicked");
					$(this).addClass("clicked");
					$("#song").hide();
					$("#disport").fadeIn();
				});
				$("#tab_music").click(function() {
					$("#tab_info").removeClass("clicked");
					$(this).addClass("clicked");
					$("#disport").hide();
					$("#song").fadeIn();
				});

				//分页实现
				var singerId = getQueryString("singerId");
				//根据歌手编号获取歌手的全部歌曲
				$("#tab_music").click(function() {
					$.ajax({
						url: "pageSongBySinger.action",
						data: {
							"singerId": singerId,
							"currPage": 1
						},
						contentType: "application/json;charset=utf-8",
						type: "get",
						dataType: "json",
						success: function(data) {
							showSongList(data);
						}
					});
				});

				//上一页
				$(document).on("click", ".noprev", function() {
					$.ajax({
						url: "pageSongBySinger.action",
						data: {
							"singerId": singerId,
							"currPage": $("a.current").text() - 1
						},
						contentType: "application/json;charset=utf-8",
						type: "get",
						dataType: "json",
						success: function(data) {
							showSongList(data);
						}
					});
				});
				//下一页
				$(document).on("click", ".next", function() {
					$.ajax({
						url: "pageSongBySinger.action",
						data: {
							"singerId": singerId,
							"currPage": eval($("a.current").text() + "+1")
						},
						contentType: "application/json;charset=utf-8",
						type: "get",
						dataType: "json",
						success: function(data) {
							showSongList(data);
						}
					});
				});
				
				//选中页
				$(document).on("click", ".currPageNo", function() {
					$.ajax({
						url: "pageSongBySinger.action",
						data: {
							"singerId": singerId,
							"currPage": $(this).text()
						},
						contentType: "application/json;charset=utf-8",
						type: "get",
						dataType: "json",
						success: function(data) {
							showSongList(data);
						}

					});
				});

				var songIdStrings  = "";
				//给 《播放全部》 按钮添加事件
				$("#songControll .playAll").on("click", function() {
					console.log("我要播放全部歌曲---" + songIdStrings);
					addPlayListAndPlay(songIdStrings);
				});
				
				//给 《添加》 按钮添加事件
				$("#songControll .addAll").on("click", function() {
					console.log("我要添加全部歌曲到我的播放列表" + songIdStrings);
					addPlayList(songIdStrings)
				});

				/*
				 * 这个方法获取上个页面传入的值，即歌手id
				 */
				function getQueryString(name) {
					var result = window.location.search.match(new RegExp("[\?\&]" +
						name + "=([^\&]+)", "i"));
					if(result == null || result.length < 1) {
						return "";
					}
					return result[1];
				}

				//封装显示方法
				function showSongList(data) {
					// 1.设置鼠标悬浮样式
					$("#page").css("cursor", "pointer")
					// 2.移除之前数据
					$("#listMusic li").remove();
					$("#page a").remove();
					// 3.获取全部的歌曲编号，拼接到字符串中
					$.each(data.list,function(index, song) {
						$("#listMusic").append(
							"<li class=\"onLine\">"+
								"<button class='songId' value='" + song.songId + "' ></button>"+
								"<div class=\"name\">" +
									"<a href=\"front/music-detail.jsp?songId=" + song.songId +"\" >" +song.songName + "</a>"+
								"</div>"+
								"<div class=\"artist\">" +
									"<a >" + song.singer.singerName + "</a>"+
								"</div>" +
								"<div class=\"heat\">"+
									"<div class=\"heatValue\" style=\"width:" + song.playCount + "\"></div>"+
								"</div>" +
								"<div class=\"listRight\">"+
									"<div class=\"tools\" style=\"display:block;\">" +
										"<div class=\"tips\" style=\"visibility: hidden\"></div>"+
										"<a class=\"play playSong\" title='播放' onclick='addPlayListAndPlay(" + song.songId + ")'></a>" +
										"<a class=\"add\" title='添加到播放列表' onclick='addPlayList(" + song.songId + ")'></a>"+
										"<a class=\"down click_log\" title='下载' href=\"${pageContext.request.contextPath}/download.action?lrc=" + song.lyric + "&mp=" + song.mp3 + " \"></a>" +
									"</div>"+
								"</div>"+
							"</li>"
						);
						
					});
					
					// 4.鼠标悬浮改边背景颜色
					$("#listMusic .onLine").mouseover(function() {
						$(this).css("background","rgb(221,221,221,0.5)");
					});
					$("#listMusic .onLine").mouseout(function() {
						$(this).css("background", "white");
					});
					
					// 5.获取歌曲列表
					songIdStrings = "";
					$.each($("#listMusic li .songId"), function(index) {
						songIdStrings += $(this).val() + "/";
					});
					
					// 6.设置鼠标悬浮
					$("#songControll .playAll").css("cursor","pointer");
					$("#songControll .addAll").css("cursor","pointer");
					$("#listMusic li .listRight .tools .play").css("cursor","pointer");
					$("#listMusic li .listRight .tools .add").css("cursor","pointer");
					
					// 7. 追加分页信息
					$("#page").append('<a  class="noprev"><img src="front/singerInfo_files/prev.png"></a>');

					for(var i = 1; i <= data.allPage; i++) {
						if(data.currPage == i) {
							$("#page").append('<a class="current currPageNo" id="">' + i +'</a>');
						} else {
							$("#page").append('<a class="currPageNo" id="currPageNo">' + i +'</a>');
						}
					}
					$("#page").append('<a class="next"><img src="front/singerInfo_files/next.png"></a>');
				}
			});
		</script>
	</head>

	<body avalonctrl="player">
		<!-- 头部 开始 -->
		<div id="top" style="height: 55px;">
			<object style="border: 0px" type="text/x-scriptlet" data="front/head.jsp" width="100%" height="100%"></object>
		</div>
		<!-- 头部 结束 -->

		<!-- 内容开始 -->
		<div id="artistContent">
			<input type="hidden" id="tab_control" value="info">
			<!-- 歌手标签 start -->
			<div class="artistTop" data-artistid="336">
				<img src="front/${singer.picture}" class="lazyLoad">
				<div class="tab">
					<h1>${singer.singerName}</h1>
					<ul>
						<li class="disport clicked" id="tab_info"><span id="singer_info">娱乐</span></li>
						<li class="song" id="tab_music"><span id="singer_music">歌曲</span></li>
					</ul>
				</div>
			</div>
			<!-- 歌手标签  end -->

			<!-- 歌手左内容 开始-->
			<div id="conBox">
				<!-- 歌手娱乐tab开始 -->
				<div id="disport" class="con" style="display: block;">
					<div id="artistInfo">
						<div class="title_">
							<i></i>
							<h2>基本信息</h2>
						</div>
						<table>
							<tbody>
								<tr style="background: rgb(242, 242, 246);">
									<td>姓名： ${singer.singerName}</td>
									<td>性别： ${singer.sex}</td>
								</tr>
								<tr>
									<td>别名： ${singer.alias}</td>
									<td>国籍： ${singer.nation}</td>
								</tr>
								<tr style="background: rgb(242, 242, 246);">
									<td>语言： ${singer.language}</td>
									<td>出生地： ${singer.birthplace}</td>
								</tr>
								<tr>
									<td>生日： ${singer.birthday}</td>
									<td>星座： ${singer.constellation}</td>
								</tr>
								<tr style="background: rgb(242, 242, 246);">
									<td>身高： ${singer.height}</td>
									<td>体重： ${singer.weight}</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div id="introduce">
						<div class="title_">
							<i></i>
							<h2>个人简介</h2>
						</div>
						<p>
							中文名:${singer.singerName}<br> &nbsp;外文名:${singer.firstname} <br> &nbsp;国籍:${singer.nation} <br> &nbsp;出生地:${singer.birthplace}
							<br> &nbsp;职业:${singer.job} <br> &nbsp;经纪公司:${singer.company} <br> &nbsp;身高:${singer.height} <br> &nbsp;星座:${singer.constellation} <br> &nbsp;出生日期:${singer.birthday} <br> &nbsp;代表作品:${singer.representative} <br> &nbsp;毕业院校:${singer.school} <br>
						</p>
					</div>
				</div>
				<!-- 歌手娱乐tab结束 -->

				<!-- 歌手单曲tab开始 -->
				<div id="song" class="con" style="display: none; height: 500px;">
					<div id="songControll">
						<a class="playAll">播放全部</a>
						<a class="addAll">添加</a>
					</div>

					<ul id="listMusic" class="listMusic" data-pn="0" data-rn="15" data-page="56">

					
					</ul>
					<!-- 分页 开始 -->
					<div id="page" class="page" style="margin-left: 500px; margin-top: -310px;">
	
					</div>
					<!-- 分页 结束 -->
				</div>
				<!-- 歌手单曲tab结束 -->

			</div>
			<!-- 歌手左内容 结束 -->
			<!-- 相似 歌手 开始 -->
			<div id="artistRight">
				<h2>相似歌手</h2>
				<ul>
					<li>
						<a href="./singerInfo.action?singerId=8"><img src="front/singerInfo_files/645139024.jpg" class="lazyLoad"></a>
						<a href="./singerInfo.actioon?singerId=8"><span>王力宏</span></a>
					</li>

					<li>
						<a href="./singerInfo.action?singerId=4"><img src="front/singerInfo_files/3913067294.jpg" onerror="imgOnError(this,&#39;artist&#39;)" class="lazyLoad"></a>
						<a href="./singerInfo.action?singerId=4"><span>光良</span></a>
					</li>

					<li>
						<a href="./singerInfo.action?singerId=2"><img src="front/singerInfo_files/2937285581.jpg" onerror="imgOnError(this,&#39;artist&#39;)" class="lazyLoad"></a>
						<a href="./singerInfo.action?singerId=2"><span>蔡健雅</span></a>
					</li>

					<li>
						<a href="./singerInfo.action?singerId=15"><img src="front/singerInfo_files/2461721588.jpg" onerror="imgOnError(this,&#39;artist&#39;)" class="lazyLoad"></a>
						<a href="./singerInfo.action?singerId=15"><span>周杰伦</span></a>
					</li>

					<li>
						<a href="./singerInfo.action?singerId=19"><img src="front/singerInfo_files/2220632891.jpg" onerror="imgOnError(this,&#39;artist&#39;)" class="lazyLoad"></a>
						<a href="./singerInfo.action?singerId=19"><span>S.H.E</span></a>
					</li>

					<li>
						<a href="./singerInfo.action?singerId=13"><img src="front/singerInfo_files/2102134023.jpg" onerror="imgOnError(this,&#39;artist&#39;)" class="lazyLoad"></a>
						<a href="./singerInfo.action?singerId=13"><span>张学友</span></a>
					</li>

				</ul>
			</div>
			<!-- 相似内容 结束 -->
		</div>
		<!-- 内容 结束 -->
		<!-- 底部开始 -->
		<div id="bottom" style="height: 270px;">
			<object style="border: 0px" type="text/x-scriptlet" data="front/bottom.jsp" width="100%" height="100%"></object>
		</div>
		<!-- 底部结束 -->
	</body>

</html>