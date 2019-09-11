/**
 * table utils
 */

//constants
const URL = "/CAone/api/cars/all";
/**
 * Making table of all cars and utils
 */
function getAllCars(e) {
    e.preventDefault();
    console.log(e.target.innerText);
    fetch(URL)
            .then(res => res.json())
            .then(data => {
                var headerCellText = e.target.innerText;
                if (headerCellText === "price") {
                    data.sort((a, b) => parseFloat(a.price) - parseFloat(b.price));
                } else if (headerCellText === "make") {
                    data.sort((a, b) => a.make.localeCompare(b.make));
                } else if (headerCellText === "carID" ||
                       headerCellText === "Cars" ) {
                    data.sort((a, b) => parseInt(a.carID) - parseInt(b.carID));
                } else {
                    data.sort((a, b) => parseInt(a.year) - parseInt(b.year));
                }
                /**
                 * jsonList2Table() is in tables.js
                 * It converts a json-list into a html table
                 * arguments: a jsonlist and the tag identifier
                 */
                jsonList2Table(data, "#content");
                addEvents();
            });
}

function addEvents() {
    document.querySelector("#make").addEventListener("click", getAllCars);
    document.querySelector("#year").addEventListener("click", getAllCars);
    document.querySelector("#carID").addEventListener("click", getAllCars);
    document.querySelector("#price").addEventListener("click", getAllCars);
}

//eventlistener
document.querySelector("#carPage").addEventListener("click", getAllCars);
console.log("check");


