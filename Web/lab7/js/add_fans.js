'use strict';


function getCurrentDate() {
    let today = new Date();
    let dd = today.getDate();
    let mm = today.getMonth()+1; //January is 0
    let yyyy = today.getFullYear();

    if(dd<10) {dd = '0'+dd}
    if(mm<10) {mm = '0'+mm}

    return today = mm + '.' + dd + '.' + yyyy;
}
function getCurrentTime() {
    let today = new Date();
    let hours = today.getHours();
    let minutes = today.getMinutes();

    return today =hours + ':' + minutes;
}

function add_fans(fansBody) {
    let fansMainSection = document.getElementsByClassName('fans-wrap')[0];
    fansMainSection.innerHTML += "<div class=\"item\">\n" +
        "<p>" + fansBody + "</p>\n" +
        "<div class=\"date_time\"><span class=\"date\">"+ getCurrentDate() +"</span>," +
        "<span class=\"time\">"+ getCurrentTime() +"</span></div>\n" +
        "<div class=\"author\">Basketball fan 2000</div>\n" +
        "</div>"
}


var fansBodyInput = document.getElementById('fans-body-input');
var fansBody;
var fansSend = document.getElementById('fans-send-input');

fansSend.onclick = function () {
    fansBody = fansBodyInput.value;
    fansBodyInput.value = null;

    if (fansBody == 0) {
        alert ("one of input fields is empty");
    } else {
        add_fans(fansBody);
    }

};
