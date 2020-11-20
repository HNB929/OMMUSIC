<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<base target="_black" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>播放测试</title>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		var songIds = "" ;
		console.log("------------");
/* 		$.each($(".id"),function(index,element){
			console.log("index="+index)
			songIds += index+1+","
		})
		console.log(songIds); */
		
		
		$("#play").on("click",function(){
			console.log("play");
			// 执行操作 
			 $.ajax({
				url : "../addListAndPlay.action",
				data : '{"songIds" : "2/21/25/26/32/35/33/"}',
				contentType : "application/json;charset=utf-8",
				dataType : "json",
				type : "post",
				success : function(data) {
					console.log("data="+data);
					console.log("data="+data.songIds);
				}
			}); 
		});

		$("#addList").on("click",function(){
			console.log("addList");
			//获取列表信息 
			// 执行操作 
			window.location.href="../addList.action?songIds=2,";
		}); 

		//ajax请求页面
		var student1 = '{"sname" : "jxy","age" : "18"}';
		$.ajax({
			url : "../jsonTest.action",
			data : student1,
			contentType : "application/json;charset=utf-8",
			dataType : "json",
			type : "post",
			success : function(data) {
				console.log(data);
			}
		});

	});
</script>
</head>
<body>
	<div class="id"></div>
	<div class="id"></div>
	<div class="id"></div>
	<div class="id"></div>
	
	<!-- 将列表中的歌曲加入到播放列表，并且播放当前列表的第一首歌曲 -->
	<button id="play">播放</button>
	
	<!-- 将当前列表下的歌曲加入到播放列表 -->
	<button id="addList">添加到播放列表</button>

	<a href="play.jsp">查看播放列表</a>
	{msg }
</body>
</html>