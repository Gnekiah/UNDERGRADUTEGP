$(document).ready(function() {
	// load info while open the page
	$.post("/subsys_interactive/classview",
	{
		workviewreq: "true"
	},
	function(result) {
		$("#title").attr("name", result.id);
		$("#title").val(result.title);
		$("#content").val(result.content);
		$("#codetype").val(result.codetype);
		$("#time").val(result.time);
	});

	
});