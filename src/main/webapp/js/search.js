$(document).ready(function(){
	$("#searchForm").submit(function(e){

		e.preventDefault();
		alert("saveRecord called");
		
		var searchLink = $("#searchForm").serialize();
		var tester;
		tester = $.post("saveRecord",searchLink, function(data){
			console.log(data);
			console.log(data.html)
			//console.log(tester.getResponseHeader('test'));
		});
		return false;

	});
});