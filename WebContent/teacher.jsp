<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <!-- head begin -->
    <title>代码自动评测系统</title>
    <link rel="icon" href="img/tabicon.png">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/teacher.css">
    
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/teacher.js"></script>

</head>

<body background="img/background.png">
    <h1 align="center">代码自动评测系统</h1>
    <div id="main">
        <!-- user info area -->
        <div class="container col-md-3" id="container-info">
            <div class="panel panel-info">
                <div class="panel-heading"><h3 class="panel-title" id="userid"></h3></div>
                <div class="panel-body form-horizonal" align="left">
                    <h4><span class="label label-default">姓名</span></h4>
                    <input class="form-control info-change" type="text" readonly="readonly" id="name">
                    <h4><span class="label label-default">邮箱</span></h4>
                    <input class="form-control info-change" type="text" readonly="readonly" id="email">
                    <h4><span class="label label-default">简介</span></h4>
                    <textarea class="form-control info-change" rows="9" readonly="readonly" id="resume"></textarea>
                </div>
                <div class="alert alert-danger" id="failed">
                    <a href="#" class="close" data-dismiss="alert">&times;</a>
                    <strong>修改失败！</strong>
                </div>
                <div class="alert alert-success" id="success">
                    <a href="#" class="close" data-dismiss="alert">&times;</a>操作成功！    
                </div>
                <div>
                    <button type="button" class="btn btn-info change" data-toggle="modal" data-target="#chpasswd">修改密码</button>
                    <button type="button" id="change" class="btn btn-info change" name="change">修改信息</button>
                    <button type="button" id="logout" class="btn btn-warning change">注销</button>
                </div>
                <!-- root panel -->
                <div id="root-div" style="display: none">
                    <button type="button" class="btn btn-danger change" data-toggle="modal" data-target="#addteachermodal">创建教师</button>
                    <button type="button" id="delteacher" class="btn btn-danger change" onclick="window.open('/subsys_interactive/admindelteacher.jsp')">删除教师</button>
                    <button type="button" id="delstudent" class="btn btn-danger change" onclick="window.open('/subsys_interactive/admindelstudent.jsp')">删除学生</button>
                    <!-- add tercher -->
                    <div class="modal fade container" id="addteachermodal" tabindex="-1" role="dialog" aria-labelledby="modallabel3" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                    <h4 class="modal-title" id="modallabel3">注册教师账户</h4>
                                </div>
                                <div class="modal-body">
                                    <!-- main add teacher area -->
                                    <form class="form-signin">
                                        <input type="text" id="regname" class="form-control" placeholder="请输入账号" required autofocus>
                                        <input type="password" id="regpassword" class="form-control" placeholder="请输入密码" required>
                                        <input type="password" id="regconfirm" class="form-control" placeholder="请确认密码" required>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                    <button type="button" class="btn btn-primary" data-dismiss="modal" id="regbutton">确认注册</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- change password -->
        <div class="modal fade container" id="chpasswd" tabindex="-1" role="dialog" aria-labelledby="modallabel1" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="modallabel1">修改密码</h4>
                    </div>
                    <div class="modal-body">
                        <!-- main paswd change area -->
                        <form class="form-signin">
                            <input type="password" name="password" id="password" class="form-control" placeholder="请输入密码" required>
                            <input type="password" name="confirm" id="confirm" class="form-control" placeholder="请确认密码" required>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" data-dismiss="modal" id="dochange">提交修改</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- class list area -->
        <div id="classlist" class="col-md-8 col-md-offset-1">           
            <div class="class-div col-md-3" id="addclass" data-toggle="modal" data-target="#addclassmodal">
                <span class="glyphicon glyphicon-plus"></span>
            </div>
        </div>
        <!-- add a class modal -->
        <div class="modal fade container" id="addclassmodal" tabindex="-1" role="dialog" aria-labelledby="modallabel2" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="modallabel2">添加班级</h4>
                    </div>
                    <div class="modal-body">
                        <!-- main add class area -->
                        <form class="form-signin">
                            <input type="text" id="inputname" class="form-control" placeholder="班级名称" required>
                            <input type="text" id="inputresume" class="form-control" placeholder="班级简介" required>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" data-dismiss="modal" id="doadd">提交</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>