<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <!-- head begin -->
    <title>修改作业</title>
    <link rel="icon" href="img/tabicon.png">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/work.css">
    
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/work.js"></script>

</head>

<body background="img/background.png">
    <div id="main">
        <!-- work area -->
        <div id="work-area" class="col-md-6 col-md-offset-2">
            <input id="title" class="form-control input-lg" placeholder="作业标题" name="">
            <textarea id="content" class="form-control input-lg" rows="10" placeholder="作业内容"></textarea>
            <textarea id="referto" class="form-control input-lg" rows="10" placeholder="参考代码"></textarea>
            <input id="keylines" class="form-control input-lg" placeholder="重要代码段, e.g: (5,20);(25,25);(25,30)">
            <input id="time" class="form-control input-lg" placeholder="作业关闭时间, e.g: 2017-05-01 12:00:00">
            <!-- test case area -->
            <div>
                <div class="col-md-4 testcase-area">
                    <textarea id="testcasein" class="form-control input-lg" rows="5" placeholder="测试用例输入"></textarea>
                </div>
                <div class="col-md-4 testcase-area">
                    <textarea id="testcaseout" class="form-control input-lg" rows="5" placeholder="测试用例输出"></textarea>
                </div>
            </div>
                <!-- other limit -->
            <div class="col-md-4 testcase-area">
                <!-- limit cpu -->
                <div class="input-group limit-area">
                    <span class="input-group-addon">@</span>
                    <input id="limcpu" type="text" class="form-control" placeholder="CPU时间限制">                    
                </div>
                <!-- limit memory -->
                <div class="input-group limit-area">
                    <span class="input-group-addon">@</span>
                    <input id="limmem" type="text" class="form-control" placeholder="Mem限制">  
                </div>
                <!-- code type -->
                <div class="input-group limit-area">
                    <span class="input-group-addon">@</span>
                    <input id="codetype" type="text" class="form-control" placeholder="代码类型"> 
                </div>
            </div>
        </div>
        <!-- control area -->
        <div id="ctl-area" class="col-md-2 col-md-offset-0">
            <div class="panel panel-info">
                <div class="panel-heading"><h3 class="panel-title"></h3></div>
                <button type="button" id="submit-button" class="btn btn-info ctl-button">提交修改</button>
                <button type="button" id="dotest-button" class="btn btn-info ctl-button" onclick="window.open('/subsys_interactive/dotest.jsp')">执行测试</button>
                <button type="button" id="delete-button" class="btn btn-danger ctl-button" onclick="window.open('/subsys_interactive/work?deletereq=true')">删除作业</button>
                <button type="button" id="return-button" class="btn btn-success ctl-button" onclick="javascrtpt:window.location.href='/subsys_interactive/class.jsp'">返回班级</button>
                <form method="post" action="/subsys_interactive/work?downloadreq=true" enctype="multipart/form-data">
                    <button type="submit" id="download-button" class="btn btn-info ctl-button">下载代码</button>
                </form>
                
            </div>
        </div>
    </div>
</body>
</html>