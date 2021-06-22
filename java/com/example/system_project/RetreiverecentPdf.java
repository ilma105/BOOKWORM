package com.example.system_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class RetreiverecentPdf extends AppCompatActivity {
      //private List<userbookclass> bookslists;
    private  RecentAdapter recentAdapter;
   private ListView listView;
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;
   private  List<userbookclass> uploadpdf;
    public static final String MY_Pdf = "my_book.pdf";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retreiverecent_pdf);
        this.setTitle("Recent Book");
        mAuth=FirebaseAuth.getInstance();

        listView=findViewById(R.id.listpdf);
        uploadpdf=new ArrayList<>();

        recentAdapter = new RecentAdapter(RetreiverecentPdf.this, uploadpdf);

    }
@Override
    protected void onStart() {
    FirebaseUser firebaseUser=mAuth.getCurrentUser();

    String userid=firebaseUser.getUid();
    databaseReference=FirebaseDatabase.getInstance().getReference().child("recent").child(userid);

    File file=new File(RetreiverecentPdf.this.getFilesDir(),MY_Pdf);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
             uploadpdf.clear();
                for (DataSnapshot ds:snapshot.getChildren()){
                    userbookclass book=ds.getValue(userbookclass.class);
                    book.setKey(ds.getKey());
                    uploadpdf.add(book);

                }

                listView.setAdapter(recentAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    super.onStart();
    }
}