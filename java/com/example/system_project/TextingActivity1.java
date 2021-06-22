package com.example.system_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.system_project.Adapters.FragmentAdapter1;
import com.example.system_project.Adapters.FragmentsAdapter;
import com.example.system_project.databinding.ActivityTextingBinding;

public class TextingActivity1 extends AppCompatActivity {

    ActivityTextingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Text here");
        binding = ActivityTextingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.viewPager.setAdapter(new FragmentAdapter1(getSupportFragmentManager()));
        binding.tabLayout.setupWithViewPager(binding.viewPager);

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.settings){
            Toast.makeText(TextingActivity1.this,"Settings",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(),SettingsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}