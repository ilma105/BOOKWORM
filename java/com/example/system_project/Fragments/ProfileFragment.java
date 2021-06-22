package com.example.system_project.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.system_project.BookSell;
import com.example.system_project.BookSellfol;
import com.example.system_project.ChatDetailActivity;
import com.example.system_project.ContributionActivity;
import com.example.system_project.DATA;
import com.example.system_project.DownloadList;
import com.example.system_project.Downloadlistfol;
import com.example.system_project.EditProfileActivity;
import com.example.system_project.FollowersActivity;
import com.example.system_project.HomeActivity;
import com.example.system_project.NotesActivity;
import com.example.system_project.ProfileActivity;
import com.example.system_project.R;
import com.example.system_project.Recent;
import com.example.system_project.RecomActivity;
import com.example.system_project.Search;
import com.example.system_project.TextingActivity;
import com.example.system_project.TextingActivity1;
import com.example.system_project.WishList;
import com.example.system_project.authorinfo;
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

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment {


    BoomMenuButton bmb;
    private Button editprofile,search;
    private CircleImageView imageProfile;
    private ImageView options;
    private TextView books,followers,following,email,bio,username,nationality,use;
    private ImageView myPictures,savedpictures,textingpro;
    private EditText wishlist, contribution,booksell,downloadlist,rrcentread;
    private FirebaseUser fuser;
    DatabaseReference databaseReference;
    String profileid,userid;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
View view=inflater.inflate(R.layout.fragment_profile,container,false);


        search=view.findViewById(R.id.searchbutton);
        fuser= FirebaseAuth.getInstance().getCurrentUser();
        SharedPreferences sharedPreferences= getContext().getSharedPreferences("PREFS", Context.MODE_PRIVATE);
        profileid=sharedPreferences.getString("profileid","none");
        userid=fuser.getUid();
        textingpro=view.findViewById(R.id.textingpro);
        bmb = (BoomMenuButton) view.findViewById(R.id.boombtnID);
        bmb.setButtonEnum(ButtonEnum.Ham);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_4_1);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.HAM_4);
       // profileid=fuser.getUid();
        editprofile=view.findViewById(R.id.editprofile);
        imageProfile=view.findViewById(R.id.imageprofile);
        followers=view.findViewById(R.id.followers);
        following=view.findViewById(R.id.following);
        books=view.findViewById(R.id.posts);
        email=view.findViewById(R.id.emailedit);
        bio=view.findViewById(R.id.bioedit);
        username=view.findViewById(R.id.usernameedit);
        nationality=view.findViewById(R.id.nationalityedit);
        use=view.findViewById(R.id.joker);

        wishlist=view.findViewById(R.id.wishlist);
        contribution=view.findViewById(R.id.contribution);
        booksell=view.findViewById(R.id.sellbook);
        downloadlist=view.findViewById(R.id.download);
        rrcentread=view.findViewById(R.id.recent);
        userinfo();
        getfollowers();
        if (profileid.equals(fuser.getUid())){
            editprofile.setText("EDIT");

        }
        else {
            checkFollow();
            downloadlist.setVisibility(View.GONE);
            rrcentread.setVisibility(View.GONE);
            wishlist.setVisibility(View.GONE);

        }
        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  btn=editprofile.getText().toString();
                if (btn.equals("EDIT")) {
                    startActivity(new Intent(getContext(), EditProfileActivity.class));
                }
