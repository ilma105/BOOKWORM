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

public class DownloadList extends AppCompatActivity {
    private ListView listView;
    DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    String profileid;
    private List<DownloadClass> downlist;
    private  DownLoadAdapter downLoadAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_list);
        this.setTitle("Visited Pdf List");
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();

       profileid=firebaseUser.getUid();
        mAuth=FirebaseAuth.getInstance();
        listView = findViewById(R.id.listviewdown);

        downlist = new ArrayList<>();
        downLoadAdapter = new DownLoadAdapter(DownloadList.this, downlist);


    }
    @Override
    protected void onStart() {
        FirebaseUser firebaseUser=mAuth.getCurrentUser();
        String userid=firebaseUser.getUid();
        databaseReference=FirebaseDatabase.getInstance().getReference().child("download").child(profileid);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                downlist.clear();

                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    DownloadClass users=snapshot1.getValue(DownloadClass.class);
                    users.setKey(snapshot1.getKey());
                    downlist.add(users);
                }

                listView.setAdapter(downLoadAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        super.onStart();
    }
}