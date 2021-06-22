package com.example.system_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import com.example.system_project.Adapters.UserAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FollowersActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
String id,title;
List<String> idlist;
RecyclerView recyclerView;
UserAdapter userAdapter;
List<DATA> userlist;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);
        Intent intent=getIntent();
        id=intent.getStringExtra("id");
        title=intent.getStringExtra("title");
      //  Toolbar toolbar=findViewById(R.id.toolbarfol);
      /*  setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/
recyclerView=findViewById(R.id.recycler_view);
recyclerView.setHasFixedSize(true);
recyclerView.setLayoutManager(new LinearLayoutManager(this));
userlist=new ArrayList<>();
userAdapter=new UserAdapter(this,userlist,false);
recyclerView.setAdapter(userAdapter);
idlist=new ArrayList<>();
switch (title){
    case "Following":
        getFollowing();
        this.setTitle("Following List");
        break;
    case "Followers":
        getfolllowers();
        this.setTitle("Followers List");
        break;
}

    }
    private void getFollowing(){
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("Followw")
                .child(id).child("following");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                idlist.clear();
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    idlist.add(snapshot1.getKey());
                }
                showuser();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getfolllowers(){
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("Followw")
                .child(id).child("followers");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                idlist.clear();
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    idlist.add(snapshot1.getKey());
                }
                showuser();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

private  void showuser(){
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("data");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userlist.clear();
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    DATA data=snapshot1.getValue(DATA.class);
                    data.setUserId(snapshot1.getKey());
                    for (String id:idlist)
                    {
                        if (data.getUserId().equals(id)){
                            userlist.add(data);
                        }
                    }
                }
                userAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
}
}