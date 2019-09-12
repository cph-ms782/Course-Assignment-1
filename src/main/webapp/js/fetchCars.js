/**
 * table utils
 */

//constants
const URL = "/CAone/api/cars/all";
const CONTENTDIV = document.querySelector("#content");
var filterNow = false;
/**
 * Making table of all cars
 */
function getAllCars(e) {
    e.preventDefault();
    console.log(e.target.innerText);

    fetch(URL)
            .then(res => res.json())
            .then(data => {
                //sorting arrays
                sorting(e, data);

                // filtering
                data = filtering(data);

                /**
                 * jsonList2Table() is in tables.js
                 * It converts a json-list into a html table
                 * arguments: a jsonlist and the tag identifier
                 */
                console.log("data before table.js: " + data +
                        "\ntypeof data: " + typeof data);
                jsonList2Table(data, "#content");
                //For small screens
                CONTENTDIV.classList.add("table-responsive");
                //Events needs to be added after the table has been written
                addEvents();
            });
}

function sorting(event, data) {
    switch (event.target.innerText) {
        case "price":
            data.sort((a, b) => parseFloat(a.price) - parseFloat(b.price));
            break;
        case "make":
            data.sort((a, b) => a.make.localeCompare(b.make));
            break;
        case "carID":
        case "Cars": //e.target = Cars first time table is made
            data.sort((a, b) => parseInt(a.carID) - parseInt(b.carID));
            break;
        case "year":
            data.sort((a, b) => parseInt(a.year) - parseInt(b.year));
            break;
    }
}

function filtering(data) {
    console.log("data in filtering: " + data);
    var makeInput = document.querySelector("#makeInput").value;
    var beforeYearInput = document.querySelector("#beforeYearInput").value;
    var afterYearInput = document.querySelector("#afterYearInput").value;
    var priceLessInput = document.querySelector("#priceLessInput").value;
    var priceMoreInput = document.querySelector("#priceMoreInput").value;
    
    //input checks
    if (beforeYearInput < afterYearInput
            && afterYearInput !== "" && beforeYearInput !== "") {
        alert("'Before year' has to be higher than 'After year'!\n" +
                "beforeYearInput: " + beforeYearInput +
                "\afterYearInput: " + afterYearInput
                );
        return;
    }
    
    if (priceLessInput < priceMoreInput
            && priceMoreInput !== "" && priceLessInput !== "") {
        alert("'Price less..' has to be higher than 'Price more..'!\n" +
                "priceMoreInput: " + priceMoreInput +
                "\npriceLessInput: " + priceLessInput
                );
        return;
    }
    
    if (makeInput !== "") {
        let isWhatMake = (car) => {
            return car.make === document.querySelector("#makeInput").value;
        };
        var data = data.filter(isWhatMake);
    }

    if (beforeYearInput !== "") {
        let isBefore = (car) => {
            return car.year < document.querySelector("#beforeYearInput").value;
        };
        var data = data.filter(isBefore);
    }

    if (afterYearInput !== "") {
        let isAfter = (car) => {
            return car.year > document.querySelector("#afterYearInput").value;
        };
        var data = data.filter(isAfter);
    }

    if (priceLessInput !== "") {
        let isLowerPrice = (car) => {
            return car.price < document.querySelector("#priceLessInput").value;
        };
        var data = data.filter(isLowerPrice);
    }

    if (priceMoreInput !== "") {
        let isHigherPrice = (car) => {
            return car.price > document.querySelector("#priceMoreInput").value;
        };
        var data = data.filter(isHigherPrice);
    }
    console.log("data before returning - filtering: " + data);
    return data;
}

function addEvents() {
    console.log("Adding events");
    document.querySelector("#make").addEventListener("click", getAllCars);
    document.querySelector("#year").addEventListener("click", getAllCars);
    document.querySelector("#carID").addEventListener("click", getAllCars);
    document.querySelector("#price").addEventListener("click", getAllCars);
    document.querySelector("#beforeYearInput").addEventListener("change", getAllCars);
    document.querySelector("#afterYearInput").addEventListener("change", getAllCars);
    document.querySelector("#makeInput").addEventListener("change", getAllCars);
    document.querySelector("#priceLessInput").addEventListener("change", getAllCars);
    document.querySelector("#priceMoreInput").addEventListener("change", getAllCars);
}

function insertForms() {
    CONTENTDIV.appendChild(document.createTextNode("Before year:"));
    var input = document.createElement("input");
    input.type = "text";
    input.id = "beforeYearInput";
    CONTENTDIV.appendChild(input);
    CONTENTDIV.appendChild(document.createTextNode("After year:"));
    var input = document.createElement("input");
    input.type = "text";
    input.id = "afterYearInput";
    CONTENTDIV.appendChild(input);
    CONTENTDIV.appendChild(document.createTextNode("Make:"));
    var input = document.createElement("input");
    input.type = "text";
    input.id = "makeInput";
    CONTENTDIV.appendChild(input);
    CONTENTDIV.appendChild(document.createTextNode("Price less than:"));
    var input = document.createElement("input");
    input.type = "text";
    input.id = "priceLessInput";
    CONTENTDIV.appendChild(input);
    CONTENTDIV.appendChild(document.createTextNode("Price more than:"));
    var input = document.createElement("input");
    input.type = "text";
    input.id = "priceMoreInput";
    CONTENTDIV.appendChild(input);
}

//Cars button eventlistener
document.querySelector("#carPage").addEventListener("click", insertForms);
document.querySelector("#carPage").addEventListener("click", getAllCars);


