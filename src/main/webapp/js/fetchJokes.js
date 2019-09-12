//Constants for all DOM elements im manipulating
const JOKESPAGE = document.querySelector("#jokePage");
const JOKECONTENT = document.querySelector("#content");
const JOKEBYID = document.querySelector("#jokeByIdButton");
const JOKEID = document.querySelector("#jokeId");

/**
 * 
 * @param {type} jokes
 * @returns {unresolved}
 */
function jokesToTable(jokes) {
    var tableContent = jokes.map(j => "<tr><td>" + j.joke + "</td><td>" + j.jokeID + "</td><td>" + j.jokeType + "</td><td>" + j.reference + "</td></tr>");

    tableContent.unshift("<table class=\"table\" border=\"1\"><tr><th>Joke</th><th>JokeID</th><th>Type</th><th>Reference</th></tr>");
    tableContent.push("</table>");
    return tableContent.join('');
};

function jokeByIdButton(){
    return "<form> Joke ID: <input type=\"text\" id=\"jokeId\"> <input type=\"submit\" id=\"jokeByIdButton\" value=\"Get a joke\"></form>"
}


JOKESPAGE.onclick = function (e) {
    e.preventDefault();
    let url = "/CAone/api/joke/all";
    fetch(url)
            .then(res => res.json()) //in flow1, just do it
            .then(data => {
                // Inside this callback, and only here, the response data is available
                console.log("data", data);
                JOKECONTENT.innerHTML = jokesToTable(data);
                JOKECONTENT.innerHTML += jokeByIdButton();
                /* data now contains the response, converted to JavaScript
                 Observe the output from the log-output above
                 Now, just build your DOM changes using the data*/
            });
};

JOKEBYID.onclick = function(e) {
    e.preventDefault();
    let jokeId = JOKEID.value;
    let url = "/CAone/api/joke/" + jokeId;
    fetch(url)
            .then(res => res.json()) //in flow1, just do it
            .then(data => {
                // Inside this callback, and only here, the response data is available
                console.log("data", data);
                JOKECONTENT.innerHTML = "<p>" + data.joke + "</p>";
                JOKECONTENT.innerHTML += jokeByIdButton();
                /* data now contains the response, converted to JavaScript
                 Observe the output from the log-output above
                 Now, just build your DOM changes using the data*/
            });
}