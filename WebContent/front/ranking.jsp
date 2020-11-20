<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
		<link href="" type="image/x-icon" rel="shortcut icon">
		<link rel="stylesheet" type="text/css" href="./ranking_files/reset.css">
		<link rel="stylesheet" href="./ranking_files/jquery.mCustomScrollbar.css">
		<link rel="stylesheet" href="./ranking_files/common.css">
		<link rel="stylesheet" href="./ranking_files/player.css">
		<link type="text/css" rel="stylesheet" href="./ranking_files/charts.css">
		<title>欧米榜单</title>
		<link type="text/css" rel="stylesheet" href="./ranking_files/ugc.css">
		<link href="./ranking_files/comm_COMMENT.css" rel="stylesheet" type="text/css">
		<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
		<script src="src/js/play.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			$(function() {

				// 1.页面加载 获取热歌榜歌曲信息
				showMessage(1);

				// 2. 设置页面css 样式
				$.each($("#chartsContent .leftNav .classify ul .songtype"), function() {
					$(this).on("click", function() {
						$("#chartsContent .leftNav .classify ul .songtype").children("a").prev("span").css("visibility", "hidden");
						$(this).children("a").prev("span").css("visibility", "visible");
					})
				})
				$.each($("#chartsContent .leftNav .classify ul li"), function() {
					$(this).css("cursor", 'pointer');
				});

				// 3.导航按钮绑定事件
				/* 热搜榜 */
				$("#ahotsong").click(function() {
					$(this).addClass("clicked");
					$("#anewsong").removeClass("clicked");
					$("#achineselksong").removeClass("clicked");
					$("#afolksong").removeClass("clicked");
					$("#anetsong").removeClass("clicked");
					$("#aeasong").removeClass("clicked");
					$("#ajssong").removeClass("clicked");
					//$("#hotsong span").css("visibility","visible")
				});
				$("#hotsong").click(function() {
					showMessage(1);
				});
				/* 新歌榜*/
				$("#anewsong").click(function() {
					$("#ahotsong").removeClass("clicked");
					$(this).addClass("clicked");
					$("#achineselksong").removeClass("clicked");
					$("#afolksong").removeClass("clicked");
					$("#anetsong").removeClass("clicked");
					$("#aeasong").removeClass("clicked");
					$("#ajssong").removeClass("clicked");
				});
				$("#newsong").click(function() {
					showMessage(2);
				});
				/* 华语榜*/
				$("#achineselksong").click(function() {
					$("#ahotsong").removeClass("clicked");
					$("#anewsong").removeClass("clicked");
					$(this).addClass("clicked");
					$("#afolksong").removeClass("clicked");
					$("#anetsong").removeClass("clicked");
					$("#aeasong").removeClass("clicked");
					$("#ajssong").removeClass("clicked");
				});
				$("#chineselksong").click(function() {
					showMessage(3);
				});
				/* 民谣榜*/
				$("#afolksong").click(function() {
					$("#ahotsong").removeClass("clicked");
					$("#anewsong").removeClass("clicked");
					$("#achineselksong").removeClass("clicked");
					$(this).addClass("clicked");
					$("#anetsong").removeClass("clicked");
					$("#aeasong").removeClass("clicked");
					$("#ajssong").removeClass("clicked");
				});
				$("#folksong").click(function() {
					showMessage(4);
				});
				/* 网络榜 */
				$("#anetsong").click(function() {
					$("#ahotsong").removeClass("clicked");
					$("#anewsong").removeClass("clicked");
					$("#achineselksong").removeClass("clicked");
					$("#afolksong").removeClass("clicked");
					$(this).addClass("clicked");
					$("#aeasong").removeClass("clicked");
					$("#ajssong").removeClass("clicked");
				});
				$("#netsong").click(function() {
					showMessage(5);
				});
				/* 欧美 */
				$("#aeasong").click(function() {
					$("#ahotsong").removeClass("clicked");
					$("#anewsong").removeClass("clicked");
					$("#achineselksong").removeClass("clicked");
					$("#afolksong").removeClass("clicked");
					$("#anetsong").removeClass("clicked");
					$(this).addClass("clicked");
					$("#ajssong").removeClass("clicked");
				});
				$("#easong").click(function() {
					showMessage(6);
				});
				/* 日韩 */
				$("#ajssong").click(function() {
					$("#ahotsong").removeClass("clicked");
					$("#anewsong").removeClass("clicked");
					$("#achineselksong").removeClass("clicked");
					$("#afolksong").removeClass("clicked");
					$("#anetsong").removeClass("clicked");
					$("#aeasong").removeClass("clicked");
					$(this).addClass("clicked");
				});
				$("#jssong").click(function() {
					showMessage(7);
				});
				//修改更新时间
				var myDate = new Date();
				var time = myDate.toLocaleDateString();
				$("#songControll p").text("最近更新：" + time);

				var songIdStrings = "";

				// 5.给 《播放全部》 按钮添加事件
				$("#songControll .playAll").on("click", function() {
					console.log("点击播放全部")
					console.log(songIdStrings)
					addPlayListAndPlay(songIdStrings)
				});
				
				// 6.给 《添加》 按钮添加事件
				$("#songControll .addAll").on("click", function() {
					console.log("点击添加")
					console.log(songIdStrings)
					addPlayList(songIdStrings)
				});
				
				
				
				/**
				 * 获取歌曲信息
				 * @param {Object} songTypeString
				 */
				function showMessage(songTypeString) {
					// 1. 移除原先播放列表
					$("#songInfo li").remove();
					var num = 0;
					// 2. 获取数据, 在界面显示
					$.ajax({
						url: "http://localhost:8080/OMmusic/getSongByType.action?songTypeString=" + songTypeString,
						contentType: "application/json;charset=utf-8",
						dataType: "json",
						type: "get",
						success: function(data) {
							console.log(data)
							$.each(data, function(index, song) {
								var i = index + 1;
								num = num + 1;
								// 1 追加页面数据
								$("#songInfo").append(
									"<li class='showbtn'>" +
										"<button class='songId' value='" + song.songid + "'></button>" +
										"<div class='leftInfo'>" +
											"<p class='num'>" + i + "</p>" +
											"<div class='mTrend'>" +
												"<span class='e0'></span>" +
											"</div>" +
										"</div>" +
										"<div class='name'>" +
											"<a href='music-detail.jsp?songId=" + song.songid + "'>" + song.songname + "</a>" +
										"</div>" +
										"<div class='artist'>" +
											"<a href='../singerInfo.action?singerId=" + song.singerid + "'>" + song.singername + "</a>" +
										"</div>" +
										"<div class='heat'>" +
											"<div class='heatValue' style='width:" + song.songcount + ";'></div>" +
										"</div>" +
										"<div class='listRight'>" +
											"<div class='tools' style='display: none; padding-left:20px'>" +
												"<a class='play playSong' title='播放' onclick='addPlayListAndPlay(" + song.songid + ")'></a>" +
												"<a class='add' title='添加到播放列表' onclick='addPlayList(" + song.songid + ")'></a>" +
												"<a class='down click_log' href='${pageContext.request.contextPath}/download.action?lrc=" + song.song.lyric +
													"&mp=" + song.song.mp3 + "' title='下载'/>" +
											"</div>" +
											"<span style='display:block;'>" + song.songtime + "</sapn>" +
										"</div>" +
									"</li>"
								);
							});

							// 2 设置鼠标悬浮在歌曲列表上，显示下载，播放，加入播放列表组件
							$.each($("#songInfo li"), function() {
								$(this).hover(function() { //上去
									$(this).children(".listRight").children(".tools").css("display", "block");
									$(this).children(".listRight").children("span").css("display", "none");
								}, function() { //下来
									$(this).children(".listRight").children(".tools").css("display", "none");
									$(this).children(".listRight").children("span").css("display", "block");
								})
							});

							// 3 设置鼠标悬浮
							$("#songControll .playAll").css("cursor", "pointer");
							$("#songControll .addAll").css("cursor", "pointer");
							$("#songInfo li .listRight .tools .play").css("cursor", "pointer");
							$("#songInfo li .listRight .tools .add").css("cursor", "pointer");

							// 4. 获取全部的歌曲编号，拼接到字符串中
							songIdStrings = "" ;
							$.each($("#songInfo li .songId"), function(index) {
								//存放全部的歌曲编号的字符串
								songIdStrings += $(this).val() + "/";
							});
						},
						
					});
					
					
				};

				/**
				 * 根据传入的参数打开新的窗口
				 * @param {Object} url
				 */
				function openNewWindow(url) {
					let a = $("<a href='" + url + "' target='_blank'>播放界面</a>").get(0);
					let e = document.createEvent('MouseEvents');
					e.initEvent('click', false, false);
					a.dispatchEvent(e);
				}
			});
		</script>
		<script>
			window._bd_share_config = {
				"common": {
					"bdText": "",
					"bdMiniList": false,
					"bdPic": "",
					"bdStyle": "1",
					"bdSize": "24"
				},
				"share": {},
				"selectShare": {
					"bdContainerClass": null,
					"bdSelectMiniList": ["more","qzone", "tsina", "tqq", "renren"]
				}
			};
			with(document) 0[(getElementsByTagName('head')[0] || body).appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion=' + ~(-new Date() / 36e5)];
		</script>
	</head>

	<body avalonctrl="player">

		<!-- 头部 开始 -->
		<div id="top" style="height: 55px;">
			<object style="border: 0px" type="text/x-scriptlet" data="head.jsp" width="100%" height="100%"></object>
		</div>
		<!-- 头部 结束 -->
		<!-- 内容 开始 -->
		<div id="chartsContent" class="comW">
			<!-- 排行榜 开始 -->
			<div class="leftNav">
				<div class="classify bang" data-choosed="酷我新歌榜">
					<h2 id="bang_navbar_class" class="down" data-flag="true">分类榜</h2>
					<ul style="display: block;">

						<li id="hotsong" data-name="欧米热歌榜" data-bangid="16" class="songtype"><span style="visibility: visible;"></span>
							<a> <img src="./ranking_files/1444901362664_.jpg" >
							</a>
							<a id="ahotsong" class="name clicked song">欧米热歌榜</a>
						</li>

						<li id="newsong" data-name="欧米新歌榜" data-bangid="17" class="songtype"><span></span>
							<a><img src="./ranking_files/1444901569447_.jpg" ></a>
							<a id="anewsong" class="name song">欧米新歌榜</a>
						</li>

						<li id="chineselksong" data-name="欧米华语榜" data-bangid="62" class="songtype"><span></span>
							<a><img src="./ranking_files/1530587833022_.png" ></a>
							<a id="achineselksong" class="name song">欧米华语榜</a>
						</li>

						<li id="folksong" data-name="欧米民谣榜" data-bangid="158" class="songtype"><span></span>
							<a><img src="./ranking_files/1531102994754_.png" ></a>
							<a id="afolksong" class="name">欧米民谣榜</a>
						</li>

						<li id="netsong" data-name=欧米网络榜 data-bangid="157" class="songtype"><span></span>
							<a><img src="./ranking_files/1531102994740_.png" ></a>
							<a id="anetsong" class="name">欧米网络榜</a>
						</li>

						<li id="easong" data-name="欧米欧美榜" data-bangid="22" class="songtype"><span></span>
							<a><img src="./ranking_files/1444901107656_.jpg" ></a>
							<a id="aeasong" class="name">欧米欧美榜</a>
						</li>

						<li id="jssong" data-name="欧米日韩榜" data-bangid="23" class="songtype"><span></span>
							<a><img src="./ranking_files/1444901436021_.jpg" ></a>
							<a id="ajssong" class="name">欧米日韩榜</a>
						</li>
					</ul>
				</div>
			</div>
			<!-- 排行版 结束 -->
			<!-- 右边 内容 开始 -->
			<div class="rightContentBox">
				<!-- 头部 图片 开始 -->
				<div class="banner">
					<img data-id="17" src="./ranking_files/bang_xgb.png">
				</div>
				<!-- 头部 图片 结束 -->

				<div id="songControll">
					<a class="playAll">播放全部</a>
					<a class="addAll">添加</a>
					<p>最近更新：2018-11-17</p>
				</div>

				<div class="chooseAll">
					<dl>
						<dd class="trend">&nbsp;</dd>
						<dd class="name">歌曲</dd>
						<dd class="artist">歌手</dd>
						<dd class="heat">热度</dd>
						<dd class="listRight">时长</dd>
					</dl>
				</div>

				<!-- 歌曲列表 开始 -->
				<ul class="listMusic" id="songInfo">
				</ul>
				<!-- 歌曲列表 结束 -->

			</div>
			<!-- 右边 内容 结束 -->
		</div>
		<!-- 内容 结束 -->
		<!-- 底部 开始 -->
		<div id="bottom" style="height: 270px;">
			<object style="border: 0px" type="text/x-scriptlet" data="bottom.jsp" width="100%" height="100%"></object>
		</div>
		<!-- 底部 结束 -->
	</body>

</html>
