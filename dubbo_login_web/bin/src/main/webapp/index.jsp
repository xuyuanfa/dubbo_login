<%@ page contentType="text/html; charset=UTF-8"%>
<html>


<body>

<h2>Hello World!</h2>
<br>
<form name="from1" action="login/login.do" method="post">
用户名：<input type="text" name="username" id="username" value="username">
密码：<input type="text" name="password" id="password" value="password">
<input type="submit" name="submit" value="登录"/>
<!-- <input type="button"  value="登录" onclick="login()"/>
<script type="text/javascript">
function login(){
	$.ajax( {    
	    url:'/login/login',// 跳转到 action    
	    data:{    
	    	username : $("#username").val(),    
	    	password : $("#password").val()    
	    },    
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {    
	        alert(data);
	     },    
	     error : function() {    
	          // view("异常！");    
	          alert("异常！");    
	     }    
	});  
}
</script> -->
</form>


</body>
</html>
