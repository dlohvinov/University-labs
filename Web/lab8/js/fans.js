'use strict';

let fansMainSection = document.getElementsByClassName('fans-wrap')[0];

function processFans(fansToSend) {
    if(isOnline()) {
        if (arguments.length === 0) {
            var fans = getItem('fans');
            if (fans == undefined) {
                return
            } else {
               showFans(fans);
               deleteItem('fans');
            }
        } else {
            showFans(fansToSend);
        }

    } else {
        addItem('fans', fansToSend);
    }
}

function showFans(fans) {
    fansMainSection.innerHTML += "<div class=\"item\">\n" +
        "<p><pre>" + fans.body + "</pre></p>\n" +
        "<div class=\"date_time\"><span class=\"date\">"+ fans.date +"</span>," +
        "<span class=\"time\">"+ fans.time +"</span></div>\n" +
        "<div class=\"author\">" + fans.author + "</div>\n" +
        "</div>"
}
processFans();



var fansBodyInput = document.getElementById('fans-body-input');
var fans = {};
var fansSend = document.getElementById('fans-send-input');

fansSend.onclick = function () {
    fans.body = fansBodyInput.value;
    fansBodyInput.value = null;
    fans.date = getCurrentDate();
    fans.time = getCurrentTime();
    fans.author = "Basketball fan 2000";

    if (fans.body == 0) {
        alert ("one of input fields is empty");
    } else {
        processFans(fans);
    }

};


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
