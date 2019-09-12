//Constants for all DOM elements im manipulating
const JOKESPAGE = document.querySelector("#jokePage");
const JOKECONTENT = document.querySelector("#content");
const JOKEBYID = document.querySelector("#jokeByIdButton");
const JOKEID = document.querySelector("#jokeId");
const H1CJOKES = document.querySelector("#h1content");
const H3CJOKES = document.querySelector("#h3content");
const JOKEBUTTONS = document.querySelector("#jokeButtons");
const RANDOMJOKE = document.querySelector("#randomJokeButton");

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


JOKESPAGE.onclick = function (e) {
    e.preventDefault();
    let url = "/CAone/api/joke/all";
    fetch(url)
            .then(res => res.json()) //in flow1, just do it
            .then(data => {
                // Inside this callback, and only here, the response data is available
                console.log("data", data);
                JOKECONTENT.innerHTML = jokesToTable(data);
                JOKEBUTTONS.style = "";
                H1CJOKES.innerHTML = "These are our jokes";
                H3CJOKES.innerHTML = " ";
                /* data now contains the response, converted to JavaScript
                 Observe the output from the log-output above
                 Now, just build your DOM changes using the data*/
            });
};

JOKEBYID.onclick = function (e) {
    e.preventDefault();
    let jokeId = JOKEID.value;
    let url = "/CAone/api/joke/" + jokeId;
    fetch(url)
            .then(res => res.json()) //in flow1, just do it
            .then(data => {
                // Inside this callback, and only here, the response data is available
                console.log("data", data);
                JOKECONTENT.innerHTML = "<p>Here is the joke with id: " + jokeId + " : " + data.joke + "</p>";
                JOKEID.value = "";
                /* data now contains the response, converted to JavaScript
                 Observe the output from the log-output above
                 Now, just build your DOM changes using the data*/
            });
};

RANDOMJOKE.onclick = function (e) {
    e.preventDefault();
    let url = "/CAone/api/joke/random";
    fetch(url)
            .then(res => res.json()) //in flow1, just do it
            .then(data => {
                console.log("data", data);
                JOKECONTENT.innerHTML = "<p>Here is a random joke: " + data.joke + "</p>";
            });
};