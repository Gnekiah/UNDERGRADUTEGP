<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <!-- head begin -->
    <title>执行测试</title>
    <link rel="icon" href="img/tabicon.png">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/dotest.css">
    
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/dotest.js"></script>

</head>

<body background="img/background.png">
    <!-- control area -->
    <div id="ctl-area" class="col-md-6 col-md-offset-3" align="center">
        <button type="button" id="dotest" class="btn btn-info ctl-button">执行测试</button>
        <button type="button" class="btn btn-success ctl-button" onclick="javascrtpt:window.location.href='/subsys_interactive/work.jsp'">返回作业</button>
        <button type="button" class="btn btn-warning ctl-button" onclick="javascrtpt:window.location.href='/subsys_interactive/dotest.jsp'">刷新页面</button>
    </div><!-- end of control area -->
    
    <div id="main" class="col-md-12 col-md-offset-0">
        <!-- report area -->
        <div class="col-md-5 col-md-offset-2" id="rpt-div">
            <h2 class="sub-header" align="center">测试结果</h2>
            <table class="table table-hover" id="rpt-table">
                <thead><tr><th width="100">ID</th><th width="100">动态评分</th><th width="100">静态评分</th><th>提交时间</th></tr></thead>
                <tbody id="rpt-tbody" align="left">
                    <tr id="last-item"></tr>
                </tbody>
            </table>
        </div><!-- end of report area -->
        <!-- no report area -->
        <div class="col-md-2 col-md-offset-1" id="unrpt-div">
            <h2 class="sub-header" align="center">未提交</h2>
            <table class="table table-striped" id="unrpt-table">
                <thead><tr><th>ID</th></tr></thead>
                <tbody id="unrpt-tbody" align="left">
                    <tr id="unlast-item"></tr>
                </tbody>
            </table>
        </div><!-- end of no report area -->
    </div>
</body>
</html>