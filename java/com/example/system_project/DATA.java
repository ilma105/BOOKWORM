package com.example.system_project;

import android.icu.lang.UProperty;

import java.util.HashMap;
import java.util.Map;

public class DATA {
    private String name,username,age,nationality,bio,imageurl,email,userId,lastMessage,profilePic;

    public DATA()
    {

    }
  /*  public DATA(String name, String username, String age, String nationality,String bio,String imageurl,String email ) {
        this.name = name;
        this.username = username;
        this.age = age;
        this.nationality = nationality;
       this.bio=bio;
       this.imageurl=imageurl;
       this.email=email;
    }*/
    public DATA(String name, String username, String age, String nationality, String bio, String imageurl, String email, String profilePic, String userId, String lastMessage) {
        this.name = name;
        this.username = username;
        this.age = age;
        this.nationality = nationality;
        this.bio = bio;
        this.imageurl = imageurl;
        this.email = email;
        this.userId = userId;
        this.profilePic=profilePic;
        this.lastMessage = lastMessage;

    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
}


