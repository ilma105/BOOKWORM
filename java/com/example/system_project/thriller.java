package com.example.system_project;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class thriller extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Object> viewItem=new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final String TAG="check";
    int[] images={R.drawable.misery,
            R.drawable.bird_box,
            R.drawable.the_silent_patient,
            R.drawable.angels_and_demons,
            R.drawable.da_vinci_code,
            R.drawable.sharp_objects,
            R.drawable.an_untamed_state,
            R.drawable.gone_girl,
            R.drawable.killing_floor_
            ,R.drawable.memory_man,
            R.drawable.the_snowman,
            R.drawable.my_lovely_wife,
            R.drawable.the_girl_with_the_dragon_tattoo,
            R.drawable.the_guest_list,
            R.drawable.the_woman_in_the_window,
            R.drawable.the_husbands_secret,
            R.drawable.the_girl_on_the_train,
            R.drawable.the_talented_mr_reply,
            R.drawable.the_wife_between_us,
            R.drawable.the_herd};
    String [] url={"https://drive.google.com/file/d/1NREmYoN_V_uKrxG7SbNgQPKfu6_SduS3/view?usp=sharing",
            "https://drive.google.com/file/d/1TSOvETyYkjglXontHeit5afcCKGwmKAf/view?usp=sharing",
            "https://drive.google.com/file/d/11drhTm5uYu2avq1LDmU0pmaouN3llTxl/view?usp=sharing",
            "https://drive.google.com/file/d/1r0VDqMIa8-WPPJ-X31SPUTNW2OyGuv4d/view?usp=sharing",
            "https://drive.google.com/file/d/1kF1T9bbvoKXd7d4bxavA-HOeU82QVQKm/view?usp=sharing",
            "https://drive.google.com/file/d/1l5bUKIMPyZf5Ywa_47xHuTikjZE6pyZg/view?usp=sharing",
            "https://drive.google.com/file/d/1kRT3PENqs9goeXLv584jSFRG3rEYPG4M/view?usp=sharing",
            "https://drive.google.com/file/d/1gglp21IyCBcv_Ow-5DfGqa6AorvUlW8U/view?usp=sharing",
            "https://drive.google.com/file/d/16xIrD0oCMzcjOnv4u4dILqaXTj2MH8W2/view?usp=sharing",
            "https://drive.google.com/file/d/1gt1ohdbplR14nYghmu-2piYCEhLTbbUy/view?usp=sharing",
            "https://drive.google.com/file/d/1xab24hZBbqWYBMnZYAPrWOIEOhX_nXvQ/view?usp=sharing",
            "https://drive.google.com/file/d/1qGO-GIMSAZCIeWNUMx6kEBHHUk0EJO2o/view?usp=sharing",
            "https://drive.google.com/file/d/1HmjfxXaMAspe8h65CHMtQAKWY2sSfSUp/view?usp=sharing",
            "https://drive.google.com/file/d/1n3q3yd40r9qh2sf8kXNEg_oa2Zm8LX9o/view?usp=sharing",
            "https://drive.google.com/file/d/1wnegX8r1YThlRaNaY_0OjVYbP7r2cftJ/view?usp=sharing",
            "https://drive.google.com/file/d/1p0ZghcZg6oFF4uMbHRMayEUHjiWPFvrJ/view?usp=sharing",
            "https://drive.google.com/file/d/1_uwbaoCp_J5fGf5IwQC4noLi8pCzrcZ9/view?usp=sharing",
            "https://drive.google.com/file/d/1FR5uHuOn89w06jO5_SEJNs3IhX0X2YgS/view?usp=sharing",
            "https://drive.google.com/file/d/1uJ2k0qslPKwFuukNVL6YXKLBFKRzzEnq/view?usp=sharing",
            "https://drive.google.com/file/d/1BGf0uMqdHzsTeKjt5413-ZRNKt3VcHle/view?usp=sharing"};

    //String [] url={"","","","","","","","","","","","","","","","","","","",""}


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thriller);
        this.setTitle("ThrillerBook");
        recyclerView = (RecyclerView) findViewById(R.id.booklist);
        //Toast.makeText(romance.this, "Please", Toast.LENGTH_SHORT).show();

        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter=new RecyclerAdapterdrama(this,viewItem,images,url);
        recyclerView.setAdapter(mAdapter);
        additemsfromjson();


    }
    private void additemsfromjson() {
        try {
            String jsondata=readjsondatafromfile();
            JSONArray jsonArray=new JSONArray(jsondata);
            for (int i=0;i<jsonArray.length();++i){
                JSONObject itemobj=jsonArray.getJSONObject(i);
                String name=itemobj.getString("bookname");
                String rating=itemobj.getString("rating");
                // String image=itemobj.getString("cover");
                String author=itemobj.getString("author name");
                //String image=itemobj.getString("cover");
                String desc=itemobj.getString("description");
                dramatic t=new dramatic(name,author,desc,rating);
                viewItem.add(t);


                // Toast.makeText(romance.this, "Please add", Toast.LENGTH_SHORT).show();


            }
        }catch (Exception e){
            Log.d(TAG,"additemsfromjson",e);
        }
    }
    private String readjsondatafromfile() throws IOException {
        InputStream inputStream=null;
        StringBuilder builder=new StringBuilder();
        try {
            String jsonstring=null;
            inputStream=getResources().openRawResource(R.raw.thriller);
            BufferedReader bufferedReader=new BufferedReader(
                    new InputStreamReader(inputStream,"UTF-8"));

            while ((jsonstring=bufferedReader.readLine())!=null){
                builder.append(jsonstring);
                // Toast.makeText(romance.this, "Please read", Toast.LENGTH_SHORT).show();

            }

        }finally {
            if (inputStream!=null){
                inputStream.close();
            }
        }
        return new String(builder);
    }
}
