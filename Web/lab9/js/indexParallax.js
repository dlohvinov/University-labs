'use strict';
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