function performRestCall() {
    //using fetch() to get the resource from the Rest API 
    fetch("/flowers-web/rest/flowers")
        //using then() to get response from the server as a parameter when the fetch() promise resolves
        .then(function (response) {
                if (response.status == 200) {
                    //once the fetch() is successful, using the json() to read and parse the data in the response
                    response.json().then(function (data) {
                        parseResponse(data);
                    });
                }
            }
        )
}
function parseResponse(request) {
    var html= "";
    //using forEach() to iterate over every object in the array 
    request.forEach(function (product) {
        // creates a <tr> element
        html += "<tr>";
        // to populate the table
        html += createCell(product.productId);
        html += createCell(product.productName);
        html += createCell(product.mainColor);
        html += createCell(product.environment);
        html += createCell(product.price);

        html += "</tr>";
    });
    //using querySelector to get the element by it's id specified in the HTML
    document.querySelector("#tb").innerHTML = html;
}
// creates an <td> element
function createCell(info) {
    return "<td>" + info + "</td>";
}

//using the addEventListener to register an action that will be executed once the DOM is ready
window.addEventListener('DOMContentLoaded', (event) => {
    performRestCall();
});

