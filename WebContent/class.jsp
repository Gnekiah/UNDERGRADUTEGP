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
    <link rel="stylesheet" href="css/class.css">
    
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/class.js"></script>

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
                    <button type="button" class="btn btn-success change" onclick="javascrtpt:window.location.href='/subsys_interactive/teacher.jsp'">返回</button>
                    <button type="button" id="change" class="btn btn-info change" name="change">修改信息</button>
                    <button type="button" id="logout" class="btn btn-warning change">注销</button>
                </div>
                <div>
                    <button type="button" id="delete-class" class="btn btn-danger change" onclick="javascrtpt:window.location.href='/subsys_interactive/class?deleteclass=true'">删除班级</button>
                </div>
            </div>
        </div>
        <!-- works list --> 
        <div class="col-md-5 col-md-offset-1" id="work-div">
            <h2 class="sub-header" align="center">作业列表</h2>
            <table class="table table-hover" id="work-table">
                <thead><tr><th width="100">ID</th><th>标题</th></tr></thead>
                <tbody id="work-tbody" align="left">
                    <tr id="add-work-item"><td><button type="button" class="btn btn-primary" onclick="window.open('/subsys_interactive/work?addwork=true')">创建作业</button></td></tr>
                </tbody>
            </table>
        </div>
        <!-- student list -->
        <div class="col-md-3 col-md-offset-0" id="student-div">
            <h2 class="sub-header" align="center">学生列表</h2>
            <table class="table table-striped" id="student-table">
                <thead><tr><th width="100">ID</th><th>姓名</th><th width="50"></th></tr></thead>
                <tbody id="student-tbody" align="left">
                    <tr id="add-student-item"><td><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addstudentmodal">添加学生</button></td></tr>  
                </tbody>
            </table>
        </div>
        <!-- add student -->
        <div class="modal fade container" id="addstudentmodal" tabindex="-1" role="dialog" aria-labelledby="modallabel1" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="modallabel1">添加学生</h4>
                    </div>
                    <div class="modal-body">
                        <!-- main add teacher area -->
                        <form class="form-signin">
                            <textarea id="studentarea" class="form-control" rows="7" placeholder="请输入学生的ID，学生密码默认为学生的学号。例如：&#13;&#10;20130101&#13;&#10;20130102&#13;&#10;20130103" required autofocus></textarea>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" data-dismiss="modal" id="doaddstudent">添加</button>
                    </div>
                </div>
            </div>
        </div><!-- end of modal -->
    </div>
</body>
</html>