$(document).ready(function(){

	$('#firstFB').click(function(){
	
		if($(this).val() == 'full 1'){
			resetcss();
			$('.frameDiv').css('display', 'none');
			$('#firstF').css('display', 'block');
			$('#firstF').css('height', '100%');
			$('#firstF').css('width','100%');
			$('#centerMenu').css('display', 'none');
			$('#centerMenuHover').css('display', 'none');
			$('#closeFS').css('display', 'block');
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
			$('#secondF').css('display', 'block');
			$('#secondF').css('height', '100%');
			$('#secondF').css('width','100%');
			$('#centerMenu').css('display', 'none');
			$('#centerMenuHover').css('display', 'none')
			$('#closeFS').css('display', 'block');;
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
			$('#thirdF').css('display', 'block');
			$('#thirdF').css('height', '100%');
			$('#thirdF').css('width','100%');
			$('#centerMenu').css('display', 'none');
			$('#centerMenuHover').css('display', 'none');
			$('#closeFS').css('display', 'block');
			$(this).val('fourup');
		}else{
			resetcss();
			$(this).val('full 3');
		}
	});

	$('#fourthFB').click(function(){
		if($(this).val() == 'full 4'){
			resetcss();
			$('.frameDiv').css('display', 'none');
			$('#fourthF').css('display', 'block');
			$('#fourthF').css('height', '100%');
			$('#fourthF').css('width','100%');
			$('#centerMenu').css('display', 'none');
			$('#centerMenuHover').css('display', 'none');
			$('#closeFS').css('display', 'block');
			$(this).val('fourup');
		}else{
			resetcss();
			$(this).val('full 4');
		}
	});

	$('#closeFS').click(function(){
		resetcss();
	});
	
	function resetcss(){
		$('#firstFB').val('full 1')
		$('#secondFB').val('full 2')
		$('#thirdFB').val('full 3')
		$('#fourthFB').val('full 4')
		$('.frameDiv').css('display', 'block');
		$('#firstF').css('display', '');
		$('#firstF').css('height', '');
		$('#firstF').css('width','');
		$('#secondF').css('display', '');
		$('#secondF').css('height', '');
		$('#secondF').css('width','');
		$('#thirdF').css('display','');
		$('#thirdF').css('height','');
		$('#thirdF').css('width','');
		$('#fourthF').css('display', '');
		$('#fourthF').css('height', '');
		$('#fourthF').css('width','');
		$('#centerMenu').css('display', 'block');
		$('#closeFS').css('display', 'none');
	}
});