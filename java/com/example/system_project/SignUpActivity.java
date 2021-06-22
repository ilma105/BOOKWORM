package com.example.system_project;

import androidx.annotation.NonNull;
import  androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    ProgressBar progressBar;

    EditText namee,usernamee,fav,national,email,userage, password, repassword;
    ImageButton signup, signin,Faq;
    private FirebaseAuth mAuth;
Dialog dialog;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        this.setTitle("BOOKWORM");


        namee = (EditText) findViewById(R.id.name);
        Faq = (ImageButton) findViewById(R.id.FAQButtonID);
        mAuth = FirebaseAuth.getInstance();
        usernamee = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        national = (EditText) findViewById(R.id.nation);
        userage = (EditText) findViewById(R.id.Birth);
        email = (EditText) findViewById(R.id.email);
        progressBar = (ProgressBar) findViewById(R.id.progressBarID);
        signup = (ImageButton) findViewById(R.id.btnsignup);
        signin = (ImageButton) findViewById(R.id.btnsignin);
        signin.setOnClickListener(this);
        signup.setOnClickListener(this);
        dialog = new Dialog(this,R.style.AnimateDialog);
        Button close;
        dialog.setContentView(R.layout.activity_custom_popup);
        close = dialog.findViewById(R.id.close);
        dialog.show();
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        //fav = (EditText) findViewById(R.id.favtype);

        Faq.setOnClickListener(this) ;


    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnsignup) {
            progressBar.setVisibility(View.VISIBLE);
            String nam = namee.getText().toString().trim();
            String usern = usernamee.getText().toString().trim();
            String ages = userage.getText().toString().trim();
            String nat = national.getText().toString().trim();
            String pass = password.getText().toString();

            String repass = repassword.getText().toString();
            String emailid = email.getText().toString().trim();

            if (pass.equals("") || emailid.equals("") || nam.equals("") || usern.equals("") || nat.equals("") || ages.equals("") || repass.equals(""))
                Toast.makeText(SignUpActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();


            else {
                if (!Patterns.EMAIL_ADDRESS.matcher(emailid).matches()) {
                    email.setError("Enter a valid email");
                    email.requestFocus();
                    return;
                }
                //       databaseReference.child(key).setValue(data);
                //Toast.makeText(LoginActivity.this, "Please enter", Toast.LENGTH_SHORT).show();

                if (pass.length() < 6) {
                    password.setError("minimum lenght should be 6");
                    password.requestFocus();
                    return;
                }

                if (!pass.equals(repass)) {
                    Toast.makeText(SignUpActivity.this, "pass doesn't match", Toast.LENGTH_SHORT).show();
                } else {
                    userregister(emailid,pass,nam,usern,nat,ages);
                }
            }
        }
        else if(v.getId()==R.id.btnsignin){

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            Toast.makeText(SignUpActivity.this,"Welcome"+usernamee.getText(),Toast.LENGTH_SHORT).show();

            startActivity(intent);
        }
        else if(v.getId()==R.id.FAQButtonID){
            Toast.makeText(SignUpActivity.this,"Take me to website",Toast.LENGTH_SHORT).show();
            Uri uri=Uri.parse("https://ilma105.github.io/bookwompro.github.io/INDEX.html");
            startActivity(new Intent(Intent.ACTION_VIEW,uri));
        }

    }
    private void userregister(String emailid,String pass,String nam,String usern,String nat,String  ages) {


                mAuth.createUserWithEmailAndPassword(emailid, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);

                        if (task.isSuccessful()) {
                          FirebaseUser firebaseUser=mAuth.getCurrentUser();

                          String userid=firebaseUser.getUid();
                            databaseReference=FirebaseDatabase.getInstance().getReference().child("data").child(userid);

                            HashMap<String, Object> map = new HashMap<>();
                            //  Map<String, Object> postValues = data.toMap();
                            map.put("id",userid);
                            map.put("fullUsername", nam);
                            map.put("email",emailid);
                            map.put("username", usern);
                            map.put("nationality",nat);
                            map.put("age",ages);
                            map.put("bio","");
                            map.put("imageurl","https://firebasestorage.googleapis.com/v0/b/bookworm-7195d.appspot.com/o/uploads%2Fuser-1.png?alt=media&token=9c653d2d-262f-4c8d-9433-5e626f69ac74");
                            databaseReference.setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                     if (task.isSuccessful()){
                                         Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                                         startActivity(intent);
                                         Toast.makeText(SignUpActivity.this, "Registration is Successful", Toast.LENGTH_SHORT).show();

                                     }
                                }
                             });


                        } else {
                            Toast.makeText(SignUpActivity.this, "Existing User!!Go to Sign In Page", Toast.LENGTH_SHORT).show();

                        }

                    }

                });

        }

}