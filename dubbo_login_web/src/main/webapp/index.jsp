<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.js"></script>
</head>

<body>

	<h2>Hello World!</h2>
	<br>
	<form name="from1" action="login/login.do" method="post">
		用户名：<input type="text" name="username" id="username" value="kingdee">
		密码：<input type="text" name="password" id="password" value="123456">
		<br>
		<!-- <input type="submit" name="submit" value="登录" />  -->
		<input type="button" value="登录" onclick="login()" />
		<input type="button" value="退出登录" onclick="loginOut()" />
		<input type="button" value="登录状态" onclick="verify()" />
		
		<script type="text/javascript">
			//var localhost = "http://localhost:8080/";
			var hosts = new Array();
			hosts[0] = "http://1.com:18880/";
			hosts[1] = "http://2.com:18880/";
			function login() {
				$.ajax({
					url : 'login/login.do',// 跳转到 action    
					data : {
						username : $("#username").val(),
						password : $("#password").val()
					},
					type : 'post',
					cache : false,
					//dataType : 'json',
					success : function(data) {
						alert(data);
						var host = document.domain;
						for(var i=0;i<hosts.length;i++){
							if(hosts[i].indexOf(host) > 0){
								continue;
							}
							loginByjsonp(hosts[i]);
						}
					},
					error : function() {
						alert("异常！");
					}
				});
			}
			function verify() {
				$.ajax({
					url : 'login/verify.do',// 跳转到 action    
					data : {
						username : $("#username").val(),
					},
					type : 'get',
					cache : false,
					//dataType : 'json',
					success : function(data) {
						alert(data);
					},
					error : function() {
						alert("异常！");
					}
				});
			}
			function loginOut() {
				$.ajax({
					url : 'login/loginOut.do',// 跳转到 action    
					data : {
						username : $("#username").val(),
					},
					type : 'get',
					cache : false,
					//dataType : 'json',
					success : function(data) {
						alert(data);
						var host = document.domain;
						for(var i=0;i<hosts.length;i++){
							if(hosts[i].indexOf(host) > 0){
								continue;
							}
							loginOutByjsonp(hosts[i]);
						}
					},
					error : function() {
						alert("异常！");
					}
				});
			}
			
			//登录
			function loginByjsonp(url) {
				alert("loginByJsonp");
		        $.ajax({
		            type: "GET",
		            cache: false,
		            url: url+"dubbo_login_web/login/loginOther.do",
		            data: {
		            	username : $("#username").val(),
						password : $("#password").val()
						},
		            dataType: "jsonp",
		            //jsonp: "callback",
		            jsonpCallback: "loginSuccessByJsonp"
		        });
			}
			function loginSuccessByJsonp(data) {
			    //处理data
			    alert(data);
			}
			
			//登出
			function loginOutByjsonp(url) {
				alert("loginOutByjsonp");
		        $.ajax({
		            type: "GET",
		            cache: false,
		            url: url+"dubbo_login_web/login/loginOutOther.do",
		            data: {
		            	username : $("#username").val()
						},
		            dataType: "jsonp",
		            //jsonp: "callback",
		            jsonpCallback: "loginSuccessByJsonp"
		        });
			}
		</script>
	</form>
<!-- 
<script src='localhost:8080/login/login.do?username=kingdee&password=123456'></script>
<script src='127.0.0.1:8080/login/login.do?username=kingdee&password=123456'></script> -->
</body>
</html>
