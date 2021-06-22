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

public class action extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Object> viewItem=new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final String TAG="check";

    int[] images={R.drawable.academia,R.drawable.ender,R.drawable.catching,R.drawable.crow,
            R.drawable.the_princess_bride,R.drawable.without_remorse,R.drawable.diver,R.drawable.stonr,
            R.drawable.saph
            ,R.drawable.sea,R.drawable.war,R.drawable.crime,R.drawable.seven,R.drawable.wave,
            R.drawable.insur,R.drawable.a_clockwork_orange,R.drawable.the_bourne_identity,
            R.drawable.inca_gold,
            R.drawable.trials,R.drawable.treasure_island};
    String[] url={"https://drive.google.com/file/d/17RM5VyzhW0-yvuqylQ0TMXstysRFd4NT/view?usp=sharing",
            "https://drive.google.com/file/d/1lJ5rT5gs1Rbj9N1mp41Si8JW0CeyG0Ez/view?usp=sharing",
            "https://drive.google.com/file/d/1BOQWz8vDyOTKTAaBwctG_35z4qX3_TT-/view?usp=sharing",
            "https://drive.google.com/file/d/1YcTKy8fWo08E-Qbpnk940x5_fnhXNUTc/view?usp=sharing",
            "https://drive.google.com/file/d/1pN7gxm3CxODgezDLzU9St5mEcfYEJEWH/view?usp=sharing",
            "https://drive.google.com/file/d/1CvLEIVoFi4vtgs48DhhURvlBuxdSuTAF/view?usp=sharing",
            "https://drive.google.com/file/d/1C9Cg-1N2argTKRNPQcvI6Wecq11y6HYS/view?usp=sharing",
            "https://drive.google.com/file/d/1g_Rsz3rd1gomP8pWM4EpvKTuwRejAQBe/view?usp=sharing",
            "https://drive.google.com/file/d/1bFb7hViD5yLAPH7fxhPpzGjQKTZl2dZ9/view?usp=sharing",
            "https://drive.google.com/file/d/1yZ-ucbhcCqqnOvllhsXBaStlIAWYsSow/view?usp=sharing"
            ,"https://drive.google.com/file/d/1YhS7XjZkXouAqkEWQI7AexDwgUfj2YFE/view?usp=sharing",
            "https://drive.google.com/file/d/1RxLacbX7qdFvLNDDVsDoBlxwiF0cFe0P/view?usp=sharing",
            "https://drive.google.com/file/d/1hOX_eQWpsVsb4Oj0K1q8wa4GjBjTv2TV/view?usp=sharing",
            "https://drive.google.com/file/d/1tv6QWWzjZFIQMCR309B6knivBsjE47w6/view?usp=sharing",
            "https://drive.google.com/file/d/1Hf27GgxUxYKFQ6iltzo9LzLdAwPoi5uk/view?usp=sharing",
            "https://drive.google.com/file/d/1PfqOdFAfWyEFA8OVJeKU20Joj6ki-y6v/view?usp=sharing",
            "https://drive.google.com/file/d/16Ee-lmzj_2SY0mT1Q0ZE3K7bmvNfN3M1/view?usp=sharing",
            "https://drive.google.com/file/d/1ziAFbsCg9uyVTS9KOFOY18eUits8ZjLq/view?usp=sharing",
            "https://drive.google.com/file/d/14B8GajTzfnJ7OqH-z_rfBQma5olViHFa/view?usp=sharing",
            "https://drive.google.com/file/d/1ltx6_ptN8OcB3BJ3gk04-Xr8lg9XM_2H/view?usp=sharing"};

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);
        this.setTitle("actionbook");
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
                dramatic Action=new dramatic(name,author,desc,rating);
                viewItem.add(Action);


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
            inputStream=getResources().openRawResource(R.raw.actionbook);
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
