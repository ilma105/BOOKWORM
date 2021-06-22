package com.example.system_project.Fragments;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.system_project.Adapters.NotificationAdapter;
import com.example.system_project.Adapters.NotificationSAdapter;
import com.example.system_project.NotifucationClass;
import com.example.system_project.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.system_project.App.CHANNEL_2_ID;


public class NotificationFragment extends Fragment {
private RecyclerView recyclerView;
private NotificationAdapter notificationAdapter;
private NotificationSAdapter notificationSAdapter;
private List<NotifucationClass> notifucationClassList;
String  profileid;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
               View view= inflater.inflate(R.layout.fragment_notification, container, false);
recyclerView=view.findViewById(R.id.recycle_view);


       SharedPreferences sharedPreferences= getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        profileid=sharedPreferences.getString("profileid","none");


recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
notifucationClassList=new ArrayList<>();
notificationAdapter=new NotificationAdapter(getContext(),notifucationClassList);

recyclerView.setAdapter(notificationAdapter);

readNotification();
//read();
   return  view;
    }

    private void readNotification() {
        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Notification").child(firebaseUser.getUid());
      //  DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Notification").child(profileid);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                notifucationClassList.clear();
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    NotifucationClass notifucationClass=snapshot1.getValue(NotifucationClass.class);
                    notifucationClassList.add(notifucationClass);
                }
                Collections.reverse(notifucationClassList);
                notificationAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }




}