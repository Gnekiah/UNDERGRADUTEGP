$(document).ready(function() {
	
	// load info while open the page
	$.post("/subsys_interactive/classview",
		{
			classviewreq: "true"
		},
		function(result) {
			$("title").html(result.name);
			$("#classname").html(result.name);
			$("#classid").text(result.id);
			$("#name").val(result.name);
			$("#resume").text(result.resume);

			// show works
			$.each(result.works, function(i, item) {
				var link = "/subsys_interactive/classview?workid=" + item.id;
				var wunit = "<tr class=\"work-item\" onclick=\"window.open('" + link +"')\">";
				wunit += "<td>" + item.id + "</td><td>" + item.title + "</td><td>" + item.time + "</td></tr>";
				
				$("#add-work-item").before(wunit);
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