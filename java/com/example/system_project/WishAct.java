package com.example.system_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class WishAct extends AppCompatActivity {

    private ListView listView;
    DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    FirebaseUser firebaseUser;

    private List<userbookclass> bookslists;
    private  comedy1 contributionAdapter;
    String profileid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish);
        this.setTitle("Wishlist");
        mAuth=FirebaseAuth.getInstance();
        firebaseUser= mAuth.getCurrentUser();

        profileid=firebaseUser.getUid();
        listView = findViewById(R.id.listview);

        bookslists = new ArrayList<>();
        contributionAdapter = new comedy1(WishAct.this, bookslists);

    }


    @Override
    protected void onStart() {
        // FirebaseUser firebaseUser=mAuth.getCurrentUser();

        String userid=firebaseUser.getUid();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("wish").child(profileid);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bookslists.clear();

                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    userbookclass users=snapshot1.getValue(userbookclass.class);
                    users.setKey(snapshot1.getKey());
                    bookslists.add(users);
                }

                listView.setAdapter(contributionAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        super.onStart();
    }
}