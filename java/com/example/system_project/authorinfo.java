package com.example.system_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

public class authorinfo extends AppCompatActivity {
    ImageButton mail,sendmegh;
    ImageView homebtn;
    BoomMenuButton bmb;
    TextView email,emailmegh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorinfo);
        homebtn = (ImageView)  findViewById(R.id.refresh);
        bmb = (BoomMenuButton) findViewById(R.id.boombtnID);
        bmb.setButtonEnum(ButtonEnum.Ham);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_4_1);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.HAM_4);
        mail=(ImageButton)findViewById(R.id.send);
        sendmegh=(ImageButton)findViewById(R.id.sendmegh);
        email=(TextView)findViewById(R.id.email);
        emailmegh=(TextView)findViewById(R.id.emailmegh);
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=email.getText().toString();

                Intent inte=new Intent(authorinfo.this,sendmail.class);
                inte.putExtra("email",str);
                startActivity(inte);
            }
        });
        sendmegh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=emailmegh.getText().toString();
                Intent inte=new Intent(authorinfo.this,sendmail.class);
                inte.putExtra("email",str);
                startActivity(inte);
            }
        });
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),authorinfo.class);
                startActivity(intent);
                finish();

            }
        });

        for(int i=0;i < bmb.getPiecePlaceEnum().pieceNumber(); i++){
            int position = i;
            if(i==0){
                HamButton.Builder builder = new HamButton.Builder()
                        .normalImageRes(R.drawable.user)
                        .normalText("Profile").subNormalText("My world")
                        .listener(new OnBMClickListener() {
                            @Override
                            public void onBoomButtonClick(int index) {
                                Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
                                startActivity(intent);
                                Toast.makeText(authorinfo.this,"Wohhoo",Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                bmb.addBuilder(builder);
            }else if (i==1){
                HamButton.Builder builder = new HamButton.Builder()
                        .normalImageRes(R.drawable.search)
                        .normalText("Search User").subNormalText("Other Users")
                        .listener(new OnBMClickListener() {
                            @Override
                            public void onBoomButtonClick(int index) {
                                Intent intent = new Intent(getApplicationContext(),Search.class);
                                startActivity(intent);
                                Toast.makeText(authorinfo.this,"Connect with other users",Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                bmb.addBuilder(builder);
            }else if (i==2){
                HamButton.Builder builder = new HamButton.Builder()
                        .normalImageRes(R.drawable.notes)
                        .normalText("Notes").subNormalText("Write down notes")
                        .listener(new OnBMClickListener() {
                            @Override
                            public void onBoomButtonClick(int index) {
                                Intent intent = new Intent(getApplicationContext(),NotesActivity.class);
                                startActivity(intent);
                                Toast.makeText(authorinfo.this,"Note down",Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                bmb.addBuilder(builder);
            }else if (i==3){
                HamButton.Builder builder = new HamButton.Builder()
                        .normalImageRes(R.drawable.bright)
                        .normalText("Wish List").subNormalText("Wanna see others wishes")
                        .listener(new OnBMClickListener() {
                            @Override
                            public void onBoomButtonClick(int index) {
                                Intent intent = new Intent(getApplicationContext(),notepad.class);
                                startActivity(intent);
                                Toast.makeText(authorinfo.this,"Others wishes",Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                bmb.addBuilder(builder);
            }

        }
    }
}