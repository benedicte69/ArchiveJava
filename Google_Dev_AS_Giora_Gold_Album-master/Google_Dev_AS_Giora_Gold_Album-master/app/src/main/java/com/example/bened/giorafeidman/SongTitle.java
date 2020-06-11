package com.example.bened.giorafeidman;


public class SongTitle {

    //fields
    private String mTitle;
    private String mAlbum;
    private String mDate;
    private String mVideo;

    //constructor
    public SongTitle(String title, String album, String date, String video) {
        mTitle = title;
        mAlbum = album;
        mDate = date;
        mVideo = video;
    }

    //getters
    public String getTitle() {
        return mTitle;
    }

    public String getAlbum() {
        return mAlbum;
    }

    public String getDate() {
        return mDate;
    }

    public String getVideo() {
        return mVideo;
    }

}
