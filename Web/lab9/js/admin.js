

let newsTitleInput = document.getElementById('news-title-input');
let newsTitle;
let newsDescriptionInput = document.getElementById('news-description-input');
let newsDescription;
let newsBodyInput = document.getElementById('news-body-input');
let newsBody;
let newsSend = document.getElementById('news-send-input');
let news = {};


function newsToDB(newsToSend) {
    if(isOnline()) {

    } else {
        addItem('news', newsToSend);
    }

}

newsSend.onclick = function () {
    newsTitle = newsTitleInput.value;
    // newsTitleInput.value = null;
    newsDescription = newsDescriptionInput.value;
    // newsDescriptionInput.value = null;
    newsBody = newsBodyInput.value;
    // newsBodyInput.value = null;

    if (newsTitle == 0) {
        newsTitleInput.classList.add('blank');
    } else {
        newsTitleInput.classList.remove('blank');
    }
    if (newsDescription == 0) {
        newsDescriptionInput.classList.add('blank');
    } else {
        newsDescriptionInput.classList.remove('blank');
    }
    if (newsBody == 0) {
        newsBodyInput.classList.add('blank');
    } else {
        newsBodyInput.classList.remove('blank');
    }
    if (!(newsTitle == 0) && !(newsDescription== 0) && !(newsBody == 0))  {
        newsTitleInput.value = null;
        newsDescriptionInput.value = null;
        newsBodyInput.value = null;
    }

    news.title =  "" + newsTitle;
    news.description =   "" + newsDescription;
    news.body =  "" + newsBody;


    newsToDB(news);
// Put the object into storage
//     InitLS.setItem('news', JSON.stringify(news));

};




