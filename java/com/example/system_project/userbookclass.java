package com.example.system_project;

import com.google.firebase.database.Exclude;

public class userbookclass {
    private String bookname,authorname,typename,key,url,price,copy,userid;
    private  float rating;
public userbookclass(){

}
    public userbookclass(String bookname, String authorname, String typename, float rating,String url) {

        this.bookname = bookname;
        this.authorname = authorname;
        this.typename = typename;
        this.rating = rating;
        this.url=url;
    }
    public userbookclass(String bookname, String authorname, String typename, float rating,String copy,String price) {

        this.bookname = bookname;
        this.authorname = authorname;
        this.typename = typename;
        this.rating = rating;
        this.copy=copy;
        this.price=price;

    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCopy() {
        return copy;
    }

    public void setCopy(String copy) {
        this.copy = copy;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
