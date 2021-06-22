package com.example.system_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.system_project.Adapters.UserAdapterfol;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

public class notepad extends AppCompatActivity {
    private ListView listView;
    DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    private ImageButton text;
    private List<userbookclass> bookslists;
    private fantasy1 contributionAdapter;
    String profileid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad);
        this.setTitle("Wish list");
        mAuth=FirebaseAuth.getInstance();
        firebaseUser= mAuth.getCurrentUser();
        SharedPreferences sharedPreferences= getApplicationContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        profileid=sharedPreferences.getString("profileid","none");
        text=findViewById(R.id.up);

        listView = findViewById(R.id.listview);


        bookslists = new ArrayList<>();

        contributionAdapter = new fantasy1(notepad.this, bookslists);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(notepad.this,TextingActivity.class);
                startActivity(intent);


            }
        });

    }


    @Override
    protected void onStart() {
        // FirebaseUser firebaseUser=mAuth.getCurrentUser();

        String userid=firebaseUser.getUid();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("wish");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bookslists.clear();
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    userbookclass users1=snapshot1.getValue(userbookclass.class);
                    users1.setKey(snapshot1.getKey());
                    for (DataSnapshot snapshot2:snapshot1.getChildren()){
                        userbookclass users2=snapshot2.getValue(userbookclass.class);
                        users2.setKey(snapshot2.getKey());
                        if (!users1.getKey().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                                bookslists.add(users2);

                        }
                        listView.setAdapter(contributionAdapter);
                    }

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        DatabaseReference databaseReference1=FirebaseDatabase.getInstance().getReference("wish").child(profileid);
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userbookclass user=snapshot.getValue(userbookclass.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        super.onStart();
    }

}