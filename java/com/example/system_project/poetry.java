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

public class poetry extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Object> viewItem=new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final String TAG="check";
    int[] images={R.drawable.duino,
            R.drawable.rainerm,
            R.drawable.fourquaters,
            R.drawable.sidewalkends,
            R.drawable.crush,
            R.drawable.dumal,
            R.drawable.frost,
            R.drawable.prophet,
            R.drawable.ariel
            ,R.drawable.milk_and_honey,
            R.drawable.the_sun_and_her_flowers,
            R.drawable.collecedpoems,
            R.drawable.lunchpoems,
            R.drawable.leaves_of_grass,
            R.drawable.wasteland,
            R.drawable.tennyson,
            R.drawable.inferno,
            R.drawable.cantod,
            R.drawable.the_odyssey,
            R.drawable.venice};
    String[] url= {"https://drive.google.com/file/d/10ERMM7SYf3YJD1rqegyUgLWKsykgBB4F/view?usp=sharing",
                    "https://drive.google.com/file/d/1yzpscSb8wlRHzJ_LbqVjTA-em7YX7qE-/view?usp=sharing",
                    "https://drive.google.com/file/d/12AMyj5eRUQIy2sb6oRNLTUda8kFUz-4m/view?usp=sharing",
                    "https://drive.google.com/file/d/1XXAJ9LpTKZH5vOtiLgiC7c0zCjKde3xb/view?usp=sharing",
                    "https://drive.google.com/file/d/1m1ZYuddOV7dBF-5pUwAKqZKM0cU7U322/view?usp=sharing",
                    "https://drive.google.com/file/d/1uXib3eOo1tWKIZZNzWgAn2ORoLC6U44c/view?usp=sharing",
                    "https://drive.google.com/file/d/1DSMwSxmD-tK4isjWS7WIm9iccNAv_9ty/view?usp=sharing",
                    "https://drive.google.com/file/d/189Bk50wqYk2gBTWVwa91kyXGEsVs5Ugo/view?usp=sharing",
                    "https://drive.google.com/file/d/1cB-C3FBgbm6Dw3YlNwrt1qONJ-k6_8Kk/view?usp=sharing",
                    "https://drive.google.com/file/d/1r0NtMpulNCpGO4PBsHqoGWVagS92FYoR/view?usp=sharing",
                    "https://drive.google.com/file/d/13aJJsD-SOudZO_sV8qn5NaHmiP5CiE9p/view?usp=sharing",
                    "https://drive.google.com/file/d/1a0Ur8KuPaue4IsbOJwGQD6I1IY_Pn_VY/view?usp=sharing",
                    "https://drive.google.com/file/d/1E8My1vS0CE2SpmpKhbgi8DeOxMsoxQoV/view?usp=sharing",
                    "https://drive.google.com/file/d/1jXvWEfZMFK7eaK8YIiTft31lAX-yjx9x/view?usp=sharing",
                    "https://drive.google.com/file/d/1eBYMo9g1avQl0Vfre-mvevRW9b-8F54H/view?usp=sharing",
                    "https://drive.google.com/file/d/1LniN9Cz7qsvhcr3F4kdabboAC_2HNLpp/view?usp=sharing",
                    "https://drive.google.com/file/d/1qKlSuyY3rfIEtU7DPVeY-AD1v8ckX3Eq/view?usp=sharing",
                    "https://drive.google.com/file/d/1AXxwAb2XKADtNiZzWBNC0yveTC8QDXmB/view?usp=sharing",
                    "https://drive.google.com/file/d/17aK3wbK0dNJNC4Irbcezz5yIGgNV9N8n/view?usp=sharing",
                    "https://drive.google.com/file/d/1Zv4ohs32LBWsTik7REBRK36esYG0vFH1/view?usp=sharing"};


    //int image[]={R.drawable.image2,R.drawable.image2,R.drawable.
    //      image2,R.drawable.image2,R.drawable.image2,R.drawable.image2,
    //    R.drawable.image2,R.drawable.image2,R.drawable.image2};

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poetry);
        this.setTitle("poetrybook");
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
                //String image=itemobj.getString("cover");
                String desc=itemobj.getString("description");
                dramatic Poetryic =new dramatic(name,author,desc,rating);
                viewItem.add(Poetryic);


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
            inputStream=getResources().openRawResource(R.raw.poetry);
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
