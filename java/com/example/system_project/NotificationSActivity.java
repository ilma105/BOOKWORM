package com.example.system_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.system_project.Fragments.NotificationFragment;
import com.example.system_project.Fragments.NotificationsFragment;

public class NotificationSActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_s);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new NotificationsFragment()).commit();


    }
}