function isExistsName(userName){
			var names = [];
		 
				$.ajax({
				async: false,
				dataType : 'json',
				url: "https://personal-library-application.herokuapp.com/users",
				type : 'GET',
				success: function(data) {
					for(var i in data){ 
						names.push(data[i].userName);
						}
					}
				});
			for(var j = 0; j < names.length; j++){       
				if(userName === names[j]){
					return true;
				}
			}
		return false;
}

function isExistsEmail(parEmail){
	 var emails = [];
	 
       $.ajax({
       async: false,
        dataType : 'json',
        url: "https://personal-library-application.herokuapp.com/users",
        type : 'GET',
        success: function(data) {
        for(var k in data){ 
			emails.push(data[k].email);
         	}
         }
     });
for(var l = 0; l < emails.length; l++){       
if(parEmail === emails[l]){
return true;
}
} return false;
}

function isEmail(validEmail) {
	  var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	  return regex.test(validEmail);
	}

$(document).delegate("#new",'click',function(event){
	
	event.preventDefault();
	var firstName = $('#firstName').val();
	var lastName = $('#lastName').val();
	var email = $('#email').val();
	var userName = $('#userName').val();
	var password = $('#password').val();
	
	if(firstName && lastName && userName && email && password && !(isExistsName(userName)) && !(isExistsEmail(email)) && isEmail(email) ){
	$.ajax({
		type: "POST",
		contentType: "application/json; charset=utf-8",
		url: "https://personal-library-application.herokuapp.com/new",
		data: JSON.stringify({'firstName': firstName,
				'lastName':lastName,
				'email':email,
				'userName':userName,
				'password':password
		}),
		cache: false,
		success: function(result) {
			$("#mess").html( "<span style='color: #ADFF2F'>You have been successfully registered. <a style='color:#4169E1' href='login'>Sign in</a></span>" );
		},
		error: function(err) {
			$("#mess").html( "<span style='color: red'>Something is wrong</span>" );
		}
	});
	} else if(isExistsName(userName)) {
		$('#mess').html( "<span style='color: red'>User name is already exist</span>" );	
	} else if(isExistsEmail(email)){
		$('#mess').html( "<span style='color: red'>Email is already exist</span>" );
	}
	else if(email && !isEmail(email)){
		$('#mess').html( "<span style='color: red'>Please enter valid email</span>" );
	}
	else {
	$('#mess').html( "<span style='color: red'>All fields are required</span>" );	
	}
});