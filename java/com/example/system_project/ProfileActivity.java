package com.example.system_project;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.system_project.Fragments.ProfileFragment;
import com.example.system_project.Fragments.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity{

    BoomMenuButton bmb;
    private Button editprofile,search;
    private CircleImageView imageProfile;
    private ImageView options;
    private TextView books,followers,following,email,bio,username,nationality,use;
    private ImageView myPictures,savedpictures,textingpro;
    private EditText wishlist, contribution,booksell,downloadlist,rrcentread;
    private FirebaseUser fuser;
    DatabaseReference databaseReference;
    String profileid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        this.setTitle("Profile");


        search=findViewById(R.id.searchbutton);
        fuser= FirebaseAuth.getInstance().getCurrentUser();
       SharedPreferences sharedPreferences= getApplicationContext().getSharedPreferences("PREFS",MODE_PRIVATE);
       //  profileid=sharedPreferences.getString("profileid","none");
        textingpro=findViewById(R.id.textingpro);
        bmb = (BoomMenuButton) findViewById(R.id.boombtnID);
        bmb.setButtonEnum(ButtonEnum.Ham);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_5_1);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.HAM_5);
        profileid=fuser.getUid();
        editprofile=findViewById(R.id.editprofile);
        imageProfile=findViewById(R.id.imageprofile);
        followers=findViewById(R.id.followers);
        following=findViewById(R.id.following);
        books=findViewById(R.id.posts);
        email=findViewById(R.id.emailedit);
        bio=findViewById(R.id.bioedit);
        username=findViewById(R.id.usernameedit);
        nationality=findViewById(R.id.nationalityedit);
        use=findViewById(R.id.joker);

        wishlist=findViewById(R.id.wishlist);
        contribution=findViewById(R.id.contribution);
        booksell=findViewById(R.id.sellbook);
        downloadlist=findViewById(R.id.download);
        rrcentread=findViewById(R.id.recent);
        userinfo();
        getfollowers();
        if (profileid.equals(fuser.getUid())){
            editprofile.setText("EDIT");

        }
        else {
            checkFollow();

        }
        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  btn=editprofile.getText().toString();
if (btn.equals("EDIT")) {
    startActivity(new Intent(getApplicationContext(), EditProfileActivity.class));
}

}
        });


        followers.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(ProfileActivity.this,FollowersActivity.class);
        intent.putExtra("id",profileid);
        intent.putExtra("title","Followers");
        startActivity(intent);
    }
});
        following.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfileActivity.this,FollowersActivity.class);
                intent.putExtra("id",profileid);
                intent.putExtra("title","Following");
                startActivity(intent);
            }
        });
        textingpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten=new Intent(ProfileActivity.this,TextingActivity.class);
                startActivity(inten);
            }
        });
        contribution.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(ProfileActivity.this,ContributionActivity.class);
        startActivity(intent);
    }
});
        booksell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfileActivity.this,BookSell.class);
                startActivity(intent);
            }
        });
        wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfileActivity.this,WishList.class);
                startActivity(intent);
            }
        });
        downloadlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfileActivity.this,DownloadList.class);
                startActivity(intent);
            }
        });
        rrcentread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProfileActivity.this,Recent.class);
                startActivity(intent);
            }
        });
        FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        String userid=firebaseUser.getUid();
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference().child("contribution").child(userid);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                   int ii= (int) snapshot.getChildrenCount();
                String s=String.valueOf(ii);
                books.setText(s);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        for(int i=0;i < bmb.getPiecePlaceEnum().pieceNumber(); i++){
            int position = i;
            if (i==0){
                HamButton.Builder builder = new HamButton.Builder()
                        .normalImageRes(R.drawable.search)
                        .normalText("Search User").subNormalText("Other Users")
                        .listener(new OnBMClickListener() {
                            @Override
                            public void onBoomButtonClick(int index) {
                                Intent intent = new Intent(getApplicationContext(),Search.class);
                                startActivity(intent);
                                Toast.makeText(ProfileActivity.this,"Connect with other users",Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                bmb.addBuilder(builder);
            }else if (i==1){
                HamButton.Builder builder = new HamButton.Builder()
                        .normalImageRes(R.drawable.notes)
                        .normalText("Notes").subNormalText("Write down notes")
                        .listener(new OnBMClickListener() {
                            @Override
                            public void onBoomButtonClick(int index) {
                                Intent intent = new Intent(getApplicationContext(),NotesActivity.class);
                                startActivity(intent);
                                Toast.makeText(ProfileActivity.this,"Note down",Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                bmb.addBuilder(builder);
            }else if (i==2){
                HamButton.Builder builder = new HamButton.Builder()
                        .normalImageRes(R.drawable.cont)
                        .normalText("App Developer's Info").subNormalText("Need any help?")
                        .listener(new OnBMClickListener() {
                            @Override
                            public void onBoomButtonClick(int index) {
                                Intent intent = new Intent(getApplicationContext(),authorinfo.class);
                                startActivity(intent);
                                Toast.makeText(ProfileActivity.this,"Contact with us",Toast.LENGTH_SHORT).show();
                                finish();

                            }
                        });
                bmb.addBuilder(builder);
            }
            else if (i==3){
                HamButton.Builder builder = new HamButton.Builder()
                        .normalImageRes(R.drawable.notify)
                        .normalText("Notification").subNormalText("Notify Me")
                        .listener(new OnBMClickListener() {
                            @Override
                            public void onBoomButtonClick(int index) {

                                Intent intent = new Intent(getApplicationContext(),NotificationActivity.class);
                                startActivity(intent);
                                Toast.makeText(ProfileActivity.this,"Notification",Toast.LENGTH_SHORT).show();

                                
                            }
                        });
                bmb.addBuilder(builder);
            }
            else if (i==4){
                HamButton.Builder builder = new HamButton.Builder()
                        .normalImageRes(R.drawable.wishno)
                        .normalText("Wish & Contribution & Book Sell").subNormalText("Notify Me")
                        .listener(new OnBMClickListener() {
                            @Override
                            public void onBoomButtonClick(int index) {
                                Intent intent = new Intent(getApplicationContext(),NotificationSActivity.class);
                                startActivity(intent);
                                Toast.makeText(ProfileActivity.this,"Notification",Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                bmb.addBuilder(builder);
            }
        }
    }

private void userinfo(){
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("data").child(profileid);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (getApplicationContext()==null){
                    return;
                }
                DATA user=snapshot.getValue(DATA.class);
                use.setText(user.getUsername());
                Glide.with(getApplicationContext()).load(user.getImageurl()).into(imageProfile);
                username.setText(user.getUsername());

                bio.setText(user.getBio());
                email.setText(user.getEmail());
                nationality.setText(user.getNationality());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
}
private void checkFollow()
{
    DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference().child("Followw")
            .child(fuser.getUid()).child("following");
    databaseReference.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if (snapshot.child(profileid).exists()){
                editprofile.setText("following");
            }
            else {
                editprofile.setText("follow");
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });


}
private void getfollowers(){
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference()
                .child("Followw").child(profileid).child("followers");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                followers.setText(""+snapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    DatabaseReference databaseReference1=FirebaseDatabase.getInstance().getReference()
            .child("Followw").child(profileid).child("following");
    databaseReference1.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            following.setText(""+snapshot.getChildrenCount());
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });

}

}