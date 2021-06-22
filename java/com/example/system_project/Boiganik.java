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

public class Boiganik extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Object> viewItem=new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final String TAG="check";
    int[] images={R.drawable.koyantam,
            R.drawable.niurone_onuronon,
            R.drawable.niurone_abaro_onuronon,
            R.drawable.bisshoyer_indrojal,
            R.drawable.boro_shaheb,
            R.drawable.zafar_science_1,
            R.drawable.zafar_science_2,
            R.drawable.zafar_science_3,
            R.drawable.zafar_science_4,
            R.drawable.zafar_science_5,
            R.drawable.humayan_science_1,
            R.drawable.humayan_science_2,
            R.drawable.humayan_science_3
    };
    String[] url={"https://drive.google.com/file/d/1sa6Zi3_MiUCOJcSv4aangGXfjwMxpwWL/view?usp=sharing",
                 "https://drive.google.com/file/d/1v1fpOJOSfJWEJLXJ1lCLi21QLhNSCKOQ/view?usp=sharing",
                "https://drive.google.com/file/d/1U5PwKPXaBcknjhu6ON5mb3QWFx1tUeXd/view?usp=sharing",
                "https://drive.google.com/file/d/1WmnwKNZtsrj9h_li99svW_BSipABxZqG/view?usp=sharing",
                "https://drive.google.com/file/d/1tyZGwNBs7ef-WBq6Hk8mcjNyTVLiELec/view?usp=sharing",
                "https://drive.google.com/file/d/1ERq4M5QvcWy-Nxmx9CPBzz-h0AxDCnVF/view?usp=sharing",
                "https://drive.google.com/file/d/1ERq4M5QvcWy-Nxmx9CPBzz-h0AxDCnVF/view?usp=sharing",
                "https://drive.google.com/file/d/10i3Y1RaEk-gno_h8WlQUVTIIVpzJO44J/view?usp=sharing",
                "https://drive.google.com/file/d/1RGdOOTF1A3Kt6_c1j3qJnlHI2S0WWJLe/view?usp=sharing",
                "https://drive.google.com/file/d/1Dj5gEyis_g32cApT0P4AeX8xyn8WQdkF/view?usp=sharing",
                "https://drive.google.com/file/d/15QaZs0ZYsvlqPA7cnAQYGOq5ra_pRT9r/view?usp=sharing",
                "https://drive.google.com/file/d/1MneUbLQazOld16fB1JFZLpiiBsFskwnD/view?usp=sharing",
                "https://drive.google.com/file/d/1t4-DMWv-K-yfx4jayP723j0-BHTyB3No/view?usp=sharing",
    };



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boiganik);
        this.setTitle("বৈজ্ঞানিক কল্পকাহিনী");
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
            inputStream=getResources().openRawResource(R.raw.science);
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
