$(document).ready(function() {
	// 检索数据
	$("#search").click(function() {
		var cmd = $("#sector").val();
		if (cmd.length == 0) {
			$("#result").html("请输入关键字！<br/>1. 显示全部: *<br/>2. 输入学号: 20170501<br/>3. 输入区间: 20170101-20170510");
			return;
		}
		var getcmd1 = "";
		var getcmd2 = "";
		var getcmd3 = "";
		if (cmd == "*") {
			getcmd1 = "true";
		}
		else if ($.isNumeric(cmd)) {
			getcmd2 = cmd;
		}
		else {
			var tmps = cmd.split("-");
			if (tmps.length == 2 && $.isNumeric(tmps[0] && $.isNumeric(tmps[1]))) {
				getcmd3 = cmd;
			}
			else {
				$("#result").html("请输入正确的关键字！<br/>1. 显示全部: *<br/>2. 输入学号: 20170501<br/>3. 输入区间: 20170101-20170510");
				return;
			}
		}
		$.post("/subsys_interactive/admindelstudent", 
			{
				getlist: getcmd1,
				getone: getcmd2,
				getsect: getcmd3
			},
			function(data) {
				var table = "";
				var arrids = data.split("|");
				for (var i=0; i < arrids.length; i++) {
					var arrid = arrids[i].split("^");
					table += "<tr id=\"" + (i+1) + "\" name=\"" + arrid[0] + "\">";
					table += "<td>" + (i+1) + "</td><td>" + arrid[0] + "</td><td>" + arrid[1] + "</td>";
					table += "<td><button type=\"button\" class=\"btn btn-default btn-sm\" name=\"" + (i+1) +"\"><span class=\"glyphicon glyphicon-trash\"></span></button></td></tr>";				
				}
				$("#tbody").html(table);
				
				// 表格中的button事件
				$("button").click(function() {
					// 排除search和delall按钮的作用
					if (!$.isNumeric($(this).attr("name")))
						return;
					// 删除某个用户
					var nr = $(this).attr("name");
					var userid = $("#"+nr).attr("name");
					if (!confirm("删除该账号？")) {return;}
					$.post("/subsys_interactive/admindelstudent", 
						{
							delone: userid
						},
						function(result) {
							if (result == "SUCCESS") {
								$("#"+nr).remove();
							}
							$("#result").html(result);
						});
				});
			});
		
	});
	
	// 删除表格显示的全部数据
	$("#delall").click(function() {
		if (!confirm("删除表格中的全部账号？")) {return;}
		var students = "";
		var objs = $("#tbody").find("button");
		if (objs.length == 0) {
			$("#result").html("无数据！");
			return;
		}
		for (var i=0; i < objs.length; i++) {
			students += $("#"+objs[i].getAttribute("name")).attr("name") + "|";
		}
		students = students.substring(0, students.length-1);
		$.post("/subsys_interactive/admindelstudent", 
		{
			dellist: students
		},
		function(result) {
			$("#tbody").remove();
			$("#result").html("学生账号删除成功！");
		});
	});
});