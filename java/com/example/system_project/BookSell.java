package com.example.system_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class BookSell extends AppCompatActivity {
    EditText booknamedit,authoredit,ratedit,typedit,rpiceedit,copyedit;
    //TextView row1,row2,row3,book1,author1,type1,rate1;
    Button save;
    BoomMenuButton bmb;
    ImageButton textingpro,load;

    DatabaseReference databaseReference;

    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    StorageReference storageReference;
    String bookname,authname,type;
    Float rate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_sell);
this.setTitle("Book Sell");
        //  databaseReference=FirebaseDatabase.getInstance().getReference("contribution");
        mAuth = FirebaseAuth.getInstance();

        booknamedit=findViewById(R.id.booknameedit);
        authoredit=findViewById(R.id.authornameedit);
        load=findViewById(R.id.loadcontri);
        ratedit=findViewById(R.id.rateedit);
        typedit=findViewById(R.id.typeedit);
        rpiceedit=findViewById(R.id.price);
        copyedit=findViewById(R.id.copyedit);
        save=findViewById(R.id.saveContributionID);

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BookSell.this,SettingsActivity.class);
                startActivity(intent);
            }
        });
        //showin();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                       saveinfo(booknamedit.getText().toString(), authoredit.getText().toString(), typedit.getText().toString(), ratedit.getText().toString(),copyedit.getText().toString(),rpiceedit.getText().toString());

            }
        });
    }







    private void saveinfo(String book, String author, String  type, String rate,String copy,String price) {
        if (book.equals("") ||author.equals("") || type.equals("") || rate.equals("") || copy.equals("")|| price.equals("")) {
            Toast.makeText(BookSell.this, "enter all the fields", Toast.LENGTH_SHORT).show();

        }
        else {
            FirebaseUser firebaseUser = mAuth.getCurrentUser();
            float rate1=Float.parseFloat(rate);
            String userid = firebaseUser.getUid();
            databaseReference = FirebaseDatabase.getInstance().getReference().child("booksell").child(userid);

            String key = databaseReference.push().getKey();


            userbookclass user = new userbookclass(book, author, type, rate1,copy,price);

            databaseReference.child(key).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                    addnotification();

                }

            });

            Toast.makeText(BookSell.this, "saved", Toast.LENGTH_SHORT).show();

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
        hashMap.put("text","added  a book for sell"+" "+mydate);
        reference.push().setValue(hashMap);
    }
}