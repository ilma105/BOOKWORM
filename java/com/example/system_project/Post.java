package com.example.system_project;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Post {


    public String name;
    public String title;
    public String body;

    public Map<String, Boolean> stars = new HashMap<>();

    public Post() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public Post(String name, String title, String body) {

        this.name = name;
        this.title = title;
        this.body = body;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("name", name);
        result.put("title", title);
        result.put("body", body);

        result.put("stars", stars);

        return result;
    }
}
