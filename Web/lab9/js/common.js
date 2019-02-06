

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


//nav toggle
let burger = document.getElementsByClassName('burger')[0];
let menu = document.getElementsByClassName('menu')[0];
burger.onclick = function () {
    menu.classList.toggle('hidden');
};

let matchesToggle = document.getElementsByClassName('matches-toggle')[0];
matchesToggle.onclick = function () {
    matchesToggle.classList.toggle('opened')
};

// toggle news
let newsMore = document.getElementsByClassName('more');
let newsItem = document.getElementsByClassName('item');
function toggleNews(item) {
    if (newsItem[item].classList.contains('opened')) {
        newsItem[item].classList.remove('opened');
        newsMore[item].innerHTML = "More..";
    } else {
        newsItem[item].classList.add('opened');
        newsMore[item].innerHTML = "Less..";
    }
}
for (let i = 0; i < newsMore.length; i++) {
    newsMore[i].addEventListener('click', function(){toggleNews(i)}, false);
}



function isOnline() {
    return window.navigator.onLine;
}

// window.addEventListener('load', function () {
//     function updateOnlineStatus(event) {
//         if(event.type === 'online') {
//             alert('online');
//         } else {
//             alert("offline")
//         }
//     }
//
//     window.addEventListener('online',  updateOnlineStatus);
//     window.addEventListener('offline', updateOnlineStatus);
// });
//

