/**
 * 
 */
function GenerateTable() {
    //Build an array containing Customer records.
    var customers = new Array();
    customers.push(["Customer Id", "Name", "Country"]);
    customers.push([1, "John Hammond", "United States"]);
    customers.push([2, "Mudassar Khan", "India"]);
    customers.push([3, "Suzanne Mathews", "France"]);
    customers.push([4, "Robert Schidner", "Russia"]);

    //Create a HTML Table element.
    var table = document.createElement("TABLE");
    table.border = "1";

    //Get the count of columns.
    var columnCount = customers[0].length;

    //Add the header row.
    var row = table.insertRow(-1);
    console.log("customers[0] : " + customers[0]);
    for (var cust in customers[0]) {
        console.log("header : " + cust);
        var headerCell = document.createElement("TH");
        var text = document.createTextNode(cust);
        headerCell.appendChild(text);
        row.appendChild(headerCell);
    }

    //Add the header row.
//        var row = table.insertRow(-1);
//        for (var i = 0; i < columnCount; i++) {
//            var headerCell = document.createElement("TH");
//            headerCell.innerHTML = customers[0][i];
//            row.appendChild(headerCell);
//        }

    //Add the data rows.
    for (var i = 1; i < customers.length; i++) {
        row = table.insertRow(-1);
        for (var j = 0; j < columnCount; j++) {
            var cell = row.insertCell(-1);
            cell.innerHTML = customers[i][j];
        }
    }

    var dvTable = document.getElementById("content");
    dvTable.innerHTML = "";
    dvTable.appendChild(table);
}

//table.push("<thead><tr>    <th>Title:</th> <th>Year:</th> </th>      </tr></thead>");

function list2Table(list) {

    var tags = makeTableHeader();
    console.log("Efter header: " + tags);

    makeTableBody(tags);
    console.log("Efter body: " + tags);
    document.querySelector("#content").appendChild(tags);
}

function makeTableHeader() {
    var obj = {hejsa: 234, listen: "hujsa"};

    var node = document.createElement("thead");
    var nodeRow = document.createElement("tr");
    node.appendChild(nodeRow);

    var th;
    for (var item in obj) {
        th = document.createElement("th");
        var nodeCell = document.createTextNode(item);
//        th.lastElementChild.appendChild(nodeCell);
    }

    nodeRow.appendChild(th);

    console.log("Table header tags: " + node.toString());

    return node;
}

function makeTableBody(tags) {

    return tags;
}

list2Table("");
GenerateTable();