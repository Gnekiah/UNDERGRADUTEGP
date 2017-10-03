<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <!-- head begin -->
    <title></title>
    <link rel="icon" href="img/tabicon.png">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/classview.css">
    
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/classview.js"></script>

</head>

<body background="img/background.png">
    <div id="main">
        <!-- user info area -->
        <div class="container col-md-3" id="container-info">
            <div class="panel panel-info">
                <div class="panel-heading"><h3 class="panel-title" id="classid"></h3></div>
                <div class="panel-body form-horizonal" align="left">
                    <h4><span class="label label-default">班级名称</span></h4>
                    <input class="form-control info-change" type="text" readonly="readonly" id="name">
                    <h4><span class="label label-default">班级简介</span></h4>
                    <textarea class="form-control info-change" rows="7" readonly="readonly" id="resume"></textarea>
                </div>
                <div>
                    <button type="button" class="btn btn-success change" onclick="javascrtpt:window.location.href='/subsys_interactive/student.jsp'">返回</button>
                    <button type="button" id="logout" class="btn btn-warning change">注销</button>
                </div>
            </div>
        </div>
        <!-- works list --> 
        <div class="col-md-7 col-md-offset-1" id="work-div">
            <h2 class="sub-header" align="center" id="classname"></h2>
            <table class="table table-hover" id="work-table">
                <thead><tr><th width="100">ID</th><th>标题</th><th width="180">截止时间</th></tr></thead>
                <tbody id="work-tbody" align="left">
                <tr id="add-work-item"></tr>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>