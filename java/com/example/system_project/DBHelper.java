package com.example.system_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBHelper{
    int followers;
    int folllowing;

    public DBHelper(int followers) {

        this.followers = followers;
      //  this.folllowing = folllowing;

    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

   // public int getFolllowing() {
     //   return folllowing;
    //}

    //public void setFolllowing(int folllowing) {
      //  this.folllowing = folllowing;
    //}
}
