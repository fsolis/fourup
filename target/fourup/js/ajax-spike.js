function register(){
	//do some stuff
	alert("register called")
	var email = $("#regEmail").val();
	var password = $("#password1Reg").val()
	
	$.ajax({
		type : "POST",
		url : '/signup' + email + '&pwd=' + password ,
		data: {'email': 'email', 'password' : 'password'}
		success: function(data) {
			alert(data);
		},
		error : function() {
			alert("Sorry, The requested mock entity could not be found.");
		}
	});
}