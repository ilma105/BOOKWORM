package com.example.system_project;

import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import static com.nightonke.boommenu.ButtonEnum.*;

public class WishList extends AppCompatActivity {

    EditText booknamedit,authoredit,usernameed;

    Button save,load;
    BoomMenuButton bmb;
    ImageButton textingpro;

    DatabaseReference databaseReference;

    private FirebaseAuth mAuth;
    FirebaseDatabase database;

    String profileid;
    FirebaseUser firebaseUser;
    Float rate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);

        this.setTitle("Wish");
        //  databaseReference=FirebaseDatabase.getInstance().getReference("contribution");
        SharedPreferences sharedPreferences= getApplicationContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        profileid=sharedPreferences.getString("profileid","none");
        mAuth = FirebaseAuth.getInstance();
        usernameed=findViewById(R.id.usnameedit);
        booknamedit=findViewById(R.id.booknameedit);
        authoredit=findViewById(R.id.authornameedit);
        load=findViewById(R.id.loadcontri);
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();

        save=findViewById(R.id.saveContributionID);

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WishList.this,WishAct.class);
                startActivity(intent);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveinfo(usernameed.getText().toString(),booknamedit.getText().toString(), authoredit.getText().toString());

            }
        });
    }







    private void saveinfo(String username,String book, String author) {
        if (username.equals("")||book.equals("") ||author.equals("")) {
            Toast.makeText(WishList.this, "enter all the fields", Toast.LENGTH_SHORT).show();

        }
        else {
            FirebaseUser firebaseUser = mAuth.getCurrentUser();

            String userid = firebaseUser.getUid();
            databaseReference = FirebaseDatabase.getInstance().getReference().child("wish").child(userid);

            String key = databaseReference.push().getKey();


float rate1=0;
String  copy="";
String price="";
            userbookclass user = new userbookclass(book, author, username, rate1,copy,price);
            databaseReference.child(key).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                    addnotification();

                }

            });

            Toast.makeText(WishList.this, "saved", Toast.LENGTH_SHORT).show();

        }

    }
    private void addnotification(){
         FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        //  Time time=new Time();
        //time.setToNow();
        String currentTime = new SimpleDateFormat("HH:mm:ss:a", Locale.getDefault()).format(new Date());
        String mydate = java.text.DateFormat.getTimeInstance().format(Calendar.getInstance().getTime());
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference("Notifications");
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("userid",firebaseUser.getUid());
        hashMap.put("text","upload a wish at"+" "+mydate);
        reference.push().setValue(hashMap);
    }
}