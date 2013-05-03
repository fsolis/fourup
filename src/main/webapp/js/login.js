$(document).ready(function(){
	$("#loginForm").submit(function(e){

		e.preventDefault();
		alert("login called");
		
		var userinfo = $("#loginForm").serialize();
		var tester;
		tester = $.post("login",userinfo, function(data){
			console.log(data);
			console.log(data.html)
			$("#loginForm").addClass("hidden");
			$("#loggedIn").html(data.html);
			$("#loggedIn").removeClass("hidden");
			$("#register").addClass("hidden");
			//console.log(tester.getResponseHeader('test'));
		});
		return false;

	});
});