package com.example.system_project;

public class romantic {
    String name;
    String author;
    String descrip;
    String rating;

    // String coverImage;

    public romantic(String name,String author,String description, String rating) {
        this.name = name;
        this.rating = rating;
        this.author=author;
        this.descrip=description;
        // this.coverImage = coverImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }



    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }
}

