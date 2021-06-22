package com.example.system_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class FirstMostActivity extends AppCompatActivity {

    TextView txt_animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_most);

        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        txt_animation = findViewById(R.id.txt_animation);
        startAnimation();
        Thread td = new Thread(){

            public void run(){
                try {
                    sleep(5000);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                finally {
                    Intent intent = new Intent(FirstMostActivity.this,MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_from_top,R.anim.slide_2_bottom);
                    finish();
                }
            }
        };
        td.start();
    }

    private void startAnimation() {

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.rotate);
        txt_animation.startAnimation(animation);
    }
}