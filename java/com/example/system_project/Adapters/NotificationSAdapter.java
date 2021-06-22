package com.example.system_project.Adapters;

import android.app.Notification;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.system_project.DATA;
import com.example.system_project.Fragments.ProfileFragment;
import com.example.system_project.NotifucationClass;
import com.example.system_project.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
;import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.system_project.App.CHANNEL_2_ID;

public class NotificationSAdapter extends  RecyclerView.Adapter<NotificationSAdapter.ViewHolder>{
    private Context mContext;
    private List<NotifucationClass> mNotification;

    public NotificationSAdapter(Context mContext, List<NotifucationClass> mNotification) {
        this.mContext = mContext;
        this.mNotification = mNotification;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.notification_item,parent,false);
        return new NotificationSAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NotifucationClass notification=mNotification.get(position);
        holder.text.setText(notification.getText());
        getUserinfo(holder.image_profile,holder.username,notification.getUserid(),notification.getText());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = mContext.getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit();
                editor.putString("profileid", notification.getUserid());
                editor.apply();

                ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();


            }

        });
       /* int notificationId=1;
        Notification builder = new NotificationCompat.Builder(mContext,CHANNEL_2_ID)
                .setSmallIcon(R.drawable.notify)
                .setContentTitle("arrived")
                .setContentText(notification.getText())
                .setPriority(NotificationCompat.PRIORITY_HIGH).
                        setCategory(NotificationCompat.CATEGORY_MESSAGE).build();
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(mContext);

// notificationId is a unique int for each notification that you must define
        notificationManager.notify(notificationId, builder);*/
    }

    @Override
    public int getItemCount() {
        return mNotification.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder{
        public CircleImageView image_profile;
        public TextView username,text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_profile=itemView.findViewById(R.id.image_profile);
            username=itemView.findViewById(R.id.userName);
            text=itemView.findViewById(R.id.comment);
        }
    }
    private  void getUserinfo(ImageView imageView,TextView username,String  publisherid,String text){
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("data").child(publisherid);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //DataSnapshot snapshot1= (DataSnapshot) snapshot.getChildren();
                DATA user=snapshot.getValue(DATA.class);
                Glide.with(mContext).load(user.getImageurl()).into(imageView);
                username.setText(user.getUsername());
                int notificationId=1;
                Notification builder = new NotificationCompat.Builder(mContext,CHANNEL_2_ID)
                        .setSmallIcon(R.drawable.notime)
                        .setContentTitle("arrived")
                        .setContentText(user.getUsername()+" "+text)
                        .setPriority(NotificationCompat.PRIORITY_HIGH).
                                setCategory(NotificationCompat.CATEGORY_MESSAGE).build();
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(mContext);

// notificationId is a unique int for each notification that you must define
                notificationManager.notify(notificationId, builder);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
