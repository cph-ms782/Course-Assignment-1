//Constants for all DOM elements im manipulating
const RELOAD = document.querySelector("#reload");
const CONTENT = document.querySelector("#content");

/**
 * Method for converting an array of GroupMembers to a HTML string
 * @param {type} groupMembers
 * @returns {unresolved}
 * 
 * Frederik
 */
function groupMembersToTable(groupMembers) {
    var tableinfo = groupMembers.map(x => "<tr><td>" + x.name + "</td><td>" + x.studentID + "</td><td>" + x.color + "</td></tr>");
    tableinfo.unshift("<table class=\"table\"><tr><th>Name</th><th>Student ID</th><th>Color</th></tr>");
    tableinfo.push("</table>");
    return tableinfo.join('');
}

RELOAD.onclick = function (e) {
    e.preventDefault();
    let url = "https://www.sandersolutions.dk/CAone/api/groupmembers/all";
    fetch(url)
            .then(res => res.json()) //in flow1, just do it
            .then(data => {
                // Inside this callback, and only here, the response data is available
                console.log("data", data);
                CONTENT.innerHTML = groupMembersToTable(data);
                /* data now contains the response, converted to JavaScript
                 Observe the output from the log-output above
                 Now, just build your DOM changes using the data*/
            })
}


