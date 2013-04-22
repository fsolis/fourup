$(document).ready(function(){

	$('#firstFB').click(function(){
		console.log("clicked");
		if($(this).val() == 'full 1'){
			resetcss();
			$('.frameDiv').css('display', 'none');
			$('#firstF').css('display', 'block');
			$('#firstF').css('height', '100%');
			$('#firstF').css('width','100%');
			$(this).val('fourup');
		}else{
			resetcss();
			$(this).val('full 1');
		}
	});

	$('#secondFB').click(function(){

		if($(this).val() == 'full 2'){
			resetcss();
			$('.frameDiv').css('display', 'none');
			$('secondF').css('display', 'block');
			$('secondF').css('height', '100%');
			$('secondF').css('width','100%');
			$(this).val('fourup');
		}else{
			resetcss();
			$(this).val('full 2');
		}
	});

	$('#thirdFB').click(function(){
		if($(this).val() == 'full 3'){
			resetcss();
			$('.frameDiv').css('display', 'none');
			$('thridF').css('height', '100%');
			$('thridF').css('display', 'block');
			$('thridF').css('width','100%');
		}else{
			resetcss();
			$(this).val('full 3');
		}
	});

	$('#fourthFB').click(function(){
		if($(this).val() == 'full 3'){
			resetcss();
			$('.frameDiv').css('display', 'none');
			$('fourthF').css('display', 'block');
			$('fourthF').css('height', '100%');
			$('fourthF').css('width','100%');
		}else{
			resetcss();
			$(this).val('full 4');
		}
	});

	function resetcss(){
		$('.frameDiv').css('display', 'block');
		$('#firstF').css('display', '');
		$('#firstF').css('height', '');
		$('#firstF').css('width','');
		$('secondF').css('display', '');
		$('secondF').css('height', '');
		$('secondF').css('width','');
		$('thridF').css('height', '');
		$('thridF').css('display', '');
		$('thridF').css('width','');
		$('fourthF').css('height', '');
		$('fourthF').css('display', '');
		$('fourthF').css('width','');
	}
});