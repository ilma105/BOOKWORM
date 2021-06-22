package com.example.system_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.system_project.Adapters.NotificationAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import static com.nightonke.boommenu.ButtonEnum.*;


public class HomeActivity extends AppCompatActivity {
    FirebaseAuth ma;
    ImageView icon;
    BoomMenuButton bmb;
    private LinearLayout linearLayout;

    CheckBox drama,fiction,romantic,action,mystery,horror,fantasy,select,history,comedy,thriller,layoutbanglacheck,kobita,somogro,goyenda,boiganikkolpo;
    ImageButton recom,fantasybook,homebtn,romancticbook,actionbook,mysterybook,poetbook,dramabook,historybook,comedybook,thrillerbook,horrorbook,somogrobook,boiganikbook,kobitabook,goyendBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.setTitle("HomePage");
        Toast.makeText(HomeActivity.this,"HEYYY WELCOME TO BOOKWORM\uD83D\uDE04",Toast.LENGTH_SHORT).show();
        ma=FirebaseAuth.getInstance();
     recom=findViewById(R.id.recommendedbtnID);
        homebtn = (ImageButton)  findViewById(R.id.homeIconID);
        bmb = (BoomMenuButton) findViewById(R.id.boombtnID);
        bmb.setButtonEnum(ButtonEnum.Ham);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_6_1);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.HAM_6);
        fantasybook=(ImageButton)findViewById(R.id.fantasybutton);
        horrorbook=(ImageButton)findViewById(R.id.horrorbutton);
        drama=(CheckBox)findViewById(R.id.dramacheckbox);
        somogro=findViewById(R.id.somogroedit);
        fiction=(CheckBox)findViewById(R.id.fictioncheckbox);
        romantic=(CheckBox)findViewById(R.id.romanticcheckbox);
        action=(CheckBox)findViewById(R.id.actioncheckbox);
        mystery=(CheckBox)findViewById(R.id.mysterycheckbox);
        horror=(CheckBox)findViewById(R.id.horrorcheckbox);
        thriller=(CheckBox)findViewById(R.id.thrillercheckbox);
        historybook=(ImageButton)findViewById(R.id.historybutton);
        history=(CheckBox)findViewById(R.id.histprycheckbox);
        kobita=findViewById(R.id.kobitaedit);
        goyenda=findViewById(R.id.goyendaedit);
        boiganikkolpo=findViewById(R.id.boigganikedit);

        goyendBook=findViewById(R.id.goyendabutton);
        comedy=(CheckBox)findViewById(R.id.comedycheckbox);
        fantasy=(CheckBox)findViewById(R.id.fantasycheckbox);
        select=(CheckBox)findViewById(R.id.checkselecttype);
        mysterybook=(ImageButton)findViewById(R.id.mysterybutton);
        romancticbook=(ImageButton)findViewById(R.id.romanticbutton);
        actionbook=(ImageButton)findViewById(R.id.actionbutton);
        poetbook=(ImageButton)findViewById(R.id.poetbutton);
        dramabook=(ImageButton)findViewById(R.id.dramabutton);
        layoutbanglacheck=findViewById(R.id.checkselecttypebangla);
        historybook=(ImageButton)findViewById(R.id.historybutton);
        comedybook=(ImageButton)findViewById(R.id.comedybutton);
        thrillerbook=(ImageButton)findViewById(R.id.thrillerbutton);
        kobitabook=findViewById(R.id.kobitabutton);
        somogrobook=findViewById(R.id.sombutton);
        boiganikbook=findViewById(R.id.boiganikbutton);

        LinearLayout dramalinearLayout1=(LinearLayout)findViewById(R.id.dramalinear);
        LinearLayout fictionlinearLayout=(LinearLayout)findViewById(R.id.fictionlinear);
        LinearLayout romanticinearLayout2=(LinearLayout)findViewById(R.id.romanticlinear);
        LinearLayout actionlinearlayout=(LinearLayout)findViewById(R.id.actionlinear);
        LinearLayout mysterylinearlayout=(LinearLayout)findViewById(R.id.mysterylinear);
        LinearLayout thrillerlinearlayout=(LinearLayout)findViewById(R.id.thrillerlinear);
        LinearLayout fantasylinearlayout=(LinearLayout)findViewById(R.id.fantasylinear);
        LinearLayout historylinearlayout=(LinearLayout)findViewById(R.id.historylinear);
        LinearLayout comedylinearlayout=(LinearLayout)findViewById(R.id.comedylinear);
        LinearLayout horrorlinearlayout=(LinearLayout)findViewById(R.id.horrorlinear);
        LinearLayout somogrolinear=findViewById(R.id.somogrolinear);
        LinearLayout layout=(LinearLayout)findViewById(R.id.layout);
        LinearLayout kobitalinlayout=findViewById(R.id.kobitalinear);
        LinearLayout goyendalinear=findViewById(R.id.goyandalinear);
        LinearLayout boiganikLinear=findViewById(R.id.boiganiklinear);

        RelativeLayout goyendarelativelayout=(RelativeLayout) findViewById(R.id.goyenda1);

        RelativeLayout booganikrelativelayout=(RelativeLayout) findViewById(R.id.boiganikrel);

        RelativeLayout dramarelativelayout=(RelativeLayout) findViewById(R.id.dramarelative);
        RelativeLayout fictionrelativelayout=(RelativeLayout) findViewById(R.id.fictionrelative);
        RelativeLayout romanticrelativelayout=(RelativeLayout) findViewById(R.id.romanticrelative);
        RelativeLayout actionrelativelayout=(RelativeLayout) findViewById(R.id.actionrelative);
        RelativeLayout mysteryrelativelayout=(RelativeLayout) findViewById(R.id.mysteryrelative);
        RelativeLayout thrillerelativelayout=(RelativeLayout) findViewById(R.id.thrillerrelative);
        RelativeLayout fantasyrelativelayout=(RelativeLayout) findViewById(R.id.fantasyrelative);
        RelativeLayout historyrelativelayout=(RelativeLayout) findViewById(R.id.historyrelative);
        RelativeLayout comedyrelativelayout=(RelativeLayout) findViewById(R.id.comedyrelative);
        RelativeLayout horrorrelativelayout=(RelativeLayout) findViewById(R.id.horrorelative);
        RelativeLayout kobitarellayout=findViewById(R.id.kobitarel);
        RelativeLayout somgrorela=findViewById(R.id.somgrorel);
        icon=(ImageView)findViewById(R.id.icon);
        linearLayout=(LinearLayout)findViewById(R.id.linear);
        linearLayout.animate().alpha(0f).setDuration(1);
        TranslateAnimation animation=new TranslateAnimation(0,0,0,-1000);
        animation.setDuration(1000);
        animation.setFillAfter(false);
        animation.setAnimationListener(new myanimation());
        icon.setAnimation(animation);

        //linearLayout.setVisibility(View.VISIBLE);
        recom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this, RecomActivity.class);
                startActivity(intent);
            }
        });
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this,"Home Page",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
            }
        });
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (select.isChecked())
                {
                    layout.setVisibility(View.VISIBLE);
                }
                else{

                    layout.setVisibility(View.GONE);
                }
            }
        });
        LinearLayout layout1ban=findViewById(R.id.layoutbangla);
        layoutbanglacheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layoutbanglacheck.isChecked())
                {
                    layout1ban.setVisibility(View.VISIBLE);
                }
                else{

                    layout1ban.setVisibility(View.GONE);
                }
            }
        });
        romancticbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(HomeActivity.this,"",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),romance.class);
                startActivity(intent);
            }
        });
        thrillerbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(HomeActivity.this,"",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),thriller.class);
                startActivity(intent);
            }
        });
        comedybook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(HomeActivity.this,"",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),comedy.class);
                startActivity(intent);
            }
        });
        fantasybook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(HomeActivity.this,"",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),fantasy.class);
                startActivity(intent);
            }
        });
        somogrobook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(HomeActivity.this,"",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),Somogro.class);
                startActivity(intent);
            }
        });
        kobitabook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(HomeActivity.this,"",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),kobita.class);
                startActivity(intent);
            }
        });
        horrorbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(HomeActivity.this,"",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),horror.class);
                startActivity(intent);
            }
        });
        historybook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(HomeActivity.this,"",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),history.class);
                startActivity(intent);
            }
        });
        actionbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(HomeActivity.this,"",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),action.class);
                startActivity(intent);
            }
        });
        poetbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(HomeActivity.this,"",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),poetry.class);
                startActivity(intent);
            }
        });
        dramabook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(HomeActivity.this,"",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),drama.class);
                startActivity(intent);
            }
        });
        mysterybook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(HomeActivity.this,"",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),mystery.class);
                startActivity(intent);
            }
        });
        goyendBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(HomeActivity.this,"",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),Goyenda.class);
                startActivity(intent);
            }
        });
        boiganikbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(HomeActivity.this,"",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),Boiganik.class);
                startActivity(intent);
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
                                Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
                                startActivity(intent);
                               // finish();
                                Toast.makeText(HomeActivity.this,"Wohhoo",Toast.LENGTH_SHORT).show();
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
                                Intent intent = new Intent(getApplicationContext(),Search.class);
                                startActivity(intent);
                                //finish();
                                Toast.makeText(HomeActivity.this,"Connect with other users",Toast.LENGTH_SHORT).show();
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
                                Intent intent = new Intent(getApplicationContext(),NotesActivity.class);
                                startActivity(intent);
                                //finish();
                                Toast.makeText(HomeActivity.this,"Note down",Toast.LENGTH_SHORT).show();
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
                                Intent intent = new Intent(getApplicationContext(),authorinfo.class);
                                startActivity(intent);
                               // finish();
                                Toast.makeText(HomeActivity.this,"Contact with us",Toast.LENGTH_SHORT).show();
                            }
                        });
                bmb.addBuilder(builder);
            }else if (i==4){
                HamButton.Builder builder = new HamButton.Builder()
                        .normalImageRes(R.drawable.bright)
                        .normalText("Wish List").subNormalText("Wanna see others wishes")
                        .listener(new OnBMClickListener() {
                            @Override
                            public void onBoomButtonClick(int index) {


                                Intent intent = new Intent(getApplicationContext(),notepad.class);
                                startActivity(intent);

                               // Toast.makeText(HomeActivity.this,"Bye... :(",Toast.LENGTH_SHORT).show();

                            }
                        });
                bmb.addBuilder(builder);
            }

            else if (i==5){
                HamButton.Builder builder = new HamButton.Builder()
                        .normalImageRes(R.drawable.out)
                        .normalText("Sign Out").subNormalText("Come back soon")
                        .listener(new OnBMClickListener() {
                            @Override
                            public void onBoomButtonClick(int index) {
                                SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("remember","false");
                                editor.apply();
                                FirebaseAuth.getInstance().signOut();

                                //finish();
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                                Toast.makeText(HomeActivity.this,"Bye... :(",Toast.LENGTH_SHORT).show();
                            finish();
                            }
                        });
                bmb.addBuilder(builder);
            }

        }
        drama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drama.isChecked()){
                    dramarelativelayout.setVisibility(View.VISIBLE);

                    dramalinearLayout1.setVisibility(View.VISIBLE);

                }
                else
                {
                    dramarelativelayout.setVisibility(View.GONE);
                    dramalinearLayout1.setVisibility(View.GONE);

                }

            }
        });
        kobita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kobita.isChecked()){
                    kobitalinlayout.setVisibility(View.VISIBLE);
                    kobitarellayout.setVisibility(View.VISIBLE);

                }
                else
                {
                    kobitalinlayout.setVisibility(View.GONE);
                    kobitarellayout.setVisibility(View.GONE);

                }

            }
        });
        somogro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (somogro.isChecked()){
                    somgrorela.setVisibility(View.VISIBLE);

                    somogrolinear.setVisibility(View.VISIBLE);

                }
                else
                {
                    somgrorela.setVisibility(View.GONE);
                    somogrolinear.setVisibility(View.GONE);

                }

            }
        });
        fiction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fiction.isChecked()){
                    fictionrelativelayout.setVisibility(View.VISIBLE);

                    fictionlinearLayout.setVisibility(View.VISIBLE);

                }
                else
                {
                    fictionrelativelayout.setVisibility(View.GONE);
                    fictionlinearLayout.setVisibility(View.GONE);

                }
            }
        });
        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (action.isChecked()){
                    actionrelativelayout.setVisibility(View.VISIBLE);

                    actionlinearlayout.setVisibility(View.VISIBLE);

                }
                else
                {
                    actionrelativelayout.setVisibility(View.GONE);
                    actionlinearlayout.setVisibility(View.GONE);

                }
    }
});
        romantic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (romantic.isChecked()){
                    romanticrelativelayout.setVisibility(View.VISIBLE);

                    romanticinearLayout2.setVisibility(View.VISIBLE);

                }
                else
                {
                    romanticrelativelayout.setVisibility(View.GONE);
                    romanticinearLayout2.setVisibility(View.GONE);

                }
        }
        });
        fantasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fantasy.isChecked()){
                    fantasyrelativelayout.setVisibility(View.VISIBLE);

                    fantasylinearlayout.setVisibility(View.VISIBLE);

                }
                else
                {
                    fantasyrelativelayout.setVisibility(View.GONE);
                    fantasylinearlayout.setVisibility(View.GONE);

                }
        }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (history.isChecked()){
                    historyrelativelayout.setVisibility(View.VISIBLE);

                    historylinearlayout.setVisibility(View.VISIBLE);

                }
                else
                {
                    historyrelativelayout.setVisibility(View.GONE);
                    historylinearlayout.setVisibility(View.GONE);

                }
        }
        });
        comedy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (comedy.isChecked()){
                    comedyrelativelayout.setVisibility(View.VISIBLE);

                    comedylinearlayout.setVisibility(View.VISIBLE);

                }
                else
                {
                    comedylinearlayout.setVisibility(View.GONE);
                    comedyrelativelayout.setVisibility(View.GONE);

                }
        }
        });
        thriller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (thriller.isChecked()){
                    thrillerelativelayout.setVisibility(View.VISIBLE);


                    thrillerlinearlayout.setVisibility(View.VISIBLE);

                }
                else
                {

                    thrillerelativelayout.setVisibility(View.GONE);

                    thrillerlinearlayout.setVisibility(View.GONE);

                }
        }
        });
        mystery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mystery.isChecked()){
                    mysteryrelativelayout.setVisibility(View.VISIBLE);


                    mysterylinearlayout.setVisibility(View.VISIBLE);

                }
                else
                {

                    mysteryrelativelayout.setVisibility(View.GONE);

                    mysterylinearlayout.setVisibility(View.GONE);

                }
        }
        });
        horror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (horror.isChecked()){
                    horrorrelativelayout.setVisibility(View.VISIBLE);


                    horrorlinearlayout.setVisibility(View.VISIBLE);

                }
                else
                {

                    horrorrelativelayout.setVisibility(View.GONE);

                    horrorlinearlayout.setVisibility(View.GONE);

                }
        }
        });
        goyenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (goyenda.isChecked()){
                    goyendalinear.setVisibility(View.VISIBLE);
                    goyendarelativelayout.setVisibility(View.VISIBLE);

                }
                else
                {
                    goyendalinear.setVisibility(View.GONE);
                    goyendarelativelayout.setVisibility(View.GONE);

                }

            }
        });
        boiganikkolpo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (boiganikkolpo.isChecked()){
                    boiganikLinear.setVisibility(View.VISIBLE);
                    booganikrelativelayout.setVisibility(View.VISIBLE);

                }
                else
                {
                    boiganikLinear.setVisibility(View.GONE);
                    booganikrelativelayout.setVisibility(View.GONE);
                }

            }
        });

    }
    private class myanimation implements Animation.AnimationListener{

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            icon.clearAnimation();
            icon.setVisibility(View.INVISIBLE);
            linearLayout.animate().alpha(1f).setDuration(1000);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

}