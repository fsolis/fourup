var login = "marcb70";
var api_key = "R_9d2db1de70dae269b6ee91cd96bd44b0";

function get_short_url(long_url, login, api_key, func) {
	$.getJSON("http://api.bitly.com/v3/shorten?callback=?", {
		"format" : "json",
		"apiKey" : api_key,
		"login" : login,
		"longUrl" : long_url
	}, function(response) {
		func(response.data.url);
	});
}

function create(hostname) {
	if(hostname == null){
		hostname = "http://fourup-v1-9.herokuapp.com";
	}
	linka = hostname + '?first=http://' + $('#first').val()
			+ '&second=http://' + $('#second').val() + '&third=http://'
			+ $('#third').val() + '&fourth=http://' + $('#fourth').val();
	$('#titlea').html(
			'<a href="' + linka + '" target="_new">Here is your fourup.</a>');

	get_short_url(linka, login, api_key, function(short_url) {
		console.log(short_url);
		$('#result').val(short_url);
	});
}
// http://stackoverflow.com/questions/901115/how-can-i-get-query-string-values

