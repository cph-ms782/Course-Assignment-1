/**
 * Making table of all cars
 */
function getAllCars(e) {
    e.preventDefault();
    let url = "/CAone/api/cars/all";
//    console.log(url);
    fetch(url)
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

// Cars button eventlistener
document.querySelector("#carPage").addEventListener("click", getAllCars);
console.log("check");


