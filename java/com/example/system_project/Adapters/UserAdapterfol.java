package com.example.system_project.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.system_project.R;
import com.example.system_project.userbookclass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class UserAdapterfol extends ArrayAdapter<userbookclass> {
    private Activity context;
    private List<userbookclass> booklistcontri;

    public UserAdapterfol(Activity context,List<userbookclass> booklistcontri) {
        super(context, R.layout.samplelayout, booklistcontri);
        this.context = context;
        this.booklistcontri = booklistcontri;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=context.getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.samplelayout,null,true);
        userbookclass books=booklistcontri.get(position);
        TextView t1=view.findViewById(R.id.bookname1);
        TextView t2=view.findViewById(R.id.authorname1);
        ImageButton b=view.findViewById(R.id.delete);
        // ImageButton up=view.findViewById(R.id.upload);
        TextView t3=view.findViewById(R.id.type1);
        TextView t4=view.findViewById(R.id.rate1);
        // TextView t5=view.findViewById(R.id.bookpdflayer);
        t1.setText(books.getBookname());
        t2.setText(books.getAuthorname());
        t3.setText(books.getTypename());
        t4.setText(String.valueOf(books.getRating()));
        b.setVisibility(View.GONE);
        /*up.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        context.startActivity(new Intent(context.getApplicationContext(),RetreivePdf.class));
    }
});*/

        return view;
    }
}
