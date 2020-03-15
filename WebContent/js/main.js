var rootURL = "http://localhost:8080/flowers-web/rest/flowers";

// when DOM is ready
$(document).ready(function() {
	$('#example').DataTable({
		"ajax" : {
			"url" : rootURL,
			"dataSrc" : ""
		},
		"columns" : [ {
			"data" : "productName"
		}, {
			"data" : "mainColor"
		}, {
			"data" : "enviroment"
		}, {
			"data" : "price"
		}, {
			data : null,
			className : "center",// edit column
			defaultContent : '<a href="" class="editor_edit">Edit</a>'

		} ]

	});
	findAll();
});

// create findAll
var findAll = function() {
	console.log('findAll');
	$.ajax({
		type : 'GET',
		url : rootURL,
		dataType : "json",
		success : renderList,

	});
}

// create renderList
var renderList = function(response) {
	response.data = response;
	console.log(response);
	$
			.each(
					response,
					function(index, flower) {
						$('#flowerList')
								.append(
										'<div class="col-sm-6 col-md-4 col-lg-3 mb-5"><div class="card h-100"><a href="#"><img class="card-img-top" src="http://placehold.it/700x400" alt=""></a><div class="card-body"><h4 class="card-title"><a href="#">'
												+ flower.productName
												+ '</a></h4><h5>€'
												+ flower.price
												+ '</h5><p class="card-text">'
												+ flower.description
												+ '</p></div></div></div>');

					});
}