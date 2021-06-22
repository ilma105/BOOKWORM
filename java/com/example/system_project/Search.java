package com.example.system_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.system_project.Fragments.NotificationFragment;
import com.example.system_project.Fragments.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Search extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Fragment searchfragment=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new SearchFragment()).commit();


    }


}