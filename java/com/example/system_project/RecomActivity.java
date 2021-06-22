package com.example.system_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.system_project.Adapters.UserAdapterfol;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class RecomActivity extends AppCompatActivity {
    private ListView listView;
    DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    private List<userbookclass> bookslists;
    private UserAdapterfol contributionAdapter;
    String profileid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recom);
        this.setTitle("Recommendation list");
        mAuth=FirebaseAuth.getInstance();
        firebaseUser= mAuth.getCurrentUser();
        SharedPreferences sharedPreferences= getApplicationContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        profileid=sharedPreferences.getString("profileid","none");

        listView = findViewById(R.id.listview);


        bookslists = new ArrayList<>();
        Button up=findViewById(R.id.upload);
        contributionAdapter = new UserAdapterfol(RecomActivity.this, bookslists);
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RetrivePdf1.class));
            }
        });

    }


    @Override
    protected void onStart() {

        String userid=firebaseUser.getUid();
        databaseReference=FirebaseDatabase.getInstance().getReference().child("contribution");

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
                            if (users2.getRating() > 3.9) {
                                bookslists.add(users2);
                            }
                        }


                        listView.setAdapter(contributionAdapter);
                    }

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        super.onStart();
    }
}