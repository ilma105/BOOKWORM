package com.example.system_project;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class comedy1 extends ArrayAdapter<userbookclass> {
    private Activity context;
    private List<userbookclass> booklistcontri;

    public comedy1(Activity context,List<userbookclass> booklistcontri) {
        super(context, R.layout.wishsample, booklistcontri);
        this.context = context;
        this.booklistcontri = booklistcontri;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=context.getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.wishsample,null,true);
        userbookclass books=booklistcontri.get(position);
        TextView t1=view.findViewById(R.id.bookname1);
        TextView t2=view.findViewById(R.id.authorname1);
        TextView t3=view.findViewById(R.id.username1);
        ImageButton b=view.findViewById(R.id.delete);
        // ImageButton up=view.findViewById(R.id.upload);

        // TextView t5=view.findViewById(R.id.bookpdflayer);
        t1.setText(books.getBookname());
        t2.setText(books.getAuthorname());
t3.setVisibility(View.GONE);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
                String  profileid=firebaseUser.getUid();

                FirebaseDatabase.getInstance().getReference().child("wish").child(profileid)
                        .child(books.getKey()).setValue(null).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
            }
        });
/*up.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        context.startActivity(new Intent(context.getApplicationContext(),RetreivePdf.class));
    }
});*/

        return view;
    }
}
