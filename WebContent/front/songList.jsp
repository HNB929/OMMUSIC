<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
		<link href="" type="image/x-icon" rel="shortcut icon">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/front/songList_files/reset.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/front/songList_files/jquery.mCustomScrollbar.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/front/songList_files/common.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/front/songList_files/player.css">
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/front/songList_files/charts.css">
		<title>歌单</title>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/front/songList_files/ugc.css">
		<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
		<script src="src/js/play.js" type="text/javascript" charset="utf-8"></script>
		<script>
			$(function() {
				
				//存放上个界面传递过来的类型编号
				var songTypeString = getQueryString("songTypeString");
				//判断类型编号
				if(songTypeString == ""){
					songTypeString = "9";
				}else{
					$.each($("#chartsContent .leftNav .earth ul li .songType"),function(){
						//匹配传递过来的类型编号对应的列表组件
						if($(this).val() == songTypeString){
							//设置当前对象添加class
							$("#chartsContent .leftNav .earth ul li .songType").removeClass("clicked");
							$(this).addClass("clicked");
							//设置当前对象的兄弟样式课件
							$("#chartsContent .leftNav .earth ul li .songType").prev("a").prev("span").css("visibility","hidden");
							$(this).prev("a").prev("span").css("visibility","visible");
						}
					});
				}
				
				//页面加载获取最新的播放列表（默认获取最新歌曲）
				getList(songTypeString);
				
				//遍历播放列表，绑定事件
				$.each($(".songType"), function(index) {
					$(this).on("click", function() {
						var songTypeString = $(this).val();
						getList(songTypeString);
					});
				});
				
				//分类列表设置属性样式
				$.each($("#chartsContent .leftNav .earth ul li .songType"),function(){
					// 设置悬浮小手 
					$(this).css("cursor",'pointer');
					//设置背景颜色
					$(this).css("backgroundColor","white");
					//设置点击事件
					$(this).on("click",function(){
						console.log($(this).prev("a").prev("span").html());
						$("#chartsContent .leftNav .earth ul li .songType").removeClass("clicked");
						//设置当前对象添加class
						$(this).addClass("clicked");
						$("#chartsContent .leftNav .earth ul li .songType").prev("a").prev("span").css("visibility","hidden");
						//设置当前对象的兄弟样式可见
						$(this).prev("a").prev("span").css("visibility","visible");
						
					});
				});
							
				var songIdStrings = "";
				
				//给 《播放全部》 按钮添加事件
				$("#songControll .playAll").on("click",function(){
					console.log("我要播放全部歌曲---"+songIdStrings);
					addPlayListAndPlay(songIdStrings);
				});
										
				//给 《添加》 按钮添加事件
				$("#songControll .addAll").on("click",function(){
					console.log("我要播放添加全部歌曲到我的播放列表"+songIdStrings);
					addPlayList(songIdStrings);
				});
				
				/**
				 * 根据传入的参数打开新的窗口
				 * @param {Object} url
				 */
				function openNewWindow(url) {
		            let a = $("<a href='"+url+"' target='_blank'>播放界面</a>").get(0);
		            let e = document.createEvent('MouseEvents');
		            e.initEvent( 'click', false, false );
		            a.dispatchEvent(e);
		        }
				
				/**
				 * 获取请求参数里面的东西 
				 * @param {Object} str
				 */
				function getQueryString(str) {
					var result = window.location.search.match(new RegExp("[\?\&]" + str + "=([^\&]+)", "i"));
					if(result == null || result.length < 1) {
						return "";
					}
					return result[1];
				}
				
				/**
				 * 获取播放列表
				 * @param {Object} songTypeString
				 */
				function getList(songTypeString){
					$.ajax({
						url: "../getNewSongListBySongType.action?songTypeString=" + songTypeString,
						contentType: "application/json;charset=utf-8",
						dataType: "json",
						type: "post",
						success: function(data) {
							console.log(data);
							// 1.移除原始列表
							$("#songList li").remove();
							// 2.追加新歌曲列表
							$.each(data, function(index, song) {
								$("#songList").append(
									"<li>"+
										"<button class='songId' value='"+song.songId+"' ></button>"+
										"<div class='leftInfo'>"+
											"<p class='num'>"+(index+1)+"</p>"+
										"</div>"+
										"<div class='name'>"+
											"<a href='music-detail.jsp?songId="+song.songId+"'>"+song.songName+"</a>"+
										"</div>"+
										"<div class='artist'>"+
											"<a href='../singerInfo.action?singerId="+song.singerid+"'>"+song.singer.singerName+"</a>"+
										"</div>"+
										"<div class='heat'>"+
											"<div class='heatValue' style='width:"+song.playCount+"'></div>"+
										"</div>"+
										"<div class='listRight'>"+
											"<div class='tools' style='display: none;padding-left:20px;'>"+
												"<a class='play playSong' title='播放' onclick='addPlayListAndPlay(" + song.songId + ")'></a>"+
												"<a class='add' title='添加' onclick='addPlayList(" + song.songId + ")'></a>"+
												"<a class='down click_log' href='${pageContext.request.contextPath}/download.action?lrc="+song.lyric+"&mp="+song.mp3+"' title='下载'></a>"+
											"</div>"+
											"<span style='display:block;'>"+song.duration+"</sapn>"+
										"</div>"+
									"</li>"
								);
							});
							
							// 3.设置鼠标悬浮在歌曲列表上，显示下载，播放，加入播放列表组件
							$.each($("#songList li"),function(){
								$(this).hover(function(){//鼠标悬浮上去
									$(this).children(".listRight").children(".tools").css("display","block");
									$(this).children(".listRight").children("span").css("display","none");
								},function(){//鼠标离开
									$(this).children(".listRight").children(".tools").css("display","none");
									$(this).children(".listRight").children("span").css("display","block");
								})
							});
							
							// 4. 获取全部的歌曲编号，拼接到字符串中
							songIdStrings = "";
							$.each($("#songList li .songId"),function(index){
								//存放全部的歌曲编号的字符串
								songIdStrings += $(this).val()+"/"; 
							});
							
						}
					});
				}
				
			});
		</script>

	</head>

	<body avalonctrl="player">

		<!-- 头部 开始 -->
		<div id="top" style="height: 55px;">
			<object style="border:0px" type="text/x-scriptlet" data="head.jsp" width="100%" height="100%"></object>
		</div>
		<!-- 头部 结束 -->
		<!-- 内容 开始 -->
		<div id="chartsContent" class="comW">
			<!-- 歌单列表 开始 -->
			<div class="leftNav">
				<div class="earth bang">
					<h2 id="bang_navbar_world" class="down" data-flag="true">精选歌单</h2>
					<ul style="display: block;">
						<li class="active">
							<span style="visibility: visible;"></span>
							<a>
								<img src="./songList_files/1461561991794_.jpg">
							</a>
							<button class="name clicked songType" value="9">最新单曲</button>
						</li>
						<li class="">
							<span style="visibility: hidden;"></span>
							<a>
								<img src="./songList_files/1451890901298_.jpg"></a>
							<button class="name  songType" value="10">欧米热门单曲</button>
						</li>
						<li class="">
							<span style="visibility: hidden;"></span>
							<a>
								<img src="./songList_files/1452859847170_.jpg"></a>
							<button class="name songType" value="1">最新网络单曲</button>
						</li>
						<li class="">
							<span style="visibility: hidden;"></span>
							<a>
								<img src="./songList_files/1448940356781_.jpg">
							</a>
							<button class="name songType" value="2">最新DJ舞曲</button>
						</li>
						<li class="">
							<span style="visibility: hidden;"></span>
							<a>
								<img src="./songList_files/1444623978017_.jpg">
							</a>
							<button class="name songType" value="3">最新伤感情歌</button>
						</li>
						<li class="">
							<span style="visibility: hidden;"></span>
							<a>
								<img src="./songList_files/1452244642923_.jpg">
							</a>
							<button class="name songType" value="4">最新影视单曲</button>
						</li>
						<li class="">
							<span style="visibility: hidden;"></span>
							<a>
								<img src="./songList_files/1437214765360_.jpg">
							</a>
							<button class="name songType" value="5">KTV经典歌曲</button>
						</li>
						<li class="">
							<span style="visibility: hidden;"></span>
							<a>
								<img src="./songList_files/1452480426467_.jpg">
							</a>
							<button class="name songType" value="7">最新欧美单曲</button>
						</li>
						<li class="">
							<span style="visibility: hidden;"></span>
							<a>
								<img src="./songList_files/1458528231607_.jpg">
							</a>
							<button class="name songType" value="8">最新日韩原声</button>
						</li>
						<li class="">
							<span style="visibility: hidden;"></span>
							<a>
								<img src="./songList_files/1452480444427_.jpg">
							</a>
							<button class="name songType" value="6">最新民谣单曲</button>
						</li>
					</ul>
				</div>
			</div>
			<!-- 歌单 列表 结束 -->

			<div class="rightContentBox">
				<div id="songControll">
					<a class="playAll" href="#">播放全部</a>
					<a class="addAll" href="#">添加</a>
				</div>
				<div class="chooseAll" style="padding: 0 20px 0 52px;">
					<dl>
						<dd class="name">歌曲</dd>
						<dd class="artist">歌手</dd>
						<dd class="heat">热度</dd>
						<dd class="listRight">时长</dd>
					</dl>
				</div>



				<!-- 歌曲 列表  开始 -->
				<ul class="listMusic" id="songList">
					<li>
						<button value=""></button>
						<div class="leftInfo">
							<p class="num">1</p>
						</div>
						<div class="name">
							<a href="" target="_blank">我们</a>
						</div>
						<div class="artist">
							<a href="">林俊杰</a>
						</div>
						<div class="heat">
							<div class="heatValue" style="width:100%"></div>
						</div>
						<div class="listRight">
							<!-- 操作开始 -->
							<div class="tools" style="display: block;">
								<a class="play playSong" href=""></a>
								<a class="add" href=""></a>
								<a class="down click_log" href=""></a>
								<a class="share" title="分享"></a>
							</div>
							<!-- 操作结束 -->
							<!-- 时长 -->
							<span style="display: block;">05:28</span>
						</div>
					</li>
				</ul>
				<!-- 歌曲 列表结束 -->

			</div>
		</div>
		<!-- 内容 结束 -->
		<!-- 底部开始 -->
		<div id="bottom" style="height: 270px;">
			<object style="border:0px" type="text/x-scriptlet" data="bottom.jsp" width="100%" height="100%"></object>
		</div>
		<!-- 底部结束 -->
	</body>

</html>
