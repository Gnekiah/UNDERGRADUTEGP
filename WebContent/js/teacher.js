$(document).ready(function() {
	$("#success").hide();
	$("#failed").hide();
	
	// load info while open the page
	$.post("/subsys_interactive/teacher",
		{
			getclass: "true"
		},
		function(result) {
			$("#userid").text(result.id);
			$("#name").val(result.name);
			$("#email").val(result.email);
			$("#resume").text(result.resume);
			$.each(result.classes, function(i, item) {
				var link = "/subsys_interactive/class?classid=" + item.id;
				var cunit = "<div class=\"class-div col-md-3\" onclick=\"window.open('" + link +"')\">";
                cunit += "<h4 id=\"classid\">" + item.id + "</h4>";
                cunit += "<h3 id=\"classname\">" + item.name + "</h3>";
                cunit += "<p id=\"classresume\">" + item.resume + "</p></div>";
				$("#addclass").before(cunit);
			});
		// if current user is root, show root panel
		if (result.id == "root") {
			$("#root-div").show();
		}
		});
	
	// change user info
	$("#change").click(function() {
		if ($(this).attr("name") == "change") {
			$(".info-change").removeAttr("readonly");
			$(this).css("background", "red");
			$(this).html("确认修改");
			$(this).attr("name", "submit");
			return;
		}
		else {
			$.post("/subsys_interactive/teacher", 
			{
				name: $("#name").val(),
				email: $("#email").val(),
				resume: $("#resume").val()
			},
			function(result) {
				if (result == "SUCCESS") {
					$("#success").show();
				}
				else {
					$("failed").show();
				}
				$(".info-change").attr("readonly", "readonly");
				$("#change").css("background-color", "#5bc0de");
				$("#change").text("修改信息");
				$("#change").attr("name", "change");
				return;
			});
		}
	});
	// change password
	$("#dochange").click(function() {
		var password = $("#password").val();
		var confirm = $("#confirm").val();
		if (password != confirm) {
			alert("请输入相同的密码！");
			return;
		}
		$.post("/subsys_interactive/teacher",
			{
				password: $("#confirm").val()
			},
			function(result) {
				$("#password").val("");
				$("#confirm").val("");
				$("#password").attr("placeholder", "请输入密码");
				$("#confirm").attr("placeholder", "请确认密码");
				if (result == "SUCCESS") {
					alert("修改成功！");
					return;
				}
				else {
					alert(result);
					return;
				}
			});
	});
	// add a class
	$("#doadd").click(function() {
		var classname = $("#inputname").val();
		var classresume = $("#inputresume").val();
		if (classname == "" || classresume == "") {
			alert("请输入班级名称和班级简介！");
			return;
		}
		$.post("/subsys_interactive/teacher",
			{
				classname: $("#inputname").val(),
				classresume: $("#inputresume").val()
			},
			function(result) {
				$("#inputname").val("");
				$("#inputresume").val("");
				$("#inputname").attr("placeholder", "班级名称");
				$("#inputresume").attr("placeholder", "班级简介");
				if (result.substring(0,7) == "SUCCESS") {
					alert("添加成功！");
					var link = "/subsys_interactive/class?classid=" + result.substring(7);
					var cunit = "<div class=\"class-div col-md-3\" onclick=\"document.location='" + link +"'\">";
	                cunit += "<h4 id=\"classid\">" + result.substring(7) + "</h4>";
	                cunit += "<h3 id=\"classname\">" + classname + "</h3>";
	                cunit += "<p id=\"classresume\">" + classresume + "</p></div>";
					$("#addclass").before(cunit);
					return;
				}
				else {
					alert(result);
					return;
				}
			});
	});
	
	// register a teacher account
	$("#regbutton").click(function() {
		if ($("#regname").val().length == 0) {
			alert("请输入账号");
            return;
		}
		if ($("#regpassword").val() != $("#regconfirm").val()) {
			alert("请输入相同的密码");
			return;
		}
		if ($("#regpassword").val().length < 6) {
			alert("密码必须为6位以上");
            return;
		}
		$.post("/subsys_interactive/adminaddteacher", 
		{
			username: $("#regname").val(),
			password: $("#regpassword").val()
		},
		function(result) {
			$("#regname").val("");
			$("#regpassword").val("");
			$("#regconfirm").val("");
			$("#regname").attr("placeholder", "请输入账号");
			$("#regpassword").attr("placeholder", "请输入密码");
			$("#regconfirm").attr("placeholder", "请确认密码");
			alert(result);
		});
	});
	
	// logout
	$("#logout").click(function() {
		$.post("/subsys_interactive/logout", {
			user: $("#userid").text()
		},
		function(result) {
			window.location.href="/subsys_interactive/login.jsp";
		});
	});
	
});