package com.example.system_project;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class kobita extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Object> viewItem=new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final String TAG="check";
    int[] images={R.drawable.kobor,
            R.drawable.abol_tabol,
            R.drawable.birangona,
            R.drawable.banalata_sen,
            R.drawable.dhusar_pandulipi,
            R.drawable.taslima_nasrin_kobita_shomogro,
            R.drawable.bela_obela_kalbela
            ,R.drawable.agni_bina,
            R.drawable.sanchita
            ,R.drawable.bisher_bashi,
            R.drawable.rakhali_gaan,
            R.drawable.sanchita,
            R.drawable.meghnadh_badh_kabya,
            R.drawable.sonali_kabin,
            R.drawable.ekei_ki_bole_sovvota
    };
    String [] url={"https://drive.google.com/file/d/1NvcGw72F7h5Dv7ucTYud_tRRjvAJC0Ed/view?usp=sharing",
            "https://drive.google.com/file/d/155plDkjtaQ1nP7JdkczKLbq3CExt7Sqo/view?usp=sharing",
            "https://drive.google.com/file/d/1Cicc3GwQF_MSJaQ6yt4kAOmsuhIpdwBM/view?usp=sharing",
            "https://drive.google.com/file/d/1VKY2aFnhGqKCQ9OYnKDK5frLMQ9F2qNU/view?usp=sharing",
            "https://drive.google.com/file/d/1e0CUxHAvyShUPyB5G0kasVUfsHa5RbCQ/view?usp=sharing",
            "https://drive.google.com/file/d/1s3RDCl8kVT8oFIwYqHEwKJf2GKAIOOaF/view?usp=sharing",
            "https://drive.google.com/file/d/1rgyW9SlNA4ElmXML7eeCKJsWcfX3pI1F/view?usp=sharing",
            "https://drive.google.com/file/d/13qCWjCnDLH-Pukqb5o1AcIFkhCyiE3W0/view?usp=sharing",
            "https://drive.google.com/file/d/1vdsuA1HoMiGWvXg-2-qnvZHRVebtxPVt/view?usp=sharing",
            "https://drive.google.com/file/d/1oZ8hgw6jTJ6-eJiFakp9EsYDFTmkQxRc/view?usp=sharing",
            "https://drive.google.com/file/d/19H4o7qs-GBPr4Q5uHSfl5oY8qErYiwpU/view?usp=sharing",
            "https://drive.google.com/file/d/1-4Xdl_6WN81w9zAAAMC5ze9AGAbOkEgH/view?usp=sharing",
            "https://drive.google.com/file/d/1KpXFCjrEq5KLqgsOZxaeBTX894sx6N-g/view?usp=sharing",
            "https://drive.google.com/file/d/1rZzGAkNvZ2Zwe1NWdT5o9f5TWVpJDxXX/view?usp=sharing",
            "https://drive.google.com/file/d/1MPv1wP_Gf-0sxYrJTLFtNuftLCDRZLv1/view?usp=sharing"};
//String [] url={"","","","","","","","","","","","","","","","","","","",""}
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kobita);
        this.setTitle("কবিতা");
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
                String author=itemobj.getString(" কবির নাম");
                //String image=itemobj.getString("cover");
                String desc=itemobj.getString("বিবরণ");
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
            inputStream=getResources().openRawResource(R.raw.kobitas);
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
