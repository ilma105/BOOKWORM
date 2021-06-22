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

public class history extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Object> viewItem=new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final String TAG="check";
    int[] images={
            R.drawable.the_nightingale,
            R.drawable.all_the_light_we_cannot_see,
            R.drawable.the_other_boleyn_girl,
            R.drawable.the_book_of_negroes,
            R.drawable.gone_with_the_wind_cover,
            R.drawable.war_and_peace,
            R.drawable.the_far_pavilions
            ,R.drawable.alias_grace,
            R.drawable.bookthief,
            R.drawable.the_pillars_of_the_earth,
            R.drawable.an_army_at_dawn,
            R.drawable.i_claudius,
            R.drawable.the__three_musketeers,
            R.drawable.a_tale_of_two_cities,
            R.drawable.the_name_of_the_rose,
            R.drawable.the_guns_of_august,
            R.drawable.a_history_of_ancient_rome,
            R.drawable.oscar_and_lucinda,
    R.drawable.khangengh,
    R.drawable.the_painted_girls};
    String [] url={"https://drive.google.com/file/d/1KFOd2n37NYBREwKF7MMAk0CfqXEQNs_G/view?usp=sharing",
            "https://drive.google.com/file/d/1hqwJelgHP9RMIkcm_UG6r9am1FOLgTwv/view?usp=sharing",
            "https://drive.google.com/file/d/1n3yPeVwl-YX9SUNKXrjhg3rb7eNZjQYb/view?usp=sharing",
            "https://drive.google.com/file/d/1LRgjW_O6RfQKqUaz68NFnbc_tsKL90R9/view?usp=sharing",
            "https://drive.google.com/file/d/1XoCHO64Bvo7YjecwXoM25j-7gk7WxYT-/view?usp=sharing",
            "https://drive.google.com/file/d/1t3EifaW8N5n-hbz5thtIWA-Yy9cKuUx9/view?usp=sharing",
            "https://drive.google.com/file/d/10eRFrZGYh4ZsrXY-Wa48XdOyHRtObRAJ/view?usp=sharing",
            "https://drive.google.com/file/d/177-ioM4N3Cyp3UcACflSn4xAGbIxo8Xc/view?usp=sharing",
            "https://drive.google.com/file/d/1h_cF9voOsbAOIxQW6Zw27Qu3mU6EPZXb/view?usp=sharing",
            "https://drive.google.com/file/d/1s6jQPLLzcdsuAXYOkpdFFYMVf_PhXfTs/view?usp=sharing",
            "https://drive.google.com/file/d/1ux1EPTonY9lXHmXsKu4ueWcUa4mGHewD/view?usp=sharing",
            "https://drive.google.com/file/d/1wzD5PVJ7sBGJqDvmMxJsqBwCqvNmYgxA/view?usp=sharing",
            "https://drive.google.com/file/d/1GcrnvgZebJ8xpOztocNWxinRPiAFibcz/view?usp=sharing",
            "https://drive.google.com/file/d/15K1eJs1h1PgimsAqFZmroEKbJR8fHDZS/view?usp=sharing",
            "https://drive.google.com/file/d/1TGDUhqDA1KN3VqKBXglb8ALKe4pZ-L49/view?usp=sharing",
            "https://drive.google.com/file/d/1OIvRzkzCb83YQJlr91Lq5nbIjezY-FyX/view?usp=sharing",
            "https://drive.google.com/file/d/14MasAE2YBXSQZ49wXPxDXzbnuTOXGRot/view?usp=sharing",
            "https://drive.google.com/file/d/1uLd_ixfT8gVEczsGXRZPkB0lsCItK_IX/view?usp=sharing",
            "https://drive.google.com/file/d/13Vl3Zct9hnYIUlJZMiDR8yJTghePMmzn/view?usp=sharing",
            "https://drive.google.com/file/d/1BQ-LRSntTtBsISa82LOeFdlmDGnUGvY4/view?usp=sharing"};


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        this.setTitle("HistoryBook");
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
                dramatic his=new dramatic(name,author,desc,rating);
                viewItem.add(his);


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
            inputStream=getResources().openRawResource(R.raw.history);
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
