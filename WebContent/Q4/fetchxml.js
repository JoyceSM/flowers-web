// creates an instance of an object that interacts with server
function getHTTPObject() {
	var xhr = new XMLHttpRequest();
	return xhr;
}

function performRestCall() {
	var request = getHTTPObject();
	if (request) {
		// once the onreadyState is reached the function is called
		request.onreadystatechange = function () {
			parseResponse(request);
		};
		//points to the rest API
		request.open("GET", "/flowers-web/rest/flowers", true);
		// to initiate the request
		request.send(null);
	}
}

function parseResponse(request) {
	//proceeds if the operation is completed
	if (request.readyState == 4) {
		//proceeds if the operation is OK or Not Modified
		if (request.status == 200 || request.status == 304) {
			var data = JSON.parse(request.responseText);
			// to iterate over every object in the array 
			for (var i = 0; i < data.length; i++) {
				var product = data[i];
				// creates a <tr> element
				data_row = document.createElement("tr");
				// to populate the table
				createCell(product.productId);
				createCell(product.productName);
				createCell(product.mainColor);
				createCell(product.environment);
				createCell(product.price);
			}
		}
	}
}

function createCell(info) {
	// creates an <td> element
	data_cell = document.createElement("td");
	// creates a Text Node
	product_data = document.createTextNode(info);
	// appends the Text Node that was created into the cell <td>
	data_cell.appendChild(product_data);
	// appends the cell <td> into the row <tr>
	data_row.appendChild(data_cell);
	// appends the row <tr> into <tbody>
	document.getElementById("tb").appendChild(data_row);
}

// once the DOM is ready the following method is called
window.onload = function () {
	performRestCall();
};
