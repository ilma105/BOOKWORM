package com.example.system_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RetrievePdffol extends AppCompatActivity {
    ListView listView;
    DatabaseReference databaseReference;
    List<userbookclass> uploadpdf;
    FirebaseAuth mAuth;
    String  profileid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retreive_pdf);
        SharedPreferences sharedPreferences= getApplicationContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        profileid=sharedPreferences.getString("profileid","none");
        mAuth=FirebaseAuth.getInstance();
        listView=findViewById(R.id.listpdf);
        uploadpdf=new ArrayList<>();
        retrivepdffile();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                userbookclass bookclass=uploadpdf.get(position);
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setType("application/pdf");
                intent.setData(Uri.parse(bookclass.getUrl()));
                startActivity(intent);
            }
        });

    }

    private void retrivepdffile() {
        FirebaseUser firebaseUser=mAuth.getCurrentUser();

        String userid=firebaseUser.getUid();
        databaseReference=FirebaseDatabase.getInstance().getReference().child("contribution").child(profileid);

        // databaseReference= FirebaseDatabase.getInstance().getReference("contribution");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds:snapshot.getChildren()){
                    userbookclass book=ds.getValue(userbookclass.class);
                    uploadpdf.add(book);
                }
                String [] uploadname=new String[uploadpdf.size()];
                for(int i=0;i<uploadname.length;i++)
                {
                    uploadname[i]=uploadpdf.get(i).getBookname();
                }
                ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_list_item_1,uploadname){
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View view= super.getView(position, convertView, parent);
                        TextView textView=(TextView)view.findViewById(android.R.id.text1);

                        textView.setTextColor(Color.GREEN);
                        textView.setTextSize(20);
                        return view;

                    }
                };
                listView.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}