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

public class drama extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Object> viewItem=new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final String TAG="check";
    int[] images={R.drawable.thehelp,
            R.drawable.bookthief,
            R.drawable.suns,
            R.drawable.kite,
            R.drawable.mocking,
            R.drawable.lies,
            R.drawable.bear,
            R.drawable.fault,
            R.drawable.litttlefire
            ,R.drawable.gone_girl,
            R.drawable.siskeep,
            R.drawable.hamlet,
            R.drawable.gatsby,
            R.drawable.pi,
            R.drawable.macbeth,
            R.drawable.alche,
            R.drawable.normalpeople,
            R.drawable.romeo,
    R.drawable.othello,
   R.drawable.the_tempest };
String[] url={"https://drive.google.com/file/d/1p4mJgBZOmMJyZW1c1VRtOIoMFL5iuQjW/view?usp=sharing",
        "https://drive.google.com/file/d/19JUUu3tTAX1PnGOB_9HhFazaSZ2eUpP7/view?usp=sharing",
        "https://drive.google.com/file/d/1FqdEsZzn8OnMaN2LFenT9KpDQ-qKDUxf/view?usp=sharing",
        "https://drive.google.com/file/d/1nyA5UELWbk67ZYp9ez_biFMuErevB47c/view?usp=sharing",
        "https://drive.google.com/file/d/1uDFY8j-erHU8a11arAyCGd3UHK-89gvi/view?usp=sharing",
        "https://drive.google.com/file/d/13IAlHC-B-AskHPKb1XkvAtxfou_V9SW7/view?usp=sharing",
        "https://drive.google.com/file/d/1DZ5PQGtxILbA1rtGSSI5UEZmBqnLZ9K6/view?usp=sharing",
        "https://drive.google.com/file/d/1QR0x-0FgqOhMtzcrQtGdeG1NYPlKj8EL/view?usp=sharing",
        "https://drive.google.com/file/d/1F4beaUOslM5HH4AYPRi1cyAymicRQrnD/view?usp=sharing",
        "https://drive.google.com/file/d/1i4sf-1w1mDtCk8cmLcsTOXiBR9XSb5E3/view?usp=sharing",
        "https://drive.google.com/file/d/11O3UF3Qjlocj0xzEq5gI5nGpl1RCHe7O/view?usp=sharing",
        "https://drive.google.com/file/d/1XYH8v8F4duaWYJ0CrMRzHdkPOdJQFEPG/view?usp=sharing",
        "https://drive.google.com/file/d/1T-be8zplBfRt1hAG5h9VAny2lLKVfZMF/view?usp=sharing",
        "https://drive.google.com/file/d/1P9x36HO_cpUtzjWfGB8crvHkXXIoi1qC/view?usp=sharing",
        "https://drive.google.com/file/d/10y2CT-eVCRnk3sx-hrH21aATYbaolV91/view?usp=sharing",
        "https://drive.google.com/file/d/1vZj0UIaizSFY307pMNDtUzUOdZ_M9B8u/view?usp=sharing",
        "https://drive.google.com/file/d/17klyQUdTE8QN-sYf2LAK2OeZE7xHQ8Gu/view?usp=sharing",
        "https://drive.google.com/file/d/1duX-kFX_w4HKpyuOnRfp4bFVanGQQett/view?usp=sharing",
        "https://drive.google.com/file/d/1vHIrdVSVskgZgP87SVrbStoOwVgHLlok/view?usp=sharing",
        "https://drive.google.com/file/d/1tDNRt-ZI9aYKWQQUHuuHDI31MH0yM4Fa/view?usp=sharing"};



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drama);
        this.setTitle("Dramabook");
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
                String image=itemobj.getString("cover");
                String author=itemobj.getString("author");

                String desc=itemobj.getString("description");
                dramatic Dramatic=new dramatic(name,author,desc,rating);
                viewItem.add(Dramatic);

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
            inputStream=getResources().openRawResource(R.raw.drama);
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
