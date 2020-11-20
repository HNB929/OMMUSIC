<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
		<link href="" type="image/x-icon" rel="shortcut icon">
		<title>歌手分类</title>
		<link rel="stylesheet" type="text/css" href="./singer_files/reset.css">
		<link rel="stylesheet" href="./singer_files/jquery.mCustomScrollbar.css">
		<link rel="stylesheet" href="./singer_files/common.css">
		<link rel="stylesheet" href="./singer_files/player.css">
		<link rel="stylesheet" type="text/css" href="./singer_files/artist.css">
		<link type="text/css" rel="stylesheet" href="./singer_files/ugc.css">
		<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
		<style type="text/css">
			#test-tip {
				font-size: 0.3rem;
				height: 30px;
				background-color: red;
				color: white;
				font-weight: bolder;
				position: fixed;
				width: 100%;
				z-index: 99;
				text-align: center;
				line-height: 30px;
				top: 0;
			}
		</style>
		<script type="text/javascript">
			$(document).ready(function() {
				console.log("页面加载！")
				
				// 1. 获取热门歌手信息
				var pageNum = 1;
				var pageSize = 6;
				var sexNum = null;
				var firstName = null;
				var singerTypeId = null;
				findSingerInfo(pageNum,null,null,null);
				

				// 2.遍历首字母列表,首字母绑定事件
				$(".topSelect a").css("cursor", "pointer");
				$(".topSelect a").each(function() {
					$(this).click(function() {
						// 2.1 修改样式
						$(this).addClass("active");
						$(".topSelect a").not(this).removeClass("active");
						
						var firstName = $(this).text();
						console.log("firstName = " + firstName);
						
						if(firstName == "热门"){
							findSingerInfo(1,null,sexNum,singerTypeId);
						}else{
							findSingerInfo(1,firstName,sexNum,singerTypeId);
						}
					})
				})
				
				// 3. 歌手导航绑定事件
				$(".singerInfoButton").css("cursor", "pointer");
				$.each($(".singerInfoButton"),function(){
					$(this).on("click",function(){
						// 3.1 修改样式
						$(this).addClass("active");
						$(".singerInfoButton").not(this).removeClass("active");
						
						// 3.2 设置请求事件
						var singerTypeName = $(this).html();
						console.log(singerTypeName.trim());
						if(singerTypeName.trim() == "全部歌手"){
							findSingerInfo(1,firstName,null,null);
						}else if(singerTypeName.trim() == "华语歌手"){
							findSingerInfo(1,firstName,null,1);
						}else if(singerTypeName.trim() == "华语男歌手"){
							findSingerInfo(1,firstName,1,1);
						}else if(singerTypeName.trim() == "华语女歌手"){
							findSingerInfo(1,firstName,2,1);
						}else if(singerTypeName.trim() == "华语组合"){
							findSingerInfo(1,firstName,null,2);
						}else if(singerTypeName.trim() == "日韩歌手"){
							findSingerInfo(1,firstName,null,3);
						}else if(singerTypeName.trim() == "日韩男歌手"){
							findSingerInfo(1,firstName,1,3);
						}else if(singerTypeName.trim() == "日韩女歌手"){
							findSingerInfo(1,firstName,2,3);
						}else if(singerTypeName.trim() == "日韩组合"){
							findSingerInfo(1,firstName,null,4);
						}else if(singerTypeName.trim() == "欧美歌手"){
							findSingerInfo(1,firstName,null,5);
						}else if(singerTypeName.trim() == "欧美男歌手"){
							findSingerInfo(1,firstName,1,5);
						}else if(singerTypeName.trim() == "欧美女歌手"){
							findSingerInfo(1,firstName,2,5);
						}else if(singerTypeName.trim() == "欧美组合"){
							findSingerInfo(1,firstName,null,6);
						}
					});
					
				});
				
				
				/**
				 * 获取歌手信息
				 * @param {Object} pageNum
				 */
				function findSingerInfo(pageNum,firstName,sexNum,singerTypeId){
					// 1.ajax 获取歌手信息
					$.ajax({
						url: "http://localhost:8080/OMmusic/singer/findSingerInfo",
						data: {
							"pageNum": pageNum,
							"firstName": firstName,
							"sexNum": sexNum,
							"singerTypeId": singerTypeId
						},
						contentType: "application/json;charset=utf-8",
						dataType: "json",
						type: "GET",
						success: function(data) {
							console.log(data);
							if(data.success == false){
								alert(data.msg);
								return ;
							}
							// 2.显示歌手信息
							showSingerList(data);
						}
					});
				}
				
				/**
				 * 显示歌手信息
				 * @param {Object} data
				 */
				function showSingerList(data) {
					pageNum = data.pageNum;
					pageSize = data.pageSize;
					sexNum = data.sexNum;
					firstName = data.firstName;
					singerTypeId = data.singerTypeId;
					
					// 1.移除原先歌曲信息
					$("#artistList ul li").remove();
					
					// 2.追加歌手信息
					$.each(data.data, function(index, singer) {
						// 2.1 添加歌手信息
						$("#artistList ul").append(
							'<li>'+
								'<div class="artistTop">'+
									'<a href="../singerInfo.action?singerId=' + singer.id + '" class="artistLeft fl">'+
										'<img src="' + singer.picture + '">'+
										'<span class="splice"></span>'+
									'</a>'+
									'<div class="artistRight fr">'+
										'<div class="artistnav" data-id="336">'+
											'<a  class="title active">热歌</a>'+
										'</div>'+
										'<ol class="hotlist"></ol>'+
									'</div>'+
								'</div>'+
								'<a class="a_name" href = "../singerInfo.action?singerId='+singer.id+'" >' + singer.name + '</a>'+
							'</li>'
						);
					});
					
					// 3.追加歌曲信息
					$.each(data.data,function(index1,singer){
						$.each($(".hotlist"),function(index2){
							if(index1 == index2){
								var $hotlist = $(this);
								if(singer.songList != null){
									$.each(singer.songList,function(index3,song){
										$hotlist.append(
											'<li style="clear: both;width: auto;height: 28px;line-height: 28px;margin: 0;font-size: 12px;">'+
												'<a href="music-detail.jsp?songId=' + song.id + '" >' + (index3 + 1) + '.' + song.name + '</a>'+
											'</li>'
										);
									});
								}
							}
						})
					})
					
					
					// 4.设置分页样式
					$("#page a").css("cursor", "pointer")
					//5. 移除分页数据
					$("#page a").remove();
					
					// 6.追加分页信息
					if(data.allPage > 0){
						if(pageNum == 1){
							if(data.allPage == 1){
								$("#page").append(
									"<a  class=\"noprev\"><img src=\"../front/singerInfo_files/prev.png\"></a>"+
									"<a class=\"current currPageNo\" >" + pageNum + "</a>"+
									"<a class=\"next\"><img src=\"../front/singerInfo_files/next.png\"></a>"
								);
							}else if(data.allPage == 2){
								$("#page").append(
									"<a  class=\"noprev\"><img src=\"../front/singerInfo_files/prev.png\"></a>"+
									"<a class=\"current currPageNo\" >" + pageNum + "</a>"+
									"<a class=\"currPageNo\" id=\"pageNumAdd\"><input type=\"hidden\" value="+(pageNum+1)+" />" + (pageNum+1) + "</a>"+
									"<a class=\"next\" id=\"nextPage\"><input type=\"hidden\" value="+(pageNum+1)+" /><img src=\"../front/singerInfo_files/next.png\"></a>"
								);
							}else{
								$("#page").append(
									"<a  class=\"noprev\"><img src=\"../front/singerInfo_files/prev.png\"></a>"+
									"<a class=\"current currPageNo\" >" + pageNum + "</a>"+
									"<a class=\"currPageNo\" id=\"pageNumAdd\"><input type=\"hidden\" value="+(pageNum+1)+" />" + (pageNum+1) + "</a>"+
									"<a class=\"currPageNo\" id=\"pageNumAdd2\"><input type=\"hidden\" value="+(pageNum+2)+" />" + (pageNum+2) + "</a>"+
									"<a class=\"next\" id=\"nextPage\"><input type=\"hidden\" value="+(pageNum+1)+" /><img src=\"../front/singerInfo_files/next.png\"></a>"
								);
							}
						}else if(pageNum == data.allPage){
							if(data.allPage == 1){
								$("#page").append(
									"<a  class=\"noprev\"><img src=\"../front/singerInfo_files/prev.png\"></a>"+
									"<a class=\"current currPageNo\" >" + (pageNum) + "</a>"+
									"<a class=\"next\"><img src=\"../front/singerInfo_files/next.png\"></a>"
								);
							}else if(data.allPage == 2){
								$("#page").append(
									"<a  class=\"noprev\" id=\"prevPage\"><input type=\"hidden\" value="+(pageNum-1)+" /><img src=\"../front/singerInfo_files/prev.png\"></a>"+
									"<a class=\"currPageNo\" id=\"pageNumSub2\"><input type=\"hidden\" value="+(pageNum-1)+" />" + (pageNum-1) + "</a>"+
									"<a class=\"current currPageNo\" >" + (pageNum) + "</a>"+
									"<a class=\"next\"><img src=\"../front/singerInfo_files/next.png\"></a>"
								);
							}else{
								$("#page").append(
									"<a  class=\"noprev\" id=\"prevPage\"><input type=\"hidden\" value="+(pageNum-1)+" /><img src=\"../front/singerInfo_files/prev.png\"></a>"+
									"<a class=\"currPageNo\" id=\"pageNumSub2\"><input type=\"hidden\" value="+(pageNum-2)+" />" + (pageNum-2) + "</a>"+
									"<a class=\"currPageNo\" id=\"pageNumSub\"><input type=\"hidden\" value="+(pageNum-1)+" />" + (pageNum-1) + "</a>"+
									"<a class=\"current currPageNo\" >" + (pageNum) + "</a>"+
									"<a class=\"next\"><img src=\"../front/singerInfo_files/next.png\"></a>"
								);
							}
						}else{
							if(data.allPage > 0){
								$("#page").append(
									"<a  class=\"noprev\" id=\"prevPage\"><input type=\"hidden\" value="+(pageNum-1)+" /><img src=\"../front/singerInfo_files/prev.png\"></a>"+
									"<a class=\"currPageNo\" id=\"pageNumSub\"><input type=\"hidden\" value="+(pageNum-1)+" />" + (pageNum-1) + "</a>"+
									"<a class=\"current currPageNo\" >" + (pageNum) + "</a>"+
									"<a class=\"currPageNo\" id=\"pageNumAdd\"><input type=\"hidden\" value="+(pageNum+1)+" />" + (pageNum + 1) + "</a>"+
									"<a class=\"next\" id=\"prevPage\"><input type=\"hidden\" value="+(pageNum+1)+" /><img src=\"../front/singerInfo_files/next.png\"></a>"
								);
							}
						}
					}
					
					// 7.分页按钮绑定事件
					$("#nextPage,#prevPage,#pageNumSub,#pageNumAdd,#pageNumSub2,#pageNumAdd2").on("click",function(){
						// 2.1 获取页码 
						var currentPageNum = $(this).children("input").val();
						if(currentPageNum != undefined){
							// 2.2 重新获取歌手信息
							findSingerInfo(currentPageNum,data.firstName,data.sexNum,data.singerTypeId);
						}
					});
				}
			
			})
		</script>
	</head>

	<body>
		<!-- 头部 开始 -->
		<div id="top" style="height: 55px;">
			<object style="border:0px" type="text/x-scriptlet" data="head.jsp" width="100%" height="100%"></object>
		</div>
		<!-- 头部 结束 -->
		
		<!-- 内容 开始 -->
		<div id="artistContent" class="comW" data-catid="0" data-letter="">
			<div class="leftNav fl">
				<a data-index="0" class="all active singerInfoButton" >全部歌手</a>
				<dl class="area">
					<dt>
						<a class="singerInfoButton">华语歌手</a>
					</dt>
					<dd>
						<a class="singerInfoButton" >华语男歌手</a>
					</dd>
					<dd>
						<a class="singerInfoButton" >华语女歌手</a>
					</dd>
					<dd>
						<a class="singerInfoButton" >华语组合</a>
					</dd>
				</dl>
				<dl class="area">
					<dt>
						<a class="singerInfoButton">日韩歌手</a>
					</dt>
					<dd>
						<a class="singerInfoButton" >日韩男歌手</a>
					</dd>
					<dd>
						<a class="singerInfoButton" >日韩女歌手</a>
					</dd>
					<dd>
						<a class="singerInfoButton" >日韩组合</a>
					</dd>
				</dl>
				<dl class="area" style="border:none;">
					<dt>
						<a class="singerInfoButton">欧美歌手</a>
					</dt>
					<dd>
						<a class="singerInfoButton" >欧美男歌手</a>
					</dd>
					<dd>
						<a class="singerInfoButton" >欧美女歌手</a>
					</dd>
					<dd>
						<a class="singerInfoButton" >欧美组合</a>
					</dd>
				</dl>
			</div>

			<div class="rightContentBox fr" style="height: auto;">
			
				<!-- 字母 查询 start -->
				<div class="topSelect">
					<a  class="select hot fl active" id="remen">热门</a>
					<a  class="select">A</a>
					<a  class="select ">B</a>
					<a  class="select ">C</a>
					<a  class="select ">D</a>
					<a  class="select ">E</a>
					<a  class="select ">F</a>
					<a  class="select ">G</a>
					<a  class="select ">H</a>
					<a  class="select ">I</a>
					<a  class="select ">J</a>
					<a  class="select ">K</a>
					<a  class="select ">L</a>
					<a  class="select ">M</a>
					<a  class="select ">N</a>
					<a  class="select ">O</a>
					<a  class="select ">P</a>
					<a  class="select ">Q</a>
					<a  class="select ">R</a>
					<a  class="select ">S</a>
					<a  class="select ">T</a>
					<a  class="select ">U</a>
					<a  class="select ">V</a>
					<a  class="select ">W</a>
					<a  class="select ">X</a>
					<a  class="select ">Y</a>
					<a  class="select ">Z</a>
					<a  class="select ">#</a>
				</div>
				<!-- 字母 查询 end -->
				
				<!-- 列表 start -->
				<div id="artistList">
					<ul class="artistBox" style="height: auto;">

					</ul>
				</div>
				<!-- 列表 end -->
				
			</div>
		</div>
		<!-- 内容 结束 -->
		<!-- 分页 开始 -->
		<!-- style="background-color: red;cursor: pointer; height: auto; float: none;" -->
		<div style="">
			<div class="page" id="page" data-page="6780" style="margin: 0px auto; height: auto; float: none;width: 175px;"> 
			
			</div>
		</div>
		<!-- 分页 结束 -->
		<!-- 底部开始 -->
		<div id="bottom" style="height: 270px;">
			<object style="border:0px" type="text/x-scriptlet" data="bottom.jsp" width="100%" height="100%"></object>
		</div>
		<!-- 底部结束 -->
	</body>

</html>
