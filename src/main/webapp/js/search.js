$(document).ready(function(){
	$("#searchForm").submit(function(e){

		e.preventDefault();
		
		var searchLink = $("#searchForm").serialize();
		var tester;
		tester = $.post("saveRecord",searchLink, function(data){
			console.log(data);
			//console.log(tester.getResponseHeader('test'));
		});
		create();
		return false;
		
	});
});