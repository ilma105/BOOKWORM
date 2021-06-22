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

public class horror extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Object> viewItem=new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final String TAG="check";

    int[] images={R.drawable.bird_box,
            R.drawable.the_exorcist,
            R.drawable.interview_with_the_vampire,
            R.drawable.it,
            R.drawable.the_haunting_of_hill_house,
            R.drawable.the_shining,
            R.drawable.at_the_mountains_of_madness,
            R.drawable.carrie,
            R.drawable.hell_house,
            R.drawable.the_ruins,
            R.drawable.i_am_legend,
            R.drawable.nos4a2,
            R.drawable.house_of_leaves,
            R.drawable.dracula,
            R.drawable.the_stand,
            R.drawable.the_woman_in_black,
            R.drawable.frankenstein,
            R.drawable.the_tell_tale_heart,
            R.drawable.the_turn_of_the_screw
    ,R.drawable.a_head_full_of_ghosts};
    String [] url=
            {
            "https://drive.google.com/file/d/1s1v4Z2jQqFSDC6eOaAHPs2CI-E53BCma/view?usp=sharing",
            "https://drive.google.com/file/d/1qUVP7_sfMMGeNQnDrFEUg95GoDpRPVfP/view?usp=sharing",
            "https://drive.google.com/file/d/1njDDil7MNX0mumIAkMS_fHqS1mcUzIq3/view?usp=sharing",
            "https://drive.google.com/file/d/1YfwxscauJFy02vKOcBQ0BHaNwUY6K8EL/view?usp=sharing",
            "https://drive.google.com/file/d/1hMv_o_zf3383v91a6kLRP6uo9H4yE0rf/view?usp=sharing",
            "https://drive.google.com/file/d/1fEAv6_w6dO8nRV6zSJM5tKXmSvo65s6B/view?usp=sharing",
            "https://drive.google.com/file/d/1wClBgLTjHRz4v4rYdNjoykDi5Eo2Xx1o/view?usp=sharing",
            "https://drive.google.com/file/d/1l9Ngp-U41AFZaKKz1J9re832Emu-hjQQ/view?usp=sharing",
            "https://drive.google.com/file/d/1ViXOiAyyo6fJvIDZY9v7inUHus6cU5vf/view?usp=sharing",
            "https://drive.google.com/file/d/1KQt1hpIigBkhDhiQzjmShPzxil556AmE/view?usp=sharing",
            "https://drive.google.com/file/d/1ZUlGyw5c5BsEU-PuF-bV8aDRJ6YA9qBe/view?usp=sharing",
            "https://drive.google.com/file/d/1X_VFXXFQiqnPavFFD0Kjj3bT4PwqmH6k/view?usp=sharing",
            "https://drive.google.com/file/d/1P5GAif6xiTpCZ4Jl_UXaNllbGyqdwrgW/view?usp=sharing",
            "https://drive.google.com/file/d/1JneLK8JfQ_8j46N8QgWQ5sQ5MglY8qcI/view?usp=sharing",
            "https://drive.google.com/file/d/1lX9-7KiDBod3utrsQDBjJ9h4r1Hy81MU/view?usp=sharing",
            "https://drive.google.com/file/d/16EQtrK9mRYZDOaZQ4VOekCRvl72v7tEC/view?usp=sharing",
            "https://drive.google.com/file/d/1d-WwIlwXITUPXNUy-AmwClACS9ll9lef/view?usp=sharing",
            "https://drive.google.com/file/d/1YpZnzWgMAEBbzy0Y7xenld2AOAbjsYM6/view?usp=sharing",
            "https://drive.google.com/file/d/1ncFeC_ahFEmtL6N-V1xsfaTK4vwTO2Y3/view?usp=sharing",
            "https://drive.google.com/file/d/1HchH6tcYzWcTTrxycjZAaCW6ungsMlNd/view?usp=sharing"};


//1 book missing
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horror);
        this.setTitle("HorrorBook");
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
                dramatic HORROR=new dramatic(name,author,desc,rating);
                viewItem.add(HORROR);


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
            inputStream=getResources().openRawResource(R.raw.horror);
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
