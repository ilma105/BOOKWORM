package com.example.system_project;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.List;

public class RecentAdapter extends ArrayAdapter<userbookclass> {
    private Activity context;
    private List<userbookclass> booklistcontri;
    public static final String MY_Pdf = "my_book.pdf";
    public RecentAdapter(Activity context,List<userbookclass> booklistcontri) {
        super(context, R.layout.recentlayout, booklistcontri);
        this.context = context;
        this.booklistcontri = booklistcontri;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater=context.getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.recentlayout,null,true);
        userbookclass books=booklistcontri.get(position);
        TextView t1=view.findViewById(R.id.booknamerecent1);

        t1.setText(books.getBookname());

t1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        String str=books.getUrl();
        Intent inte=new Intent(context,ReadingActivity.class);
        inte.putExtra("book",str);
       context.startActivity(inte);
    }
});
        t1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                        new AlertDialog.Builder(context)
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .setTitle("Do You Want To Delete?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
                                        String  profileid=firebaseUser.getUid();
                                        StorageReference ref= FirebaseStorage.getInstance().getReferenceFromUrl(books.getUrl());
                                        ref.delete();
                                        File file=new File(context.getFilesDir(),MY_Pdf);
                                        FirebaseDatabase.getInstance().getReference().child("recent").child(profileid)
                                                .child(books.getKey()).setValue(null).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                            }
                                        });

                                        if (file.exists()){

                                            context.deleteFile(MY_Pdf);
                                            Toast.makeText(context,"file deleted",Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            Toast.makeText(context,"file not found",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                })
                                .setNegativeButton("No",null)
                                .show();

return false;

            }
        });



        return view;
    }
}
