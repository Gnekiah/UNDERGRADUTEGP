$(document).ready(function() {
	$("#success").hide();
	$("#failed").hide();
	
	// load info while open the page
	$.post("/subsys_interactive/class",
		{
			classreq: "true"
		},
		function(result) {
			$("title").html(result.name);
			$("#classid").text(result.id);
			$("#name").val(result.name);
			$("#resume").text(result.resume);

			// show works
			$.each(result.works, function(i, item) {
				var link = "/subsys_interactive/work?workid=" + item.id;
				var wunit = "<tr class=\"work-item\" onclick=\"window.open('" + link +"')\">";
				wunit += "<td>" + item.id + "</td><td>" + item.title + "</td></tr>";
				$("#add-work-item").before(wunit);
			});
			// show students
			$.each(result.students, function(i, item) {
				var sunit = "<tr id=\"" + (i+1) + "\" name=\"" + item.id + "\">";
				sunit += "<td>" + item.id + "</td><td>" + item.name + "</td>";
				sunit += "<td><button type=\"button\" class=\"btn btn-default btn-sm\" name=\"" + (i+1) +"\"><span class=\"glyphicon glyphicon-trash\"></span></button></td></tr>";
				$("#add-student-item").before(sunit);
			});
			
			// add button action for delete student
			$("button").click(function() {
				if (!$.isNumeric($(this).attr("name")))
					return;
				var nr = $(this).attr("name");
				var userid = $("#"+nr).attr("name");
				if (!confirm("移除该学生？")) {return;}
				$.post("/subsys_interactive/class", 
					{
						delstudent: userid
					},
					function(result) {
						if (result == "SUCCESS") {
							$("#"+nr).remove();
							alert("已移除！");
						}
						else {
							alert("移除失败！");
						}
					});
			});
		});
	
	// change class info
	$("#change").click(function() {
		if ($(this).attr("name") == "change") {
			$(".info-change").removeAttr("readonly");
			$(this).css("background", "red");
			$(this).html("确认修改");
			$(this).attr("name", "submit");
			return;
		}
		else {
			$.post("/subsys_interactive/class", 
			{
				updatecname: $("#name").val(),
				updatecresume: $("#resume").val()
			},
			function(result) {
				if (result == "SUCCESS") {
					alert("修改成功！");
				}
				else {
					alert("修改失败！");
				}
				$(".info-change").attr("readonly", "readonly");
				$("#change").css("background-color", "#5bc0de");
				$("#change").text("修改信息");
				$("#change").attr("name", "change");
				return;
			});
		}
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
	
	// add student to the class
	$("#doaddstudent").click(function() {
		$.post("/subsys_interactive/addstudent", 
			{
				sids: $("#studentarea").val()
			},
			function(result) {
				if (result.substring(0,7) == "SUCCESS") {
					alert("添加成功！");
					// show students after added
					var students = result.substring(7).split(",");
					var i = $("#student-tbody").children().length;
					for (var cnt = 0; cnt < students.length; cnt++, i++) {
						var sunit = "<tr id=\"" + i + "\" name=\"" + students[cnt] + "\">";
						sunit += "<td><h4>" + students[cnt] + "</h4></td><td><h4></h4></td>";
						sunit += "<td><button type=\"button\" class=\"btn btn-default btn-sm\" name=\"" + i +"\"><span class=\"glyphicon glyphicon-trash\"></span></button></td></tr>";
						$("#add-student-item").before(sunit);
					}
				}
				else {
					alert(result);
				}
				$("#studentarea").val("");
			});
	});
	
});