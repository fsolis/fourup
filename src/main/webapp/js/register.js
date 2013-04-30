$(document).ready(function(){
	$("#registration").submit(function(e){

		e.preventDefault();
		alert("register called");
		
		var userinfo = $("#registration").serialize();
		
		$.post("signup",userinfo, function(data){
			alert(data);
		});
		return false;

	});
});


