$(document).ready(function() {
	// load info while open the page
	$.post("/subsys_interactive/dotest",
	{
		loadrq: "true"
	},
	function(result) {
		$.each(result.dotests, function(i, item) {
			if (item.tested == "no") {
				$("#unlast-item").before("<tr><td>" + item.sid + "</td></tr>");
			} else {
				var rptunit = "<tr class=\"rpt-item\"><td>" + item.sid + "</td>";
				rptunit += "<td><a href=# title=\"" + item.dynamicReport + "\">" + item.dynamicScore + "</a></td>";
				rptunit += "<td><a href=# title=\"" + item.staticReport + "\">" + item.staticScore + "</a></td>";
				rptunit += "<td>" + item.time + "</td></tr>";
				$("#last-item").before(rptunit);
			}
		});
	});
	
	$("#dotest").click(function() {
		$.post("/subsys_interactive/dotest",
		{
			dotestrq: "true"
		},
		function(result) {
			alert(result);
		});
	});
});