
 function addNews(title, description, body) {
        let newsMainSection = document.getElementsByClassName('news-wrap')[0];
        newsMainSection.innerHTML += "<div class=\"item\">\n" +
            "<div class=\"pic\">\n" +
            "<img src=\"img/news_item.jpg\" alt=\"Oops..\">\n" +
            "</div>\n" +
            "<div class=\"txt\">\n" +
            "<h3>" + title + "</h3>\n" +
            "<h4>" + description + "</h4>\n" +
            "<p>" + body + "</p>\n" +
            "</div>\n" +
            "<button class=\"more\">More..</button>\n" +
            "</div>"
    }

 // Retrieve the object from storage
     var retrievedNews = localStorage.getItem('news');
    retrievedNews = JSON.parse(retrievedNews);
    // console.log('news: ', JSON.parse(retrievedNews));

    addNews(retrievedNews.title, retrievedNews.description, retrievedNews.body);