else if (btn.equals("follow")){
    FirebaseDatabase.getInstance().getReference().child("Followw").child(fuser.getUid())
            .child("following").child(profileid).setValue(true);
    FirebaseDatabase.getInstance().getReference().child("Followw").child(profileid)
            .child("followers").child(fuser.getUid()).setValue(true);
    addnotification();

}
else {
    FirebaseDatabase.getInstance().getReference().child("Followw").child(fuser.getUid())
            .child("following").child(profileid).removeValue();
    FirebaseDatabase.getInstance().getReference().child("Followw").child(profileid)
            .child("followers").child(fuser.getUid()).removeValue();
}
            }
        });
        followers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), FollowersActivity.class);
                intent.putExtra("id",profileid);
                intent.putExtra("title","Followers");
                startActivity(intent);
            }
        });
        following.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),FollowersActivity.class);
                intent.putExtra("id",profileid);
                intent.putExtra("title","Following");
                startActivity(intent);
            }
        });
        textingpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten=new Intent(getContext(), TextingActivity1.class);
                startActivity(inten);
            }
        });
        contribution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), Downloadlistfol.class);
                startActivity(intent);
            }
        });
        booksell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), BookSellfol.class);
                startActivity(intent);
            }
        });
        wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), WishList.class);
                startActivity(intent);
            }
        });
        downloadlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), Downloadlistfol.class);
                startActivity(intent);
            }
        });
        rrcentread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), Recent.class);
                startActivity(intent);
            }
        });
        FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        String userid=firebaseUser.getUid();
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("contribution").child(profileid);
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
            if(i==0){
                HamButton.Builder builder = new HamButton.Builder()
                        .normalImageRes(R.drawable.user)
                        .normalText("Profile").subNormalText("My world")
                        .listener(new OnBMClickListener() {
                            @Override
                            public void onBoomButtonClick(int index) {
                                Intent intent = new Intent(getContext(),ProfileActivity.class);
                                startActivity(intent);
                                Toast.makeText(getContext(),"Wohho",Toast.LENGTH_SHORT).show();
                                getActivity().finish();
                            }
                        });
                bmb.addBuilder(builder);
            }else if (i==1){
                HamButton.Builder builder = new HamButton.Builder()
                        .normalImageRes(R.drawable.search)
                        .normalText("Search User").subNormalText("Other Users")
                        .listener(new OnBMClickListener() {
                            @Override
                            public void onBoomButtonClick(int index) {
                                Intent intent = new Intent(getContext(), Search.class);
                                startActivity(intent);
                                Toast.makeText(getContext(),"Connect with other users",Toast.LENGTH_SHORT).show();
                                getActivity().finish();
                            }
                        });
                bmb.addBuilder(builder);
            }else if (i==2){
                HamButton.Builder builder = new HamButton.Builder()
                        .normalImageRes(R.drawable.notes)
                        .normalText("Notes").subNormalText("Write down notes")
                        .listener(new OnBMClickListener() {
                            @Override
                            public void onBoomButtonClick(int index) {
                                Intent intent = new Intent(getContext(), NotesActivity.class);
                                startActivity(intent);
                                Toast.makeText(getContext(),"Note down",Toast.LENGTH_SHORT).show();
                                getActivity().finish();
                            }
                        });
                bmb.addBuilder(builder);
            }else if (i==3){
                HamButton.Builder builder = new HamButton.Builder()
                        .normalImageRes(R.drawable.cont)
                        .normalText("App Developer's Info").subNormalText("Need any help?")
                        .listener(new OnBMClickListener() {
                            @Override
                            public void onBoomButtonClick(int index) {
                                Intent intent = new Intent(getContext(), authorinfo.class);
                                startActivity(intent);
                                Toast.makeText(getContext(),"Contact with us",Toast.LENGTH_SHORT).show();
                                getActivity().finish();
                            }
                        });
                bmb.addBuilder(builder);
            }
        }
        return view;
    }
    private void addnotification(){
        FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference reference=FirebaseDatabase.getInstance().getReference("Notification")
                .child(profileid);
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("userid",firebaseUser.getUid());
        hashMap.put("text","started following you");
        reference.push().setValue(hashMap);

    }

    private void userinfo(){
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("data").child(profileid);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (getContext()==null){
                    return;
                }
                DATA user=snapshot.getValue(DATA.class);
                use.setText(user.getUsername());
                Glide.with(getContext()).load(user.getImageurl()).into(imageProfile);
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

   /* private void getFollowersandfollowingcount() {
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("data");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1:snapshot.getChildren()) {
                  //  followers.setText("" + snapshot1.getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1:snapshot.getChildren()) {
                   // following.setText("" + snapshot1.getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void userinfo() {
        FirebaseDatabase.getInstance().getReference().child(profileid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DATA user=snapshot.getValue(DATA.class);
                username.setText(user.getUsername());
                fullname.setText(user.getName());
                nationality.setText(user.getNationality());
                bio.setText(user.getAge());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }*/
}
