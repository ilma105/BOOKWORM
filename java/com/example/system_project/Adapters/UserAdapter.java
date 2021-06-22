package com.example.system_project.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.system_project.DATA;
import com.example.system_project.Fragments.ProfileFragment;
import com.example.system_project.HomeActivity;
import com.example.system_project.ProfileActivity;
import com.example.system_project.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends  RecyclerView.Adapter<UserAdapter.ViewHolder>  {

    private Context mContext;
    private List<DATA> mUsers;
    private FirebaseUser firebaseUser;
    private  boolean isfragment;
    String userid;

    public UserAdapter(Context mContext, List<DATA> mUsers) {
        this.mContext = mContext;
        this.mUsers = mUsers;

    }

    public UserAdapter(Context mContext, List<DATA> mUsers, boolean isfragment) {
        this.mContext = mContext;
        this.mUsers = mUsers;

        this.isfragment = isfragment;

    }

    @NonNull
    @Override


    public ViewHolder onCreateViewHolder(@NonNull ViewGroup  parent, int viewType) {

View view= LayoutInflater.from(mContext).inflate(R.layout.user_item,parent,false);
        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        final DATA user=mUsers.get(position);

        holder.btn_follow.setVisibility(View.VISIBLE);
        holder.username.setText(user.getUsername());
        holder.fullname.setText(user.getName());
        Glide.with(mContext).load(user.getImageurl()).into(holder.image_profile);
       isFollowing(user.getUserId(),holder.btn_follow);
        if (user.getUserId().equals(firebaseUser.getUid())){
            holder.btn_follow.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (isfragment) {
                    SharedPreferences.Editor editor = mContext.getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit();
                    editor.putString("profileid", user.getUserId());
                    editor.apply();

                    ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();


              }
                else
                {
                    Intent intent=new Intent(mContext, ProfileActivity.class);
                    intent.putExtra("publisherid",user.getUserId());
                    mContext.startActivity(intent);
                }


            }
        });
        holder.btn_follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             if (holder.btn_follow.getText().toString().equals("follow")){
                 FirebaseDatabase.getInstance().getReference().child("Followw").child(firebaseUser.getUid())
                         .child("following").child(user.getUserId()).setValue(true);
                 FirebaseDatabase.getInstance().getReference().child("Followw").child(user.getUserId())
                         .child("followers").child(firebaseUser.getUid()).setValue(true);

            addNotifications(user.getUserId());
             }
             else
             {
                 FirebaseDatabase.getInstance().getReference().child("Followw").child(firebaseUser.getUid())
                         .child("following").child(user.getUserId()).removeValue();
                 FirebaseDatabase.getInstance().getReference().child("Followw").child(user.getUserId())
                         .child("followers").child(firebaseUser.getUid()).removeValue();
             }
            }
        });

    }
private void addNotifications(String userid){
DatabaseReference reference=FirebaseDatabase.getInstance().getReference("Notification").child(userid);

   // Time time=new Time();
    //time.setToNow();
    String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
    String mydate = java.text.DateFormat.getTimeInstance().format(Calendar.getInstance().getTime());
    HashMap<String,Object> hashMap=new HashMap<>();
    hashMap.put("userid",firebaseUser.getUid());
    hashMap.put("text","started following you "+"at"+" "+mydate);
    reference.push().setValue(hashMap);
}
    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder{
public TextView username,fullname;
public CircleImageView image_profile;
public Button btn_follow;
      public ViewHolder(@NonNull View itemView) {
          super(itemView);
          username=itemView.findViewById(R.id.username);
          fullname=itemView.findViewById(R.id.fullname);
          image_profile=itemView.findViewById(R.id.image_profile);
          btn_follow=itemView.findViewById(R.id.btn_follow);
      }
  }
private void isFollowing(String userid,Button button){
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Followw")
            .child(firebaseUser.getUid()).child("following");
    databaseReference.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
                   // String  userid=snapshot.getKey();
            if (snapshot.child(userid).exists()){
                button.setText("following");

            }
            else {
                button.setText("follow");
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });
}
}
