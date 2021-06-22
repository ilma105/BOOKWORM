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
import com.example.system_project.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class romance extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Object> viewItem=new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final String TAG="check";
    int[] images={R.drawable.redem,
            R.drawable.hungergames,
            R.drawable.paradise,
            R.drawable.wind,
            R.drawable.price,
            R.drawable.mebeforeyou,
            R.drawable.addicted,
            R.drawable.bride
            ,R.drawable.outlander,
            R.drawable.kaherine,
            R.drawable.toallthe,
            R.drawable.jane,
            R.drawable.geisha,
            R.drawable.notebook,
            R.drawable.still_love_you,
            R.drawable.fangilr,
            R.drawable.wood,
            R.drawable.bottle,
            R.drawable.gatsbygreat,
            R.drawable.attachments};
    String[] url={
            "https://drive.google.com/file/d/13bMKgC6UBHAPkih7f1w4qqSSGRxI1cXz/view?usp=sharing",
            "https://drive.google.com/file/d/1t2iHacfz9knJXHSR10WFOZhG61jHTfHY/view?usp=sharing",
            "https://drive.google.com/file/d/1yI9vCvTzLF20pVQGrQ_1YaFQtd_EZCdu/view?usp=sharing",
            "https://drive.google.com/file/d/1vFqO1v5FMBAoohvR8jHeGtNHhrboTxQ2/view?usp=sharing",
            "https://drive.google.com/file/d/1kAqDYHKB_5XS_vdvSDBu-ZOwmDprSL2h/view?usp=sharing",
            "https://drive.google.com/file/d/12Q8sErwqh2wZTX6cLfQzF55WR9gvV3xJ/view?usp=sharing",
            "https://drive.google.com/file/d/1Zba6xItGddM5D-CjKiS3gYoNnCWAvkcq/view?usp=sharing",
            "https://drive.google.com/file/d/1QAVnEJ8sujO8aZfq71Za3wBEw2WOOpxQ/view?usp=sharing",
            "https://drive.google.com/file/d/1y2BRfMqyoJu2mlStmhV76OvqOzr-VRhq/view?usp=sharing",
            "https://drive.google.com/file/d/1Pj3TyLOEunEqVdbFqW360leveEiJLLLs/view?usp=sharing",
            "https://drive.google.com/file/d/1CNqNlGOf2U52c13Xknk1iCtzyk8TLIcf/view?usp=sharing",
            "https://drive.google.com/file/d/1294DZyVqCE3bWRjm_Oc6D6NAwVzDChG3/view?usp=sharing",
            "https://drive.google.com/file/d/1UhQEMH1c3C5nUuxueIRyvK9E2dcqyRjG/view?usp=sharing",
            "https://drive.google.com/file/d/117wPmFb__92arlLAXDq8NXFLTnczO-O-/view?usp=sharing",
            "https://drive.google.com/file/d/1nC3vF5e93mFZUShaPBmG8_tGTOy55M8L/view?usp=sharing",
            "https://drive.google.com/file/d/1i8bi9ehK4fnlL4BFg64HxbFN0oazBSvZ/view?usp=sharing",
            "https://drive.google.com/file/d/1eqq0zAeVOFRcg862KP9IhN6NVK5EPxvO/view?usp=sharing",
            "https://drive.google.com/file/d/1X-Jo-6fxK3f3srK7M5OcQ7pychcn6-9N/view?usp=sharing",
            "https://drive.google.com/file/d/1-ypoNyPsvipFXKq5X3-chhBvfF8O7Hrt/view?usp=sharing",
            "https://drive.google.com/file/d/1cYjXoDLg1Qa1tfZln0-j_ieH47bl15bX/view?usp=sharing"};


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_romance);
        this.setTitle("romanticbook");
        recyclerView = (RecyclerView) findViewById(R.id.booklist);
      //  Toast.makeText(romance.this, "Please", Toast.LENGTH_SHORT).show();

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
                String image=itemobj.getString("cover");
                String desc=itemobj.getString("description");
                dramatic Romantic=new dramatic(name,author,desc,rating);
                viewItem.add(Romantic);


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
            inputStream=getResources().openRawResource(R.raw.romanticbook);
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
