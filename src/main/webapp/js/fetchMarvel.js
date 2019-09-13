/**
 * table utils
 */

//constants
const MARVELURL = "/CAone/api/marvel/all";
const MARVELCONTENTDIV = document.querySelector("#content");
/**
 * Making table of all marvel movies. 
 */
function getAllMarvel(e) {
    e.preventDefault();
    console.log(e.target.innerText);

    fetch(MARVELURL)
            .then(res => res.json())
            .then(data => {
                /**
                 * jsonList2Table() is in tables.js
                 * It converts a json-list into a html table
                 * arguments: a jsonlist and the tag identifier
                 */
                    jsonList2Table(data, "#content");

                //For small screens
                MARVELCONTENTDIV.classList.add("table-responsive");
            });
}


/**
 * inserting forms and text on page
 */
function insertForms() {

    document.querySelector("#h1content").innerHTML = "Marvel Movies";
    document.querySelector("#h3content").innerHTML = "";
    document.querySelector("#jokeButtons").style = "display: none;";
    
    // making div to contain all ( for CSS )
    var inputDiv = document.createElement("DIV");
    inputDiv.id="inputDiv";
    MARVELCONTENTDIV.appendChild(inputDiv);
    
    // inserting text and input fields ( named for CSS )
    inputDiv.appendChild(document.createTextNode("After year:"));
    var input = document.createElement("input");
    input.type = "text";
    input.id = "afterYearInput";
    inputDiv.appendChild(input);
    
    inputDiv.appendChild(document.createTextNode("Before year:"));
    var input = document.createElement("input");
    input.type = "text";
    input.id = "beforeYearInput";
    inputDiv.appendChild(input);
    
    inputDiv.appendChild(document.createTextNode("Make:"));
    var input = document.createElement("input");
    input.type = "text";
    input.id = "makeInput";
    inputDiv.appendChild(input);
    
    inputDiv.appendChild(document.createTextNode("Price more than:"));
    var input = document.createElement("input");
    input.type = "text";
    input.id = "priceMoreInput";
    inputDiv.appendChild(input);
    
    inputDiv.appendChild(document.createTextNode("Price less than:"));
    var input = document.createElement("input");
    input.type = "text";
    input.id = "priceLessInput";
    inputDiv.appendChild(input);
}

//Cars button eventlistener
document.querySelector("#marvelPage").addEventListener("click", getAllMarvel);


