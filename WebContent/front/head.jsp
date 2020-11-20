<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<base target="_top">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>头部</title>
		<link rel="stylesheet" type="text/css" href="./index/reset.css">
		<link rel="stylesheet" href="./index/jquery.mCustomScrollbar.css">
		<link rel="stylesheet" href="./index/common.css">
		<link rel="stylesheet" href="./index/player.css">
		<link type="text/css" rel="stylesheet" href="./index/index.css">
		<link type="text/css" rel="stylesheet" href="./index/ugc.css">
		<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
		<script type="text/javascript">
			$(function() {
				// 1.搜索按钮绑定事件
				$(document).on("click", "#searchmsg", function() {
					var msg = $("#search").val();
					$("#searchmsg").prop("href", "http://localhost:8080/OMmusic/front/search.jsp?msg=" + msg);
				});

				// 2.设置登录之后显示用户名
				var username = "${user.username}"
				if (username !== "") {
					$("#loginBtn").html(username);
					$("#loginBtn").prop("href", "javascript:");
					$("#removeUser").prop("style", "display:block");
					$("#removeUser").css("cursor", "pointer");
					
				}

				// 3.退出事件
				$("#removeUser").on("click",function(){
					window.location.href = "http://localhost:8080/OMmusic/removeUser";
				})

			});
		</script>
	</head>

	<body>
		<!-- 头部 开始 -->
		<div id="top" kw_ver="1.0.0" style="background-color: black;">
			<div class="top">
				<!-- logo 开始 -->
				<a class="logo" href="index.jsp"> 
					<img src="./index/logo2.png" alt="logo" style="width: 120px; margin-right: 20px; margin-top: 8px;">
				</a>
				<!-- logo 结束 -->
				<ul class="nav">
					<li><a href="index.jsp">首页</a></li>
					<li><a href="ranking.jsp">榜单</a></li>
					<li><a href="singer.jsp">歌手</a></li>
					<li><a href="songList.jsp">歌单</a></li>
					<li><a href="play.jsp" target="_black">播放列表</a></li>
				</ul>
				<!-- 搜索框 开始 -->
				<div class="tools">
					<div class="search">
						<input type="text" id="search" name="search" placeholder="找到好音乐">
						<a class="searchBtn" title="搜索" id="searchmsg" target="_top"></a>
					</div>
					<div class="logo">
						<a id="removeUser" style="display: none;">退出</a>
					</div>
					<div class="logo">
						<a id="loginBtn" style="display: block;cursor: pointer;" href="loginAndRegister.jsp">登录</a>
					</div>
				</div>
				<!-- 搜索框 结束 -->
			</div>
		</div>
		<!-- 头部 结束 -->
	</body>
</html>
