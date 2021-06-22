package com.example.system_project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class DownLoadAdapter extends ArrayAdapter<DownloadClass> {
    private Activity context;
    private List<DownloadClass> booklistcontri;

    public DownLoadAdapter(Activity context,List<DownloadClass> booklistcontri) {
        super(context, R.layout.samplelayout1, booklistcontri);
        this.context = context;
        this.booklistcontri = booklistcontri;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=context.getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.samplelayout1,null,true);
        DownloadClass books=booklistcontri.get(position);
        ImageView imageButton=view.findViewById(R.id.bookimg);
        TextView t1=view.findViewById(R.id.booknamedown1);
        TextView t2=view.findViewById(R.id.authornamedown1);
        ImageButton b=view.findViewById(R.id.delete);
        // ImageButton up=view.findViewById(R.id.upload);
        TextView t3=view.findViewById(R.id.typedown1);
        TextView t4=view.findViewById(R.id.ratedown1);
        // TextView t5=view.findViewById(R.id.bookpdflayer);
        imageButton.setImageResource(books.getImageButton());
        t1.setText(books.getBookname());
        t2.setText(books.getAuthorname());
        t3.setText(books.getTypename());
        t4.setText(books.getRating());
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(books.getUrl())));
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
                String  profileid=firebaseUser.getUid();

                FirebaseDatabase.getInstance().getReference().child("download").child(profileid)
                        .child(books.getKey()).setValue(null).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
            }
        });


        return view;
    }
}
