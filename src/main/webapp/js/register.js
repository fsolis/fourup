$(document).ready(function(){
	$("#registration").submit(function(e){

		e.preventDefault();
		
		var userinfo = $("#registration").serialize();
		var tester;
		tester = $.post("signup",userinfo, function(data){
			console.log(data);
			console.log(data.Status)
			//console.log(tester.getResponseHeader('test'));
		});
		return false;

	});
});


