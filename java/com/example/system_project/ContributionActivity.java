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

public class ContributionActivity extends AppCompatActivity {
EditText booknamedit,authoredit,ratedit,typedit,rowdit,bookpdf;
//TextView row1,row2,row3,book1,author1,type1,rate1;
Button save,load;

//String profileid;
//FirebaseUser firebaseUser;
DatabaseReference databaseReference;

    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    StorageReference storageReference;
    String bookname,authname,type;
    Float rate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contribution);
        this.setTitle("Contribution List");

      //  databaseReference=FirebaseDatabase.getInstance().getReference("contribution");
        mAuth = FirebaseAuth.getInstance();
        booknamedit=findViewById(R.id.booknameedit);
        authoredit=findViewById(R.id.authornameedit);
        load=findViewById(R.id.loadcontri);
        ratedit=findViewById(R.id.rateedit);
        typedit=findViewById(R.id.typeedit);
        save=findViewById(R.id.saveContributionID);
        bookpdf=findViewById(R.id.uploadbookpdf);
        storageReference= FirebaseStorage.getInstance().getReference();
bookpdf.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        selectpdf();
    }
});

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ContributionActivity.this,Dramabook.class);
                startActivity(intent);
            }
        });
        //showin();
    }

    private void selectpdf() {
        Intent intent=new Intent();
        intent.setType("application/pdf");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Selected"),12);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==12&& resultCode==RESULT_OK&& data!=null && data.getData()!=null){
            bookpdf.setText(data.getDataString().substring(data.getDataString().lastIndexOf("/")+1));
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveinfo(booknamedit.getText().toString(), authoredit.getText().toString(), typedit.getText().toString(), ratedit.getText().toString(),data.getData());

                }
            });
        }

    }

    private void saveinfo(String book, String author, String  type, String rate, Uri data) {
        FirebaseUser firebaseUser=mAuth.getCurrentUser();
        float rate1=Float.parseFloat(rate);

        String userid=firebaseUser.getUid();
        databaseReference=FirebaseDatabase.getInstance().getReference().child("contribution").child(userid);

        String key=databaseReference.push().getKey();

        final ProgressDialog progressDialog=new ProgressDialog(this);
            progressDialog.setTitle("loading");
            progressDialog.show();
            StorageReference ref=storageReference.child("uploadpdf"+System.currentTimeMillis()+".pdf");

            ref.putFile(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
Task<Uri> uriTask=taskSnapshot.getStorage().getDownloadUrl();
while (!uriTask.isComplete());
Uri uri=uriTask.getResult();
                    userbookclass user=new userbookclass(book,author,type,rate1,uri.toString());

                    databaseReference.child(key).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                            addnotification();

                        }

                    });

                    Toast.makeText(ContributionActivity.this, "Uploaded \uD83D\uDE0A\uD83D\uDE0A", Toast.LENGTH_SHORT).show();
progressDialog.dismiss();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
double progreaa=(100.0* snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
progressDialog.setMessage("Uploading...."+(int)progreaa+"%");
                }
            });


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
        hashMap.put("text","contributed  a book to the app at"+" "+mydate);
        reference.push().setValue(hashMap);
    }


}