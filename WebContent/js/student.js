$(document).ready(function() {
	$("#success").hide();
	$("#failed").hide();
	
	// load info while open the page
	$.post("/subsys_interactive/student",
		{
			getclass: "true"
		},
		function(result) {
			$("#userid").text(result.id);
			$("#name").val(result.name);
			$("#email").val(result.email);
			$("#resume").text(result.resume);
			$.each(result.classes, function(i, item) {
				var link = "/subsys_interactive/classview?classid=" + item.id;
				var cunit = "<div class=\"class-div col-md-3\" onclick=\"window.open('" + link +"')\">";
                cunit += "<h4 id=\"classid\">" + item.id + "</h4>";
                cunit += "<h3 id=\"classname\">" + item.name + "</h3>";
                cunit += "<p id=\"classresume\">" + item.resume + "</p></div>";
				$("#addclass").before(cunit);
			});
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
			$.post("/subsys_interactive/student", 
			{
				name: $("#name").val(),
				email: $("#email").val(),
				resume: $("#resume").val()
			},
			function(result) {
				if (result == "SUCCESS") {
					alert("SUCCESS!");
				}
				else {
					alert("FAILED!");
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
		$.post("/subsys_interactive/student",
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