$(document).ready(function() {
	// delete all teacher account
	$("#delall").click(function() {
		if (!confirm("是否清空所有教师账号？")) {return;}
		if (!confirm("请重复确认是否清空所有教师账号？")) {return;}
		alert("即将清空所有教师账号！");
		$.post("/subsys_interactive/admindelteacher", 
		{
			delall: "true"
		},
		function(result) {
			$("#table").remove();
			$("#result").html("清空教师账号！！！！");
		});
	});
	
	// 加载完毕就执行的函数,从服务器请求数据
	$.post("/subsys_interactive/admindelteacher", 
		{
			getlist: "true"
		},
		function(data) {
			// 将数据显示到页面
			var table = "";
			var arrids = data.split("|");
			for (var i=0; i < arrids.length; i++) {
				var arrid = arrids[i].split("^");
				table += "<tr id=\"" + (i+1) + "\" name=\"" + arrid[0] + "\">";
				table += "<td>" + (i+1) + "</td><td>" + arrid[0] + "</td><td>" + arrid[1] + "</td>";
				table += "<td><button type=\"button\" class=\"btn btn-default btn-sm\" name=\"" + (i+1) +"\"><span class=\"glyphicon glyphicon-trash\"></span></button></td></tr>";				
			}
			$("#tbody").html(table);
			
			/* 这里有个巨坑！！！！！ 
			 * 故事是这样的：
			 * 一开始把 button 的响应事件写到了ready() 下，与 $.post() 平行
			 * 导致 button 绑定的事件一直无法响应
			 * 原因是在加载好后，button 事件无法与最新添加的 dom 产生关联
			 * 所以要把响应事件写在 ajax 动态加载好 dom 的后面
			 */
			// 监听button事件
			$("button").click(function() {
				if (!$.isNumeric($(this).attr("name")))
					return;
				var nr = $(this).attr("name");
				var userid = $("#"+nr).attr("name");
				if (!confirm("删除该账号？")) {return;}
				if (!confirm("请重复确认是否删除该账号？")) {return;}
				$.post("/subsys_interactive/admindelteacher", 
					{
						deluser: userid
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