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

public class Goyenda extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Object> viewItem=new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final String TAG="check";
    int[] images={R.drawable.ghatok_rupak_saha,
            R.drawable.agantuk,
            R.drawable.goyenda_uponnash_swapan_kumar,
            R.drawable.polatoker_shondhane,
            R.drawable.chathurta_tadanta,
            R.drawable.sherlock_one,
            R.drawable.sherlock_two,
            R.drawable.faand};
    String[] url={"https://drive.google.com/file/d/1mT6Zb1vtuRlPENp5xVvIywiqrE7UI7hM/view?usp=sharing",
            "https://drive.google.com/file/d/1j0VSM4gswxXce9n9eN6tgfSZz21_9k0k/view?usp=sharing",
            "https://drive.google.com/file/d/1JgJ95-gynR2EHE9O4qwjhdj-nLpS4_qH/view?usp=sharing",
            "https://drive.google.com/file/d/14Z3i_FyYtK68025SIleGckRX9mcDdZ5G/view?usp=sharing",
            "https://drive.google.com/file/d/1DhaBkLW-OvSpJSKxa4V8c7vJLcSHyo0r/view?usp=sharing",
            "https://drive.google.com/file/d/1W_ZkBNO8vcn-9UY52brvTIg87Fk31Bly/view?usp=sharing",
            "https://drive.google.com/file/d/157QnZhNJtFf53FQof8BdIE_OiG5WNLBh/view?usp=sharing",
            "https://drive.google.com/file/d/17y8qyYCX6Q868AWtTdoOvuki1mUnIEhw/view?usp=sharing"};



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goyenda);
        this.setTitle("গোয়েন্দা");
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
                String name=itemobj.getString("বইয়ের নাম");
                String rating=itemobj.getString("রেটিং");
                // String image=itemobj.getString("cover");
                String author=itemobj.getString("লেখকের নাম");
                //String image=itemobj.getString("cover");
                String desc=itemobj.getString("বিবরণ");
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
            inputStream=getResources().openRawResource(R.raw.goyenda);
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
