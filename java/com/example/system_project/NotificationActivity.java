package com.example.system_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.system_project.Fragments.NotificationFragment;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new NotificationFragment()).commit();


    }
}