<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>删除教师账户</title>
    <link rel="icon" href="img/tabicon.png">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/admindelteacher.css">
    
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/admindelteacher.js"></script>

</head>
<body background="img/background.png">

    <div class="col-md-6 col-md-offset-3" id="main">
        <h2 class="sub-header" align="center">删除教师账户</h2>
        <p id="result" align="center"></p>
         <table class="table table-striped" id="table">
            <thead><tr><th>#</th><th>账号</th><th>姓名</th>
                <th width="100"><button type="button" id="delall" class="btn btn-danger">删除全部</button></th>
            </tr></thead>
            <tbody id="tbody"></tbody>
         </table>
    </div>


</body>
</html>