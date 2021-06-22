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

public class mystery extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Object> viewItem=new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final String TAG="check";
    int[] images={R.drawable.the_silent_patient,
            R.drawable.completepoems,
            R.drawable.godfather,
            R.drawable.seasons,
            R.drawable.shadowwind,
            R.drawable.jackal,
            R.drawable.cristo,
            R.drawable.crimeandpunish,
            R.drawable.murder_on_the_orient_express,
            R.drawable.neddle,
            R.drawable.cuckooo,
            R.drawable.zoombie
            ,R.drawable.rivermys,
            R.drawable.the_name_of_the_rose,
            R.drawable.the_girl_with_the_dragon_tattoo,
            R.drawable.goneforgood,
            R.drawable.the_guest_list,
            R.drawable.womanwhite,
            R.drawable.thinman,
            R.drawable.deceptionpoint};
    String[] url={"https://drive.google.com/file/d/1NkLdX4CscX75Zlcx0zw-vGcH4XZaHd1F/view?usp=sharing",
            "https://drive.google.com/file/d/1OGWrvkXkGdwD8b_xjHyCNg9EcsZkW5RS/view?usp=sharing",
            "https://drive.google.com/file/d/1H2CyigHcT3CYk9cBHxHACXJ8pGW6JDrw/view?usp=sharing",
            "https://drive.google.com/file/d/1qhs6xYIz0hvABAl7y6c8Cp7fGwbR20R8/view?usp=sharing",
            "https://drive.google.com/file/d/1VjFUBawI1q53aA3Ob-9rZRRLGllkng3S/view?usp=sharing",
            "https://drive.google.com/file/d/1mJq0FUTFyTwOQMIDtfJ8Yzw3DAj9O9dm/view?usp=sharing",
            "https://drive.google.com/file/d/1gGJqE-QJIHYP0gmzeJSNdog7fuE1Hbn1/view?usp=sharing",
            "https://drive.google.com/file/d/11VBOmU3aw2KcmF-WoFrMZ8IzhDvYlbmo/view?usp=sharing",
            "https://drive.google.com/file/d/1Z6KgqQrH5llKHaWz64wHyWxzmZBsGGyV/view?usp=sharing",
            "https://drive.google.com/file/d/1jacD1OYK3EG7Kt3nq-ms7sQBPub3caGd/view?usp=sharing",
            "https://drive.google.com/file/d/1H-dTBMnKPpjnxsmRl9sJGOyiSwYMqqY9/view?usp=sharing",
            "https://drive.google.com/file/d/1xH9IrRCouwvFpSrlLcYkWwSGk-E2M4oQ/view?usp=sharing",
            "https://drive.google.com/file/d/1h2WAUpb-bHLlxeIGXfAeBOr4YGC-wtSx/view?usp=sharing",
            "https://drive.google.com/file/d/1BfnKR-9N-rvhwKW0P-UFdOd9FFXDpeMl/view?usp=sharing",
            "https://drive.google.com/file/d/1qADFgAxE-8sVVVXKgTdBOfbm82hQFRfc/view?usp=sharing",
            "https://drive.google.com/file/d/1RiyeibSpvu3tfaB_FXaFyY-wmsjR76PT/view?usp=sharing",
            "https://drive.google.com/file/d/1a01EmML0VDrghCX_p0dtkdscxuwS4V95/view?usp=sharing",
            "https://drive.google.com/file/d/1y5Dp66KWWmHZUESMl48OAqJDqRIbajy0/view?usp=sharing",
            "https://drive.google.com/file/d/1vDXRD0KAXVv48jCwFbCV83gsI8J1VGV9/view?usp=sharing",
            "https://drive.google.com/file/d/1IT_3au_EGx4bGKaXVg1hn0KjKbKcpYyJ/view?usp=sharing"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mystery);
        this.setTitle("mysterybook");
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
                String author=itemobj.getString("author");
                //String image=itemobj.getString("cover");
                String desc=itemobj.getString("description");
                String image=itemobj.getString("cover");
                dramatic Mysterious =new dramatic(name,author,desc,rating);
                viewItem.add(Mysterious);


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
            inputStream=getResources().openRawResource(R.raw.mysterybook);
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
