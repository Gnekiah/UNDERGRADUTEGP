<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <!-- head begin -->
    <title>Login</title>
    <link rel="icon" href="img/tabicon.png">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/login.css">
    
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/login.js"></script>

</head>
<body background="img/background.png">
	
	<div class="container">
        <form class="form-signin" action="/subsys_interactive/login" method="post">
            <h2 class="form-signin-heading">用户登录</h2>
            <label for="username" class="sr-only">用户名</label>
            <input type="text" name="username" id="username" class="form-control" placeholder="请输入账号" required autofocus>
            <label for="password" class="sr-only">密码</label>
            <input type="password" name="password" id="password" class="form-control" placeholder="请输入密码" required>
            <div class="checkbox">
                <label><input type="checkbox" id="remember" value="remember-me">记住密码</label>
            </div>
            <button class="btn btn-lg btn-primary btn-block" id="submit" type="submit">登录</button>
        </form>
    </div> 
	
	
</body>
</html>