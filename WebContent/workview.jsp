<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <!-- head begin -->
    <title>查看作业</title>
    <link rel="icon" href="img/tabicon.png">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/workview.css">
    
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/workview.js"></script>

</head>

<body background="img/background.png">
    <div id="main">
        <!-- work area -->
        <div id="work-area" class="col-md-6 col-md-offset-2">
            <input id="title" class="form-control input-lg" readonly="readonly" name="">
            <textarea id="content" class="form-control input-lg" rows="11" readonly="readonly"></textarea>
        </div>
        <!-- control area -->
        <div id="ctl-area" class="col-md-2 col-md-offset-0">
            <div class="panel panel-info">
                <div class="panel-heading"><h3 class="panel-title"></h3></div>
                <div align="left" id="limit">
                    <h4><span class="label label-default">类型</span></h4>
                    <input id="codetype" type="text" class="form-control" readonly="readonly"> 
                    <h4><span class="label label-default">时限</span></h4>
                    <input id="time" type="text" class="form-control" readonly="readonly"> 
                </div>
            
                <button type="button" id="return-button" class="btn btn-success ctl-button" onclick="javascrtpt:window.location.href='/subsys_interactive/classview.jsp'">返回班级</button>                                
                <form method="post" action="/subsys_interactive/classview?downloadreq=true" enctype="multipart/form-data">
                    <button type="submit" id="download-button" class="btn btn-success ctl-button">下载离线接口</button>
                </form>
                <form method="post" class="form-signin" action="/subsys_interactive/upload" enctype="multipart/form-data">
                    <a class="file-input">选择文件<input type="file"></a>
                    <button type="submit" class="btn btn-warning upload-button" data-dismiss="modal">上传代码</button>
                </form>
                <form method="post" class="form-signin" action="/subsys_interactive/uploadrpt" enctype="multipart/form-data">
                    <a class="file-input">选择文件<input type="file"></a>
                    <button type="submit" class="btn btn-warning upload-button" data-dismiss="modal">上传报告</button>
                </form>
            </div>
        </div>       
    </div>
</body>
</html>