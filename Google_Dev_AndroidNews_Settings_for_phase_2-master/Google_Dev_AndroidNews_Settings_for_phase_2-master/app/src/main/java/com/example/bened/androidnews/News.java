package com.example.bened.androidnews;

class News {

    /*Title of the news*/
    private final String headline;
    /*Section of the news*/
    private final String section;
    /*Author of the news*/
    private final String author;
    /*Date of publication*/
    private final String datePublication;

    /* Website URL of the news*/
    private final String webSiteUrl;

    /**
     * Constructor
     *
     * @param headline        The title of the article
     * @param section         The category it belongs to
     * @param author          The author of the article
     * @param datePublication The web publication date of the article
     * @param webSiteUrl      The url to the full content of the article
     */
    public News(String headline, String section, String author, String datePublication, String webSiteUrl) {
        this.headline = headline;
        this.section = section;
        this.author = author;
        this.datePublication = datePublication;
        this.webSiteUrl = webSiteUrl;
    }

    /**
     * Setters
     */
    public String getHeadline() {
        return headline;
    }

    public String getSection() {
        return section;
    }

    public String getAuthor() {
        return author;
    }

    public String getDatePublication() {
        return datePublication;
    }

    public String getWebSiteUrl() {
        return webSiteUrl;
    }
}
