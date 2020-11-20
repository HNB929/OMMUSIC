<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="zh">

	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>登录注册</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/front/css/styles.css">
		<!--引入css样式文件-->
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/front/dist/css/bootstrap.css" />
		<!--引入jquery-->
		<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
		<script src="https://cdn.bootcss.com/jquery.form/4.2.1/jquery.form.min.js"></script>
		<!--bootstrap-->
		<script src="${pageContext.request.contextPath}/front/dist/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
		<script>
			$(function(){
				var status = getQueryString("status"); //获取请求参数里面的songId
				//获取请求参数里面的东西
				function getQueryString(str) {
					var result = window.location.search.match(new RegExp("[\?\&]" + str
							+ "=([^\&]+)", "i"));
					if (result == null || result.length < 1) {
						return "";
					}
					return result[1];
				}
				
				// 1. 注册按钮绑定事件
				$("#confirmRegister").on("click",function(){
					var username = $("#registerUserName").val();
					var password = $("#registerPassword").val();
					var confirmPassword = $("#registerConfirmPassword").val();
					var tel = $("#registerMobile").val();
					$.ajax({
						url: "http://localhost:8080/OMmusic/user/register",
						contentType: "application/json;charset=utf-8",
						data: {
							username: username,
							password: password,
							confirmPassword: confirmPassword,
							tel: tel
						},
						dataType: "json",
						type: "GET",
						success: function(data) {
							console.log(data);
							if(data.success == false){
								alert(data.msg);
							}else{
								alert(data.msg);
								history.go(0);
							}
						}
					});	
				})
				
				// 2.登录按钮绑定事件
				$("#confirmLogin").on("click",function(){
					var tel = $("#loginMobile").val();
					var password = $("#loginPassword").val();
					$.ajax({
						url: "http://localhost:8080/OMmusic/user/login",
						contentType: "application/json;charset=utf-8",
						data: {
							tel: tel,
							password: password
						},
						dataType: "json",
						type: "GET",
						success: function(data) {
							console.log(data);
							if(data.success == false){
								alert(data.msg);
							}else{
								alert(data.msg);
								if(data.data.role == 1){
									window.location.href = "http://localhost:8080/OMmusic/front/index.jsp";
								}else if(data.data.role == 0){
									window.location.href = "http://localhost:8080/OMmusic/manager/index-song.html";
								}else{
									alert("用户角色错误");
								}
								
							}
						}
					});	
				})
				
				// 3.验证码登录,绑定事件
				$("#yanzheng span").css("cursor","pointer");
				$("#yanzheng").on("click",function(){
					$("#myModal").modal('show');
					// 3.1获取验证码，绑定事件
					$(".btn-primary").on("click",function(){
						getMessage();
					});
				});
				

				
				// 4.验证码登录按钮绑定事件
				$("#confirmCodeLogin").on("click",function(){
					console.log("点击验证码登录按钮")
					
					$("#codeLoginForm").ajaxSubmit(function(data){
						console.log(data);
						if(data.success == false){
							alert(data.msg);
						}else{
							if(data.data.role == 1){
								window.location.href = "http://localhost:8080/OMmusic/front/index.jsp";
							}else if(data.data.role == 0){
								window.location.href = "http://localhost:8080/OMmusic/manager/index-song.html";
							}else{
								alert("用户角色错误");
							}
						}
					});
				});
			});
			
			/**
			 * 获取验证码
			 */
			function getMessage(){
				var phone = $("#telphone").val();
				if($(".btn-primary").text()=="获取验证码" || $(".btn-primary").text()=="重新获取"){
					$.ajax({
						url:"../getCode?phone="+phone,
						contentType:"application/json;charset=utf-8",
						type:"post",
						dataType:"json",
						success:function(data){
							console.log(data)
							if (data.success == false) {
								alert(data.msg);
							}else{
								start11();
							}
						}
					});
				}
			}
			//倒计时
			var d = 60;
			var n;
			function daojishi(){
				$(".btn-primary").attr("disabled",true);
				var j = d+"s后可重新发送"
				//清除计时
				//倒计时
				d--;
				//把文本赋值进去
				$(".btn-primary").html(j)
				//一秒刷新
				if(d == 0){
					$(".btn-primary").html("重新获取");
					$(".btn-primary").attr("disabled",false);
					d = 60;
					clearInterval(n);
					return;
				} 
			}
			function start11(){//每秒调用函数
			 	n = setInterval("daojishi()",1000);
			}
			
		</script>

	</head>

	<body>
		<!-- 头部 开始 -->
		<div id="top" style="height: 55px;">
			<object 
				style="border:0px" 
				type="text/x-scriptlet" 
				data="http://localhost:8080/OMmusic/front/head.jsp" 
				width="100%" 
				height="100%">
			</object>
			
		</div>
		<!-- 头部 结束 -->

		<!-- 内容 开始 -->
		<div style="padding: 10px;">
			<div class="login-wrap">
				<div class="login-html">
					<input id="tab-1" type="radio" name="tab" class="sign-in" checked>
					<label for="tab-1" class="tab">登录</label>
					<input id="tab-2" type="radio" name="tab" class="sign-up">
					<label for="tab-2" class="tab">注册</label>
					<div class="login-form">
						<!-- 登录 开始 -->
						<div class="sign-in-htm">
							<form>
								<div class="group" style="margin-top: 20px;">
									<span style="color:#ccc">手机号</span>
									<input required id="loginMobile" type="text" class="input" maxlength="11" value=""/>
								</div>
								<div class="group" style="margin-top: 50px;">
									<span style="color:#ccc">密码</span>
									<input required id="loginPassword" type="password" class="input" value=""/>
								</div>
								<div class="group" style="margin-top: 50px;">
									<button id="confirmLogin" type="button" class="button">登录</button>
								</div>
							</form>
							<div class="hr"></div>
							<div class="foot-lnk" id="yanzheng">
								<span style="color:#ccc;">验证码登录</span>
							</div>
						</div>
						<!-- 登录 结束 -->

						<!-- 注册 开始 -->
						<form>
							<div class="sign-up-htm">
								<div class="group">
									<span style="color:#ccc">手机号</span>
									<input required id="registerMobile" type="text" class="input" maxlength="11" value=""/>
								</div>
								<div class="group">
									<span style="color:#ccc">用户名</span>
									<input required id="registerUserName" type="text" class="input" maxlength="6" value=""/>
								</div>
								<div class="group">
									<span style="color:#ccc">密码</span>
									<input required id="registerPassword" type="password" class="input" value=""/>
								</div>
								<div class="group">
									<span style="color:#ccc">确认密码</span>
									<input required id="registerConfirmPassword" type="password" class="input" value=""/>
								</div>
								<div class="group">
									<button id="confirmRegister" type="button" class="button">注册</button>
								</div>
								<div class="hr"></div>
								<div class="foot-lnk">
									<label for="tab-1"><span style="color:#ccc">存在账号？</span></a>
								</div>
							</div>
						</form>
						<!-- 注册 结束 -->
					</div>
				</div>
			</div>
		</div>
		<!-- 内容 结束 -->

		<!-- 底部开始 -->
		<div id="bottom" style="height: 270px;">
			<object style="border:0px" type="text/x-scriptlet" data="${pageContext.request.contextPath}/front/bottom.jsp" width="100%"
			 height="100%"></object>
		</div>
		<!-- 底部结束 -->

		<!-- 遮罩开始 -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width:450px;height:350px">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">验证登录：</h4>
					</div>
					<div class="modal-body" style="background-color:white;">
						<!--这里面是一个表单-->
						<div id="wrap" class="bg-info">
							<form id="codeLoginForm" class="form-horizontal" role="form" action="../codeLogin" method="post" style="background-color: white;">
								<div class="form-group">
									<label class="col-sm-2"></label>
									<div class="col-sm-8">
										<input type="text" class="form-control" id="telphone" name="phonenumber">
										<label style="float:right;"></label>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-2"></div>
									<div class="col-sm-5">
										<input required type="text" class="form-control" id="lastname" name="userpwd" placeholder="请输入验证码" >
									</div>
									<div class="col-sm-3">
										<button type="button" class="btn btn-primary">获取验证码</button>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-2">
									</div>
									<div class="col-sm-8">
										<button type="button" class="btn btn-success col-sm-12" id="confirmCodeLogin">登录</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
		
		</div>
		<!-- 遮罩结束 -->


	</body>
</html>
