let newsMainSection = document.getElementsByClassName('news-wrap')[0];

function processNews() {
    if (isOnline()) {
        var news = getItem('news');
        if (news == undefined) {
            return
        } else {
            showNews(news);
            deleteItem('news');
        }
    }

}

processNews();

function showNews(news) {
    newsMainSection.innerHTML += "<div class=\"item\">\n" +
        "<div class=\"pic\">\n" +
        "<img src=\"img/news_item.jpg\" alt=\"Oops..\">\n" +
        "</div>\n" +
        "<div class=\"txt\">\n" +
        "<h3>" + news.title + "</h3>\n" +
        "<h4>" + news.description + "</h4>\n" +
        "<p>" + news.body + "</p>\n" +
        "</div>\n" +
        "<button class=\"more\">More..</button>\n" +
        "</div>"
}

