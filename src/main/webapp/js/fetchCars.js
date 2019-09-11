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
//    console.log(URL);
    fetch(URL)
            .then(res => res.json())
            .then(data => {
//                console.log("data: " + data);

                /**
                 * jsonList2Table() is in tables.js
                 * It converts a json-list into a html table
                 * arguments: a jsonlist and the tag identifier
                 */
                jsonList2Table(data, "#content");
            });
}

function sort(e){
    e.preventDefault();
        fetch(URL)
            .then(res => res.json())
            .then(data => {
//                data.sort((a, b) => parseFloat(a.price) - parseFloat(b.price));
                data.sort((a, b) => a.make.localeCompare(b.make));
                /**
                 * jsonList2Table() is in tables.js
                 * It converts a json-list into a html table
                 * arguments: a jsonlist and the tag identifier
                 */
                jsonList2Table(data, "#content");
            });
}


// Cars button eventlistener
document.querySelector("#carPage").addEventListener("click", getAllCars);
document.querySelector("#make").addEventListener("click", sort);
console.log("check");


