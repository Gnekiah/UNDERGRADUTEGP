$(document).ready(function() {
	// load info while open the page
	$.post("/subsys_interactive/work",
	{
		workreq: "true"
	},
	function(result) {
		$("#title").attr("name", result.id);
		$("#title").val(result.title);
		$("#content").val(result.content);
		$("#referto").val(result.referto);
		$("#keylines").val(result.keylines);
		$("#testcasein").val(result.testcasein);
		$("#testcaseout").val(result.testcaseout);
		$("#limcpu").val(result.cpu);
		$("#limmem").val(result.mem);
		$("#codetype").val(result.codetype);
		$("#time").val(result.time);
	});
	
	// submit work content to server
	$("#submit-button").click(function() {
		$.post("/subsys_interactive/work",
			{
				title: $("#title").val(),
				content: $("#content").val(),
				referto: $("#referto").val(),
				testcasein: $("#testcasein").val(),
				testcaseout: $("#testcaseout").val(),
				keylines: $("#keylines").val(),
				limcpu: $("#cpu").val(),
				limmem: $("#mem").val(),
				codetype: $("#codetype").val(),
				time: $("#time").val()
			},
			function(result) {
				alert("SUCCESS!");
				window.location.reload();
			});
	});
	
});