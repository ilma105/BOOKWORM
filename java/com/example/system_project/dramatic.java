package com.example.system_project;

public class dramatic{
    String name;
    String rating;
    String descrip;
    String author;

    // String coverImage;

    public dramatic(String name,String author,String description, String rating) {
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

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

