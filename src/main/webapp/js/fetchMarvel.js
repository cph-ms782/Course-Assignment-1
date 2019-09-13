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
    

}

//Cars button eventlistener
document.querySelector("#marvelPage").addEventListener("click", insertForms);
document.querySelector("#marvelPage").addEventListener("click", getAllMarvel);


