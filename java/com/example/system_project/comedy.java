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

public class comedy extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Object> viewItem=new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final String TAG="check";

    int[] images={R.drawable.hyperbole_and_a_half,
            R.drawable.good_omens,
            R.drawable.borncrime,
            R.drawable.three_men_in_a_boat,
            R.drawable.equal_rights,
            R.drawable.the_diary_of_a_nobody,
            R.drawable.the_overdue_life_of_amy_byler,
            R.drawable.a_confederacy_of_dunces,
            R.drawable.bossy
    ,R.drawable.lets_pretend_this_never_happened,
            R.drawable.carry_on_jeeves,
            R.drawable.heading_over_the_hill,
            R.drawable.where_you_go_bernadette,
            R.drawable.yesplease,
            R.drawable.portnoy_complaint,
            R.drawable.barrel_fever,
            R.drawable.big_trouble,
            R.drawable.lucky_jam,
            R.drawable.cold_comfort_farm,
            R.drawable.scoop
    };

    String [] url={"https://drive.google.com/file/d/1G9kCX8J9-ryk2NOeCKSGuwA9SL_TS0mr/view?usp=sharing",
            "https://drive.google.com/file/d/1VGBUHBXmN3DtMZbfzGXkv5WMk1Tu4o9v/view?usp=sharing",
            "https://drive.google.com/file/d/1uSt34dPBhRxdDcFw6uzND08pf7gqVWR8/view?usp=sharing",
            "https://drive.google.com/file/d/1NCzhGczH6RthT3_PyJKVwJRn21Dj50dD/view?usp=sharing",
            "https://drive.google.com/file/d/1tGdabqOBZEu-sbgWf9aGFxXG5p6J877Z/view?usp=sharing",
            "https://drive.google.com/file/d/19CcvjndoYbgYlaKsrBnFlHOvzDRH0GA0/view?usp=sharing",
            "https://drive.google.com/file/d/1qe3t82VkbIgAkxOCjDUAdVE8Ej2YDT1i/view?usp=sharing",
            "https://drive.google.com/file/d/1XutcwuN78tW9FqsGlCCZnJVk_azERMri/view?usp=sharing",
            "https://drive.google.com/file/d/1PehtjjPi6NZVaEk-wwMt3z7gj8Juk6jA/view?usp=sharing",
            "https://drive.google.com/file/d/13r0KDnVHWBKpMBtLMbR9ftFJxOGHRola/view?usp=sharing",
"https://drive.google.com/file/d/1udj5-NUWnFRgg8B3ddziKHCnADo6tb2E/view?usp=sharing",
            "https://drive.google.com/file/d/1WLKKERULk6DHVdXBLs05sXVoGACaGXGw/view?usp=sharing"
            ,"https://drive.google.com/file/d/1wSLq3TzOY-ukKdIkhqOsFlqOCuLZYZmc/view?usp=sharing",
            "https://drive.google.com/file/d/10NhqT8WRCMpdGSbziN6Kx3XU7_ng11NZ/view?usp=sharing",
            "https://drive.google.com/file/d/1ZCvml6IlpfT6iaRxqTs6YcYYS8rMfLCK/view?usp=sharing",
            "https://drive.google.com/file/d/1oo2WOiQzMloRfrKDgT6vxCLUfYDCeaAz/view?usp=sharing",
            "https://drive.google.com/file/d/1KgMAQH0VakSW4KFBZ2P9gTPEgSFiWXEx/view?usp=sharing",
            "https://drive.google.com/file/d/1yzE5qmKaM94UqaBDiqwD7hBdRGFW1MSw/view?usp=sharing",
            "https://drive.google.com/file/d/1oAnaXKQrt6SP8L8rGEs-MEspY2NPU_bd/view?usp=sharing",
            "https://drive.google.com/file/d/1s2NnFfrsZubVYICzVMEgXdNPVNV-Bk4r/view?usp=sharing"};

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comedy);
        this.setTitle("ComedyBook");
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
                dramatic c=new dramatic(name,author,desc,rating);
                viewItem.add(c);


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
            inputStream=getResources().openRawResource(R.raw.comedy);
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
