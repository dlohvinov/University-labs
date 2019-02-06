

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

//adding parallax to photo's
function getElementTopHeight(elem) {
    let box = elem.getBoundingClientRect();
    return box.top + pageYOffset;
}
function getElementBottomHeight(elem) {
    return getElementTopHeight(elem) + elem.offsetHeight;
}
function isInViewport(elem) {
    return pageYOffset+(document.documentElement.clientHeight*0.9) >= getElementTopHeight(elem);
}

let parallaxHistory = document.getElementsByClassName('parallax')[0];
let parallaxWin = document.getElementsByClassName('parallax')[1];
function parallaxScroll(parallax) {
    if(isInViewport(parallax)) {
        var elemTop = getElementTopHeight(parallax);
        var windowTop = pageYOffset;
        var shiftDistance = (elemTop - windowTop)*0.4;
        parallax.style.transform = "translateY("+shiftDistance+"px)";
    }
}

window.onscroll = function () {parallaxScroll(parallaxHistory),
    parallaxScroll(parallaxWin)};

//toggle news
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
