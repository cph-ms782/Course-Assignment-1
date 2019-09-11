/**
 * jsonList2Table converts a json-list into a html table
 * and places it at the html tag position
 * arguments: a jsonlist and the tag identifier
 */
function jsonList2Table(jsonList, htmlTag) {
//    console.log("jsonList: " + jsonList);

    //Create a HTML Table element.
    var table = document.createElement("TABLE");
    table.setAttribute("class", "table");

    //insert empty row.
    var row = table.insertRow(-1);

    //insert header cell elements
    // it uses the first entry to get object properties
    Object.keys(jsonList[0]).forEach(function (item) {
//        console.log("item: " + item);
        var headerCell = document.createElement("TH");
        var text = document.createTextNode(item);
        headerCell.appendChild(text);
        row.appendChild(headerCell);
    });

    //Add the data rows.
    jsonList.forEach(function (listItem, index) {
//        console.log("listItem.carID: " + listItem.carID);

        row = table.insertRow(-1);
        Object.keys(listItem).forEach(function (parts) {
//            console.log("listItem[parts]: " + listItem[parts]);
            var cell = row.insertCell(-1);
            var text = document.createTextNode(listItem[parts]);
            cell.appendChild(text);
        });
    });
    
    var dvTable = document.querySelector(htmlTag);
    dvTable.innerHTML = "";
    dvTable.appendChild(table);
}
