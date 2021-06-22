package com.example.system_project;

import android.widget.ImageButton;

import com.google.firebase.database.Exclude;

public class DownloadClass {
    int imageButton;
    private String bookname,authorname,rating,key,typename,url;
    public DownloadClass(){

    }
    public DownloadClass(int imageButton,String bookname, String authorname,String typename,String rating,String url) {
        this.imageButton=imageButton;
        this.bookname = bookname;
        this.authorname = authorname;
        this.typename = typename;
        this.rating = rating;
        this.url=url;
       // this.url=url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getImageButton() {
        return imageButton;
    }

    public void setImageButton(int imageButton) {
        this.imageButton = imageButton;
    }

    @Exclude
    public String getKey() {
        return key;
    }
    @Exclude
    public void setKey(String key) {
        this.key = key;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
