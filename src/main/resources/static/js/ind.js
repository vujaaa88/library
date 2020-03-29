$(document).delegate('#btn', 'click', function() {
		$.getJSON("http://library-env.eba-i6btkwrd.eu-west-2.elasticbeanstalk.com/user", function(data){
			if(data.library !== null){
				location.replace("homepage");
			} else {
				$('#add').css('display', 'block');
			}
		});
});

$(document).delegate('#closeBtn', 'click', function() {
	$('#add').css('display', 'none');
});

$(document).delegate('#create', 'click', function(event) {
	event.preventDefault();
	var name = $('#name').val();
	if(name){
	$.ajax({
		type: "POST",
		contentType: "application/json; charset=utf-8",
		url: "http://library-env.eba-i6btkwrd.eu-west-2.elasticbeanstalk.com/create",
		data: JSON.stringify({'libraryName': name}),
		cache: false,
		success: function(result) {
			$("#msg").html( "<span style='color: #ADFF2F'>Your library created successfully</span>" );
			window.setTimeout(function() {location.replace("homepage")},1000)
		},
		error: function(err) {
			$("#msg").html( "<span style='color: red'>Something is wrong try again</span>" );
		}
	});
	} else {
		$("#msg").html( "<span style='color: red'>Library name is required</span>" );
	}
});
