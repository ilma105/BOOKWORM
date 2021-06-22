package com.example.system_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.system_project.Adapters.FragmentsAdapter;
import com.example.system_project.Fragments.NotificationFragment;
import com.example.system_project.Fragments.SearchFragment;
import com.example.system_project.databinding.ActivityTextingBinding;

public class TextingActivity extends AppCompatActivity {

    ActivityTextingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Text here");
        binding = ActivityTextingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.viewPager.setAdapter(new FragmentsAdapter(getSupportFragmentManager()));
        binding.tabLayout.setupWithViewPager(binding.viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.setting_texting,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.settings){

            LinearLayout linearLayout=findViewById(R.id.linear2);
            linearLayout.setVisibility(View.GONE);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new NotificationFragment()).commit();

        }
        return super.onOptionsItemSelected(item);
    }
}