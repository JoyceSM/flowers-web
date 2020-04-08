var rootURL = "http://localhost:8080/flowers-web/rest/flowers";

// when DOM is ready
$(document).ready(
	function () {

		$.ajax({
			type: 'GET',
			url: "http://localhost:8080/flowers-web/rest/occasion",
			dataType: "json",
			success: function (occasions) {
				$.each(occasions, function (index, occasion) {

					$("#val-product-occasion").append(
						'<option value="' + occasion.id + '">'
						+ occasion.occasion + '</option>');
					$("#val-newProduct-occasion").append(
						'<option value="' + occasion.id + '">'
						+ occasion.occasion + '</option>');
				});

			}
		})

		findAll();
	});
// create add function
$('#btnAdd').click(function () {
	if ($('#val-newProduct-id').val() == '') {
		addProduct();

	}
	return false;
});
// create edit function
function onClickEdit(ele) {
	var productId = $(ele).closest("tr").find("td:eq(0)").text();
	$('#val-product-id').prop('disabled', true);

	$.ajax({
		type: 'GET',
		url: rootURL + '/' + productId,
		dataType: "json",
		success: function (flower) {
			$('#val-product-id').val(flower.productId);
			$('#val-product-name').val(flower.productName);
			$('#val-product-description').val(flower.description);
			$('#val-product-color').val(flower.mainColor);
			$('#val-product-available').val(flower.available);
			$('#val-product-occasion').val(flower.occasionId);
			$('#val-product-environment').val(flower.environment);
			$('#val-product-dimensions').val(flower.dimensions);
			$('#val-product-price').val(flower.price);
			$('#val-product-image').val(flower.picture);
			$('#val-product-pics').attr('src', flower.picture);
			$('#val-product-occasion').val(flower.occasionId);
			$('#editProductModal').modal('show');

		}
	});
	return false;
}
// create add function
function onClickAdd(ele) {
	var productId = $(ele).closest("tr").find("td:eq(0)").text();
	$.ajax({
		type: 'POST',
		url: rootURL,
		dataType: "json",
		success: function (flower) {
			$('#val-product-id').val(flower.productId);
			$('#val-newProduct-name').val(flower.productName);
			$('#val-product-description').val(flower.description);
			$('#val-product-color').val(flower.mainColor);
			$("#val-product-available").val(flower.available);
			$('#val-product-occasion').val(flower.occasionId);
			$('#val-product-enviroment').val(flower.enviroment);
			$('#val-product-dimensions').val(flower.dimensions);
			$('#val-product-price').val(flower.price);
			$('#val-product-image').val(flower.picture);
			$("#val-product-occasion").val(flower.occasionId);
			$('#addProductModal').modal('show');

		}
	});
	return false;
}

// create remove function
function onClickRemove(ele) {
	if (confirm("Are you sure that you want to delete this product?")) {
		var productId = $(ele).closest("tr").find("td:eq(0)").text();
		deleteProduct(productId);
	}
	return false;

}
// create save function
$('#btnSave').click(function () {
	if ($('#val-product-id').val() == '') {
		addProduct();

	} else {
		updateProduct();

	}
	return false;
});

// create new product
var addProduct = function () {
	console.log('addProduct');
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL,
		dataType: 'json',
		data: formToJSONAdd(),
		success: function (data) {
			alert('Product created successfully');
			clearFormField();
			$('#addProductModal').modal('hide');
			findAll();

		},
		error: function (jqXHR, textStatus) {
			alert('addProduct error: ' + textStatus);
		}
	});
}
// clear form field
function clearFormField() {
	$('#val-newProduct-name').val('')
	$('#val-newProduct-description').val(''),
	$("#val-newProduct-available").val(''),
	$('#val-newProduct-dimensions').val(''),
	$('#val-newProduct-price').val(''),
	$('#val-newProduct-color').val(''),
	$('#val-newProduct-image').val(''),
	$('#val-newProduct-environment').val(''),
	$('#val-newProduct-occasion').val('')

};

// create findAll
var findAll = function () {
	console.log('findAll');
	$.ajax({
		type: 'GET',
		url: rootURL,
		dataType: "json",
		success: renderList,

	});
}

// create remove 
var deleteProduct = function (productId) {
	console.log('deleteProduct');
	$.ajax({
		type: 'DELETE',
		url: rootURL + '/' + productId,
		success: function () {
			alert('Product deleted successfuly');
			currentProduct = {};
			findAll();
		},
		error: function () {
			alert('Delete product error')
		}
	})
}
// create update
var updateProduct = function () {
	console.log('updateProduct');
	$.ajax({
		type: 'PUT',
		contentType: 'application/json',
		url: rootURL + '/' + $('#val-product-id').val(),
		dataType: "json",
		data: formToJSONEdit(),
		success: function () {
			alert('Product updated successfully');
			$('#editProductModal').modal('hide');
			findAll();
		},
		error: function (jqXHR, textStatus, errorThrown) {
			alert('updateProduct error: ' + textStatus);
		}
	});
};
// Helper function to serialize all the form fields into a JSON string
var formToJSONEdit = function () {
	return JSON.stringify({

		"description": $('#val-product-description').val(),
		"available": $("#val-product-available").val(),
		"dimensions": $('#val-product-dimensions').val(),
		"price": $('#val-product-price').val(),
		"mainColor": $('#val-product-color').val(),
		"picture": $('#val-product-image').val(),
		"environment": $('#val-product-environment').val(),
		"occasionId": $('#val-product-occasion').val(),
		"productName": $('#val-product-name').val()

	});

};
// Helper function to serialize all the form fields into a JSON string
var formToJSONAdd = function () {
	return JSON.stringify({

		"description": $('#val-newProduct-description').val(),
		"available": $("#val-newProduct-available").val(),
		"dimensions": $('#val-newProduct-dimensions').val(),
		"price": $('#val-newProduct-price').val(),
		"mainColor": $('#val-newProduct-color').val(),
		"picture": $('#val-newProduct-image').val(),
		"environment": $('#val-newProduct-environment').val(),
		"occasionId": $('#val-newProduct-occasion').val(),
		"productName": $('#val-newProduct-name').val()

	});

};

// create renderList
var renderList = function (response) {
	response.data = response;
	console.log(response);
	$('#flowerList').html('');
	$.each(response, function (index, flower) {
		// instead of add the HTML in JS, it was created a
		// template in
		// index.html and used it in js to keep the good
		// practice
		var pt = $('#product-template').clone();
		pt.find('.product-id').text(flower.productId);
		pt.find('.product-name').text(flower.productName);
		pt.find('.product-description').text(flower.description);
		pt.find('.product-price').text('€' + flower.price);
		pt.attr('id', 'product-id-' + index)
		pt.find('.product-image').attr('src', flower.picture);
		pt.show();

		$('#flowerList').append(pt);

		console.log(pt);

	});
	// initializing datatable
	if ($.fn.dataTable.isDataTable('#list')) {

		var datatable = $('#list').DataTable();
		datatable.clear();
		datatable.rows.add(response);
		datatable.draw();

	} else {
		$('#list')
			.DataTable(
				{
					data: response.data,
					columns: [
						{
							"data": "productId"
						},
						{
							"data": "productName"
						},
						{
							"data": "mainColor"
						},
						{
							"data": "environment"
						},
						{
							"data": "price"
						},
						{

							data: null,
							className: "center",

							defaultContent: '<a href="#""class="editor_edit"onclick="onClickEdit(this);">Edit</a>/<a href="#"class="editor_remove"onclick="onClickRemove(this);">Delete</a>'
						}]
				});

	}


}
