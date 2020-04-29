$.getJSON("https://personal-library-application.herokuapp.com/user", function(data) {
	var nameOf = data.library.libraryName.valueOf();
		document.getElementById("libName").innerHTML +=" " + nameOf;
	});

	$(document).ready(function() {
						$.getJSON("https://personal-library-application.herokuapp.com/books",function(json) {
									var tr = [];
									for (var i = 0; i < json.length; i++) {
										tr.push('<tr>');
										tr.push('<td>' + json[i].id + '</td>');
										tr.push('<td>' + json[i].title + '</td>');
										tr.push('<td>' + json[i].description + '</td>');
										tr.push('<td>' + json[i].createdOn + '</td>');
										tr.push('<td>' + json[i].publishedOn + "s" + '</td>');
										tr.push('<td>' + json[i].author + '</td>');
										var birthday = new Date(json[i].authorsBirthday);
										var newdate = birthday.getDate() + "." + (birthday.getMonth() + 1) + "." + birthday.getFullYear();
										
										tr.push('<td>' + newdate + '</td>');
										tr.push('<td><button class=\'edit\' id =' + json[i].id + ' style="background-color:green">Edit</button><button class=\'delete\' id=' + json[i].id + ' style="background-color:red">Delete</button></td>');
										tr.push('</tr>');
									}
											$('table').append($(tr.join('')));
						});

						$(document).delegate('#book','click',function(event) {
											event.preventDefault();
											var title = $('#title').val();
											var description = $('#description').val();
											var publishedOn = $('#publishedOn').val();
											var author = $('#author').val();
											var authorsBirthday = $('#authorsBirthday').val();

											$.ajax({
													type : "POST",
													contentType : "application/json; charset=utf-8",
													url : "https://personal-library-application.herokuapp.com/add",
													data : JSON.stringify({
																'title' : title,
																'description' : description,
																'publishedOn' : publishedOn,
																'author' : author,
																'authorsBirthday' : authorsBirthday
															}),
													cache : false,
													success : function(result) {
															$("#msg").html("<span style='color: #ADFF2F'>Book added successfully</span>");
															window.setTimeout(function() {location.reload()},1000)
														},
													error : function(err) 
															{$("#msg").html("<span style='color: red'>Something is wrong</span>");
														}
											});
						});

						$(document).delegate('#open', 'click', function() {
							$('#add').css('display', 'block');
						});
						
						$(document).delegate('#closeBtn', 'click', function() {
							$('#add').css('display', 'none');
							window.setTimeout(function() {
								location.reload()
									})
						});

						$(document).delegate('.delete','click',function() {
											if (confirm('Do you really want to delete book?')) {
												var id = $(this).attr('id');
												var parent = $(this).parent().parent();
												$.ajax({
													type : "DELETE",
													url : "https://personal-library-application.herokuapp.com/delete" + id,
													cache : false,
													success : function() {
														parent.fadeOut('slow',function() {
																	$(this).remove();
																	});
														location.reload(true)
																},
													error : function() {
														$('#err').html('<span style=\'color:red; font-weight: bold; font-size: 3vw;\'>Error deleting record')
																.fadeIn()
																.fadeOut(
																4000,function() {
																	$(this).remove();
																	});
															}
												});
											}
						});
						
						$(document).delegate('#deleteAll','click',function() {
							if (confirm('Do you really want to delete all books?')) {
								$.ajax({
									type : "DELETE",
									url : "https://personal-library-application.herokuapp.com/deleteAll",
									cache : false,
									success : function() {
										location.reload(true)
												},
									error : function() {
										$('#err').html('<span style=\'color:red; font-weight: bold; font-size: 3vw;\'>Error deleting record')
												.fadeIn()
												.fadeOut(
												4000,function() {
													$(this).remove();
													});
											}
								});
							}
						});


						$(document).delegate('.edit','click',function() {
											$("#bookTitle").html("Edit book");
											$("#save").html("<input type='submit' id='update' value='Update' class='sub'>");
											var id = $(this).attr('id');
											$.getJSON("https://personal-library-application.herokuapp.com/book" + id, function(book) {
											
														$("#title").val(book.title);
														$("#description").val(book.description);
														$("#createdOn").val(book.createdOn);
														$("#publishedOn").val(book.publishedOn);
														$("#author").val(book.author);
														$("#authorsBirthday").val(book.authorsBirthday);
														$('#add').css('display','block');
											});
						$(document).delegate('#update','click',function(event) {
														event.preventDefault();
														
														var title = $('#title').val();
														var description = $('#description').val();
														var createdOn = $('#createdOn').val();
														var publishedOn = $('#publishedOn').val();
														var author = $('#author').val();
														var authorsBirthday = $('#authorsBirthday').val();
													$.ajax({
														type : "POST",
														contentType : "application/json; charset=utf-8",
														url : "https://personal-library-application.herokuapp.com/add",
														data : JSON.stringify({
															'id' : id,
															'title' : title,
															'description' : description,
															'createdOn' : createdOn,
															'publishedOn' : publishedOn,
															'author' : author,
															'authorsBirthday' : authorsBirthday
															}),
														cache : false,
														success : function(result) {
															$("#msg").html("<span style='color: #ADFF2F'>Book edited successfully</span>");
																window.setTimeout(function() {
																	location.reload()
																		},1000)
																	},
														error : function(err) {
															$("#msg").html("<span style='color: red'>Something is wrong</span>");
																}
													});
									});
						});
					});