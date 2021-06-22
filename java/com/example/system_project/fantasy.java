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

public class fantasy extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Object> viewItem=new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final String TAG="check";
    int[] images={R.drawable.the_silmarillion,
            R.drawable.the_belgariad,
            R.drawable.the_lord_of_the_rings,
            R.drawable.the_night_circus,
            R.drawable.good_omens,
            R.drawable.royal_assassin,
            R.drawable.the_last_wish
            ,R.drawable.the_way_of_kings,
            R.drawable.the_hobbit
            ,R.drawable.american_gods,
            R.drawable.the_mists_of_avalon,
            R.drawable.the_subtle_knife,
            R.drawable.the_name_of_the_wind,
            R.drawable.northern_lights,
            R.drawable.the_eye_of_the_world,
            R.drawable.the_chronicles_of_narnia,
    R.drawable.the_white_rose,
            R.drawable.a_wizard_of_earthsea,
    R.drawable.alice_adventure,
    R.drawable.jonathan_strange_mr_norrell};
    String [] url={"https://drive.google.com/file/d/1rP0LGTCuoJY2OomeYjzpeB6AzPJO1d3u/view?usp=sharing",
            "https://drive.google.com/file/d/1LUOMPSvwHBMQEv3VEgjL_LXCgz77goFP/view?usp=sharing",
            "https://drive.google.com/file/d/1uYPiy2HSdUgmyWsA2uRJixWEIH1inPhU/view?usp=sharing",
            "https://drive.google.com/file/d/1NJiDgOpoU9AIWCoOXJW8MqQKyMtFeOib/view?usp=sharing",
            "https://drive.google.com/file/d/16yQ58uWYSLORXx7YgfxxbzWLFX_0Y9Td/view?usp=sharing",
            "https://drive.google.com/file/d/1Fg7bfVyxjPNKGO9l-9IxlO6bpY28iYZl/view?usp=sharing",
            "https://drive.google.com/file/d/1IVydUsxCz7hPxtJKl9eaYRUyKcihTwnx/view?usp=sharing",
            "https://drive.google.com/file/d/1NWWfEmPn8yhq7oG9RB4gpn6sYm0AQLyx/view?usp=sharing",
            "https://drive.google.com/file/d/1hFw0WadriYqB5kpCkLUIQ-7EM5OBNiOe/view?usp=sharing",
            "https://drive.google.com/file/d/15Czx4UVQ7HiKCMDTdfuH_MntVa_odLo-/view?usp=sharing",
            "https://drive.google.com/file/d/1ctbQI3VWJmE3fZIcvZtuvwaNY-6XlC04/view?usp=sharing",
            "https://drive.google.com/file/d/1FtyVtfXPBNQpGXhr4qr0hDfD3DHHX_PC/view?usp=sharing",
            "https://drive.google.com/file/d/1_y2jVaJUj2SFKEkFQlENhntfwYyG7G2z/view?usp=sharing",
            "https://drive.google.com/file/d/1tUAkqrd5fqO3I5qUda-Tfrmj42Sxq13K/view?usp=sharing",
            "https://drive.google.com/file/d/143pdNR9R-wFoxtQEIS3UoGZ5Pzp-oAWj/view?usp=sharing",
            "https://drive.google.com/file/d/1kUT47N7OpsfmTqjFuZeYFCsddKTwhfLK/view?usp=sharing",
            "https://drive.google.com/file/d/17TsLj-shpaESdiyFXV_KwqX5KdTtnPW2/view?usp=sharing",
            "https://drive.google.com/file/d/1wbiPhlRPh3WVbx1iu6UNsbKRPbcnAXNH/view?usp=sharing",
            "https://drive.google.com/file/d/1gcFJSnkFIX1SYk4fsHsKGjvR0awn-nhj/view?usp=sharing",
            "https://drive.google.com/file/d/1qXIeICxXMM7yL-1fza4c6gDvLi1Lp6QB/view?usp=sharing"};

//missing 1 book only 19

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fantasy);
        this.setTitle("Fantasybook");
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
                dramatic Fantasy=new dramatic(name,author,desc,rating);
                viewItem.add(Fantasy);


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
            inputStream=getResources().openRawResource(R.raw.fantasy);
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
