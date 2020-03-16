var rootURL = "http://localhost:8080/flowers-web/rest/flowers";

// when DOM is ready
$(document).ready(function() {
	$('#list').DataTable({// create a datatable list
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
			className : "center",// edit
			// column
			defaultContent : '<a href=""class="editor_edit">Edit</a>'

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
	$.each(response, function(index, flower) {
		// instead of add the HTML in JS, it was created a template in
		// index.html and used it in js to keep the good practice
		var pt = $('#product-template').clone();
		pt.find('.product-name').text(flower.productName);
		pt.find('.product-description').text(flower.description);
		pt.find('.product-price').text('€' + flower.price);
		pt.attr('id', 'product-id-' + index)
		pt.find('.product-image').attr('src', flower.picture);
		pt.show();

		$('#flowerList').append(pt);

		console.log(pt);

	});
}
