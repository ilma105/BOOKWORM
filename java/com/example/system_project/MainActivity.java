package com.example.system_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private ImageButton FAQ, signin,signup;
    private EditText useremail, password;
    private TextView forgotpass;
    ProgressBar progressBar;
    CheckBox remember;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("BOOKWORM");
        mAuth = FirebaseAuth.getInstance();
        progressBar = (ProgressBar) findViewById(R.id.progressBarID);

        FAQ = (ImageButton) findViewById(R.id.FAQButtonID);
        useremail = (EditText) findViewById(R.id.UseremailID);
        password = (EditText) findViewById(R.id.PasswordID);
        signin = (ImageButton) findViewById(R.id.SignInButtonID);
        forgotpass = (TextView) findViewById(R.id.btnforgotpass);
        signup = (ImageButton) findViewById(R.id.SignUpButtonID);
remember=findViewById(R.id.rememberMeCheckBox);

        FAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Take me to website",Toast.LENGTH_SHORT).show();
            // Intent intent=new Intent(MainActivity.this,Webpage.class);
             //startActivity(intent);
               Uri uri=Uri.parse("https://ilma105.github.io/bookwompro.github.io/INDEX.html");
                startActivity(new Intent(Intent.ACTION_VIEW,uri));
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Take me to Sign up page",Toast.LENGTH_SHORT).show();

                Intent SignUpPage = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(SignUpPage);

            }
        });

        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"No Worry",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), PasswordActivity.class);
                startActivity(intent);
            }
        });
        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String checkbox = preferences.getString("remember","");
        if (checkbox.equals("true")){
            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        }else if (checkbox.equals("false")){
            Toast.makeText(this,"Please Sign In",Toast.LENGTH_SHORT).show();
        }
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }

            private void userLogin() {
                String emailid=useremail.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if(emailid.equals("")||pass.equals(""))
                    Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                if(!Patterns.EMAIL_ADDRESS.matcher(emailid).matches()){
                    useremail.setError("Enter a valid email");
                    useremail.requestFocus();
                    return;
                }

                if (pass.length()<6){
                    password.setError("Minimum length of password should be 6");
                    password.requestFocus();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                mAuth.signInWithEmailAndPassword(emailid,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if(task.isSuccessful()) {
                            DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("data")
                                    .child(mAuth.getCurrentUser().getUid());
                            databaseReference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    Toast.makeText(MainActivity.this,"Sign In successful",Toast.LENGTH_SHORT).show();

                                    finish();
                                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                        }else{
                            Toast.makeText(MainActivity.this,"User doesn't exist!! Sign In Unsuccessful",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","true");
                    editor.apply();
                    Toast.makeText(MainActivity.this,"Checked",Toast.LENGTH_SHORT).show();
                }else if (!compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","false");
                    editor.apply();
                    Toast.makeText(MainActivity.this,"Unchecked",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}